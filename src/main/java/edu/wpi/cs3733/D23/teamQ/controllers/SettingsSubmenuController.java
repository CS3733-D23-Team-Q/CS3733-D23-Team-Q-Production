package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.navigation.Navigation;
import edu.wpi.cs3733.D23.teamQ.navigation.Screen;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.FXML;

public class SettingsSubmenuController {
  @FXML MFXButton aSettings;
  @FXML MFXButton dSettings;
  @FXML MFXButton help;

  @FXML
  public void aSettingsClicked() {
    Navigation.navigate(Screen.HELP);
  }

  @FXML
  public void dSettingsClicked() {
    Navigation.navigate(Screen.HELP);
  }

  @FXML
  public void helpClicked() {
    Navigation.navigate(Screen.HELP);
  }
}
