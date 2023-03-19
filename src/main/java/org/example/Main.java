import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
    public static void main(String[] args) {


        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        String formattedDate = formatter.format(date);
        System.out.println("Aktuelles Datum und Uhrzeit: " + formattedDate);


        Shop shop = new Shop();
        shop.startMenu();
    }
}