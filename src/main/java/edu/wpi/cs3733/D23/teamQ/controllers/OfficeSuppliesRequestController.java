package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.navigation.Navigation;
import edu.wpi.cs3733.D23.teamQ.navigation.Screen;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.FXML;

public class OfficeSuppliesRequestController {
  @FXML private MFXButton navigateFromOffiButton;

  public void initialize() {
    this.navigateFromOffiButton.setOnMouseClicked(event -> Navigation.navigate(Screen.HOME));
  }
}
