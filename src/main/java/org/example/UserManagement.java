import java.io.Console;
import java.util.HashMap;
import java.util.Scanner;

public class UserManagement {
    Scanner scanner = new Scanner(System.in);
    Console console = System.console();
    private HashMap<String, User> users = new HashMap<String, User>();
    public static User admin = new User("admin", "admin", "adminpassword");
    public static User admin2 = new User("admin2", "admin2", "adminpassword2");

    public UserManagement() {
        users.put("admin", admin);
        users.put("admin2", admin2);
    }

    public User login() {
        System.out.println("Geben sie ihre Email ein: ");
        String username = scanner.nextLine();
        String password = passwordEntryTest();

        if (users.containsKey(username)) {
            System.out.println(users.get(username).getName());
            if (users.get(username).getPassword().equals(password)) {
                System.out.println("Login erfolgreich");
                return users.get(username);
            }
        }
        System.out.println("Login fehlgeschlagen");
        return null;
    }

    public void register() {
        System.out.println("Geben sie ihre Email ein: ");
        String username = scanner.nextLine();
        String password = passwordEntryTest();
        System.out.println("Geben sie ihren Namen ein: ");
        String name = scanner.nextLine();
        if (!users.containsKey(username)) {
            users.put(username, new User(username, name, password));
            System.out.println("Registrierung erfolgreich");
        } else {
            System.out.println("Registrierung fehlgeschlagen: Email bereits vergeben");
        }
    }

    public void printUsers() {
        System.out.println("Registered Users: ");
        System.out.println(users);
        users.forEach((key, value) -> {
            System.out.print("E-mail: " + key + " Name: " + value.getName() + " \n");
        });
    }

    public String passwordEntry() {
        Console cons;
        char[] passwd = null;
        if ((cons = System.console()) != null &&
                (passwd = cons.readPassword("[%s]", "Password:")) != null) {
            java.util.Arrays.fill(passwd, ' ');
        } else {
            System.out.println("Konsole wird nicht unterstützt");
            System.exit(0);
        }

        return new String(passwd);

    }

    public String passwordEntryTest() {
        String password;
        Scanner scanner = new Scanner(System.in);
        password = scanner.nextLine();
        return password;
    }


}
