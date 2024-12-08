package main.java.inventory;

// Abstract class for the composite pattern
public abstract class InventoryComponent {

    // Add a component to the inventory
    public void add(InventoryComponent component) {
        throw new UnsupportedOperationException();
    }

    // Remove a component from the inventory
    public void remove(InventoryComponent component) {
        throw new UnsupportedOperationException();
    }

    // Get a child component from the inventory
    public InventoryComponent getChild(int i) {
        throw new UnsupportedOperationException();
    }

    // Get the name of the component
    public String getName() {
        throw new UnsupportedOperationException();
    }

    // Get the price of the component
    public int getQuantity() {
        throw new UnsupportedOperationException();
    }

    // Get the quantity of the component
    public void display() {
        throw new UnsupportedOperationException();
    }
}
