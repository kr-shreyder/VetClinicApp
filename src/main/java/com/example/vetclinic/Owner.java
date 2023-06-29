package com.example.vetclinic;

public class Owner {
    private int id;
    private String name;
    private String number;
    private String address;
    private int user_id;

    public Owner(int id, int user_id, String name,String number, String address) {
        this.id = id;
        this.user_id = user_id;
        this.name = name;
        this.number = number;
        this.address = address;
    }

    public Owner(int user_id, String name, String number, String address) {
        this.user_id = user_id;
        this.name = name;
        this.number = number;
        this.address = address;
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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getUserId() {
        return user_id;
    }

    public void setUserId(int user_id) {
        this.user_id = user_id;
    }
}
