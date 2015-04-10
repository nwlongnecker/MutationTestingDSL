/**
 * 
 */
package mut;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.StandardLocation;
import javax.tools.ToolProvider;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

import mut.runtime.MutationRunner;
import mut.runtime.MutatorRuntime;
import mut.utility.MutCompiler;
import mut.utility.MutJUnitRunner;
import mut.utility.MutClassLoader;
import mut.utility.Utility;

/**
 * Runs the mutator DSL
 * @author Nathan
 */
public class CompilerMain {

	/**
	 * @param args Arguments
	 * @throws IOException
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws Exception {
		// Save source in .java file.
//		File root = new File("/nwlongneckerMutator"); // On Windows running on C:\, this is C:\nwlongneckerMutator.
//		root.mkdir();
		
		MutationRunner runner = new MutationRunner("/nwlongneckerMutator");
//		MutCompiler compiler = new MutCompiler();
//		compiler.setOutputDir(runner.getWorkingDir());

		List<String> sourceFileNames = new ArrayList<String>();
//		sourceFileNames.add("C:\\Users\\Nathan\\Documents\\CS 544\\CompilerWorkspace\\MutationTestingDSL\\sampleMutationFiles\\src\\LogicalFunctions.java");
		sourceFileNames.add("C:\\Users\\Nathan\\Documents\\CS 544\\CompilerWorkspace\\MutationTestingDSL\\sampleMutationFiles\\src\\MathFunctions.java");
		List<String> testFileNames = new ArrayList<String>();
//		testFileNames.add("C:\\Users\\Nathan\\Documents\\CS 544\\CompilerWorkspace\\MutationTestingDSL\\sampleMutationFiles\\test\\LogicalFunctionsTest.java");
		testFileNames.add("C:\\Users\\Nathan\\Documents\\CS 544\\CompilerWorkspace\\MutationTestingDSL\\sampleMutationFiles\\test\\MathFunctionsTest.java");
		
		Map<String,String> mutations = new HashMap<String,String>();
		mutations.put("-", "+");

		runner.setSourceFiles(sourceFileNames);
		runner.setTestFiles(testFileNames);
		runner.setMutations(mutations);
		runner.start();
//		compiler.addClassPathLocation(root);
//		compiler.addClassPathLocation(JUnitCore.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());

//		for(String fileName :sourceFileNames) {
//			String sourceContents = Utility.readFile(fileName);
//			sourceContents = sourceContents.replace('-', '+');
//			File sourceFile = new File(root, Utility.getFilename(fileName));
//			sourceFile.getParentFile().mkdirs();
//			Utility.writeFile(sourceFile, sourceContents);
//		}
//		
//		compiler.compile(sourceFileNames);
//		for(String fileName :testFileNames) {
//			String testContents = Utility.readFile(fileName);
//			File testFile = new File(root, Utility.getFilename(fileName));
//			testFile.getParentFile().mkdirs();
//			Utility.writeFile(testFile, testContents);
//			
//		}
//		
//		compiler.compile(testFileNames);
		
//		MutJUnitRunner testRunner = new MutJUnitRunner(root);
//		testRunner.runAndReport(testFileNames);
		
//		Utility.deleteDirectory(root);
	}
}
