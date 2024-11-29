package main.java.inventory;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.*;
import java.time.LocalDate;
import java.util.Map;
import java.util.Arrays;

public class InventoryDashboard extends JFrame {

    private InventoryManager manager;

    public InventoryDashboard() {
        manager = InventoryManager.getInstance();
        setTitle("Inventory Dashboard");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        try {
            setIconImage(new ImageIcon(getClass().getClassLoader().getResource("new/path/to/drugs.png")).getImage());
        } catch (Exception e) {
        }

        JPanel mainPanel = new JPanel(new BorderLayout());

        Map<String, Integer> categoryStocks = manager.getCategoryStockByDate(LocalDate.now());

        BarChartPanel barChartPanel = new BarChartPanel(categoryStocks);
        mainPanel.add(barChartPanel, BorderLayout.CENTER);

        setContentPane(mainPanel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new InventoryDashboard().setVisible(true));
    }

    class BarChartPanel extends JPanel {
        private Map<String, Integer> data;
        private String[] categories;
        private int[] values;
        private Rectangle[] barBounds;

        public BarChartPanel(Map<String, Integer> data) {
            this.data = data;
            this.categories = data.keySet().toArray(new String[0]);
            this.values = data.values().stream().mapToInt(Integer::intValue).toArray();
            this.barBounds = new Rectangle[categories.length];

            setPreferredSize(new Dimension(400, 300));

            addMouseMotionListener(new MouseMotionAdapter() {
                @Override
                public void mouseMoved(MouseEvent e) {
                    handleMouseHover(e);
                }
            });
        }

        private void handleMouseHover(MouseEvent e) {
            Point mousePoint = e.getPoint();
            for (int i = 0; i < barBounds.length; i++) {
                if (barBounds[i] != null && barBounds[i].contains(mousePoint)) {
                    String tooltip = categories[i] + ": " + values[i];
                    setToolTipText(tooltip);
                    return;
                }
            }
            setToolTipText(null);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;

            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int width = getWidth();
            int height = getHeight();
            int padding = 150;
            int barHeight = (height - 2 * padding) / data.size();

            int maxStock = Arrays.stream(values).max().orElse(1);

            g2.setColor(Color.WHITE);
            g2.fillRect(0, 0, width, height);

            g2.setColor(Color.BLACK);
            g2.drawLine(padding, padding, padding, height - padding);
            g2.drawLine(padding, height - padding, width - padding, height - padding);

            int numGridLines = 5;
            int stepSize = maxStock / numGridLines;
            for (int i = 0; i <= numGridLines; i++) {
                int x = padding + i * (width - 2 * padding) / numGridLines;
                g2.setColor(Color.LIGHT_GRAY);
                g2.drawLine(x, padding, x, height - padding);
                g2.setColor(Color.BLACK);
                String label = Integer.toString(i * stepSize);
                int labelWidth = g2.getFontMetrics().stringWidth(label);
                g2.drawString(label, x - labelWidth / 2, height - padding + 20);
            }

            for (int i = 0; i < categories.length; i++) {
                int barWidth = (int) ((double) values[i] / maxStock * (width - 2 * padding));
                int x = padding;
                int y = padding + i * barHeight;

                g2.setColor(new Color(100, 149, 237));
                barBounds[i] = new Rectangle(x, y + barHeight / 10, barWidth, barHeight - barHeight / 5);
                g2.fill(barBounds[i]);

                String categoryLabel = categories[i];
                g2.setColor(Color.BLACK);
                g2.drawString(categoryLabel, x - g2.getFontMetrics().stringWidth(categoryLabel) - 10, y + barHeight / 2 + 5);

                String valueLabel = Integer.toString(values[i]);
                g2.drawString(valueLabel, x + barWidth + 5, y + barHeight / 2 + 5);
            }
        }
    }
}