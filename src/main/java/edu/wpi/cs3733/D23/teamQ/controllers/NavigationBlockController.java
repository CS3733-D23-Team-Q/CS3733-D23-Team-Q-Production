package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.App;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

public class NavigationBlockController {
  @FXML AnchorPane anchor;
  @FXML HBox navigationHB;
  @FXML MFXButton navigation;
  @FXML AnchorPane navigationSMPane;
  @FXML ImageView navigationIcon;
  @FXML MenuController mc;

  @FXML
  public void anchorEntered() {
    mc.closeAll();
    navigation.setStyle("-fx-background-color: #f1f1f1; -fx-text-fill: #012d5a");
    navigationIcon.setImage(new Image(App.class.getResourceAsStream("MapBlue.png")));
    navigationSMPane.setVisible(true);
  }

  @FXML
  public void anchorExited() {
    navigation.setStyle("-fx-background-color: #012d5a; -fx-text-fill: #f1f1f1");
    navigationIcon.setImage(new Image(App.class.getResourceAsStream("Map.png")));
    navigationSMPane.setVisible(false);
  }

  @FXML
  public void navigationEntered() {}

  @FXML
  public void navigationExited() {}

  @FXML
  public void hideSM() {
    navigationSMPane.setVisible(false);
  }

  public void setMCController(MenuController MC) {
    mc = MC;
  }
}
