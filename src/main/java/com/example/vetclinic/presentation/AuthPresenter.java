package com.example.vetclinic.presentation;

import java.sql.SQLException;

import animations.Shake;
import com.example.vetclinic.core.controllers.UserController;
import com.example.vetclinic.core.interfaces.AuthController;
import com.example.vetclinic.core.models.Doctor;
import com.example.vetclinic.core.models.Owner;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class AuthPresenter extends BaseController implements AuthController {

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passwField;

    @FXML
    private Button authButton;

    @FXML
    private Button logButton;

    @FXML
    void initialize() {
        authButton.setOnAction(event -> {
            String logText = loginField.getText().trim();
            String logPassword  = passwField.getText().trim();

            if(!logText.equals("") && !logPassword.equals("")) {
                try {
                    logUser(logText, logPassword);
                } catch (SQLException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        logButton.setOnAction(event -> {
            logButton.getScene().getWindow().hide();
            newWin("reg-view.fxml");
        });
    }

    private void logUser(String logText, String logPassword) throws SQLException, ClassNotFoundException {
        UserController userController = new UserController(this);
        userController.login(logText, logPassword);
    }

    public void openLkOwnWindow (Owner owner) throws SQLException, ClassNotFoundException {
        logButton.getScene().getWindow().hide();
        LkOwnPresenter lkOwnController = (LkOwnPresenter) newWin("lk-owner-view.fxml");
        lkOwnController.setOwner(owner);
    }

    public void openLkDocWindow (Doctor doctor) throws SQLException, ClassNotFoundException {
        logButton.getScene().getWindow().hide();
        LkDocPresenter lkDocController = (LkDocPresenter) newWin("lk-doctor-view.fxml");
        lkDocController.setDoctor(doctor);
    }

    public void enteredWrongData () {
        Shake logAmimation = new Shake(loginField);
        Shake passAnimation = new Shake(passwField);
        logAmimation.play();
        passAnimation.play();
    }
}
