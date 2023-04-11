package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.db.Qdb;
import edu.wpi.cs3733.D23.teamQ.db.obj.ConferenceRequest;
import edu.wpi.cs3733.D23.teamQ.navigation.Navigation;
import edu.wpi.cs3733.D23.teamQ.navigation.Screen;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.MenuItem;

public class ConferenceRoomRequestController {
  @FXML private MFXTextField assigneeField;
  @FXML private MFXTextField roomNumberField;
  @FXML private MFXTextField dateTimeField;
  ObservableList<String> foodOptionsList =
      FXCollections.observableArrayList(
          "Brunch spread", "Dinner spread", "Snack spread", "No food");
  @FXML private ChoiceBox foodField;
  @FXML private MFXTextField specialInstructionsField;

  @FXML Button resetButton;
  @FXML Button backButton;
  @FXML Button submitButton;

  @FXML MenuItem homeItem;
  @FXML MenuItem exitItem;
  @FXML MenuItem profileItem;

  @FXML
  public void initialize() {
    this.foodField.setValue("Select Food Option");
    this.foodField.setItems(foodOptionsList);
  }

  @FXML
  public void resetButtonClicked() {
    assigneeField.clear();
    roomNumberField.clear();
    dateTimeField.clear();
    specialInstructionsField.clear();
    foodField.setValue("No food");
  }

  @FXML
  public void backButtonClicked() {
    Navigation.navigate(Screen.SERVICE_REQUEST_HUB);
  }

  @FXML
  public void submitButtonClicked() {
    Qdb qdb = Qdb.getInstance();


//    ConferenceRequest newCCR =
//        new ConferenceRequest(
//            0,
//            "temp user",
//            0,
//            assigneeField.getText(),
//            qdb.retrieveNode(Integer.parseInt(roomNumberField.getText())),
//            specialInstructionsField.getText(),
//            // add for date
//            // add for time
//            (String) foodField.getValue());
//    qdb.addConferenceRequest(newCCR);


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
