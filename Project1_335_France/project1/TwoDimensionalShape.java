/*
 * File Name: TwoDimensionalShape.java
 * Date: 3/14/2024
 * Author: Andrew France
 * Purpose: Project 1 from CMSC 335
 * A TwoDimensionalShape is a Shape and has an area. The constructor initializes number of dimensions to 2
 * and the area to 0 by default. It has two member functions, calculateArea() and displayArea() that 
 * will be used by all 2 dimensional shapes. 
 */
package project1;

public class TwoDimensionalShape extends Shape{
	double area;
	
	TwoDimensionalShape(){
		numberOfDimensions = 2;
		area = 0;
	}
	
	// This method will be implemented as a polymorphic function by each subclass,
	// each subclass will override this function to calculate the area based on the
	// specific shape's formula.
	void calculateArea() {
		
	}
	
	// This method is used by every 2-D subclass to display its area
	void displayArea() {
		System.out.println("The area is: "+ area);
	}
}
