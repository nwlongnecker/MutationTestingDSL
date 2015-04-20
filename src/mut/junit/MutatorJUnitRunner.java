package mut.junit;

import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import mut.files.InMemoryFileSystem;
import mut.util.Msg;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

public class MutatorJUnitRunner {
	private ClassLoader classLoader;
	private JUnitCore junit;

	public MutatorJUnitRunner(ClassLoader classLoader) {
		this.classLoader = classLoader;
		junit = new JUnitCore();
	}
	
	public Collection<Class<?>> loadClasses(Collection<String> fileNames) throws ClassNotFoundException {
		Collection<Class<?>> classes = new HashSet<Class<?>>();
		for(String fileName : fileNames) {
			String className = getClassname(fileName);
			Class<?> c = classLoader.loadClass(className);
			if (c != null) {
				classes.add(c);
				if (Msg.verbose) {
					Msg.msgln("Loaded test " + c.getName() + " from file " + fileName);
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
			Msg.reportResults(result);
		}
	}
	
	/**
	 * Gets the java class name by reading the file and looking for the package
	 * and class declaration lines
	 * @param filename The name of the file to parse
	 * @return The class name of the file
	 */
	public static String getClassname(String filename) {
	    String packageDecl = null;
	    String className = null;
		String fileContents = "";
		try {
			fileContents = InMemoryFileSystem.getFile(filename).getCharContent(false).toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
	    String packageRegexp = "\\bpackage\\s*(.*?);";
	    Pattern packagePattern = Pattern.compile(packageRegexp);
		Matcher match = packagePattern.matcher(fileContents);
		if (match.find()) {
			packageDecl = match.group(1);
		}
	    
	    String classRegexp = ".*?\\bpublic.*?class\\s*(.*?)\\s.*?";
	    Pattern classPattern = Pattern.compile(classRegexp);
		match = classPattern.matcher(fileContents);
		if (match.find()) {
			className = match.group(1);
		}
		return packageDecl + "." + className;
	}
}
