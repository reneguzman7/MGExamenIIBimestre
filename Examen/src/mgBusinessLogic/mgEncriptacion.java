import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class mgEncriptacion{

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

    public static String mgEncriptarClaves(String mgEntrada) {
        System.out.println("");

    }
}
