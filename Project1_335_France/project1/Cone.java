/*
 * File Name: Cone.java
 * Date: 3/14/2024
 * Author: Andrew France
 * Purpose: Project 1 from CMSC 335
 * A Cone is a ThreeDimensionalShape and has a radius and height. The constructor initializes them to 0 by default. The value of
 * radius and height are used to calculate the volume. The member functions set the values from user input and overrides the 
 * calculateVolume() function to get the total volume.  
 */
package project1;

public class Cone extends ThreeDimensionalShape { 
	double radius;
	double height;
	
	Cone(){
		radius = 0;
		height = 0;
	}
	
	// Get user input for radius
	void setRadius(){
		System.out.println("Enter the radius: ");
		radius = getInput();
	}
	
	// Get user input for height
	void setHeight(){
		System.out.println("Enter the height: ");
		height = getInput();
	}
	
	// Volume of a cone: V = (1/3)*pi*r^2*h
	@Override
	void calculateVolume() {
		volume = (1.0/3.0)*Math.PI*radius*radius*height; 
	}
}
