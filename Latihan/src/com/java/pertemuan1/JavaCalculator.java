package com.java.pertemuan1;

import javax.swing.*;
import java.awt.*;

interface Operasi {
    int hitung(int a, int b);
}

class Tambah implements Operasi {
    public int hitung(int a, int b) { return a + b; }
}

class Kurang implements Operasi {
    public int hitung(int a, int b) { return a - b; }
}

abstract class Bentuk {
    abstract String gambar();
}

class Lingkaran extends Bentuk {
    String gambar() { return "Menggambar Lingkaran"; }
}

class Persegi extends Bentuk {
    String gambar() { return "Menggambar Persegi"; }
}

class Counter {
    static int count = 0;
    static void tambah() { count++; }
}

class Kalkulator {
    int tambah(int a, int b) { return a + b; }
    double tambah(double a, double b) { return a + b; } 
}

class KalkulatorCanggih extends Kalkulator {
    @Override
    int tambah(int a, int b) {
        int hasil = super.tambah(a, b);
        System.out.println("Override dijalankan, hasil = " + hasil);
        return hasil;
    }
}

public class JavaCalculator extends JFrame {
    private JTextField inputA, inputB;
    private JLabel lblHasil, lblStatic;

    public JavaCalculator() {
        setTitle("Mini App Java Swing - OOP & Exception");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(7, 1, 5, 5));

        inputA = new JTextField();
        inputB = new JTextField();
        add(new JLabel("Input Angka 1:"));
        add(inputA);
        add(new JLabel("Input Angka 2:"));
        add(inputB);

        JButton btnBagi = new JButton("Pembagian (Exception Handling)");
        btnBagi.addActionListener(e -> handleException());
        add(btnBagi);

        lblStatic = new JLabel("Counter: 0");
        JButton btnStatic = new JButton("Tambah Counter (static)");
        btnStatic.addActionListener(e -> {
            Counter.tambah();
            lblStatic.setText("Counter: " + Counter.count);
        });
        add(btnStatic);
        add(lblStatic);

        JButton btnKalkulator = new JButton("Kalkulator (Overloading & Overriding)");
        btnKalkulator.addActionListener(e -> handleKalkulator());
        add(btnKalkulator);

        JButton btnBentuk = new JButton("Bentuk (Abstract & Interface)");
        btnBentuk.addActionListener(e -> handleBentukDanInterface());
        add(btnBentuk);

        lblHasil = new JLabel("Hasil akan muncul di sini...");
        add(lblHasil);

        setVisible(true);
    }


    private void handleException() {
        try {
            int a = Integer.parseInt(inputA.getText()); 
            int b = Integer.parseInt(inputB.getText()); 
            int hasil = a / b;
            lblHasil.setText("Hasil pembagian: " + hasil);
        } catch (ArithmeticException ex) {
            JOptionPane.showMessageDialog(this, "Error: Pembagi tidak boleh 0");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Error: Input harus angka");
        }
    }

    private void handleKalkulator() {
        Kalkulator kalkulator = new Kalkulator();
        KalkulatorCanggih canggih = new KalkulatorCanggih();

        int a = 5, b = 3;
        int hasil1 = kalkulator.tambah(a, b);    
        double hasil2 = kalkulator.tambah(2.5, 3.5); 
        int hasil3 = canggih.tambah(a, b);       

        lblHasil.setText("<html>"
                + "Normal: " + hasil1 + "<br>"
                + "Overloading: " + hasil2 + "<br>"
                + "Overriding: " + hasil3 + "</html>");
    }

    private void handleBentukDanInterface() {
        Bentuk b1 = new Lingkaran();
        Bentuk b2 = new Persegi();

        Operasi tambah = new Tambah();
        Operasi kurang = new Kurang();

        int a = 10, b = 4;
        int hasilTambah = tambah.hitung(a, b);
        int hasilKurang = kurang.hitung(a, b);

        lblHasil.setText("<html>"
                + b1.gambar() + "<br>"
                + b2.gambar() + "<br>"
                + "Interface Tambah: " + hasilTambah + "<br>"
                + "Interface Kurang: " + hasilKurang + "</html>");
    }

    public static void main(String[] args) {
        new JavaCalculator();
    }
}
