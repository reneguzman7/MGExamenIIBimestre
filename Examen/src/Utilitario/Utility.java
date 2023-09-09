package Utilitario;

import java.util.Scanner;

/**
 * Clase de utilidades que proporciona métodos comunes para la aplicación.
 */

public class Utility {
    public static Scanner mg= new Scanner(System.in);
    
    /**
     * Valida y solicita un número entero al usuario.
     *
     * @param mensaje El mensaje que se muestra para solicitar el número.
     * @return El número entero validado.
     */

    public static int mgValidarNum(String mensaje) {
        int numero;
        System.out.print(mensaje);
        do {
            try {
                numero = Integer.parseInt(mg.nextLine());
                if (numero < 0 || numero > 4) {
                    System.out.println("Opcion invalida. Intente nuevamente.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Opcion invalida. Intente nuevamente.");
                numero = -1; 
            }
        } while (numero < 0 || numero > 4);

        return numero;
    }
    /**
     * Pausa la ejecución del programa durante un tiempo determinado en milisegundos.
     *
     * @param milisegundos El tiempo en milisegundos.
     */
    public static void pausa(int milisegundos) {
        try {
            Thread.sleep(milisegundos);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    /**
     * Borra el contenido de la consola.
     */
    public static void borrarConsola() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
