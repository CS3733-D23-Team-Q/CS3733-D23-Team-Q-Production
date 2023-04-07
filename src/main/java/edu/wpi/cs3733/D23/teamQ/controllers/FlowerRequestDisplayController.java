package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.navigation.Navigation;
import edu.wpi.cs3733.D23.teamQ.navigation.Screen;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;

public class FlowerRequestDisplayController implements IController {
  @FXML Button resetButton;
  @FXML Button backButton;
  @FXML Button submitButton;

  @FXML Label roomNumberField;

  @FXML Label flowerChoiceField;

  @FXML Label numberBouquetField;

  @FXML Label flowerNoteField;

  @FXML Label specialInstructionsField;

  @FXML Label assigneeField;

  @FXML MenuItem homeItem;
  @FXML MenuItem exitItem;
  @FXML MenuItem profileItem;

  @FXML
  public void initialize() {
    roomNumberField.setText(ListServiceRequestController.getFlowerRequest().getRoomNumber());
    flowerNoteField.setText(ListServiceRequestController.getFlowerRequest().getNote());
    flowerChoiceField.setText(ListServiceRequestController.getFlowerRequest().getFlowerType());
    numberBouquetField.setText(
        String.valueOf(ListServiceRequestController.getFlowerRequest().getNumberOfBouquets()));
    assigneeField.setText(ListServiceRequestController.getFlowerRequest().getAssignee());
    specialInstructionsField.setText(
        ListServiceRequestController.getFlowerRequest().getSpecialInstructions());
  }

  @FXML
  public void resetButtonClicked() {
    Navigation.navigate(Screen.LIST_REQUESTS);
  }

  @FXML
  public void backButtonClicked() {
    Navigation.navigate(Screen.HOME);
  }

  @FXML
  public void submitButtonClicked() {}

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
