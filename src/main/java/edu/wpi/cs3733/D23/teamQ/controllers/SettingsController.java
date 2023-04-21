package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.db.Qdb;
import edu.wpi.cs3733.D23.teamQ.navigation.Navigation;
import edu.wpi.cs3733.D23.teamQ.navigation.Screen;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXFilterComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

public class SettingsController {

  Qdb qdb = Qdb.getInstance();
  @FXML MFXFilterComboBox fontField;
  @FXML MFXFilterComboBox voiceField;
  @FXML MFXFilterComboBox languageField;
  @FXML MFXFilterComboBox algorithmField;

  ObservableList<String> fontList = FXCollections.observableArrayList("Small", "Normal", "Large");
  ObservableList<String> voiceList =
      FXCollections.observableArrayList("Wilson Wong", "Siri", "Morgan Freeman");
  ObservableList<String> languageList =
      FXCollections.observableArrayList("English", "Spanish", "French");
  ObservableList<String> algorithmList =
      FXCollections.observableArrayList("A*", "Dijkstra", "BFS", "DFS", "Q*");

  @FXML MFXButton backButton;

  /*
      this.assigneeField.setValue("");
    this.assigneeField.setItems(qdb.getAllNames());
    this.roomNumberField.setValue("");
    this.roomNumberField.setItems(qdb.getAllLongNames());
    this.itemRequestedField.setValue("");
    this.itemRequestedField.setItems(itemList);
    this.timeField.setValue("");
    this.timeField.setItems(timeList);

  */ @FXML
  public void initialize() {
    this.fontField.setValue("");
    this.fontField.setItems(fontList);
    this.voiceField.setValue("");
    this.voiceField.setItems(voiceList);
    this.languageField.setValue("");
    this.languageField.setItems(languageList);
    this.algorithmField.setValue("");
    this.algorithmField.setItems(algorithmList);
  };

  public void backButtonClicked() {
    Navigation.navigate(Screen.HOME);
  }
}
