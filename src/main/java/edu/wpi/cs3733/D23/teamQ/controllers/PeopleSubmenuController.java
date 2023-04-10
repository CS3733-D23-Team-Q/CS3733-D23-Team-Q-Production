package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.navigation.Navigation;
import edu.wpi.cs3733.D23.teamQ.navigation.Screen;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

public class PeopleSubmenuController {
  @FXML VBox peopleSM;
  @FXML MFXButton profile;
  @FXML MFXButton directory;

  @FXML
  public void profileClicked() {
    Navigation.navigate(Screen.PROFILE_PAGE);
  }

  @FXML
  public void directoryClicked() {
    // Navigation.navigate(Screen.Directory);
  }

  @FXML
  public void psmExited() {
    setVisible(false);
  }

  public void setVisible(boolean v) {
    peopleSM.toFront();
    peopleSM.setVisible(v);
  }
}
