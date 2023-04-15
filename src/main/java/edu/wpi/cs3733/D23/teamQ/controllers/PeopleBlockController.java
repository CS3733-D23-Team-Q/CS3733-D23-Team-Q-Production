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
  @FXML HBox peopleHB;
  @FXML MFXButton people;
  @FXML ImageView peopleIcon;
  @FXML AnchorPane peopleSMPane;

  @FXML
  public void peopleEntered() {
    peopleSMPane.setVisible(true);
    peopleIcon.setImage(new Image(App.class.getResourceAsStream("PeopleBlue.png")));
  }

  @FXML
  public void peopleExited() {
    peopleSMPane.setVisible(false);
    peopleIcon.setImage(new Image(App.class.getResourceAsStream("People.png")));
  }
}
