package com.example.vetclinic.core.interfaces;

import java.sql.SQLException;

public interface LkOwnController {
    public void openEditOwnWindow ();
    public void updateTableData() throws SQLException, ClassNotFoundException;
}
