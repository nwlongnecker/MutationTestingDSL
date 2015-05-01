package mut.files;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collection;

import mut.util.Msg;

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
		String contents = FileReader.readFile(filename, new Msg());
		assertEquals("us\nq\nagsd\n", contents);
		assertTrue(new File(filename).delete());
	}

	@Test
	public void readFileBadPath() {
		String contents = FileReader.readFile("badFilename", new Msg());
		assertNull(contents);
	}
	
	@Test
	public void getAllFilesInDir() {
		Collection<String> files = FileReader.getAllFilesInDir("sampleMutationFiles", "");
		assertEquals(6, files.size());
		assertTrue(files.contains("/math.mut"));
		assertTrue(files.contains("/logic.mut"));
		assertTrue(files.contains("/src/LogicalFunctions.java"));
		assertTrue(files.contains("/src/MathFunctions.java"));
		assertTrue(files.contains("/test/LogicalFunctionsTest.java"));
		assertTrue(files.contains("/test/MathFunctionsTest.java"));
	}
	
	@Test
	public void findMatchingFilesInDirTreeTest() {
		Collection<String> files = FileReader.findMatchingFilesInDirTree("sampleMutationFiles", "*.java");
		assertEquals(4, files.size());
		assertTrue(files.contains("src/LogicalFunctions.java"));
		assertTrue(files.contains("src/MathFunctions.java"));
		assertTrue(files.contains("test/LogicalFunctionsTest.java"));
		assertTrue(files.contains("test/MathFunctionsTest.java"));
	}
	
	@Test
	public void findMatchingFilesInDirTreeTestCrossFolder() {
		Collection<String> files = FileReader.findMatchingFilesInDirTree("sampleMutationFiles", "s*.java");
		assertEquals(2, files.size());
		assertTrue(files.contains("src/LogicalFunctions.java"));
		assertTrue(files.contains("src/MathFunctions.java"));
	}
	
	@Test
	public void findMatchingFilesMultipleStars() {
		Collection<String> files = FileReader.findMatchingFilesInDirTree("src", "*lexparse*.tokens");
		assertEquals(2, files.size());
		assertTrue(files.contains("mut/lexparse/Mutator.tokens"));
		assertTrue(files.contains("mut/lexparse/MutatorLexer.tokens"));
	}
}
