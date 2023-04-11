package edu.wpi.cs3733.D23.teamQ.controllers;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class RootController {
  public AnchorPane rootAnchor;
  @FXML MenuRootController menuController;

  @FXML
  public void initialize() {}

  public void showMenu(boolean v) {
    menuController.setVisible(v);
  }
}
