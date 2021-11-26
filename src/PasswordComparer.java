import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class PasswordComparer {

    private MessageDigest d = null;
    private  String encrypted = null;

    private PasswordComparer() {
    }

    public  PasswordComparer(String encrypted){
       this.encrypted = encrypted;
       try {
           d = MessageDigest.getInstance("sha-1");
       }catch (NoSuchAlgorithmException nsae){
       }
    }

    public PasswordComparer(String value, boolean decode, String algorithm){

        String value1 = null;
        if (decode == true){
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
    public PasswordComparer(String value, boolean decode){
        this(value, decode, "sha-1");

    }

   public boolean isEqual(String password){
       String encPassword = new String(PasswordEncrypter.encrypt(password));
       return MessageDigest.isEqual(encrypted.getBytes(), encPassword.getBytes());
   }
}
