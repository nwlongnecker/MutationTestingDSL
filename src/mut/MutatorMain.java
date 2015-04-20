/**
 * 
 */
package mut;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;

import mut.files.FileReader;
import mut.interpreter.InterpreterState;
import mut.interpreter.MutatorInterpreter;
import mut.lexparse.LexerParserFactory;
import mut.lexparse.MutatorParser;
import mut.util.Msg;

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
		Msg msg = new Msg();
		if(args.length == 0) {
			System.out.println("Welcome to the Mutator DSL!");
			repl(System.in, System.out, msg);
		} else {
			for (String filename : args) {
				executeScript(FileReader.readFile(filename, msg), new InterpreterState(msg));
			}
		}
	}
	
	/**
	 * Creates a read evaluate print loop for use on the command line
	 * @param in The stream to use for input
	 * @param out The stream to use for output
	 */
	public static void repl(InputStream in, PrintStream out, Msg msg) {
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		msg.setOut(out);
		InterpreterState state = new InterpreterState(msg);
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
			if (command.equalsIgnoreCase("quit") || command.equalsIgnoreCase("q") || command.equalsIgnoreCase("exit")) {
				break;
			} else if (command.isEmpty()) {
				continue;
			}
			// Otherwise, attempt to parse the command
			parser = LexerParserFactory.makeParser(new ANTLRInputStream(command));
			try {
				tree = parser.command();
				tree.accept(interpreter);
			} catch (Exception e) {
				System.err.flush();
				System.out.println(e.getMessage());
				if(!out.equals(System.out)) {
					out.println(e.getMessage());
				}
			}
		}
	}
	
	/**
	 * Runs a mutator script
	 * @param mutatorCode The code to run
	 * @param initialState the initial state when starting this program
	 * @return Returns the state of the interpreter after execution
	 */
	public static InterpreterState executeScript(String mutatorCode, InterpreterState initialState) {
		MutatorParser parser = LexerParserFactory.makeParser(new ANTLRInputStream(mutatorCode));
		ParserRuleContext tree = parser.mutFile();
		tree.accept(new MutatorInterpreter(initialState));
		return initialState;
	}
}
