package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.App;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

public class StatisticsBlockController {
  @FXML AnchorPane anchor;
  @FXML HBox statisticsHB;
  @FXML MFXButton statistics;
  @FXML ImageView statisticsIcon;
  @FXML AnchorPane statisticsSMPane;
  @FXML MenuController mc;

  @FXML
  public void anchorEntered() {
    mc.closeAll();
    statistics.setStyle("-fx-background-color: #f1f1f1; -fx-text-fill: #012d5a");
    statisticsIcon.setImage(new Image(App.class.getResourceAsStream("StatisticsBlue.png")));
    statisticsSMPane.setVisible(true);
  }

  @FXML
  public void anchorExited() {
    statistics.setStyle("-fx-background-color: #012d5a; -fx-text-fill: #f1f1f1");
    statisticsIcon.setImage(new Image(App.class.getResourceAsStream("statistics.png")));
    statisticsSMPane.setVisible(false);
  }

  @FXML
  public void statisticsEntered() {}

  @FXML
  public void statisticsExited() {}

  @FXML
  public void hideBlock() {
    statisticsHB.setVisible(false);
  }

  @FXML
  public void showBlock() {
    statisticsHB.setVisible(true);
  }

  @FXML
  public void hideSM() {
    statisticsSMPane.setVisible(false);
  }

  public void setMCController(MenuController MC) {
    mc = MC;
  }
}
