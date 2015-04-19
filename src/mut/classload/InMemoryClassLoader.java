package mut.classload;

import mut.files.CompiledCode;

public class InMemoryClassLoader extends ClassLoader {
	
	private CompiledClasses compiledClasses;
	private final boolean verbose = false;

	public InMemoryClassLoader(ClassLoader parent, CompiledClasses compiledClasses) {
		super(parent);
		this.compiledClasses = compiledClasses;
	}

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
    	if (verbose) {
    		System.out.println("Looking for loaded class " + name);
    	}
        CompiledCode cc = compiledClasses.getCode(name);
        if (cc == null) {
            return super.findClass(name);
        }
        byte[] byteCode = cc.getByteCode();
        return defineClass(name, byteCode, 0, byteCode.length);
    }
}
