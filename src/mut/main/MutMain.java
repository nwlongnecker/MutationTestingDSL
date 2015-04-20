package mut.main;

import java.util.Collection;
import java.util.HashSet;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

import mut.files.InMemoryFileManager;
import mut.interpreter.InterpreterState;
import mut.mutator.MutationRunner;
import mut.statistics.StatisticsCollector;
import mut.util.Msg;

public class MutMain {

	public static void main(String[] args) {
		Msg msg = new Msg();
		InterpreterState state = new InterpreterState(msg);
		
		// Start with file names and mutations
		Collection<String> sourceFilePaths = new HashSet<String>();
		sourceFilePaths.add("C:\\Users\\Nathan\\Documents\\CS 544\\CompilerWorkspace\\MutationTestingDSL\\sampleMutationFiles\\src\\LogicalFunctions.java");
		sourceFilePaths.add("C:\\Users\\Nathan\\Documents\\CS 544\\CompilerWorkspace\\MutationTestingDSL\\sampleMutationFiles\\src\\MathFunctions.java");
		
		Collection<String> testFilePaths = new HashSet<String>();
		testFilePaths.add("C:\\Users\\Nathan\\Documents\\CS 544\\CompilerWorkspace\\MutationTestingDSL\\sampleMutationFiles\\test\\LogicalFunctionsTest.java");
		testFilePaths.add("C:\\Users\\Nathan\\Documents\\CS 544\\CompilerWorkspace\\MutationTestingDSL\\sampleMutationFiles\\test\\MathFunctionsTest.java");
		
		Collection<String> mutateFrom = new HashSet<String>();
		Collection<String> mutateTo = new HashSet<String>();
		mutateFrom.add("-");
		mutateFrom.add("/");
		mutateFrom.add("+");
		mutateFrom.add("*");
		mutateTo.add("+");
		mutateTo.add("-");
		mutateTo.add("*");
		mutateTo.add("/");
		
		Collection<String> mutateFrom2 = new HashSet<String>();
		Collection<String> mutateTo2 = new HashSet<String>();
		mutateFrom2.add("&&");
		mutateFrom2.add("||");
		mutateTo2.add("&&");
		mutateTo2.add("||");
		
		state.setSourceFiles(sourceFilePaths);
		state.setTestFiles(testFilePaths);
		
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		if (compiler == null) {
			throw new RuntimeException("No system compiler provided, try running with jdk instead of jre");
		}
		InMemoryFileManager fileManager = new InMemoryFileManager(compiler.getStandardFileManager(null, null, null), state.getFileSystem(), msg);

		StatisticsCollector statistics = new StatisticsCollector();
		MutationRunner runner = new MutationRunner(state, mutateFrom, mutateTo, fileManager, statistics);
		runner.start();
		MutationRunner runner2 = new MutationRunner(state, mutateFrom2, mutateTo2, fileManager, statistics);
		runner2.start();
	}
}
