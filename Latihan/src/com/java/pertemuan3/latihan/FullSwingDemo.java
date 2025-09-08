package com.java.pertemuan3.latihan;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.java.pertemuan3.latihan.model.PersonModel;
import com.java.pertemuan3.latihan.model.PersonTableModel;
import com.java.pertemuan3.tugas.model.ProductModel;
import com.java.pertemuan3.tugas.model.ProductTableModel;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;


public class FullSwingDemo extends JFrame {
    private JTextField txtName, txtAge;
    private JTable table;
    // private DefaultTableModel tableModel;
    private final ArrayList<PersonModel> persons = new ArrayList<>();
    private final PersonTableModel tableModel = new PersonTableModel(persons);
    private JTextArea textArea;

    public FullSwingDemo() {
        setTitle("Full Swing Components Demo");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // ===== MENU BAR =====
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(e -> System.exit(0));
        fileMenu.add(exitItem);
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);

        // ===== TABEL DATA (BorderLayout CENTER) =====
        // String[] columnNames = {"Name", "Age"};
        // tableModel = new DefaultTableModel(columnNames, 0);
        // table = new JTable(tableModel);
        // JScrollPane scrollPane = new JScrollPane(table);
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        // ===== TOOLBAR =====
        JToolBar toolBar = new JToolBar();
        JButton btnClear = new JButton("Remove Data");
        btnClear.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                tableModel.removePerson(selectedRow);
            } else {
                JOptionPane.showMessageDialog(this,
                        "Pilih person yang ingin dihapus!", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        });
        toolBar.add(btnClear);
        add(toolBar, BorderLayout.NORTH);

        // ===== PANEL INPUT (FlowLayout) =====
        JPanel inputPanel = new JPanel(new FlowLayout());
        JLabel lblName = new JLabel("Name:");
        txtName = new JTextField(10);
        JLabel lblAge = new JLabel("Age:");
        txtAge = new JTextField(5);
        JButton btnAdd = new JButton("Add");
        inputPanel.add(lblName);
        inputPanel.add(txtName);
        inputPanel.add(lblAge);
        inputPanel.add(txtAge);
        inputPanel.add(btnAdd);


        // ===== TEXT AREA + POPUP MENU (GridLayout) =====
        JPanel textPanel = new JPanel(new GridLayout(1, 1));
        textArea = new JTextArea("Right-click here...");
        JScrollPane textScroll = new JScrollPane(textArea);

        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem cutItem = new JMenuItem("Cut");
        JMenuItem copyItem = new JMenuItem("Copy");
        JMenuItem pasteItem = new JMenuItem("Paste");
        popupMenu.add(cutItem);
        popupMenu.add(copyItem);
        popupMenu.add(pasteItem);

        textArea.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) { showPopup(e); }
            public void mouseReleased(MouseEvent e) { showPopup(e); }
            private void showPopup(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    popupMenu.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });
        textPanel.add(textScroll);

        // ===== MAIN PANEL (BorderLayout) =====
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(textPanel, BorderLayout.SOUTH);

        add(mainPanel, BorderLayout.CENTER);

        // ===== AKSI TOMBOL ADD =====
        btnAdd.addActionListener(e -> {
            String name = txtName.getText();
            String age = txtAge.getText();
            if (!name.isEmpty() && !age.isEmpty()) {
                PersonModel person = new PersonModel(name, Integer.parseInt(age));
                tableModel.addPerson(person);
                txtName.setText("");
                txtAge.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Please fill all fields!");
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FullSwingDemo().setVisible(true));
    }
}

