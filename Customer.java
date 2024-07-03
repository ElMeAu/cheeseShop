//contains money the customer has, contains the items the customer owns.
// Whenever the customer buys something, money is reduced.
// If customer doesn't have any money left, then notify the user about it.
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Customer {
    private double money;
    private List<Cheese> ownedCheeses = new ArrayList<>();
    private static final String CUSTOMER_FILE_PATH = "customer.json";

    public Customer() {}

    public Customer(double money) {
        this.money = money;
    }

    public double getMoney() {
        return money;
    }

    public List<Cheese> getOwnedCheeses() {
        return new ArrayList<>(ownedCheeses);
    }

    public void deductMoney(double amount) {
        money -= amount;
        saveToFile();
    }

    public void addCheese(Cheese cheese) {
        ownedCheeses.add(cheese);
        saveToFile();
    }

    public void saveToFile() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new File(CUSTOMER_FILE_PATH), this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Customer loadFromFile() {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File(CUSTOMER_FILE_PATH);
        if (file.exists()) {
            try {
                return mapper.readValue(file, Customer.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new Customer(50.00);
    }
}
