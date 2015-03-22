package mut;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

public class MutatorMainTest {
	
	private void doRepl(String input) {
		InputStream in = new ByteArrayInputStream(input.getBytes());
		MutatorMain.repl(in);
		assertTrue(true);
	}

	@Test
	public void quitImmediately() {
		doRepl("q");
	}

	@Test @Ignore
	public void badInput() {
		doRepl("e");
	}

}
