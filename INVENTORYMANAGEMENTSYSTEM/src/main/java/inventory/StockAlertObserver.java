package main.java.inventory;

// Observer class for stock alerts
public class StockAlertObserver implements Observer {

    private int threshold;

    public StockAlertObserver(int threshold) {
        this.threshold = threshold;
    }

    @Override
    public void update(String productName, int newStock) {
        if (newStock < threshold) {
            System.out.println("ALERT!!!: Stock for " + productName + " is below the threshold (" + newStock + ").");
        }
    }
}
