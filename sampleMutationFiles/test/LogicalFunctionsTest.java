package test;

import static org.junit.Assert.*;

import org.junit.Test;

import src.LogicalFunctions;

public class LogicalFunctionsTest {

	@Test
	public void testAnd() {
		assertFalse(LogicalFunctions.logicalAnd(false, true));
	}
	
	@Test
	public void testOr() {
		assertTrue(LogicalFunctions.logicalOr(false, true));
	}
}
