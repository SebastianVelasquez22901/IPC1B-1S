/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.lab13_02;

import java.util.Scanner;

/**
 *
 * @author SBGam
 */
public class Lab13_02 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Juego juego = new Juego();
        boolean salir = false;

        while (!salir) {
            System.out.println("\n--- Menú Principal ---");
            System.out.println("1. Nueva Partida");
            System.out.println("2. Historial de Partidas");
            System.out.println("3. Mostrar Puntuaciones Más Altas");
            System.out.println("4. Mostrar Información del Estudiante");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            
            int opcion = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcion) {
                case 1:
                    juego.iniciarNuevaPartida(scanner);
                    break;
                case 2:
                    juego.mostrarHistorial();
                    break;
                case 3:
                    juego.mostrarMejoresPuntuaciones();
                    break;
                case 4:
                    System.out.println("Estudiante: Cristiano Carnet: CR7");
                    break;
                case 5:
                    salir = true;
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
        scanner.close();
    }
}
