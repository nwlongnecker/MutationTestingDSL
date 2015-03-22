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

}
