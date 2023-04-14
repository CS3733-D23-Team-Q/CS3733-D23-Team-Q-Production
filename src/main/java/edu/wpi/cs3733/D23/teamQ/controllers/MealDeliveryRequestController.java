package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.db.Qdb;
import edu.wpi.cs3733.D23.teamQ.db.obj.MealRequest;
import edu.wpi.cs3733.D23.teamQ.db.obj.Node;
import edu.wpi.cs3733.D23.teamQ.navigation.Navigation;
import edu.wpi.cs3733.D23.teamQ.navigation.Screen;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;

import java.sql.Date;

public class MealDeliveryRequestController {
  Qdb qdb = Qdb.getInstance();
  @FXML MFXComboBox assigneeField;
  @FXML MFXComboBox roomNumberField;
  @FXML MFXDatePicker dateField;
  @FXML MFXTextField timeField;
  @FXML MFXTextField specialInstructionsField;

  @FXML MFXComboBox drinkField;
  @FXML MFXComboBox entreeField;
  @FXML MFXComboBox sideField;

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
    this.assigneeField.setValue("Select an Assignee");
    this.assigneeField.setItems(qdb.getAllNames());
    this.roomNumberField.setValue("Select a Location");
    this.roomNumberField.setItems(qdb.getAllLongNames());
    this.drinkField.setValue("Select a Drink");
    this.drinkField.setItems(drinkList);
    this.entreeField.setValue("Select an Entree");
    this.entreeField.setItems(entreeList);
    this.sideField.setValue("Select a Side");
    this.sideField.setItems(sideList);
  }

  @FXML
  public void resetButtonClicked() {}

  @FXML
  public void cancelButtonClicked() {}

  @FXML
  public void submitButtonClicked() {

        Qdb qdb = Qdb.getInstance();

        MealRequest newMR =
            new MealRequest(
                0,
                "temp user",
                0,
                    (String)assigneeField.getValue(),
                    (Node) roomNumberField.getValue(),
                    specialInstructionsField.getText(),
                    Date.valueOf(dateField.getValue()),
                    timeField.getText(),
                    (String) drinkField.getValue(),
                    (String)entreeField.getValue(),
                    (String)sideField.getValue());

         qdb.addMealRequest(newMR);
        Navigation.navigate(Screen.SUBMISSION);


  }
}
