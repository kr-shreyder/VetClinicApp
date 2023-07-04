package com.example.vetclinic.core.interfaces;

import com.example.vetclinic.core.models.Doctor;
import com.example.vetclinic.core.models.Owner;

import java.sql.SQLException;

public interface AuthController {
    void openLkOwnWindow (Owner owner) throws SQLException, ClassNotFoundException;
    void openLkDocWindow (Doctor doctor) throws SQLException, ClassNotFoundException;
    void enteredWrongData ();
}
