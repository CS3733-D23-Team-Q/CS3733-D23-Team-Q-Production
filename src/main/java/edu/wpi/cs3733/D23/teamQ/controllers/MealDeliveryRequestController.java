package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.db.Qdb;
import edu.wpi.cs3733.D23.teamQ.db.obj.MealRequest;
import edu.wpi.cs3733.D23.teamQ.navigation.Navigation;
import edu.wpi.cs3733.D23.teamQ.navigation.Screen;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;

public class MealDeliveryRequestController {
  @FXML MFXTextField assigneeField;
  @FXML MFXTextField roomNumberField;
  @FXML MFXDatePicker dateField;
  @FXML MFXTextField timeField;

  @FXML private MFXTextField drinkField;
  @FXML private MFXTextField entreeField;
  @FXML private MFXTextField sideField;
  @FXML private MFXTextField specialInstructionsField;

  @FXML Button resetButton;
  @FXML Button cancelButton;
  @FXML Button submitButton;


  @FXML
  public void initialize() {}

  @FXML
  public void resetButtonClicked() {
    roomNumberField.clear();
    drinkField.clear();
    entreeField.clear();
    sideField.clear();
    specialInstructionsField.clear();
  }

  @FXML
  public void cancelButtonClicked() {
  }

  @FXML
  public void submitButtonClicked() {
    Qdb qdb = Qdb.getInstance();
    MealRequest newMR =
            new MealRequest(
                    0,
                    "temp user",
                    0,
                    assigneeField.getText(),
                    roomNumberField.getText(),
                    specialInstructionsField.getText(),
                    drinkField.getText(),
                    entreeField.getText(),
                    sideField.getText());
    // qdb.addMealRequest(newMR);
    Navigation.navigate(Screen.HOME);
  }
}
