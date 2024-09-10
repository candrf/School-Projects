/*
 * File Name: ThreeDimensionalShape.java
 * Date: 3/14/2024
 * Author: Andrew France
 * Purpose: Project 1 from CMSC 335
 * A ThreeDimensionalShape is a Shape and has a volume. The constructor initializes number of dimensions to 3
 * and the volume to 0 by default. It has two member functions, calculateVolume() and displayVolume() that 
 * will be used by all 3-D shapes. 
 */
package project1;

public class ThreeDimensionalShape extends Shape{
	double volume;
	
	ThreeDimensionalShape(){
		numberOfDimensions = 3;
		volume = 0;
	}
	
	// This method will be implemented as a polymorphic function by each subclass,
	// each subclass will override this function to calculate the volume based on the
	// specific shape's formula.
	void calculateVolume() {
		
	}
	
	// This method is used by all 3-D shapes to display volume
	void displayVolume() {
		System.out.println("The volume is: "+ volume);
	}
}
