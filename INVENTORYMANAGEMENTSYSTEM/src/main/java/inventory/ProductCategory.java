package main.java.inventory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

// Composite class for ProductCategory
public class ProductCategory implements ProductComponent {

    private String name;
    private List<ProductComponent> products = new ArrayList<>();

    public ProductCategory(String name) {
        this.name = name;
    }

    // Implementing methods from ProductComponent interface
    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getStock() {
        return products.stream().mapToInt(ProductComponent::getStock).sum();
    }

    @Override
    public double getPrice() {
        return 0; // Not applicable for category
    }

    @Override
    public String getCategory() {
        return name;
    }

    @Override
    public LocalDate getExpiryDate() {
        return null; // Not applicable for category
    }

    @Override
    public void add(ProductComponent productComponent) {
        products.add(productComponent);
    }

    @Override
    public void remove(ProductComponent productComponent) {
        products.remove(productComponent);
    }

    @Override
    public ProductComponent getChild(int i) {
        return products.get(i);
    }

    public List<ProductComponent> getProducts() {
        return products;
    }
}
