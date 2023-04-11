package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.navigation.Navigation;
import edu.wpi.cs3733.D23.teamQ.navigation.Screen;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

public class MenuRootController {
  @FXML MFXButton home;
  @FXML MFXButton people;
  @FXML MFXButton navigation;
  @FXML MFXButton servicerequest;
  @FXML MFXButton statistics;
  @FXML MFXButton settings;
  @FXML MFXButton signout;
  @FXML HBox mainPane;
  @FXML AnchorPane subPane;
  @FXML PeopleSubmenuController peopleSMController;
  @FXML NavigationSubmenuController navigationSMController;
  @FXML ServiceRequestSubmenuController servicerequestSMController;
  @FXML StatisticsSubmenuController statisticsSMController;
  @FXML SettingsSubmenuController settingsSMController;
  @FXML SignoutSubmenuController signoutSMController;

  @FXML
  public void initialize() {}

  @FXML
  public void homeClicked() {
    Navigation.navigate(Screen.HOME);
  }

  @FXML
  public void homeEntered() {
    peopleSMController.setVisible(false);
    navigationSMController.setVisible(false);
    servicerequestSMController.setVisible(false);
    statisticsSMController.setVisible(false);
    settingsSMController.setVisible(false);
    signoutSMController.setVisible(false);
  }

  @FXML
  public void peopleEntered() {
    peopleSMController.setVisible(true);
    navigationSMController.setVisible(false);
    servicerequestSMController.setVisible(false);
    statisticsSMController.setVisible(false);
    settingsSMController.setVisible(false);
    signoutSMController.setVisible(false);
    //    TranslateTransition tt = new TranslateTransition();
    //    javafx.util.Duration duration = Duration.millis(200);
    //    tt.setToX(432);
    //    tt.setDuration(duration);
    //    tt.setNode(subPane);
    //    tt.play();
  }

  @FXML
  public void navEntered() {
    peopleSMController.setVisible(false);
    navigationSMController.setVisible(true);
    servicerequestSMController.setVisible(false);
    statisticsSMController.setVisible(false);
    settingsSMController.setVisible(false);
    signoutSMController.setVisible(false);
  }

  @FXML
  public void srEntered() {
    peopleSMController.setVisible(false);
    navigationSMController.setVisible(false);
    servicerequestSMController.setVisible(true);
    statisticsSMController.setVisible(false);
    settingsSMController.setVisible(false);
    signoutSMController.setVisible(false);
  }

  @FXML
  public void statEntered() {
    peopleSMController.setVisible(false);
    navigationSMController.setVisible(false);
    servicerequestSMController.setVisible(false);
    statisticsSMController.setVisible(true);
    settingsSMController.setVisible(false);
    signoutSMController.setVisible(false);
  }

  @FXML
  public void setEntered() {
    peopleSMController.setVisible(false);
    navigationSMController.setVisible(false);
    servicerequestSMController.setVisible(false);
    statisticsSMController.setVisible(false);
    settingsSMController.setVisible(true);
    signoutSMController.setVisible(false);
  }

  @FXML
  public void soEntered() {
    peopleSMController.setVisible(false);
    navigationSMController.setVisible(false);
    servicerequestSMController.setVisible(false);
    statisticsSMController.setVisible(false);
    settingsSMController.setVisible(false);
    signoutSMController.setVisible(true);
  }

  public void setVisible(boolean v) {
    mainPane.setVisible(v);
  }
}
