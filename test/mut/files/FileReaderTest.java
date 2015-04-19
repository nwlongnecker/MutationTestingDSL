package mut.files;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import mut.files.FileReader;

import org.junit.Test;

public class FileReaderTest {

	@Test
	public void readFileTest() {
		String filename = "readFileTest";
		try (FileOutputStream out = new FileOutputStream(filename)) {
			out.write("us\nq\nagsd\n".getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		String contents = FileReader.readFile(filename);
		assertEquals("us\nq\nagsd\n", contents);
		assertTrue(new File(filename).delete());
	}

	@Test
	public void readFileBadPath() {
		String contents = FileReader.readFile("badFilename");
		assertNull(contents);
	}
}
