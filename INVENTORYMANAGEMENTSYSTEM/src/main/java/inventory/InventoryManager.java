package main.java.inventory;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class InventoryManager {

    private static InventoryManager instance;
    private Map<String, ProductComponent> products;
    private Map<String, InventoryComposite> categories;
    private List<Observer> observers = new ArrayList<>();

    private InventoryManager() {
        products = new HashMap<>();
        categories = new HashMap<>();
        SampleData.initializeSampleData(this);
    }

    public static synchronized InventoryManager getInstance() {
        if (instance == null) {
            instance = new InventoryManager();
        }
        return instance;
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers(String productName, int newStock) {
        for (Observer observer : observers) {
            observer.update(productName, newStock);
        }
    }

    public void addProduct(String name, int stock, double price, String category, LocalDate expiryDate) {
        if (!products.containsKey(name)) {
            products.put(name, new Product(name, stock, price, category, expiryDate));
            System.out.println("Product added: " + name);
        } else {
            System.out.println("Product already exists!");
        }
    }

    public void updateStock(String name, int stock) {
        if (products.containsKey(name)) {
            ((Product) products.get(name)).setStock(stock);

            System.out.println("Stock updated for product: " + name);

            notifyObservers(name, stock);
        } else {
            System.out.println("Product not found!");
        }
    }

    public int getStock(String name) {
        if (products.containsKey(name)) {
            return products.get(name).getStock();
        } else {
            return -1;
        }
    }

    public void listAllProducts() {
        if (products.isEmpty()) {
            System.out.println("No products in inventory.");
            return;
        }

        System.out.println("=== All Products in Inventory ===");
        for (ProductComponent productComponent : products.values()) {
            if (productComponent instanceof Product) {
                Product product = (Product) productComponent;
                System.out.println("Name: " + product.getName() +
                        ", Stock: " + product.getStock() +
                        ", Price: " + product.getPrice() +
                        ", Category: " + product.getCategory() +
                        ", Expiry Date: " + product.getExpiryDate());
            }
        }
    }

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
