package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.db.Qdb;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;

public class OfficeSuppliesRequestController {
  Qdb qdb = Qdb.getInstance();
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
    this.assigneeField.setValue("Select an Assignee");
    this.assigneeField.setItems(qdb.getAllNames());
    this.roomNumberField.setValue("Select a Location");
    this.roomNumberField.setItems(qdb.getAllLongNames());
    this.itemRequestedField.setValue("Select Item");
    this.itemRequestedField.setItems(itemList);
  }

  @FXML
  public void resetButtonClicked() {}

  @FXML
  public void cancelButtonClicked() {}

  @FXML
  public void submitButtonClicked() {
    /*
        Qdb qdb = Qdb.getInstance();
        if (((String) quantityField.getValue()).equals("Select Quantity")) {
          quantityField.setValue("0");
        }

    //    OfficeSuppliesRequest newOSR =
    //        new OfficeSuppliesRequest(
    //            0,
    //            "temp user",
    //            0,
    //            assigneeField.getText(),
    //            qdb.retrieveNode(Integer.parseInt(roomNumberField.getText())),
    //            specialInstructionsField.getText(),
    //            //for date
    //                //for time
    //            (String) itemField.getValue(),
    //            Integer.parseInt((String) quantityField.getValue()));


        // qdb.addFlowerRequest(newOSR);
        Navigation.navigate(Screen.HOME);

         */
  }
}
