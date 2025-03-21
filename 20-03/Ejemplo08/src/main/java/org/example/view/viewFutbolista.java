package org.example.view;

import org.example.controller.controllerFutbolista;
import org.example.model.Futbolista;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class viewFutbolista extends JFrame {
    private JTextField filePathField;
    private JTextField chartTitleField;
    private JPanel chartPanel;
    private controllerFutbolista controller;
    private Futbolista[] futbolistas;

    public viewFutbolista() {
        controller = new controllerFutbolista(); // Inicialización del controlador

        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Añadir padding

        filePathField = new JTextField(20);
        JButton browseButton = new JButton("Buscar");
        chartTitleField = new JTextField("Titulo para la grafica", 20);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        topPanel.add(new JLabel("Ruta del archivo:"), gbc);

        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        topPanel.add(filePathField, gbc);

        gbc.gridx = 2;
        gbc.fill = GridBagConstraints.NONE;
        topPanel.add(browseButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        topPanel.add(new JLabel("Titulo para la grafica:"), gbc);

        gbc.gridx = 1;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        topPanel.add(chartTitleField, gbc);

        add(topPanel, BorderLayout.NORTH);

        chartPanel = new JPanel(new BorderLayout());
        add(chartPanel, BorderLayout.CENTER);

        JButton sortButton = new JButton("Ordenar");

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.NONE;
        topPanel.add(sortButton, gbc);

        sortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (futbolistas != null) {
                    System.out.println("Aplicando ordenamiento de burbuja");
                    controller.bubbleSort(futbolistas, () -> {
                        DefaultCategoryDataset dataset = controller.createDataset(futbolistas);
                        updateChart(dataset);
                    });
                }
            }
        });

        browseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("CSV files", "csv"));
                int result = fileChooser.showOpenDialog(viewFutbolista.this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    filePathField.setText(selectedFile.getAbsolutePath());
                    loadChartData(selectedFile);
                }
            }
        });
    }

    private void loadChartData(File file) {
        futbolistas = controller.readCSV(file);
        DefaultCategoryDataset dataset = controller.createDataset(futbolistas);
        updateChart(dataset);
        System.out.println("Chart data loaded");
    }

    private void updateChart(DefaultCategoryDataset dataset) { // Add updateChart method
        String chartTitle = chartTitleField.getText();
        JFreeChart barChart = ChartFactory.createBarChart(
                chartTitle,
                "Futbolista",
                "Goles",
                dataset,
                PlotOrientation.VERTICAL,
                false, true, false);

        ChartPanel chartPanel = new ChartPanel(barChart);
        this.chartPanel.removeAll();
        this.chartPanel.add(chartPanel, BorderLayout.CENTER);
        this.chartPanel.validate();
    }

}