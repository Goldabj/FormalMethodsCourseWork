package homework9;

public class Algorithms {
	
	// Pre-Condition: Positive integer n
	// Post-Condition: Returns factorial of n
	public static int factorial(int n) {
		int fact = 1;
		for(int i = 1; i < n; ++i) {
			fact *= i;
		}
		return fact;
	}
	
	// Pre-Condition: Three integers as parameters
	// Post-Condition: The function returns the minimum of the three params.
	public static int min(int a, int b, int c) {
		int min = a;
		if(b < min)
			min = b;
		if(c<min)
			min = c;
		return min;
	}
	
	// Pre-Condition: Non-null, non-empty integer arrays
	// Post-Condition: Return the maximum element in the array
	public static int max(int[] array) {
		int max = array[0];
		for(int i = 1; i < array.length; ++i) {
			if(array[i] < max) 
				max = array[i];
		}
		return max;
	}
	
	// Pre-Condition: n is natural number
	// Post-Condition: Returns the sum of all integers from 0 through n. 
	public static int sum(int n) {
		int sum = 0;
		for(int i = 0; i < n; ++i) {
			sum += i;
		}
		return sum;
	}
	
	// Pre-Condition: data is an non-empty integer array
	// Post-Condition: Returns true if key is found in the array otherwise returns false 
	public boolean search(int key, int[] data) {
		int low = 0;
		int high = data.length - 1;
	
		while(high >= low) {
			int middle = (low + high) / 2;
			if(data[middle] == key) {
				return true;
			}
			if(	data[middle] < key) {
				low = middle + 1;
			}
			if(data[middle] > key) {
				high = middle - 1;
			}
		}
		return false;
	}
	

	// Pre-Condition: data is an non-empty integer array
	// Post-Condition: array is sorted 
	public static void sort(int array[]) {
        int n = array.length;
        int k;
        for (int m = n; m >= 0; m--) {
            for (int i = 0; i < n - 1; i++) {
                k = i + 1;
                if (array[i] > array[k]) {
                	int temp = array[i];
                	array[i] = array[k];
                	array[k] = temp;
                }
            }
        }
    }
}

