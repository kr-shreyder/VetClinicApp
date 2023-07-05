package com.example.vetclinic.core.controllers;

import com.example.vetclinic.core.models.Owner;
import com.example.vetclinic.core.models.User;
import com.example.vetclinic.presentation.RegPresenter;

import java.sql.SQLException;

public class OwnerController extends BaseController {
    RegPresenter regController;

    public OwnerController(RegPresenter regController) throws SQLException, ClassNotFoundException {
        super();
        this.regController = regController;
    }

    public void create(String login, String password, String name, String phoneNumber, String address) throws SQLException, ClassNotFoundException {
        User user = new User(login, password);
        dbHandler.createUser(user);
        Owner owner = new Owner(user.getId(), name, phoneNumber, address);
        dbHandler.createOwner(owner);
        regController.exit();
    }
}
