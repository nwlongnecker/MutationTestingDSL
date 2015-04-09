package mut.interpreter;

import java.util.Collection;
import java.util.HashSet;

/**
 * Class for holding data to maintain state between calls to the interpreter
 * @author Nathan
 */
public class InterpreterState {
	
	private final Collection<String> sourceFiles;
	private final Collection<String> testFiles;
	private final Collection<String> useFiles;

	/**
	 * Constructs a new interpreter state
	 */
	public InterpreterState() {
		sourceFiles = new HashSet<String>();
		testFiles = new HashSet<String>();
		useFiles = new HashSet<String>();
	}

	/**
	 * @return the sourceFiles
	 */
	public Collection<String> getSourceFiles() {
		return sourceFiles;
	}

	/**
	 * @param sourceFiles the sourceFiles
	 */
	public void setSourceFiles(Collection<String> sourceFiles) {
		this.sourceFiles.clear();
		this.sourceFiles.addAll(sourceFiles);
	}

	/**
	 * @param sourceFiles the sourceFiles to add
	 */
	public void addSourceFiles(Collection<String> sourceFiles) {
		this.sourceFiles.addAll(sourceFiles);
	}

	/**
	 * @param sourceFiles the sourceFiles to remove
	 */
	public void removeSourceFiles(Collection<String> sourceFiles) {
		for (String file : sourceFiles) {
			this.sourceFiles.remove(file);
		}
	}

	/**
	 * @return the testFiles
	 */
	public Collection<String> getTestFiles() {
		return testFiles;
	}

	/**
	 * @param testFiles the testFiles
	 */
	public void setTestFiles(Collection<String> testFiles) {
		this.testFiles.clear();
		this.testFiles.addAll(testFiles);
	}

	/**
	 * @param testFiles the testFiles
	 */
	public void addTestFiles(Collection<String> testFiles) {
		this.testFiles.addAll(testFiles);
	}

	/**
	 * @param testFiles the testFiles to remove
	 */
	public void removeTestFiles(Collection<String> testFiles) {
		for (String file : testFiles) {
			this.testFiles.remove(file);
		}
	}

	/**
	 * @return the useFiles
	 */
	public Collection<String> getUseFiles() {
		return useFiles;
	}

	/**
	 * @param files the files
	 */
	public void addUseFiles(Collection<String> useFiles) {
		this.useFiles.addAll(useFiles);
	}

}
