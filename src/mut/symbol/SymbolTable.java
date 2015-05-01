package mut.symbol;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mut.lexparse.MutatorParser;
import mut.util.Msg;

/**
 * Keeps track of the current strains defined in the state
 * @author Nathan Longnecker
 */
public class SymbolTable {
	
	private final Map<String, List<MutatorParser.MutateContext>> strainSymbols;
	private final Msg msg;

	/**
	 * Constructor
	 * @param msg The messager to write output to
	 */
	public SymbolTable(Msg msg) {
		strainSymbols = new HashMap<String, List<MutatorParser.MutateContext>>();
		this.msg = msg;
	}
	
	/**
	 * Adds a strain to the symbol table. If it is a duplicate, overwrites the previous one
	 * @param symbol The id of the strain
	 * @param strain The contents of the strain
	 */
	public void add(String symbol, List<MutatorParser.MutateContext> strain) {
		if (strainSymbols.containsKey(symbol)) {
			msg.msgln("Redefined strain " + symbol);
		}
		strainSymbols.put(symbol, strain);
	}
	
	/**
	 * Retrieves a strain from the symbol table
	 * @param symbol The id of the strain to retrieve
	 * @return The list of mutations in the strain
	 */
	public List<MutatorParser.MutateContext> get(String symbol) {
		if (strainSymbols.containsKey(symbol)) {
			return strainSymbols.get(symbol);
		} else {
			throw new SymbolException("Strain " + symbol + " is not defined");
		}
	}
}
