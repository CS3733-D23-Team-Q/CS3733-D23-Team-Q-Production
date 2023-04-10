package edu.wpi.cs3733.D23.teamQ.controllers;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;

public class FlowerRequestDisplayController {

  @FXML MFXButton cancelButton;

  @FXML MFXButton backButton;

  @FXML MFXButton updateButton;

  @FXML ChoiceBox assigneeField;

  // NEED TO SET ALL THE CHOICE BOXES

  @FXML ChoiceBox roomNumberField;

  @FXML MFXDatePicker dateField;

  @FXML MFXTextField timeField;

  @FXML ChoiceBox flowerTypeField;

  @FXML MFXTextField bouquetChoiceField;

  @FXML MFXTextField specialInstructionsField;

  @FXML
  public void initialize() {
    assigneeField.setValue(ListServiceRequestController.getFlowerRequest().getAssignee());
    roomNumberField.setValue(ListServiceRequestController.getFlowerRequest().getRoomNumber());
    // dateField.setValue(ListServiceRequestController.getFlowerRequest().getDate);
    // timeField.setText(ListServiceRequestController.getFlowerRequest().getTime);
    flowerTypeField.setValue(ListServiceRequestController.getFlowerRequest().getFlowerType());
    bouquetChoiceField.setText(
        String.valueOf(ListServiceRequestController.getFlowerRequest().getNumberOfBouquets()));
    specialInstructionsField.setText(
        ListServiceRequestController.getFlowerRequest().getSpecialInstructions());
  }

  @FXML
  public void cancelButtonClicked() {}

  @FXML
  public void backButtonClicked() {}

  @FXML
  public void updateButtonClicked() {}
}
