package com.example.vetclinic.presentation;

import com.example.vetclinic.core.controllers.PetController;
import com.example.vetclinic.core.interfaces.CreatePetController;
import com.example.vetclinic.core.models.Owner;
import com.example.vetclinic.core.models.Pet;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class CreatePetControllerImpl extends BaseController implements CreatePetController {
    @FXML
    private TextField breedPetText1;

    @FXML
    private TextField namePetText1;

    @FXML
    private Button savePetBut1;

    LkOwnControllerImpl lkOwnController;
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
        PetController petController = new PetController(this);
        petController.create(name, this.owner.getId(), nameBreed);
    }

    public void setParentController(LkOwnControllerImpl lkOwnController) {
        this.lkOwnController = lkOwnController;
    }

    public void setPetOwner(Owner owner) throws SQLException, ClassNotFoundException {
        this.owner = owner;
    }

    public void exit() throws SQLException, ClassNotFoundException {
        lkOwnController.updateTableData();
        savePetBut1.getScene().getWindow().hide();
    }
}
