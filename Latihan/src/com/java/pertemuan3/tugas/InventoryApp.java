package com.java.pertemuan3.tugas;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.util.ArrayList;
import com.java.pertemuan3.tugas.model.ProductModel;
import com.java.pertemuan3.tugas.model.ProductTableModel;

// If Product and ProductTableModel are not defined, you need to define them as well.

// Main App
public class InventoryApp extends JFrame {
    private final ArrayList<ProductModel> products = new ArrayList<>();
    private final ProductTableModel tableModel = new ProductTableModel(products);

    public InventoryApp() {
        setTitle("Inventory App (Memory Only)");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // --- Panel Form ---
        JPanel formPanel = new JPanel(new GridLayout(5, 2, 5, 5));

        JTextField codeField = new JTextField();
        JTextField nameField = new JTextField();
        JTextField qtyField = new JTextField();
        JTextField priceField = new JTextField();

        JButton addButton = new JButton("Tambah");

        formPanel.add(new JLabel("Kode:"));
        formPanel.add(codeField);
        formPanel.add(new JLabel("Nama:"));
        formPanel.add(nameField);
        formPanel.add(new JLabel("Qty:"));
        formPanel.add(qtyField);
        formPanel.add(new JLabel("Harga:"));
        formPanel.add(priceField);
        formPanel.add(new JLabel(""));
        formPanel.add(addButton);

        // --- Tabel Produk ---
        JTable table = new JTable(tableModel);
        JScrollPane tableScroll = new JScrollPane(table);

        // --- Tombol Hapus ---
        JButton deleteButton = new JButton("Hapus Produk Terpilih");

        // --- Layout Utama ---
        setLayout(new BorderLayout(10, 10));
        add(formPanel, BorderLayout.NORTH);
        add(tableScroll, BorderLayout.CENTER);
        add(deleteButton, BorderLayout.SOUTH);

        // --- Event Tambah Produk ---
        addButton.addActionListener(e -> {
            try {
                String code = codeField.getText();
                String name = nameField.getText();
                int qty = Integer.parseInt(qtyField.getText());
                double price = Double.parseDouble(priceField.getText());

                ProductModel newProduct = new ProductModel(code, name, qty, price);
                tableModel.addProduct(newProduct);

                // reset field
                codeField.setText("");
                nameField.setText("");
                qtyField.setText("");
                priceField.setText("");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,
                        "Input tidak valid!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // --- Event Hapus Produk ---
        deleteButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                tableModel.removeProduct(selectedRow);
            } else {
                JOptionPane.showMessageDialog(this,
                        "Pilih produk yang ingin dihapus!", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new InventoryApp().setVisible(true));
    }
}