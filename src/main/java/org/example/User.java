import java.util.ArrayList;

public class User {
    private final String username, name, password;
    private ArrayList<Product> productsToSell;
    private ArrayList<Product> boughtProducts = new ArrayList<Product>();
    private double balance;

    public User(String username, String name, String password) {
        this.name = name;
        this.username = username;
        this.password = password;
        productsToSell = new ArrayList<Product>();
        balance = 0;
    }

    public void addProduct(String name, double price) {
        Product newProduct = new Product(name, price, this);
        this.productsToSell.add(newProduct);
    }

    public void deleteProduct(String name) {
        ArrayList<Product> foundProducts = search(name, productsToSell);
        if (productsToSell.size() <= 0) {
            System.out.println("Keine Produkte vorhanden!");
        }
        if (foundProducts.size() <= 0) {
            System.out.println("Kein Produkt mit diesem Namen gefunden!");
        }
        productsToSell.removeAll(search(name, productsToSell));

    }


    public boolean buy(Product product) {
        if (this.balance >= product.getPrice()) {
            this.balance -= product.getPrice();
            this.boughtProducts.add(product);
            return true;
        } else {
            return false;
        }
    }

    public void withdraw(double amount) {
        if (balance < 0 || balance < amount) {
            System.out.println("Abheben felgeschlagen!");
        } else {
            System.out.println(amount + "€ wurden von Ihrem Konto abgehoben!");
            this.balance -= amount;
        }
    }

    public void deposit(double amount) {
        if (amount < 0) {
            System.out.println("Geldbetrag muss positiv sein!");
        } else {
            this.balance += amount;
        }
    }

    public double getBalance() {
        return this.balance;
    }
    public double setBalance(double balance) {
        return this.balance = balance;
    }

    public String getName() {
        return this.name;
    }

    public String getPassword() {
        return this.password;
    }

    public ArrayList<Product> getProductsToSell() {
        return this.productsToSell;
    }
    public ArrayList<Product> getBoughtProducts() {
        return this.boughtProducts;
    }

    public ArrayList<Product> search(String searchTerm, ArrayList<Product> products) {
        ArrayList<Product> searchResults = new ArrayList<Product>();
        for (Product product : products) {
            if (product.getName().contains(searchTerm)) {
                searchResults.add(product);
            }
        }
        return searchResults;
    }

}
