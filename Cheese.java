import javax.xml.namespace.QName;

public class Cheese {
    private String name;
    private double price;

    public Cheese() {}

    public Cheese(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }


}
