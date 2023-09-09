package mgBusinessLogic;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class mgEncriptacion{

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
            // Manejo de la excepciÃƒÂ³n NoSuchAlgorithmException
            throw new RuntimeException("Error al encriptar: Algoritmo MD5 no disponible", e);
        }
    }

    
}
