package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.App;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

public class NavigationBlockController {
  @FXML HBox navigationHB;
  @FXML MFXButton navigation;
  @FXML AnchorPane navigationSMPane;
  @FXML ImageView navigationIcon;

  @FXML
  public void navigationEntered() {
    navigationSMPane.setVisible(true);
    navigationIcon.setImage(new Image(App.class.getResourceAsStream("MapBlue.png")));
  }

  @FXML
  public void navigationExited() {
    navigationSMPane.setVisible(false);
    navigationIcon.setImage(new Image(App.class.getResourceAsStream("Map.png")));
  }
}
