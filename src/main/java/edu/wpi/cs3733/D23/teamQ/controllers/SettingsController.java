package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.db.Qdb;
import edu.wpi.cs3733.D23.teamQ.db.obj.Account;
import edu.wpi.cs3733.D23.teamQ.db.obj.Settings;
import edu.wpi.cs3733.D23.teamQ.navigation.Navigation;
import edu.wpi.cs3733.D23.teamQ.navigation.Screen;
import io.github.palexdev.materialfx.controls.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
      FXCollections.observableArrayList("Male", "Female", "Snoop Dogg");
  ObservableList<String> algorithmList =
      FXCollections.observableArrayList("A*", "Dijkstra", "BFS", "DFS", "Q*");

  @FXML
  public void initialize() {
    String username = LoginController.getLoginUsername();
    Account account = qdb.retrieveAccount(username);
    this.voiceSelection.setText(
        qdb.retrieveSettings(LoginController.getLoginUsername()).getVoice().toString());
    this.voiceSelection.setItems(voiceList);
    tableField.setItems(tableList);
    this.preferredAlgorithm.setText(
        qdb.retrieveSettings(LoginController.getLoginUsername()).getAlgorithm().toString());
    this.preferredAlgorithm.setItems(algorithmList);
    Color main = Color.web("012D5A", 1.0);
    Color secondary = Color.web("7D7D7D", 1.0);
    soundToggle.setColors(main, secondary);
    this.soundToggle.setSelected(true);

    voiceSelection
        .valueProperty()
        .addListener(
            new ChangeListener<>() {
              @Override
              public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                Settings.Voice voice = null;

                if (newValue.toString().equals("Female")) voice = Settings.Voice.FEMALE;
                if (newValue.toString().equals("Male")) voice = Settings.Voice.MALE;
                if (newValue.toString().equals("Snoop Dogg")) voice = Settings.Voice.SNOOP;

                Settings settings =
                    new Settings(
                        LoginController.getLoginUsername(),
                        qdb.retrieveSettings(LoginController.getLoginUsername()).isTwoFactor(),
                        qdb.retrieveSettings(LoginController.getLoginUsername()).isSound(),
                        qdb.retrieveSettings(LoginController.getLoginUsername())
                            .getAlgorithm()
                            .ordinal(),
                        voice.ordinal());
                qdb.updateSettingsRow(LoginController.getLoginUsername(), settings);

                System.out.println("Setting Voice to" + voice);
              }
            });
  }

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
