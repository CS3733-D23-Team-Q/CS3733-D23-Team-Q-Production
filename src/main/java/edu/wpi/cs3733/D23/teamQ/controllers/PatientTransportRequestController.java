package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.navigation.Navigation;
import edu.wpi.cs3733.D23.teamQ.navigation.Screen;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.FXML;

public class PatientTransportRequestController {
  @FXML private MFXButton navigateFromPTButton;

  public void initialize() {
    this.navigateFromPTButton.setOnMouseClicked(event -> Navigation.navigate(Screen.HOME));
  }
}
