package com.example.vetclinic.core.controllers;

import com.example.vetclinic.core.models.Pet;
import com.example.vetclinic.presentation.CreatePetControllerImpl;
import com.example.vetclinic.presentation.EditPetControllerImpl;
import com.example.vetclinic.presentation.LkOwnControllerImpl;

import java.sql.SQLException;

public class PetController extends BaseController{
    private CreatePetControllerImpl createPetController;
    private EditPetControllerImpl editPetController;
    private LkOwnControllerImpl lkOwnController;
    public PetController(CreatePetControllerImpl createPetController) throws SQLException, ClassNotFoundException {
        super();
        this.createPetController = createPetController;
    }
    public PetController(EditPetControllerImpl editPetController) throws SQLException, ClassNotFoundException {
        super();
        this.editPetController = editPetController;
    }
    public PetController(LkOwnControllerImpl lkOwnController) throws SQLException, ClassNotFoundException {
        super();
        this.lkOwnController = lkOwnController;
    }

    public void create(String name, int ownerId, String nameBreed) throws SQLException, ClassNotFoundException {
        Pet pet = new Pet(name, ownerId, nameBreed);
        dbHandler.createPet(pet);
        createPetController.exit();
    }

    public void get() {

    }

    public void update(Pet pet, String name, String breedName) throws SQLException, ClassNotFoundException {
        pet.setName(name);
        pet.setBreed(breedName);
        dbHandler.updatePet(pet);
        editPetController.exit();
    }

    public void delete(Pet pet) {
        dbHandler.deletePet(pet.getPetId());
    }
}
