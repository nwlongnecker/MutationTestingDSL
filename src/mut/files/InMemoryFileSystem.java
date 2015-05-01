package mut.files;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.tools.JavaFileObject;

/**
 * An in memory file system implementation using hashmaps to map filenames to their contents
 * @author Nathan Longnecker
 */
public class InMemoryFileSystem implements Cloneable {

	/**
	 * Files that the compiler will use when compiling. The files in here will change frequently with mutations.
	 */
	private Map<String, SourceCode> files = new HashMap<String, SourceCode>();
	/**
	 * Original file contents. Intended to be write once, read many. Used to get a fresh version of the file before each mutation.
	 */
	private Map<String, String> originalFileContents = new HashMap<String, String>();

	/**
	 * Adds a file to the file system. Overwrites any preexisting file with the same name
	 * @param filepath The filename
	 * @param fileSource The contents of the file
	 */
	public void addFile(String filepath, String fileSource) {
		files.put(filepath, new SourceCode(filepath, fileSource));
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		InMemoryFileSystem fileSystem = new InMemoryFileSystem();
		for (String key : files.keySet()) {
			try {
				fileSystem.addFile(key, (String)files.get(key).getCharContent(false));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		for (String key : originalFileContents.keySet()) {
			fileSystem.addOriginalSourceFile(key, originalFileContents.get(key));
		}
	    return fileSystem;
	}
	
	/**
	 * Reads a file from the InMemoryFileSystem
	 * @param filename The file to retrieve
	 * @return The source file
	 */
	public SourceCode getFile(String filename) {
		return files.get(filename);
	}
	
	/**
	 * Removes/deletes a file from the file system
	 * @param filename The file to delete
	 * @return Whether the deletion was successful
	 */
	public boolean removeFile(String filename) {
		return files.remove(filename) != null;
	}
	
	/**
	 * Adds a file to the list of original source files
	 * @param filepath The name of the file
	 * @param fileSource The contents of the file
	 */
	public void addOriginalSourceFile(String filepath, String fileSource) {
		originalFileContents.put(filepath, fileSource);
	}
	
	/**
	 * Reads an original source file
	 * @param filename The name of the file
	 * @return The source contents
	 */
	public String getOriginalSourceFile(String filename) {
		return originalFileContents.get(filename);
	}
	
	/**
	 * Deletes a file from the file system
	 * @param filename The name of the file
	 * @return Whether the deletion was successful
	 */
	public boolean removeOriginalSourceFile(String filename) {
		return originalFileContents.remove(filename) != null;
	}
	
	/**
	 * Gets a list of files in the in memory file system
	 * @param kinds The kinds of files to list
	 * @return A list of file objects that exist in the file system and are of the appropriate type
	 */
	public Collection<JavaFileObject> listFiles(Set<JavaFileObject.Kind> kinds) {
		Collection<JavaFileObject> filesOfKind = new HashSet<JavaFileObject>();
		for(SourceCode file : files.values()) {
			if (kinds.contains(file.getKind())) {
				filesOfKind.add(file);
			}
		}
		return filesOfKind;
	}
	
	/**
	 * Checks whether the file system has the file
	 * @param file The name of the file
	 * @return Whether the file system has the file
	 */
	public boolean hasFile(JavaFileObject file) {
		return files.containsValue(file);
	}
	
	/**
	 * Gets the java class name by reading the file and looking for the package
	 * and class declaration lines using regex
	 * @param filename The name of the file to parse
	 * @return The class name of the file
	 */
	public String getClassname(String filename) {
	    String packageDecl = null;
	    String className = null;
		String fileContents = "";
		try {
			fileContents = getFile(filename).getCharContent(false).toString();
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
