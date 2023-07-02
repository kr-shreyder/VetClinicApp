package com.example.vetclinic;

public class Pet {
    private int id;
    private String name;
    private int ownerId;
    private String breed;

    public Pet(int id, String name, int ownerId, String breed) {
        this.id = id;
        this.name = name;
        this.ownerId = ownerId;
        this.breed = breed;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public int getPetId() {
        return id;
    }

    public void setpetId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }
}
