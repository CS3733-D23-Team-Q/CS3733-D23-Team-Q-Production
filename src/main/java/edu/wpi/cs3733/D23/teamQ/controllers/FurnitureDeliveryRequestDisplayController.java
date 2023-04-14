package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.db.Qdb;
import edu.wpi.cs3733.D23.teamQ.db.obj.FurnitureRequest;
import edu.wpi.cs3733.D23.teamQ.navigation.Navigation;
import edu.wpi.cs3733.D23.teamQ.navigation.Screen;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXFilterComboBox;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import io.github.palexdev.materialfx.controls.MFXTextField;
import java.sql.Date;
import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;

public class FurnitureDeliveryRequestDisplayController {

  ObservableList<String> TypeOfFurniture =
      FXCollections.observableArrayList("Desk", "Desk Chair", "Couch", "Examination Table");

  @FXML MFXButton deleteButton;
  @FXML MFXButton backButton;
  @FXML MFXButton updateButton;

  @FXML MFXFilterComboBox assigneeField;
  @FXML MFXFilterComboBox roomNumberField;
  @FXML MFXDatePicker dateField;
  @FXML MFXTextField timeField;
  @FXML MFXFilterComboBox furnitureChoiceField;
  @FXML MFXTextField specialInstructionsField;

  @FXML
  public void initialize() {
    furnitureChoiceField.setItems(TypeOfFurniture);
    assigneeField.setText(ListServiceRequestController.getFurnitureRequest().getAssignee());
    roomNumberField.setText(ListServiceRequestController.getFurnitureRequest().getNode().toString());
    dateField.setValue(
        LocalDate.of(
            ListServiceRequestController.getConferenceRequest().getDate().getYear(),
            ListServiceRequestController.getConferenceRequest().getDate().getMonth(),
            ListServiceRequestController.getConferenceRequest().getDate().getDay()));
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
