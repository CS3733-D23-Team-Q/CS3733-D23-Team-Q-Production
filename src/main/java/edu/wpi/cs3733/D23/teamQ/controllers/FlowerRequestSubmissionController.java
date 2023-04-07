package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.navigation.Navigation;
import edu.wpi.cs3733.D23.teamQ.navigation.Screen;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.FXML;

public class FlowerRequestSubmissionController {

  @FXML private MFXButton FlowerRequestSubmission_HomeButton;

  /** Switches screens to the Home page when the Home Button is pressed */
  @FXML
  public void initialize() {
    this.FlowerRequestSubmission_HomeButton.setOnMouseClicked(
        event -> {
          Navigation.navigate(Screen.HOME);
        });
  }
}
