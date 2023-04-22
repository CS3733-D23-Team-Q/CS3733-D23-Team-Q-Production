package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.App;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

public class SignoutBlockController {
  @FXML AnchorPane anchor;
  @FXML HBox signoutHB;
  @FXML MFXButton signout;
  @FXML ImageView signoutIcon;
  @FXML AnchorPane signoutSMPane;
  @FXML MenuController mc;

  @FXML
  public void anchorEntered() {
    mc.closeAll();
    signout.setStyle("-fx-background-color: #f1f1f1; -fx-text-fill: #012d5a");
    signoutIcon.setImage(new Image(App.class.getResourceAsStream("ExitBlue.png")));
    signoutSMPane.setVisible(true);
  }

  @FXML
  public void anchorExited() {
    signout.setStyle("-fx-background-color: #012d5a; -fx-text-fill: #f1f1f1");
    signoutIcon.setImage(new Image(App.class.getResourceAsStream("Exit.png")));
    signoutSMPane.setVisible(false);
  }

  @FXML
  public void signoutEntered() {}

  @FXML
  public void signoutExited() {}

  @FXML
  public void hideBlock() {
    signoutHB.setVisible(false);
  }

  @FXML
  public void showBlock() {
    signoutHB.setVisible(true);
  }

  @FXML
  public void hideSM() {
    signoutSMPane.setVisible(false);
  }

  public void setMCController(MenuController MC) {
    mc = MC;
  }
}
