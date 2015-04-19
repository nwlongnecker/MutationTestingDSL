package mut.classload;

import java.util.HashMap;
import java.util.Map;

import mut.files.CompiledCode;

public class CompiledClasses {

	private Map<String, CompiledCode> compiledClasses = new HashMap<>();

    public void addCode(CompiledCode cc) {
        compiledClasses.put(cc.getName(), cc);
    }
    
    public CompiledCode getCode(String name) {
    	return compiledClasses.get(name);
    }
}
