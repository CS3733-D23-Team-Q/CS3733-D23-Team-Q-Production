package edu.wpi.cs3733.D23.teamQ.controllers;

import io.github.palexdev.materialfx.controls.MFXDatePicker;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;

public class MealDeliveryRequestController {
  @FXML ChoiceBox assigneeField;
  @FXML ChoiceBox roomNumberField;
  @FXML MFXDatePicker dateField;
  @FXML MFXTextField timeField;
  @FXML MFXTextField specialInstructionsField;

  @FXML ChoiceBox drinkField;
  @FXML ChoiceBox entreeField;
  @FXML ChoiceBox sideField;

  @FXML Button resetButton;
  @FXML Button cancelButton;
  @FXML Button submitButton;

  @FXML
  public void initialize() {}

  @FXML
  public void resetButtonClicked() {}

  @FXML
  public void cancelButtonClicked() {}

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
