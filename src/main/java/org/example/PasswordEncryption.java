import org.mindrot.jbcrypt.BCrypt;

public class PasswordEncryption {

    public static String hashPassword(String password) {
        int workFactor = 12; // Je höher der Wert, desto sicherer, aber langsamer ist der Hashing-Prozess
        return BCrypt.hashpw(password, BCrypt.gensalt(workFactor));
    }

    public static boolean checkPassword(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }

    public static void main(String[] args) {
        String password = "meinSicheresPasswort";

        // Passwort hashen
        String hashedPassword = hashPassword(password);
        System.out.println("Gehashtes Passwort: " + hashedPassword);

        // Passwort validieren
        boolean isPasswordCorrect = checkPassword(password, hashedPassword);
        System.out.println("Passwort korrekt: " + isPasswordCorrect);
    }
}
