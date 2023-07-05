package com.example.vetclinic.presentation;

import com.example.vetclinic.core.controllers.UserController;
import com.example.vetclinic.core.interfaces.EditUserController;
import com.example.vetclinic.core.models.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class EditUserPresenter extends BasePresenter implements EditUserController {
    @FXML
    private TextField newLogin;

    @FXML
    private PasswordField newPassword;

    @FXML
    private PasswordField oldPassword;

    @FXML
    private Button saveUserBut;
    User user;


    @FXML
    void initialize() {
        saveUserBut.setOnAction(event -> {
            String oldPasswText = oldPassword.getText().trim();
            String newLoginText = newLogin.getText().trim();
            String newPasswText = newPassword.getText().trim();

            if (!oldPasswText.equals("") && !newLoginText.equals("") && !newPasswText.equals("")) {
                try {
                    editUser(oldPasswText, newLoginText, newPasswText);
                } catch (SQLException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    private void editUser(String oldPassw, String newLogin, String newPassw) throws SQLException, ClassNotFoundException {
        UserController UserController = new UserController(this);
        UserController.update(user, oldPassw, newLogin, newPassw);
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void exit() {
        saveUserBut.getScene().getWindow().hide();
    }
}