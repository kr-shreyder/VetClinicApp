package com.example.vetclinic;

public class Owner {
    private String name;
    private String number;
    private String address;
    private String login;
    private String password;

    public Owner(String name,String number, String address, String login, String password) {
        this.name = name;
        this.number = number;
        this.address = address;
        this.login = login;
        this.password = password;
    }

    public Owner() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
}
