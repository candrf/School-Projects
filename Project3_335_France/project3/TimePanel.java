/*
 * TimePanel.java
 * Andrew France
 * 5/6/2024
 * 
 * The purpose of this class is to display the current time to the GUI. It updates the time every
 * second, and it has methods to start and stop the time display.  
 */
package project3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimePanel extends JPanel {
    private JLabel timeLabel;
    private Timer timer;

    public TimePanel() {
        setPreferredSize(new Dimension(100, 100));
        setBorder(BorderFactory.createTitledBorder("Time"));
        // Initial display all 0s
        timeLabel = new JLabel("00:00:00");
        add(timeLabel);
    }

    // Method to start the timer
    public void startTimer() {
        if (timer == null) {
            timer = new Timer(1000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    timeLabel.setText(getCurrentTime());
                }
            });
            timer.start();
        }
    }

    // Method to stop the timer
    public void stopTimer() {
        if (timer != null) {
            timer.stop();
            timer = null;
        }
    }

    // Method to get current time in HH:mm:ss format
    private String getCurrentTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        return dateFormat.format(new Date());
    }
}
