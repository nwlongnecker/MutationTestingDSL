package mut.util;

import java.io.PrintStream;
import java.util.Collection;

public class Msg {

	private PrintStream out;
	private PrintStream err;

	public static final int VERY_VERBOSE = 5;
	public static final int VERBOSE = 4;
	public static final int NORMAL = 3;
	public static final int DEFAULT = 2;
	public static final int SPARSE = 1;
	
	public int verbosity;
	
	public Msg() {
		verbosity = DEFAULT;
	}

	/**
	 * @param out the out to set
	 */
	public void setOut(PrintStream out) {
		this.out = out;
	}

	public void setErr(PrintStream err) {
		this.err = err;
	}
	
	public void msgln(String message) {
		if(out == null) out = System.out;
		out.println(message);
	}
	
	public void msg(String message) {
		if(out == null) out = System.out;
		out.print(message);
	}
	
	public void err(String message) {
		if(err == null) err = System.err;
		err.println(message);
	}

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
}
