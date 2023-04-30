package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.SecondaryStage;
import edu.wpi.cs3733.D23.teamQ.navigation.Screen;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class PathfindingSettingController extends SecondaryStage {
  static Stage stage;

  @FXML
  public void initialize() {}

  public static void display() throws IOException {
    stage = newStage(Screen.PATH_FINDING_SETTING);
    stage.show();
    stage.centerOnScreen();
  }
}
