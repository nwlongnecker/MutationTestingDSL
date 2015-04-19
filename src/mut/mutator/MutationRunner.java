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
	private final boolean verbose;
	private final InMemoryFileManager fileManager;

	public MutationRunner(InterpreterState state, Collection<String> mutateFrom, Collection<String> mutateTo, InMemoryFileManager fileManager) {
		sourceFiles = state.getSourceFiles();
		testFiles = state.getTestFiles();
		this.mutateFrom = mutateFrom;
		this.mutateTo = mutateTo;
		this.compiler = ToolProvider.getSystemJavaCompiler();
		verbose = false;
		this.fileManager = fileManager;
	}
	
	@Override
	public void run() {
		print("Mutating " + getMutationStrings() + " in files " + getShortFileNames(sourceFiles) + " with tests " + getShortFileNames(testFiles));
		
		// Ensure the tests pass normally
		Collection<String> compileFiles = new HashSet<String>();
		compileFiles.addAll(sourceFiles);
		compileFiles.addAll(testFiles);
		compile(compileFiles);
		MutatorJUnitRunner origTestRunner = new MutatorJUnitRunner(fileManager.getClassLoader());
		if (origTestRunner.runTests(testFiles).wasSuccessful()) {
			print("Tests pass on unmutated code: check");
		} else {
			print("Tests do not pass on unmutated code");
			return;
		}
		
		for (String from : mutateFrom) {
			for (String to : mutateTo) {
				if (!from.equals(to)) {
					print("Mutating " + from + " to " + to);
					for(String filename : sourceFiles) {
						String originalSourceContents = InMemoryFileSystem.getOriginalSourceFile(filename);
						int currentReplacementIndex = originalSourceContents.indexOf(from);
						while (currentReplacementIndex > -1) {
							String mutatedSourceContents = originalSourceContents.substring(0, currentReplacementIndex) +
									originalSourceContents.substring(currentReplacementIndex).replaceFirst(escapeSpecialChars(from), to);
							InMemoryFileSystem.addFile(filename, mutatedSourceContents);
							compile(filename);
							
//							print("Testing with " + getShortFileNames(testFiles));
							MutatorJUnitRunner testRunner = new MutatorJUnitRunner(fileManager.getClassLoader());
							if (testRunner.runTests(testFiles).wasSuccessful()) {
								print(getLocInSourceFromIndex(currentReplacementIndex, originalSourceContents) + ": Tests passed on mutating " + from + " to " + to);
							} else {
								print(getLocInSourceFromIndex(currentReplacementIndex, originalSourceContents) + ": Tests failed, mutant killed");
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
	
	private void compile(String filename) {
		Collection<String> filenames = new HashSet<String>();
		filenames.add(filename);
		compile(filenames);
	}
	
	private void compile(Collection<String> filenames) {
		final DiagnosticCollector<JavaFileObject> diagnostics = new  DiagnosticCollector<JavaFileObject>();
		Collection<JavaFileObject> files = null;
		try {
			files = fileManager.getJavaFileObjectsForInputFromStrings(filenames, JavaFileObject.Kind.SOURCE);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (verbose) {
			for (String filename : filenames) {
				System.out.println("Compiling " + filename);
			}
		}
		boolean success = compiler.getTask(null, fileManager, diagnostics, null, null, files).call();
		if (verbose) {
			System.out.println("Compiled Successfully: " + success);
		}
		for(Diagnostic<? extends JavaFileObject> d: diagnostics.getDiagnostics()) {
			System.out.println(d.getMessage(null));
		}
	}
	
	private void print(String message) {
		Msg.msgln(getId() + ": " + message);
	}
	
	private String getMutationStrings() {
		StringBuilder sb = new StringBuilder();
		for(String mutation: mutateFrom) {
			sb.append(',');
			sb.append(mutation);
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
			File f = new File(name);
			sb.append(',');
			sb.append(f.getName());
		}
		final String ret;
		if (sb.length() > 0) {
			ret = sb.toString().substring(1);
		} else {
			ret = "";
		}
		return ret;
	}
}
