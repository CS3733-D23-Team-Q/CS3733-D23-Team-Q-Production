package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.db.Qdb;
import edu.wpi.cs3733.D23.teamQ.navigation.Navigation;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

public class SignoutSubmenuController {
  @FXML VBox signoutSM;
  @FXML MFXButton signout;
  @FXML MFXButton exit;
  Qdb qdb = Qdb.getInstance();
  @FXML
  public void signoutClicked() {
    Navigation.logout();
    qdb.getAccountFromUsername(LoginController.getLoginUsername()).setActive(false);
  }

  @FXML
  public void exitClicked() {
    Platform.exit();
    qdb.getAccountFromUsername(LoginController.getLoginUsername()).setActive(false);
  }

  @FXML
  boolean soHovered() {
    return signoutSM.isHover();
  }
}
