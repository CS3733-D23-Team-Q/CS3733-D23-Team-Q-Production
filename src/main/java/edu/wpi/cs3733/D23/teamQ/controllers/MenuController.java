package edu.wpi.cs3733.D23.teamQ.controllers;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class MenuController {
  @FXML AnchorPane menuPane;
  @FXML VBox spacer;
  @FXML HomeBlockController homeBlockController;
  @FXML PeopleBlockController peopleBlockController;
  @FXML NavigationBlockController navigationBlockController;
  @FXML ServiceRequestBlockController srBlockController;
  @FXML StatisticsBlockController statBlockController;
  @FXML SettingsBlockController settingsBlockController;
  @FXML SignoutBlockController signoutBlockController;

  @FXML
  public void initialize() {
    homeBlockController.setMCController(this);
    peopleBlockController.setMCController(this);
    navigationBlockController.setMCController(this);
    srBlockController.setMCController(this);
    statBlockController.setMCController(this);
    settingsBlockController.setMCController(this);
    signoutBlockController.setMCController(this);
  }

  @FXML
  public void spacerEntered() {
    closeAll();
  }

  @FXML
  public void closeAll() {
    homeBlockController.hideAlert();
    peopleBlockController.hideSM();
    navigationBlockController.hideSM();
    srBlockController.hideSM();
    statBlockController.hideSM();
    settingsBlockController.hideSM();
    signoutBlockController.hideSM();
  }

  @FXML
  public void showAll() {
    navigationBlockController.showBlock();
    peopleBlockController.showBlock();
    srBlockController.showBlock();
    settingsBlockController.showBlock();
    signoutBlockController.showBlock();
    statBlockController.showBlock();
  }

  @FXML
  public void hideAll() {
    navigationBlockController.hideBlock();
    peopleBlockController.hideBlock();
    srBlockController.hideBlock();
    settingsBlockController.hideBlock();
    signoutBlockController.hideBlock();
    statBlockController.hideBlock();
  }

  @FXML
  public void setVisible(boolean v) {
    menuPane.setVisible(v);
  }
}
