package com.example.vetclinic;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class CreatePetController extends Controller {
    @FXML
    private TextField breedPetText1;

    @FXML
    private TextField namePetText1;

    @FXML
    private Button savePetBut1;

    Pet pet;
    LkOwnController lkOwnController;
    Owner owner;

    @FXML
    void initialize() {
        savePetBut1.setOnAction(event -> {
            String nameText = namePetText1.getText().trim();
            String breedText = breedPetText1.getText().trim();

            if (!nameText.equals("") && !breedText.equals("")) {
                try {
                    createPetSave(nameText, breedText);
                } catch (SQLException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    private void createPetSave(String name, String nameBreed) throws SQLException, ClassNotFoundException {
        DBhandler dbHandler = DBhandler.getInstance();
        Pet pet = new Pet(name, this.owner.getId(), nameBreed);
        dbHandler.createPet(pet);
        lkOwnController.updateTableData();
        savePetBut1.getScene().getWindow().hide();
    }

    public void setParentController(LkOwnController lkOwnController) {
        this.lkOwnController = lkOwnController;
    }

    public void setPetOwner(Owner owner) throws SQLException, ClassNotFoundException {
        this.owner = owner;
    }
}
