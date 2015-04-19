package mut.files;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.tools.JavaFileObject;

public class InMemoryFileSystem {

	private static Map<String, SourceCode> files = new HashMap<String, SourceCode>();
	private static Map<String, String> originalFileContents = new HashMap<String, String>();

	public static void addFile(String filepath, String fileSource) {
		files.put(filepath, new SourceCode(filepath, fileSource));
	}
	
	public static SourceCode getFile(String filename) {
		return files.get(filename);
	}
	
	public static boolean removeFile(String filename) {
		return files.remove(filename) != null;
	}
	
	public static void addOriginalSourceFile(String filepath, String fileSource) {
		originalFileContents.put(filepath, fileSource);
	}
	
	public static String getOriginalSourceFile(String filename) {
		return originalFileContents.get(filename);
	}
	
	public static boolean removeOriginalSourceFile(String filename) {
		return originalFileContents.remove(filename) != null;
	}
	
	public static Collection<JavaFileObject> listFiles(Set<JavaFileObject.Kind> kinds) {
		Collection<JavaFileObject> filesOfKind = new HashSet<JavaFileObject>();
		for(SourceCode file : files.values()) {
			if (kinds.contains(file.getKind())) {
				filesOfKind.add(file);
			}
		}
		return filesOfKind;
	}
	
	public static boolean hasFile(JavaFileObject file) {
		return files.containsValue(file);
	}
}
