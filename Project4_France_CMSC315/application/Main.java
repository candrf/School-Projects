/*
 * Name: Andrew France
 * Project Name: Project 4
 * Date: 10/09/2023
 * 
 * Class Description: The Main class implements main method to run the project. It creates all  
 * the buttons and text boxes, and sets the scene. 
 * 
 */
package application;
	
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Graph graph = new Graph();
        TextField vertex1TextField = new TextField();
        TextField vertex2TextField = new TextField();
        TextArea messageTextArea = new TextArea(); // TextArea to display messages
        messageTextArea.setEditable(false); // Make it non-editable
        messageTextArea.setWrapText(true); // Wrap text to fit within the TextArea
        GraphPane graphPane = new GraphPane(graph, vertex1TextField, vertex2TextField, messageTextArea);
        graphPane.setPrefSize(600, 500); // Set the preferred size of the GraphPane

        // Create GUI components, buttons, and text fields
        Button connectEdgesButton = new Button("Connect Edges");
        connectEdgesButton.setOnAction(event -> graphPane.connectEdges());

        Button hasCyclesButton = new Button("Has Cycles?");
        hasCyclesButton.setOnAction(event -> {
            // Call the hasCycles method and display the result
            boolean hasCycles = graph.hasCycles();
            messageTextArea.setText("Graph has cycles: " + hasCycles);
        });

        Button isConnectedButton = new Button("Is Connected?");
        isConnectedButton.setOnAction(event -> {
            // Call the isConnected method and display the result
            boolean isConnected = graph.isConnected();
            messageTextArea.setText("Graph is connected: " + isConnected);
        });

        Button depthFirstSearchButton = new Button("Depth First Search");
        depthFirstSearchButton.setOnAction(event -> {
            // Call the depthFirstSearch method and display the result
        	messageTextArea.setText("Depth First Search: " + graph.depthFirstSearch());
        });

        Button breadthFirstSearchButton = new Button("Breadth First Search");
        breadthFirstSearchButton.setOnAction(event -> {
            // Call the breadthFirstSearch method and display the result
        	messageTextArea.setText("Breadth First Search: " + graph.breadthFirstSearch());
        });

        HBox controls = new HBox(10,
                new Text("Vertex 1:"), vertex1TextField,
                new Text("Vertex 2:"), vertex2TextField,
                connectEdgesButton);
        controls.setAlignment(Pos.TOP_CENTER); // Align to the top of the HBox

        HBox analysisButtons = new HBox(5,
                hasCyclesButton, isConnectedButton, depthFirstSearchButton, breadthFirstSearchButton);
        analysisButtons.setAlignment(Pos.CENTER);
        
        HBox messageBox = new HBox(10, messageTextArea);
        messageBox.setAlignment(Pos.BOTTOM_CENTER);

        // Set up the scene
        VBox root = new VBox(controls, graphPane, analysisButtons, messageBox);
        Scene scene = new Scene(root, 600, 500); 
        primaryStage.setScene(scene);
        primaryStage.setTitle("Graph Visualization");
        primaryStage.show();
    }
}
