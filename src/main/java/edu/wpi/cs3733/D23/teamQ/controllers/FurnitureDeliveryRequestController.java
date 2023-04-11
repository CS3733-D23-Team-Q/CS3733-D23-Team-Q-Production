package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.db.Qdb;
import edu.wpi.cs3733.D23.teamQ.db.obj.FurnitureRequest;
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

public class FurnitureDeliveryRequestController {
  @FXML private MFXTextField assigneeField;
  @FXML private MFXTextField roomNumberField;
  @FXML private MFXTextField dateTimeField;
  ObservableList<String> itemList =
      FXCollections.observableArrayList("Desk", "Desk Chair", "Couch", "Examination Table");
  @FXML private ChoiceBox itemField;
  @FXML private MFXTextField specialInstructionsField;

  @FXML Button resetButton;
  @FXML Button backButton;
  @FXML Button submitButton;

  @FXML MenuItem homeItem;
  @FXML MenuItem exitItem;
  @FXML MenuItem profileItem;

  @FXML
  public void initialize() {
    this.itemField.setValue("Select Furniture Item");
    this.itemField.setItems(itemList);
  }

  @FXML
  public void resetButtonClicked() {
    assigneeField.clear();
    roomNumberField.clear();
    dateTimeField.clear();
    specialInstructionsField.clear();
    itemField.setValue("Select Furniture Item");
  }

  @FXML
  public void backButtonClicked() {
    Navigation.navigate(Screen.SERVICE_REQUEST_HUB);
  }

  @FXML
  public void submitButtonClicked() {
    Qdb qdb = Qdb.getInstance();
    FurnitureRequest newFR =
        new FurnitureRequest(
            0,
            "temp user",
            0,
            assigneeField.getText(),
            qdb.retrieveNode(Integer.parseInt(roomNumberField.getText())),
            specialInstructionsField.getText(),
            //for date,
                // for time
            (String) itemField.getValue());
    // qdb.addConferenceRequest(newFR);
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
