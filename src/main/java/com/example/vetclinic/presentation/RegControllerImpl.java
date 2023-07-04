package com.example.vetclinic.presentation;

import com.example.vetclinic.core.controllers.OwnerController;
import com.example.vetclinic.core.interfaces.RegController;
import com.example.vetclinic.db.DBhandler;
import com.example.vetclinic.core.models.Owner;
import com.example.vetclinic.core.models.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class RegControllerImpl extends BaseController implements RegController {
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
        String name = regName.getText();
        String login = loginField.getText();
        String password = passwField.getText();
        String phoneNumber = regNumber.getText();
        String address = regAddress.getText();

        OwnerController ownerController = new OwnerController(this);
        ownerController.create(login, password, name, phoneNumber, address);
    }
}
