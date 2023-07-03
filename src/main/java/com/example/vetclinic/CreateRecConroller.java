/*
package com.example.vetclinic;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;

public class CreateRecConroller {

    @FXML
    private TextField dateField;

    @FXML
    private TextArea diseasesField;

    @FXML
    private TextField ownerField;

    @FXML
    private TextField petField;

    @FXML
    private Button saveRecBut1;

    @FXML
    private TextField timeField;

    @FXML
    void initialize() {
        saveRecBut1.setOnAction(event -> {
            Date dateText = Date.valueOf(dateField.getText().trim());
            Time timeText = Time.valueOf(timeField.getText().trim());
            String ownerText = ownerField.getText().trim();
            String petText = petField.getText().trim();
            String diseasesText = diseasesField.getText().trim();


            if (!dateText.equals("") && !timeText.equals("") && !ownerText.equals("") && !petText.equals("") && !diseasesText.equals("")) {
                try {
                    createRecSave(nameText, breedText);
                } catch (SQLException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    private void createPetSave(String name, String nameBreed) throws SQLException, ClassNotFoundException {
        DBhandler dbHandler = DBhandler.getInstance();
        Pet pet = new Pet(name, this.doctor.getId(), nameBreed);
        dbHandler.createPet(pet);
        lkDocController.updateTableData();
        saveRecBut1.getScene().getWindow().hide();
    }

    public void setParentController(LkDocController lkDocController) {
        this.lkDocController = lkDocController;
    }

    public void setRecDoc(Doctor doctor) throws SQLException, ClassNotFoundException {
        this.doctor = doctor;
    }
}*/
