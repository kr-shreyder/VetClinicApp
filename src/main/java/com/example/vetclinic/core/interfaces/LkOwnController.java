package com.example.vetclinic.core.interfaces;

import java.sql.SQLException;

public interface LkOwnController {
    void openEditOwnWindow ();
    void updateTableData() throws SQLException, ClassNotFoundException;
}
