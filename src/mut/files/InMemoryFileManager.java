package mut.files;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.tools.FileObject;
import javax.tools.ForwardingJavaFileManager;
import javax.tools.JavaFileObject;
import javax.tools.JavaFileObject.Kind;
import javax.tools.StandardJavaFileManager;
import javax.tools.StandardLocation;

import mut.classload.CompiledClasses;
import mut.classload.InMemoryClassLoader;
import mut.util.Msg;

/**
 * A custom JavaFileManager implementation to allow the JavaCompiler to interface with the InMemoryFileSystem
 * @author Nathan Longnecker
 */
public class InMemoryFileManager extends ForwardingJavaFileManager<StandardJavaFileManager> {
	
	private Set<StandardLocation> locations;
	private CompiledClasses classes;
	private InMemoryFileSystem fileSystem;
	private Msg msg;
	
	/**
	 * Constructor
	 * @param fileManager The parent filemanager
	 * @param fileSystem The InMemoryFileSystem to use
	 * @param msg The message logger to print to
	 */
	public InMemoryFileManager(StandardJavaFileManager fileManager, InMemoryFileSystem fileSystem, Msg msg) {
		super(fileManager);
		locations = new HashSet<StandardLocation>();
		locations.add(StandardLocation.CLASS_PATH);
		locations.add(StandardLocation.SOURCE_PATH);
		locations.add(StandardLocation.CLASS_OUTPUT);
		classes = new CompiledClasses();
		this.fileSystem = fileSystem;
		this.msg = msg;
	}

	@Override
	public ClassLoader getClassLoader(Location location) {
		ClassLoader cl;
		if (msg.verbosity >= Msg.VERY_VERBOSE) {
			msg.msgln("Retrieving class loader for location: " + location);
		}
		if(locations.contains(location)) {
			// Must return a new class loader each time otherwise we get errors for trying to redefine a class
			cl = new InMemoryClassLoader(ClassLoader.getSystemClassLoader(), classes, msg);
		} else {
			cl = super.getClassLoader(location);
		}
		return cl;
	}

	@Override
	public Iterable<JavaFileObject> list(Location location, String packageName, Set<Kind> kinds, boolean recurse) throws IOException {
		if (msg.verbosity >= Msg.VERY_VERBOSE) {
			msg.msg("The compiler is asking for a list of ");
			for(Kind kind : kinds) {
				msg.msg(kind.toString());
			}
			msg.msgln(" from " + location.getName());
		}
		
		Iterable<JavaFileObject> list = super.list(location, packageName, kinds, recurse);
		
		if (locations.contains(location)) {
			Collection<JavaFileObject> files = fileSystem.listFiles(kinds);
			Iterator<JavaFileObject> iterator = list.iterator();
			while(iterator.hasNext()) {
				files.add(iterator.next());
			}
			list = files;
		}
		return list;
	}

	@Override
	public String inferBinaryName(Location location, JavaFileObject file) {
		if (fileSystem.hasFile(file)) {
			return file.getName();
		} else {
			return super.inferBinaryName(location, file);
		}
	}

	@Override
	public boolean hasLocation(Location location) {
		boolean ret = location.equals(StandardLocation.SOURCE_PATH) || location.equals(StandardLocation.CLASS_PATH) || super.hasLocation(location);
		if (msg.verbosity >= Msg.VERY_VERBOSE) {
			msg.msgln("File manager reported it has " + location.getName() + ": " + ret);
		}
		return ret;
	}

	@Override
	public JavaFileObject getJavaFileForInput(Location location, String className, Kind kind) throws IOException {
		if (msg.verbosity >= Msg.VERY_VERBOSE) {
			msg.msgln("Retrieving " + className);
		}
		return fileSystem.getFile(className);
	}

	@Override
	public JavaFileObject getJavaFileForOutput(Location location, String className, Kind kind, FileObject sibling) throws IOException {
		if (locations.contains(location)) {
			if (msg.verbosity >= Msg.VERY_VERBOSE) {
				msg.msgln("Getting file for output " + location + " " + className + " " + kind);
			}
			if (kind.equals(Kind.CLASS)) {
				CompiledCode output = null;
				try {
					output = new CompiledCode(className);
				} catch (URISyntaxException e) {
					e.printStackTrace();
				}
				classes.addCode(output);
				return output;
			}
		} else {
			if (msg.verbosity >= Msg.VERY_VERBOSE) {
				msg.msgln("Getting system file for output: " + className);
			}
			return super.getJavaFileForOutput(location, className, kind, sibling);
		}
		throw new RuntimeException("not implemented");
	}

	@Override
	public void flush() throws IOException {
		// Does nothing
	}

	/**
	 * Converts a list of filenames to a list of JavaFileObjects for writing
	 * @param filePaths The list of filenames to create JavaFileObjects for
	 * @param kind The kind of file objects to make
	 * @return Returns a list of file objects ready to read from or write to
	 * @throws IOException This implementation should never throw an IOException, because it does not read and write to files
	 */
	public Collection<JavaFileObject> getJavaFileObjectsForInputFromStrings(Collection<String> filePaths, JavaFileObject.Kind kind) throws IOException {
		Collection<JavaFileObject> fileObjects = new HashSet<JavaFileObject>();
		for (String filePath : filePaths) {
			fileObjects.add(getJavaFileForInput(null, filePath, kind));
		}
		return fileObjects;
	}
	
	/**
	 * @return a classloader
	 */
	public ClassLoader getClassLoader() {
		return getClassLoader(StandardLocation.CLASS_OUTPUT);
	}
	
	/**
	 * @return the InMemoryFileSystem
	 */
	public InMemoryFileSystem getFileSystem() {
		return fileSystem;
	}

}
