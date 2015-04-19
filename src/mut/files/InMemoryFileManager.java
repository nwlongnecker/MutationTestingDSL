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

public class InMemoryFileManager extends ForwardingJavaFileManager<StandardJavaFileManager> {
	
	private Set<StandardLocation> locations;
	private CompiledClasses classes;
	
	public InMemoryFileManager(StandardJavaFileManager fileManager) {
		super(fileManager);
		locations = new HashSet<StandardLocation>();
		locations.add(StandardLocation.CLASS_PATH);
		locations.add(StandardLocation.SOURCE_PATH);
		locations.add(StandardLocation.CLASS_OUTPUT);
		classes = new CompiledClasses();
	}

	@Override
	public ClassLoader getClassLoader(Location location) {
		ClassLoader cl;
		if (Msg.verbose) {
			Msg.msgln("Retrieving class loader for location: " + location);
		}
		if(locations.contains(location)) {
			cl = new InMemoryClassLoader(ClassLoader.getSystemClassLoader(), classes);
		} else {
			cl = super.getClassLoader(location);
		}
		return cl;
	}

	@Override
	public Iterable<JavaFileObject> list(Location location, String packageName, Set<Kind> kinds, boolean recurse) throws IOException {
		if (Msg.verbose) {
			Msg.msg("The compiler is asking for a list of ");
			for(Kind kind : kinds) {
				Msg.msg(kind.toString());
			}
			Msg.msgln(" from " + location.getName());
		}
		
		Iterable<JavaFileObject> list = super.list(location, packageName, kinds, recurse);
		
		if (locations.contains(location)) {
			Collection<JavaFileObject> files = InMemoryFileSystem.listFiles(kinds);
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
		if (InMemoryFileSystem.hasFile(file)) {
			return file.getName();
		} else {
			return super.inferBinaryName(location, file);
		}
	}

	@Override
	public boolean hasLocation(Location location) {
		boolean ret = location.equals(StandardLocation.SOURCE_PATH) || location.equals(StandardLocation.CLASS_PATH) || super.hasLocation(location);
		if (Msg.verbose) {
			Msg.msgln("File manager reported it has " + location.getName() + ": " + ret);
		}
		return ret;
	}

	@Override
	public JavaFileObject getJavaFileForInput(Location location, String className, Kind kind) throws IOException {
		if (Msg.verbose) {
			Msg.msgln("Retrieving " + className);
		}
		return InMemoryFileSystem.getFile(className);
	}

	@Override
	public JavaFileObject getJavaFileForOutput(Location location, String className, Kind kind, FileObject sibling) throws IOException {
		if (locations.contains(location)) {
			if (Msg.verbose) {
				Msg.msgln("Getting file for output " + location + " " + className + " " + kind);
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
			if (Msg.verbose) {
				Msg.msgln("Getting system file for output: " + className);
			}
			return super.getJavaFileForOutput(location, className, kind, sibling);
		}
		throw new RuntimeException("not implemented");
	}

	@Override
	public void flush() throws IOException {
		// Does nothing
	}

	public Collection<JavaFileObject> getJavaFileObjectsForInputFromStrings(Collection<String> filePaths, JavaFileObject.Kind kind) throws IOException {
		Collection<JavaFileObject> fileObjects = new HashSet<JavaFileObject>();
		for (String filePath : filePaths) {
			fileObjects.add(getJavaFileForInput(null, filePath, kind));
		}
		return fileObjects;
	}
	
	public ClassLoader getClassLoader() {
		return getClassLoader(StandardLocation.CLASS_OUTPUT);
	}

}
