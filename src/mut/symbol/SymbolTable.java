package mut.symbol;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mut.lexparse.MutatorParser;
import mut.util.Msg;

public class SymbolTable {
	
	private final Map<String, List<MutatorParser.MutateContext>> strainSymbols;
	private final Msg msg;

	public SymbolTable(Msg msg) {
		strainSymbols = new HashMap<String, List<MutatorParser.MutateContext>>();
		this.msg = msg;
	}
	
	public void add(String symbol, List<MutatorParser.MutateContext> strain) {
		if (strainSymbols.containsKey(symbol)) {
			msg.msgln("Redefined strain " + symbol);
		}
		strainSymbols.put(symbol, strain);
	}
	
	public List<MutatorParser.MutateContext> get(String symbol) {
		if (strainSymbols.containsKey(symbol)) {
			return strainSymbols.get(symbol);
		} else {
			throw new SymbolException("Strain " + symbol + " is not defined");
		}
	}
}
