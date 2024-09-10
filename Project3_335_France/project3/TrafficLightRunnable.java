/*
 * TrafficLightRunnable.java
 * Andrew France
 * 5/6/2024
 * 
 * The purpose of this class is to handle the Threads for the traffic light information. It has the three 
 * colors pre-loaded into a String array. It uses the Thread.sleep() method to cycle through the light colors
 * for a pre-determined amount of time during the simulation.    
 */
package project3;

import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class TrafficLightRunnable implements Runnable {
    private JLabel trafficLight;
    private String[] lightColors = {"Green", "Yellow", "Red"};
    
    public TrafficLightRunnable(JLabel trafficLight) {
        this.trafficLight = trafficLight;  
    }

    @Override
    public void run() {
    	// Run 3 iterations (1 for each intersection)
    	for (int i = 0; i < 3; i++) {
            try {
            	// Green 8 seconds
                SwingUtilities.invokeLater(() -> trafficLight.setText(lightColors[0]));
                Thread.sleep(8000);
                // Yellow 2 seconds
                SwingUtilities.invokeLater(() -> trafficLight.setText(lightColors[1]));
                Thread.sleep(2000);
                // Red 5 seconds
                SwingUtilities.invokeLater(() -> trafficLight.setText(lightColors[2]));
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    	}
    }
}
