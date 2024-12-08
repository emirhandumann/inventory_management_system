package main.java.inventory;

import java.time.LocalDate;

// Interface representing a product component in the inventory system.
public interface ProductComponent {

    // Methods to be implemented by the concrete classes
    String getName();

    int getStock();

    double getPrice();

    String getCategory();

    LocalDate getExpiryDate();

    void add(ProductComponent productComponent);

    void remove(ProductComponent productComponent);

    ProductComponent getChild(int i);
}
