package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.App;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

public class InformationBlockController {
  @FXML AnchorPane anchor;
  @FXML HBox informationHB;
  @FXML MFXButton information;
  @FXML ImageView informationIcon;
  @FXML AnchorPane informationSMPane;
  @FXML InformationSubmenuController informationSMController;
  @FXML MenuController mc;

  @FXML
  public void initialize() {
    informationSMController.setSBC(this);
  }

  @FXML
  public void anchorEntered() {
    mc.closeAll();
    information.setStyle("-fx-background-color: #f1f1f1; -fx-text-fill: #012d5a");
    informationIcon.setImage(new Image(App.class.getResourceAsStream("SettingsBlue.png")));
    informationSMPane.setVisible(true);
  }

  @FXML
  public void anchorExited() {
    information.setStyle("-fx-background-color: #012d5a; -fx-text-fill: #f1f1f1");
    informationIcon.setImage(new Image(App.class.getResourceAsStream("Settings.png")));
    informationSMPane.setVisible(false);
  }

  @FXML
  public void informationEntered() {}

  @FXML
  public void informationExited() {}

  @FXML
  public void hideBlock() {
    informationHB.setVisible(false);
  }

  @FXML
  public void showBlock() {
    informationHB.setVisible(true);
  }

  @FXML
  public void hideAllButHome() {
    mc.hideAll();
  }

  @FXML
  public void hideSM() {
    informationSMPane.setVisible(false);
  }

  public void setMCController(MenuController MC) {
    mc = MC;
  }
}
