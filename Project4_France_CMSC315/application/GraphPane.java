/*
 * Name: Andrew France
 * Project Name: Project 4
 * Date: 10/09/2023
 * 
 * Class Description: The GraphPane class extends the Pane class. It handles the event for 
 * mouse clicks, creating the display for points, labels, and lines. 
 * 
 */
package application;

import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

public class GraphPane extends Pane {
    private Graph graph;
    private char vertexLabel = 'A';
    private TextField vertex1TextField;
    private TextField vertex2TextField;
    private TextArea messageTextArea;

    public GraphPane(Graph graph, TextField vertex1TextField, TextField vertex2TextField, TextArea messageText) {
        this.graph = graph;
        this.vertex1TextField = vertex1TextField;
        this.vertex2TextField = vertex2TextField;
        this.messageTextArea = messageText;
        setOnMouseClicked(this::handleMouseClick);
    }

    private void handleMouseClick(MouseEvent event) {
        double x = event.getX();
        double y = event.getY();

        // Create a new Vertex object
        Vertex newVertex = new Vertex(String.valueOf(vertexLabel), x, y);
        vertexLabel++; // Move to the next letter for the next vertex

        // Add the new vertex to the graph
        graph.addVertex(newVertex);

        // Display the new vertex as a point using the Circle class
        Circle circle = new Circle(x, y, 5); // Adjust the radius as needed
        circle.setFill(Color.BLACK);

        // Display the vertex label
        Text text = new Text(x - 5, y - 10, newVertex.getName());

        // Add the circle and text to the pane
        getChildren().addAll(circle, text);

        // Draw edges after adding a new vertex
        drawEdges();
    }

    public void drawEdges() {
        // Clear the previous edges before drawing the new ones
        getChildren().removeIf(node -> node instanceof Line);

        for (int i = 0; i < graph.getVertices().size(); i++) {
            Vertex vertex1 = graph.getVertices().get(i);

            for (int neighborIndex : graph.getAdjacencyList().get(i)) {
                Vertex vertex2 = graph.getVertices().get(neighborIndex);

                // Draw edge between vertex1 and vertex2
                Line edge = new Line(vertex1.getX(), vertex1.getY(), vertex2.getX(), vertex2.getY());
                getChildren().add(edge);
            }
        }
    }

    public void connectEdges() {
        // Get the names of the vertices to connect from the text fields
        String vertex1Name = vertex1TextField.getText();
        String vertex2Name = vertex2TextField.getText();

        // Find the corresponding vertices in the graph
        Vertex vertex1 = findVertexByName(vertex1Name);
        Vertex vertex2 = findVertexByName(vertex2Name);

        if (vertex1 != null && vertex2 != null) {
            // Add an edge between the specified vertices
            graph.addEdge(graph.getVertices().indexOf(vertex1), graph.getVertices().indexOf(vertex2));

            // Redraw edges after connecting vertices
            drawEdges();
        } else {
            // Handle the case where one or both vertices are not found
            messageTextArea.setText("Invalid vertex names entered.");
        }
    }

    private Vertex findVertexByName(String vertexName) {
        for (Vertex vertex : graph.getVertices()) {
            if (vertex.getName().equals(vertexName)) {
                return vertex;
            }
        }
        return null;
    }
}
