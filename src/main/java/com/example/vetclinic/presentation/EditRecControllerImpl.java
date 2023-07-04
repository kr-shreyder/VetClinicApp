package com.example.vetclinic.presentation;

import com.example.vetclinic.core.controllers.ReceptionController;
import com.example.vetclinic.core.interfaces.EditRecController;
import com.example.vetclinic.core.models.Reception;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;

public class EditRecControllerImpl extends BaseController implements EditRecController {

    @FXML
    private TextField dateField;

    @FXML
    private TextArea diseasesField;

    @FXML
    private TextField ownerField;

    @FXML
    private TextField petField;

    @FXML
    private Button saveRecBut;

    @FXML
    private TextField timeField;
    LkDocControllerImpl lkDocController;
    Reception reception;


    @FXML
    void initialize() {
        saveRecBut.setOnAction(event -> {
            Date dateText = Date.valueOf(dateField.getText().trim());
            Time timeText = Time.valueOf(timeField.getText().trim());
            int ownerText = Integer.parseInt(ownerField.getText().trim());
            int petText = Integer.parseInt(petField.getText().trim());
            String diseasesText = diseasesField.getText().trim();

            String[] diseasesArray = diseasesText.split(",\\s*");
            ArrayList<String> diseasesList = new ArrayList<>(Arrays.asList(diseasesArray));

            if (!dateText.equals("") && !timeText.equals("") && ownerText != 0 && petText != 0 && !diseasesText.equals("")) {
                try {
                    editRecSave(dateText, timeText, ownerText, petText, diseasesList);
                } catch (SQLException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    private void editRecSave(Date date, Time time, int ownerId, int petId, ArrayList<String> diseases) throws SQLException, ClassNotFoundException {
        ReceptionController receptionController = new ReceptionController(this);
        receptionController.update(reception, date, time, ownerId, petId, diseases);
    }

    public void setRec(Reception reception) {
        this.reception = reception;
    }

    public void setParentController(LkDocControllerImpl lkDocController) {
        this.lkDocController = lkDocController;
    }

    public void exit() throws SQLException, ClassNotFoundException {
        lkDocController.updateTableRec();
        saveRecBut.getScene().getWindow().hide();
    }
}
