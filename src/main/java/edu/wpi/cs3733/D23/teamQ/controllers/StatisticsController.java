package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.db.Qdb;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class StatisticsController {
  Qdb qdb = Qdb.getInstance();
  @FXML Label users;
  @FXML Label nodes;
  @FXML Label requests;

  @FXML
  public void initialize() {
    users.setText("Total users: " + qdb.retrieveAllPeople().size());
    nodes.setText("Total nodes: " + qdb.retrieveAllNodes().size());
    requests.setText("Total requests: " + qdb.retrieveAllServiceRequests().size());
  }
}
