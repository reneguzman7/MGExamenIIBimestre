package mgUI;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import mgUtility.Utility;

public class mgLogin {
    public static Scanner mg = new Scanner(System.in);
    public static final String mgCedula = "1722966650";
    public static final String mgNombre = "Harryson Ariel Montesdeoca Rhea";
    public static final String mgCorreo = "harryson.montesdeoca@epn.edu.ec";
    public static final String mgCedula1 = "1750366286";
    public static final String mgNombre1 = "Rene Alejandro Guzman Moreira";
    public static final String mgCorreo1 = "rene.guzman@epn.edu.ec";
    public static String mgUsuarioLogeado;

    public static boolean mgLogin() {
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
            String ClaveEncriptada = mgEncriptarMD5(Clave); // Encripta la contraseÃ±a ingresada

            System.out.println();
            System.out.println("------------------------");
            System.out.print("* Numero de Intentos: " + Intentos);
            System.out.println();
            
            boolean credencialesValidas = false;
            try {
                credencialesValidas = validarCredenciales(mgUsuarioLogeado, ClaveEncriptada); // Compara con la contraseÃ±a encriptada
            } catch (SQLException e) {
                e.printStackTrace();
            }
            
            if (credencialesValidas) {
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
        return false;
    }

    // MÃ©todo para encriptar con MD5
    public static String mgEncriptarMD5(String texto) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(texto.getBytes());
            byte[] digest = md.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(String.format("%02x", b & 0xff));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            // Manejo de la excepciÃ³n NoSuchAlgorithmException
            throw new RuntimeException("Error al encriptar: Algoritmo MD5 no disponible", e);
        }
    }

    // MÃ©todo para validar las credenciales en SQLite
    private static boolean validarCredenciales(String nombreUsuario, String contrasena) throws SQLException {
        String DB_URL = "jdbc:sqlite:Examen\\database\\MG-EXAMEN-DB.db"; // Cambia la ruta a tu archivo SQLite
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection(DB_URL);

            String SELECT_QUERY_AUTENTICACION = "SELECT * FROM MG_USUARIOS WHERE Usuario = ? AND Contrasenia = ?";
            preparedStatement = connection.prepareStatement(SELECT_QUERY_AUTENTICACION);
            preparedStatement.setString(1, nombreUsuario);
            preparedStatement.setString(2, contrasena);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return true;
            }
        } finally {
            // Cierra los recursos
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }

        return false;
    }
}
