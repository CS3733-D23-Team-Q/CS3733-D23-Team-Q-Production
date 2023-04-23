package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.navigation.Navigation;
import edu.wpi.cs3733.D23.teamQ.navigation.Screen;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

public class SettingsSubmenuController {
  @FXML VBox settingsSM;
  @FXML MFXButton settings;
  @FXML MFXButton help;
  @FXML MFXButton about;
  @FXML MFXButton credits;
  @FXML SettingsBlockController sbc;

  @FXML
  public void settingsClicked() {
    Navigation.navigate(Screen.SETTINGS);
  }

  @FXML
  public void helpClicked() {
    Navigation.navigate(Screen.HELP);
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
  public void setSBC(SettingsBlockController sbc) {
    this.sbc = sbc;
  }
}
