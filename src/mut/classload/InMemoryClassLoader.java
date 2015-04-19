package mut.classload;

import mut.files.CompiledCode;
import mut.util.Msg;

public class InMemoryClassLoader extends ClassLoader {
	
	private CompiledClasses compiledClasses;

	public InMemoryClassLoader(ClassLoader parent, CompiledClasses compiledClasses) {
		super(parent);
		this.compiledClasses = compiledClasses;
	}

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
    	if (Msg.verbose) {
    		Msg.msgln("Looking for loaded class " + name);
    	}
        CompiledCode cc = compiledClasses.getCode(name);
        if (cc == null) {
            return super.findClass(name);
        }
        byte[] byteCode = cc.getByteCode();
        return defineClass(name, byteCode, 0, byteCode.length);
    }
}
