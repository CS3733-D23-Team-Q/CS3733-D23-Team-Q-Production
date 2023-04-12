package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.db.Qdb;
import edu.wpi.cs3733.D23.teamQ.db.obj.ConferenceRequest;
import edu.wpi.cs3733.D23.teamQ.navigation.Navigation;
import edu.wpi.cs3733.D23.teamQ.navigation.Screen;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;

import java.sql.Date;

public class ConferenceRoomRequestController {

  @FXML ChoiceBox assigneeField;
  @FXML ChoiceBox roomNumberField;
  @FXML MFXDatePicker dateField;
  @FXML MFXTextField timeField;
  @FXML ChoiceBox foodField;
  @FXML MFXTextField specialInstructionsField;
  ObservableList<String> foodOptionsList =
      FXCollections.observableArrayList(
          "Brunch spread", "Dinner spread", "Snack spread", "No food");
  @FXML Button resetButton;
  @FXML Button cancelButton;
  @FXML Button submitButton;

  @FXML
  public void initialize() {
    this.foodField.setValue("Select Food Option");
    this.foodField.setItems(foodOptionsList);
  }

  @FXML
  public void resetButtonClicked() {
    this.foodField.setValue("Select Food Option");
    this.foodField.setItems(foodOptionsList);

  }

  @FXML
  public void cancelButtonClicked() {
    Navigation.navigate(Screen.SERVICE_PLACEHOLDER);
  }

  @FXML
  public void submitButtonClicked() {
    Qdb qdb = Qdb.getInstance();

        ConferenceRequest cr = new ConferenceRequest(
                LoginController.getLoginUsername(),
                0,
                (String)assigneeField.getValue(),
                qdb.retrieveNode(Integer.parseInt((String)roomNumberField.getValue())),
                specialInstructionsField.getText(),
                Date.valueOf(dateField.getCurrentDate()),
                timeField.getText(),
                (String)foodField.getValue());
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
