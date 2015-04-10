package mut.runtime;

import java.io.PrintStream;
import java.util.Collection;

import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class MutatorRuntime {

	private static PrintStream out;
	private static PrintStream err;
	
	private static boolean verbose = true;

	/**
	 * @param out the out to set
	 */
	public static void setOut(PrintStream out) {
		MutatorRuntime.out = out;
	}

	public static void printList(String title, Collection<String> items) {
		if(out == null) out = System.out;
		out.print(title);
		boolean first = true;
		for(String item : items) {
			if (first) {
				first = false;
			} else {
				out.print(", ");
			}
			out.print(item);
		}
		out.println();
	}
	
	public static void printMessage(String message) {
		if(out == null) out = System.out;
		out.println(message);
	}
	
	public static void print(String message) {
		if(out == null) out = System.out;
		out.print(message);
	}
	
	public static void printError(String message) {
		if(err == null) err = System.err;
		err.println(message);
	}

	public static void reportResults(Result result) {
		if(out == null) out = System.out;
		if(verbose) {
			for(Failure fail : result.getFailures()) {
				out.println(fail.getMessage());
			}
			if (!result.wasSuccessful()) {
				out.println("Mutant killed!");
			} else {
				out.println("Mutant survived");
			}
		} else {
			if (!result.wasSuccessful()) {
				out.println("Mutant killed!");
			} else {
				out.println("Mutant survived");
			}
		}
	}
}
