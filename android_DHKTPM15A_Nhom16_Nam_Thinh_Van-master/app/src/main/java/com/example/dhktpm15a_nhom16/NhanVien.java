package com.example.dhktpm15a_nhom16;

import java.io.Serializable;

public class NhanVien implements Serializable {
    private int id;
    private String name;
    private String position;
    private String country;
    private int age;

    public NhanVien() {
    }

    public NhanVien(String name, String position, int age, String country) {
        this.name = name;
        this.position = position;
        this.country = country;
        this.age = age;
    }

    public NhanVien(int id, String name, String position, int age, String country) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.age = age;
        this.country = country;

    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        this.age = age;
    }




}
