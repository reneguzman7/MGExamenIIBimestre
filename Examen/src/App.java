import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.SwingUtilities;

import mgDataAccess.mgArchivoBD;
import mgFramework.mgAppException;
import mgUI.mgInterfaz;
import mgUI.mgLogin;
import mgDataAccess.mgSQLiteDataHelper;



public class App extends mgArchivoBD {
    public static void main(String[] args) throws Exception, NoSuchAlgorithmException {
        mgLogin.mgLogin();
        // mgArchivoBD.mgCargarDatosABaseDeDatos(mgArchivoBD.mgRutaArchivo);

        SwingUtilities.invokeLater(() -> {
            mgInterfaz frame = new mgInterfaz();
            frame.setVisible(true);

            String username = mgLogin.mgUsuarioLogeado;
            frame.updateUserData(username);

            try {
                Connection connection = mgSQLiteDataHelper.openConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement
                        .executeQuery("SELECT Coordenada, CoordenadaTipo, TipoArsenal, Hora FROM TablaJoin");
                frame.updateTableData(resultSet);
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (mgAppException e) {
                e.printStackTrace();
            }

        });
    }
}
