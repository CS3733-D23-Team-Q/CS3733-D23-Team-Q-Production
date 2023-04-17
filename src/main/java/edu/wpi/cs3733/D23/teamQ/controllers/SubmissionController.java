package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.db.Qdb;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class SubmissionController {

  @FXML Label requestID;

  public void initialize() {
    Qdb qdb = Qdb.getInstance();
    requestID.setText(
        "Request ID : "
            + qdb.retrieveAllServiceRequests()
                .get(qdb.retrieveAllServiceRequests().size() - 1)
                .getRequestID());
  }
}
