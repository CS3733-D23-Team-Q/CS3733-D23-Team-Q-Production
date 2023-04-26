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
  @FXML ServiceRequestBlockController srbc;

  @FXML
  public void createEntered() {
    srbc.srCreationSMPane.setVisible(true);
  }

  @FXML
  public void editEntered() {
    srbc.srCreationSMPane.setVisible(false);
  }

  @FXML
  public void servicerequestSMEntered() {
    srbc.srCreationSMPane.setVisible(false);
  }

  @FXML
  public void editClicked() {
    Navigation.navigate(Screen.LIST_REQUESTS);
  }

  @FXML
  public void setSRBC(ServiceRequestBlockController srbc) {
    this.srbc = srbc;
  }
}
