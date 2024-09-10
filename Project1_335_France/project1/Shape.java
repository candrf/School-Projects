/*
 * File Name: Shape.java
 * Date: 3/14/2024
 * Author: Andrew France
 * Purpose: Project 1 from CMSC 335
 * This is the main parent class for all shapes in the project. Each different of shape will inherit from Shape.
 * A Shape is an Object and has a numberOfDimensions. Constructor initializes that number to 0. Additionally, 
 * method getInput() is used by all subclasses to take in user input for shape dimensions.  
 */
package project1;

import java.util.Scanner;

public class Shape {
	int numberOfDimensions;
	
	Shape(){
		numberOfDimensions = 0;
	}
	
	// Method gets and verifies user input for all subclasses of Shape, returns value of input as double
	double getInput() {
		Scanner in = new Scanner(System.in);
        while (true) {
			try {
  				double input = in.nextDouble();
				return input;
				
			} catch (java.util.InputMismatchException e) {
				System.out.println("Invalid input. Please enter a number.");
				in.next(); // Consume the invalid input
				continue; // Continue to the next iteration of the loop
			}
        }
	}
}