/*
 * Andrew France
 * CMSC 451 6980
 * Professor Candice Adams
 * Project 1
 * 7/7/2024
 * 
 * This class contains the quick sort algorithms, it inherits from AbstractSort.
 * The quick sort was taken from geeksforgeeks website. The critical operation 
 * being counted is the number of comparisons performed.  
 */

public class QuickSort extends AbstractSort {
    @Override
    public void sort(int[] array) {
        startSort();	// Start time
        quickSort(array, 0, array.length - 1);
        endSort();		// End time
    }

    private void quickSort(int[] array, int low, int high) {
        if (low < high) {
            int pi = partition(array, low, high);
            quickSort(array, low, pi - 1);
            quickSort(array, pi + 1, high);
        }
    }

    private int partition(int[] array, int low, int high) {
        int pivot = array[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            incrementCount(); // Critical operation: comparison
            if (array[j] <= pivot) {
                i++;
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
        int temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;
        return i + 1;
    }
}
