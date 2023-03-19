import java.util.ArrayList;
import java.util.Scanner;
//import the UserManagement class

public class Shop {
    ArrayList<Product> allProducts = new ArrayList<Product>();

    public Shop(){
        UserManagement.admin.addProduct("Apfel", 1.5);
        allProducts.add(new Product("Apfel", 1.5, UserManagement.admin));

    }
    public void startMenu() {
        UserManagement userManagement = new UserManagement();
        Scanner scanner = new Scanner(System.in);
        User user;
        System.out.println("Willkommen bei der Shop-App!");
        System.out.println("1: Registrieren");
        System.out.println("2: Einloggen");
        System.out.println("3: Beenden");
        System.out.println("4: alle user ausgeben");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                userManagement.register();
                startMenu();
                break;
            case 2:
                user = userManagement.login();

                if (user == null) {
                    startMenu();
                }


                mainMenu(user);
                break;
            case 3:
                System.exit(0);
                break;
            case 4:
                userManagement.printUsers();
                startMenu();
                break;
            default:
                System.out.println("Ungültige Eingabe");
                startMenu();
                break;
        }
    }

    public void mainMenu(User user) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1: Produktverwaltung");
        System.out.println("2: Kontoverwaltung");
        System.out.println("3: Ausloggen und zum Startmenü zurückkehren");
        int input = scanner.nextInt();
        switch (input) {
            case 1:
                productMenu(user);
                break;
            case 2:
                accountManagement(user);
                break;
            case 3:
                startMenu();
                break;
            default:
                System.out.println("Ungültige Eingabe");
                mainMenu(user);
                break;
        }
    }

    public void productMenu(User user) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1: Alle Produkte auf dem Markt ausgeben");
        System.out.println("2: Meine gekauften Produkte ausgeben");
        System.out.println("3: Produkte suchen");
        System.out.println("4: Produkt hinzufügen");
        System.out.println("5: Produkt löschen");
        System.out.println("6: Produkt kaufen");
        System.out.println("7: Zurück zum Hauptmenü");
        int input = scanner.nextInt();

        switch (input) {
            case 1:
                System.out.println("Alle Produkte: ");
                printProducts(allProducts);
                productMenu(user);
                break;
            case 2:
                System.out.println("Meine Produkte:");
                System.out.println("Produkte zum verkaufen");
                printProducts(user.getProductsToSell());
                System.out.println("Nicht zu verkaufen");
                printProducts(user.getBoughtProducts());
                productMenu(user);
                break;
            case 3:
                System.out.println("Welches Produkt möchten Sie suchen?");
                String serachTerm = scanner.next();
                searchAll(serachTerm);
                productMenu(user);
                break;
            case 4:
                System.out.println("Name");
                String productName = scanner.next();
                System.out.println("Preis");
                double price = scanner.nextDouble();
                allProducts.add(new Product(productName, price, user));

                user.addProduct(productName, price);
                productMenu(user);
                break;
            case 5:
                String productToDelete = scanner.nextLine();
                user.deleteProduct(productToDelete);
                productMenu(user);
                break;
            case 6:
                System.out.println("Geben Sie den Index des Produkts ein?");
                String productToBuyName = scanner.next();
                Product productToBuy =  allProducts.get(Integer.parseInt(productToBuyName)-1);
                User seller = productToBuy.getOwner();
                System.out.println(productToBuy.getName());
                if(user.buy(productToBuy)){
                    seller.deleteProduct(productToBuy.getName());
                    seller.setBalance(seller.getBalance() + productToBuy.getPrice());
                    allProducts.remove(productToBuy);
                    System.out.println("Sie haben das Produkt gekauft");
                }else{
                    System.out.println("Kauf fehlgeschlagen");
                }

                productMenu(user);
                break;
            case 7:
                mainMenu(user);
                break;
            default:
                productMenu(user);
                break;
        }
    }

    public void accountManagement(User user) {
        Scanner scanner = new Scanner(System.in);
        int amount;
        System.out.println("1: Geld aufladen");
        System.out.println("2: Geld abheben");
        System.out.println("3: Kontoauszug");
        System.out.println("4: Zurück zum Menü");
        int input = scanner.nextInt();
        switch (input) {
            case 1:
                System.out.println("Wie viel möchten sie aufladen?");
                amount = scanner.nextInt();
                user.deposit(amount);
                 System.out.println("Sie haben nun " + user.getBalance() + "€ auf dem Konto");
                 accountManagement(user);
                break;
            case 2:
                System.out.println("Wie viel möchten sie abheben?");
                amount = scanner.nextInt();
                user.withdraw(amount);
                System.out.println("Sie haben nun " + user.getBalance() + "€ auf dem Konto");
                accountManagement(user);
                break;
            case 3:
                System.out.println("Ihr Kontostand liegt momentan bei " + user.getBalance() + "€");
                accountManagement(user);
                break;
            case 4:
                mainMenu(user);
                break;
        }
    }

    // gibt alle bereits vorhandenen Produkte aus
    public void addProduct(Product product) {
        allProducts.add(product);
    }

    public void printProducts(ArrayList<Product> products) {
        System.out.println(products.size() + " Produkte gefunden");

        for (Product product : products) {
            System.out.println("ID: " + product.getID() + " " + product.getName() + " - " + product.getPrice() + " € " + product.getOwner().getName());
        }
    }

    public void searchAll(String searchTerm) {
        ArrayList<Product> searchResults = new ArrayList<Product>();
        for (Product product : allProducts) {
            if (product.getName().contains(searchTerm)) {
                searchResults.add(product);
            }
        }
        if (searchResults.size() == 0) {
            System.out.println("Keine Produkte gefunden");
        }else {
            System.out.println("Es wurden " + searchResults.size() + " Produkte gefunden:");
            for (Product product : searchResults) {
                System.out.println(product.getName() + " - " + product.getPrice() + " € - " + product.getOwner().getName());
            }
        }

    }
}