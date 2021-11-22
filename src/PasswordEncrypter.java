import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class PasswordEncrypter {
    //зашифрованный пароль
    public static byte[] encrypt(String password) {
        try {
            MessageDigest d = MessageDigest.getInstance("sha-1");
            d.update(password.getBytes());
            return d.digest();
        } catch (NoSuchAlgorithmException nsae) {
        }
        return null;
    }
    public static  String encryptAndEncode(String password){
        String encoded = Base64.getEncoder().encodeToString(encrypt(password));
        return encoded;
    }
}
