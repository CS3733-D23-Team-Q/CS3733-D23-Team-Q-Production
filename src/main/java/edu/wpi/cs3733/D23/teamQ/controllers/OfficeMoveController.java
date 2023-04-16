package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.db.Qdb;
import edu.wpi.cs3733.D23.teamQ.db.obj.Move;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import javafx.fxml.FXML;

public class OfficeMoveController {
  Qdb qdb = Qdb.getInstance();
  @FXML MFXComboBox currentLocationField;
  @FXML MFXComboBox newLocationField;
  @FXML MFXDatePicker dateField;

  public void initialize() {
    this.currentLocationField.setValue("Select Current Location");
    this.currentLocationField.setItems(qdb.getAllLongNames());
    this.newLocationField.setValue("Select Current Location");
    this.newLocationField.setItems(qdb.getAllLongNames());
  }

  public void resetButtonClicked() {
    this.currentLocationField.setValue("Select Current Location");
    this.newLocationField.setValue("Select Current Location");
    dateField.clear();
  }

  public void submitButtonClicked() {
    if(currentLocationField.getValue() != null && newLocationField.getValue() != null && dateField.getValue() != null) {
      Move newMove =
              new Move(
                      qdb.retrieveNode(qdb.getNodeFromLocation(currentLocationField.getSelectedItem().toString())),
                      newLocationField.getSelectedItem().toString(),
                      dateField.getValue().toString());

      qdb.addMove(newMove);
    }

  }
}
