/**
 * 
 */
package mut;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

import mut.interpreter.InterpreterState;
import mut.interpreter.MutatorInterpreter;
import mut.lexparse.MutatorLexerException;
import mut.lexparse.MutatorParser;
import mut.lexparse.MutatorParserException;
import mut.runtime.MutatorRuntime;
import mut.utility.MutatorFactory;
import mut.utility.Utility;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.ParserRuleContext;

/**
 * Runs the mutator DSL
 * @author Nathan
 */
public class MutatorMain {

	/**
	 * @param args Arguments
	 */
	public static void main(String[] args) {
		if(args.length == 0) {
			System.out.println("Welcome to the Mutator DSL!");
			repl(System.in, System.out);
		} else {
			for (String filename : args) {
				executeScript(Utility.readFile(filename));
			}
		}
	}
	
	public static void repl(InputStream in, PrintStream out) {
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		MutatorRuntime.setOut(out);
		InterpreterState state = new InterpreterState();
		String command = null;
		MutatorParser parser = null;
		ParserRuleContext tree = null;
		MutatorInterpreter interpreter = new MutatorInterpreter(state);
		while (true) {
			// prompt the user for a command
			out.print("> ");
			// Read the command from standard in
			try {
				command = br.readLine().trim();
			} catch (IOException ioe) {
				ioe.printStackTrace();
				break;
			}
			// If the user wants to exit, exit
			if (command.equals("quit") || command.equals("q") || command.equals("exit")) {
				break;
			} else if (command.isEmpty()) {
				continue;
			}
			// Otherwise, attempt to parse the command
			parser = MutatorFactory.makeParser(new ANTLRInputStream(command));
			try {
				tree = parser.command();
				tree.accept(interpreter);
			} catch (Exception e) {
				System.err.flush();
				if(!out.equals(System.out)) {
					out.println(e.getMessage());
				}
			}
		}
	}
	
	public static InterpreterState executeScript(String mutatorCode) {
		MutatorParser parser = MutatorFactory.makeParser(new ANTLRInputStream(mutatorCode));
		ParserRuleContext tree = parser.mutFile();
		InterpreterState state = new InterpreterState();
		tree.accept(new MutatorInterpreter(state));
		return state;
	}
}
