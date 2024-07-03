//User UI, accesses CheeseService and CheeseShop to buy or to add different cheeses.
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        CheeseService cheeseService = new CheeseService();
        cheeseService.loadFromFile();
        Customer customer = Customer.loadFromFile();
        CheeseShop shop = new CheeseShop(cheeseService);
        Scanner scanner = new Scanner(System.in);
        String command;

        System.out.println("Welcome to the Cheese Shop!");

        while (true) {
            System.out.println("Commands: add, remove, buy, cart, store, " +
                    " remove from cart, see all cheese I own, exit");
            System.out.print("Enter command: ");
            command = scanner.nextLine();

            switch (command.toLowerCase()) {
                case "add":
                case "remove":
                    if (validateOwner(scanner, cheeseService)) {
                        if (command.equals("add")) {
                            System.out.print("Enter cheese name: ");
                            String addName = scanner.nextLine();
                            System.out.print("Enter cheese price: ");
                            double addPrice = Double.parseDouble(scanner.nextLine());
                            cheeseService.addCheeseToStore(addName, addPrice);
                            System.out.println("Cheese added.");
                        } else {
                            System.out.print("Enter cheese name to remove: ");
                            String removeName = scanner.nextLine();
                            cheeseService.removeCheeseFromStore(removeName);
                            System.out.println("Cheese removed.");
                        }
                    } else {
                        System.out.println("Invalid owner credentials.");
                    }
                    break;
                case "buy":
                    System.out.print("Enter cheese name to buy: ");
                    String buyName = scanner.nextLine();
                    shop.buyCheese(buyName, customer);
                    break;
                case "cart":
                    System.out.println("Cheeses in cart: " + shop.getCheesesInCart());
                    break;
                case "store":
                    System.out.println("Available cheeses in store: " + shop.getAvailableCheeses());
                    break;
                case "remove from cart":
                    System.out.print("Enter cheese name to remove: ");
                    String removeCheese = scanner.nextLine();
                    shop.removeCheeseFromCart(removeCheese);
                    System.out.println("Cheese removed.");
                    break;
                case "see all cheese i own":
                    System.out.println("Cheeses owned by customer: " + customer.getOwnedCheeses());
                    break;
                case "exit":
                    System.out.println("Saving data and exiting...");
                    cheeseService.saveToFile();
                    customer.saveToFile();
                    scanner.close();
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Unknown command.");
                    break;
            }
        }
    }

    private static boolean validateOwner(Scanner scanner, CheeseService cheeseService) {
        System.out.print("Enter owner username: ");
        String username = scanner.nextLine();
        System.out.print("Enter owner password: ");
        String password = scanner.nextLine();
        return cheeseService.validateOwner(username, password);
    }
}
