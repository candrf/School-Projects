/*
 * File Name: Cube.java
 * Date: 3/14/2024
 * Author: Andrew France
 * Purpose: Project 1 from CMSC 335
 * A Cube is a ThreeDimensionalShape and has an edge. The constructor initializes edge to 0 by default. The value of
 * edge is used to calculate the volume. The member functions set the edge from user input and overrides the 
 * calculateVolume() function to get the total volume.  
 */
package project1;

public class Cube extends ThreeDimensionalShape{
	double edge;
	
	Cube(){
		edge = 0;
	}
	
	// Get user input for edge
	void setEdge(){
		System.out.println("Enter the size of the edges: ");
		edge = getInput();
	}
	
	// Volume of a cube: V = a^3
	@Override
	void calculateVolume() {
		volume = edge*edge*edge; 
	}
}
