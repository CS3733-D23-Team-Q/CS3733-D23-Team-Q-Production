package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.navigation.Navigation;
import edu.wpi.cs3733.D23.teamQ.navigation.Screen;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

public class NavigationSubmenuAdminController {
  @FXML VBox navigationSM;
  @FXML MFXButton pathfinder;

  @FXML MFXButton addSignButton;

  @FXML MFXButton displaySignButton;

  @FXML MFXButton editor;
  @FXML MFXButton oMover;

  @FXML
  public void pathfinderClicked() {
    Navigation.navigate(Screen.PATH_FINDING);
    // Navigation.navigate(Screen.PATH_FINDING_ADMIN);
  }

  @FXML
  public void editorClicked() {
    Navigation.navigate(Screen.MAP_EDITOR_TABLE);
  }

  public void moveClicked() {
    Navigation.navigate(Screen.OFFICE_MOVE);
  }

  public void addSignButtonClicked() {
    Navigation.navigate(Screen.ADD_SIGNAGE);
  };

  public void displaySignButtonClicked() {
    Navigation.navigate(Screen.SELECT_SIGNAGE);
  }
}
