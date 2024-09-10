/*
 * Andrew France
 * CMSC 451 6980
 * Professor Candice Adams
 * Project 1
 * 7/7/2024
 * 
 * This class is the main driver of project 1. It contains the main method, as well as methods
 * to generate the data sets, warm up the JVM, and benchmark the sorting algorithms. It will throw an
 * exception if the algorithm is not sorted.   
 */

import java.io.*;
import java.util.*;
import java.util.Random;

public class BenchmarkSorts {
	
	private static final int[] dataSetSizes = {100, 200, 300, 400, 500, 600, 700, 800, 900, 1000, 1100, 1200};
	private static final int numRuns = 40;
	private static final int warmupNum = 20; 
   

	public static void main(String[] args) {
		try {
	        // Initialize output file writers
			BufferedWriter bubbleSortWriter = new BufferedWriter(new FileWriter("bubble_sort_results.txt"));
			BufferedWriter quickSortWriter = new BufferedWriter(new FileWriter("quick_sort_results.txt"));
			
			// Iterating over each data set size
			for (int size : dataSetSizes) {
			    // Generate 40 data sets for the current size
				List<int[]> dataSets = generateDataSets(size);

				// Warm up Bubble Sort
				warmUp(new BubbleSort(), dataSets, size);
				
				// Benchmark Bubble Sort
				benchmarkAlgorithm(new BubbleSort(), dataSets, size, bubbleSortWriter);
				
				// Warm up Quick Sort
				warmUp(new QuickSort(), dataSets, size);
				
				// Benchmark Quick Sort
				benchmarkAlgorithm(new QuickSort(), dataSets, size, quickSortWriter);
			}
			
			// Close file writers
			bubbleSortWriter.close();
			quickSortWriter.close();
			
		} catch (IOException e) {
			e.printStackTrace();
			}
	}
		
	private static void warmUp(AbstractSort algorithm, List<int[]> dataSets, int size) {
	    // Warm up the given sorting algorithm 20 times on the dataSets
		for (int i=0; i < warmupNum; i++) {
			for (int[] dataSet : dataSets) {
				// Make a copy of the data set
				int[]dataSetCopy = Arrays.copyOf(dataSet, dataSet.length);
				// Sort data set
				algorithm.sort(dataSetCopy);
			}
		}
	}
	
	private static List<int[]> generateDataSets(int size) {
	    // Generate 40 random data sets
		List<int[]> dataSets = new ArrayList<>();
		Random random = new Random();
	
		for (int i = 0; i < numRuns; i++) {
			int[] dataSet = new int[size];
			for (int j = 0; j < size; j++) {
				dataSet[j] = random.nextInt();
	        }
	        dataSets.add(dataSet);
	    }
		return dataSets;
	}
	
	private static void benchmarkAlgorithm(AbstractSort algorithm, List<int[]> dataSets, 
			int size, BufferedWriter writer) throws IOException {
		// Benchmark the given sorting algorithm on the dataSets
		// Start file with size of data sets
		writer.write(size + " ");
		for (int[] dataSet : dataSets) {
			// Make copy of data set
			int[]dataSetCopy = Arrays.copyOf(dataSet, dataSet.length);
			
			// Sort data set
			algorithm.sort(dataSetCopy);
	
			// Verify sorted correctness
			verifySorted(dataSetCopy);
			// Write results to file
			writer.write(algorithm.getCount() + " " + algorithm.getTime() + " ");
		}
		writer.write("\n");
	}
	
	private static void verifySorted(int[] arr) {
	    // Verify that array is sorted, throw exception if not
		for (int i = 1; i < arr.length; i++) {
			if (arr[i] < arr[i - 1]) {
				throw new RuntimeException("Array is not sorted!");
			}
		}
	}
}
