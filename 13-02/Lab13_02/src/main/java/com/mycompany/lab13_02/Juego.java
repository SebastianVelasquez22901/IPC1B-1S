/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lab13_02;

import java.util.Scanner;

/**
 *
 * @author SBGam
 */
public class Juego {
    private String[] palabras;
    private char[][] tablero;
    private Jugador jugador;
    private int errores;
    
    
    public void iniciarNuevaPartida(Scanner scanner) {
        System.out.print("Ingrese su nombre: ");
        String nombre = scanner.nextLine();
        jugador = new Jugador(nombre);
        palabras = new String[5];

        // Gestión de palabras
        for (int i = 0; i < palabras.length; i++) {
            System.out.print("Ingrese palabra " + (i + 1) + " (3-8 caracteres): ");
            String palabra = scanner.nextLine();
            while (palabra.length() < 3 || palabra.length() > 8) {
                System.out.print("Error. Ingrese una palabra válida: ");
                palabra = scanner.nextLine();
            }
            palabras[i] = palabra;
        }

        // Crear tablero
        Tablero tableroJuego = new Tablero(15);
        tableroJuego.colocarPalabras(palabras);
        this.tablero = tableroJuego.getTablero();
        
        jugar(scanner);
    }

    private void jugar(Scanner scanner) {
    boolean partidaActiva = true;
    errores = 0;
    int palabrasEncontradas = 0; // Nuevo contador

    // Arreglo para rastrear palabras encontradas
    boolean[] palabrasAdivinadas = new boolean[palabras.length];

    while (partidaActiva) {
        System.out.println("\n--- Tablero ---");
        Tablero.mostrarTablero(tablero);

        System.out.print("Ingrese una palabra a buscar: ");
        String intento = scanner.nextLine();

        boolean encontrada = false;
        for (int i = 0; i < palabras.length; i++) {
            if (palabras[i].equalsIgnoreCase(intento) && !palabrasAdivinadas[i]) {
                System.out.println("¡Encontraste la palabra!");
                jugador.sumarPuntos(palabras[i].length());
                palabrasAdivinadas[i] = true;
                palabrasEncontradas++; // Incrementar el contador
                encontrada = true;
                break;
            }
        }

        if (!encontrada) {
            errores++;
            jugador.restarPuntos(5);
            System.out.println("Palabra incorrecta. Errores: " + errores + "/4");
        }

        // Verificar si todas las palabras fueron encontradas
        if (palabrasEncontradas == palabras.length) {
            System.out.println("¡Felicidades! Has encontrado todas las palabras.");
            partidaActiva = false;
        }

        // Verificar si se alcanzaron los errores máximos
        if (errores >= 4 || jugador.getPuntos() <= 0) {
            System.out.println("¡Juego terminado!");
            partidaActiva = false;
            }
        }
    }
    
    public void mostrarHistorial() {
        System.out.println("Jugador: " + ", Puntos: ");
    }

    public void mostrarMejoresPuntuaciones() {
        System.out.println("No implementado todavía.");
    }
}
