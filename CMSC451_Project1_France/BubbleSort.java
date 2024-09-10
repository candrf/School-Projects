/*
 * Andrew France
 * CMSC 451 6980
 * Professor Candice Adams
 * Project 1
 * 7/7/2024
 * 
 * This class contains the bubble sort algorithms, it inherits from AbstractSort.
 * The bubble sort was taken from geeksforgeeks website. The critical operation 
 * being counted is the number of comparisons performed.  
 */

public class BubbleSort extends AbstractSort {
    @Override
    public void sort(int[] array) {
        startSort();	// Start time
        int n = array.length;
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
        	swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                incrementCount(); // Critical operation: comparisons
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    swapped = true;
                }
            }
            // If no elements swapped by inner loop, break
            if (swapped == false)
            	break;
        }
        endSort();		// End time
    }
}
