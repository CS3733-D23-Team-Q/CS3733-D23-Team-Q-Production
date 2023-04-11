package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.App;
import edu.wpi.cs3733.D23.teamQ.navigation.Navigation;
import edu.wpi.cs3733.D23.teamQ.navigation.Screen;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

public class SignoutSubmenuController {
  @FXML VBox signoutSM;
  @FXML MFXButton signout;
  @FXML MFXButton exit;

  @FXML
  public void signoutClicked() {
    App.getRController().showMenu(false);
    Navigation.navigate(Screen.LOGIN);
  }

  @FXML
  public void exitClicked() {
    Platform.exit();
  }

  @FXML
  public void sosmExited() {
    setVisible(false);
  }

  public void setVisible(boolean v) {
    signoutSM.toFront();
    signoutSM.setVisible(v);
  }
}
