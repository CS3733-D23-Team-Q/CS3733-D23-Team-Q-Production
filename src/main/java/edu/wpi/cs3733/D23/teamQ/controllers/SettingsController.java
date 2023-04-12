package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.navigation.Navigation;
import edu.wpi.cs3733.D23.teamQ.navigation.Screen;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.FXML;

public class SettingsController {

  @FXML MFXButton backButton;

  @FXML
  public void initialize() {};

  public void backButtonClicked() {
    Navigation.navigate(Screen.HOME);
  }
}
