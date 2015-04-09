package mut.runtime;

import java.io.PrintStream;
import java.util.Collection;

import org.junit.runner.Result;

public class MutatorRuntime {
	
	private static PrintStream out;

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

	public static void reportResults(Result result) {
		if(out == null) out = System.out;
		if (!result.wasSuccessful()) {
			out.println("Mutant killed!");
		} else {
			out.println("Mutant survived");
		}
	}
}
