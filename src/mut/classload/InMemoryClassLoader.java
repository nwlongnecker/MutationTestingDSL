package mut.classload;

import mut.files.CompiledCode;
import mut.util.Msg;

public class InMemoryClassLoader extends ClassLoader {
	
	private CompiledClasses compiledClasses;
	private Msg msg;

	public InMemoryClassLoader(ClassLoader parent, CompiledClasses compiledClasses, Msg msg) {
		super(parent);
		this.compiledClasses = compiledClasses;
		this.msg = msg;
	}

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
    	if (msg.verbosity >= Msg.VERY_VERBOSE) {
    		msg.msgln("Looking for loaded class " + name);
    	}
        CompiledCode cc = compiledClasses.getCode(name);
        if (cc == null) {
            return super.findClass(name);
        }
        byte[] byteCode = cc.getByteCode();
        return defineClass(name, byteCode, 0, byteCode.length);
    }
}
