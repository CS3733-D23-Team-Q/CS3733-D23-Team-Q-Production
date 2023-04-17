package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.db.Qdb;
import edu.wpi.cs3733.D23.teamQ.db.obj.MealRequest;
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

public class MealDeliveryRequestDisplayController {

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
  @FXML MFXTextField specialInstructionsField;

  @FXML MFXFilterComboBox drinkField;
  @FXML MFXFilterComboBox entreeField;
  @FXML MFXFilterComboBox sideField;

  @FXML
  public void initialize() {
    timeField.setItems(timeList);
    assigneeField.setText(ListServiceRequestController.getMealRequest().getAssignee());
    roomNumberField.setText(ListServiceRequestController.getMealRequest().getNode().toString());
    dateField.setValue(
        LocalDate.of(
            ListServiceRequestController.getMealRequest().getDate().getYear(),
            ListServiceRequestController.getMealRequest().getDate().getMonth(),
            ListServiceRequestController.getMealRequest().getDate().getDay()));
    timeField.setText(ListServiceRequestController.getMealRequest().getTime());
    specialInstructionsField.setText(
        ListServiceRequestController.getMealRequest().getSpecialInstructions());
    drinkField.setText(ListServiceRequestController.getMealRequest().getDrink());
    entreeField.setText(ListServiceRequestController.getMealRequest().getEntree());
    sideField.setText(ListServiceRequestController.getMealRequest().getSide());
  }

  @FXML
  public void deleteButtonClicked() {
    Qdb qdb = Qdb.getInstance();
    qdb.deleteConferenceRequest(ListServiceRequestController.getConferenceRequest().getRequestID());
  }

  @FXML
  public void backButtonClicked() {
    Navigation.navigate(Screen.HOME);
  }

  // Update with proper date and time
  @FXML
  public void updateButtonClicked() {
    Qdb qdb = Qdb.getInstance();

    MealRequest newMR =
        new MealRequest(
            ListServiceRequestController.getMealRequest().getRequestID(),
            "Temp",
            0,
            "assignee",
            ListServiceRequestController.getConferenceRequest().getNode(),
            specialInstructionsField.getText(),
            Date.valueOf(dateField.getValue()),
            timeField.getText(),
            (String) drinkField.getValue(),
            (String) entreeField.getValue(),
            (String) sideField.getValue());
    qdb.updateMealRequest(ListServiceRequestController.getMealRequest().getRequestID(), newMR);
  }
}
