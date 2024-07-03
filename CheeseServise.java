//gives access for the shop owner to add different cheeses
// (should remove this functionality from CheeseShop)


import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CheeseService {
    private List<Cheese> storeCheeses = new ArrayList<>();
    private String ownerUsername = "admin";
    private String ownerPassword = "password";
    private static final String STORE_FILE_PATH = "storeCheeses.json";

    public boolean validateOwner(String username, String password) {
        return ownerUsername.equals(username) && ownerPassword.equals(password);
    }

    public void addCheeseToStore(String name, double price) {
        storeCheeses.add(new Cheese(name, price));
        saveToFile();
    }

    public void removeCheeseFromStore(String name) {
        storeCheeses.removeIf(cheese -> cheese.getName().equals(name));
        saveToFile();
    }

    public List<Cheese> getAvailableCheeses() {
        return new ArrayList<>(storeCheeses);
    }

    public void saveToFile() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new File(STORE_FILE_PATH), storeCheeses);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadFromFile() {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File(STORE_FILE_PATH);
        if (file.exists()) {
            try {
                Cheese[] cheeses = mapper.readValue(file, Cheese[].class);
                storeCheeses = new ArrayList<>(List.of(cheeses));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
