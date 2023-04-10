package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.navigation.Navigation;
import edu.wpi.cs3733.D23.teamQ.navigation.Screen;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;

public class ConferenceRoomRequestDisplayController {

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
    /*
    roomNumberField.setText(ListServiceRequestController.getConferenceRequest().getRoomNumber());
    dateField.setText(ListServiceRequestController.getConferenceRequest().getDateTime());
    foodField.setText(ListServiceRequestController.getConferenceRequest().getFoodChoice());
    assigneeField.setText(ListServiceRequestController.getConferenceRequest().getAssignee());
    specialInstructionsField.setText(
        ListServiceRequestController.getConferenceRequest().getSpecialInstructions());

     */
  }

  @FXML
  public void cancelButtonClicked() {}

  @FXML
  public void backButtonClicked() {}

  @FXML
  public void updateButtonClicked() {}
}
