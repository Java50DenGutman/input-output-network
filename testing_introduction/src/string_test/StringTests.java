package string_test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class StringTests {

	@Test
	void compareToTest() {
		String str = "Java";
	    String str1 = "CSS";
	    assertEquals(7, str.compareTo("CSS"));
	    assertEquals(-7, str1.compareTo("Java"));
	    assertEquals(0, str.compareTo("Java"));
	}
	
	@Test
	void compareToIgnoreCaseTest() {
		String str = "Java";
		String str1 = "CSS";
		assertTrue(str.compareToIgnoreCase(str1) > 0);
        assertTrue(str1.compareToIgnoreCase(str) < 0);
        assertEquals(0, str.compareToIgnoreCase("java"));
	}
	
	@Test
	void concatTest() {
		String str = "Java";
		assertEquals("JavaCSS", str.concat("CSS"));
	}
	
	@Test
	void startWithTest() {
		String str = "CSS";
        assertTrue(str.startsWith("C"));
	}
	
	@Test
	void endWithTest() {
		String str = "Java";
        assertTrue(str.endsWith("a"));
	}
	
	@Test
	void equalsIgnoreCaseTest() {
		String str = "JAVA";
        assertTrue(str.equalsIgnoreCase("java"));
	}
	
	@Test
	void indexOfTest() {
		String str = "CSS";
	    assertEquals(1, str.indexOf("S"));
	}
	
	@Test
	void lastIndexOfTest() {
		String str = "Java";
	    assertEquals(3, str.lastIndexOf('a'));
	}
	

}
