package com.java.pertemuan1;

// ====== Class & Encapsulation ======
class Hewan {
    // Attribute (private -> Encapsulation)
    private String nama;
    private int umur;

    // Constructor
    public Hewan(String nama, int umur) {
        this.nama = nama;
        this.umur = umur;
    }

    // Getter
    public String getNama() {
        return nama;
    }

    public int getUmur() {
        return umur;
    }

    // Method umum
    public void makan() {
        System.out.println(nama + " sedang makan...");
    }

    // Method yang bisa dioverride
    public void suara() {
        System.out.println(nama + " mengeluarkan suara...");
    }
}

// ====== Inheritance ======
class Kucing extends Hewan {
    public Kucing(String nama, int umur) {
        super(nama, umur);
    }

    // Polymorphism (override suara)
    @Override
    public void suara() {
        System.out.println(getNama() + " berkata: Meong...");
    }
}

class Anjing extends Hewan {
    public Anjing(String nama, int umur) {
        super(nama, umur);
    }

    // Polymorphism (override suara)
    @Override
    public void suara() {
        System.out.println(getNama() + " berkata: Guk guk...");
    }
}

// ====== Main Program ======
public class App2 {
    public static void main(String[] args) {
        // Object dari class Hewan (Parent)
        Hewan h = new Hewan("Helli", 2);
        h.makan();
        h.suara();

        // Object dari subclass Kucing
        Kucing k = new Kucing("Kitty", 2);
        k.makan();   // diwarisi dari Hewan
        k.suara();   // Polymorphism

        // Object dari subclass Anjing
        Anjing a = new Anjing("Bobby", 3);
        a.makan();
        a.suara();
    }
}
