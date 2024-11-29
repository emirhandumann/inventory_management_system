
package main.java.inventory;

public class InventoryItem extends InventoryComponent {
    private String name;
    private int quantity;

    public InventoryItem(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getQuantity() {
        return quantity;
    }

    @Override
    public void display() {
        System.out.println("Item: " + getName() + ", Quantity: " + getQuantity());
    }
}