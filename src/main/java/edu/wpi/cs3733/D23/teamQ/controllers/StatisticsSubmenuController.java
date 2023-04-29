package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.navigation.Navigation;
import edu.wpi.cs3733.D23.teamQ.navigation.Screen;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

public class StatisticsSubmenuController {
  @FXML VBox statisticsSM;
  @FXML MFXButton statistics;

  @FXML
  public void statisticsClicked() {
    Navigation.navigate(Screen.STATISTICS);
  }

  public void setMCController(MenuController menuController) {}
}
