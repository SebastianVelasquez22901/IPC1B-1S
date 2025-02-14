/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lab13_02;

/**
 *
 * @author SBGam
 */

import java.util.Random;
public class Tablero {
    private char[][] tablero;
    private int tamaño;

    public Tablero(int tamaño) {
        this.tamaño = tamaño;
        this.tablero = new char[tamaño][tamaño];
        llenarConLetrasAleatorias();
    }

    private void llenarConLetrasAleatorias() {
        Random rand = new Random();
        for (int i = 0; i < tamaño; i++) {
            for (int j = 0; j < tamaño; j++) {
                tablero[i][j] = (char) ('A' + rand.nextInt(26));
            }
        }
    }

    public void colocarPalabras(String[] palabras) {
        Random rand = new Random();
        for (String palabra : palabras) {
            int fila = rand.nextInt(tamaño);
            int col = rand.nextInt(tamaño - palabra.length());

            for (int i = 0; i < palabra.length(); i++) {
                tablero[fila][col + i] = palabra.charAt(i);
            }
        }
    }

    public char[][] getTablero() {
        return tablero;
    }

    public static void mostrarTablero(char[][] tablero) {
        for (char[] fila : tablero) {
            for (char c : fila) {
                System.out.print(c + " ");
            }
            System.out.println();
        }
    }
}
