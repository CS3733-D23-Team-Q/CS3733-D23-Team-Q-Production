package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.db.Qdb;
import edu.wpi.cs3733.D23.teamQ.db.obj.FurnitureRequest;
import edu.wpi.cs3733.D23.teamQ.navigation.Navigation;
import edu.wpi.cs3733.D23.teamQ.navigation.Screen;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import io.github.palexdev.materialfx.controls.MFXFilterComboBox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import java.sql.Date;
import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

public class FurnitureDeliveryRequestDisplayController {

  ObservableList<String> TypeOfFurniture =
      FXCollections.observableArrayList("Desk", "Desk Chair", "Couch", "Examination Table");

  @FXML MFXButton deleteButton;
  @FXML MFXButton backButton;
  @FXML MFXButton updateButton;

  @FXML MFXFilterComboBox assigneeField;
  @FXML MFXFilterComboBox roomNumberField;
  @FXML MFXDatePicker dateField;
  ObservableList<String> timeList =
      FXCollections.observableArrayList(
          "00:30", "01:00", "01:30", "02:00", "02:30", "03:00", "03:30", "04:00", "04:30", "05:00",
          "05:30", "06:00", "06:30", "07:00", "07:30", "08:00", "08:30", "09:00", "09:30", "10:00",
          "10:30", "11:00", "11:30", "12:00", "12:30", "13:00", "13:30", "14:00", "14:30", "15:00",
          "15:30", "16:00", "16:30", "17:00", "17:30", "18:00", "18:30", "19:00", "19:30", "20:00",
          "20:30", "21:00", "21:30", "22:00", "22:30", "23:00", "23:30", "24:00");
  @FXML MFXFilterComboBox timeField;
  @FXML MFXFilterComboBox furnitureChoiceField;
  @FXML MFXTextField specialInstructionsField;

  @FXML
  public void initialize() {
    timeField.setItems(timeList);
    furnitureChoiceField.setItems(TypeOfFurniture);
    assigneeField.setText(ListServiceRequestController.getFurnitureRequest().getAssignee());
    roomNumberField.setText(
        ListServiceRequestController.getFurnitureRequest().getNode().toString());
    dateField.setValue(
        LocalDate.of(
            ListServiceRequestController.getConferenceRequest().getDate().getYear() + 1900,
            ListServiceRequestController.getConferenceRequest().getDate().getMonth() + 1,
            ListServiceRequestController.getConferenceRequest().getDate().getDate()));
    timeField.setText(ListServiceRequestController.getConferenceRequest().getTime());
    specialInstructionsField.setText(
        ListServiceRequestController.getConferenceRequest().getSpecialInstructions());
    furnitureChoiceField.setText(ListServiceRequestController.getFurnitureRequest().getItem());
  }

  @FXML
  public void deleteButtonClicked() {
    Qdb qdb = Qdb.getInstance();
    qdb.deleteFurnitureRequest(ListServiceRequestController.getFurnitureRequest().getRequestID());
  }

  @FXML
  public void backButtonClicked() {
    Navigation.navigate(Screen.HOME);
  }

  @FXML
  public void updateButtonClicked() {

    Qdb qdb = Qdb.getInstance();

    FurnitureRequest newFurR =
        new FurnitureRequest(
            ListServiceRequestController.getFurnitureRequest().getRequestID(),
            "temp requester",
            0,
            "temp assignee",
            ListServiceRequestController.getFurnitureRequest().getNode(),
            (String) specialInstructionsField.getText(),
            Date.valueOf(dateField.getValue()),
            timeField.getText(),
            (String) furnitureChoiceField.getValue());

    qdb.updateFurnitureRequest(
        ListServiceRequestController.getFurnitureRequest().getRequestID(), newFurR);
  }
}
