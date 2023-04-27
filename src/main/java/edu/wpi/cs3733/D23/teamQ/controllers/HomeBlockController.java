package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.App;
import edu.wpi.cs3733.D23.teamQ.navigation.Navigation;
import edu.wpi.cs3733.D23.teamQ.navigation.Screen;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

public class HomeBlockController {
  @FXML AnchorPane anchor;
  @FXML HBox homeHB;
  @FXML MFXButton home;
  @FXML ImageView homeIcon;
  @FXML AnchorPane homeAlertPane;
  @FXML MenuController mc;

  @FXML
  public void anchorEntered() {
    mc.closeAll();
    home.setStyle("-fx-background-color: #f1f1f1; -fx-text-fill: #012d5a");
    homeIcon.setImage(new Image(App.class.getResourceAsStream("HomeBlue.png")));
    homeAlertPane.setVisible(true);
  }

  @FXML
  public void anchorExited() {
    home.setStyle("-fx-background-color: #012d5a; -fx-text-fill: #f1f1f1");
    homeIcon.setImage(new Image(App.class.getResourceAsStream("Home.png")));
    homeAlertPane.setVisible(false);
  }

  public void homeClicked() {
    mc.showAll();
    Navigation.navigate(Screen.HOME);
  }

  @FXML
  public void homeEntered() {}

  @FXML
  public void homeExited() {}

  @FXML
  public void hideAlert() {}

  public void setMCController(MenuController MC) {
    mc = MC;
  }
}
