package mut.files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import mut.util.Msg;

public class FileReader {
	
	/**
	 * Reads the specified file and returns the contents as a string
	 * @param path Path to the file
	 * @return The file contents
	 */
	public static String readFile(String path)	{
		String ret = null;
		try {
			ret = new String(Files.readAllBytes(Paths.get(path)));
		} catch (IOException e) {
			Msg.err("Error reading from file " + e.getMessage());
		}
		return ret;
	}
}
