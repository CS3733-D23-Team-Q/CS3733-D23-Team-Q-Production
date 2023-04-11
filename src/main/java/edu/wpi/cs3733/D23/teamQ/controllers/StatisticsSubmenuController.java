package edu.wpi.cs3733.D23.teamQ.controllers;

import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

public class StatisticsSubmenuController {
  @FXML VBox statisticsSM;
  @FXML MFXButton statistics;
  @FXML MenuRootController mrc;

  @FXML
  public void statisticsClicked() {
    // Navigation.navigate(Screen.Statistics);
  }

  @FXML
  public void stsmExited() {
    mrc.showStatSM(false);
  }

  public void setVisible(boolean v) {
    statisticsSM.toFront();
    statisticsSM.setVisible(v);
  }

  public void setRootController(MenuRootController mrc) {
    this.mrc = mrc;
  }
}
