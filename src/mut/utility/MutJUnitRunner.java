package mut.utility;

import java.io.File;
import java.util.Collection;
import java.util.HashSet;

import mut.runtime.MutatorRuntime;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

public class MutJUnitRunner {
	private MutClassLoader classLoader;
	private JUnitCore junit;
	
	private boolean verbose;

	public MutJUnitRunner(File workingDir) {
		classLoader = new MutClassLoader(workingDir);
		junit = new JUnitCore();
		verbose = true;
	}
	
	public Collection<Class<?>> loadClasses(Collection<String> fileNames) {
		Collection<Class<?>> classes = new HashSet<Class<?>>();
		for(String fileName : fileNames) {
			Class<?> c = classLoader.loadClass(fileName);
			if (c != null) {
				classes.add(c);
				if (verbose) {
					MutatorRuntime.printMessage("Loaded test " + c.getName() + " from file " + fileName);
				}
			}
		}
		return classes;
	}
	
	public Result runTests(Collection<String> fileNames) {
		Collection<Class<?>> classes = loadClasses(fileNames);
		if (!classes.isEmpty()) {
			return junit.run(classes.toArray(new Class<?>[0]));
		} else {
			return null;
		}
	}
	
	public void runAndReport(Collection<String> fileNames) {
		if (!fileNames.isEmpty()) {
			Result result = runTests(fileNames);
			MutatorRuntime.reportResults(result);
		}
	}
}
