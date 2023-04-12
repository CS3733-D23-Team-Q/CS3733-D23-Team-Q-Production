package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.db.Qdb;
import edu.wpi.cs3733.D23.teamQ.db.obj.OfficeSuppliesRequest;
import edu.wpi.cs3733.D23.teamQ.navigation.Navigation;
import edu.wpi.cs3733.D23.teamQ.navigation.Screen;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import io.github.palexdev.materialfx.controls.MFXTextField;
import java.sql.Date;
import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;

public class OfficeSuppliesRequestDisplayController {

  ObservableList<String> officeItemList =
      FXCollections.observableArrayList(
          "Printer Paper (by ream)", "Pencil", "Pen", "Highlighter", "Notepad");

  @FXML MFXButton deleteButton;
  @FXML MFXButton backButton;
  @FXML MFXButton updateButton;

  @FXML ChoiceBox assigneeField;
  @FXML ChoiceBox roomNumberField;
  @FXML MFXDatePicker dateField;
  @FXML MFXTextField timeField;
  @FXML ChoiceBox itemRequestedField;
  @FXML MFXTextField quantityField;
  @FXML MFXTextField specialInstructionsField;

  @FXML
  public void initialize() {
    itemRequestedField.setItems(officeItemList);

    assigneeField.setValue(ListServiceRequestController.getOfficeRequest().getAssignee());
    roomNumberField.setValue(ListServiceRequestController.getOfficeRequest().getNode());
    dateField.setValue(
        LocalDate.of(
            ListServiceRequestController.getConferenceRequest().getDate().getYear(),
            ListServiceRequestController.getConferenceRequest().getDate().getMonth(),
            ListServiceRequestController.getConferenceRequest().getDate().getDay()));
    timeField.setText(ListServiceRequestController.getConferenceRequest().getTime());
    specialInstructionsField.setText(
        ListServiceRequestController.getConferenceRequest().getSpecialInstructions());
    quantityField.setText(
        String.valueOf(ListServiceRequestController.getOfficeRequest().getQuantity()));
  }

  @FXML
  public void deleteButtonClicked() {
    Qdb qdb = Qdb.getInstance();
    qdb.deleteOfficeSuppliesRequest(ListServiceRequestController.getOfficeRequest().getRequestID());
  }

  @FXML
  public void backButtonClicked() {
    Navigation.navigate(Screen.HOME);
  }

  @FXML
  public void updateButtonClicked() {

    Qdb qdb = Qdb.getInstance();

    OfficeSuppliesRequest newOR =
        new OfficeSuppliesRequest(
            ListServiceRequestController.getOfficeRequest().getRequestID(),
            "temp requester",
            0,
            "temp assignee",
            ListServiceRequestController.getOfficeRequest().getNode(),
            (String) specialInstructionsField.getText(),
            Date.valueOf(dateField.getValue()),
            timeField.getText(),
            (String) itemRequestedField.getValue(),
            Integer.parseInt((String) quantityField.getText()));

    qdb.updateOfficeSuppliesRequest(
        ListServiceRequestController.getFlowerRequest().getRequestID(), newOR);
  }
}
