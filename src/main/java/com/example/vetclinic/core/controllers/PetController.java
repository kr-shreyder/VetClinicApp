package com.example.vetclinic.core.controllers;

import com.example.vetclinic.core.models.Pet;
import com.example.vetclinic.presentation.CreatePetPresenter;
import com.example.vetclinic.presentation.EditPetPresenter;
import com.example.vetclinic.presentation.LkOwnPresenter;

import java.sql.SQLException;

public class PetController extends BaseController {
    private CreatePetPresenter createPetController;
    private EditPetPresenter editPetController;
    private LkOwnPresenter lkOwnController;

    public PetController(CreatePetPresenter createPetController) throws SQLException, ClassNotFoundException {
        super();
        this.createPetController = createPetController;
    }

    public PetController(EditPetPresenter editPetController) throws SQLException, ClassNotFoundException {
        super();
        this.editPetController = editPetController;
    }

    public PetController(LkOwnPresenter lkOwnController) throws SQLException, ClassNotFoundException {
        super();
        this.lkOwnController = lkOwnController;
    }

    public void create(String name, int ownerId, String nameBreed) throws SQLException, ClassNotFoundException {
        Pet pet = new Pet(name, ownerId, nameBreed);
        dbHandler.createPet(pet);
        createPetController.exit();
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
