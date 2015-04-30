package mut.interpreter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import mut.files.FileReader;
import mut.files.InMemoryFileSystem;
import mut.statistics.StatisticsCollector;
import mut.symbol.SymbolTable;
import mut.util.Msg;

/**
 * Class for holding data to maintain state between calls to the interpreter
 * @author Nathan
 */
public class InterpreterState {
	
	/**
	 * Keeps track of whether we are currently testing or not
	 * If we are testing we won't multithread, otherwise we will
	 */
	public static boolean TESTING = false;
	private final Collection<String> sourceFiles;
	private final Collection<String> testFiles;
	private final Collection<String> useFiles;
	private final SymbolTable symbolTable;
	private final InMemoryFileSystem fileSystem;
	private final Msg msg;
	private final List<StatisticsCollector> statistics;
	private final List<Thread> runningThreads;
	
	/**
	 * Constructs a new interpreter state
	 */
	public InterpreterState(Msg msg) {
		sourceFiles = new HashSet<String>();
		testFiles = new HashSet<String>();
		useFiles = new HashSet<String>();
		symbolTable = new SymbolTable(msg);
		fileSystem = new InMemoryFileSystem();
		this.msg = msg;
		statistics = new ArrayList<StatisticsCollector>();
		runningThreads = new ArrayList<Thread>();
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
		for(String filepath : this.sourceFiles) {
			fileSystem.removeFile(filepath);
			fileSystem.removeOriginalSourceFile(filepath);
		}
		this.sourceFiles.clear();
		for(String filepath : sourceFiles) {
			String fileContents = FileReader.readFile(filepath, msg);
			fileSystem.addOriginalSourceFile(filepath, fileContents);
			fileSystem.addFile(filepath, fileContents);
		}
		this.sourceFiles.addAll(sourceFiles);
	}

	/**
	 * @param sourceFiles the sourceFiles to add
	 */
	public void addSourceFiles(Collection<String> sourceFiles) {
		for(String filepath : sourceFiles) {
			String fileContents = FileReader.readFile(filepath, msg);
			fileSystem.addOriginalSourceFile(filepath, fileContents);
			fileSystem.addFile(filepath, fileContents);
		}
		this.sourceFiles.addAll(sourceFiles);
	}

	/**
	 * @param sourceFiles the sourceFiles to remove
	 */
	public void removeSourceFiles(Collection<String> sourceFiles) {
		for (String file : sourceFiles) {
			fileSystem.removeFile(file);
			fileSystem.removeOriginalSourceFile(file);
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
		for(String filepath : this.testFiles) {
			fileSystem.removeFile(filepath);
		}
		this.testFiles.clear();
		for (String filepath : testFiles) {
			String fileContents = FileReader.readFile(filepath, msg);
			fileSystem.addFile(filepath, fileContents);
		}
		this.testFiles.addAll(testFiles);
	}

	/**
	 * @param testFiles the testFiles
	 */
	public void addTestFiles(Collection<String> testFiles) {
		for (String filepath : testFiles) {
			String fileContents = FileReader.readFile(filepath, msg);
			fileSystem.addFile(filepath, fileContents);
		}
		this.testFiles.addAll(testFiles);
	}

	/**
	 * @param testFiles the testFiles to remove
	 */
	public void removeTestFiles(Collection<String> testFiles) {
		for (String file : testFiles) {
			fileSystem.removeFile(file);
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

	/**
	 * @return the symbolTable
	 */
	public SymbolTable getSymbolTable() {
		return symbolTable;
	}
	
	/**
	 * @return the fileSystem
	 */
	public InMemoryFileSystem getFileSystem() {
		return fileSystem;
	}

	/**
	 * @return the msg
	 */
	public Msg getMsg() {
		return msg;
	}

	/**
	 * @return the statistics
	 */
	public List<StatisticsCollector> getStatistics() {
		return statistics;
	}
	
	/**
	 * @return the running threads
	 */
	public List<Thread> getRunningThreads() {
		return runningThreads;
	}
	
	/**
	 * @param t The thread to add
	 */
	public void addThread(Thread t) {
		runningThreads.add(t);
	}

}
