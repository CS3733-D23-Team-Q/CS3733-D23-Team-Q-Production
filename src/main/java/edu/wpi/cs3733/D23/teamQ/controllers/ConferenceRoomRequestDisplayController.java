package edu.wpi.cs3733.D23.teamQ.controllers;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;

public class ConferenceRoomRequestDisplayController {

  // NEED TO SET ALL THE CHOICE BOXES

  @FXML MFXButton cancelButton;

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
    assigneeField.setValue(ListServiceRequestController.getConferenceRequest().getAssignee());
    roomNumberField.setValue(ListServiceRequestController.getConferenceRequest().getRoomNumber());
    // dateField.setValue(ListServiceRequestController.getConferenceRequest().getDate);
    // timeField.setText(ListServiceRequestController.getConferenceRequest().getTime);
    foodField.setValue(ListServiceRequestController.getConferenceRequest().getFoodChoice());
    specialInstructionsField.setText(
        ListServiceRequestController.getConferenceRequest().getSpecialInstructions());
  }

  @FXML
  public void cancelButtonClicked() {}

  @FXML
  public void backButtonClicked() {}

  @FXML
  public void updateButtonClicked() {}
}
