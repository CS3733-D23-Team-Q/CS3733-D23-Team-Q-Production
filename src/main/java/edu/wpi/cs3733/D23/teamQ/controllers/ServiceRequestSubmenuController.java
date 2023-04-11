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
  @FXML MenuRootController mrc;

  @FXML
  public void createClicked() {
    Navigation.navigate(Screen.SERVICE_REQUEST_HUB);
  }

  @FXML
  public void editClicked() {
    Navigation.navigate(Screen.LIST_REQUESTS);
  }

  @FXML
  public void srsmExited() {
    mrc.showSRSM(false);
  }

  public void setVisible(boolean v) {
    servicerequestSM.toFront();
    servicerequestSM.setVisible(v);
  }

  public void setRootController(MenuRootController mrc) {
    this.mrc = mrc;
  }
}
