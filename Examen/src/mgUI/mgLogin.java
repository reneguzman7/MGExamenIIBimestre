package mgUI;

import java.util.Scanner;

import Utilitario.Utility;

public class mgLogin {
    public static Scanner mg= new Scanner(System.in);
    public static final String mgCedula = "1722966650";
    public static final String mgNombre = "Harryson Ariel Montesdeoca Rhea";
    public static final String mgCorreo = "harryson.montesdeoca@epn.edu.ec";
    public static final String mgCedula1 = "1750366286";
    public static final String mgNombre1 = "Rene Alejandro Guzman Moreira";
    public static final String mgCorreo1 = "rene.guzman@epn.edu.ec";
    static String mgUsuarioLogeado;

    /**
     * Realiza el proceso de inicio de sesion.
     *
     * @return true si el inicio de sesion es exitoso, false en caso contrario.
     */
    public static boolean mgLogin() {
        String mgUsuarioProfe = "profe";
        String mgClaveProfe = "1234";
        int Intentos = 1;
        while (Intentos <= 3) {
            Utility.borrarConsola();
            System.out.println(mgCedula);
            System.out.println(mgCorreo.toLowerCase());
            System.out.println(mgNombre.toUpperCase() + "\n");
            System.out.println(mgCedula1);
            System.out.println(mgCorreo1.toLowerCase());
            System.out.println(mgNombre1.toUpperCase());
            System.out.println();
            System.out.println("------------------------");
            System.out.print("+ Usuario: ");
            mgUsuarioLogeado = mg.nextLine();
            System.out.println();
            System.out.print("+ Clave: ");
            String Clave = mg.nextLine();
            String hiddenClave = mghidePassword(Clave); // Oculta la contrase�a
            System.out.println(hiddenClave); // Muestra la contrase�a oculta
            System.out.println("------------------------");
            System.out.print("* Numero de Intentos: " + Intentos);
            System.out.println();
            // pausa(1000);
            if ((mgUsuarioLogeado.equals(mgCorreo) && Clave.equals(mgCedula))
                    || mgUsuarioLogeado.equals(mgUsuarioProfe) && Clave.equals(mgClaveProfe)
                    || (mgUsuarioLogeado.equals(mgCorreo1) && Clave.equals(mgCedula1))) {
                System.out.println("Bienvenido " + mgUsuarioLogeado.toUpperCase());
                System.out.println("\nPress Any Key to Continue...");
                mg.nextLine();
                return true;
            }
            Intentos++;
        }
        System.out.println("\nLo sentimos, tu usuario y clave son incorrectos..!");
        System.out.println("Gracias!");

        Utility.pausa(1000);
        System.exit(-1);
        return true;
    }
    
    /**
     * <b>hidePassword</b>: Esta funcion nos permite esconder la contrase�a 
     * @param password: Contrase�a ingresada por el usuario
     * @return
     */
    private static String mghidePassword(String password) {
        StringBuilder hiddenPassword = new StringBuilder();
        for (int i = 0; i < password.length(); i++) {
            hiddenPassword.append("*");
        }
        return hiddenPassword.toString();
    }
}
