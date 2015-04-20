package mut.statistics;

public class Survivor {

	private final String line;
	private final String from;
	private final String to;
	
	public Survivor(String line, String from, String to) {
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
