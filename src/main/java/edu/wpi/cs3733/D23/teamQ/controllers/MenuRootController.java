package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.navigation.Navigation;
import edu.wpi.cs3733.D23.teamQ.navigation.Screen;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

public class MenuRootController {
  @FXML MFXButton home;
  @FXML MFXButton people;
  @FXML MFXButton navigation;
  @FXML MFXButton servicerequests;
  @FXML MFXButton statistics;
  @FXML MFXButton settings;
  @FXML MFXButton signout;

  @FXML
  public void initialize() {
    home.setStyle("-fx-background-color: #012d5a");
    people.setStyle("-fx-background-color: #012d5a");
    navigation.setStyle("-fx-background-color: #012d5a");
    servicerequests.setStyle("-fx-background-color: #012d5a");
    statistics.setStyle("-fx-background-color: #012d5a");
    settings.setStyle("-fx-background-color: #012d5a");
    signout.setStyle("-fx-background-color: #012d5a");
  }

  @FXML
  public void homeClicked() {
    home.setStyle("-fx-background-color: #f1f1f1");
    Navigation.navigate(Screen.HOME);
  }

  @FXML
  public void peopleClicked() {
    people.setStyle("-fx-background-color: #f1f1f1");
    Navigation.navigate(Screen.PROFILE_PAGE);
  }

  @FXML
  public void navigationClicked() {
    navigation.setStyle("-fx-background-color: #f1f1f1");
    Navigation.navigate(Screen.PATH_TEXT);
  }

  @FXML
  public void srClicked() {
    servicerequests.setStyle("-fx-background-color: #f1f1f1");
    Navigation.navigate(Screen.SERVICE_REQUEST_HUB);
  }

  @FXML
  public void statisticsClicked() {
    statistics.setStyle("-fx-background-color: #f1f1f1");
    Platform.exit();
  }

  @FXML
  public void settingsClicked() {
    settings.setStyle("-fx-background-color: #f1f1f1");
    Navigation.navigate(Screen.HELP);
  }

  @FXML
  public void signoutClicked() {
    signout.setStyle("-fx-background-color: #f1f1f1");
    Navigation.logout();
  }
}
