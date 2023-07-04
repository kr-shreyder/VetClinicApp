package com.example.vetclinic.presentation;

import com.example.vetclinic.core.controllers.ReceptionController;
import com.example.vetclinic.db.DBhandler;
import com.example.vetclinic.core.models.Doctor;
import com.example.vetclinic.core.models.Reception;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;

public class LkDocControllerImpl extends BaseController{
    @FXML
    private Label addressText;

    @FXML
    private Button chngDocBut;

    @FXML
    private Button createRecBut;

    @FXML
    private Button deleteRecBut;

    @FXML
    private Button editRecBut;

    @FXML
    private ImageView imgButHome;

    @FXML
    private Label nameText;

    @FXML
    private Label numberText;

    @FXML
    private TableColumn<Reception, Date> dateCol;

    @FXML
    private TableColumn<Reception, String> ownCol;

    @FXML
    private TableColumn<Reception, String> petCol;

    @FXML
    private TableView<Reception> tableRec;

    @FXML
    private TableColumn<Reception, Time> timeCol;

    @FXML
    private TableColumn<Reception, String> diseaseCol;

    Doctor doctor;
    Reception rec;

    @FXML
    void initialize() {
        createRecBut.setOnAction(event -> {
            CreateRecControllerImpl createRecController = (CreateRecControllerImpl) newWin("create-rec-view.fxml");
            try {
                createRecController.setDoc(this.doctor);
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            createRecController.setParentController(this);
        });

        editRecBut.setOnAction(event -> {
            Reception selectedRec = tableRec.getSelectionModel().getSelectedItem();
            EditRecControllerImpl editRecController = (EditRecControllerImpl) newWin("edit-rec-view.fxml");
            editRecController.setRec(selectedRec);
            editRecController.setParentController(this);
        });

        deleteRecBut.setOnAction(event -> {
            try {
                ReceptionController receptionController = new ReceptionController(this);
                receptionController.delete(getSelectRec());
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });

        chngDocBut.setOnAction(event -> {
            openEditDocWindow();
        });
    }

    public void setDoctor(Doctor doctor) throws SQLException, ClassNotFoundException {
        this.doctor = doctor;

        nameText.setText("Ваше имя: " + doctor.getName());
        numberText.setText("Ваш телефон: " + doctor.getNumber());
        addressText.setText("Ваш адрес: " + doctor.getAddress());

        setRecs(doctor.getId());
    }

    public void setRec(Reception rec) {
        this.rec = rec;

        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        timeCol.setCellValueFactory(new PropertyValueFactory<>("time"));
        ownCol.setCellValueFactory(new PropertyValueFactory<>("ownerId"));
        petCol.setCellValueFactory(new PropertyValueFactory<>("petId"));
        diseaseCol.setCellValueFactory(cellData -> {
            Reception reception = cellData.getValue();
            String diseases = String.join(", ", reception.getDiseaseName());
            return new SimpleStringProperty(diseases);
        });


        tableRec.getItems().addAll(rec);
    }

    public void setRecs(int doctorId) throws SQLException, ClassNotFoundException {
        ArrayList<Reception> recs = DBhandler.getInstance().getReceptionsByDoctorId(doctorId);

        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        timeCol.setCellValueFactory(new PropertyValueFactory<>("time"));
        ownCol.setCellValueFactory(new PropertyValueFactory<>("ownerId"));
        petCol.setCellValueFactory(new PropertyValueFactory<>("petId"));
        diseaseCol.setCellValueFactory(cellData -> {
            Reception reception = cellData.getValue();
            String diseases = String.join(", ", reception.getDiseaseName());
            return new SimpleStringProperty(diseases);
        });


        tableRec.getItems().addAll(recs);
    }

    public void updateTableRec() throws SQLException, ClassNotFoundException {
        ArrayList<Reception> recs = DBhandler.getInstance().getReceptionsByDoctorId(this.doctor.getId());

        tableRec.getItems().clear();

        tableRec.getItems().addAll(recs);

        tableRec.refresh();
    }

    public void openEditDocWindow() {
        EditUserControllerImpl editDocController = (EditUserControllerImpl) newWin("edit-user-view.fxml");
        try {
            editDocController.setUser(DBhandler.getInstance().getUserById(this.doctor.getUserId()));
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public Reception getSelectRec () {
        return tableRec.getSelectionModel().getSelectedItem();
    }
}