package main.java.inventory;

public class InventoryApp {
    public static void main(String[] args) {
        InventoryMenu menu = InventoryMenu.getInstance();
        menu.start();
    }
}
