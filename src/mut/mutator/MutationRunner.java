package mut.mutator;

import java.io.File;
import java.io.IOException;
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
import mut.util.Msg;

public class MutationRunner extends Thread {

	private final Collection<String> sourceFiles;
	private final Collection<String> testFiles;
	private final Collection<String> mutateFrom;
	private final Collection<String> mutateTo;
	private final JavaCompiler compiler;
	private final InMemoryFileManager fileManager;

	public MutationRunner(InterpreterState state, Collection<String> mutateFrom, Collection<String> mutateTo, InMemoryFileManager fileManager) {
		sourceFiles = state.getSourceFiles();
		testFiles = state.getTestFiles();
		this.mutateFrom = mutateFrom;
		this.mutateTo = mutateTo;
		this.compiler = ToolProvider.getSystemJavaCompiler();
		this.fileManager = fileManager;
	}
	
	@Override
	public void run() {
		print("Mutating " + getMutationStrings(true) + " to " + getMutationStrings(false) + " in files " + getShortFileNames(sourceFiles) + " with tests " + getShortFileNames(testFiles));
		
		// Ensure the tests pass normally
		Collection<String> compileFiles = new HashSet<String>();
		compileFiles.addAll(sourceFiles);
		compileFiles.addAll(testFiles);
		if(!compile(compileFiles)) {
			Msg.err(getId() + ": Supplied code does not compile! Did you include every file in the classpath?");
			return;
		}
		MutatorJUnitRunner origTestRunner = new MutatorJUnitRunner(fileManager.getClassLoader());
		if (origTestRunner.runTests(testFiles).wasSuccessful()) {
			if(Msg.verbose) {
				print("Tests pass on unmutated code: check");
			}
		} else {
			Msg.err(getId() + ": Tests do not pass on unmutated code");
			return;
		}
		
		// Do the mutations
		for (String from : mutateFrom) {
			for (String to : mutateTo) {
				if (!from.equals(to)) {
					if(Msg.verbose) {
						print("Mutating " + from + " to " + to);
					}
					for(String filename : sourceFiles) {
						String originalSourceContents = InMemoryFileSystem.getOriginalSourceFile(filename);
						int currentReplacementIndex = originalSourceContents.indexOf(from);
						while (currentReplacementIndex > -1) {
							String mutatedSourceContents = originalSourceContents.substring(0, currentReplacementIndex) +
									originalSourceContents.substring(currentReplacementIndex).replaceFirst(escapeSpecialChars(from), to);
							InMemoryFileSystem.addFile(filename, mutatedSourceContents);
							
							if(compile(filename)) {
								if (Msg.verbose) {
									print(getShortFilename(filename) + ": Testing with " + getShortFileNames(testFiles));
								}
								MutatorJUnitRunner testRunner = new MutatorJUnitRunner(fileManager.getClassLoader());
								if (testRunner.runTests(testFiles).wasSuccessful()) {
									print(getShortFilename(filename) + " " + getLocInSourceFromIndex(currentReplacementIndex, originalSourceContents) + ": Mutant survived when mutating " + from + " to " + to);
								} else {
									if(Msg.verbose) {
										print(getShortFilename(filename) + " " + getLocInSourceFromIndex(currentReplacementIndex, originalSourceContents) + ": Tests failed, mutant killed");
									}
								}
							} else {
								if (Msg.verbose) {
									print(getShortFilename(filename) + " " + getLocInSourceFromIndex(currentReplacementIndex, originalSourceContents) + ": Stillborn mutant");
								}
							}
							
							currentReplacementIndex = originalSourceContents.indexOf(from, currentReplacementIndex + 1);
						}
						InMemoryFileSystem.addFile(filename, originalSourceContents);
						compile(filename);
					}
				}
			}
		}
	}
	
	private String escapeSpecialChars(String in) {
		return "\\" + in;
	}
	
	private String getLocInSourceFromIndex(int index, String originalSourceContents) {
        int numLines = originalSourceContents.substring(0, index).split("\r?\n").length;
		return "line " + numLines;
	}
	
	private boolean compile(String filename) {
		Collection<String> filenames = new HashSet<String>();
		filenames.add(filename);
		return compile(filenames);
	}
	
	private boolean compile(Collection<String> filenames) {
		final DiagnosticCollector<JavaFileObject> diagnostics = new  DiagnosticCollector<JavaFileObject>();
		Collection<JavaFileObject> files = null;
		try {
			files = fileManager.getJavaFileObjectsForInputFromStrings(filenames, JavaFileObject.Kind.SOURCE);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (Msg.verbose) {
			for (String filename : filenames) {
				print("Compiling " + filename);
			}
		}
		boolean success = compiler.getTask(null, fileManager, diagnostics, null, null, files).call();
		if (Msg.verbose) {
			print("Compiled Successfully: " + success);
		}
		for(Diagnostic<? extends JavaFileObject> d: diagnostics.getDiagnostics()) {
			if (Msg.verbose || d.getCode().equals("compiler.err.cant.resolve.location")) {
				Msg.err(getId() + ": " + d.getMessage(null));
			}
		}
		return success;
	}
	
	private void print(String message) {
		Msg.msgln(getId() + ": " + message);
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
			sb.append(',');
			sb.append(getShortFilename(name));
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
	 * Gets the short version of a filename
	 * @param name The long filename
	 * @return The short filename
	 */
	private String getShortFilename(String name) {
		File f = new File(name);
		return f.getName();
	}
}
