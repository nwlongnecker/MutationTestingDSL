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
import java.util.HashSet;
import java.util.List;

import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.StandardLocation;
import javax.tools.ToolProvider;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

import mut.runtime.MutatorRuntime;
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
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		if (compiler == null) {
			throw new RuntimeException("No system compiler provided, try running with jdk instead of jre");
		}
		DiagnosticCollector<JavaFileObject> diagnostics = new  DiagnosticCollector<JavaFileObject>();
		StandardJavaFileManager stdFileManager = compiler.getStandardFileManager(diagnostics, null, null);
//		
//		ArrayList<File> sources = new ArrayList<File>();
//		sources.add(new File("C:\\Users\\Nathan\\Documents\\CS 544\\CompilerWorkspace\\MutationTestingDSL\\sampleMutationFiles\\src\\LogicalFunctions.java"));
//		Iterable<? extends JavaFileObject> src = stdFileManager.getJavaFileObjectsFromFiles(sources);
//
//		ArrayList<File> outputLocation = new ArrayList<File>();
//		outputLocation.add(new File("C:\\Users\\Nathan\\Documents\\CS 544\\CompilerWorkspace\\MutationTestingDSL\\sampleMutationFiles\\bin"));
//		stdFileManager.setLocation(StandardLocation.CLASS_OUTPUT, outputLocation);
//		ArrayList<File> sourceLocation = new ArrayList<File>();
//		sourceLocation.add(new File("C:\\Users\\Nathan\\Documents\\CS 544\\CompilerWorkspace\\MutationTestingDSL\\sampleMutationFiles\\src"));
//		stdFileManager.setLocation(StandardLocation.SOURCE_PATH, outputLocation);
//		
//		Iterable<? extends File> files = stdFileManager.getLocation(StandardLocation.CLASS_OUTPUT);
//		
//		for(File file:files) {
//			System.out.println(file.getAbsolutePath());
//		}
//		
//		System.out.println(compiler.getTask(null, stdFileManager, diagnostics, null, null, src).call());

		List<String> sourceFileNames = new ArrayList<String>();
//		sourceFileNames.add("C:\\Users\\Nathan\\Documents\\CS 544\\CompilerWorkspace\\MutationTestingDSL\\sampleMutationFiles\\src\\LogicalFunctions.java");
		sourceFileNames.add("C:\\Users\\Nathan\\Documents\\CS 544\\CompilerWorkspace\\MutationTestingDSL\\sampleMutationFiles\\src\\MathFunctions.java");
		List<String> testFileNames = new ArrayList<String>();
//		testFileNames.add("C:\\Users\\Nathan\\Documents\\CS 544\\CompilerWorkspace\\MutationTestingDSL\\sampleMutationFiles\\test\\LogicalFunctionsTest.java");
		testFileNames.add("C:\\Users\\Nathan\\Documents\\CS 544\\CompilerWorkspace\\MutationTestingDSL\\sampleMutationFiles\\test\\MathFunctionsTest.java");

		// Save source in .java file.
		File root = new File("/nwlongneckerMutator"); // On Windows running on C:\, this is C:\nwlongneckerMutator.
		ArrayList<File> classPath = new ArrayList<File>();
		classPath.add(root);
		classPath.add(new File(JUnitCore.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath()));
		stdFileManager.setLocation(StandardLocation.CLASS_PATH, classPath);

		for(String fileName :sourceFileNames) {
			String sourceContents = Utility.readFile(fileName);
//			System.out.println(sourceContents);
			sourceContents = sourceContents.replace('-', '+');
			File sourceFile = new File(root, "src/MathFunctions.java");
			sourceFile.getParentFile().mkdirs();
			new FileWriter(sourceFile).append(sourceContents).close();
			
//			compiler.run(null, null, null, sourceFile.getPath());
		}
		Iterable<? extends JavaFileObject> src = stdFileManager.getJavaFileObjectsFromStrings(sourceFileNames);
		System.out.println(compiler.getTask(null, stdFileManager, diagnostics, null, null, src).call());
		
		Collection<Class<?>> classes = new HashSet<Class<?>>();
		for(String fileName :testFileNames) {
			String testContents = Utility.readFile(fileName);
			File testFile = new File(root, "test/MathFunctionsTest.java");
			testFile.getParentFile().mkdirs();
			new FileWriter(testFile).append(testContents).close();
			
		}
		
		Iterable<? extends JavaFileObject> test = stdFileManager.getJavaFileObjectsFromStrings(testFileNames);
		System.out.println(compiler.getTask(null, stdFileManager, diagnostics, null, null, test).call());
		for(Diagnostic d: diagnostics.getDiagnostics()) {
			System.out.println(d.getMessage(null));
		}
		
		URL dirUrl = new URL("file:/nwlongneckerMutator/");
		URLClassLoader cl = new URLClassLoader(new URL[] {dirUrl}, CompilerMain.class.getClassLoader());
		Class c = cl.loadClass(Utility.getClassname(testFileNames.get(0)));

		if (c != null) {
			classes.add(c);
			MutatorRuntime.printMessage("Running " + testFileNames.get(0) + " on mutated code");
		}
		// Find the test classes
		JUnitCore junit = new JUnitCore();
		//Run JUnit tests
		if (!classes.isEmpty()) {
			Result result = junit.run(classes.toArray(new Class<?>[0]));
			MutatorRuntime.reportResults(result);
		}
		
		// Prepare source somehow.
//		String source = "package test; public class Test { static { System.out.println(\"hello\"); } public Test() { System.out.println(\"world\"); } }";
//
//		File sourceFile = new File(root, "test/Test.java");
//		sourceFile.getParentFile().mkdirs();
//		new FileWriter(sourceFile).append(source).close();

		// Compile source file.
//		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
//		compiler.run(null, null, null, sourceFile.getPath());

		// Load and instantiate compiled class.
//		URLClassLoader classLoader = URLClassLoader.newInstance(new URL[] { root.toURI().toURL() });
//		Class<?> cls = Class.forName("test.Test", true, classLoader); // Should print "hello".
//		Object instance = cls.newInstance(); // Should print "world".
//		System.out.println(instance); // Should print "test.Test@hashcode".
//		deleteDirectory(root);
		
//		stdFileManager.close();
	}
	
	private static boolean deleteDirectory(File directory) {
	    if(directory.exists()){
	        File[] files = directory.listFiles();
	        if(null!=files){
	            for(int i=0; i<files.length; i++) {
	                if(files[i].isDirectory()) {
	                    deleteDirectory(files[i]);
	                } else {
	                    files[i].delete();
	                }
	            }
	        }
	    }
	    return directory.delete();
	}
}
