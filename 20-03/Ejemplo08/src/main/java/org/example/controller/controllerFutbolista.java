package org.example.controller;

import org.example.model.Futbolista;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class controllerFutbolista {

    public Futbolista[] readCSV(File file) {
        Futbolista[] futbolistas = new Futbolista[100];
        int index = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                Futbolista futbolista = new Futbolista();
                futbolista.setNombre(values[0]);
                futbolista.setGoles(Integer.parseInt(values[1]));
                futbolistas[index++] = futbolista;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return futbolistas;
    }

    public DefaultCategoryDataset createDataset(Futbolista[] futbolistas) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (Futbolista futbolista : futbolistas) {
            if (futbolista != null) {
                dataset.addValue(futbolista.getGoles(), "Goles", futbolista.getNombre());
            }
        }
        return dataset;
    }

    public void bubbleSort(Futbolista[] futbolistas, Runnable updateUI) {
        System.out.println("Inicio de ordenamiento");

        new Thread(() -> {
            int n = futbolistas.length;
            boolean swapped;
            for (int i = 0; i < n - 1; i++) {
                swapped = false;
                for (int j = 0; j < n - i - 1; j++) {
                    if (futbolistas[j] != null && futbolistas[j + 1] != null && futbolistas[j].getGoles() > futbolistas[j + 1].getGoles()) {

                        Futbolista temp = futbolistas[j];
                        futbolistas[j] = futbolistas[j + 1];
                        futbolistas[j + 1] = temp;
                        swapped = true;
                        System.out.println("Swapped: " + futbolistas[j].getNombre() + " with " + futbolistas[j + 1].getNombre());
                    }
                }

                SwingUtilities.invokeLater(() -> {;
                    updateUI.run();
                });
                if (!swapped) break;
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Fin de ordenamiento");
        }).start();
    }

}