package main.java.inventory;

import java.awt.*;
import java.time.LocalDate;
import java.util.Vector;
import java.util.stream.Collectors;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

// Application class for the Inventory Management System
public class InventoryApp extends JFrame {

    private InventoryManager manager;
    private JTabbedPane tabbedPane;
    private JPasswordField passwordField;
    private JTextField pharmacyNameField;

    public InventoryApp() {
        manager = InventoryManager.getInstance();
        initializeGUI();
    }

    // Method to initialize the GUI components
    private void initializeGUI() {

        setTitle("APW - Akdeniz Pharmaceutical Warehouse");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Login Panel
        JPanel loginPanel = createLoginPanel();
        add(loginPanel);
    }

    // Method to create the login panel
    private JPanel createLoginPanel() {

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel titleLabel = new JLabel("APW Inventory Management System");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 32));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(titleLabel, gbc);

        JLabel loginTypeLabel = new JLabel("Select Login Type:");
        loginTypeLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(loginTypeLabel, gbc);

        String[] loginTypes = {"Warehouse", "Pharmacy"};
        JComboBox<String> loginTypeCombo = new JComboBox<>(loginTypes);
        loginTypeCombo.setFont(new Font("Arial", Font.PLAIN, 24));
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(loginTypeCombo, gbc);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(passwordLabel, gbc);

        passwordField = new JPasswordField(25);
        passwordField.setFont(new Font("Arial", Font.PLAIN, 24));
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(passwordField, gbc);

        JLabel pharmacyLabel = new JLabel("Pharmacy Name:");
        pharmacyLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        gbc.gridx = 0;
        gbc.gridy = 3;
        pharmacyLabel.setVisible(false);
        panel.add(pharmacyLabel, gbc);

        pharmacyNameField = new JTextField(15);
        pharmacyNameField.setFont(new Font("Arial", Font.PLAIN, 24));
        gbc.gridx = 1;
        gbc.gridy = 3;
        pharmacyNameField.setVisible(false);
        panel.add(pharmacyNameField, gbc);

        loginTypeCombo.addActionListener(e -> {
            boolean isPharmacy = loginTypeCombo.getSelectedItem().equals("Pharmacy");
            pharmacyLabel.setVisible(isPharmacy);
            pharmacyNameField.setVisible(isPharmacy);
        });

        JButton loginButton = new JButton("Login");
        loginButton.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        panel.add(loginButton, gbc);

        loginButton.addActionListener(e -> {
            String loginType = (String) loginTypeCombo.getSelectedItem();
            String password = new String(passwordField.getPassword());

            if (loginType.equals("Warehouse")) {
                if (password.equals("eczadeposu123")) {
                    openWarehouseMenu();
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid Warehouse Password!");
                }
            } else {
                String pharmacyName = pharmacyNameField.getText();
                if (password.equals("akdeniz123")) {
                    openPharmacyMenu(pharmacyName);
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid Pharmacy Password!");
                }
            }
        });

        return panel;
    }

    // Method to create the Warehouse Menu
    private void openWarehouseMenu() {
        getContentPane().removeAll();
        tabbedPane = new JTabbedPane();

        // Add Product Tab
        JPanel addProductPanel = createAddProductPanel();
        tabbedPane.addTab("Add Product", addProductPanel);

        // Update Stock Tab
        JPanel updateStockPanel = createUpdateStockPanel();
        tabbedPane.addTab("Update Stock", updateStockPanel);

        // List Products Tab
        JPanel listProductsPanel = createListProductsPanel();
        tabbedPane.addTab("List Products", listProductsPanel);

        // Remove Product Tab
        JPanel removeProductPanel = createRemoveProductPanel();
        tabbedPane.addTab("Remove Product", removeProductPanel);

        // Stock by Category Tab
        JPanel stockByCategoryPanel = createStockByCategoryPanel();
        tabbedPane.addTab("Stock by Category", stockByCategoryPanel);

        // Stock by Product
        JPanel stockByProductPanel = createStockByProductPanel();
        tabbedPane.addTab("Stock by Product", stockByProductPanel);

        // Expiring Products Tab
        JPanel expiringProductsPanel = createExpiringProductsPanel();
        tabbedPane.addTab("Expiring Products", expiringProductsPanel);

        // Logout Button
        JButton logoutButton = new JButton("Logout");
        logoutButton.setFont(new Font("Segoe UI", Font.BOLD, 24));
        logoutButton.setPreferredSize(new Dimension(200, 35));
        logoutButton.addActionListener(e -> {
            getContentPane().removeAll();
            JPanel loginPanel = createLoginPanel();
            add(loginPanel);
            revalidate();
            repaint();
        });

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(tabbedPane, BorderLayout.CENTER);

        JPanel logoutPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        logoutPanel.add(logoutButton);
        mainPanel.add(logoutPanel, BorderLayout.SOUTH);

        add(mainPanel);
        revalidate();
        repaint();
    }

    // Method to create the Add Product panel
    private JPanel createAddProductPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        String[] labels = {"Product Name:", "Stock Quantity:", "Price:", "Category:", "Expiry Date (YYYY-MM-DD):"};
        JTextField[] fields = new JTextField[5];

        for (int i = 0; i < labels.length; i++) {
            gbc.gridx = 0;
            gbc.gridy = i;
            gbc.gridwidth = 1;

            JLabel label = new JLabel(labels[i]);
            label.setFont(new Font("Arial", Font.BOLD, 24));
            panel.add(label, gbc);

            gbc.gridx = 1;
            gbc.gridwidth = 2;

            fields[i] = new JTextField();
            fields[i].setPreferredSize(new Dimension(250, 30));
            fields[i].setFont(new Font("Segoe UI", Font.PLAIN, 24));
            panel.add(fields[i], gbc);
        }

        JButton addButton = new JButton("Add Product");
        addButton.setFont(new Font("Segoe UI", Font.BOLD, 24));
        addButton.setPreferredSize(new Dimension(150, 35));

        gbc.gridx = 1;
        gbc.gridy = labels.length;
        gbc.gridwidth = 1;
        panel.add(addButton, gbc);

        //variables for fields
        JTextField nameField = fields[0];
        JTextField stockField = fields[1];
        JTextField priceField = fields[2];
        JTextField categoryField = fields[3];
        JTextField expiryField = fields[4];

        addButton.addActionListener(e -> {
            try {
                String name = nameField.getText();
                int stock = Integer.parseInt(stockField.getText());
                double price = Double.parseDouble(priceField.getText());
                String category = categoryField.getText();
                LocalDate expiryDate = LocalDate.parse(expiryField.getText());

                manager.addProduct(name, stock, price, category, expiryDate);
                JOptionPane.showMessageDialog(this, "Product added successfully!");

                // clear fields after adding product
                nameField.setText("");
                stockField.setText("");
                priceField.setText("");
                categoryField.setText("");
                expiryField.setText("");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });

        return panel;
    }

    // Method to create the Update Stock panel
    private JPanel createUpdateStockPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        // Product Name
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel nameLabel = new JLabel("Product Name:");
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        panel.add(nameLabel, gbc);

        gbc.gridx = 1;
        JTextField nameField = new JTextField();
        nameField.setPreferredSize(new Dimension(250, 30));
        nameField.setFont(new Font("Segoe UI", Font.PLAIN, 24));
        panel.add(nameField, gbc);

        // New Stock Quantity
        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel quantityLabel = new JLabel("New Stock Quantity:");
        quantityLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        panel.add(quantityLabel, gbc);

        gbc.gridx = 1;
        JTextField stockField = new JTextField();
        stockField.setPreferredSize(new Dimension(250, 30));
        stockField.setFont(new Font("Segoe UI", Font.PLAIN, 24));
        panel.add(stockField, gbc);

        // Update Button
        JButton updateButton = new JButton("Update Stock");
        updateButton.setFont(new Font("Segoe UI", Font.BOLD, 24));
        updateButton.setPreferredSize(new Dimension(150, 35));

        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(updateButton, gbc);

        updateButton.addActionListener(e -> {
            try {
                String name = nameField.getText();
                int stock = Integer.parseInt(stockField.getText());
                manager.updateStock(name, stock);
                JOptionPane.showMessageDialog(this, "Stock updated successfully!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });

        return panel;
    }

    // Method to create the List Products panel
    private JPanel createListProductsPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        Vector<String> columnNames = new Vector<>();
        columnNames.add("Name");
        columnNames.add("Stock");
        columnNames.add("Price");
        columnNames.add("Category");
        columnNames.add("Expiry Date");
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(model);

        table.setFont(new Font("Arial", Font.PLAIN, 24));
        table.getTableHeader().setFont(new Font("Arial", Font.PLAIN, 24));
        table.setRowHeight(25);

        JButton refreshButton = new JButton("Refresh Products");
        refreshButton.setFont(new Font("Arial", Font.PLAIN, 24));
        refreshButton.setPreferredSize(new Dimension(150, 35));
        refreshButton.addActionListener(e -> {
            model.setRowCount(0);
            manager.getProducts().values().forEach(product -> {
                Vector<Object> row = new Vector<>();
                row.add(product.getName());
                row.add(product.getStock());
                row.add(product.getPrice());
                row.add(product.getCategory());
                row.add(product.getExpiryDate());
                model.addRow(row);
            });
        });

        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        panel.add(refreshButton, BorderLayout.SOUTH);

        return panel;
    }

    // Method to create the Remove Product panel
    private JPanel createRemoveProductPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        // Product Name
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel nameLabel = new JLabel("Product Name:");
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        panel.add(nameLabel, gbc);

        gbc.gridx = 1;
        JTextField nameField = new JTextField();
        nameField.setPreferredSize(new Dimension(250, 30));
        nameField.setFont(new Font("Arial", Font.PLAIN, 24));
        panel.add(nameField, gbc);

        // Remove Button
        JButton removeButton = new JButton("Remove Product");
        removeButton.setFont(new Font("Arial", Font.PLAIN, 24));
        removeButton.setPreferredSize(new Dimension(150, 35));

        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(removeButton, gbc);

        removeButton.addActionListener(e -> {
            String productName = nameField.getText();
            if (manager.getProducts().remove(productName) != null) {
                JOptionPane.showMessageDialog(this, "Product '" + productName + "' removed successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Product Not Found!");
            }
        });

        return panel;
    }

    // Method to create the Stock by Category panel
    private JPanel createStockByCategoryPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        // Category Name
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel catNameLabel = new JLabel("Category Name:");
        catNameLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        panel.add(catNameLabel, gbc);

        gbc.gridx = 1;
        JTextField categoryField = new JTextField();
        categoryField.setPreferredSize(new Dimension(250, 30));
        categoryField.setFont(new Font("Arial", Font.PLAIN, 24));
        panel.add(categoryField, gbc);

        // Result Area
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        JTextArea resultArea = new JTextArea();
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Arial", Font.PLAIN, 24));
        JScrollPane scrollPane = new JScrollPane(resultArea);
        scrollPane.setPreferredSize(new Dimension(250, 100));
        panel.add(scrollPane, gbc);

        // Check Button
        JButton checkButton = new JButton("Check Stock");
        checkButton.setFont(new Font("Arial", Font.PLAIN, 24));
        checkButton.setPreferredSize(new Dimension(150, 35));

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        panel.add(checkButton, gbc);

        checkButton.addActionListener(e -> {
            String category = categoryField.getText();
            int totalStock = manager.getProducts().values().stream()
                    .filter(product -> product.getCategory().equalsIgnoreCase(category))
                    .mapToInt(ProductComponent::getStock)
                    .sum();

            resultArea.setText("Total stock of '" + category + "' is: " + totalStock);
        });

        return panel;
    }

    // Method to create the Stock by Product panel
    private JPanel createStockByProductPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        // Product Name
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel nameLabel = new JLabel("Product Name:");
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        panel.add(nameLabel, gbc);

        gbc.gridx = 1;
        JTextField productNameField = new JTextField();
        productNameField.setPreferredSize(new Dimension(250, 30));
        productNameField.setFont(new Font("Arial", Font.PLAIN, 24));
        panel.add(productNameField, gbc);

        // Result Area
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        JTextArea resultArea = new JTextArea();
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Arial", Font.PLAIN, 24));
        JScrollPane scrollPane = new JScrollPane(resultArea);
        scrollPane.setPreferredSize(new Dimension(250, 100));
        panel.add(scrollPane, gbc);

        // Check Button
        JButton checkButton = new JButton("Check Stock");
        checkButton.setFont(new Font("Arial", Font.PLAIN, 24));
        checkButton.setPreferredSize(new Dimension(150, 35));

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        panel.add(checkButton, gbc);

        checkButton.addActionListener(e -> {
            String productName = productNameField.getText();
            ProductComponent product = manager.getProducts().get(productName);
            if (product != null) {
                resultArea.setText("Stock for '" + product.getName() + "': " + product.getStock());
            } else {
                resultArea.setText("Product '" + productName + "' not found.");
            }
        });

        return panel;
    }

    // Method to create the Expiring Products panel
    private JPanel createExpiringProductsPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        // Expiry Date
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel expiryLabel = new JLabel("Expiry Date (YYYY-MM-DD):");
        expiryLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        panel.add(expiryLabel, gbc);

        gbc.gridx = 1;
        JTextField expiryField = new JTextField();
        expiryField.setPreferredSize(new Dimension(250, 30));
        expiryField.setFont(new Font("Arial", Font.PLAIN, 24));
        panel.add(expiryField, gbc);

        // Result Area
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        JTextArea resultArea = new JTextArea();
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Arial", Font.PLAIN, 24));
        JScrollPane scrollPane = new JScrollPane(resultArea);
        scrollPane.setPreferredSize(new Dimension(250, 200));
        panel.add(scrollPane, gbc);

        // Check Button
        JButton checkButton = new JButton("Show Expiring Products");
        checkButton.setFont(new Font("Arial", Font.PLAIN, 24));
        checkButton.setPreferredSize(new Dimension(200, 35));

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        panel.add(checkButton, gbc);

        checkButton.addActionListener(e -> {
            try {
                LocalDate expiryDate = LocalDate.parse(expiryField.getText());
                java.util.List<String> expiringProducts = manager.getProducts().values().stream()
                        .filter(product -> product.getExpiryDate().isBefore(expiryDate))
                        .map(product -> "Name: " + product.getName() + ", Expiry Date: " + product.getExpiryDate())
                        .collect(Collectors.toList());

                resultArea.setText(String.join("\n", expiringProducts));
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid date format!");
            }
        });

        return panel;
    }

    // Method to create the Pharmacy Menu
    private void openPharmacyMenu(String pharmacyName) {
        getContentPane().removeAll();
        tabbedPane = new JTabbedPane();

        PharmacyObserver pharmacy = new PharmacyObserver(pharmacyName);

        // Check Stock Tab
        JPanel checkStockPanel = createCheckStockPanel(pharmacy);
        tabbedPane.addTab("Check Stock", checkStockPanel);

        // Order Product Tab
        JPanel orderProductPanel = createOrderProductPanel(pharmacy);
        tabbedPane.addTab("Order Product", orderProductPanel);

        // Track Product Tab
        JPanel trackProductPanel = createTrackProductPanel(pharmacy, pharmacyName);
        tabbedPane.addTab("Track Product", trackProductPanel);

        // Logout Button
        JButton logoutButton = new JButton("Logout");
        logoutButton.setFont(new Font("Arial", Font.PLAIN, 24));
        logoutButton.setPreferredSize(new Dimension(200, 35));
        logoutButton.addActionListener(e -> {
            getContentPane().removeAll();
            JPanel loginPanel = createLoginPanel();
            add(loginPanel);
            revalidate();
            repaint();
        });

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(tabbedPane, BorderLayout.CENTER);

        JPanel logoutPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        logoutPanel.add(logoutButton);
        mainPanel.add(logoutPanel, BorderLayout.SOUTH);

        add(mainPanel);
        revalidate();
        repaint();
    }

    // Method to create the Check Stock panel
    private JPanel createCheckStockPanel(PharmacyObserver pharmacy) {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        // Product Name
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel nameLabel = new JLabel("Product Name:");
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        panel.add(nameLabel, gbc);

        gbc.gridx = 1;
        JTextField productNameField = new JTextField();
        productNameField.setPreferredSize(new Dimension(250, 30));
        productNameField.setFont(new Font("Arial", Font.PLAIN, 24));
        panel.add(productNameField, gbc);

        // Check Stock Button
        JButton checkStockButton = new JButton("Check Stock");
        checkStockButton.setFont(new Font("Arial", Font.PLAIN, 24));
        checkStockButton.setPreferredSize(new Dimension(150, 35));

        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(checkStockButton, gbc);

        checkStockButton.addActionListener(e -> {
            String productName = productNameField.getText();
            int stock = manager.getStock(productName);
            String message = (stock >= 0)
                    ? "Stock of " + productName + ": " + stock
                    : "Product not found!";
            JOptionPane.showMessageDialog(this, message);
        });

        return panel;
    }

    // Method to create the Order Product panel
    private JPanel createOrderProductPanel(PharmacyObserver pharmacy) {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        // Product Name
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel nameLabel = new JLabel("Product Name:");
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        panel.add(nameLabel, gbc);

        gbc.gridx = 1;
        JTextField productNameField = new JTextField();
        productNameField.setPreferredSize(new Dimension(250, 30));
        productNameField.setFont(new Font("Arial", Font.PLAIN, 24));
        panel.add(productNameField, gbc);

        // Quantity
        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel quantityLabel = new JLabel("Quantity:");
        quantityLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        panel.add(quantityLabel, gbc);

        gbc.gridx = 1;
        JTextField quantityField = new JTextField();
        quantityField.setPreferredSize(new Dimension(250, 30));
        quantityField.setFont(new Font("Arial", Font.PLAIN, 24));
        panel.add(quantityField, gbc);

        // Order Button
        JButton orderButton = new JButton("Order Product");
        orderButton.setFont(new Font("Arial", Font.PLAIN, 24));
        orderButton.setPreferredSize(new Dimension(150, 35));

        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(orderButton, gbc);

        orderButton.addActionListener(e -> {
            try {
                String productName = productNameField.getText();
                int quantity = Integer.parseInt(quantityField.getText());

                int currentStock = manager.getStock(productName);
                if (currentStock >= quantity) {
                    manager.updateStock(productName, currentStock - quantity);
                    JOptionPane.showMessageDialog(this, "Order placed for " + quantity + " units of " + productName);

                    // Clear fields after successful order
                    productNameField.setText("");
                    quantityField.setText("");
                } else {
                    JOptionPane.showMessageDialog(this, "Not enough stock for " + productName);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });

        return panel;
    }

    // Method to create the Track Product panel
    private JPanel createTrackProductPanel(PharmacyObserver pharmacy, String pharmacyName) {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        // Product Name
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel nameLabel = new JLabel("Product Name:");
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        panel.add(nameLabel, gbc);

        gbc.gridx = 1;
        JTextField productNameField = new JTextField();
        productNameField.setPreferredSize(new Dimension(250, 30));
        productNameField.setFont(new Font("Arial", Font.PLAIN, 24));
        panel.add(productNameField, gbc);

        // Track Button
        JButton trackButton = new JButton("Track Product");
        trackButton.setFont(new Font("Arial", Font.PLAIN, 24));
        trackButton.setPreferredSize(new Dimension(150, 35));

        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(trackButton, gbc);

        trackButton.addActionListener(e -> {
            String productName = productNameField.getText();
            manager.addObserver(pharmacy);
            JOptionPane.showMessageDialog(this, pharmacyName + " is now tracking " + productName);

            // Clear the field after tracking
            productNameField.setText("");
        });

        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new InventoryApp().setVisible(true);
        });
    }
}
