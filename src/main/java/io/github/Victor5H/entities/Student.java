package io.github.Victor5H.entities;

import javax.persistence.*;

// All part of hibernate
@Entity(name="student")
@Table(name="student")
public class Student {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int roll;
    @Column(name ="name")//not necessary
    private String name;
    private double height;

    public Student(int roll, String name, double height) {
        this.roll = roll;
        this.name = name;
        this.height = height;
    }
    public Student( String name, double height) {
        this.name = name;
        this.height = height;
    }

    public Student() {
    }

    public int getRoll() {
        return roll;
    }

    public void setRoll(int roll) {
        this.roll = roll;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "Student{" +
                "roll=" + roll +
                ", name='" + name + '\'' +
                ", height=" + height +
                '}';
    }
}
