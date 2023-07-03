package com.example.vetclinic;

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

public class LkDocController extends Controller{
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
        /*editRecBut.setOnAction(event -> {
            Reception selectedRec = tableRec.getSelectionModel().getSelectedItem();
            EditRecController editRecController = (EditRecController) newWin("edit-rec-view.fxml");
            editRecController.setRec(selectedRec);
            editRecController.setParentController(this);
        });

        createRecBut.setOnAction(event -> {
            CreateRecController createRecController = (CreateRecController) newWin("create-rec-view.fxml");
            try {
                createRecController.setRecOwner(this.doctor);
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            createRecController.setParentController(this);
        });

        deleteRecBut.setOnAction(event -> {
            try {
                Reception selectedRec = tableRec.getSelectionModel().getSelectedItem();
                DBhandler.getInstance().deleteRec(selectedRec.getRecId());
                this.updateTableRec();
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
        chngOwnBut.setOnAction(event -> {
            EditUserController editOwnController = (EditUserController) newWin("edit-user-view.fxml");
            try {
                editOwnController.setUser(DBhandler.getInstance().getUser());
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            editOwnController.setParentController(this);
        });*/
    }

    public void setDoctor(Doctor doctor) throws SQLException, ClassNotFoundException {
        this.doctor = doctor;

        nameText.setText("Ваше имя: " + doctor.getName());
        numberText.setText("Ваш телефон: " + doctor.getNumber());
        addressText.setText("Ваш адрес: " + doctor.getAddress());

        //setRecs(doctor.getId());
    }

    /*public void setRec(Reception rec) {
        this.rec = rec;

        dateCol.setCellValueFactory(new PropertyValueFactory<>("data"));
        timeCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        ownCol.setCellValueFactory(new PropertyValueFactory<>("owner"));
        petCol.setCellValueFactory(new PropertyValueFactory<>("pet"));
        diseaseCol.setCellValueFactory(new PropertyValueFactory<>("disease"));

        tableRec.getItems().addAll(rec);
    }

    public void setRecs(int doctorId) throws SQLException, ClassNotFoundException {
        ArrayList<Reception> recs = DBhandler.getInstance().getDoctorByOwnerId(doctorId);

        dateCol.setCellValueFactory(new PropertyValueFactory<>("data"));
        timeCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        ownCol.setCellValueFactory(new PropertyValueFactory<>("owner"));
        petCol.setCellValueFactory(new PropertyValueFactory<>("pet"));
        diseaseCol.setCellValueFactory(new PropertyValueFactory<>("disease"));

        tableRec.getItems().addAll(recs);
    }

    public void updateTableRec() throws SQLException, ClassNotFoundException {
        ArrayList<Pet> pets = DBhandler.getInstance().getPetsByOwnerId(this.doctor.getId());

        tableRec.getItems().clear();

        tableRec.getItems().addAll(recs);

        tableRec.refresh();
    }*/
}