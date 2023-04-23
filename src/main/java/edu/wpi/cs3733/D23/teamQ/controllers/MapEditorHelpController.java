package edu.wpi.cs3733.D23.teamQ.controllers;

import javafx.stage.Stage;

public class MapEditorHelpController implements IController {

  static Stage stage;

  public void setStage(Stage stage) {
    this.stage = stage;
  }

  public Stage getStage() {
    return stage;
  }
}
