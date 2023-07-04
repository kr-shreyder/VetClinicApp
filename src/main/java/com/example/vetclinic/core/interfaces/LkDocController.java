package com.example.vetclinic.core.interfaces;

import java.sql.SQLException;

public interface LkDocController {
    void openEditDocWindow();
    void updateTableRec() throws SQLException, ClassNotFoundException;
}
