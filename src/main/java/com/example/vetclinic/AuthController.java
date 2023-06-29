package com.example.vetclinic;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import animations.Shake;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class AuthController extends Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

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
        DBhandler.getInstance();
        DBhandler.getInstance();
        Owner owner = new Owner();
        owner.setLogin(logText);
        owner.setPassword(logPassword);
        ResultSet res = dbHandler.getUser(owner);

        int counter = 0;

        while (true) {
            try {
                if (!res.next()) break;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            counter++;
        }

        if (counter >= 1) {
            logButton.getScene().getWindow().hide();
            newWin("open-view.fxml");
        }
        else {
            Shake logAmimation = new Shake(loginField);
            Shake passAnimation = new Shake(passwField);
            logAmimation.play();
            passAnimation.play();
        }
    }

}
