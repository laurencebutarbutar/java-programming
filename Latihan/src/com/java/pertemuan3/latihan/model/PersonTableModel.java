package com.java.pertemuan3.latihan.model;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class PersonTableModel extends AbstractTableModel{
    private final String[] columnNames = {"Nama", "Umur"};
    private final ArrayList<PersonModel> persons;

    public PersonTableModel(ArrayList<PersonModel> persons) {
        this.persons = persons;
    }

    @Override
    public int getRowCount() {
        return persons.size();
    }

    // @Override
    // public int setRowCount() {
    //     return persons.size();
    // }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        PersonModel p = persons.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> p.getName();
            case 1 -> p.getAge();
            default -> null;
        };
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    public void addPerson(PersonModel p) {
        persons.add(p);
        fireTableDataChanged();
    }

    public void removePerson(int index) {
        persons.remove(index);
        fireTableDataChanged();
    }
}
