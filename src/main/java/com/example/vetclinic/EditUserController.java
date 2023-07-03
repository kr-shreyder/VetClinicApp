package com.example.vetclinic;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class EditUserController extends Controller {
    @FXML
    private TextField newLogin;

    @FXML
    private TextField newPassword;

    @FXML
    private TextField oldPassword;

    @FXML
    private Button saveUserBut;

    LkOwnController lkOwnController;
    User user;


    /*@FXML
    void initialize() {
        saveUserBut.setOnAction(event -> {
            String oldPasswText = oldPassword.getText().trim();
            String newLoginText = newLogin.getText().trim();
            String newPasswText = newPassword.getText().trim();

            if (!oldPasswText.equals("") && !newLoginText.equals("") && !newPasswText.equals("")) {
                try {
                    editUser(nameText, breedText);
                } catch (SQLException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }*/

    private void editUser(String oldPassw, String newLogin, String newPassw) throws SQLException, ClassNotFoundException {
        DBhandler dbHandler = DBhandler.getInstance();
        user.setLogin(newLogin);
        user.setPassword(newPassw);
        lkOwnController.updateTableData();
        saveUserBut.getScene().getWindow().hide();
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setParentController(LkOwnController lkOwnController) {
        this.lkOwnController = lkOwnController;
    }
}