package com.java.pertemuan3.tugas.model;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

// Table Model untuk JTable
public class ProductTableModel extends AbstractTableModel {
    private final String[] columnNames = {"Kode", "Nama", "Qty", "Harga"};
    private final ArrayList<ProductModel> products;

    public ProductTableModel(ArrayList<ProductModel> products) {
        this.products = products;
    }

    @Override
    public int getRowCount() {
        return products.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ProductModel p = products.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> p.getCode();
            case 1 -> p.getName();
            case 2 -> p.getQty();
            case 3 -> p.getPrice();
            default -> null;
        };
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    public void addProduct(ProductModel p) {
        products.add(p);
        fireTableDataChanged();
    }

    public void removeProduct(int index) {
        products.remove(index);
        fireTableDataChanged();
    }
}
