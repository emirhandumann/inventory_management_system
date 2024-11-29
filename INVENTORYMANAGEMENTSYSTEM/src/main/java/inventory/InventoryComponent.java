
package main.java.inventory;

public abstract class InventoryComponent {
    public void add(InventoryComponent component) {
        throw new UnsupportedOperationException();
    }

    public void remove(InventoryComponent component) {
        throw new UnsupportedOperationException();
    }

    public InventoryComponent getChild(int i) {
        throw new UnsupportedOperationException();
    }

    public String getName() {
        throw new UnsupportedOperationException();
    }

    public int getQuantity() {
        throw new UnsupportedOperationException();
    }

    public void display() {
        throw new UnsupportedOperationException();
    }
}