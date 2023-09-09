import java.security.NoSuchAlgorithmException;

import mgDataAccess.mgArchivoBD;

public class App {
    public static void main(String[] args) throws Exception, NoSuchAlgorithmException {
        // mgLogin.mgLogin();
        mgArchivoBD.mgCargarDatosABaseDeDatos(mgArchivoBD.mgRutaArchivo);
    }
}
