package com.example.vetclinic.presentation;

import com.example.vetclinic.core.controllers.ReceptionController;
import com.example.vetclinic.core.interfaces.CreateRecController;
import com.example.vetclinic.core.models.Doctor;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;

public class CreateRecPresenter extends BasePresenter implements CreateRecController {

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
    LkDocPresenter lkDocController;
    Doctor doctor;

    @FXML
    void initialize() {
        saveRecBut1.setOnAction(event -> {
            Date dateText = Date.valueOf(dateField.getText().trim());
            Time timeText = Time.valueOf(timeField.getText().trim());
            int ownerText = Integer.parseInt(ownerField.getText().trim());
            int petText = Integer.parseInt(petField.getText().trim());
            String diseasesText = diseasesField.getText().trim();

            String[] diseasesArray = diseasesText.split(",\\s*");
            ArrayList<String> diseasesList = new ArrayList<>(Arrays.asList(diseasesArray));

            if (!dateText.equals("") && !timeText.equals("") && ownerText != 0 && petText != 0 && !diseasesText.equals("")) {
                try {
                    createRecSave(dateText, timeText, ownerText, petText, diseasesList);
                } catch (SQLException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void createRecSave(Date date, Time time, int ownerId, int petId, ArrayList<String> diseases) throws SQLException, ClassNotFoundException {
        ReceptionController receptionController = new ReceptionController(this);
        receptionController.create(date, time, this.doctor.getId(), ownerId, petId, diseases);
    }

    public void setParentController(LkDocPresenter lkDocController) {
        this.lkDocController = lkDocController;
    }

    public void setDoc(Doctor doctor) throws SQLException, ClassNotFoundException {
        this.doctor = doctor;
    }

    public void exit() throws SQLException, ClassNotFoundException {
        lkDocController.updateTableRec();
        saveRecBut1.getScene().getWindow().hide();
    }
}