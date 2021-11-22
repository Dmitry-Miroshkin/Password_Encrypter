import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class PasswordEncrypter {
    //зашифрованный пароль
    // можно указать не только пароль но и алгоритм шифрования

    public static byte[] encrypt(String password, String algorithm) throws IllegalArgumentException {
        try {
            MessageDigest d = MessageDigest.getInstance(algorithm);
            d.update(password.getBytes());
            return d.digest();
        } catch (NoSuchAlgorithmException nsae) {
            throw new IllegalArgumentException("Illegal algorithm value.");
        }
    }

    // упрощенный вызов без  указания алгоритма
    public static byte[] encrypt(String password) throws IllegalArgumentException {
        return encrypt(password, "sha-1");
    }

    public static String encryptAndEncode(String password, String algorithm) {
        return Base64.getEncoder().encodeToString(encrypt(password, algorithm));
    }
    // упрощенная версия для формирования хэш- ключа по алгоритму sha-1
    public static String encryptAndEncode(String password) throws IllegalArgumentException {
        return encryptAndEncode(password, "sha-1");
    }
}
