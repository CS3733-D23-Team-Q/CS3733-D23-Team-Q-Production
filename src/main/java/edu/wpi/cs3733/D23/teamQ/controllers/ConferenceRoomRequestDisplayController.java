package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.db.Qdb;
import edu.wpi.cs3733.D23.teamQ.db.obj.Account;
import edu.wpi.cs3733.D23.teamQ.db.obj.ConferenceRequest;
import edu.wpi.cs3733.D23.teamQ.navigation.Navigation;
import edu.wpi.cs3733.D23.teamQ.navigation.Screen;
import io.github.palexdev.materialfx.controls.*;
import java.sql.Date;
import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;

public class ConferenceRoomRequestDisplayController implements IController {

  ObservableList<String> foodOptionsList =
      FXCollections.observableArrayList(
          "Brunch spread", "Dinner spread", "Snack spread", "No food");

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
  @FXML MFXFilterComboBox foodField;
  @FXML MFXTextField specialInstructionsField;
  @FXML private ImageView BrunchFood;
  @FXML private ImageView DinnerFood;
  @FXML private ImageView SnackFood;

  @FXML
  public void initialize() {
    Qdb qdb = Qdb.getInstance();
    this.timeField.setItems(timeList);
    this.assigneeField.setItems(qdb.getAllNames());
    String[] conf = {"CONF"};
    this.roomNumberField.setItems(qdb.getAllLongNames(conf));
    this.foodField.setItems(foodOptionsList);

    assigneeField.setText(
        ListServiceRequestController.getConferenceRequest().getAssignee().getUsername());
    roomNumberField.setText(
        qdb.retrieveNode(ListServiceRequestController.getConferenceRequest().getNodeID())
            .getLocation()
            .getLongName());
    dateField.setValue(
        LocalDate.of(
            ListServiceRequestController.getConferenceRequest().getDate().getYear() + 1900,
            ListServiceRequestController.getConferenceRequest().getDate().getMonth() + 1,
            ListServiceRequestController.getConferenceRequest().getDate().getDate()));
    timeField.setText(ListServiceRequestController.getConferenceRequest().getTime());
    foodField.setText(ListServiceRequestController.getConferenceRequest().getFoodChoice());
    specialInstructionsField.setText(
        ListServiceRequestController.getConferenceRequest().getSpecialInstructions());
    if (ListServiceRequestController.getConferenceRequest().getFoodChoice().equals("Brunch spread")) {
      BrunchFood.setOpacity(1.0);
      DinnerFood.setOpacity(0.0);
      SnackFood.setOpacity(0.0);
    }
    if (ListServiceRequestController.getConferenceRequest().getFoodChoice().equals("Dinner spread")) {
      BrunchFood.setOpacity(0.0);
      DinnerFood.setOpacity(1.0);
      SnackFood.setOpacity(0.0);
    }
    if (ListServiceRequestController.getConferenceRequest().getFoodChoice().equals("Snack spread")) {
      BrunchFood.setOpacity(0.0);
      DinnerFood.setOpacity(0.0);
      SnackFood.setOpacity(1.0);
    }
  }

  @FXML
  public void deleteButtonClicked() {
    Qdb qdb = Qdb.getInstance();
    qdb.deleteConferenceRequest(ListServiceRequestController.getConferenceRequest().getRequestID());
    Navigation.navigateRight(Screen.SERVICE_PLACEHOLDER);
  }

  @FXML
  public void backButtonClicked() {
    Navigation.navigateRight(Screen.SERVICE_PLACEHOLDER);
  }

  // Update with proper date and time
  @FXML
  public void updateButtonClicked() {
    Qdb qdb = Qdb.getInstance();

    ConferenceRequest newCCR =
        new ConferenceRequest(
            ListServiceRequestController.getConferenceRequest().getRequestID(),
            ListServiceRequestController.getConferenceRequest().getNode(),
            qdb.retrieveAccount(LoginController.getUsername()),
            (Account) assigneeField.getValue(),
            specialInstructionsField.getText(),
            Date.valueOf(dateField.getValue()),
            timeField.getText(),
            ListServiceRequestController.getConferenceRequest().getProgress().ordinal(),
            foodField.getValue().toString());
    qdb.updateConferenceRequest(
        ListServiceRequestController.getConferenceRequest().getRequestID(), newCCR);
    Navigation.navigateRight(Screen.HOME);
  }

  @FXML
  public void FoodSelected(ActionEvent event) {
    if (foodField.getValue().equals("Brunch spread")) {
      BrunchFood.setOpacity(1.0);
      DinnerFood.setOpacity(0.0);
      SnackFood.setOpacity(0.0);
    }
    if (foodField.getValue().equals("Dinner spread")) {
      BrunchFood.setOpacity(0.0);
      DinnerFood.setOpacity(1.0);
      SnackFood.setOpacity(0.0);
    }
    if (foodField.getValue().equals("Snack spread")) {
      BrunchFood.setOpacity(0.0);
      DinnerFood.setOpacity(0.0);
      SnackFood.setOpacity(1.0);
    }
  }
}
