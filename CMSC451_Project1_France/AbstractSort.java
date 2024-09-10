/*
 * Andrew France
 * CMSC 451 6980
 * Professor Candice Adams
 * Project 1
 * 7/7/2024
 * 
 * This class is an abstract class for the sorting algorithms. It contains
 * the methods to calculate and get the times and counts of the algorithms.  
 */

public abstract class AbstractSort {
	private long startTime;
	private long endTime;
	private int count;
	
	public abstract void sort(int[] array);
	
	protected void startSort() {
		this.count = 0;
		this.startTime = System.nanoTime();
	}
	
	protected void endSort() {
		this.endTime = System.nanoTime();
	} 
	
	protected void incrementCount() {
	    this.count++; 
	} 
	
	public long getCount() {
	    return this.count; 
	} 
	
	public long getTime() {
	    return (this.endTime - this.startTime); 
	}
}

