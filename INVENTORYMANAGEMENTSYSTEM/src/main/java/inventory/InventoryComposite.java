package main.java.inventory;

import java.util.ArrayList;
import java.util.List;

// Composite class that extends InventoryComponent
public class InventoryComposite extends InventoryComponent {

    private List<InventoryComponent> components = new ArrayList<>();
    private String name;

    public InventoryComposite(String name) {
        this.name = name;
    }

    // Implementing the methods of the InventoryComponent interface
    @Override
    public void add(InventoryComponent component) {
        components.add(component);
    }

    @Override
    public void remove(InventoryComponent component) {
        components.remove(component);
    }

    @Override
    public InventoryComponent getChild(int i) {
        return components.get(i);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void display() {
        System.out.println("Composite: " + getName());
        for (InventoryComponent component : components) {
            component.display();
        }
    }
}
