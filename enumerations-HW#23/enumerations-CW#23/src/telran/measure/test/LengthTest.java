package telran.measure.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import telran.measure.Length;
import telran.measure.LengthUnit;

class LengthTest {
	Length length1 = new Length(1, LengthUnit.KM);
	Length length2 = new Length(500, LengthUnit.M);
	Length length3 = new Length(50000, LengthUnit.CM);

	@Test
	void testEqualsObject() {
		assertEquals(length2, length3);
		assertNotEquals(length1, length2);
	}

	@Test
	void testCompareTo() {
		assertTrue(length2.compareTo(length1) < 0);
		assertTrue(length1.compareTo(length2) > 0);
	}
}