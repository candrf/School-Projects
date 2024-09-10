/*
 * CarRunnable.java
 * Andrew France
 * 5/6/2024
 * 
 * The purpose of this class is to handle the Threads for the 3 cars' information. It uses pre-loaded positions
 * as well as the Thread.sleep() method to display when the car is moving during a green light or stopped at a red.
 * It cycles through each position once during the simulation.    
 */
package project3;

import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class CarRunnable implements Runnable {
    private JLabel carInfo;
    private String[] positions = {"X: 0m, Speed: 360km/h", "X: 100m, Speed: 360km/h", "X: 200m, Speed: 360km/h", 
    		"X: 300m, Speed: 360km/h", "X: 400m, Speed: 360km/h", "X: 500m, Speed: 360km/h", "X: 600m, Speed: 360km/h", 
    		"X: 700m, Speed: 360km/h", "X: 800m, Speed: 360km/h", "X: 900m, Speed: 360km/h", "X: 1000m, Speed: 0km/h", 
    		"X: 1000m, Speed: 360km/h", "X: 1100m, Speed: 360km/h", "X: 1200m, Speed: 360km/h", "X: 1300m, Speed: 360km/h",
    		"X: 1400m, Speed: 360km/h", "X: 1500m, Speed: 360km/h", "X: 1600m, Speed: 360km/h", "X: 1700m, Speed: 360km/h", 
    		"X: 1800m, Speed: 360km/h", "X: 1900m, Speed: 360km/h", "X: 2000m, Speed: 0km/h", "X: 2000m, Speed: 360km/h", 
    		"X: 2100m, Speed: 360km/h", "X: 2200m, Speed: 360km/h", "X: 2300m, Speed: 360km/h", "X: 2400m, Speed: 360km/h",
    		"X: 2500m, Speed: 360km/h", "X: 2600m, Speed: 360km/h", "X: 2700m, Speed: 360km/h","X: 2800m, Speed: 360km/h",
    		"X: 2900m, Speed: 360km/h", "X: 3000m, Speed: 0km/h"};
    
    public CarRunnable(JLabel carInfo) {
        this.carInfo = carInfo;
    }
    
    @Override
    public void run() {
    	// Run all hard coded positions for 3000m (3 intersections). Update GUI every second while moving.
        try {
            SwingUtilities.invokeLater(() -> carInfo.setText(positions[0])); // green light
            Thread.sleep(1000);
            SwingUtilities.invokeLater(() -> carInfo.setText(positions[1]));
            Thread.sleep(1000);
            SwingUtilities.invokeLater(() -> carInfo.setText(positions[2]));
            Thread.sleep(1000);
            SwingUtilities.invokeLater(() -> carInfo.setText(positions[3]));
            Thread.sleep(1000);
            SwingUtilities.invokeLater(() -> carInfo.setText(positions[4]));
            Thread.sleep(1000);
            SwingUtilities.invokeLater(() -> carInfo.setText(positions[5]));
            Thread.sleep(1000);
            SwingUtilities.invokeLater(() -> carInfo.setText(positions[6]));
            Thread.sleep(1000);
            SwingUtilities.invokeLater(() -> carInfo.setText(positions[7]));
            Thread.sleep(1000);
            SwingUtilities.invokeLater(() -> carInfo.setText(positions[8])); // yellow light
            Thread.sleep(1000);
            SwingUtilities.invokeLater(() -> carInfo.setText(positions[9]));
            Thread.sleep(1000);
            SwingUtilities.invokeLater(() -> carInfo.setText(positions[10])); // red light
            Thread.sleep(5000);
            SwingUtilities.invokeLater(() -> carInfo.setText(positions[11])); // green light
            Thread.sleep(1000);
            SwingUtilities.invokeLater(() -> carInfo.setText(positions[12]));
            Thread.sleep(1000);
            SwingUtilities.invokeLater(() -> carInfo.setText(positions[13]));
            Thread.sleep(1000);
            SwingUtilities.invokeLater(() -> carInfo.setText(positions[14]));
            Thread.sleep(1000);
            SwingUtilities.invokeLater(() -> carInfo.setText(positions[15]));
            Thread.sleep(1000);
            SwingUtilities.invokeLater(() -> carInfo.setText(positions[16]));
            Thread.sleep(1000);
            SwingUtilities.invokeLater(() -> carInfo.setText(positions[17]));
            Thread.sleep(1000);
            SwingUtilities.invokeLater(() -> carInfo.setText(positions[18]));
            Thread.sleep(1000);
            SwingUtilities.invokeLater(() -> carInfo.setText(positions[19])); // yellow light
            Thread.sleep(1000);
            SwingUtilities.invokeLater(() -> carInfo.setText(positions[20]));
            Thread.sleep(1000);
            SwingUtilities.invokeLater(() -> carInfo.setText(positions[21])); // red light
            Thread.sleep(5000);
            SwingUtilities.invokeLater(() -> carInfo.setText(positions[22])); // green light
            Thread.sleep(1000);
            SwingUtilities.invokeLater(() -> carInfo.setText(positions[23]));
            Thread.sleep(1000);
            SwingUtilities.invokeLater(() -> carInfo.setText(positions[24]));
            Thread.sleep(1000);
            SwingUtilities.invokeLater(() -> carInfo.setText(positions[25]));
            Thread.sleep(1000);
            SwingUtilities.invokeLater(() -> carInfo.setText(positions[26]));
            Thread.sleep(1000);
            SwingUtilities.invokeLater(() -> carInfo.setText(positions[27]));
            Thread.sleep(1000);
            SwingUtilities.invokeLater(() -> carInfo.setText(positions[28]));
            Thread.sleep(1000);
            SwingUtilities.invokeLater(() -> carInfo.setText(positions[29]));
            Thread.sleep(1000);
            SwingUtilities.invokeLater(() -> carInfo.setText(positions[30])); // yellow light
            Thread.sleep(1000);
            SwingUtilities.invokeLater(() -> carInfo.setText(positions[31]));
            Thread.sleep(1000);
            SwingUtilities.invokeLater(() -> carInfo.setText(positions[32])); // red light
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }    
    }
}
