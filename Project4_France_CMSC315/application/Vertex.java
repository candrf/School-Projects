/*
 * Name: Andrew France
 * Project Name: Project 4
 * Date: 10/09/2023
 * 
 * Class Description: The Vertex class holds the x and y coordinates and name for the label 
 * of the points.  
 * 
 */
package application;

public class Vertex {
    private String name;
    private double x;
    private double y;

    public Vertex(String name, double x, double y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }

    public String getName() {
        return name;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    @Override
    public String toString() {
        return name; // Override toString to return the name of the vertex
    }
}
