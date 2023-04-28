package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.db.Qdb;
import edu.wpi.cs3733.D23.teamQ.db.obj.Account;
import edu.wpi.cs3733.D23.teamQ.navigation.Navigation;
import edu.wpi.cs3733.D23.teamQ.navigation.Screen;
import io.github.palexdev.materialfx.controls.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.paint.Color;

public class SettingsController {

  Qdb qdb = Qdb.getInstance();
  @FXML MFXToggleButton soundToggle;

  @FXML MFXFilterComboBox tableField;
  @FXML MFXTextField filenameField;
  @FXML MFXButton submitButton;
  @FXML MFXButton setupButton;
  @FXML MFXComboBox preferredAlgorithm;
  @FXML MFXComboBox voiceSelection;

  ObservableList<String> tableList =
      FXCollections.observableArrayList(
          "Accounts",
          "Edges",
          "Locations",
          "Moves",
          "Nodes",
          "Messages",
          "Alerts",
          "Signs",
          "Service requests");

  ObservableList<String> voiceList =
      FXCollections.observableArrayList("Male", "Female");
  ObservableList<String> algorithmList =
      FXCollections.observableArrayList("A*", "Dijkstra", "BFS", "DFS", "Q*");

  @FXML
  public void initialize() {
    String username = LoginController.getLoginUsername();
    Account account = qdb.retrieveAccount(username);
    this.voiceSelection.setValue("");
    this.voiceSelection.setItems(voiceList);
    tableField.setItems(tableList);
    this.preferredAlgorithm.setValue("");
    this.preferredAlgorithm.setItems(algorithmList);
    Color main = Color.web("012D5A", 1.0);
    Color secondary = Color.web("7D7D7D", 1.0);
    soundToggle.setColors(main, secondary);
    this.soundToggle.setSelected(true);
  };

  public void backButtonClicked() {
    Navigation.navigate(Screen.HOME);
  }

  public void submitButtonClicked() {
    if (filenameField.getText().equals("")) {
      if (qdb.exportToCSV(tableField.getText())) {
        System.out.println("Exported Successfully!");
      } else {
        System.out.println("Failed to Export.");
      }
    } else {
      if (qdb.exportToCSV(tableField.getText(), filenameField.getText())) {
        System.out.println("Exported Successfully");
      } else {
        System.out.println("Failed to Export.");
      }
    }
  }

  public void setupButtonClicked() {}
}
