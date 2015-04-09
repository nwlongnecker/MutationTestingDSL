package utility;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import mut.utility.Utility;

import org.junit.Test;

public class UtilityTest {

	@Test
	public void readFileTest() {
		String filename = "readFileTest";
		try (FileOutputStream out = new FileOutputStream(filename)) {
			out.write("us\nq\nagsd\n".getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		String contents = Utility.readFile(filename);
		assertEquals("us\nq\nagsd\n", contents);
		assertTrue(new File(filename).delete());
	}

	@Test
	public void readFileBadPath() {
		String contents = Utility.readFile("badFilename");
		assertNull(contents);
	}
}
