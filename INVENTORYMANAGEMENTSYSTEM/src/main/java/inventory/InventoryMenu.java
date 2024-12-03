package main.java.inventory;

import java.time.LocalDate;
import java.util.Scanner;

public class InventoryMenu {

    private static InventoryMenu instance;
    private InventoryManager manager;

    private InventoryMenu() {
        this.manager = InventoryManager.getInstance();
    }

    public static InventoryMenu getInstance() {
        if (instance == null) {
            instance = new InventoryMenu();
        }
        return instance;
    }

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
            scanner.nextLine();

            switch (choice) {
                case 1 ->
                    addProduct(scanner);
                case 2 ->
                    updateStock(scanner);
                case 3 ->
                    getStock(scanner);
                case 4 ->
                    manager.listAllCategories();
                case 5 ->
                    manager.listAllProducts();
                case 6 ->
                    manager.listExpiringProducts(30);
                case 8 -> {
                    System.out.print("Enter product name to track: ");
                    String productName = scanner.nextLine();

                    System.out.print("Enter pharmacy name: ");
                    String pharmacyName = scanner.nextLine();

                    PharmacyObserver pharmacy = new PharmacyObserver(pharmacyName);
                    manager.addObserver(pharmacy);

                    System.out.println("Pharmacy '" + pharmacyName + "' is now tracking '" + productName + "'.");
                }
                case 7 ->
                    System.out.println("Exiting the system...");
                default ->
                    System.out.println("Invalid Choice, Please try again!");
            }
        } while (choice != 7);
    }

    private void addProduct(Scanner scanner) {
        System.out.print("Product name: ");
        String name = scanner.nextLine();

        System.out.print("Stock quantity: ");
        int stock = scanner.nextInt();

        System.out.print("Price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Category: ");
        String category = scanner.nextLine();

        System.out.print("Expiry date (YYYY-MM-DD): ");
        LocalDate expiryDate = LocalDate.parse(scanner.nextLine());

        manager.addProduct(name, stock, price, category, expiryDate);
    }

    private void updateStock(Scanner scanner) {
        System.out.print("Product name to update: ");
        String name = scanner.nextLine();

        System.out.print("New stock quantity: ");
        int stock = scanner.nextInt();

        manager.updateStock(name, stock);
    }

    private void getStock(Scanner scanner) {
        System.out.print("Product name: ");
        String name = scanner.nextLine();
        System.out.println("Stock of the product: " + manager.getStock(name));
    }
}
