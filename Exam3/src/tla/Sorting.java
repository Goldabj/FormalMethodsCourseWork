package tla;

import java.util.Arrays;

public class Sorting {
	// Sorts the given non-empty array of integers.
	public static void sort(int[] array) {
		for (int j = 0; j < array.length - 1; j++) {
		    int minIndex = j;
		    for (int i = j + 1; i <= array.length - 1; i++) {
		        if (array[i] < array[minIndex])
		            minIndex = i;
		    }

		    if(minIndex != j) {
		    	int temp = array[j];
		    	array[j] = array[minIndex];
		    	array[minIndex] = temp;
		    }
		}
	}
	
	public static void main(String[] args) {
		//int[] array = {5,1,4,2,6,3,7};
		int[] array = {-3, -2, -3};
		System.out.println("Before Sorting: " + Arrays.toString(array));
		Sorting.sort(array);
		System.out.println("After Sorting: " + Arrays.toString(array));
	}
}
