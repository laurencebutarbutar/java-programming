package com.java.pertemuan1;

class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age > 0) { 
            this.age = age;
        }
    }

    public void introduce() {
        System.out.println("Halo, nama saya " + name + " dan umur saya " + age);
    }
}

class Student extends Person {
    private String major;

    public Student(String name, int age, String major) {
        super(name, age); 
        this.major = major;
    }

    public void study() {
        System.out.println(getName() + " sedang belajar " + major);
    }

    @Override
    public void introduce() {
        System.out.println("Halo, saya mahasiswa bernama " + getName() + 
                           ", umur " + getAge() + 
                           ", jurusan " + major);
    }
}

public class App {
    public static void main(String[] args) {
        Person p1 = new Person("Andi", 30);
        p1.introduce(); 

        Student s1 = new Student("Budi", 20, "Informatika");
        s1.introduce(); 
        s1.study();    
    }
}
