package mut.junit;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import mut.util.Msg;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

public class MutatorJUnitRunner {
	private ClassLoader classLoader;
	private JUnitCore junit;
	
	private boolean verbose;

	public MutatorJUnitRunner(ClassLoader classLoader) {
		this.classLoader = classLoader;
		junit = new JUnitCore();
		verbose = false;
	}
	
	public Collection<Class<?>> loadClasses(Collection<String> fileNames) throws ClassNotFoundException {
		Collection<Class<?>> classes = new HashSet<Class<?>>();
		for(String fileName : fileNames) {
			String className = getClassname(fileName);
			Class<?> c = classLoader.loadClass(className);
			if (c != null) {
				classes.add(c);
				if (verbose) {
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
		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
		    String line;
		    String packageRegexp = "\\bpackage\\s*(.*?);";
		    Pattern packagePattern = Pattern.compile(packageRegexp);
		    String classRegexp = "\\bpublic.*?class\\s*(.*?)\\s.*?$";
		    Pattern classPattern = Pattern.compile(classRegexp);
		    while ((line = br.readLine()) != null) {
		    	line = line.trim();
		    	// Package declaration must come first
		    	if (packageDecl == null) {
		    		Matcher match = packagePattern.matcher(line);
		    		if (match.find()) {
		    			packageDecl = match.group(1);
		    		}
		    	} else {
		    		Matcher match = classPattern.matcher(line);
		    		if (match.find()) {
		    			className = match.group(1);
		    			break;
		    		}
		    	}
		    }
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return packageDecl + "." + className;
	}
}
