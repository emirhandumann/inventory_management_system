package main.java.inventory;

// Observer interface
public interface Observable {

    void addObserver(Observer observer);

    void removeObserver(Observer observer);

    void notifyObservers(String productName, int newStock);
}
