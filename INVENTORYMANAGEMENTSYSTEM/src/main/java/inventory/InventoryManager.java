package main.java.inventory;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class InventoryManager {

    private static InventoryManager instance; // Singleton instance
    private Map<String, Product> products; // Store products in a Map

    // Private constructor for Singleton pattern
    private InventoryManager() {
        products = new HashMap<>();
    }

    // Public method to get the single instance of InventoryManager
    public static synchronized InventoryManager getInstance() {
        if (instance == null) {
            instance = new InventoryManager();
        }
        return instance;
    }

    // Add a new product to the inventory
    public void addProduct(String name, int stock, double price, String category, LocalDate expiryDate) {
        if (!products.containsKey(name)) {
            products.put(name, new Product(name, stock, price, category, expiryDate));
            System.out.println("Product added: " + name);
        } else {
            System.out.println("Product already exists!");
        }
    }

    // Update the stock quantity of an existing product
    public void updateStock(String name, int stock) {
        if (products.containsKey(name)) {
            products.get(name).setStock(stock);
            System.out.println("Stock updated for product: " + name);
        } else {
            System.out.println("Product not found!");
        }
    }

    // List all products in the inventory
    public void listAllProducts() {
        if (products.isEmpty()) {
            System.out.println("No products in inventory.");
        } else {
            System.out.println("Inventory:");
            for (Product product : products.values()) {
                System.out.println(" - " + product.getName() + ": " + product.getStock() + " units");
            }
        }
    }

    // List products with expiry date within a given number of days
    public void listExpiringProducts(int days) {
        LocalDate today = LocalDate.now();
        boolean found = false;

        for (Product product : products.values()) {
            if (product.getExpiryDate().isBefore(today.plusDays(days))) {
                System.out.println(" - " + product.getName() + ", Expiry Date: " + product.getExpiryDate());
                found = true;
            }
        }

        if (!found) {
            System.out.println("No products with approaching expiry dates.");
        }
    }
}
