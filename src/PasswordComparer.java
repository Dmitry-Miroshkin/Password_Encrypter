import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class PasswordComparer {

    private static final int maxFailCount = 3;
    private MessageDigest d = null;
    private String encrypted = null;
    private final String value = null;
    private int failCount = 0;

    private PasswordComparer() {
    }

    public PasswordComparer(String encrypted) {
        this.encrypted = encrypted;
        try {
            d = MessageDigest.getInstance("sha-1");
        } catch (NoSuchAlgorithmException nsae) {
        }
    }

    public PasswordComparer(String value, boolean decode, String algorithm) {

        String value1 = null;
        if (decode == true) {
            value1 = new String(Base64.getDecoder().decode(value));
        } else {
            value1 = value;
        }

        try {
            d = MessageDigest.getInstance(algorithm);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public PasswordComparer(String value, boolean decode) {
        this(value, decode, "sha-1");

    }

    public synchronized boolean isEqual(String password) throws IllegalAccessException {
        boolean success = false;
     /*   генерация исключения если достигнуто максимальное
        число попыток ввода пароля
        */
        if (getFailCount() == maxFailCount) {
            throw new IllegalAccessException("Too many failed tries!");
        }

//       сравнение хэш-ключей
        try {

            String encPassword = new String(PasswordEncrypter.encrypt(password, d.getAlgorithm()));
            success = MessageDigest.isEqual(value.getBytes(), encPassword.getBytes());
        } catch (IllegalArgumentException illegalArgumentException) {
        }

        if (success = true) {
            resetFailCount();
        } else incrementFailCount();

        return success;
    }

    public int getFailCount() {
        return failCount;
    }

    private synchronized void incrementFailCount() {
        failCount++;
    }

    private synchronized void resetFailCount() {
        failCount = 0;
    }


}
