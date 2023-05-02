package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.navigation.Navigation;
import edu.wpi.cs3733.D23.teamQ.navigation.Screen;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

public class NavigationSubmenuController {
  @FXML VBox navigationSM;
  @FXML MFXButton pathfinder;
  @FXML MFXButton signage;

  @FXML
  public void pathfinderClicked() {
    Navigation.navigate(Screen.PATH_FINDING);
  }

  @FXML
  public void signageClicked() {
    Navigation.navigate(Screen.SELECT_SIGNAGE);
  }
}
