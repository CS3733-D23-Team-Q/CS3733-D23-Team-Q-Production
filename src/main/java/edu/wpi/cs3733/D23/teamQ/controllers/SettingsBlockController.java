package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.App;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

public class SettingsBlockController {
  @FXML AnchorPane anchor;
  @FXML HBox settingsHB;
  @FXML MFXButton settings;
  @FXML ImageView settingsIcon;
  @FXML AnchorPane settingsSMPane;
  @FXML MenuController mc;

  @FXML
  public void anchorEntered() {
    mc.closeAll();
    settings.setStyle("-fx-background-color: #f1f1f1; -fx-text-fill: #012d5a");
    settingsIcon.setImage(new Image(App.class.getResourceAsStream("SettingsBlue.png")));
    settingsSMPane.setVisible(true);
  }

  @FXML
  public void anchorExited() {
    settings.setStyle("-fx-background-color: #012d5a; -fx-text-fill: #f1f1f1");
    settingsIcon.setImage(new Image(App.class.getResourceAsStream("Settings.png")));
    settingsSMPane.setVisible(false);
  }

  @FXML
  public void settingsEntered() {}

  @FXML
  public void settingsExited() {}

  @FXML
  public void hideSM() {
    settingsSMPane.setVisible(false);
  }

  public void setMCController(MenuController MC) {
    mc = MC;
  }
}
