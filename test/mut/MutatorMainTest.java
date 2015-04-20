package mut;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import mut.util.Msg;

import org.junit.Test;

public class MutatorMainTest {
	
	private String doRepl(String input) {
		InputStream in = new ByteArrayInputStream(input.getBytes());
		ByteArrayOutputStream bytes = new ByteArrayOutputStream();
		PrintStream out = new PrintStream(bytes);
		MutatorMain.repl(in, out, new Msg());
		return bytes.toString();
	}

	@Test
	public void quitWithQ() {
		String out = doRepl("q");
		assertEquals("> ", out);
	}

	@Test
	public void quitWithExit() {
		String out = doRepl("exit");
		assertEquals("> ", out);
	}

	@Test
	public void quitWithQuit() {
		String out = doRepl("quit");
		assertEquals("> ", out);
	}

	@Test
	public void printEmptySources() {
		String out = doRepl("list source\nq");
		assertEquals("> Sources: \r\n> " ,out);
	}

	@Test
	public void printEmptyTests() {
		String out = doRepl("list test\nq");
		assertEquals("> Tests: \r\n> ", out);
	}

	@Test
	public void setSourcesAndPrint() {
		String out = doRepl("source: .gitignore, sampleMutationFiles/src/\nlist source\nq");
		assertEquals("> > Sources: .gitignore, sampleMutationFiles/src/LogicalFunctions.java, sampleMutationFiles/src/MathFunctions.java\r\n> ", out);
	}

	@Test
	public void setBadSource() {
		String out = doRepl("source: source.java, source/\nq");
		assertEquals("> File source.java does not exist!\r\nFile source/ does not exist!\r\n> ", out);
	}

	@Test
	public void addSourcesAndPrint() {
		String out = doRepl("source: .classpath, sampleMutationFiles/src/\nadd source src/mut/MutatorMain.java\nlist source\nq");
		assertEquals("> > > Sources: .classpath, src/mut/MutatorMain.java, sampleMutationFiles/src/LogicalFunctions.java, sampleMutationFiles/src/MathFunctions.java\r\n> ", out);
	}

	@Test
	public void removeSourcesAndPrint() {
		String out = doRepl("source: .classpath, src/\nremove source src/\nlist source\nq");
		assertEquals("> > > Sources: .classpath\r\n> ", out);
	}

	@Test
	public void setTestsAndPrint() {
		String out = doRepl("test: .classpath, sampleMutationFiles/test/\nlist test\nq");
		assertEquals("> > Tests: sampleMutationFiles/test/LogicalFunctionsTest.java, .classpath, sampleMutationFiles/test/MathFunctionsTest.java\r\n> ", out);
	}

	@Test
	public void setBadTest() {
		String out = doRepl("source: test.java, tst/\nq");
		assertEquals("> File test.java does not exist!\r\nFile tst/ does not exist!\r\n> ", out);
	}

	@Test
	public void addTestsAndPrint() {
		String out = doRepl("test: .gitignore, sampleMutationFiles/test/\nadd test test/mut/MutatorMainTest.java\nlist test\nq");
		assertEquals("> > > Tests: .gitignore, sampleMutationFiles/test/LogicalFunctionsTest.java, sampleMutationFiles/test/MathFunctionsTest.java, test/mut/MutatorMainTest.java\r\n> ", out);
	}

	@Test
	public void removeTestsAndPrint() {
		String out = doRepl("test: .gitignore, test/\nremove test test/\nlist test\nq");
		assertEquals("> > > Tests: .gitignore\r\n> ", out);
	}

	@Test
	public void addUseFile() {
		String out = doRepl("use sampleMutationFiles/math.mut\nq");
		assertEquals("> > ", out);
	}

	@Test
	public void addBadUse() {
		String out = doRepl("use use.java\nq");
		assertEquals("> File use.java does not exist!\r\n> ", out);
	}

	@Test
	public void badInput() {
		String out = doRepl("us\nq");
		assertEquals("> no viable alternative at input 'us'\r\n> ", out);
	}

	@Test
	public void ignoreEmptyCommandAndStripSpaces() {
		String out = doRepl("\n \t\n\tq ");
		assertEquals("> > > ", out);
	}

}
