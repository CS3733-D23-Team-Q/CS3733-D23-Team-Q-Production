package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.db.Qdb;
import edu.wpi.cs3733.D23.teamQ.navigation.Navigation;
import edu.wpi.cs3733.D23.teamQ.navigation.Screen;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import io.github.palexdev.materialfx.controls.MFXFilterComboBox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MealDeliveryRequestController {
  Qdb qdb = Qdb.getInstance();
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

  @FXML Button resetButton;
  @FXML Button cancelButton;
  @FXML Button submitButton;

  ObservableList<String> drinkList =
      FXCollections.observableArrayList("Water", "Coke", "Coffee", "Tea");
  ObservableList<String> entreeList =
      FXCollections.observableArrayList("Chicken", "Steak", "Pork", "Fish", "Vegetarian");

  ObservableList<String> sideList =
      FXCollections.observableArrayList("Fries", "Onion Rings", "Soup", "Salad");

  @FXML
  public void initialize() {
    this.assigneeField.setValue("");
    this.assigneeField.setItems(qdb.getAllNames());
    this.roomNumberField.setValue("");
    this.roomNumberField.setItems(qdb.getAllLongNames());
    this.timeField.setValue("");
    this.timeField.setItems(timeList);
    this.drinkField.setValue("");
    this.drinkField.setItems(drinkList);
    this.entreeField.setValue("");
    this.entreeField.setItems(entreeList);
    this.sideField.setValue("");
    this.sideField.setItems(sideList);
  }

  @FXML
  public void resetButtonClicked() {
    assigneeField.setValue("");
    roomNumberField.setValue("");
    dateField.clear();
    timeField.setValue("");
    drinkField.setValue("");
    entreeField.setValue("");
    sideField.setValue("");
    specialInstructionsField.clear();
  }

  @FXML
  public void cancelButtonClicked() {
    Navigation.navigateRight(Screen.SERVICE_PLACEHOLDER);
  }

  @FXML
  public void submitButtonClicked() {
    /*
        Qdb qdb = Qdb.getInstance();

    //    MealRequest newMR =
    //        new MealRequest(
    //            0,
    //            "temp user",
    //            0,
    //            assigneeField.getText(),
    //            qdb.retrieveNode(Integer.parseInt(roomNumberField.getText())),
    //            specialInstructionsField.getText(),
    //                // for date
    //                // for time
    //            drinkField.getText(),
    //            entreeField.getText(),
    //            sideField.getText());

        // qdb.addMealRequest(newMR);
        Navigation.navigate(Screen.HOME);

         */
  }
}
