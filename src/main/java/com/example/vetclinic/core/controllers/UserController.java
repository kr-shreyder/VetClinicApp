package com.example.vetclinic.core.controllers;

import com.example.vetclinic.core.interfaces.AuthController;
import com.example.vetclinic.core.interfaces.EditUserController;
import com.example.vetclinic.core.models.Doctor;
import com.example.vetclinic.core.models.Owner;
import com.example.vetclinic.core.models.User;
import com.example.vetclinic.presentation.AuthPresenter;
import com.example.vetclinic.presentation.Service;

import java.sql.SQLException;

public class UserController extends BaseController {
    AuthController authController;
    EditUserController editUserController;
    public UserController (AuthPresenter authController) throws SQLException, ClassNotFoundException {
        super();
        this.authController = authController;
    }
    public UserController (EditUserController editUserController) throws SQLException, ClassNotFoundException {
        super();
        this.editUserController = editUserController;
    }

    public void login(String login, String password) throws SQLException, ClassNotFoundException {
        User user = dbHandler.getUser(login, password);

        if (user != null && user.getRoleId() == 3) {
            Owner owner = dbHandler.getOwner(user.getId());
            authController.openLkOwnWindow(owner);
        }
        if (user != null && user.getRoleId() == 2) {
            Doctor doctor = dbHandler.getDoctor(user.getId());
            authController.openLkDocWindow(doctor);
        } else {
            authController.enteredWrongData();
        }
    }

    public void update(User user, String oldPassw, String newLogin, String newPassw) {
        oldPassw = Service.hashPassword(oldPassw);
        if (oldPassw.equals(user.getPassword())) {
            user.setLogin(newLogin);
            user.setPassword(newPassw);
            dbHandler.updateUser(user);
            editUserController.exit();
        }
    }
}
