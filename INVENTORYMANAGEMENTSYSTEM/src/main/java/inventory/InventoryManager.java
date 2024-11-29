package main.java.inventory;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.*;

public class InventoryManager {

    private static InventoryManager instance; // Singleton instance
    private Map<String, ProductComponent> products; // Store products in a Map
    private Map<String, InventoryComposite> categories;

    // Private constructor for Singleton pattern
    private InventoryManager() {
        products = new HashMap<>();
        categories = new HashMap<>();
        SampleData.initializeSampleData(this);
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
            ((Product) products.get(name)).setStock(stock);
            System.out.println("Stock updated for product: " + name);
        } else {
            System.out.println("Product not found!");
        }
    }

    // Get the stock quantity of a product
    public int getStock(String name) {
        if (products.containsKey(name)) {
            return products.get(name).getStock();
        } else {
            return -1;
        }
    }

    // List all products in the inventory
    public void listAllProducts() {
        List<String> sortedCategories = new ArrayList<>(categories.keySet());
        Collections.sort(sortedCategories);
        for (String category : sortedCategories) {
            System.out.println("Category: " + category);
            InventoryComposite composite = categories.get(category);
            composite.display();
        }
    }

    // List all categories in the inventory
    public void listAllCategories() {
        if (products.isEmpty()) {
            System.out.println("No products in inventory.");
        } else {
            for (ProductComponent productComponent : products.values()) {
                if (productComponent instanceof Product) {
                    Product product = (Product) productComponent;
                    System.out.println(" - " + product.getCategory());
                }
            }
        }
    }

    // List products with expiry date within a given number of days
    public void listExpiringProducts(int days) {
        LocalDate today = LocalDate.now();
        boolean found = false;

        for (ProductComponent productComponent : products.values()) {
            if (productComponent instanceof Product) {
                Product product = (Product) productComponent;
                if (product.getExpiryDate().isBefore(today.plusDays(days))) {
                    System.out.println(" - " + product.getName() + ", Expiry Date: " + product.getExpiryDate());
                    found = true;
                }
            }
        }

        if (!found) {
            System.out.println("No products with approaching expiry dates.");
        }
    }

    // Method to get all products
    public Map<String, ProductComponent> getProducts() {
        return products;
    }

    public Map<String, Integer> getCategoryStockByDate(LocalDate date) {
        Map<String, Integer> categoryStocks = products.values().stream()
                .filter(product -> !product.getExpiryDate().isBefore(date))
                .collect(Collectors.groupingBy(ProductComponent::getCategory,
                        Collectors.summingInt(ProductComponent::getStock)));
        return categoryStocks;
    }

}
