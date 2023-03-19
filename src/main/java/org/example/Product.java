public class Product {
    private String name;
    private double price;
    private User owner;
    private static int count = 0;
    private int id;

    public Product(String name, double price, User owner) {
        this.name = name;
        this.price = price;
        this.owner = owner;
        id = count;
        count++;
    }
    public Product(){}

    public User getOwner(){
        return this.owner;
    }
    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getName() {
        return this.name;
    }
    public double getPrice() {
        return this.price;
    }
    public int getID() {return this.id;}
}