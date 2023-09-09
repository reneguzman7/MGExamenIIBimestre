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

    private static final String DBPathConnection = "jdbc:sqlite:database\\MG-ARSENAL-DB.db";
    private static final String SELECT_QUERY_AUTENTICACION = "SELECT * FROM credenciales WHERE usuario = ? and contrasena = ?";
    private static Connection conn = null;

    public mgSQLiteDataHelper() {

    }

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

    protected static void closeConnection() throws mgAppException {
        try {
            if (conn != null)
                conn.close();
        } catch (Exception e) {
            throw new mgAppException(e, "SQLiteDataHelper", "Fallo la conección con la base de datos");
        }
    }

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

    public static void mgInsertarUsuario(String nombreUsuario, String contrasena) {
        try {
            String contrasenaEncriptada = mgEncriptarMD5(contrasena);

            // Obtener una conexión existente desde el método openConnection
            Connection connection = openConnection();

            String insertQuery = "INSERT INTO usuarios (nombre_usuario, contrasena) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, nombreUsuario);
            preparedStatement.setString(2, contrasenaEncriptada);
            preparedStatement.executeUpdate();

            // No cierres la conexión aquí, ya que es compartida y debe cerrarse en otro
            // lugar

        } catch (NoSuchAlgorithmException | SQLException | mgAppException e) {
            e.printStackTrace();
        }
    }

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
}
