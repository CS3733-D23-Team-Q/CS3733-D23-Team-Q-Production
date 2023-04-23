package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.App;
import io.github.palexdev.materialfx.controls.MFXButton;
import java.awt.*;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

public class PeopleBlockController {
  @FXML AnchorPane anchor;
  @FXML HBox peopleHB;
  @FXML MFXButton people;
  @FXML ImageView peopleIcon;
  @FXML AnchorPane peopleSMPane;
  @FXML MenuController mc;

  @FXML
  public void anchorEntered() {
    mc.closeAll();
    people.setStyle("-fx-background-color: #f1f1f1; -fx-text-fill: #012d5a");
    peopleIcon.setImage(new Image(App.class.getResourceAsStream("PeopleBlue.png")));
    peopleSMPane.setVisible(true);
  }

  @FXML
  public void anchorExited() {
    people.setStyle("-fx-background-color: #012d5a; -fx-text-fill: #f1f1f1");
    peopleIcon.setImage(new Image(App.class.getResourceAsStream("People.png")));
    peopleSMPane.setVisible(false);
  }

  @FXML
  public void peopleEntered() {}

  @FXML
  public void peopleExited() {}

  @FXML
  public void hideBlock() {
    peopleHB.setVisible(false);
  }

  @FXML
  public void showBlock() {
    peopleHB.setVisible(true);
  }

  @FXML
  public void hideSM() {
    peopleSMPane.setVisible(false);
  }

  public void setMCController(MenuController MC) {
    mc = MC;
  }
}
