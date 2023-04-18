package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.db.Qdb;
import edu.wpi.cs3733.D23.teamQ.db.obj.MedicalSuppliesRequest;
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

public class MedicalSuppliesRequestDisplayController {

  ObservableList<String> medicalItemList =
      FXCollections.observableArrayList("tbd1", "tbd2", "tbd3", "tbd4", "tbd5");

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
    itemRequestedField.setItems(medicalItemList);

    assigneeField.setValue(ListServiceRequestController.getMedicalRequest().getAssignee());
    roomNumberField.setValue(ListServiceRequestController.getMedicalRequest().getNode());
    dateField.setValue(
        LocalDate.of(
            ListServiceRequestController.getConferenceRequest().getDate().getYear(),
            ListServiceRequestController.getConferenceRequest().getDate().getMonth(),
            ListServiceRequestController.getConferenceRequest().getDate().getDay()));
    timeField.setText(ListServiceRequestController.getConferenceRequest().getTime());
    specialInstructionsField.setText(
        ListServiceRequestController.getConferenceRequest().getSpecialInstructions());
    quantityField.setText(
        String.valueOf(ListServiceRequestController.getMedicalRequest().getQuantity()));
  }

  @FXML
  public void deleteButtonClicked() {
    Qdb qdb = Qdb.getInstance();
    qdb.deleteMedicalSuppliesRequest(
        ListServiceRequestController.getMedicalRequest().getRequestID());
  }

  @FXML
  public void backButtonClicked() {
    Navigation.navigate(Screen.HOME);
  }

  @FXML
  public void updateButtonClicked() {

    Qdb qdb = Qdb.getInstance();

    MedicalSuppliesRequest newMSP =
        new MedicalSuppliesRequest(
            ListServiceRequestController.getMedicalRequest().getRequestID(),
            ListServiceRequestController.getMedicalRequest().getRequester(),
            0,
            qdb.retrieveAccount(assigneeField.getValue().toString()),
            ListServiceRequestController.getMedicalRequest().getNode(),
            (String) specialInstructionsField.getText(),
            Date.valueOf(dateField.getValue()),
            timeField.getText(),
            (String) itemRequestedField.getValue(),
            Integer.parseInt((String) quantityField.getText()));

    qdb.updateMedicalSuppliesRequest(
        ListServiceRequestController.getFlowerRequest().getRequestID(), newMSP);
  }
}
