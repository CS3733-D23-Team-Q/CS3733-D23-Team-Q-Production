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
  }

  @FXML
  public void homeClicked() {
    Navigation.navigate(Screen.HOME);
  }

  @FXML
  public void peopleClicked() {
    Navigation.navigate(Screen.PROFILE_PAGE);
  }

  @FXML
  public void navigationClicked() {
    Navigation.navigate(Screen.PATH_TEXT);
  }

  @FXML
  public void srClicked() {
    Navigation.navigate(Screen.SERVICE_REQUEST_HUB);
  }

  @FXML
  public void statisticsClicked() {
    Platform.exit();
  }

  @FXML
  public void settingsClicked() {
    Navigation.navigate(Screen.HELP);
  }

  @FXML
  public void signoutClicked() {
    Navigation.logout();
  }
}
