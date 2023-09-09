import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.SwingUtilities;

import mgDataAccess.mgArchivoBD;
import mgDataAccess.mgSQLiteDataHelper;
import mgFramework.mgAppException;
import mgUI.mgInterfaz;
import mgUI.mgLogin;


public class App extends mgArchivoBD {
    public static void main(String[] args) throws Exception, NoSuchAlgorithmException {
        mgLogin.mgLogin();
        mgArchivoBD.mgCargarDatosABaseDeDatos(mgArchivoBD.mgRutaArchivo);

        SwingUtilities.invokeLater(() -> {
            mgInterfaz frame = new mgInterfaz();
            frame.setVisible(true);

            String username = mgLogin.mgUsuarioLogeado;
            frame.updateUserData(username);

            try {
                Connection connection = mgSQLiteDataHelper.openConnection();
                Statement statement = connection.createStatement();

                // Query SQL para obtener los datos deseados mediante JOIN
                String query = "SELECT MG_USUARIOS.Usuario, Coordenada.Coordenada, CoordenadaTipo.CoordenadaTipo, Arsenal.TipoArsenal, Horarios.Dia, Horarios.Hora "
                        +
                        "FROM MG_USUARIOS " +
                        "INNER JOIN Coordenada ON MG_USUARIOS.CoordenadaID = Coordenada.id " +
                        "INNER JOIN CoordenadaTipo ON MG_USUARIOS.CoordenadaTipoID = CoordenadaTipo.id " +
                        "INNER JOIN Arsenal ON MG_USUARIOS.ArsenalID = Arsenal.id " +
                        "INNER JOIN Horarios ON MG_USUARIOS.HorariosID = Horarios.HorariosID";

                ResultSet resultSet = statement.executeQuery(query);
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
