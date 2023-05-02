package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.db.Qdb;
import edu.wpi.cs3733.D23.teamQ.db.obj.*;
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
  String username = LoginController.getUsername();
  // boolean isStartSelected;
  // @FXML MFXFilterComboBox<String> startLocSelect;
  @FXML MFXToggleButton soundToggle;

  @FXML MFXFilterComboBox tableField;
  @FXML MFXTextField filenameField;
  @FXML MFXButton submitButton;
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
      FXCollections.observableArrayList("ASTAR", "DJIKSTRA", "BFS", "DFS", "Q*");

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
    this.soundToggle.setSelected(
        qdb.retrieveSettings(LoginController.getLoginUsername()).isSound());

    soundToggle
        .selectedProperty()
        .addListener(
            new ChangeListener<Boolean>() {
              @Override
              public void changed(
                  ObservableValue<? extends Boolean> observable,
                  Boolean oldValue,
                  Boolean newValue) {

                Settings settings =
                    new Settings(
                        LoginController.getLoginUsername(),
                        qdb.retrieveSettings(LoginController.getLoginUsername()).isTwoFactor(),
                        newValue,
                        qdb.retrieveSettings(LoginController.getLoginUsername())
                            .getAlgorithm()
                            .ordinal(),
                        qdb.retrieveSettings(LoginController.getLoginUsername())
                            .getVoice()
                            .ordinal());
                qdb.updateSettingsRow(LoginController.getLoginUsername(), settings);
              }
            });

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
              }
            });

    preferredAlgorithm
        .valueProperty()
        .addListener(
            new ChangeListener<>() {
              @Override
              public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                Settings.Algorithm algo =
                    Settings.Algorithm.valueOf(preferredAlgorithm.getValue().toString());

                //                if (newValue.toString().equals("A*")) algo =
                // Settings.Algorithm.ASTAR;
                //                if (newValue.toString().equals("BFS")) algo =
                // Settings.Algorithm.BFS;
                //                if (newValue.toString().equals("DFS")) algo =
                // Settings.Algorithm.DFS;
                //                if (newValue.toString().equals("DIJKSTRA")) algo =
                // Settings.Algorithm.DJIKSTRA;

                Settings settings =
                    new Settings(
                        LoginController.getLoginUsername(),
                        qdb.retrieveSettings(LoginController.getLoginUsername()).isTwoFactor(),
                        qdb.retrieveSettings(LoginController.getLoginUsername()).isSound(),
                        algo.ordinal(),
                        qdb.retrieveSettings(LoginController.getLoginUsername())
                            .getVoice()
                            .ordinal());
                qdb.updateSettingsRow(LoginController.getLoginUsername(), settings);
              }
            });

    /*
    List<Node> latestNodes = PathfindingController.getLatestNodes();
    List<String> lnames = new ArrayList<>();
    for (Node n : latestNodes) {
      String lname = n.getLocation().getLongName();
      String nodetype = n.getLocation().getNodeType();
      if (!lnames.contains(lname)) {
        if (!lnames.contains(lname)
            && !nodetype.equals("HALL")
            && !nodetype.equals("ELEV")
            && !nodetype.equals("STAI")) {
          lnames.add(lname);
        }
      }
    }
    startLocSelect.getItems().add("null");
    for (String lname : lnames) {
      startLocSelect.getItems().add(lname);
    }
    isStartSelected = false;
     */
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

  /*
  public void startLocSelected() {
    String startLoc = startLocSelect.getValue();
    if (startLoc != null && !startLoc.equals("")) {
      isStartSelected = true;
    }
  }

  public void saveButtonClicked() {
    if (isStartSelected) {
      String startLocs = startLocSelect.getValue();
      if (startLocs.equals("null")) {
        if (qdb.getDefaultLocationIndex(username) != -1) {
          qdb.deleteDefaultLocation(username);
        }
      } else {
        Location startLocl = qdb.getNodeFromLocation(startLocs).getLocation();
        if (qdb.getDefaultLocationIndex(username) != -1) {
          qdb.updateDefaultLocation(username, new DefaultLocation(username, startLocl));
        } else {
          qdb.addDefaultLocation(new DefaultLocation(username, startLocl));
        }
      }
    }
  }
   */

  public void setupButtonClicked() {}
}
