package mut.junit;

import java.util.Collection;
import java.util.HashSet;

import mut.files.InMemoryFileSystem;
import mut.util.Msg;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

/**
 * Class for handling running JUnit tests
 * @author Nathan Longnecker
 */
public class MutatorJUnitRunner {
	private ClassLoader classLoader;
	private JUnitCore junit;
	private InMemoryFileSystem fileSystem;
	private Msg msg;

	/**
	 * Constructs a new JUnit test runner
	 * @param classLoader The ClassLoader to load classes from
	 * @param fileSystem The file system to retrieve files from
	 * @param msg The messager to print to
	 */
	public MutatorJUnitRunner(ClassLoader classLoader, InMemoryFileSystem fileSystem, Msg msg) {
		this.classLoader = classLoader;
		junit = new JUnitCore();
		this.fileSystem = fileSystem;
		this.msg = msg;
	}
	
	/**
	 * Loads a list of classes
	 * @param fileNames The filenames corresponding to the classes to load
	 * @return Returns a list of class objects
	 * @throws ClassNotFoundException If the class cannot be found
	 */
	private Collection<Class<?>> loadClasses(Collection<String> fileNames) throws ClassNotFoundException {
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

	/**
	 * Runs a collection of JUnit tests
	 * @param filenames A collection of tests to run
	 * @return The result of the tests
	 */
	public Result runTests(Collection<String> filenames) {
		Collection<Class<?>> classes = null;
		try {
			classes = loadClasses(filenames);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		if (classes != null && !classes.isEmpty()) {
			return junit.run(classes.toArray(new Class<?>[0]));
		} else {
			return null;
		}
	}
}
