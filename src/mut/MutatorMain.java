/**
 * 
 */
package mut;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

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
	 * Runs the mutator in the mode specified by the arguments
	 * @param args Arguments. Use -verbose to change the verbosity settings,
	 * any other arguments should be script names. If there are no file names, runs in repl mode.
	 */
	public static void main(String[] args) {
		Msg msg = new Msg();
		List<String> arguments = Arrays.asList(args);
		int verbosityIndex = -1;
		if (arguments.contains("-verbosity")) {
			verbosityIndex = arguments.indexOf("-verbosity");
			msg.setVerbosity(arguments.get(verbosityIndex + 1));
			msg.msgln("Set verbosity to " + msg.verbosity);
		}
		if(args.length == 0 || (verbosityIndex != -1 && args.length <= 2)) {
			msg.msgln("Welcome to the Mutator DSL!");
			repl(System.in, System.out, msg);
		} else {
			for (int i = 0; i < args.length; i++) {
				if (verbosityIndex == -1 || (i != verbosityIndex && i != verbosityIndex + 1)) {
					executeScript(FileReader.readFile(args[i], msg), new InterpreterState(msg));
				}
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
