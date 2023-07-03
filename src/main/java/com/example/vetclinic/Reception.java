package com.example.vetclinic;

import java.sql.Date;
import java.sql.Time;

public class Reception {
    private int id;
    private Date date;
    private Time time;
    private String nameOwner;
    private String namePet;
    private String nameDisease;

    public Reception(int id, Date date, Time time, String nameOwner, String namePet, String nameDisease) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.nameOwner = nameOwner;
        this.namePet = namePet;
        this.nameDisease = nameDisease;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public String getNameOwner() {
        return nameOwner;
    }

    public void setNameOwner(String nameOwner) {
        this.nameOwner = nameOwner;
    }

    public String getNamePet() {
        return namePet;
    }

    public void setNamePet(String namePet) {
        this.namePet = namePet;
    }

    public String getNameDisease() {
        return nameDisease;
    }

    public void setNameDisease(String nameDisease) {
        this.nameDisease = nameDisease;
    }
}


