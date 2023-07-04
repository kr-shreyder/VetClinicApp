package com.example.vetclinic.core.interfaces;

import com.example.vetclinic.core.models.Doctor;
import com.example.vetclinic.core.models.Owner;

import java.sql.SQLException;

public interface AuthController {
    public void openLkOwnWindow (Owner owner) throws SQLException, ClassNotFoundException;
    public void openLkDocWindow (Doctor doctor) throws SQLException, ClassNotFoundException;
    public void enteredWrongData ();
}
