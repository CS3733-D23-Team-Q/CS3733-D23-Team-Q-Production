package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.App;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

public class SettingsBlockController {
  @FXML HBox settingsHB;
  @FXML MFXButton settings;
  @FXML ImageView settingsIcon;
  @FXML AnchorPane settingsSMPane;

  @FXML
  public void settingsEntered() {
    settingsSMPane.setVisible(true);
    settingsIcon.setImage(new Image(App.class.getResourceAsStream("SettingsBlue.png")));
  }

  @FXML
  public void settingsExited() {
    settingsSMPane.setVisible(false);
    settingsIcon.setImage(new Image(App.class.getResourceAsStream("Settings.png")));
  }
}
