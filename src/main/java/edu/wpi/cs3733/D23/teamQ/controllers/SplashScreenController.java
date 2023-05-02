package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.db.Qdb;
import edu.wpi.cs3733.D23.teamQ.navigation.Navigation;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

public class SplashScreenController {
  @FXML MFXButton submitButton;
  @FXML MFXComboBox databaseField;

  ObservableList<String> dbList = FXCollections.observableArrayList("AWS", "WPI Hosted Database");

  @FXML
  public void initialize() {

    databaseField.setItems(dbList);
  }

  @FXML
  public void submitButtonClicked() {
    Qdb qdb = Qdb.getInstance();
    qdb.setServer(databaseField.getText());
    if (!databaseField.getText().isEmpty()) Navigation.navigateLogin();
  }
}
