package mut.utility;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

import mut.runtime.MutatorRuntime;

public class MutClassLoader {
	
	private URLClassLoader classLoader;

	public MutClassLoader(File workingDir) {
		try {
			URL[] urls = new URL[] {workingDir.toURI().toURL()};
			classLoader = URLClassLoader.newInstance(urls);
		} catch (MalformedURLException e) {
			MutatorRuntime.printError("Malformed directory URL " + e.getMessage());
		}
	}
	
	public Class<?> loadClass(String filename) {
		Class<?> c = null;
		try {
			c = classLoader.loadClass(Utility.getClassname(filename));
			System.out.println("Loaded class " + c.getProtectionDomain().getCodeSource().getLocation());
		} catch (ClassNotFoundException e) {
			MutatorRuntime.printError("Error loading class " + e.getMessage());
		}
		return c;
	}
}
