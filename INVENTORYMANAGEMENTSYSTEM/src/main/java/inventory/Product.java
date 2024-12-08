package main.java.inventory;

import java.time.LocalDate;

// Product class implements ProductComponent interface
public class Product implements ProductComponent {

    private String name;
    private int stock;
    private double price;
    private String category;
    private LocalDate expiryDate;

    public Product(String name, int stock, double price, String category, LocalDate expiryDate) {
        this.name = name;
        this.stock = stock;
        this.price = price;
        this.category = category;
        this.expiryDate = expiryDate;
    }

    // Getters and setters for Product properties
    public String getName() {
        return name;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    // Overridden methods from ProductComponent interface
    @Override
    public void add(ProductComponent productComponent) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void remove(ProductComponent productComponent) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ProductComponent getChild(int i) {
        throw new UnsupportedOperationException();
    }
}
