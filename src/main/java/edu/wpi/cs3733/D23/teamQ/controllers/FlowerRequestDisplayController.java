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

public class FlowerRequestDisplayController {

  @FXML
  MFXButton cancelButton;

  @FXML MFXButton backButton;

  @FXML MFXButton updateButton;

  @FXML
  ChoiceBox assigneeField;

  @FXML ChoiceBox roomNumberField;

  @FXML
  MFXDatePicker dateField;

  @FXML
  MFXTextField timeField;

  @FXML ChoiceBox flowerTypeField;

  @FXML MFXTextField bouquetChoiceField;

  @FXML MFXTextField specialInstructionsField;

  @FXML
  public void initialize() {/*
    roomNumberField.setText(ListServiceRequestController.getFlowerRequest().getRoomNumber());
    flowerNoteField.setText(ListServiceRequestController.getFlowerRequest().getNote());
    flowerChoiceField.setText(ListServiceRequestController.getFlowerRequest().getFlowerType());
    numberBouquetField.setText(
        String.valueOf(ListServiceRequestController.getFlowerRequest().getNumberOfBouquets()));
    assigneeField.setText(ListServiceRequestController.getFlowerRequest().getAssignee());
    specialInstructionsField.setText(
        ListServiceRequestController.getFlowerRequest().getSpecialInstructions());
        */
  }

  @FXML
  public void cancelButtonClicked() {}

  @FXML
  public void backButtonClicked() {}

  @FXML
  public void updateButtonClicked() {}
}
