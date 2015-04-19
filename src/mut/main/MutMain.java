package mut.main;

import java.util.Collection;
import java.util.HashSet;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

import mut.files.InMemoryFileManager;
import mut.interpreter.InterpreterState;
import mut.mutator.MutationRunner;

public class MutMain {

	public static void main(String[] args) {
		InterpreterState state = new InterpreterState();
		
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
		
		state.setSourceFiles(sourceFilePaths);
		state.setTestFiles(testFilePaths);
		
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		if (compiler == null) {
			throw new RuntimeException("No system compiler provided, try running with jdk instead of jre");
		}
		InMemoryFileManager fileManager = new InMemoryFileManager(compiler.getStandardFileManager(null, null, null));
		
		MutationRunner runner = new MutationRunner(state, mutateFrom, mutateTo, fileManager);
		runner.start();
	}
}
