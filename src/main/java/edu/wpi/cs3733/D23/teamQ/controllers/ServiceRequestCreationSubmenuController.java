package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.navigation.Navigation;
import edu.wpi.cs3733.D23.teamQ.navigation.Screen;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.FXML;

public class ServiceRequestCreationSubmenuController {
  @FXML MFXButton ccr;
  @FXML MFXButton frr;
  @FXML MFXButton osr;
  @FXML MFXButton fer;
  @FXML MFXButton mdr;
  @FXML MFXButton msr;

  @FXML
  public void ccrClicked() {
    Navigation.navigate(Screen.CONFERENCE_ROOM_REQUEST);
  }

  @FXML
  public void frrClicked() {
    Navigation.navigate(Screen.FLOWER_REQUEST);
  }

  @FXML
  public void osrClicked() {
    Navigation.navigate(Screen.OFFICE_SUPPLIES_REQUEST);
  }

  @FXML
  public void ferClicked() {
    Navigation.navigate(Screen.FURNITURE_REQUEST);
  }

  @FXML
  public void mdrClicked() {
    Navigation.navigate(Screen.MEAL_REQUEST);
  }

  @FXML
  public void msrClicked() {
    Navigation.navigate(Screen.MEDICAL_SUPPLIES_REQUEST);
  }
}
