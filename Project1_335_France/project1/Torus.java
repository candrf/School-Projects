/*
 * File Name: Torus.java
 * Date: 3/14/2024
 * Author: Andrew France
 * Purpose: Project 1 from CMSC 335
 * A Torus is a ThreeDimensionalShape and has a major and minor radius. The constructor initializes them to 0 by default. 
 * The value of these are used to calculate the volume. The member functions set the values from user input and overrides the 
 * calculateVolume() function to get the total volume.  
 */
package project1;

public class Torus extends ThreeDimensionalShape{
	double majorRadius;
	double minorRadius;
	
	Torus(){
		majorRadius = 0;
		minorRadius = 0;
	}
	
	// Get user input for major radius
	void setMajorRadius(){
		System.out.println("Enter the major radius: ");
		majorRadius = getInput();
	}
	
	// Get user input for minor radius
	void setMinorRadius(){
		System.out.println("Enter the minor radius: ");
		minorRadius = getInput();
	}
	
	// Volume of a torus: V = (pi*r^2)(2pi*R)
	@Override
	void calculateVolume() {
		volume = (Math.PI*minorRadius*minorRadius)*(2*Math.PI*majorRadius); 
	}
}
