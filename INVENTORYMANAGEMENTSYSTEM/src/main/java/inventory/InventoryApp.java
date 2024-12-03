package main.java.inventory;

import java.time.LocalDate;
import java.util.Scanner;

public class InventoryApp {
    public static void main(String[] args) {
        InventoryManager manager = InventoryManager.getInstance();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("----------------------------------------------------------------------");
            System.out.println("\nAPW is Akdeniz Pharmaceutical Warehouse management system.");
            System.out.println("----------------------------------------------------------------------");
            System.out.println("-=- Welcome to APW -=-");
            System.out.println("1- Warehouse Management Login");
            System.out.println("2- Pharmacy Login");
            System.out.println("3- Exit");
            System.out.print("Choose: ");
            int loginType = scanner.nextInt();
            scanner.nextLine();

            switch (loginType) {
                case 1 -> {
                    if (authenticate(scanner, "eczadeposu123")) {
                        showWarehouseMenu(manager, scanner);
                    } else {
                        System.out.println("Invalid Password! Returning to main menu...");
                    }
                }
                case 2 -> {
                    System.out.print("Enter your pharmacy name: ");
                    String pharmacyName = scanner.nextLine();

                    if (authenticate(scanner, "akdeniz123")) {
                        PharmacyObserver pharmacy = new PharmacyObserver(pharmacyName);
                        pharmacy.showMenu(manager);
                    } else {
                        System.out.println("Invalid Password! Returning to main menu...");
                    }
                }
                case 3 -> {
                    System.out.println("Exiting the system. Thank you for using APW!");
                    return;
                }
                default -> System.out.println("Invalid Choice! Try again, please.");
            }
        }
    }

    private static boolean authenticate(Scanner scanner, String correctPassword) {
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        return password.equals(correctPassword);
    }

    private static void showWarehouseMenu(InventoryManager manager, Scanner scanner) {
        int choice;
        do {
            System.out.println("\n=== Warehouse Management Menu ===");
            System.out.println("1- Add new product");
            System.out.println("2- Update stock");
            System.out.println("3- List all products");
            System.out.println("4- Remove a product");
            System.out.println("5- Check total stock by category");
            System.out.println("6- Show products by expiry date");
            System.out.println("7- Return to main menu");
            System.out.print("Your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> addNewProduct(manager, scanner);
                case 2 -> updateStock(manager, scanner);
                case 3 -> manager.listAllProducts();
                case 4 -> removeProduct(manager, scanner);
                case 5 -> checkStockByCategory(manager, scanner);
                case 6 -> showExpiringProducts(manager, scanner);
                case 7 -> System.out.println("Returning to main menu...");
                default -> System.out.println("Invalid Choice, try again please!");
            }
        } while (choice != 7);
    }

    private static void addNewProduct(InventoryManager manager, Scanner scanner) {
        System.out.print("Enter product name: ");
        String name = scanner.nextLine();

        System.out.print("Enter stock quantity: ");
        int stock = scanner.nextInt();

        System.out.print("Enter price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Enter category: ");
        String category = scanner.nextLine();

        System.out.print("Enter expiry date (YYYY-MM-DD): ");
        String expiryDate = scanner.nextLine();

        manager.addProduct(name, stock, price, category, LocalDate.parse(expiryDate));
    }

    private static void updateStock(InventoryManager manager, Scanner scanner) {
        System.out.print("Enter product name to update: ");
        String name = scanner.nextLine();

        System.out.print("Enter new stock quantity: ");
        int stock = scanner.nextInt();
        manager.updateStock(name, stock);
    }

    private static void removeProduct(InventoryManager manager, Scanner scanner) {
        System.out.print("Enter product name to remove: ");
        String productName = scanner.nextLine();

        if (manager.getProducts().remove(productName) != null) {
            System.out.println("Product '" + productName + "' removed from inventory.");
        } else {
            System.out.println("Product Not Found!");
        }
    }

    private static void checkStockByCategory(InventoryManager manager, Scanner scanner) {
        System.out.print("Enter category name: ");
        String category = scanner.nextLine();

        int totalStock = manager.getProducts().values().stream()
                .filter(product -> product.getCategory().equalsIgnoreCase(category))
                .mapToInt(ProductComponent::getStock)
                .sum();

        System.out.println("Total stock of '" + category + "' is  : " + totalStock);
    }

    private static void showExpiringProducts(InventoryManager manager, Scanner scanner) {
        System.out.print("Enter expiry date (YYYY-MM-DD): ");
        LocalDate expiryDate = LocalDate.parse(scanner.nextLine());

        System.out.println("-=- Products Expiring Before " + expiryDate + " -=-");
        manager.getProducts().values().stream()
                .filter(product -> product.getExpiryDate().isBefore(expiryDate))
                .forEach(product -> System.out.println("Name: " + product.getName() +
                        ", Expiry Date: " + product.getExpiryDate()));
    }

}
