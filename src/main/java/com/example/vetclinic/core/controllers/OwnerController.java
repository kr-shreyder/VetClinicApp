package com.example.vetclinic.core.controllers;

import com.example.vetclinic.core.interfaces.LkOwnController;
import com.example.vetclinic.core.models.Owner;
import com.example.vetclinic.core.models.User;
import com.example.vetclinic.db.DBhandler;
import com.example.vetclinic.presentation.RegControllerImpl;

import java.sql.SQLException;

public class OwnerController extends BaseController {
    LkOwnController lkOwnController;
    RegControllerImpl regController;

    public OwnerController(LkOwnController lkOwnController) throws SQLException, ClassNotFoundException {
        super();
        this.lkOwnController = lkOwnController;
    }
    public OwnerController(RegControllerImpl regController) throws SQLException, ClassNotFoundException {
        super();
        this.regController = regController;
    }

    public void create(String login, String password, String name, String phoneNumber, String address) {
        User user = new User(login, password);
        dbHandler.createUser(user);
        Owner owner = new Owner(user.getId(), name, phoneNumber, address);
        dbHandler.createOwner(owner);
    }
}
