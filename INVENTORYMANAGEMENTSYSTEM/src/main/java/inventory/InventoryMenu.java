package main.java.inventory;

import java.time.LocalDate;
import java.util.Scanner;

public class InventoryMenu {

    private InventoryManager manager;

    // Constructor initializes the InventoryManager instance
    public InventoryMenu() {
        this.manager = InventoryManager.getInstance();
    }

    // Start the terminal menu and handle user input
    public void start() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n=== Pharmacy Inventory System ===");
            System.out.println("1. Add new product");
            System.out.println("2. Update stock");
            System.out.println("3. Return stock of a product");
            System.out.println("4. List all categories");
            System.out.println("5. List all products");
            System.out.println("6. Show expiring products");
            System.out.println("7. Exit");
            System.out.print("Your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Clear the buffer

            switch (choice) {
                case 1 ->
                    addProduct(scanner); // Add a new product
                case 2 ->
                    updateStock(scanner); // Update stock of an existing product
                case 3 ->
                    getStock(scanner); // Get stock of a product
                case 4 ->
                    manager.listAllCategories(); // List all categories
                case 5 ->
                    manager.listAllProducts(); // List all products
                case 6 ->
                    manager.listExpiringProducts(30); // Show products expiring in the next 30 days
                case 7 ->
                    System.out.println("Exiting the system...");
                default ->
                    System.out.println("Invalid choice, please try again!");
            }
        } while (choice != 7); // Repeat until user chooses to exit
    }

    // Method to add a new product
    private void addProduct(Scanner scanner) {
        System.out.print("Product name: ");
        String name = scanner.nextLine();

        System.out.print("Stock quantity: ");
        int stock = scanner.nextInt();

        System.out.print("Price: ");
        double price = scanner.nextDouble();
        scanner.nextLine(); // Clear the buffer

        System.out.print("Category: ");
        String category = scanner.nextLine();

        System.out.print("Expiry date (YYYY-MM-DD): ");
        LocalDate expiryDate = LocalDate.parse(scanner.nextLine());

        manager.addProduct(name, stock, price, category, expiryDate);
    }

    // Method to update stock of an existing product
    private void updateStock(Scanner scanner) {
        System.out.print("Product name to update: ");
        String name = scanner.nextLine();

        System.out.print("New stock quantity: ");
        int stock = scanner.nextInt();

        manager.updateStock(name, stock);
    }

    // Method to get stock of a product
    private void getStock(Scanner scanner) {
        System.out.print("Product name: ");
        String name = scanner.nextLine();
        System.out.println("Stock of the product: " + manager.getStock(name));
    }
}
