package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.App;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

public class SignoutBlockController {
  @FXML HBox signoutHB;
  @FXML MFXButton signout;
  @FXML ImageView signoutIcon;
  @FXML AnchorPane signoutSMPane;

  @FXML
  public void signoutEntered() {
    signoutSMPane.setVisible(true);
    signoutIcon.setImage(new Image(App.class.getResourceAsStream("ExitBlue.png")));
  }

  @FXML
  public void signoutExited() {
    signoutSMPane.setVisible(false);
    signoutIcon.setImage(new Image(App.class.getResourceAsStream("Exit.png")));
  }
}
