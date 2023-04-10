package edu.wpi.cs3733.D23.teamQ.controllers;

import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

public class StatisticsSubmenuController {
  @FXML VBox statisticsSM;
  @FXML MFXButton statistics;
  @FXML MFXButton temp1;
  @FXML MFXButton temp2;

  @FXML
  public void statisticsClicked() {
    // Navigation.navigate(Screen.Statistics);
  }

  @FXML
  public void temp1Clicked() {
    // Navigation.navigate(Screen.Statistics);
  }

  @FXML
  public void temp2Clicked() {
    // Navigation.navigate(Screen.Statistics);
  }

  public void setVisible(boolean v) {
    statisticsSM.toFront();
    statisticsSM.setVisible(v);
  }
}
