package mut.utility;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utility {

	/**
	 * Takes a given string and attempts to find the class associated with it.
	 * Prefers files in the form of package.package.package.classname, but if that fails then
	 * assumes the string is a filepath and parses the folders as packages.
	 * @param filename The string describing the file
	 * @return Returns the class file if found, null otherwise
	 */
	public static Class<?> getClass(String filename) {
		Class<?> c = null;
		
		try {
			c = Class.forName(getClassname(filename));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return c;
	}
	
	/**
	 * Reads the specified file and returns the contents as a string
	 * @param path Path to the file
	 * @return The file contents
	 */
	public static String readFile(String path)	{
		byte[] encoded = null;
		try {
			encoded = Files.readAllBytes(Paths.get(path));
		} catch (IOException e) {
			System.out.println("Error reading from file " + e.getMessage());
			return null;
		}
		return new String(encoded);
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
