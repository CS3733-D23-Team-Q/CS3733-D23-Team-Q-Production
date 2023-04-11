package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.db.Qdb;
import edu.wpi.cs3733.D23.teamQ.db.obj.OfficeSuppliesRequest;
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

public class OfficeSuppliesRequestController {
  @FXML ChoiceBox assigneeField;
  @FXML ChoiceBox roomNumberField;
  @FXML MFXDatePicker dateField;
  @FXML MFXTextField timeField;
  @FXML MFXTextField specialInstructionsField;
  @FXML ChoiceBox itemRequestedField;
  ObservableList<String> itemList =
      FXCollections.observableArrayList(
          "Printer Paper (by ream)", "Pencil", "Pen", "Highlighter", "Notepad");
  @FXML MFXTextField quantityField;

  @FXML Button resetButton;
  @FXML Button cancelButton;
  @FXML Button submitButton;


  @FXML
  public void initialize() {
    this.itemRequestedField.setValue("Select Item");
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
    if (((String) quantityField.getValue()).equals("Select Quantity")) {
      quantityField.setValue("0");
    }
    OfficeSuppliesRequest newOSR =
        new OfficeSuppliesRequest(
            0,
            "temp user",
            0,
            assigneeField.getText(),
            qdb.retrieveNode(Integer.parseInt(roomNumberField.getText())),
            specialInstructionsField.getText(),
            (String) itemField.getValue(),
            Integer.parseInt((String) quantityField.getValue()));
    // qdb.addFlowerRequest(newOSR);
    Navigation.navigate(Screen.HOME);

     */
  }
}
