package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.db.Qdb;
import edu.wpi.cs3733.D23.teamQ.db.obj.ConferenceRequest;
import edu.wpi.cs3733.D23.teamQ.navigation.Navigation;
import edu.wpi.cs3733.D23.teamQ.navigation.Screen;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;

import java.sql.Date;
import java.time.LocalDate;

public class ConferenceRoomRequestDisplayController implements IController {

  ObservableList<String> foodOptionsList =
      FXCollections.observableArrayList(
          "Brunch spread", "Dinner spread", "Snack spread", "No food");

  @FXML MFXButton deleteButton;

  @FXML MFXButton backButton;

  @FXML MFXButton updateButton;
  @FXML ChoiceBox assigneeField;
  @FXML ChoiceBox roomNumberField;
  @FXML MFXDatePicker dateField;
  @FXML MFXTextField timeField;
  @FXML ChoiceBox foodField;
  @FXML MFXTextField specialInstructionsField;

  @FXML
  public void initialize() {

    this.foodField.setItems(foodOptionsList);

    assigneeField.setValue(ListServiceRequestController.getConferenceRequest().getAssignee());
    roomNumberField.setValue(ListServiceRequestController.getConferenceRequest().getNode());
    dateField.setValue(LocalDate.of(ListServiceRequestController.getConferenceRequest().getDate().getYear(),ListServiceRequestController.getConferenceRequest().getDate().getMonth(), ListServiceRequestController.getConferenceRequest().getDate().getDay()));
    timeField.setText(ListServiceRequestController.getConferenceRequest().getTime());
    foodField.setValue(ListServiceRequestController.getConferenceRequest().getFoodChoice());
    specialInstructionsField.setText(ListServiceRequestController.getConferenceRequest().getSpecialInstructions());
  }

  @FXML
  public void deleteButtonClicked() {
    Qdb qdb = Qdb.getInstance();
    qdb.deleteConferenceRequest(ListServiceRequestController.getConferenceRequest().getRequestID());
  }

  @FXML
  public void backButtonClicked() {
    Navigation.navigate(Screen.HOME);}

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

  }
}
