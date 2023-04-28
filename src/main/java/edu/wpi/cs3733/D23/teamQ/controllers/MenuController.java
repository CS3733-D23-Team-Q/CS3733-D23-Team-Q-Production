package edu.wpi.cs3733.D23.teamQ.controllers;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class MenuController {
  @FXML AnchorPane menuPane;
  @FXML VBox spacer;
  @FXML HomeBlockController homeBlockController;
  @FXML PeopleBlockController peopleBlockController;
  @FXML MessagesBlockController messagesBlockController;
  @FXML NavigationBlockController navigationBlockController;
  @FXML ServiceRequestBlockController srBlockController;
  @FXML InformationBlockController informationBlockController;
  @FXML SignoutBlockController signoutBlockController;

  @FXML
  public void initialize() {
    homeBlockController.setMCController(this);
    peopleBlockController.setMCController(this);
    messagesBlockController.setMCController(this);
    navigationBlockController.setMCController(this);
    srBlockController.setMCController(this);
    informationBlockController.setMCController(this);
    signoutBlockController.setMCController(this);
  }

  @FXML
  public void spacerEntered() {
    closeAll();
  }

  @FXML
  public void closeAll() {
    peopleBlockController.hideSM();
    navigationBlockController.hideSM();
    srBlockController.hideSM();
    informationBlockController.hideSM();
    signoutBlockController.hideSM();
  }

  @FXML
  public void showAll() {
    peopleBlockController.showBlock();
    messagesBlockController.showBlock();
    navigationBlockController.showBlock();
    srBlockController.showBlock();
    informationBlockController.showBlock();
    signoutBlockController.showBlock();
  }

  @FXML
  public void hideAll() {
    peopleBlockController.hideBlock();
    messagesBlockController.hideBlock();
    navigationBlockController.hideBlock();
    srBlockController.hideBlock();
    informationBlockController.hideBlock();
    signoutBlockController.hideBlock();
  }

  @FXML
  public void setVisible(boolean v) {
    menuPane.setVisible(v);
  }
}
