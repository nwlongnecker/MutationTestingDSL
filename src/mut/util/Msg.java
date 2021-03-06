package mut.util;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * A class for controlling the output of the program. All print statements go through this class
 * @author Nathan Longnecker
 */
public class Msg {

	private PrintStream out;
	private PrintStream err;

	public static final int VERY_VERBOSE = 5;
	public static final int VERBOSE = 4;
	public static final int NORMAL = 3;
	public static final int DEFAULT = 2;
	public static final int SPARSE = 1;
	
	public int verbosity;
	
	/**
	 * Constructs a new messager with default verbosity
	 */
	public Msg() {
		verbosity = DEFAULT;
	}

	/**
	 * @param out the out to set
	 */
	public void setOut(PrintStream out) {
		this.out = out;
	}

	/**
	 * @param err the err to set
	 */
	public void setErr(PrintStream err) {
		this.err = err;
	}
	
	/**
	 * Prints a message to the output with a line terminator
	 * @param message The message to print
	 */
	public void msgln(String message) {
		if(out == null) out = System.out;
		out.println(message);
	}
	
	/**
	 * Prints a message to the output with no line terminator
	 * @param message The message to print
	 */
	public void msg(String message) {
		if(out == null) out = System.out;
		out.print(message);
	}
	
	/**
	 * Prints an error message line to the err
	 * @param message The message to print
	 */
	public void err(String message) {
		if(err == null) err = System.err;
		err.println(message);
	}

	/**
	 * Prints a comma separated list of strings prepended by a label
	 * @param label The label to prepend to the output
	 * @param list The list of strings to print
	 */
	public void printList(String label, Collection<String> list) {
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

	/**
	 * Sets the verbosity of this message object
	 * @param verbosity The verbosity setting
	 */
	public void setVerbosity(String verbosity) {
		if (verbosity.equals("veryverbose")) {
			this.verbosity = VERY_VERBOSE;
		} else if (verbosity.equals("verbose")) {
			this.verbosity = VERBOSE;
		} else if (verbosity.equals("normal")) {
			this.verbosity = NORMAL;
		} else if (verbosity.equals("default")) {
			this.verbosity = DEFAULT;
		} else if (verbosity.equals("sparse")) {
			this.verbosity = SPARSE;
		} else {
			this.verbosity = Integer.parseInt(verbosity);
		}
	}
}
