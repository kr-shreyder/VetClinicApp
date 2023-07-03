package com.example.vetclinic;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

import java.sql.SQLException;
import java.util.ArrayList;

public class LkOwnController extends Controller {
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
            Pet selectedPet = tablePet.getSelectionModel().getSelectedItem();
            EditPetController editPetController = (EditPetController) newWin("edit-pet-view.fxml");
            editPetController.setPet(selectedPet);
            editPetController.setParentController(this);
        });

        createPetBut.setOnAction(event -> {
            CreatePetController createPetController = (CreatePetController) newWin("create-pet-view.fxml");
            try {
                createPetController.setPetOwner(this.owner);
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            createPetController.setParentController(this);
        });

        deletePetBut.setOnAction(event -> {
            try {
                Pet selectedPet = tablePet.getSelectionModel().getSelectedItem();
                DBhandler.getInstance().deletePet(selectedPet.getPetId());
                this.updateTableData();
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
/*
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

    public void updateTableData() throws SQLException, ClassNotFoundException {
        ArrayList<Pet> pets = DBhandler.getInstance().getPetsByOwnerId(this.owner.getId());

        tablePet.getItems().clear();

        tablePet.getItems().addAll(pets);

        tablePet.refresh();
    }
}
