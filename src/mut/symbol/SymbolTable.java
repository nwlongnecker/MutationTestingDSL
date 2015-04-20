package mut.symbol;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mut.lexparse.MutatorParser;
import mut.util.Msg;

public class SymbolTable {
	
	private Map<String, List<MutatorParser.MutateContext>> strainSymbols;

	public SymbolTable() {
		strainSymbols = new HashMap<String, List<MutatorParser.MutateContext>>();
	}
	
	public void add(String symbol, List<MutatorParser.MutateContext> strain) {
		if (strainSymbols.containsKey(symbol)) {
			Msg.msgln("Redefined strain " + symbol);
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
