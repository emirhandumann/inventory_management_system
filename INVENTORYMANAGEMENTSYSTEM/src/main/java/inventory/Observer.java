package main.java.inventory;

public interface Observer {
    void update(String productName, int newStock); // Ürün adıbı ve yeni stok bilgisini burdan göndericeez
}
