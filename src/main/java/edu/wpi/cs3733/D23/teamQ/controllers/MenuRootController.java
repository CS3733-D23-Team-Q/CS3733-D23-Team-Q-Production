package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.navigation.Navigation;
import edu.wpi.cs3733.D23.teamQ.navigation.Screen;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

public class MenuRootController {
  @FXML MFXButton home;
  @FXML MFXButton people;
  @FXML MFXButton navigation;
  @FXML MFXButton servicerequest;
  @FXML MFXButton statistics;
  @FXML MFXButton settings;
  @FXML MFXButton signout;
  @FXML VBox mainPane;
  @FXML PeopleSubmenuController peopleSMController;
  @FXML NavigationSubmenuController navigationSMController;
  @FXML ServiceRequestSubmenuController servicerequestSMController;
  @FXML StatisticsSubmenuController statisticsSMController;
  @FXML SettingsSubmenuController settingsSMController;
  @FXML SignoutSubmenuController signoutSMController;

  @FXML
  public void initialize() {
  }

  @FXML
  public void homeClicked() {
    Navigation.navigate(Screen.HOME);
  }

  @FXML
  public void peopleEntered() {
    peopleSMController.setVisible(true);
  }

  @FXML
  public void peopleExited() {
    peopleSMController.setVisible(false);
  }

  @FXML
  public void navEntered() {
    navigationSMController.setVisible(true);
  }

  @FXML
  public void navExited() {
    navigationSMController.setVisible(false);
  }

  @FXML
  public void srEntered() {
    servicerequestSMController.setVisible(true);
  }

  @FXML
  public void srExited() {
    servicerequestSMController.setVisible(false);
  }

  @FXML
  public void statEntered() {
    statisticsSMController.setVisible(true);
  }

  @FXML
  public void statExited() {
    statisticsSMController.setVisible(false);
  }

  @FXML
  public void setEntered() {
    settingsSMController.setVisible(true);
  }

  @FXML
  public void setExited() {
    settingsSMController.setVisible(false);
  }

  @FXML
  public void soEntered() {
    signoutSMController.setVisible(true);
  }

  @FXML
  public void soExited() {
    signoutSMController.setVisible(false);
  }
}
