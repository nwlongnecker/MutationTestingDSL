package mut.classload;

import java.util.HashMap;
import java.util.Map;

import mut.files.CompiledCode;

/**
 * A class for storing compiled bytecode (class files) in memory.
 * @author Nathan Longnecker
 */
public class CompiledClasses {

	private Map<String, CompiledCode> compiledClasses = new HashMap<>();

    /**
     * Adds the compiled code to memory. Overwrites the previous code with that name
     * @param cc The compiled class to add
     */
    public void addCode(CompiledCode cc) {
        compiledClasses.put(cc.getName(), cc);
    }
    
    /**
     * Gets compiled code from memory
     * @param name The name of the class to retrieve
     * @return The compiled code
     */
    public CompiledCode getCode(String name) {
    	return compiledClasses.get(name);
    }
}
