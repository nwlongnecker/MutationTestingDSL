package mut;

import static org.junit.Assert.assertEquals;
import mut.interpreter.InterpreterState;

import org.junit.Test;

public class ExecuteScriptTest {
	
	private InterpreterState state;
	
	private void execute(String script) {
		state = MutatorMain.executeScript(script);
	}

	@Test
	public void setSource() {
		execute("source: .gitignore, src/");
		assertEquals(2, state.getSourceFiles().size());
		assertEquals("src/", state.getSourceFiles().toArray()[1]);
		assertEquals(".gitignore", state.getSourceFiles().toArray()[0]);
	}

	@Test
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

	@Test
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

	@Test
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
