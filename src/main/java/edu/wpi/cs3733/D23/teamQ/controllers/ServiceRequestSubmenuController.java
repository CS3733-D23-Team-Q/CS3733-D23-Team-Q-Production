package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.navigation.Navigation;
import edu.wpi.cs3733.D23.teamQ.navigation.Screen;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

public class ServiceRequestSubmenuController {
  @FXML VBox servicerequestSM;
  @FXML MFXButton create;
  @FXML MFXButton edit;

  @FXML
  public void createClicked() {
    Navigation.navigate(Screen.SERVICE_REQUEST_HUB);
    Navigation.navigateRight(Screen.SERVICE_PLACEHOLDER);
  }

  @FXML
  public void editClicked() {
    Navigation.navigate(Screen.LIST_REQUESTS);
    Navigation.navigateRight(Screen.SERVICE_PLACEHOLDER);
  }
}
