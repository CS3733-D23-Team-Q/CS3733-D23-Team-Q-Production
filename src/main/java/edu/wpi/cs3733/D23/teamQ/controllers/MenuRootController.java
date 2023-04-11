package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.navigation.Navigation;
import edu.wpi.cs3733.D23.teamQ.navigation.Screen;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class MenuRootController {
  @FXML MFXButton home;
  @FXML MFXButton people;
  @FXML MFXButton navigation;
  @FXML MFXButton servicerequest;
  @FXML MFXButton statistics;
  @FXML MFXButton settings;
  @FXML MFXButton signout;
  @FXML AnchorPane mainPane;
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
    navigationSMController.setVisible(false);
    servicerequestSMController.setVisible(false);
    statisticsSMController.setVisible(false);
    settingsSMController.setVisible(false);
    signoutSMController.setVisible(false);
  }

  @FXML
  public void peopleEntered() {
    showPeopleSM(true);
  }

  @FXML
  public void navEntered() {
    showNavSM(true);
  }

  @FXML
  public void srEntered() {
    showSRSM(true);
  }

  @FXML
  public void statEntered() {
    showStatSM(true);
  }

  @FXML
  public void setEntered() {
    showSetSM(true);
  }

  @FXML
  public void soEntered() {
    showSOSM(true);
  }

  public void setVisible(boolean v) {
    mainPane.setVisible(v);
  }

  public void showPeopleSM(boolean v) {
    subPane.setVisible(v);
    peopleSMController.setVisible(v);
    navigationSMController.setVisible(false);
    servicerequestSMController.setVisible(false);
    statisticsSMController.setVisible(false);
    settingsSMController.setVisible(false);
    signoutSMController.setVisible(false);
  }

  public void showNavSM(boolean v) {
    subPane.setVisible(v);
    peopleSMController.setVisible(false);
    navigationSMController.setVisible(v);
    servicerequestSMController.setVisible(false);
    statisticsSMController.setVisible(false);
    settingsSMController.setVisible(false);
    signoutSMController.setVisible(false);
  }

  public void showSRSM(boolean v) {
    subPane.setVisible(v);
    peopleSMController.setVisible(false);
    navigationSMController.setVisible(false);
    servicerequestSMController.setVisible(v);
    statisticsSMController.setVisible(false);
    settingsSMController.setVisible(false);
    signoutSMController.setVisible(false);
  }

  public void showStatSM(boolean v) {
    subPane.setVisible(v);
    peopleSMController.setVisible(false);
    navigationSMController.setVisible(false);
    servicerequestSMController.setVisible(false);
    statisticsSMController.setVisible(v);
    settingsSMController.setVisible(false);
    signoutSMController.setVisible(false);
  }

  public void showSetSM(boolean v) {
    subPane.setVisible(v);
    peopleSMController.setVisible(false);
    navigationSMController.setVisible(false);
    servicerequestSMController.setVisible(false);
    statisticsSMController.setVisible(false);
    settingsSMController.setVisible(v);
    signoutSMController.setVisible(false);
  }

  public void showSOSM(boolean v) {
    subPane.setVisible(v);
    peopleSMController.setVisible(false);
    navigationSMController.setVisible(false);
    servicerequestSMController.setVisible(false);
    statisticsSMController.setVisible(false);
    settingsSMController.setVisible(false);
    signoutSMController.setVisible(v);
  }
}

//    TranslateTransition tt = new TranslateTransition();
//    javafx.util.Duration duration = Duration.millis(200);
//    tt.setToX(432);
//    tt.setDuration(duration);
//    tt.setNode(subPane);
//    tt.play();
