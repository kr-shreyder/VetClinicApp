package com.example.vetclinic.core.controllers;

import com.example.vetclinic.db.DBhandler;

import java.sql.SQLException;

public class BaseController {

    protected final DBhandler dbHandler;

    public BaseController() throws SQLException, ClassNotFoundException {
        this.dbHandler = DBhandler.getConnect();
    }
}
