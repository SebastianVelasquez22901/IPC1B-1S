

package com.mycompany.ejemplo1;

/**
 *
 * @author SBGam
 */

// Aqui se importan las librerias
import java.util.Random;
import java.util.Scanner;

public class Ejemplo1 {
    // Funcion principal
    public static void main(String[] args) {
        //Se llaman las librerias
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int numeroParaAdivinar = random.nextInt(10) + 1;
        int numeroIntentos = 0;
        int numeroPrueba = 0;
        boolean validadorAdivinanza = false;

        System.out.println("Bienvenido al juego de adivinanza de numeros!");
        System.out.println("Estoy pensando en un numero entre 1 y 10. Puedes adivinar cual es?");
        /*
        Sentencia de control que se llama asi mismo
        hasta que se cumpla la condicion
        */
        while (!validadorAdivinanza) {
            System.out.print("Introduce tu adivinanza: ");
            numeroPrueba = scanner.nextInt();
            numeroIntentos++;
            if (numeroPrueba < 1 || numeroPrueba > 10) {
                System.out.println("Por favor, introduce un numero entre 1 y 10.");
            } else if (numeroPrueba < numeroParaAdivinar) {
                System.out.println("Demasiado bajo. Intenta de nuevo!");
            } else if (numeroPrueba > numeroParaAdivinar) {
                System.out.println("Demasiado alto. Intenta de nuevo!");
            } else {
                validadorAdivinanza = true;
                System.out.println("Felicidades! Has adivinado el numero en " + numeroIntentos + " intentos.");
            }
        }

        scanner.close();
    }
}
