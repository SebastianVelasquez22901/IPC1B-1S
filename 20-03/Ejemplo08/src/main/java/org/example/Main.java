package org.example;

import org.example.view.viewFutbolista;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new viewFutbolista().setVisible(true));
    }
}