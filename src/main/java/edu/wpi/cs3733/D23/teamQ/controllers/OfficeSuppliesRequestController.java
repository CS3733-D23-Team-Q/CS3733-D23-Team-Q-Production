package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.db.Qdb;
import edu.wpi.cs3733.D23.teamQ.db.obj.Node;
import edu.wpi.cs3733.D23.teamQ.db.obj.OfficeSuppliesRequest;
import edu.wpi.cs3733.D23.teamQ.navigation.Navigation;
import edu.wpi.cs3733.D23.teamQ.navigation.Screen;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;

import java.sql.Date;
import java.time.LocalDate;

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

    assigneeField.setValue(ListServiceRequestController.getOfficeRequest().getAssignee());
    roomNumberField.setValue(ListServiceRequestController.getOfficeRequest().getNode());
    dateField.setValue(
            LocalDate.of(
                    ListServiceRequestController.getOfficeRequest().getDate().getYear(),
                    ListServiceRequestController.getOfficeRequest().getDate().getMonth(),
                    ListServiceRequestController.getOfficeRequest().getDate().getDay()));
    timeField.setText(ListServiceRequestController.getOfficeRequest().getTime());
    specialInstructionsField.setText(ListServiceRequestController.getOfficeRequest().getSpecialInstructions());
    itemRequestedField.setValue(ListServiceRequestController.getOfficeRequest().getItem());
    quantityField.setText("" + ListServiceRequestController.getOfficeRequest().getQuantity());
  }

  @FXML
  public void resetButtonClicked() {}

  @FXML
  public void cancelButtonClicked() {}

  @FXML
  public void submitButtonClicked() {

        Qdb qdb = Qdb.getInstance();
        if (((String) quantityField.getText()).equals("Select Quantity")) {
          quantityField.setText("0");
        }

        OfficeSuppliesRequest newOSR =
            new OfficeSuppliesRequest(
                ListServiceRequestController.getOfficeRequest().getRequestID(),
                "temp user",
                0,
                    (String) assigneeField.getValue(),
                    (Node)roomNumberField.getValue(),
                specialInstructionsField.getText(),
                Date.valueOf(dateField.getValue()),
                timeField.getText(),
                (String) itemRequestedField.getValue(),
                Integer.parseInt((String) quantityField.getText()));


        // qdb.addFlowerRequest(newOSR);
        Navigation.navigate(Screen.HOME);


  }
}
