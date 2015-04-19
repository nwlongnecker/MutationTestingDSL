package mut.files;

import java.net.URI;

import javax.tools.SimpleJavaFileObject;

public class InMemoryFileObject extends SimpleJavaFileObject {
	
	private byte[] contents;

	protected InMemoryFileObject(URI uri, Kind kind, byte[] contents) {
		super(uri, kind);
		this.contents = contents;
	}
	
	@Override
	public CharSequence getCharContent(boolean ignoreEncodingErrors) {
		return new String(contents);
	}

}
