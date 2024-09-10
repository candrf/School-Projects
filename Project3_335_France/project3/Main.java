/*
 * Main.java
 * Andrew France
 * 5/6/2024
 * 
 * The purpose of this class is to be the main driver of the program. It holds the main method and makes all
 * of the appropriate function calls to run the program. In terms of the GUI, it is the base. It contains the
 * buttons that start/stop the program and incorporates JFrams from other classes to display the GUI. 
 */
package project3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.util.concurrent.ExecutorService;

public class Main extends JFrame {
    private JButton startButton, pauseButton, stopButton, continueButton;
    private TimePanel timePanel;
    private TrafficLightPanel trafficLightPanel;
    private CarPanel carPanel;
    // Use executor service to pause, stop, and continue threads
    // private ExecutorService simulation;

    public Main() {
        setTitle("Traffic Simulation");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 300);
        setLayout(new BorderLayout());

        // Create components
        startButton = new JButton("Start");
        pauseButton = new JButton("Pause");
        stopButton = new JButton("Stop");
        continueButton = new JButton("Continue");

        timePanel = new TimePanel();
        trafficLightPanel = new TrafficLightPanel();
        carPanel = new CarPanel();

        // Add event listeners
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Start simulation
            	timePanel.startTimer();
            	trafficLightPanel.trafficLightStart();
            	carPanel.carStart();
            }
        });

        pauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timePanel.stopTimer(); // Stop the timer
            	//pauseSimulation();
            }
        });

        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timePanel.stopTimer(); // Stop the timer
            	//stopSimulation();
            }
        });

        continueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	timePanel.startTimer(); // Start the timer
            	//continueSimulation();
            }
        });

        // Add components to the frame
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(startButton);
        buttonPanel.add(pauseButton);
        buttonPanel.add(stopButton);
        buttonPanel.add(continueButton);

        add(buttonPanel, BorderLayout.NORTH);
        add(timePanel, BorderLayout.WEST);
        add(trafficLightPanel, BorderLayout.CENTER);
        add(carPanel, BorderLayout.EAST);
    }
    
    // This method pauses the simulation
    public void pauseSimulation() {
    	// Need to implement
    }
    //This method stops the simulation
    public void stopSimulation() {
    	// Need to implement
    }
    //This method continues the simulation
    public void continueSimulation() {
    	// Need to implement
    }
    
    // Main driver method
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Main simulation = new Main();
                simulation.setVisible(true);
            }
        });
    }
}
