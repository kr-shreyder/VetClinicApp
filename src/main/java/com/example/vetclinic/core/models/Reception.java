package com.example.vetclinic.core.models;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

public class Reception {
    private int id;
    private Date date;
    private Time time;
    private int doctorId;
    private int ownerId;
    private int petId;
    private ArrayList<String> diseaseName;

    public Reception(int id, Date date, Time time, int doctorId, int ownerId, int petId, ArrayList<String> diseaseName) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.doctorId = doctorId;
        this.ownerId = ownerId;
        this.petId = petId;
        this.diseaseName = diseaseName;
    }

    public Reception(Date date, Time time, int doctorId, int ownerId, int petId, ArrayList<String> diseaseName) {
        this.date = date;
        this.time = time;
        this.doctorId = doctorId;
        this.ownerId = ownerId;
        this.petId = petId;
        this.diseaseName = diseaseName;
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

    public int getPetId() {
        return petId;
    }

    public void setPetId(int petId) {
        this.petId = petId;
    }

    public ArrayList<String> getDiseaseName() {
        return diseaseName;
    }

    public void setDiseaseName(ArrayList<String> diseaseName) {
        this.diseaseName = diseaseName;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }
}


