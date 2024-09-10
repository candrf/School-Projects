/*
 * File Name: Triangle.java
 * Date: 3/14/2024
 * Author: Andrew France
 * Purpose: Project 1 from CMSC 335
 * A Triangle is a TwoDimensionalShape and has a base and height. The constructor initializes them to 0 by default. 
 * The value of base and height are used to calculate the area. The member functions set the base and height from user 
 * input and overrides the calculateArea() function to get the total area. Note: type of triangle is not specified,
 * area formula works for any triangle.
 */
package project1;

public class Triangle extends TwoDimensionalShape {
	double base;
	double height;
	
	Triangle(){
		base = 0;
		height = 0;
	}
	
	// Get user input for base
	void setBase() {
		System.out.println("Enter the base: ");
		base = getInput();
	}
	
	// Get user input for height
	void setHeight() {
		System.out.println("Enter the height: ");
		height = getInput();
	}
	
	// Area of a triangle: A = (b*h)/2
	@Override
	void calculateArea() {
		area = (base * height)/2;
	}
}
