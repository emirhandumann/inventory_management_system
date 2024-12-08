package main.java.inventory;

import java.util.Scanner;

// Observer class that implements the Observer interface
public class PharmacyObserver implements Observer {

    private String pharmacyName;

    public PharmacyObserver(String pharmacyName) {
        this.pharmacyName = pharmacyName;
    }

    // Implementing the update method of the Observer interface
    @Override
    public void update(String productName, int newStock) {
        System.out.println("Notification send to " + pharmacyName + ": Product '" + productName
                + "' is now in stock with quantity: " + newStock);
    }

    public void showMenu(InventoryManager manager) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n=== Pharmacy Menu ===");
            System.out.println("1. Check stock of a product");
            System.out.println("2. Order a product");
            System.out.println("3. Track a product");
            System.out.println("4. Return to main menu");
            System.out.print("Your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 ->
                    checkStock(manager, scanner);
                case 2 ->
                    orderProduct(manager, scanner);
                case 3 ->
                    trackProduct(manager, scanner);
                case 4 ->
                    System.out.println("Returning to main menu...");
                default ->
                    System.out.println("Invalid Choice, Try again, please!");
            }
        } while (choice != 4);
    }

    // Methods for the pharmacy operations
    private void checkStock(InventoryManager manager, Scanner scanner) {
        System.out.print("Enter product name: ");
        String productName = scanner.nextLine();
        int stock = manager.getStock(productName);
        if (stock >= 0) {
            System.out.println("Stock of " + productName + ": " + stock);
        } else {
            System.out.println("Product Not Found!");
        }
    }

    private void orderProduct(InventoryManager manager, Scanner scanner) {
        System.out.print("Enter product name: ");
        String productName = scanner.nextLine();

        System.out.print("Enter quantity to order: ");
        int quantity = scanner.nextInt();

        int currentStock = manager.getStock(productName);
        if (currentStock >= quantity) {
            manager.updateStock(productName, currentStock - quantity);
            System.out.println("The order is made for " + quantity + " units of " + productName);
        } else {
            System.out.println("Not enough stock for " + productName);
        }
    }

    private void trackProduct(InventoryManager manager, Scanner scanner) {
        System.out.print("Enter product name to track: ");
        String productName = scanner.nextLine();
        manager.addObserver(this);
        System.out.println(pharmacyName + " is now tracking " + productName);
    }
}
