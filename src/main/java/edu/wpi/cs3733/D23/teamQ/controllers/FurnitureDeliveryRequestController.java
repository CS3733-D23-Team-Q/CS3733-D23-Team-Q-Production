package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.db.Qdb;
import edu.wpi.cs3733.D23.teamQ.db.obj.FurnitureRequest;
import edu.wpi.cs3733.D23.teamQ.navigation.Navigation;
import edu.wpi.cs3733.D23.teamQ.navigation.Screen;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import io.github.palexdev.materialfx.controls.MFXFilterComboBox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import java.sql.Date;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class FurnitureDeliveryRequestController {
  Qdb qdb = Qdb.getInstance();
  @FXML MFXFilterComboBox assigneeField;
  @FXML MFXFilterComboBox roomNumberField;
  @FXML MFXDatePicker dateField;
  @FXML MFXTextField timeField;
  @FXML MFXTextField specialInstructionsField;
  ObservableList<String> itemList =
      FXCollections.observableArrayList("Desk", "Desk Chair", "Couch", "Examination Table");
  @FXML MFXFilterComboBox itemRequestedField;

  @FXML Button resetButton;
  @FXML Button cancelButton;
  @FXML Button submitButton;

  @FXML
  public void initialize() {
    this.assigneeField.setValue("Select an Assignee");
    this.assigneeField.setItems(qdb.getAllNames());
    this.roomNumberField.setValue("Select a Location");
    this.roomNumberField.setItems(qdb.getAllLongNames());
    this.itemRequestedField.setValue("Select Furniture Item");
    this.itemRequestedField.setItems(itemList);
  }

  @FXML
  public void resetButtonClicked() {}

  @FXML
  public void cancelButtonClicked() {}

  @FXML
  public void submitButtonClicked() {
    Qdb qdb = Qdb.getInstance();

    FurnitureRequest newFR =
        new FurnitureRequest(
            LoginController.getLoginUsername(),
            0,
            assigneeField.getValue().toString(),
            qdb.retrieveNode(Integer.parseInt(roomNumberField.getValue().toString())),
            specialInstructionsField.getText(),
            Date.valueOf(dateField.getValue()),
            timeField.getText(),
            itemRequestedField.getValue().toString());

    qdb.addFurnitureRequest(newFR);
    Navigation.navigate(Screen.SUBMISSION);
  }
}
