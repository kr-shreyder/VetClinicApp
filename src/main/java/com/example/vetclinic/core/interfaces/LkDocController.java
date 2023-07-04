package com.example.vetclinic.core.interfaces;

import java.sql.SQLException;

public interface LkDocController {
    public void openEditDocWindow();
    public void updateTableRec() throws SQLException, ClassNotFoundException;
}
