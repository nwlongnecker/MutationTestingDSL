package mut;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import mut.interpreter.InterpreterState;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

public class ExecuteScriptTest {
	
	private InterpreterState state;
	private static final String DIRNAME = "testExecuteScript/";
	
	private void execute(String script) {
		state = MutatorMain.executeScript(script);
	}
	
	private static boolean deleteDirectory(File directory) {
	    if(directory.exists()){
	        File[] files = directory.listFiles();
	        if(null!=files){
	            for(int i=0; i<files.length; i++) {
	                if(files[i].isDirectory()) {
	                    deleteDirectory(files[i]);
	                } else {
	                    files[i].delete();
	                }
	            }
	        }
	    }
	    return directory.delete();
	}

	private static void writeFile(String filename, String contents) {
		try (FileOutputStream out = new FileOutputStream(filename)) {
			out.write(contents.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
//	@BeforeClass
//	public static void setup() {
//		new File(DIRNAME).mkdir();
//		new File(DIRNAME + "src").mkdir();
//		new File(DIRNAME + "test").mkdir();
//	}
//	
//	@AfterClass
//	public static void teardown() {
//		deleteDirectory(new File(DIRNAME));
//	}

	@Test
	public void setSource() {
		execute("source: .gitignore, src/");
		assertEquals(2, state.getSourceFiles().size());
		assertEquals("src/", state.getSourceFiles().toArray()[1]);
		assertEquals(".gitignore", state.getSourceFiles().toArray()[0]);
	}

	@Test @Ignore
	public void setInvalidSource() {
		execute("source: .gitignore, asdfasdf.file, source/, src/");
		assertEquals(2, state.getSourceFiles().size());
		assertEquals(".gitignore", state.getSourceFiles().toArray()[0]);
		assertEquals("src/", state.getSourceFiles().toArray()[1]);
	}

	@Test
	public void setTest() {
		execute("test: .gitignore, test/");
		assertEquals(2, state.getTestFiles().size());
		assertEquals(".gitignore", state.getTestFiles().toArray()[0]);
		assertEquals("test/", state.getTestFiles().toArray()[1]);
	}

	@Test @Ignore
	public void setInvalidTest() {
		execute("test: .gitignore, asdfasdf.file, tst/, test/");
		assertEquals(2, state.getTestFiles().size());
		assertEquals(".gitignore", state.getTestFiles().toArray()[0]);
		assertEquals("test/", state.getTestFiles().toArray()[1]);
	}

	@Test
	public void addUse() {
		execute("use .gitignore, test/");
		assertEquals(2, state.getUseFiles().size());
		assertEquals(".gitignore", state.getUseFiles().toArray()[0]);
		assertEquals("test/", state.getUseFiles().toArray()[1]);
	}

	@Test @Ignore
	public void addInvalidUse() {
		execute("use .gitignore, asdfasdf.file, tst/, test/");
		assertEquals(2, state.getUseFiles().size());
		assertEquals(".gitignore", state.getUseFiles().toArray()[0]);
		assertEquals("test/", state.getUseFiles().toArray()[1]);
	}

	@Test
	public void addSource() {
		execute("add source .gitignore, src/");
		assertEquals(2, state.getSourceFiles().size());
		assertEquals(".gitignore", state.getSourceFiles().toArray()[0]);
		assertEquals("src/", state.getSourceFiles().toArray()[1]);
	}

	@Test
	public void removeSource() {
		execute("add source .gitignore, src/ remove source .gitignore");
		assertEquals(1, state.getSourceFiles().size());
		assertEquals("src/", state.getSourceFiles().toArray()[0]);
	}

	@Test
	public void addTest() {
		execute("add test .gitignore, test/");
		assertEquals(2, state.getTestFiles().size());
		assertEquals(".gitignore", state.getTestFiles().toArray()[0]);
		assertEquals("test/", state.getTestFiles().toArray()[1]);
	}

	@Test
	public void removeTest() {
		execute("add test .gitignore, test/ remove test .gitignore");
		assertEquals(1, state.getTestFiles().size());
		assertEquals("test/", state.getTestFiles().toArray()[0]);
	}

	@Test
	public void runJUnitTests() {
		execute("add test sampleMutationFiles/test/MathFunctionsTest.java, sampleMutationFiles/test/LogicalFunctionsTest.java mutate > to <");
		assertEquals(2, state.getTestFiles().size());
		assertEquals("sampleMutationFiles/test/MathFunctionsTest.java", state.getTestFiles().toArray()[1]);
		assertEquals("sampleMutationFiles/test/LogicalFunctionsTest.java", state.getTestFiles().toArray()[0]);
	}
}
