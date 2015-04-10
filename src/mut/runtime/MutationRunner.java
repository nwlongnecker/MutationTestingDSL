package mut.runtime;

import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.junit.runner.JUnitCore;

import mut.utility.MutCompiler;
import mut.utility.MutJUnitRunner;
import mut.utility.Utility;

public class MutationRunner extends Thread {

	private final File workingDir;
	private final MutCompiler compiler;
	
	private final Map<String, String> sourceFiles;
	private final Map<String, String> testFiles;
	private final Map<String, String> mutations;

	public MutationRunner(String workingDirname) {
		this.workingDir = new File(workingDirname + this.getId());
		this.workingDir.mkdir();
		
		compiler = new MutCompiler();
		compiler.setOutputDir(workingDir);
		compiler.addClassPathLocation(workingDir);
		try {
			compiler.addClassPathLocation(JUnitCore.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		
		sourceFiles = new HashMap<String, String>();
		testFiles = new HashMap<String, String>();
		mutations = new HashMap<String, String>();
	}
	
	/**
	 * @return the workingDir
	 */
	public File getWorkingDir() {
		return workingDir;
	}
	
	public void setSourceFiles(Collection<String> sourceCollection) {
		sourceFiles.clear();
		for(String source : sourceCollection) {
			sourceFiles.put(source, null);
		}
	}
	
	public void setTestFiles(Collection<String> testCollection) {
		testFiles.clear();
		for(String test : testCollection) {
			testFiles.put(test, null);
		}
	}
	
	public void setMutations(Map<String, String> mutations) {
		this.mutations.clear();
		this.mutations.putAll(mutations);
	}
	
	@Override
	public void run() {
		
		print("Mutating " + getMutationStrings() + " in files " + getShortFileNames(sourceFiles.keySet()) + " with tests " + getShortFileNames(testFiles.keySet()));
		MutJUnitRunner testRunner = new MutJUnitRunner(workingDir);
		
		// Ensure the tests pass normally
		compileSourcesInitial();
		compileTests();
		if (testRunner.runTests(testFiles.values()).wasSuccessful()) {
			print("Tests pass on unmutated code: check");
		} else {
			print("Tests do not pass on unmutated code");
			Utility.deleteDirectory(workingDir);
			return;
		}
		
		mutations.forEach((String key, String value) -> {
			print("Mutating " + key + " to " + value);
			for(String filename : sourceFiles.keySet()) {
				String originalSourceContents = Utility.readFile(filename);
				int currentReplacementIndex = originalSourceContents.indexOf(key);
				while (currentReplacementIndex > -1) {
					String mutatedSourceContents = originalSourceContents.substring(0, currentReplacementIndex) +
							originalSourceContents.substring(currentReplacementIndex).replaceFirst(key, value);
					writeAndCompile(mutatedSourceContents, sourceFiles.get(filename));
					
					print("Testing with " + getShortFileNames(testFiles.values()));
					if (testRunner.runTests(testFiles.values()).wasSuccessful()) {
						print("Tests passed on mutating " + key + " to " + value);
					} else {
						print("Tests failed, mutant killed");
					}
					
					currentReplacementIndex = originalSourceContents.indexOf(key, currentReplacementIndex + 1);
				}
				writeAndCompile(originalSourceContents, sourceFiles.get(filename));
			}
		});
		
		Utility.deleteDirectory(workingDir);
	}
	
	private void writeAndCompile(String sourceContents, String filename) {
		print("Writing and compiling " + filename);
		File sourceFile = new File(filename);
		Utility.writeFile(sourceFile, sourceContents);
		ArrayList<String> files = new ArrayList<String>();
		files.add(filename);
		compiler.compile(files);
	}
	
	private void compileSourcesInitial() {
		for(String fileName : sourceFiles.keySet()) {
			String sourceContents = Utility.readFile(fileName);
			File sourceFile = new File(workingDir, Utility.getFilename(fileName));
			sourceFile.getParentFile().mkdirs();
			Utility.writeFile(sourceFile, sourceContents);
			sourceFiles.put(fileName, sourceFile.getPath());
		}
		compiler.compile(sourceFiles.values());
	}
	
	private void compileTests() {

		for(String fileName : testFiles.keySet()) {
			String testContents = Utility.readFile(fileName);
			File testFile = new File(workingDir, Utility.getFilename(fileName));
			testFile.getParentFile().mkdirs();
			Utility.writeFile(testFile, testContents);
			testFiles.put(fileName, testFile.getPath());
		}
		
		compiler.compile(testFiles.values());
	}
	
	private void print(String message) {
		MutatorRuntime.printMessage(getId() + ": " + message);
	}
	
	private String getMutationStrings() {
		StringBuilder sb = new StringBuilder();
		for(String mutation: mutations.keySet()) {
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
