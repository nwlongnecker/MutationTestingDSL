package mut.files;

import javax.tools.SimpleJavaFileObject;

import java.io.File;
import java.io.IOException;
import java.net.URI;

/**
 * Created by trung on 5/3/15.
 */
public class SourceCode extends SimpleJavaFileObject {
    private String contents;

    public SourceCode(String className, String contents) {
        super(URI.create("string:///" + new File(className).toPath().getFileName()), Kind.SOURCE);
        this.contents = contents;
    }

    public CharSequence getCharContent(boolean ignoreEncodingErrors) throws IOException {
        return contents;
    }
}
