package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.navigation.Navigation;
import edu.wpi.cs3733.D23.teamQ.navigation.Screen;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.FXML;

public class MealDeliveryRequestController {
  @FXML private MFXButton navigateFromMealButton;

  public void initialize() {
    this.navigateFromMealButton.setOnMouseClicked(event -> Navigation.navigate(Screen.HOME));
  }
}
