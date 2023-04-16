package edu.wpi.cs3733.D23.teamQ.controllers;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class RootController {
  @FXML public AnchorPane rootCenter;
  @FXML public AnchorPane rootRight;
  @FXML MenuController menuController;

  @FXML
  public void initialize() {}

  public void showMenu(boolean v) {
    menuController.setVisible(v);
  }
}
