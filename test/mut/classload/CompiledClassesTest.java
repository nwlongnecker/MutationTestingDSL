package mut.classload;

import static org.junit.Assert.*;

import java.net.URISyntaxException;

import mut.files.CompiledCode;

import org.junit.Test;

public class CompiledClassesTest {

	@Test
	public void putAndGetCode() throws URISyntaxException {
		CompiledClasses cc = new CompiledClasses();
		assertNull(cc.getCode("Name"));
		CompiledCode compCode = new CompiledCode("Name");
		cc.addCode(compCode);
		assertEquals(compCode, cc.getCode("Name"));
	}
}
