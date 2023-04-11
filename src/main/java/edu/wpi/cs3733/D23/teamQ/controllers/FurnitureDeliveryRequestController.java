package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.db.Qdb;
import edu.wpi.cs3733.D23.teamQ.db.obj.FurnitureRequest;
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

public class FurnitureDeliveryRequestController {
  @FXML ChoiceBox assigneeField;
  @FXML ChoiceBox roomNumberField;
  @FXML MFXDatePicker dateField;
  @FXML MFXTextField timeField;
  @FXML MFXTextField specialInstructionsField;
  ObservableList<String> itemList =
      FXCollections.observableArrayList("Desk", "Desk Chair", "Couch", "Examination Table");
  @FXML ChoiceBox itemRequestedField;

  @FXML Button resetButton;
  @FXML Button cancelButton;
  @FXML Button submitButton;

  @FXML
  public void initialize() {
    this.itemRequestedField.setValue("Select Furniture Item");
    this.itemRequestedField.setItems(itemList);
  }

  @FXML
  public void resetButtonClicked() {
  }

  @FXML
  public void cancelButtonClicked() {
  }

  @FXML
  public void submitButtonClicked() {
    /*
    Qdb qdb = Qdb.getInstance();
    FurnitureRequest newFR =
            new FurnitureRequest(
                    0,
                    "temp user",
                    0,
                    assigneeField.getText(),
                    roomNumberField.getText(),
                    specialInstructionsField.getText(),
                    (String) itemRequestedField.getValue());
    // qdb.addConferenceRequest(newFR);
    Navigation.navigate(Screen.HOME);

     */
  }
}
