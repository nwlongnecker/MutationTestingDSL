package mut.utility;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import mut.runtime.MutatorRuntime;

public class Utility {
	
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
			MutatorRuntime.printError("Error reading from file " + e.getMessage());
			return null;
		}
		return new String(encoded);
	}
	
	/**
	 * Writes the specified contents to the specified file
	 * @param path Path to the file
	 * @return The file contents
	 */
	public static boolean writeFile(String path, String contents)	{
		try (FileWriter fw = new FileWriter(path)) {
			fw.append(contents);
		} catch (IOException e) {
			MutatorRuntime.printError("Error writing file " + e.getMessage());
			return false;
		}
		return true;
	}
	
	/**
	 * Writes the specified contents to the specified file
	 * @param file The file
	 * @return The file contents
	 */
	public static boolean writeFile(File file, String contents)	{
		try (FileWriter fw = new FileWriter(file)) {
			fw.append(contents);
		} catch (IOException e) {
			MutatorRuntime.printError("Error writing file " + e.getMessage());
			return false;
		}
		return true;
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
	
	/**
	 * Gets the file name with package extensions by reading the file and looking for the package
	 * and class declaration lines
	 * @param filename The name of the file to parse
	 * @return The filename of the file
	 */
	public static String getFilename(String filename) {
		return getClassname(filename).replace('.', '/').concat(".java");
	}
	
	public static boolean deleteDirectory(File directory) {
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
