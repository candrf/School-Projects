/*
 * File Name: Circle.java
 * Date: 3/14/2024
 * Author: Andrew France
 * Purpose: Project 1 from CMSC 335
 * A Circle is a TwoDimensionalShape and has a radius. The constructor initializes radius to 0 by default. The value of
 * radius is used to calculate the area. The member functions set the radius from user input and overrides the 
 * calculateArea() function to get the total area.  
 */
package project1;

public class Circle extends TwoDimensionalShape {
	double radius;
	
	Circle(){
		radius = 0;
	}
	
	// Get user input for radius
	void setRadius() {
		System.out.println("Enter the radius: ");
		radius = getInput();
	}
	
	// Area of a circle: A = pi*r^2
	@Override
	void calculateArea() {
		area = radius * radius * Math.PI;
	}
}
