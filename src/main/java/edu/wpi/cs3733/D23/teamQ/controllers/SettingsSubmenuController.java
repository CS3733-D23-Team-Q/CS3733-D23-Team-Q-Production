package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.navigation.Navigation;
import edu.wpi.cs3733.D23.teamQ.navigation.Screen;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

public class SettingsSubmenuController {
  @FXML VBox settingsSM;
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

  @FXML
  public void sesmExited() {
    setVisible(false);
  }

  public void setVisible(boolean v) {
    settingsSM.toFront();
    settingsSM.setVisible(v);
  }
}
