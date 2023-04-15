package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.App;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

public class StatisticsBlockController {
  @FXML HBox statisticsHB;
  @FXML MFXButton statistics;
  @FXML ImageView statisticsIcon;
  @FXML AnchorPane statisticsSMPane;

  @FXML
  public void statisticsEntered() {
    statisticsSMPane.setVisible(true);
    statisticsIcon.setImage(new Image(App.class.getResourceAsStream("StatisticsBlue.png")));
  }

  @FXML
  public void statisticsExited() {
    statisticsSMPane.setVisible(false);
    statisticsIcon.setImage(new Image(App.class.getResourceAsStream("statistics.png")));
  }
}
