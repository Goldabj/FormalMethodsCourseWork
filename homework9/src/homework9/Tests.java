package homework9;

import static org.junit.Assert.*;

import org.junit.Test;

public class Tests {

	@Test
	public void test() {
		int[] array = new int[]{1, 5, 2, 6, 0};
		Algorithms.sort(array);
		System.out.print(array.toString());
	}

}
