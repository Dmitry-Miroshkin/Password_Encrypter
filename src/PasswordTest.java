import java.util.Objects;

public class PasswordTest {
    // пример использования класса PasswordEncrypter
    //дпя шифровки пароля
    public static void main(String[] args) {
        String pwd ="password";
        String encrypted = new String(Objects.requireNonNull(PasswordEncrypter.encrypt(pwd)));
        String encoded = PasswordEncrypter.encryptAndEncode(pwd);
        System.out.println("Зашифрованный пароль: " + encrypted);
        System.out.println("Кодированный пароль: " + encoded);
    }
}
