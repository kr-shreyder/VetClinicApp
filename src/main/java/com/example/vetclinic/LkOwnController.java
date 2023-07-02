package com.example.vetclinic;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

import java.sql.SQLException;
import java.util.ArrayList;

public class LkOwnController extends Controller{
    @FXML
    private Label addressText;

    @FXML
    private Button chngOwnBut;

    @FXML
    private Button createPetBut;

    @FXML
    private Button deletePetBut;

    @FXML
    private Button editPetBut;

    @FXML
    private ImageView imgButHome;

    @FXML
    private TableView<Pet> tablePet;

    @FXML
    private TableColumn<Pet, String> breedPetCol;

    @FXML
    private TableColumn<Pet, String> namePetCol;

    @FXML
    private Label nameText;

    @FXML
    private Label numberText;

    Owner owner;
    Pet pet;

    @FXML
    void initialize() {
        editPetBut.setOnAction(event -> {
            editPetBut.getScene().getWindow().hide();
            newWin("edit-pet-view.fxml");
        });
    }

    public void setOwner(Owner owner) throws SQLException, ClassNotFoundException {
        this.owner = owner;

        nameText.setText("Ваше имя: " + owner.getName());
        numberText.setText("Ваш телефон: " + owner.getNumber());
        addressText.setText("Ваш адрес: " + owner.getAddress());

        setPets(owner.getId());
    }

    public void setPet(Pet pet) {
        this.pet = pet;

        breedPetCol.setCellValueFactory(new PropertyValueFactory<>("breed"));
        namePetCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        tablePet.getItems().addAll(pet);
    }

    public void setPets(int ownerId) throws SQLException, ClassNotFoundException {
        ArrayList<Pet> pets = DBhandler.getInstance().getPetsByOwnerId(ownerId);

        breedPetCol.setCellValueFactory(new PropertyValueFactory<>("breed"));
        namePetCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        tablePet.getItems().addAll(pets);
    }
}
