package mut.mutator;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Collection;
import java.util.HashSet;

import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.ToolProvider;

import mut.files.InMemoryFileManager;
import mut.files.InMemoryFileSystem;
import mut.interpreter.InterpreterState;
import mut.junit.MutatorJUnitRunner;
import mut.statistics.StatisticsCollector;
import mut.statistics.Mutation;
import mut.util.Msg;

import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class MutationRunner extends Thread {

	private final Collection<String> sourceFiles;
	private final Collection<String> testFiles;
	private final Collection<String> mutateFrom;
	private final Collection<String> mutateTo;
	private final JavaCompiler compiler;
	private final InMemoryFileManager fileManager;
	private final InMemoryFileSystem fileSystem;
	private final Msg msg;
	private final StatisticsCollector statistics;

	public MutationRunner(InterpreterState state, Collection<String> mutateFrom, Collection<String> mutateTo, InMemoryFileManager fileManager, StatisticsCollector statistics) {
		sourceFiles = state.getSourceFiles();
		testFiles = state.getTestFiles();
		this.mutateFrom = mutateFrom;
		this.mutateTo = mutateTo;
		this.compiler = ToolProvider.getSystemJavaCompiler();
		this.fileManager = fileManager;
		this.fileSystem = fileManager.getFileSystem();
		this.msg = state.getMsg();
		this.statistics = statistics;
	}
	
	@Override
	public void run() {
		if (testFiles.isEmpty()) {
			msg.err("No tests files!");
			return;
		}
		print("Mutating " + getMutationStrings(true) + " to " + getMutationStrings(false) + " in files " + getShortFileNames(sourceFiles) + " with tests " + getShortFileNames(testFiles));
		
		// Ensure the tests pass normally
		Collection<String> compileFiles = new HashSet<String>();
		compileFiles.addAll(sourceFiles);
		compileFiles.addAll(testFiles);
		if(!compile(compileFiles)) {
			msg.err(getId() + ": Supplied code does not compile!");
			return;
		}
		MutatorJUnitRunner origTestRunner = new MutatorJUnitRunner(fileManager.getClassLoader(), fileSystem, msg);

		Result originalResult = null;
		PrintStream stdErr = System.err;
		try {
			// Hide error message from running the tests
			// This is not optimal, if there is a problem while running the tests it causes problems
			System.setErr(new PrintStream(new ByteArrayOutputStream()));
			originalResult = origTestRunner.runTests(testFiles);
		} catch (Exception e) {
			print("Error running tests on unmutated code");
			e.printStackTrace(System.err);
			return;
		} finally {
			// Reset standard error
			System.setErr(stdErr);
		}
		if (originalResult.wasSuccessful()) {
			if(msg.verbosity >= Msg.NORMAL) {
				print("Tests pass on unmutated code: check");
			}
		} else {
			msg.err(getId() + ": Tests do not pass on unmutated code");
			if (msg.verbosity >= Msg.SPARSE) {
				msg.err(getId() + originalResult.getFailureCount() + " tests failed!");
				for (Failure fail : originalResult.getFailures()) {
					msg.err(getId() + fail.getMessage() + " " + fail.getDescription().getDisplayName() + " " + fail.getTrace());
				}
			}
			return;
		}
		
		// Do the mutations
		for (String from : mutateFrom) {
			for (String to : mutateTo) {
				if (!from.equals(to)) {
					if(msg.verbosity >= Msg.NORMAL) {
						print("Mutating " + from + " to " + to);
					}
					for(String filename : sourceFiles) {
						String originalSourceContents = fileSystem.getOriginalSourceFile(filename);
						int currentReplacementIndex = originalSourceContents.indexOf(from);
						while (currentReplacementIndex > -1) {
							String mutatedSourceContents = originalSourceContents.substring(0, currentReplacementIndex) +
									originalSourceContents.substring(currentReplacementIndex).replaceFirst(escapeSpecialChars(from), to);
							fileSystem.addFile(filename, mutatedSourceContents);
							String line = getLocInSourceFromIndex(currentReplacementIndex, originalSourceContents);
							
							if(compile(filename)) {
								if (msg.verbosity >= Msg.VERBOSE) {
									print(getShortFilename(filename) + ": Testing with " + getShortFileNames(testFiles));
								}
								MutatorJUnitRunner testRunner = new MutatorJUnitRunner(fileManager.getClassLoader(), fileSystem, msg);
								try {
									// Hide error message from running the tests
									// This is not optimal, if there is a problem while running the tests it causes problems
									System.setErr(new PrintStream(new ByteArrayOutputStream()));
									if (testRunner.runTests(testFiles).wasSuccessful()) {
										statistics.logSurvivor(filename, new Mutation(line, from, to));
										print(getShortFilename(filename) + " " + line + ": Mutant survived when mutating " + from + " to " + to);
									} else {
										statistics.logKilled(filename, new Mutation(line, from, to));
										if(msg.verbosity >= Msg.NORMAL) {
											print(getShortFilename(filename) + " " + line + ": Tests failed, mutant killed");
										}
									}
								} catch (Exception e) {
									print(getShortFilename(filename) + " " + line + ": Error running tests when mutating " + from + " to " + to);
									e.printStackTrace(stdErr);
								} finally {
									// Reset standard error
									System.setErr(stdErr);
								}
							} else {
								statistics.logStillborn(filename, new Mutation(line, from, to));
								if (msg.verbosity >= Msg.NORMAL) {
									print(getShortFilename(filename) + " " + line + ": Stillborn mutant");
								}
							}
							
							currentReplacementIndex = originalSourceContents.indexOf(from, currentReplacementIndex + 1);
						}
						fileSystem.addFile(filename, originalSourceContents);
						compile(filename);
					}
				}
			}
		}
		print("done");
	}
	
	private String escapeSpecialChars(String in) {
		return in.replaceAll("(?=[]\\[+&|!(){}^\"~*?:\\\\-])", "\\\\");
	}
	
	private String getLocInSourceFromIndex(int index, String originalSourceContents) {
        int numLines = originalSourceContents.substring(0, index).split("\r?\n").length;
		return "line " + numLines;
	}
	
	private boolean compile(String filename) {
		Collection<String> filenames = new HashSet<String>();
		filenames.add(filename);
		return compile(sourceFiles);
	}
	
	private boolean compile(Collection<String> filenames) {
		final DiagnosticCollector<JavaFileObject> diagnostics = new  DiagnosticCollector<JavaFileObject>();
		Collection<JavaFileObject> files = null;
		try {
			files = fileManager.getJavaFileObjectsForInputFromStrings(filenames, JavaFileObject.Kind.SOURCE);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (msg.verbosity >= Msg.VERBOSE) {
			for (String filename : filenames) {
				print("Compiling " + filename);
			}
		}
		
		boolean success = compiler.getTask(null, fileManager, diagnostics, null, null, files).call();
		if (msg.verbosity >= Msg.VERBOSE) {
			print("Compiled Successfully: " + success);
		}
		for(Diagnostic<? extends JavaFileObject> d: diagnostics.getDiagnostics()) {
			if (msg.verbosity >= Msg.NORMAL || d.getCode().equals("compiler.err.cant.resolve.location")) {
				msg.err(getId() + ": " + d.getMessage(null));
			}
		}
		return success;
	}
	
	private void print(String message) {
		msg.msgln(getId() + ": " + message);
	}
	
	private String getMutationStrings(boolean fromStrings) {
		StringBuilder sb = new StringBuilder();
		if (fromStrings) {
			for(String mutation: mutateFrom) {
				sb.append(',');
				sb.append(mutation);
			}
		} else {
			for(String mutation: mutateTo) {
				sb.append(',');
				sb.append(mutation);
			}
		}
		final String ret;
		if (sb.length() > 0) {
			ret = sb.toString().substring(1);
		} else {
			ret = "";
		}
		return ret;
	}
	
	private String getShortFileNames(Collection<String> names) {
		StringBuilder sb = new StringBuilder();
		for(String name: names) {
			sb.append(", ");
			sb.append(getShortFilename(name));
		}
		final String ret;
		if (sb.length() > 0) {
			ret = sb.toString().substring(2);
		} else {
			ret = "";
		}
		return ret;
	}
	
	/**
	 * Gets the short version of a filename
	 * @param name The long filename
	 * @return The short filename
	 */
	private String getShortFilename(String name) {
		File f = new File(name);
		return f.getName();
	}
}
