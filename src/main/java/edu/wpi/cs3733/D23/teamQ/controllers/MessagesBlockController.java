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

public class MessagesBlockController {
  @FXML AnchorPane anchor;
  @FXML HBox messagesHB;
  @FXML MFXButton messages;
  @FXML ImageView messagesIcon;
  @FXML MenuController mc;

  @FXML
  public void anchorEntered() {
    mc.closeAll();
    messages.setStyle("-fx-background-color: #f1f1f1; -fx-text-fill: #012d5a");
    messagesIcon.setImage(new Image(App.class.getResourceAsStream("MessagesBlue.png")));
  }

  @FXML
  public void anchorExited() {
    messages.setStyle("-fx-background-color: #012d5a; -fx-text-fill: #f1f1f1");
    messagesIcon.setImage(new Image(App.class.getResourceAsStream("Messages.png")));
  }

  public void messagesClicked() {
    mc.showAll();
    Navigation.navigate(Screen.MESSAGES);
  }

  @FXML
  public void messagesEntered() {}

  @FXML
  public void messagesExited() {}

  public void setMCController(MenuController MC) {
    mc = MC;
  }

  @FXML
  public void hideBlock() {
    messagesHB.setVisible(false);
  }

  @FXML
  public void showBlock() {
    messagesHB.setVisible(true);
  }
}
