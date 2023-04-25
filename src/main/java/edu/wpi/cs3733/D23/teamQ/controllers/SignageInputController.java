package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.db.Qdb;
import edu.wpi.cs3733.D23.teamQ.navigation.Navigation;
import edu.wpi.cs3733.D23.teamQ.navigation.Screen;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class SignageInputController {

  @FXML Button resetButton;
  @FXML Button cancelButton;
  @FXML Button submitButton;
  @FXML MFXComboBox kioskField;
  @FXML MFXComboBox dateField;

  ObservableList<Integer> kiosks = FXCollections.observableArrayList(1, 2);

  ObservableList<String> dates =
      FXCollections.observableArrayList("May 2023", "July 2023", "November 2023");

  @FXML
  public void initialize() {
    Qdb qdb = Qdb.getInstance();
    kioskField.setItems(kiosks);
    ;
    dateField.setItems(dates);
  }

  @FXML
  public void resetButtonClicked() {
    dateField.setValue("");
    kioskField.setValue("");
  }

  @FXML
  public void cancelButtonClicked() {
    Navigation.navigateRight(Screen.HOME);
  }

  @FXML
  public void submitButtonClicked() {
    Qdb qdb = Qdb.getInstance();
    qdb.setKiosk((Integer) kioskField.getValue());
    qdb.setDate(dateField.getText());
    Navigation.navigate(Screen.SIGNAGE);
  }
}
