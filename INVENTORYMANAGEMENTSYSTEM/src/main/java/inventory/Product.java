package main.java.inventory;

import java.time.LocalDate;

public class Product {

    private String name;
    private int stock;
    private double price;
    private String category;
    private LocalDate expiryDate;

    // Constructor for Product class
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
}
