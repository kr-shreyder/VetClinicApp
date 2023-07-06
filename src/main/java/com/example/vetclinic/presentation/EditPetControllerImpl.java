package com.example.vetclinic.presentation;

import com.example.vetclinic.core.controllers.PetController;
import com.example.vetclinic.core.interfaces.EditPetController;
import com.example.vetclinic.core.models.Pet;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class EditPetControllerImpl extends BaseController implements EditPetController {

    @FXML
    private TextField breedPetText;

    @FXML
    private TextField namePetText;

    @FXML
    private Button savePetBut1;

    private Pet pet;
    LkOwnControllerImpl lkOwnController;


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

    private void editPetSave(String name, String breedName) throws SQLException, ClassNotFoundException {
        PetController petController = new PetController(this);
        petController.update(pet, name, breedName);
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public void setParentController(LkOwnControllerImpl lkOwnController) {
        this.lkOwnController = lkOwnController;
    }

    public void exit() throws SQLException, ClassNotFoundException {
        lkOwnController.updateTableData();
        savePetBut1.getScene().getWindow().hide();
    }
}