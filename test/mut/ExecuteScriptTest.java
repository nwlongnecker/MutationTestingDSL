package mut;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import mut.interpreter.InterpreterState;
import mut.util.Msg;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ExecuteScriptTest {
	
	private InterpreterState state;
	private String outputString;
	private String errorString;
	private Msg msg;
	
	@BeforeClass
	public static void setup() {
		InterpreterState.TESTING = true;
	}
	
	@Before
	public void testSetup() {
		msg = new Msg();
		msg.verbosity = Msg.SPARSE;
	}
	
	private void execute(String script) {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ByteArrayOutputStream err = new ByteArrayOutputStream();
		msg.setOut(new PrintStream(out));
		msg.setErr(new PrintStream(err));
		state = MutatorMain.executeScript(script, new InterpreterState(msg));
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
				+ "done\r\n"
				+ "Mutating && to || in files LogicalFunctions.java with tests LogicalFunctionsTest.java\r\n"
				+ "done\r\n"
				+ "Mutating || to && in files LogicalFunctions.java with tests LogicalFunctionsTest.java\r\n"
				+ "done\r\n"
				+ "Mutating <=,<,>,>= to <=,<,>,>= in files LogicalFunctions.java with tests LogicalFunctionsTest.java\r\n"
				+ "done\r\n", outputString);
	}

	@Test
	public void mutateCodeFailures() {
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
				+ "MathFunctions.java line 18: Mutant survived when mutating / to -\r\n"
				+ "done\r\n", outputString);
	}
	
	@Test
	public void addDirectory() {
		execute("source: sampleMutationFiles/src/ "
				+ "test: sampleMutationFiles/test/ "
				+ "mutate +,-,*,/ to +,-,*,/");
		assertEquals(2, state.getSourceFiles().size());
		assertEquals(2, state.getTestFiles().size());
		assertEquals("sampleMutationFiles/test/MathFunctionsTest.java", state.getTestFiles().toArray()[1]);
		assertEquals("sampleMutationFiles/test/LogicalFunctionsTest.java", state.getTestFiles().toArray()[0]);
		assertEquals("sampleMutationFiles/src/MathFunctions.java", state.getSourceFiles().toArray()[1]);
		assertEquals("sampleMutationFiles/src/LogicalFunctions.java", state.getSourceFiles().toArray()[0]);
		assertEquals("Mutating *,+,-,/ to *,+,-,/ in files LogicalFunctions.java, MathFunctions.java with tests LogicalFunctionsTest.java, MathFunctionsTest.java\r\n"
				+ "MathFunctions.java line 14: Mutant survived when mutating * to +\r\n"
				+ "MathFunctions.java line 18: Mutant survived when mutating / to *\r\n"
				+ "MathFunctions.java line 18: Mutant survived when mutating / to +\r\n"
				+ "MathFunctions.java line 18: Mutant survived when mutating / to -\r\n"
				+ "done\r\n", outputString);
	}
	
	@Test
	public void mutateStrain() {
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
		assertEquals("Mutating *,+,-,/ to *,+,-,/ in files LogicalFunctions.java, MathFunctions.java with tests LogicalFunctionsTest.java, MathFunctionsTest.java\r\n"
				+ "MathFunctions.java line 14: Mutant survived when mutating * to +\r\n"
				+ "MathFunctions.java line 18: Mutant survived when mutating / to *\r\n"
				+ "MathFunctions.java line 18: Mutant survived when mutating / to +\r\n"
				+ "MathFunctions.java line 18: Mutant survived when mutating / to -\r\n"
				+ "done\r\n", outputString);
	}
	
	@Test
	public void mutateStrainMultipleLines() {
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
		assertEquals("Mutating *,+,-,/ to *,+,-,/ in files LogicalFunctions.java, MathFunctions.java with tests LogicalFunctionsTest.java, MathFunctionsTest.java\r\n"
				+ "MathFunctions.java line 14: Mutant survived when mutating * to +\r\n"
				+ "MathFunctions.java line 18: Mutant survived when mutating / to *\r\n"
				+ "MathFunctions.java line 18: Mutant survived when mutating / to +\r\n"
				+ "MathFunctions.java line 18: Mutant survived when mutating / to -\r\n"
				+ "done\r\n"
				+ "Mutating &&,|| to ||,&& in files LogicalFunctions.java, MathFunctions.java with tests LogicalFunctionsTest.java, MathFunctionsTest.java\r\n"
				+ "done\r\n", outputString);
	}
	
	@Test
	public void mutateMultipleStrains() {
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
		assertEquals("Mutating *,+,-,/ to *,+,-,/ in files LogicalFunctions.java, MathFunctions.java with tests LogicalFunctionsTest.java, MathFunctionsTest.java\r\n"
				+ "MathFunctions.java line 14: Mutant survived when mutating * to +\r\n"
				+ "MathFunctions.java line 18: Mutant survived when mutating / to *\r\n"
				+ "MathFunctions.java line 18: Mutant survived when mutating / to +\r\n"
				+ "MathFunctions.java line 18: Mutant survived when mutating / to -\r\n"
				+ "done\r\n"
				+ "Mutating &&,|| to ||,&& in files LogicalFunctions.java, MathFunctions.java with tests LogicalFunctionsTest.java, MathFunctionsTest.java\r\n"
				+ "done\r\n", outputString);
	}
	
	@Test
	public void useStrainInOtherFile() {
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
		assertEquals("Mutating *,+,-,/ to *,+,-,/ in files LogicalFunctions.java, MathFunctions.java with tests LogicalFunctionsTest.java, MathFunctionsTest.java\r\n"
				+ "MathFunctions.java line 14: Mutant survived when mutating * to +\r\n"
				+ "MathFunctions.java line 18: Mutant survived when mutating / to *\r\n"
				+ "MathFunctions.java line 18: Mutant survived when mutating / to +\r\n"
				+ "MathFunctions.java line 18: Mutant survived when mutating / to -\r\n"
				+ "done\r\n", outputString);
	}
	
	@Test
	public void redefineStrain() {
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
				+ "Mutating *,+,-,/ to *,+,-,/ in files LogicalFunctions.java, MathFunctions.java with tests LogicalFunctionsTest.java, MathFunctionsTest.java\r\n"
				+ "MathFunctions.java line 14: Mutant survived when mutating * to +\r\n"
				+ "MathFunctions.java line 18: Mutant survived when mutating / to *\r\n"
				+ "MathFunctions.java line 18: Mutant survived when mutating / to +\r\n"
				+ "MathFunctions.java line 18: Mutant survived when mutating / to -\r\n"
				+ "done\r\n"
				+ "Mutating &&,|| to ||,&& in files LogicalFunctions.java, MathFunctions.java with tests LogicalFunctionsTest.java, MathFunctionsTest.java\r\n"
				+ "done\r\n", outputString);
	}
	
	@Test
	public void useRegexWhenDefiningSource() {
		execute("source: sampleMutationFiles/src\\*.java "
				+ "test: sampleMutationFiles\\test/*.java "
				+ "use sampleMutationFiles/*.mut ");
		assertEquals(2, state.getSourceFiles().size());
		assertEquals(2, state.getTestFiles().size());
		assertEquals("sampleMutationFiles/test/MathFunctionsTest.java", state.getTestFiles().toArray()[1]);
		assertEquals("sampleMutationFiles/test/LogicalFunctionsTest.java", state.getTestFiles().toArray()[0]);
		assertEquals("sampleMutationFiles/src/MathFunctions.java", state.getSourceFiles().toArray()[1]);
		assertEquals("sampleMutationFiles/src/LogicalFunctions.java", state.getSourceFiles().toArray()[0]);
		assertEquals("sampleMutationFiles/math.mut", state.getUseFiles().toArray()[0]);
	}
	
	@Test
	public void useStarForMultipleDirectoriesDefiningSource() {
		execute("source: sampleMutationFiles/src\\*.java "
				+ "test: sampleMutationFiles*Test.java "
				+ "use sampleMutationFiles/*.mut ");
		assertEquals(2, state.getSourceFiles().size());
		assertEquals(2, state.getUseFiles().size());
		assertEquals(2, state.getTestFiles().size());
		assertEquals("sampleMutationFiles/test/MathFunctionsTest.java", state.getTestFiles().toArray()[1]);
		assertEquals("sampleMutationFiles/test/LogicalFunctionsTest.java", state.getTestFiles().toArray()[0]);
		assertEquals("sampleMutationFiles/src/MathFunctions.java", state.getSourceFiles().toArray()[1]);
		assertEquals("sampleMutationFiles/src/LogicalFunctions.java", state.getSourceFiles().toArray()[0]);
		assertEquals("sampleMutationFiles/math.mut", state.getUseFiles().toArray()[0]);
	}
	
	@Test
	public void useMultipleStars() {
		execute("source: src/*lexparse/*.tok* ");
		assertEquals(2, state.getSourceFiles().size());
		assertTrue(state.getSourceFiles().contains("src/mut/lexparse/Mutator.tokens"));
		assertTrue(state.getSourceFiles().contains("src/mut/lexparse/MutatorLexer.tokens"));
	}
	
	@Test
	public void mutateMutatorTestProject() {
		execute("source: ../Mutator*/src/*.java test: ../Mutator*/test/*.java use *.mut mutate math report last");
		System.out.println(outputString);
	}
}
