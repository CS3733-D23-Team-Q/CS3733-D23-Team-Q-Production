package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.db.Qdb;
import edu.wpi.cs3733.D23.teamQ.db.obj.ServiceRequest;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.LinkedList;

public class SubmissionController {

    @FXML Label requestID;

    public void initialize(){
        Qdb qdb = Qdb.getInstance();
        //NEED TO CHECK CORRECT NUMBER IS RETURNED
        requestID.setText("Request ID : " + qdb.retrieveAllServiceRequests().get(qdb.retrieveAllServiceRequests().size()));
    }
}
