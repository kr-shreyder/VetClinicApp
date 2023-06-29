package com.example.vetclinic;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.sql.SQLException;

public class LkOwnController {

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
    Owner owner;

    /*@FXML
    void initialize() {
        Owner currentOwner = get

        setOwner(currentOwner);
    }
*/
    public void setOwner(Owner owner) {
        this.owner = owner;

        nameText.setText("Ваше имя:" + owner.getName());
        numberText.setText("Ваш телефон:" + owner.getNumber());
        addressText.setText("Ваш адрес:" + owner.getAddress());
    }
}
