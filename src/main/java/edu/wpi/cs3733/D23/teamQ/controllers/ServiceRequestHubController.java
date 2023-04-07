package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.navigation.Navigation;
import edu.wpi.cs3733.D23.teamQ.navigation.Screen;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;

public class ServiceRequestHubController implements IController {
  @FXML Button ccrButton;
  @FXML Button mdButton;
  @FXML Button frdButton;
  @FXML Button fedButton;
  @FXML Button ospButton;
  @FXML Button ptButton;

  @FXML Button homeButton;

  @FXML MenuItem homeItem;
  @FXML MenuItem exitItem;
  @FXML MenuItem profileItem;

  @FXML
  public void initialize() {};

  @FXML
  public void ccrButtonClicked() {
    Navigation.navigate(Screen.CONFERENCE_ROOM_REQUEST);
  }

  @FXML
  public void mdButtonClicked() {
    Navigation.navigate(Screen.MEAL_REQUEST);
  }

  @FXML
  public void frdButtonClicked() {
    Navigation.navigate(Screen.FLOWER_REQUEST);
  }

  @FXML
  public void fedButtonClicked() {
    Navigation.navigate(Screen.FURNITURE_REQUEST);
  }

  @FXML
  public void ospButtonClicked() {
    Navigation.navigate(Screen.OFFICE_SUPPLIES_REQUEST);
  }

  @FXML
  public void ptButtonClicked() {
    Navigation.navigate(Screen.PATIENT_TRANSPORT_REQUEST);
  }

  @FXML
  public void homeButtonClicked() {
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
