/*
 * File Name: Rectangle.java
 * Date: 3/14/2024
 * Author: Andrew France
 * Purpose: Project 1 from CMSC 335
 * A Rectangle is a TwoDimensionalShape and has a length and width. The constructor initializes them to 0 by default. 
 * The value of length and width are used to calculate the area. The member functions set the length and width from user 
 * input and overrides the calculateArea() function to get the total area.  
 */
package project1;

public class Rectangle extends TwoDimensionalShape {
	double length;
	double width;
	
	Rectangle(){
		length = 0;
		width = 0;
	}
	
	// Get user input for length
	void setLength() {
		System.out.println("Enter the length: ");
		length = getInput();
	}
	
	// Get user input for width
	void setWidth() {
		System.out.println("Enter the width: ");
		width = getInput();
	}
	
	// Area of a rectangle: A = l*w
	@Override
	void calculateArea() {
		area = length * width;
	}

}
