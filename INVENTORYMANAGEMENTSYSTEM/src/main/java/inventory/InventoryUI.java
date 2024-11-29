package main.java.inventory;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.Map;
import java.util.stream.Collectors;

public class InventoryUI extends JFrame {

    private InventoryManager manager;

    public InventoryUI() {
        manager = InventoryManager.getInstance();
        setTitle("Pharmacy Inventory System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(9, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton addProductButton = new JButton("Add New Product");
        addProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addProduct();
            }
        });

        JButton updateStockButton = new JButton("Update Stock");
        updateStockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateStock();
            }
        });

        JButton getStockButton = new JButton("Get Stock of a Product");
        getStockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getStock();
            }
        });

        JButton listCategoriesButton = new JButton("List All Categories");
        listCategoriesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listAllCategories();
            }
        });

        JButton listProductsButton = new JButton("List All Products");
        listProductsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listAllProducts();
            }
        });

        JButton expiringProductsButton = new JButton("Show Expiring Products");
        expiringProductsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listExpiringProducts();
            }
        });

        JButton dashboardButton = new JButton("Show Dashboard");
        dashboardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new InventoryDashboard().setVisible(true);
            }
        });

        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        panel.add(addProductButton);
        panel.add(updateStockButton);
        panel.add(getStockButton);
        panel.add(listCategoriesButton);
        panel.add(listProductsButton);
        panel.add(expiringProductsButton);
        panel.add(dashboardButton);
        panel.add(exitButton);

        add(panel);
    }

    private void addProduct() {
        JTextField nameField = new JTextField();
        JTextField stockField = new JTextField();
        JTextField priceField = new JTextField();
        JTextField categoryField = new JTextField();
        JTextField expiryDateField = new JTextField();

        Object[] message = {
            "Product Name:", nameField,
            "Stock Quantity:", stockField,
            "Price:", priceField,
            "Category:", categoryField,
            "Expiry Date (YYYY-MM-DD):", expiryDateField
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Add New Product", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String name = nameField.getText();
            int stock = Integer.parseInt(stockField.getText());
            double price = Double.parseDouble(priceField.getText());
            String category = categoryField.getText();
            LocalDate expiryDate = LocalDate.parse(expiryDateField.getText());

            manager.addProduct(name, stock, price, category, expiryDate);
        }
    }

    private void updateStock() {
        JTextField nameField = new JTextField();
        JTextField stockField = new JTextField();

        Object[] message = {
            "Product Name:", nameField,
            "New Stock Quantity:", stockField
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Update Stock", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String name = nameField.getText();
            int stock = Integer.parseInt(stockField.getText());

            manager.updateStock(name, stock);
        }
    }

    private void getStock() {
        String name = JOptionPane.showInputDialog(this, "Enter Product Name:");
        if (name != null) {
            int stock = manager.getStock(name);
            JOptionPane.showMessageDialog(this, "Stock of " + name + ": " + stock);
        }
    }

    private void listAllCategories() {
        String[] columnNames = {"Category", "Total Stock"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        Map<String, ProductComponent> products = manager.getProducts();
        Map<String, Integer> categoryStocks = products.values().stream()
                .collect(Collectors.groupingBy(ProductComponent::getCategory,
                        Collectors.summingInt(ProductComponent::getStock)));
        for (Map.Entry<String, Integer> entry : categoryStocks.entrySet()) {
            model.addRow(new Object[]{entry.getKey(), entry.getValue()});
        }
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        JOptionPane.showMessageDialog(this, scrollPane, "Categories", JOptionPane.INFORMATION_MESSAGE);
    }

    private void listAllProducts() {
        String[] columnNames = {"Category", "Product Name", "Stock"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        Map<String, ProductComponent> products = manager.getProducts();
        products.values().stream()
            .sorted((p1, p2) -> {
                int categoryComparison = p1.getCategory().compareTo(p2.getCategory());
                if (categoryComparison != 0) {
                    return categoryComparison;
                }
                return p1.getName().compareTo(p2.getName());
            })
            .forEach(productComponent -> model.addRow(new Object[]{
                productComponent.getCategory(),
                productComponent.getName(),
                productComponent.getStock()
            }));
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        JOptionPane.showMessageDialog(this, scrollPane, "Products", JOptionPane.INFORMATION_MESSAGE);
    }

    private void listExpiringProducts() {
        String daysStr = JOptionPane.showInputDialog(this, "Enter number of days:");
        if (daysStr != null) {
            int days = Integer.parseInt(daysStr);
            String[] columnNames = {"Product Name", "Expiry Date"};
            DefaultTableModel model = new DefaultTableModel(columnNames, 0);
            Map<String, ProductComponent> products = manager.getProducts();
            LocalDate today = LocalDate.now();
            for (ProductComponent productComponent : products.values()) {
                if (productComponent.getExpiryDate().isBefore(today.plusDays(days))) {
                    model.addRow(new Object[]{productComponent.getName(), productComponent.getExpiryDate()});
                }
            }
            JTable table = new JTable(model);
            JScrollPane scrollPane = new JScrollPane(table);
            JOptionPane.showMessageDialog(this, scrollPane, "Expiring Products", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new InventoryUI().setVisible(true);
            }
        });
    }
}