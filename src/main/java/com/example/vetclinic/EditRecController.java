/*
package com.example.vetclinic;

import javafx.fxml.FXML;

import java.sql.SQLException;

public class EditRecController {

    private Pet pet;
    LkOwnController lkOwnController;


    @FXML
    void initialize() {
        savePetBut1.setOnAction(event -> {
            String nameText = namePetText.getText().trim();
            String breedText = breedPetText.getText().trim();

            if (!nameText.equals("") && !breedText.equals("")) {
                try {
                    editPetSave(nameText, breedText);
                } catch (SQLException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    private void editPetSave(String name, String nameBreed) throws SQLException, ClassNotFoundException {
        DBhandler dbHandler = DBhandler.getInstance();
        pet.setName(name);
        pet.setBreed(nameBreed);
        dbHandler.updatePet(this.pet);
        lkOwnController.updateTableData();
        savePetBut1.getScene().getWindow().hide();
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public void setParentController(LkOwnController lkOwnController) {
        this.lkOwnController = lkOwnController;
    }
}
*/
