package edu.wpi.cs3733.D23.teamQ.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MessagesAlertController {
  @FXML Label num;

  @FXML
  public void setNum(String i) {
    num.setText(i);
  }
}
