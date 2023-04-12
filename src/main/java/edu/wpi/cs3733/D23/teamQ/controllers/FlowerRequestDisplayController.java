package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.db.Qdb;
import edu.wpi.cs3733.D23.teamQ.db.obj.FlowerRequest;
import edu.wpi.cs3733.D23.teamQ.navigation.Navigation;
import edu.wpi.cs3733.D23.teamQ.navigation.Screen;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import io.github.palexdev.materialfx.controls.MFXTextField;
import java.sql.Date;
import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

public class FlowerRequestDisplayController {

  ObservableList<String> TypeOfFlowers =
      FXCollections.observableArrayList("Roses", "Daisies", "Tulips", "Sunflowers", "Lilies");

  @FXML MFXButton deleteButton;
  @FXML MFXButton backButton;
  @FXML MFXButton updateButton;

  @FXML MFXComboBox assigneeField;
  @FXML MFXComboBox roomNumberField;
  @FXML MFXDatePicker dateField;
  @FXML MFXTextField timeField;
  @FXML MFXComboBox flowerTypeField;
  @FXML MFXTextField bouquetChoiceField;
  @FXML MFXTextField specialInstructionsField;

  @FXML
  public void initialize() {
    Qdb qdb = Qdb.getInstance();
    this.assigneeField.setItems(qdb.getAllNames());
    String[] conf = {"CONF"};
    this.roomNumberField.setItems(qdb.getAllLongNames(conf));
    this.flowerTypeField.setItems(TypeOfFlowers);

    assigneeField.setText(ListServiceRequestController.getConferenceRequest().getAssignee());
    roomNumberField.setText(
        ListServiceRequestController.getConferenceRequest().getNode().toString());
    dateField.setValue(
        LocalDate.of(
            ListServiceRequestController.getConferenceRequest().getDate().getYear(),
            ListServiceRequestController.getConferenceRequest().getDate().getMonth(),
            ListServiceRequestController.getConferenceRequest().getDate().getDay()));
    timeField.setText(ListServiceRequestController.getConferenceRequest().getTime());
    specialInstructionsField.setText(
        ListServiceRequestController.getConferenceRequest().getSpecialInstructions());
    flowerTypeField.setText(ListServiceRequestController.getFlowerRequest().getFlowerType());
    bouquetChoiceField.setText(
        String.valueOf(ListServiceRequestController.getFlowerRequest().getNumberOfBouquets()));
  }

  @FXML
  public void deleteButtonClicked() {
    Qdb qdb = Qdb.getInstance();
    qdb.deleteFlowerRequest(ListServiceRequestController.getFlowerRequest().getRequestID());
  }

  @FXML
  public void backButtonClicked() {
    Navigation.navigateRight(Screen.SERVICE_PLACEHOLDER);
  }

  @FXML
  public void updateButtonClicked() {

    Qdb qdb = Qdb.getInstance();

    FlowerRequest newFR =
        new FlowerRequest(
            ListServiceRequestController.getFlowerRequest().getRequestID(),
            "temp requester",
            0,
            "temp assignee",
            ListServiceRequestController.getFlowerRequest().getNode(),
            (String) specialInstructionsField.getText(),
            Date.valueOf(dateField.getValue()),
            timeField.getText(),
            "",
            (String) flowerTypeField.getValue(),
            Integer.parseInt((String) bouquetChoiceField.getText()));

    qdb.updateFlowerRequest(ListServiceRequestController.getFlowerRequest().getRequestID(), newFR);
  }
}
