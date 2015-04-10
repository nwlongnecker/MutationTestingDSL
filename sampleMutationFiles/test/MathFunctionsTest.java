package test;

import static org.junit.Assert.*;

import org.junit.Test;

import src.MathFunctions;

public class MathFunctionsTest {

	@Test
	public void testAdd() {
		assertEquals(3, MathFunctions.add(1, 2));
	}
	
	@Test
	public void testSubtract() {
		assertEquals(3, MathFunctions.subtract(7, 4));
	}
	
	@Test
	public void testAddThenSubtract() {
		assertEquals(-8, MathFunctions.addThenSubtract(7, 4, 19));
	}
	
	@Test
	public void testMultiply() {
		assertEquals(4, MathFunctions.multiply(2, 2));
	}
}
