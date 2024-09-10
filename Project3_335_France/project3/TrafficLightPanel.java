/*
 * TrafficLightPanel.java
 * Andrew France
 * 5/6/2024
 * 
 * The purpose of this class is to create the JPanel that displays the traffic light info.
 * It will display the current color based on its Runnable counterpart. It contains the method
 * to start the Threads that handle the 3 lights and intersections.   
 */
package project3;

import javax.swing.*;
import java.awt.*;

public class TrafficLightPanel extends JPanel {
    private JLabel intersection1Label, intersection2Label, intersection3Label;
    private JLabel trafficLight1, trafficLight2, trafficLight3;


    public TrafficLightPanel() {
        setPreferredSize(new Dimension(50, 300));
        setBorder(BorderFactory.createTitledBorder("Traffic Lights"));

        intersection1Label = new JLabel("Intersection 1:");
        intersection2Label = new JLabel("Intersection 2:");
        intersection3Label = new JLabel("Intersection 3:");

        trafficLight1 = new JLabel("Red");
        trafficLight2 = new JLabel("Red");
        trafficLight3 = new JLabel("Red");

        setLayout(new GridLayout(3, 2));

        add(intersection1Label);
        add(trafficLight1);
        add(intersection2Label);
        add(trafficLight2);
        add(intersection3Label);
        add(trafficLight3);


    }
	public void trafficLightStart() {
        // Start threads for each traffic light
        Thread light1Thread = new Thread(new TrafficLightRunnable(trafficLight1));
        Thread light2Thread = new Thread(new TrafficLightRunnable(trafficLight2));
        Thread light3Thread = new Thread(new TrafficLightRunnable(trafficLight3));
        
        light1Thread.start();
        light2Thread.start();
        light3Thread.start();
    }    
}
  