package mut.utility;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;

import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.StandardLocation;
import javax.tools.ToolProvider;

import mut.runtime.MutatorRuntime;

public class MutCompiler {

	private final JavaCompiler compiler;
	private final DiagnosticCollector<JavaFileObject> diagnostics;
	private final StandardJavaFileManager fileManager;
	
	private final Collection<File> classPath;
	private final Collection<File> classOutput;
	
	public MutCompiler() {
		compiler = ToolProvider.getSystemJavaCompiler();
		if (compiler == null) {
			throw new RuntimeException("No system compiler provided, try running with jdk instead of jre");
		}
		diagnostics = new  DiagnosticCollector<JavaFileObject>();
		fileManager = compiler.getStandardFileManager(diagnostics, null, null);
		classPath = new HashSet<File>();
		
		classOutput = new HashSet<File>();
	}
	
	public void addClassPathLocation(String location) {
		addClassPathLocation(new File(location));
	}
	
	public void addClassPathLocation(File directory) {
		classPath.add(directory);
		try {
			fileManager.setLocation(StandardLocation.CLASS_PATH, classPath);
		} catch (IOException e) {
			MutatorRuntime.printError(e.getMessage());
		}
	}
	
	public void setOutputDir(File directory) {
		classOutput.clear();
		classOutput.add(directory);
		try {
			fileManager.setLocation(StandardLocation.CLASS_OUTPUT, classOutput);
		} catch (IOException e) {
			MutatorRuntime.printError(e.getMessage());
		}
	}

	public void compile(Collection<String> fileNames) {
		final Iterable<? extends JavaFileObject> files = fileManager.getJavaFileObjectsFromStrings(fileNames);
		if(compiler.getTask(null, fileManager, diagnostics, null, null, files).call()) {
//			MutatorRuntime.printMessage("Compilation Successful");
		} else {
			MutatorRuntime.printError("Error compiling:");
			for(Diagnostic<? extends JavaFileObject> d: diagnostics.getDiagnostics()) {
				MutatorRuntime.printError(d.getMessage(null));
			}
		}
	}
}
