/*
 * Andrew France
 * CMSC 451 6980
 * Professor Candice Adams
 * Project 1
 * 7/7/2024
 * 
 * This class creates a report of the benchmarked data. The user can select a file to upload
 * and it will create a table displaying the relevant information. 
 */

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BenchmarkReport {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Benchmark Report");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);
            frame.setLayout(new BorderLayout());

            // Create table model with column names
            DefaultTableModel model = new DefaultTableModel(new String[]{
                    "Data Set Size", "Avg Count", "CV Count (%)",
                    "Avg Time (ns)", "CV Time (%)"
            }, 0);

            JTable table = new JTable(model);
            JScrollPane scrollPane = new JScrollPane(table);
            frame.add(scrollPane, BorderLayout.CENTER);

            // Add a button to load the data
            JButton loadButton = new JButton("Load Data");
            frame.add(loadButton, BorderLayout.NORTH);

            loadButton.addActionListener(e -> {
                JFileChooser fileChooser = new JFileChooser();
                if (fileChooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    frame.setTitle("Benchmark Report: " + file.getName());
                    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                        // Clear existing table
                    	model.setRowCount(0);
                    	// Process new table
                    	String line;
                        while ((line = reader.readLine()) != null) {
                            processLine(line, model);
                        }
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(frame, "Error reading file: " + ex.getMessage());
                    }
                }
            });

            frame.setVisible(true);
        });
    }

    private static <DeciamlFormat> void processLine(String line, DefaultTableModel model) {
        StringTokenizer tokenizer = new StringTokenizer(line);
        int dataSetSize = Integer.parseInt(tokenizer.nextToken());
        List<Integer> criticalCounts = new ArrayList<>();
        List<Long> times = new ArrayList<>();

        while (tokenizer.hasMoreTokens()) {
            criticalCounts.add(Integer.parseInt(tokenizer.nextToken()));
            times.add(Long.parseLong(tokenizer.nextToken()));
        }

        double avgCriticalCount = calculateAverage(criticalCounts);
        double covCriticalCount = calculateCoefficientOfVariance(criticalCounts, avgCriticalCount);
        double avgTime = calculateAverage(times);
        double covTime = calculateCoefficientOfVariance(times, avgTime);
        
        DecimalFormat df = new DecimalFormat("#.##");
        
        model.addRow(new Object[]{
                dataSetSize, 
                df.format(avgCriticalCount),
                df.format(covCriticalCount),
                df.format(avgTime),
                df.format(covTime)
        });
    }

    private static double calculateAverage(List<? extends Number> values) {
        double sum = 0;
        for (Number value : values) {
            sum += value.doubleValue();
        }
        return sum / values.size();
    }

    private static double calculateCoefficientOfVariance(List<? extends Number> values, double average) {
        double varianceSum = 0;
        // Subtract the mean from each value, add squared deviations. 
        for (Number value : values) {
            double diff = value.doubleValue() - average;
            varianceSum += (diff * diff);
        }
        // Divide the sum by number of values
        double variance = varianceSum / values.size();
        // Square result to get standard deviation
        double standardDeviation = Math.sqrt(variance);
        // Get Coef of Variance by doing standard deviation/mean * 100
        return (standardDeviation / average) * 100;
    }
}
