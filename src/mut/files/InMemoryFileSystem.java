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

public class InMemoryFileSystem implements Cloneable {

	private Map<String, SourceCode> files = new HashMap<String, SourceCode>();
	private Map<String, String> originalFileContents = new HashMap<String, String>();

	public void addFile(String filepath, String fileSource) {
		files.put(filepath, new SourceCode(filepath, fileSource));
	}
	
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
	
	public SourceCode getFile(String filename) {
		return files.get(filename);
	}
	
	public boolean removeFile(String filename) {
		return files.remove(filename) != null;
	}
	
	public void addOriginalSourceFile(String filepath, String fileSource) {
		originalFileContents.put(filepath, fileSource);
	}
	
	public String getOriginalSourceFile(String filename) {
		return originalFileContents.get(filename);
	}
	
	public boolean removeOriginalSourceFile(String filename) {
		return originalFileContents.remove(filename) != null;
	}
	
	public Collection<JavaFileObject> listFiles(Set<JavaFileObject.Kind> kinds) {
		Collection<JavaFileObject> filesOfKind = new HashSet<JavaFileObject>();
		for(SourceCode file : files.values()) {
			if (kinds.contains(file.getKind())) {
				filesOfKind.add(file);
			}
		}
		return filesOfKind;
	}
	
	public boolean hasFile(JavaFileObject file) {
		return files.containsValue(file);
	}
	
	/**
	 * Gets the java class name by reading the file and looking for the package
	 * and class declaration lines
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
