//gives access for the shop owner to add/remove different cheeses,
// gives access for the customer to buy different cheeses,
// remove cheese from cart,
// should be possible to get all the cheeses from the cart
// or available cheeses in the store

import java.util.ArrayList;
import java.util.List;

public class CheeseShop {
    private List<Cheese> cart = new ArrayList<>();
    private CheeseService cheeseService;

    public CheeseShop(CheeseService cheeseService) {
        this.cheeseService = cheeseService;
    }

    public void buyCheese(String name, Customer customer) {
        List<Cheese> storeCheeses = cheeseService.getAvailableCheeses();
        for (Cheese cheese : storeCheeses) {
            if (cheese.getName().equals(name)) {
                if (customer.getMoney() >= cheese.getPrice()) {
                    cart.add(cheese);
                    customer.deductMoney(cheese.getPrice());
                    customer.addCheese(cheese);
                    System.out.println("Cheese added to cart.");
                } else {
                    System.out.println("Not enough money to buy this cheese.");
                }
                return;
            }
        }
        System.out.println("Cheese not available in the store.");
    }

    public void removeCheeseFromCart(String name) {
        cart.removeIf(cheese -> cheese.getName().equals(name));
    }

    public List<Cheese> getCheesesInCart() {
        return new ArrayList<>(cart);
    }

    public List<Cheese> getAvailableCheeses() {
        return cheeseService.getAvailableCheeses();
    }
}
