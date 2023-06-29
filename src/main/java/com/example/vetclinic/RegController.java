package com.example.vetclinic;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class RegController {
    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passwField;

    @FXML
    private Button regButton;

    @FXML
    private TextField regName;

    @FXML
    private TextField regAddress;

    @FXML
    private TextField regNumber;

    @FXML
    void initialize() {
        regButton.setOnAction(event -> {

            try {
                regNewUser();
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void regNewUser() throws SQLException, ClassNotFoundException {
        DBhandler dbHandler = DBhandler.getInstance();
        String name = regName.getText();
        String login = loginField.getText();
        String password = passwField.getText();
        String phoneNumber = regNumber.getText();
        String address = regAddress.getText();

        Owner owner = new Owner(name, phoneNumber, address, login, password);

        dbHandler.createOwner(owner);
    }
}
