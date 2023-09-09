import java.security.NoSuchAlgorithmException;

import javax.swing.SwingUtilities;

import mgDataAccess.mgArchivoBD;
import mgUI.mgInterfaz;
import mgUI.mgLogin;

public class App extends mgArchivoBD {
    public static void main(String[] args) throws Exception, NoSuchAlgorithmException {
        
        mgDataAccess.mgSQLiteDataHelper.mgCrearTablas();
        mgLogin.mgLogin();
        mgArchivoBD.mgCargarDatosABaseDeDatos(mgArchivoBD.mgRutaArchivo);

       SwingUtilities.invokeLater(() -> {
            mgInterfaz tablaInterfaz = new mgInterfaz();
            tablaInterfaz.setVisible(true);
        });
    }
}
