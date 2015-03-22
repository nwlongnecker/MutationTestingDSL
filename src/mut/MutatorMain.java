/**
 * 
 */
package mut;

import java.io.*;

import mut.interpreter.InterpreterState;
import mut.interpreter.MutatorInterpreter;
import mut.lexparse.MutatorLexerException;
import mut.lexparse.MutatorParser;
import mut.lexparse.MutatorParserException;
import mut.utility.MutatorFactory;

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
		System.out.println("Welcome to the Mutator DSL!");

		repl(System.in);
		
	}
	
	public static void repl(InputStream in) {
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		InterpreterState state = new InterpreterState();
		String command = null;
		MutatorParser parser = null;
		ParserRuleContext tree = null;
		MutatorInterpreter interpreter = new MutatorInterpreter(state);
		while (true) {
			// prompt the user for a command
			System.out.print(">");
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
				continue;
			}
		}
	}

}
