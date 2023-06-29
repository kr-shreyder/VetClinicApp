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
        /*String name = regName.getText();
        String login = loginField.getText();
        String password = passwField.getText();
        String phoneNumber = regNumber.getText();
        String address = regAddress.getText();*/

        String name = "Кристина";
        String login = "Login";
        String password = "Test";
        String phoneNumber = "89119559170";
        String address = "СПБ";

        User user = new User(login, password);
        user = dbHandler.createUser(user);
        System.out.println(user.getId());

        Owner owner = new Owner(user.getId(), name, phoneNumber, address);
        dbHandler.createOwner(owner);
    }
}
