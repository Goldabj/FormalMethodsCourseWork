package pv;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class Example4 {

	// Verify the correctness of the following min method by creating
	// modules/Example4-Min.ys and checking the code using Yices.

	// You need to supply pre-condition and post-condition here
	// Pre-Condition: a, b, and c are ints
	// Post-Condition: rv <= a and rv <= b and rv <= c
	public static int min(int a, int b, int c) {
		if(a < b && b < c)
			return a;
		if(b < a && a < c)
			return b;
		return c;
	}
	
	
	// Either updated this test case to show it fails 
	// based on the courterexample reported by Yices 
	// or just leave this test case unchanged to mean that 
	// the min method was implemented correctly.
	@Test
	public void testMin() {
		int a = -1;
		int b = 0;
		int c = 0;
		int expected  = -1;
		int actual = min(a, b, c);
		assertEquals(expected, actual);	
	}
}
