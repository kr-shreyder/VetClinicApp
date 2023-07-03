package com.example.vetclinic;

import java.sql.SQLException;

import animations.Shake;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class AuthController extends Controller {

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
            else
                System.out.println("Логин и/или пароль не введены!");
        });

        logButton.setOnAction(event -> {
            logButton.getScene().getWindow().hide();
            newWin("reg-view.fxml");
        });
    }

    private void logUser(String logText, String logPassword) throws SQLException, ClassNotFoundException {
        DBhandler dbHandler = DBhandler.getInstance();
        User user = dbHandler.getUser(logText, logPassword);

        if (user != null && user.getRoleId() == 3) {
            Owner owner = DBhandler.getInstance().getOwner(user.getId());
            //System.out.println(pet.getName());
            logButton.getScene().getWindow().hide();
            LkOwnController lkOwnController = (LkOwnController) newWin("lk-owner-view.fxml");
            lkOwnController.setOwner(owner);
        }
        if (user != null && user.getRoleId() == 2) {
            Doctor doctor = DBhandler.getInstance().getDoctor(user.getId());
            //System.out.println(pet.getName());
            logButton.getScene().getWindow().hide();
            LkDocController lkDocController = (LkDocController) newWin("lk-doctor-view.fxml");
            lkDocController.setDoctor(doctor);
        }
        else {
            Shake logAmimation = new Shake(loginField);
            Shake passAnimation = new Shake(passwField);
            logAmimation.play();
            passAnimation.play();
        }
    }
}
