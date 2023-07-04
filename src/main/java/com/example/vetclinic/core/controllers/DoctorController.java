package com.example.vetclinic.core.controllers;

import com.example.vetclinic.core.interfaces.LkDocController;

import java.sql.SQLException;

public class DoctorController extends BaseController {
    LkDocController lkDocController;
    public DoctorController(LkDocController lkDocController) throws SQLException, ClassNotFoundException {
        super();
        this.lkDocController = lkDocController;
    }

    public void get() {

    }

    public void update() {

    }

    public void delete() {

    }
}
