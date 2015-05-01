package mut.statistics;

/**
 * A class encapsulating information about a mutation. Use for statistics reporting
 * @author Nathan Longnecker
 */
public class Mutation {

	private final String line;
	private final String from;
	private final String to;
	
	/**
	 * Constructs a new Mutation object
	 * @param line The line number of the mutation
	 * @param from The symbol being mutated from
	 * @param to The symbol being mutated to
	 */
	public Mutation(String line, String from, String to) {
		this.line = line;
		this.from = from;
		this.to = to;
	}

	/**
	 * @return the lineNumber
	 */
	public String getLine() {
		return line;
	}

	/**
	 * @return the from
	 */
	public String getFrom() {
		return from;
	}

	/**
	 * @return the to
	 */
	public String getTo() {
		return to;
	}
	
}
