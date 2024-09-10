/*
 * File Name: Main.java
 * Date: 3/14/2024
 * Author: Andrew France
 * Purpose: Project 1 from CMSC 335
 * The Main class contains the main functionality of the project. It handles all of the logic for creating
 * the main menu, I/O of the menu, calling all appropriate methods for each option, and exiting the program. 
 */
package project1;

import java.util.Scanner;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class Main {

	public static void main(String[] args) {
		boolean quitMenu = false;
		// Main menu, keep looping until quitMenu is true
		while(!quitMenu) {
			displayMenu();
            // Switch statement, each case based on option picked from calling menuChoice()
            // Creates object of selected shape and calls class methods to get area/volume 
			switch(menuChoice()) {
			case 1:
				System.out.println("Option 1: Circle");
				Circle circle = new Circle();
				circle.setRadius();
				circle.calculateArea();
				circle.displayArea();
				break;
			case 2:
				System.out.println("Option 2: Rectangle");
				Rectangle rectangle = new Rectangle();
				rectangle.setLength();
				rectangle.setWidth();
				rectangle.calculateArea();
				rectangle.displayArea();
				break;
			case 3:
				System.out.println("Option 3: Square");
				Square square = new Square();
				square.setSide();
				square.calculateArea();
				square.displayArea();
				break;
			case 4:
				System.out.println("Option 4: Triangle");
				Triangle triangle = new Triangle();
				triangle.setBase();
				triangle.setHeight();
				triangle.calculateArea();
				triangle.displayArea();
				break;
			case 5:
				System.out.println("Option 5: Sphere");
				Sphere sphere = new Sphere();
				sphere.setRadius();
				sphere.calculateVolume();
				sphere.displayVolume();
				break;
			case 6:
				System.out.println("Option 6: Cube");
				Cube cube = new Cube();
				cube.setEdge();
				cube.calculateVolume();
				cube.displayVolume();
				break;
			case 7:
				System.out.println("Option 7: Cone");
				Cone cone = new Cone();
				cone.setRadius();
				cone.setHeight();
				cone.calculateVolume();
				cone.displayVolume();
				break;
			case 8:
				System.out.println("Option 8: Cylinder");
				Cylinder cylinder = new Cylinder();
				cylinder.setRadius();
				cylinder.setHeight();
				cylinder.calculateVolume();
				cylinder.displayVolume();
				break;
			case 9:
				System.out.println("Option 9: Torus");
				Torus torus = new Torus();
				torus.setMajorRadius();
				torus.setMinorRadius();
				torus.calculateVolume();
				torus.displayVolume();
				break;
			case 10:
				exitMessage();
				quitMenu = true;
				break;
			}
			// if quit menu if false, call keepGoing() to get choice to continue or not
			if (!quitMenu) {
				if(!keepGoing()) {
					// if keepGoing is false, show exit message and set quitMenu true
					exitMessage();
					quitMenu = true;
				}
			}
		}
	}
	
	// Method to display menu options
	static void displayMenu() {
		System.out.println("**** Welcome to the Java OO Shapes Program ****");
		System.out.println("Select from the menu below:");
		System.out.println("1. Construct a Circle");
		System.out.println("2. Construct a Rectangle");
		System.out.println("3. Construct a Square");
		System.out.println("4. Construct a Triangle");
		System.out.println("5. Construct a Sphere");
		System.out.println("6. Construct a Cube");
		System.out.println("7. Conctruct a Cone");
		System.out.println("8. Construct a Cylinder");
		System.out.println("9. Construct a Torus");
		System.out.println("10. Exit the program");
	}
	
	// Method to display exit message with date/time
	static void exitMessage() {
		LocalDateTime dt = LocalDateTime.now();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("E, MMM dd yyyy HH:mm:ss"); 
		System.out.println("Thank you for using the program.");
		System.out.println("It is " + dt.format(dtf));
	}
	
	// Method gets user input for menu choice, returns choice as integer
	static int menuChoice() {
		Scanner in = new Scanner(System.in);
		while (true) {
			// Users selects choice from menu, implement error checking for invalid input
			try {
				int menuChoice = in.nextInt();
				// Only valid option is 1-10
				if (menuChoice >= 1 && menuChoice <= 10) {
					return menuChoice;
				}
				else {
					System.out.println("Invalid input. Please enter a valid menu choice.");
					continue;
				}
			} catch (java.util.InputMismatchException e) {
				System.out.println("Invalid input. Please enter a valid menu choice.");
				in.next(); // Consume the invalid input
				continue;
			} 
		}
	}
	
	// Method gets user input to reload the main menu or exit the program
	static boolean keepGoing() {
		System.out.println("Would you like to make another selection? Enter '1' for Yes or '0' for No.");
		Scanner in = new Scanner(System.in);
        while (true) {
			try {
				// Only valid input is 1 or 0
				int keepGoing = in.nextInt();
				if (keepGoing == 1) {
					return true;
				}
				else if (keepGoing == 0) {
					return false;
				}
				else if (keepGoing < 0 || keepGoing > 1){
					System.out.println("Invalid input. Please enter '1' to continue or '0' to exit.");
					continue;
				}
			} catch (java.util.InputMismatchException e) {
				System.out.println("Invalid input. Please enter '1' to continue or '0' to exit.");
				in.next(); // Consume the invalid input
				continue; // Continue to the next iteration of the loop
			}
		}
	}
}
