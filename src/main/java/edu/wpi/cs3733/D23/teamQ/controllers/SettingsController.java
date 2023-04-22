package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.db.Qdb;
import edu.wpi.cs3733.D23.teamQ.db.obj.Account;
import edu.wpi.cs3733.D23.teamQ.navigation.Navigation;
import edu.wpi.cs3733.D23.teamQ.navigation.Screen;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXFilterComboBox;
import io.github.palexdev.materialfx.controls.MFXToggleButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class SettingsController {

  Qdb qdb = Qdb.getInstance();

  @FXML MFXFilterComboBox fontField;
  @FXML MFXFilterComboBox voiceField;
  @FXML MFXFilterComboBox languageField;
  @FXML MFXFilterComboBox algorithmField;
  @FXML MFXToggleButton darkModeToggle;
  @FXML MFXToggleButton soundToggle;
  @FXML private Label emailDisplay;
  @FXML private Label phoneDisplay;

  ObservableList<String> fontList = FXCollections.observableArrayList("Small", "Normal", "Large");
  ObservableList<String> voiceList =
      FXCollections.observableArrayList("Wilson Wong", "Siri", "Morgan Freeman");
  ObservableList<String> languageList =
      FXCollections.observableArrayList("English", "Spanish", "French");
  ObservableList<String> algorithmList =
      FXCollections.observableArrayList("A*", "Dijkstra", "BFS", "DFS", "Q*");

  @FXML MFXButton backButton;

  @FXML
  public void initialize() {
    String username = LoginController.getLoginUsername();
    Account account = qdb.retrieveAccount(username);

    this.fontField.setValue("");
    this.fontField.setItems(fontList);
    this.voiceField.setValue("");
    this.voiceField.setItems(voiceList);
    this.languageField.setValue("");
    this.languageField.setItems(languageList);
    this.algorithmField.setValue("");
    this.algorithmField.setItems(algorithmList);

    this.darkModeToggle.setSelected(false);
    this.soundToggle.setSelected(true);

    this.emailDisplay.setText(account.getEmail());
    //    this.phoneDisplay.setText("test");
    this.phoneDisplay.setText(String.valueOf(account.getPhoneNumber()));
  };

  public void backButtonClicked() {
    Navigation.navigate(Screen.HOME);
  }
}
