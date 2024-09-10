/*
 * File Name: Sphere.java
 * Date: 3/14/2024
 * Author: Andrew France
 * Purpose: Project 1 from CMSC 335
 * A Sphere is a ThreeDimensionalShape and has a radius. The constructor initializes radius to 0 by default. The value of
 * radius is used to calculate the volume. The member functions set the radius from user input and overrides the 
 * calculateVolume() function to get the total volume.  
 */
package project1;

public class Sphere extends ThreeDimensionalShape {
	double radius;
	
	Sphere(){
		radius = 0;
	}
	
	// Get user input for radius
	void setRadius(){
		System.out.println("Enter the radius: ");
		radius = getInput();
	}
	
	// Volume of a sphere: V = 4/3*pi*r^3
	@Override
	void calculateVolume() {
		volume = (4.0/3.0)*Math.PI*radius*radius*radius; 
	}
}
