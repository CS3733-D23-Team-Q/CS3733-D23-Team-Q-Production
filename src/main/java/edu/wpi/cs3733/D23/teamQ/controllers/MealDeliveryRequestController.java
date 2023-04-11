package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.db.Qdb;
import edu.wpi.cs3733.D23.teamQ.db.obj.MealRequest;
import edu.wpi.cs3733.D23.teamQ.navigation.Navigation;
import edu.wpi.cs3733.D23.teamQ.navigation.Screen;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;

public class MealDeliveryRequestController {
  @FXML private MFXTextField assigneeField;
  @FXML private MFXTextField roomNumberField;
  @FXML private MFXTextField drinkField;
  @FXML private MFXTextField entreeField;
  @FXML private MFXTextField sideField;
  @FXML private MFXTextField specialInstructionsField;

  @FXML Button resetButton;
  @FXML Button backButton;
  @FXML Button submitButton;

  @FXML MenuItem homeItem;
  @FXML MenuItem exitItem;
  @FXML MenuItem profileItem;

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
  public void backButtonClicked() {
    Navigation.navigate(Screen.SERVICE_REQUEST_HUB);
  }

  @FXML
  public void submitButtonClicked() {
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
  }

  @FXML
  public void homeItemClicked() {
    Navigation.navigate(Screen.HOME);
  }

  @FXML
  public void exitItemClicked() {
    Platform.exit();
  }

  @FXML
  public void profileItemClicked() {
    Navigation.navigate(Screen.PROFILE_PAGE);
  }
}
