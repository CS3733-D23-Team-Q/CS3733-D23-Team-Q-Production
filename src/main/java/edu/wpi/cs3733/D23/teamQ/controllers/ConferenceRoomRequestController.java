package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.db.Qdb;
import edu.wpi.cs3733.D23.teamQ.db.obj.ConferenceRequest;
import edu.wpi.cs3733.D23.teamQ.navigation.Navigation;
import edu.wpi.cs3733.D23.teamQ.navigation.Screen;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.MenuItem;

public class ConferenceRoomRequestController {
  @FXML MFXTextField assigneeField;
  @FXML MFXTextField roomNumberField;
  @FXML MFXDatePicker dateField;
  @FXML MFXTextField timeField;
  ObservableList<String> foodOptionsList =
      FXCollections.observableArrayList(
          "Brunch spread", "Dinner spread", "Snack spread", "No food");
  @FXML ChoiceBox foodField;
  @FXML MFXTextField specialInstructionsField;

  @FXML Button resetButton;
  @FXML Button cancelButton;
  @FXML Button submitButton;

  @FXML
  public void initialize() {
    this.foodField.setValue("Select Food Option");
    this.foodField.setItems(foodOptionsList);
  }

  @FXML
  public void resetButtonClicked() {
    assigneeField.clear();
    roomNumberField.clear();
    dateField.clear();
    timeField.clear();
    foodField.setValue("No food");
    specialInstructionsField.clear();
  }

  @FXML
  public void cancelButtonClicked() {
    Navigation.navigate(Screen.SERVICE_PLACEHOLDER);
  }

  @FXML
  public void submitButtonClicked() {
//    Qdb qdb = Qdb.getInstance();
//    ConferenceRequest newCCR =
//        new ConferenceRequest(
//            0,
//            "temp user",
//            0,
//            assigneeField.getText(),
//            roomNumberField.getText(),
//            specialInstructionsField.getText(),
//            dateTimeField.getText(),
//            (String) foodField.getValue());
//    qdb.addConferenceRequest(newCCR);
//    Navigation.navigate(Screen.HOME);
  }
}
