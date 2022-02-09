package tla;

import java.util.Arrays;

public class Partition {
	// Partitions the given array such that elements less than the given pivot 
	// are moved to the left and elements greater than the pivot are moved to 
	// the right.
	// Returns the index of the new pivot point. 
	// Note that the array is permuted such that all the array elements on the 
	// left of (and including) the returned pivot index is less than or equal 
	// to all the array elements on the right of the pivot index.
	public static int partition(int[] array, int pivot) {
		int i = 0;
		int j = array.length - 1;
		
		while (i <= j) {
			// Move index markers i,j toward center
			// Until we find a pair of out-of-order elements
			while (i <= j && array[i] < pivot) {
				i++;
			}
			while (i <= j && array[j] > pivot) {
				j--;
			}
			if (i <= j) {
				int temp = array[i];
				array[i] = array[j];
				array[j] = temp;
				i++;
				j--;
			}
		}
		if (i <= array.length -1 && array[i] == pivot) {
			return i;
		}
		return i - 1;
	}
	
	public static void main(String[] args) {
		//int[] array = {8,1,5,7,4,9,4,5,3,6,2,5};
		int[] array = {0, -3, -1};
		int pivot = -2;

		//int pivot = 5;
		System.out.println("Pivot Element: " + pivot);
		System.out.println("Before Partitioning: " + Arrays.toString(array));
		int pivotIndex = partition(array, pivot);
		System.out.println("After Partitioning: " + Arrays.toString(array));
		System.out.println("New Pivot Point: " + pivotIndex);

		System.out.println();
	
		pivot = 4;
		System.out.println("Pivot Element: " + pivot);
		System.out.println("Before Partitioning: " + Arrays.toString(array));
		pivotIndex = partition(array, pivot);
		System.out.println("After Partitioning: " + Arrays.toString(array));
		System.out.println("New Pivot Point: " + pivotIndex);
	}
}
