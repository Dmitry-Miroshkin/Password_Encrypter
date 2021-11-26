public class PasswordCompare {
    public static void main(String[] args) {
        if (args.length == 0) return;

//        шифровка пароля пользователя
        String encrypted = new String(PasswordEncrypter.encrypt("ivfk"));
        String usr_enc = new String(PasswordEncrypter.encrypt(args[0]));

        // операция сравнения
        if (encrypted.compareTo(usr_enc) == 0) {
            System.out.println("Пароль введен верно");
        } else System.out.println("Пароль указан не верно");
    }
}
