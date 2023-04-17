package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.db.Qdb;
import edu.wpi.cs3733.D23.teamQ.db.obj.FlowerRequest;
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

public class FlowerRequestDisplayController {

  ObservableList<String> TypeOfFlowers =
      FXCollections.observableArrayList("Roses", "Daisies", "Tulips", "Sunflowers", "Lilies");

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
  @FXML MFXFilterComboBox flowerTypeField;
  @FXML MFXTextField bouquetChoiceField;
  @FXML MFXTextField specialInstructionsField;

  @FXML
  public void initialize() {
    Qdb qdb = Qdb.getInstance();
    timeField.setItems(timeList);
    this.assigneeField.setItems(qdb.getAllNames());
    String[] conf = {"CONF"};
    this.roomNumberField.setItems(qdb.getAllLongNames(conf));
    this.flowerTypeField.setItems(TypeOfFlowers);

    assigneeField.setText(
        ListServiceRequestController.getConferenceRequest().getAssignee().getUsername());
    roomNumberField.setText(
        ListServiceRequestController.getConferenceRequest().getNode().toString());
    dateField.setValue(
        LocalDate.of(
            ListServiceRequestController.getConferenceRequest().getDate().getYear() + 1900,
            ListServiceRequestController.getConferenceRequest().getDate().getMonth() + 1,
            ListServiceRequestController.getConferenceRequest().getDate().getDate()));
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
            ListServiceRequestController.getFlowerRequest().getRequester(),
            0,
            qdb.retrieveAccount(assigneeField.getSelectedItem().toString()),
            ListServiceRequestController.getFlowerRequest().getNode(),
            specialInstructionsField.getText(),
            Date.valueOf(dateField.getValue()),
            timeField.getText(),
            "",
            (String) flowerTypeField.getValue(),
            Integer.parseInt((String) bouquetChoiceField.getText()));

    qdb.updateFlowerRequest(ListServiceRequestController.getFlowerRequest().getRequestID(), newFR);
  }
}
