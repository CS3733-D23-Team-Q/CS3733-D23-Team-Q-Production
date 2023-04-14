package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.db.Qdb;
import edu.wpi.cs3733.D23.teamQ.db.obj.ConferenceRequest;
import edu.wpi.cs3733.D23.teamQ.navigation.Navigation;
import edu.wpi.cs3733.D23.teamQ.navigation.Screen;
import io.github.palexdev.materialfx.controls.*;

import java.sql.Date;
import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

public class ConferenceRoomRequestDisplayController implements IController {

  ObservableList<String> foodOptionsList =
      FXCollections.observableArrayList(
          "Brunch spread", "Dinner spread", "Snack spread", "No food");

  @FXML MFXButton deleteButton;

  @FXML MFXButton backButton;

  @FXML MFXButton updateButton;
  @FXML
  MFXFilterComboBox assigneeField;
  @FXML MFXFilterComboBox roomNumberField;
  @FXML MFXDatePicker dateField;
  @FXML MFXTextField timeField;
  @FXML MFXFilterComboBox foodField;
  @FXML MFXTextField specialInstructionsField;

  @FXML
  public void initialize() {
    Qdb qdb = Qdb.getInstance();
    this.assigneeField.setItems(qdb.getAllNames());
    String[] conf = {"CONF"};
    this.roomNumberField.setItems(qdb.getAllLongNames(conf));
    this.foodField.setItems(foodOptionsList);

    assigneeField.setText(ListServiceRequestController.getConferenceRequest().getAssignee());
    roomNumberField.setText(
        ListServiceRequestController.getConferenceRequest().getNode().toString());
    dateField.setValue(
        LocalDate.of(
            ListServiceRequestController.getConferenceRequest().getDate().getYear(),
            ListServiceRequestController.getConferenceRequest().getDate().getMonth(),
            ListServiceRequestController.getConferenceRequest().getDate().getDay()));
    timeField.setText(ListServiceRequestController.getConferenceRequest().getTime());
    foodField.setText(ListServiceRequestController.getConferenceRequest().getFoodChoice());
    specialInstructionsField.setText(
        ListServiceRequestController.getConferenceRequest().getSpecialInstructions());
  }

  @FXML
  public void deleteButtonClicked() {
    Qdb qdb = Qdb.getInstance();
    qdb.deleteConferenceRequest(ListServiceRequestController.getConferenceRequest().getRequestID());
    Navigation.navigateRight(Screen.SERVICE_PLACEHOLDER);
  }

  @FXML
  public void backButtonClicked() {
    Navigation.navigateRight(Screen.SERVICE_PLACEHOLDER);
  }

  // Update with proper date and time
  @FXML
  public void updateButtonClicked() {
    Qdb qdb = Qdb.getInstance();

    ConferenceRequest newCCR =
        new ConferenceRequest(
            ListServiceRequestController.getConferenceRequest().getRequestID(),
            ListServiceRequestController.getConferenceRequest().getNode(),
            "temp user",
            (String) assigneeField.getValue(),
            0,
            specialInstructionsField.getText(),
            Date.valueOf(dateField.getValue()),
            timeField.getText(),
            (String) foodField.getValue());
    qdb.updateConferenceRequest(
        ListServiceRequestController.getConferenceRequest().getRequestID(), newCCR);
    Navigation.navigateRight(Screen.HOME);
  }
}
