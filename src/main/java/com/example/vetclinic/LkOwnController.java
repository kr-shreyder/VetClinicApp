package com.example.vetclinic;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

import java.sql.SQLException;

public class LkOwnController extends Controller{

    @FXML
    private Button ChngOwnBut;

    @FXML
    private Button MyPetsBut;

    @FXML
    private Button MyReceptionsBut;

    @FXML
    private Label addressText;

    @FXML
    private ImageView imgButHome;

    @FXML
    private Label nameText;

    @FXML
    private Label numberText;

    @FXML
    private TableView<Pet> TablePet;

    @FXML
    private TableColumn<Pet, String> breedPetCol;

    @FXML
    private TableColumn<Pet, String> namePetCol;

    Owner owner;
    Pet pet;

    @FXML
    void initialize() {
    }

    public void setOwner(Owner owner) {
        this.owner = owner;

        nameText.setText("Ваше имя: " + owner.getName());
        numberText.setText("Ваш телефон: " + owner.getNumber());
        addressText.setText("Ваш адрес: " + owner.getAddress());
    }

    public void setPet(Pet pet) {
        this.pet = pet;

        breedPetCol.setCellValueFactory(new PropertyValueFactory<>("breed"));
        namePetCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        TablePet.getItems().addAll(pet);
    }

}
