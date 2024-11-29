package main.java.inventory;

import java.time.LocalDate;

/**
 * Interface representing a product component in the inventory system.
 */
public interface ProductComponent {
    /**
     * Gets the name of the product.
     * @return the name of the product.
     */
    String getName();

    /**
     * Gets the stock quantity of the product.
     * @return the stock quantity of the product.
     */
    int getStock();

    /**
     * Gets the price of the product.
     * @return the price of the product.
     */
    double getPrice();

    /**
     * Gets the category of the product.
     * @return the category of the product.
     */
    String getCategory();

    /**
     * Gets the expiry date of the product.
     * @return the expiry date of the product.
     */
    LocalDate getExpiryDate();

    /**
     * Adds a product component.
     * @param productComponent the product component to add.
     */
    void add(ProductComponent productComponent);

    /**
     * Removes a product component.
     * @param productComponent the product component to remove.
     */
    void remove(ProductComponent productComponent);

    /**
     * Gets a child product component.
     * @param i the index of the child product component.
     * @return the child product component.
     */
    ProductComponent getChild(int i);
}