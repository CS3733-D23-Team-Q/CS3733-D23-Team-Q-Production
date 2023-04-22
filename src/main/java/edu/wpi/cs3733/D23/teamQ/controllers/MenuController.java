package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.App;
import edu.wpi.cs3733.D23.teamQ.navigation.Navigation;
import edu.wpi.cs3733.D23.teamQ.navigation.Screen;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class MenuController {
  @FXML AnchorPane menuPane;
  @FXML MFXButton home;
  @FXML ImageView homeIcon;
  @FXML VBox spacer;
  @FXML PeopleBlockController peopleBlockController;
  @FXML NavigationBlockController navigationBlockController;
  @FXML ServiceRequestBlockController srBlockController;
  @FXML StatisticsBlockController statBlockController;
  @FXML SettingsBlockController settingsBlockController;
  @FXML SignoutBlockController signoutBlockController;

  @FXML
  public void initialize() {
    peopleBlockController.setMCController(this);
    navigationBlockController.setMCController(this);
    srBlockController.setMCController(this);
    statBlockController.setMCController(this);
    settingsBlockController.setMCController(this);
    signoutBlockController.setMCController(this);
  }

  @FXML
  public void homeClicked() {
    this.showAll();
    Navigation.navigate(Screen.HOME);
  }

  @FXML
  public void homeEntered() {
    homeIcon.setImage(new Image(App.class.getResourceAsStream("HomeBlue.png")));
  }

  @FXML
  public void homeExited() {
    homeIcon.setImage(new Image(App.class.getResourceAsStream("Home.png")));
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
