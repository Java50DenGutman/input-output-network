package telran.numbers.tests;

import static org.junit.jupiter.api.Assertions.*;
import static telran.numbers.Calculator.*;

import org.junit.jupiter.api.Test;

class CalculatorTest {

	@Test
	void testSum() {
		assertEquals(4, sum(2,2));
		assertEquals(0, sum(2,-2));
		assertEquals(-4, sum(-2,-2));
	}

	@Test
	void testSubtract() {
		assertEquals(0, subtract(2,2));
		assertEquals(4, subtract(2,-2));
		assertEquals(-2, subtract(2,4));
		assertEquals(2, subtract(4,2));
		assertEquals(-2, subtract(-4,-2));
		assertEquals(2, subtract(-2,-4));
	}

	@Test
	void testMultiply() {
		assertEquals(4, multiply(2,2));
		assertEquals(-4, multiply(2,-2));
		assertEquals(4, multiply(-2,-2));
		assertEquals(0, multiply(1000,0));
		assertEquals(0, multiply(0,-1000));
	}

	@Test
	void testDivide() {
		assertEquals(0, divide(2,4));
		assertEquals(2, divide(4,2));
		assertEquals(-2, divide(-4,2));
		assertEquals(2, divide(-4,-2));
	}

	@Test
	void testRemainder() {
		assertEquals(2, remainder(2,4));
		assertEquals(0, remainder(4,2));
		assertEquals(-1, remainder(-10,3));
		assertEquals(-1, remainder(-10,-3));
	}

}
