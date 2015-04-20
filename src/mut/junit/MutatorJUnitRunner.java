package mut.junit;

import java.util.Collection;
import java.util.HashSet;

import mut.files.InMemoryFileSystem;
import mut.util.Msg;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

public class MutatorJUnitRunner {
	private ClassLoader classLoader;
	private JUnitCore junit;
	private InMemoryFileSystem fileSystem;
	private Msg msg;

	public MutatorJUnitRunner(ClassLoader classLoader, InMemoryFileSystem fileSystem, Msg msg) {
		this.classLoader = classLoader;
		junit = new JUnitCore();
		this.fileSystem = fileSystem;
		this.msg = msg;
	}
	
	public Collection<Class<?>> loadClasses(Collection<String> fileNames) throws ClassNotFoundException {
		Collection<Class<?>> classes = new HashSet<Class<?>>();
		for(String fileName : fileNames) {
			String className = fileSystem.getClassname(fileName);
			Class<?> c = classLoader.loadClass(className);
			if (c != null) {
				classes.add(c);
				if (msg.verbosity >= Msg.VERY_VERBOSE) {
					msg.msgln("Loaded test " + c.getName() + " from file " + fileName);
				}
			}
		}
		return classes;
	}

	public Result runTests(Collection<String> fileNames) {
		Collection<Class<?>> classes = null;
		try {
			classes = loadClasses(fileNames);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		if (classes != null && !classes.isEmpty()) {
			return junit.run(classes.toArray(new Class<?>[0]));
		} else {
			return null;
		}
	}
	
	public void runAndReport(Collection<String> fileNames) {
		if (!fileNames.isEmpty()) {
			Result result = runTests(fileNames);
			msg.reportResults(result);
		}
	}
}
