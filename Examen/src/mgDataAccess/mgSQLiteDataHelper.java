package mgDataAccess;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import mgFramework.mgAppException;

public abstract class mgSQLiteDataHelper {

    private static final String DBPathConnection = "jdbc:sqlite:Examen\\database\\MG-EXAMEN-DB.db";
    private static final String SELECT_QUERY_AUTENTICACION = "SELECT * FROM credenciales WHERE usuario = ? and contrasena = ?";
    private static Connection conn = null;

    public mgSQLiteDataHelper() {

    }

    /**
     * Abre una conexiÃƒÆ’Ã‚Â³n a la base de datos SQLite.
     *
     * @return La conexiÃƒÆ’Ã‚Â³n a la base de datos.
     * @throws mgAppException Si ocurre un error al abrir la conexiÃƒÆ’Ã‚Â³n.
     */
    public static synchronized Connection openConnection() throws mgAppException {
        try {
            if (conn == null || conn.isClosed()) {
                conn = DriverManager.getConnection(DBPathConnection);
            }
        } catch (SQLException e) {
            throw new mgAppException(e, "SQLiteDataHelper", "Fallo la coneccion a la base de datos");
        }
        return conn;
    }

    /**
     * Cierra la conexiÃƒÆ’Ã‚Â³n a la base de datos.
     *
     * @throws mgAppException Si ocurre un error al cerrar la conexiÃƒÆ’Ã‚Â³n.
     */
    protected static void closeConnection() throws mgAppException {
        try {
            if (conn != null)
                conn.close();
        } catch (Exception e) {
            throw new mgAppException(e, "SQLiteDataHelper", "Fallo la conecciÃƒÆ’Ã‚Â¯Ãƒâ€šÃ‚Â¿Ãƒâ€šÃ‚Â½n con la base de datos");
        }
    }

    /**
     * Cierra los recursos de la base de datos, como el ResultSet, PreparedStatement y Connection.
     *
     * @param resultSet  El ResultSet a cerrar.
     * @param statement  El PreparedStatement a cerrar.
     * @param connection La Connection a cerrar.
     * @throws mgAppException Si ocurre un error al cerrar los recursos.
     */
    protected static void closeResources(ResultSet resultSet, PreparedStatement statement, Connection connection)
            throws mgAppException {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                throw new mgAppException(e, "SQLiteDataHelper", "Error al cerrar el ResultSet");
            }
        }

        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new mgAppException(e, "SQLiteDataHelper", "Error al cerrar el PreparedStatement");
            }
        }

        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new mgAppException(e, "SQLiteDataHelper", "Error al cerrar la Connection");
            }
        }

    }

    /**
     * Valida las credenciales de un usuario en la base de datos.
     *
     * @param nombreUsuario El nombre de usuario a validar.
     * @param contrasena    La contraseÃƒÆ’Ã‚Â±a a validar.
     * @return true si las credenciales son vÃƒÆ’Ã‚Â¡lidas, false en caso contrario.
     * @throws SQLException    Si ocurre un error al interactuar con la base de datos.
     * @throws mgAppException Si ocurre un error de la aplicaciÃƒÆ’Ã‚Â³n.
     */
    public static boolean validarCredenciales(String nombreUsuario, String contrasena)
            throws SQLException, mgAppException {
        try (Connection connection = mgSQLiteDataHelper.openConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUERY_AUTENTICACION)) {
            preparedStatement.setString(1, nombreUsuario);
            preparedStatement.setString(2, contrasena);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (((ResultSet) resultSet).next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    /**
     * Encripta una cadena de texto utilizando el algoritmo MD5.
     *
     * @param texto El texto a encriptar.
     * @return El hash MD5 en formato hexadecimal.
     * @throws NoSuchAlgorithmException Si el algoritmo MD5 no estÃƒÆ’Ã‚Â¡ disponible.
     */
    public static String mgEncriptarMD5(String texto) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(texto.getBytes());
        byte[] digest = md.digest();
        StringBuilder sb = new StringBuilder();
        for (byte b : digest) {
            sb.append(String.format("%02x", b & 0xff));
        }
        return sb.toString();
    }

/**
     * Crea las tablas necesarias en la base de datos.
     *
     * @throws mgAppException Si ocurre un error al crear las tablas.
     */
    public static void mgCrearTablas() throws mgAppException {
        String[] sqlQueries = {

            "CREATE TABLE IF NOT EXISTS MG_USUARIOS (" +
                "IdUsuario INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "Usuario VARCHAR(30) UNIQUE NOT NULL," +
                "Contrasenia VARCHAR(10) NOT NULL);",

            "CREATE TABLE IF NOT EXISTS Coordenada (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Coordenada VARCHAR(2));",

            "CREATE TABLE IF NOT EXISTS CoordenadaTipo (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "CoordenadaTipo VARCHAR(20));",

            "CREATE TABLE IF NOT EXISTS Arsenal (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "TipoArsenal VARCHAR(50));",

            "CREATE TABLE IF NOT EXISTS Horarios (" +
                "Lunes VARCHAR(5)," +
                "Martes VARCHAR(5)," +
                "Miercoles VARCHAR(5)," +
                "Jueves VARCHAR(5)," +
                "Viernes VARCHAR(5)," +
                "HorariosID INTEGER PRIMARY KEY AUTOINCREMENT);"
        };

        try (Connection conn = mgSQLiteDataHelper.openConnection()) {
            for (String sql : sqlQueries) {
                try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    pstmt.executeUpdate();
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
  
}
