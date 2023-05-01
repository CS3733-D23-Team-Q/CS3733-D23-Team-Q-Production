package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.db.Qdb;
import edu.wpi.cs3733.D23.teamQ.navigation.Navigation;
import edu.wpi.cs3733.D23.teamQ.navigation.Screen;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class SubmissionController {

  @FXML Label requestID;

  public void initialize() {
    Qdb qdb = Qdb.getInstance();
    requestID.setText("");
  }

  @FXML
  public void homeButtonClicked(ActionEvent event) {
    Navigation.navigate(Screen.HOME);
  }
}
