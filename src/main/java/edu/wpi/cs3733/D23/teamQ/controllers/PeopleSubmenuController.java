package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.navigation.Navigation;
import edu.wpi.cs3733.D23.teamQ.navigation.Screen;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

public class PeopleSubmenuController {
  @FXML VBox peopleSM;
  @FXML MFXButton profile;
  @FXML MFXButton directory;

  @FXML MFXButton messages;
  @FXML MFXButton createAlert;

  @FXML
  public void profileClicked() {
    Navigation.navigate(Screen.PROFILE_PAGE);
  }

  @FXML
  public void messagesClicked() {
    Navigation.navigate(Screen.MESSAGES);
  }

  @FXML
  public void directoryClicked() {
    if (LoginController.isAdmin()) // checks if admin and if so can edit directory
    {
      Navigation.navigate(Screen.ADMIN_DIRECTORY);
    } else {
      Navigation.navigate(Screen.DIRECTORY);
    }
  }

  @FXML
  public void createAlertClicked() {
    Navigation.navigate(Screen.CREATE_ALERT);
  }
}
