package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.App;
import edu.wpi.cs3733.D23.teamQ.navigation.Navigation;
import edu.wpi.cs3733.D23.teamQ.navigation.Screen;
import io.github.palexdev.materialfx.controls.MFXButton;
import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class HomeBlockController {
  @FXML AnchorPane anchor;
  @FXML HBox homeHB;
  @FXML MFXButton home;
  @FXML ImageView homeIcon;
  @FXML MenuController mc;

  @FXML
  public void anchorEntered() {
    mc.closeAll();
    home.setStyle("-fx-background-color: #f1f1f1; -fx-text-fill: #012d5a");
    homeIcon.setImage(new Image(App.class.getResourceAsStream("HomeBlue.png")));
  }

  @FXML
  public void anchorExited() {
    home.setStyle("-fx-background-color: #012d5a; -fx-text-fill: #f1f1f1");
    homeIcon.setImage(new Image(App.class.getResourceAsStream("Home.png")));
  }

  public void homeClicked() throws URISyntaxException {
    alertSound("Code Blue");
    mc.showAll();
    Navigation.navigate(Screen.HOME);
  }

  @FXML
  public void homeEntered() {}

  @FXML
  public void homeExited() {}

  public void setMCController(MenuController MC) {
    mc = MC;
  }

  public void alertSound(String message) throws URISyntaxException {
    URI path = getClass().getResource("/alert.wav").toURI();

    if (message.contains("Code Blue")) path = getClass().getResource("/Blue.wav").toURI();
    if (message.contains("Code Red")) path = getClass().getResource("/Red.wav").toURI();
    if (message.contains("Code Black")) path = getClass().getResource("/Black.wav").toURI();
    if (message.contains("Code Gray")) path = getClass().getResource("/Gray.wav").toURI();
    if (message.contains("Code Yellow")) path = getClass().getResource("/Yellow.wav").toURI();
    if (message.contains("Code Orange")) path = getClass().getResource("/Orange.wav").toURI();
    if (message.contains("Code Pink")) path = getClass().getResource("/Pink.wav").toURI();
    if (message.contains("Code Purple")) path = getClass().getResource("/Purple.wav").toURI();
    if (message.contains("Code Green")) path = getClass().getResource("/Green.wav").toURI();
    if (message.contains("Code Silver")) path = getClass().getResource("/Silver.wav").toURI();

    Media media = new Media(new File(path).toURI().toString());
    MediaPlayer mediaPlayer = new MediaPlayer(media);
    mediaPlayer.play();
  }
}
