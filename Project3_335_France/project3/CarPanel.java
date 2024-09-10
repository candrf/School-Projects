/*
 * CarPanel.java
 * Andrew France
 * 5/6/2024
 * 
 * The purpose of this class is to create the JPanel that displays the car's info in the GUI.
 * This panel will display the speed and x,y coordinates of 3 cars based on the position given 
 * by its Runnable counterpart. It also contains the method to start the threads that run the car displays. 
 */
package project3;

import javax.swing.*;
import java.awt.*;

public class CarPanel extends JPanel {
    private JLabel car1Label, car2Label, car3Label;
    private JLabel car1Info, car2Info, car3Info;

    public CarPanel() {
        setPreferredSize(new Dimension(250, 300));
        setBorder(BorderFactory.createTitledBorder("Cars"));

        car1Label = new JLabel("Car 1:");
        car2Label = new JLabel("Car 2:");
        car3Label = new JLabel("Car 3:");

        car1Info = new JLabel("X: 0m, Speed: 0km/h");
        car2Info = new JLabel("X: 0m, Speed: 0km/h");
        car3Info = new JLabel("X: 0m, Speed: 0km/h");

        setLayout(new GridLayout(6,0));

        add(car1Label);
        add(car1Info);
        add(car2Label);
        add(car2Info);
        add(car3Label);
        add(car3Info);
    }
    public void carStart() {
        // Start threads for each car
        Thread car1Thread = new Thread(new CarRunnable(car1Info));
        Thread car2Thread = new Thread(new CarRunnable(car2Info));
        Thread car3Thread = new Thread(new CarRunnable(car3Info));
        
        car1Thread.start();
        car2Thread.start();
        car3Thread.start();
    }
}
