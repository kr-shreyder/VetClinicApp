package com.example.vetclinic;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class EditPetController extends Controller {

    @FXML
    private TextField breedPetText;

    @FXML
    private TextField namePetText;

    @FXML
    private Button savePetBut1;

    /*@FXML
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
        Pet pet = DBhandler.getInstance().getPet(owner.getId());
        //System.out.println(pet.getName());

        dbHandler.updatePet(name, nameBreed, petId, breedId);

        savePetBut1.getScene().getWindow().hide();
        LkOwnController lkOwnController = (LkOwnController) newWin("LkOwnController");
        lkOwnController.setPet(pet);
    }*/
}