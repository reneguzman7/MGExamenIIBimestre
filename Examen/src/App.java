import java.security.NoSuchAlgorithmException;

import mgDataAccess.mgArchivoBD;
import mgUI.mgLogin;


public class App extends mgArchivoBD {
    public static void main(String[] args) throws Exception, NoSuchAlgorithmException {
        // mgLogin.mgLogin();
        mgArchivoBD.mgCargarDatosABaseDeDatos(mgArchivoBD.mgRutaArchivo);
    }
}
