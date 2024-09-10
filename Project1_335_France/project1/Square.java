/*
 * File Name: Square.java
 * Date: 3/14/2024
 * Author: Andrew France
 * Purpose: Project 1 from CMSC 335
 * A Square is a TwoDimensionalShape and has a side. The constructor initializes side to 0 by default. The value of
 * side is used to calculate the area. The member functions set the length of the side from user input and 
 * overrides the calculateArea() function to get the total area.  
 */
package project1;

public class Square extends TwoDimensionalShape {
	double side;

	Square(){
		side = 0;
	}
	
	// Gets user input for value of side
	void setSide() {
		System.out.println("Enter the size of the sides: ");
		side = getInput();
	}
	
	// Area of a square: A = a^2
	@Override
	void calculateArea() {
		area = side * side;
	}
}
