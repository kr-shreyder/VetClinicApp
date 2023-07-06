package com.example.vetclinic.presentation;

import com.example.vetclinic.core.controllers.PetController;
import com.example.vetclinic.core.interfaces.LkOwnController;
import com.example.vetclinic.db.DBhandler;
import com.example.vetclinic.core.models.Owner;
import com.example.vetclinic.core.models.Pet;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

import java.sql.SQLException;
import java.util.ArrayList;

public class LkOwnPresenter extends BaseController implements LkOwnController {
    @FXML
    private Label addressText;

    @FXML
    private Label nameText;

    @FXML
    private Label numberText;

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

    Owner owner;
    Pet pet;

    @FXML
    void initialize() {
        createPetBut.setOnAction(event -> {
            CreatePetPresenter createPetController = (CreatePetPresenter) newWin("create-pet-view.fxml");
            try {
                createPetController.setPetOwner(this.owner);
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            createPetController.setParentController(this);
        });

        editPetBut.setOnAction(event -> {
            Pet selectedPet = tablePet.getSelectionModel().getSelectedItem();
            EditPetPresenter editPetController = (EditPetPresenter) newWin("edit-pet-view.fxml");
            editPetController.setPet(selectedPet);
            editPetController.setParentController(this);
        });

        deletePetBut.setOnAction(event -> {
            try {
                PetController petController = new PetController(this);
                petController.delete(getSelectedPet());
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });

        chngOwnBut.setOnAction(event -> {
            openEditOwnWindow();
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
        ArrayList<Pet> pets = DBhandler.getConnect().getPetsByOwnerId(ownerId);

        breedPetCol.setCellValueFactory(new PropertyValueFactory<>("breed"));
        namePetCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        tablePet.getItems().addAll(pets);
    }

    public void updateTableData() throws SQLException, ClassNotFoundException {
        ArrayList<Pet> pets = DBhandler.getConnect().getPetsByOwnerId(this.owner.getId());

        tablePet.getItems().clear();

        tablePet.getItems().addAll(pets);

        tablePet.refresh();
    }

    public void openEditOwnWindow () {
        EditUserPresenter editOwnController = (EditUserPresenter) newWin("edit-user-view.fxml");
        try {
            editOwnController.setUser(DBhandler.getConnect().getUserById(this.owner.getUserId()));
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public Pet getSelectedPet() {
        return tablePet.getSelectionModel().getSelectedItem();
    }
}
