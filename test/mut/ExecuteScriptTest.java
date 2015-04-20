package mut;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import mut.interpreter.InterpreterState;
import mut.util.Msg;

import org.junit.BeforeClass;
import org.junit.Test;

public class ExecuteScriptTest {
	
	private InterpreterState state;
	private String outputString;
	private String errorString;
	
	@BeforeClass
	public static void setup() {
		InterpreterState.TESTING = true;
	}
	
	private void execute(String script) {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ByteArrayOutputStream err = new ByteArrayOutputStream();
		Msg.setOut(new PrintStream(out));
		Msg.setErr(new PrintStream(err));
		state = MutatorMain.executeScript(script);
		outputString = out.toString().replaceAll("(^|\n)\\d+: ", "\n");
		if(!outputString.isEmpty() && outputString.charAt(0) == '\n') {
			outputString = outputString.substring(1);
		}
		errorString = err.toString();
		if (!errorString.isEmpty()) {
			System.out.println(errorString);
		}
	}

	@Test
	public void setSource() {
		execute("source: .gitignore, sampleMutationFiles/src");
		assertEquals(3, state.getSourceFiles().size());
		assertEquals("sampleMutationFiles/src/MathFunctions.java", state.getSourceFiles().toArray()[2]);
		assertEquals("sampleMutationFiles/src/LogicalFunctions.java", state.getSourceFiles().toArray()[1]);
		assertEquals(".gitignore", state.getSourceFiles().toArray()[0]);
	}

	@Test
	public void setInvalidSource() {
		execute("source: .gitignore, asdfasdf.file, source/");
		assertEquals(1, state.getSourceFiles().size());
		assertEquals(".gitignore", state.getSourceFiles().toArray()[0]);
	}

	@Test
	public void setTest() {
		execute("test: .gitignore, sampleMutationFiles/test/");
		assertEquals(3, state.getTestFiles().size());
		assertEquals(".gitignore", state.getTestFiles().toArray()[0]);
		assertEquals("sampleMutationFiles/test/MathFunctionsTest.java", state.getTestFiles().toArray()[2]);
		assertEquals("sampleMutationFiles/test/LogicalFunctionsTest.java", state.getTestFiles().toArray()[1]);
	}

	@Test
	public void setInvalidTest() {
		execute("test: .gitignore, asdfasdf.file, tst/");
		assertEquals(1, state.getTestFiles().size());
		assertEquals(".gitignore", state.getTestFiles().toArray()[0]);
	}

	@Test
	public void addUse() {
		execute("use sampleMutationFiles/math.mut");
		assertEquals(1, state.getUseFiles().size());
		assertEquals("sampleMutationFiles/math.mut", state.getUseFiles().toArray()[0]);
	}

	@Test
	public void addInvalidUse() {
		execute("use asdfasdf.file, tst/");
		assertEquals(0, state.getUseFiles().size());
	}

	@Test
	public void addSource() {
		execute("add source .gitignore, sampleMutationFiles/src/");
		assertEquals(3, state.getSourceFiles().size());
		assertEquals(".gitignore", state.getSourceFiles().toArray()[0]);
		assertEquals("sampleMutationFiles/src/LogicalFunctions.java", state.getSourceFiles().toArray()[1]);
		assertEquals("sampleMutationFiles/src/MathFunctions.java", state.getSourceFiles().toArray()[2]);
	}

	@Test
	public void removeSource() {
		execute("add source .gitignore, sampleMutationFiles/src/LogicalFunctions.java remove source .gitignore");
		assertEquals(1, state.getSourceFiles().size());
		assertEquals("sampleMutationFiles/src/LogicalFunctions.java", state.getSourceFiles().toArray()[0]);
	}

	@Test
	public void addTest() {
		execute("add test .gitignore, sampleMutationFiles/test");
		assertEquals(3, state.getTestFiles().size());
		assertEquals(".gitignore", state.getTestFiles().toArray()[0]);
		assertEquals("sampleMutationFiles/test/MathFunctionsTest.java", state.getTestFiles().toArray()[2]);
		assertEquals("sampleMutationFiles/test/LogicalFunctionsTest.java", state.getTestFiles().toArray()[1]);
	}

	@Test
	public void removeTest() {
		execute("add test .gitignore, test/ remove test test/");
		assertEquals(1, state.getTestFiles().size());
		assertEquals(".gitignore", state.getTestFiles().toArray()[0]);
	}

	@Test
	public void runJUnitTests() {
		execute("add test sampleMutationFiles/test/LogicalFunctionsTest.java add source sampleMutationFiles/src/LogicalFunctions.java, sampleMutationFiles/src/MathFunctions.java mutate > to <");
		assertEquals(2, state.getSourceFiles().size());
		assertEquals(1, state.getTestFiles().size());
		assertEquals("sampleMutationFiles/test/LogicalFunctionsTest.java", state.getTestFiles().toArray()[0]);
		assertEquals("sampleMutationFiles/src/MathFunctions.java", state.getSourceFiles().toArray()[1]);
		assertEquals("sampleMutationFiles/src/LogicalFunctions.java", state.getSourceFiles().toArray()[0]);
		System.out.println(errorString);
	}

	@Test
	public void mutateCodePasses() {
		Msg.verbose = false;
		execute("source: sampleMutationFiles/src/LogicalFunctions.java "
				+ "test: sampleMutationFiles/test/LogicalFunctionsTest.java "
				+ "mutate && to || "
				+ "mutate && to || "
				+ "mutate || to && "
				+ "mutate >,<,<=,>= to >,<,<=,>=");
		assertEquals(1, state.getTestFiles().size());
		assertEquals(1, state.getSourceFiles().size());
		assertEquals("sampleMutationFiles/test/LogicalFunctionsTest.java", state.getTestFiles().toArray()[0]);
		assertEquals("sampleMutationFiles/src/LogicalFunctions.java", state.getSourceFiles().toArray()[0]);
		assertEquals("Mutating && to || in files LogicalFunctions.java with tests LogicalFunctionsTest.java\r\n"
				+ "Mutating && to || in files LogicalFunctions.java with tests LogicalFunctionsTest.java\r\n"
				+ "Mutating || to && in files LogicalFunctions.java with tests LogicalFunctionsTest.java\r\n"
				+ "Mutating <=,<,>,>= to <=,<,>,>= in files LogicalFunctions.java with tests LogicalFunctionsTest.java\r\n", outputString);
	}

	@Test
	public void mutateCodeFailures() {
		Msg.verbose = false;
		execute("source: sampleMutationFiles/src/MathFunctions.java "
				+ "test: sampleMutationFiles/test/MathFunctionsTest.java "
				+ "mutate +,-,*,/ to +,-,*,/");
		assertEquals(1, state.getTestFiles().size());
		assertEquals(1, state.getSourceFiles().size());
		assertEquals("sampleMutationFiles/test/MathFunctionsTest.java", state.getTestFiles().toArray()[0]);
		assertEquals("sampleMutationFiles/src/MathFunctions.java", state.getSourceFiles().toArray()[0]);
		assertEquals("Mutating *,+,-,/ to *,+,-,/ in files MathFunctions.java with tests MathFunctionsTest.java\r\n"
				+ "MathFunctions.java line 14: Mutant survived when mutating * to +\r\n"
				+ "MathFunctions.java line 18: Mutant survived when mutating / to *\r\n"
				+ "MathFunctions.java line 18: Mutant survived when mutating / to +\r\n"
				+ "MathFunctions.java line 18: Mutant survived when mutating / to -\r\n", outputString);
	}
	
	@Test
	public void addDirectory() {
		Msg.verbose = false;
		execute("source: sampleMutationFiles/src/ "
				+ "test: sampleMutationFiles/test/ "
				+ "mutate +,-,*,/ to +,-,*,/");
		assertEquals(2, state.getSourceFiles().size());
		assertEquals(2, state.getTestFiles().size());
		assertEquals("sampleMutationFiles/test/MathFunctionsTest.java", state.getTestFiles().toArray()[1]);
		assertEquals("sampleMutationFiles/test/LogicalFunctionsTest.java", state.getTestFiles().toArray()[0]);
		assertEquals("sampleMutationFiles/src/MathFunctions.java", state.getSourceFiles().toArray()[1]);
		assertEquals("sampleMutationFiles/src/LogicalFunctions.java", state.getSourceFiles().toArray()[0]);
		assertEquals("Mutating *,+,-,/ to *,+,-,/ in files LogicalFunctions.java,MathFunctions.java with tests LogicalFunctionsTest.java,MathFunctionsTest.java\r\n"
				+ "MathFunctions.java line 14: Mutant survived when mutating * to +\r\n"
				+ "MathFunctions.java line 18: Mutant survived when mutating / to *\r\n"
				+ "MathFunctions.java line 18: Mutant survived when mutating / to +\r\n"
				+ "MathFunctions.java line 18: Mutant survived when mutating / to -\r\n", outputString);
	}
	
	@Test
	public void mutateStrain() {
		Msg.verbose = false;
		execute("source: sampleMutationFiles/src/ "
				+ "test: sampleMutationFiles/test/ "
				+ "strain math "
				+ "mutate +,-,*,/ to +,-,*,/ "
				+ "end "
				+ "mutate math");
		assertEquals(2, state.getSourceFiles().size());
		assertEquals(2, state.getTestFiles().size());
		assertEquals("sampleMutationFiles/test/MathFunctionsTest.java", state.getTestFiles().toArray()[1]);
		assertEquals("sampleMutationFiles/test/LogicalFunctionsTest.java", state.getTestFiles().toArray()[0]);
		assertEquals("sampleMutationFiles/src/MathFunctions.java", state.getSourceFiles().toArray()[1]);
		assertEquals("sampleMutationFiles/src/LogicalFunctions.java", state.getSourceFiles().toArray()[0]);
		assertEquals("Mutating *,+,-,/ to *,+,-,/ in files LogicalFunctions.java,MathFunctions.java with tests LogicalFunctionsTest.java,MathFunctionsTest.java\r\n"
				+ "MathFunctions.java line 14: Mutant survived when mutating * to +\r\n"
				+ "MathFunctions.java line 18: Mutant survived when mutating / to *\r\n"
				+ "MathFunctions.java line 18: Mutant survived when mutating / to +\r\n"
				+ "MathFunctions.java line 18: Mutant survived when mutating / to -\r\n", outputString);
	}
	
	@Test
	public void mutateStrainMultipleLines() {
		Msg.verbose = false;
		execute("source: sampleMutationFiles/src/ "
				+ "test: sampleMutationFiles/test/ "
				+ "strain math "
				+ "mutate +,-,*,/ to +,-,*,/ "
				+ "mutate &&, || to ||, && "
				+ "end "
				+ "mutate math");
		assertEquals(2, state.getSourceFiles().size());
		assertEquals(2, state.getTestFiles().size());
		assertEquals("sampleMutationFiles/test/MathFunctionsTest.java", state.getTestFiles().toArray()[1]);
		assertEquals("sampleMutationFiles/test/LogicalFunctionsTest.java", state.getTestFiles().toArray()[0]);
		assertEquals("sampleMutationFiles/src/MathFunctions.java", state.getSourceFiles().toArray()[1]);
		assertEquals("sampleMutationFiles/src/LogicalFunctions.java", state.getSourceFiles().toArray()[0]);
		assertEquals("Mutating *,+,-,/ to *,+,-,/ in files LogicalFunctions.java,MathFunctions.java with tests LogicalFunctionsTest.java,MathFunctionsTest.java\r\n"
				+ "MathFunctions.java line 14: Mutant survived when mutating * to +\r\n"
				+ "MathFunctions.java line 18: Mutant survived when mutating / to *\r\n"
				+ "MathFunctions.java line 18: Mutant survived when mutating / to +\r\n"
				+ "MathFunctions.java line 18: Mutant survived when mutating / to -\r\n"
				+ "Mutating &&,|| to ||,&& in files LogicalFunctions.java,MathFunctions.java with tests LogicalFunctionsTest.java,MathFunctionsTest.java\r\n", outputString);
	}
	
	@Test
	public void mutateMultipleStrains() {
		Msg.verbose = false;
		execute("source: sampleMutationFiles/src/ "
				+ "test: sampleMutationFiles/test/ "
				+ "strain math "
				+ "mutate +,-,*,/ to +,-,*,/ "
				+ "end "
				+ "strain logic "
				+ "mutate &&, || to ||, && "
				+ "end "
				+ "mutate math, logic");
		assertEquals(2, state.getSourceFiles().size());
		assertEquals(2, state.getTestFiles().size());
		assertEquals("sampleMutationFiles/test/MathFunctionsTest.java", state.getTestFiles().toArray()[1]);
		assertEquals("sampleMutationFiles/test/LogicalFunctionsTest.java", state.getTestFiles().toArray()[0]);
		assertEquals("sampleMutationFiles/src/MathFunctions.java", state.getSourceFiles().toArray()[1]);
		assertEquals("sampleMutationFiles/src/LogicalFunctions.java", state.getSourceFiles().toArray()[0]);
		assertEquals("Mutating *,+,-,/ to *,+,-,/ in files LogicalFunctions.java,MathFunctions.java with tests LogicalFunctionsTest.java,MathFunctionsTest.java\r\n"
				+ "MathFunctions.java line 14: Mutant survived when mutating * to +\r\n"
				+ "MathFunctions.java line 18: Mutant survived when mutating / to *\r\n"
				+ "MathFunctions.java line 18: Mutant survived when mutating / to +\r\n"
				+ "MathFunctions.java line 18: Mutant survived when mutating / to -\r\n"
				+ "Mutating &&,|| to ||,&& in files LogicalFunctions.java,MathFunctions.java with tests LogicalFunctionsTest.java,MathFunctionsTest.java\r\n", outputString);
	}
	
	@Test
	public void useStrainInOtherFile() {
		Msg.verbose = false;
		execute("source: sampleMutationFiles/src/ "
				+ "test: sampleMutationFiles/test/ "
				+ "use sampleMutationFiles/math.mut "
				+ "mutate math");
		assertEquals(2, state.getSourceFiles().size());
		assertEquals(2, state.getTestFiles().size());
		assertEquals(1, state.getUseFiles().size());
		assertEquals("sampleMutationFiles/test/MathFunctionsTest.java", state.getTestFiles().toArray()[1]);
		assertEquals("sampleMutationFiles/test/LogicalFunctionsTest.java", state.getTestFiles().toArray()[0]);
		assertEquals("sampleMutationFiles/src/MathFunctions.java", state.getSourceFiles().toArray()[1]);
		assertEquals("sampleMutationFiles/src/LogicalFunctions.java", state.getSourceFiles().toArray()[0]);
		assertEquals("sampleMutationFiles/math.mut", state.getUseFiles().toArray()[0]);
		assertEquals("Mutating *,+,-,/ to *,+,-,/ in files LogicalFunctions.java,MathFunctions.java with tests LogicalFunctionsTest.java,MathFunctionsTest.java\r\n"
				+ "MathFunctions.java line 14: Mutant survived when mutating * to +\r\n"
				+ "MathFunctions.java line 18: Mutant survived when mutating / to *\r\n"
				+ "MathFunctions.java line 18: Mutant survived when mutating / to +\r\n"
				+ "MathFunctions.java line 18: Mutant survived when mutating / to -\r\n", outputString);
	}
	
	@Test
	public void redefineStrain() {
		Msg.verbose = false;
		execute("source: sampleMutationFiles/src/ "
				+ "test: sampleMutationFiles/test/ "
				+ "use sampleMutationFiles/math.mut "
				+ "strain math "
				+ "mutate +,-,*,/ to +,-,*,/ "
				+ "end "
				+ "strain logic "
				+ "mutate &&, || to ||, && "
				+ "end "
				+ "mutate math, logic");
		assertEquals(2, state.getSourceFiles().size());
		assertEquals(2, state.getTestFiles().size());
		assertEquals("sampleMutationFiles/test/MathFunctionsTest.java", state.getTestFiles().toArray()[1]);
		assertEquals("sampleMutationFiles/test/LogicalFunctionsTest.java", state.getTestFiles().toArray()[0]);
		assertEquals("sampleMutationFiles/src/MathFunctions.java", state.getSourceFiles().toArray()[1]);
		assertEquals("sampleMutationFiles/src/LogicalFunctions.java", state.getSourceFiles().toArray()[0]);
		assertEquals("Redefined strain math\r\n"
				+ "Mutating *,+,-,/ to *,+,-,/ in files LogicalFunctions.java,MathFunctions.java with tests LogicalFunctionsTest.java,MathFunctionsTest.java\r\n"
				+ "MathFunctions.java line 14: Mutant survived when mutating * to +\r\n"
				+ "MathFunctions.java line 18: Mutant survived when mutating / to *\r\n"
				+ "MathFunctions.java line 18: Mutant survived when mutating / to +\r\n"
				+ "MathFunctions.java line 18: Mutant survived when mutating / to -\r\n"
				+ "Mutating &&,|| to ||,&& in files LogicalFunctions.java,MathFunctions.java with tests LogicalFunctionsTest.java,MathFunctionsTest.java\r\n", outputString);
	}
}
