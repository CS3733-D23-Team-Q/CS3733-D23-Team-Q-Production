package edu.wpi.cs3733.D23.teamQ.controllers;

import static edu.wpi.cs3733.D23.teamQ.Main.refresh;

import edu.wpi.cs3733.D23.teamQ.db.ConnectionBuilder;
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
    String serverSelected = databaseField.getText();
    if (serverSelected.equals("AWS")) {
      serverSelected =
          "jdbc:postgresql://teamq1.cthn9p80jqzf.us-east-1.rds.amazonaws.com:5432/teamq";
    } else if (serverSelected.equals("WPI Hosted Database")) {
      serverSelected = "jdbc:postgresql://database.cs.wpi.edu:5432/teamqdb";
    }
    ConnectionBuilder.setUrl(serverSelected);
    if (!databaseField.getText().isEmpty()) Navigation.navigateLogin();
    refresh.start();
  }
}
