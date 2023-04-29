package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.navigation.Navigation;
import edu.wpi.cs3733.D23.teamQ.navigation.Screen;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

public class InformationSubmenuController {
  @FXML VBox informationSM;
  @FXML MFXButton createAlert;
  @FXML MFXButton settings;
  @FXML MFXButton statistics;
  @FXML MFXButton about;
  @FXML MFXButton credits;
  @FXML InformationBlockController sbc;

  @FXML
  public void createAlertClicked() {
    Navigation.navigate(Screen.CREATE_ALERT);
  }

  @FXML
  public void settingsClicked() {
    Navigation.navigate(Screen.SETTINGS);
  }

  @FXML
  public void statisticsClicked() {
    Navigation.navigate(Screen.STATISTICS);
  }

  @FXML
  public void aboutClicked() {
    sbc.hideAllButHome();
    Navigation.navigate(Screen.ABOUT);
  }

  @FXML
  public void creditsClicked() {
    sbc.hideAllButHome();
    Navigation.navigate(Screen.CREDITS);
  }

  @FXML
  public void setSBC(InformationBlockController sbc) {
    this.sbc = sbc;
  }
}
