package mut.util;

import java.io.PrintStream;
import java.util.Collection;

import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class Msg {

	private static PrintStream out;
	private static PrintStream err;
	
	public static boolean verbose = true;

	/**
	 * @param out the out to set
	 */
	public static void setOut(PrintStream out) {
		Msg.out = out;
	}
	
	public static void msgln(String message) {
		if(out == null) out = System.out;
		out.println(message);
	}
	
	public static void msg(String message) {
		if(out == null) out = System.out;
		out.print(message);
	}
	
	public static void err(String message) {
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

	public static void printList(String label, Collection<String> list) {
		if(out == null) out = System.out;
		StringBuilder sb = new StringBuilder();
		sb.append(label);
		for (String element : list) {
			sb.append(element);
			sb.append(", ");
		}
		if (!list.isEmpty()) {
			out.println(sb.toString().substring(0, sb.length()-2));
		} else {
			out.println(sb.toString());
		}
	}
}
