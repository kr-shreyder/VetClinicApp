package com.example.vetclinic.core.controllers;

import com.example.vetclinic.core.interfaces.EditRecController;
import com.example.vetclinic.core.models.Reception;
import com.example.vetclinic.presentation.CreateRecControllerImpl;
import com.example.vetclinic.presentation.EditRecControllerImpl;
import com.example.vetclinic.presentation.LkDocControllerImpl;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;

public class ReceptionController extends BaseController {
    EditRecController editRecController;
    CreateRecControllerImpl createRecController;
    LkDocControllerImpl lkDocController;
    public ReceptionController(CreateRecControllerImpl createRecController) throws SQLException, ClassNotFoundException {
        super();
        this.createRecController = createRecController;
    }
    public ReceptionController(EditRecControllerImpl editRecController) throws SQLException, ClassNotFoundException {
        super();
        this.editRecController = editRecController;
    }
    public ReceptionController(LkDocControllerImpl lkDocController) throws SQLException, ClassNotFoundException {
        super();
        this.lkDocController = lkDocController;
    }

    public void create(Date date, Time time, int doctorId, int ownerId, int petId, ArrayList<String> diseases) throws SQLException, ClassNotFoundException {
        Reception rec = new Reception(date, time, doctorId, ownerId, petId, diseases);
        dbHandler.createRec(rec);
        createRecController.exit();
    }

    public void get() {

    }

    public void update(Reception reception, Date date, Time time, int ownerId, int petId, ArrayList<String> diseases) throws SQLException, ClassNotFoundException {
        reception.setDate(date);
        reception.setTime(time);
        reception.setOwnerId(ownerId);
        reception.setPetId(petId);
        reception.setDiseaseName(diseases);

        dbHandler.updateRec(reception);
        editRecController.exit();
    }

    public void delete(Reception rec) throws SQLException, ClassNotFoundException {
        dbHandler.deleteRec(rec.getId());
        lkDocController.updateTableRec();
    }
}
