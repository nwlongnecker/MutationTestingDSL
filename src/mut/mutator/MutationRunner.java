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

/**
 * The class in charge of doing the mutations. Extends thread so it can be started as a separate for performance reasons.
 * When running tests, first compiles and tests the original source code, then attempts to do all the mutations specified.
 * @author Nathan Longnecker
 */
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

	/**
	 * Readies a new mutation thread
	 * @param state The state of the mutator
	 * @param mutateFrom A list of symbols to mutate from
	 * @param mutateTo A list of symbols to mutate to
	 * @param fileManager The file manager to use when compiling
	 * @param statistics The statistics object to record results in
	 */
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
		PrintStream stdOut = System.out;
		try {
			// Hide error messages from running the tests
			// This is not optimal, if there is a problem while running the tests it causes problems
			System.setErr(new PrintStream(new ByteArrayOutputStream()));
			System.setOut(new PrintStream(new ByteArrayOutputStream()));
			originalResult = origTestRunner.runTests(testFiles);
		} catch (Exception e) {
			System.setOut(stdOut);
			print("Error running tests on unmutated code");
			e.printStackTrace(System.err);
			return;
		} finally {
			// Reset standard error and standard out
			System.setErr(stdErr);
			System.setOut(stdOut);
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
		// Code successfully compiled and the tests all passed, so it's safe to do mutations
		
		// Do the mutations
		for (String from : mutateFrom) {
			for (String to : mutateTo) {
				if (!from.equals(to)) {
					if(msg.verbosity >= Msg.NORMAL) {
						print("Mutating " + from + " to " + to);
					}
					for(String filename : sourceFiles) {
						// Search for the symbol we're mutating from in each of the source files
						String originalSourceContents = fileSystem.getOriginalSourceFile(filename);
						int currentReplacementIndex = originalSourceContents.indexOf(from);
						while (currentReplacementIndex > -1) {
							// Mutate the code
							String mutatedSourceContents = originalSourceContents.substring(0, currentReplacementIndex) +
									originalSourceContents.substring(currentReplacementIndex).replaceFirst(escapeSpecialChars(from), to);
							fileSystem.addFile(filename, mutatedSourceContents);
							String line = getLocInSourceFromIndex(currentReplacementIndex, originalSourceContents);
							
							if(compile(sourceFiles)) {
								if (msg.verbosity >= Msg.VERBOSE) {
									print(getShortFilename(filename) + ": Testing with " + getShortFileNames(testFiles));
								}
								MutatorJUnitRunner testRunner = new MutatorJUnitRunner(fileManager.getClassLoader(), fileSystem, msg);
								try {
									// Hide error messages from running the tests
									// This is not optimal, if there is a problem while running the tests it causes problems
									System.setErr(new PrintStream(new ByteArrayOutputStream()));
									System.setOut(new PrintStream(new ByteArrayOutputStream()));
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
									System.setOut(stdOut);
									print(getShortFilename(filename) + " " + line + ": Error running tests when mutating " + from + " to " + to);
									e.printStackTrace(stdErr);
								} finally {
									// Reset standard error and standard out
									System.setErr(stdErr);
									System.setOut(stdOut);
								}
							} else {
								statistics.logStillborn(filename, new Mutation(line, from, to));
								if (msg.verbosity >= Msg.NORMAL) {
									print(getShortFilename(filename) + " " + line + ": Stillborn mutant");
								}
							}
							
							currentReplacementIndex = originalSourceContents.indexOf(from, currentReplacementIndex + 1);
						}
						// Revert to the original source
						fileSystem.addFile(filename, originalSourceContents);
						compile(sourceFiles);
					}
				}
			}
		}
		// Finished!
		print("done");
	}
	
	/**
	 * Escape special characters in the symbol so the replacement works as expected
	 * @param in The symbol to escape
	 * @return The escaped symbol
	 */
	private String escapeSpecialChars(String in) {
		return in.replaceAll("(?=[]\\[+&|!(){}^\"~*?:\\\\-])", "\\\\");
	}
	
	/**
	 * Identifies the line number in the source file
	 * @param index The index to search for
	 * @param originalSourceContents The contents to search for the line number
	 * @return The line number in the source
	 */
	private String getLocInSourceFromIndex(int index, String originalSourceContents) {
        int numLines = originalSourceContents.substring(0, index).split("\r?\n").length;
		return "line " + numLines;
	}
	
	/**
	 * Compiles a list of filenames
	 * @param filenames The filenames to compile
	 * @return Whether the compiliation was successful
	 */
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
	
	/**
	 * Prints a message with the id number of this thread
	 * @param message The message to print
	 */
	private void print(String message) {
		msg.msgln(getId() + ": " + message);
	}
	
	/**
	 * Converts the from or the to symbols to a human readable list
	 * @param fromStrings Whether to print the from strings
	 * @return Return the human readable list
	 */
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
	
	/**
	 * Gets the short names of each file
	 * @param names The full filenames of each file
	 * @return A list of just the shortened filenames
	 */
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
