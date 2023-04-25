package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.db.Qdb;

import edu.wpi.cs3733.D23.teamQ.db.obj.Sign;

import edu.wpi.cs3733.D23.teamQ.navigation.Navigation;
import edu.wpi.cs3733.D23.teamQ.navigation.Screen;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXFilterComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class SignageFormController {

  @FXML Button resetButton;
  @FXML Button cancelButton;
  @FXML Button submitButton;
  @FXML MFXComboBox directionField;
  @FXML MFXComboBox kioskField;
  @FXML MFXFilterComboBox destinationField;
  @FXML MFXComboBox dateField;

  ObservableList<String> directions =
      FXCollections.observableArrayList("Up", "Down", "Left", "Right", "Here");
  ObservableList<Integer> kiosks = FXCollections.observableArrayList(1, 2);

  ObservableList<String> dates =
      FXCollections.observableArrayList("May 2023", "July 2023", "November 2023");

  @FXML
  public void initialize() {
    Qdb qdb = Qdb.getInstance();
    directionField.setText("");
    directionField.setItems(directions);
    destinationField.setText("");
    destinationField.setItems(qdb.getAllLongNames());
    kioskField.setText("");
    kioskField.setItems(kiosks);
    dateField.setText("");
    dateField.setItems(dates);
  }

  @FXML
  public void resetButtonClicked() {
    dateField.setValue("");
    directionField.setValue("");
    destinationField.setValue("");
    kioskField.setValue("");
  }

  @FXML
  public void cancelButtonClicked() {


    Navigation.navigateRight(Screen.HOME);
  }

  @FXML
  public void submitButtonClicked() {
    Qdb qdb = Qdb.getInstance();

    Sign newSign = new Sign((Integer) kioskField.getValue(),dateField.getText(),destinationField.getText(),directionField.getText());
    qdb.addSign(newSign);
  }
}
