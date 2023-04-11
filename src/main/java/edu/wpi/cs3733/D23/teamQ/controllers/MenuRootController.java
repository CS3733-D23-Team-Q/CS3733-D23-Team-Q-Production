package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.navigation.Navigation;
import edu.wpi.cs3733.D23.teamQ.navigation.Screen;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class MenuRootController {
  @FXML MFXButton home;
  @FXML MFXButton people;
  @FXML MFXButton navigation;
  @FXML MFXButton servicerequest;
  @FXML MFXButton statistics;
  @FXML MFXButton settings;
  @FXML MFXButton signout;
  @FXML AnchorPane mainPane;
  @FXML VBox menuPane;
  @FXML AnchorPane subPane;
  @FXML PeopleSubmenuController peopleSMController;
  @FXML NavigationSubmenuController navigationSMController;
  @FXML ServiceRequestSubmenuController servicerequestSMController;
  @FXML StatisticsSubmenuController statisticsSMController;
  @FXML SettingsSubmenuController settingsSMController;
  @FXML SignoutSubmenuController signoutSMController;

  @FXML
  public void initialize() {
    peopleSMController.setRootController(this);
    navigationSMController.setRootController(this);
    servicerequestSMController.setRootController(this);
    statisticsSMController.setRootController(this);
    settingsSMController.setRootController(this);
    signoutSMController.setRootController(this);
  }

  @FXML
  public void menuPaneExited() {}

  @FXML
  public void subPaneExited() {
    closeAll();
  }

  @FXML
  public void homeClicked() {
    Navigation.navigate(Screen.HOME);
  }

  @FXML
  public void homeEntered() {
    closeAll();
  }

  @FXML
  public void peopleEntered() {
    if (peopleHovered()) showPeopleSM(true);
  }

  @FXML
  public void navEntered() {
    if (navHovered()) showNavSM(true);
  }

  @FXML
  public void srEntered() {
    if (srHovered()) showSRSM(true);
  }

  @FXML
  public void statEntered() {
    if (statHovered()) showStatSM(true);
  }

  @FXML
  public void setEntered() {
    if (setHovered()) showSetSM(true);
  }

  @FXML
  public void soEntered() {
    if (soHovered()) showSOSM(true);
  }

  @FXML
  public void peopleExited() {
    if (!peopleHovered()) showPeopleSM(false);
    else showPeopleSM(true);
  }

  @FXML
  public void navExited() {
    if (!navHovered()) showNavSM(false);
    else showNavSM(true);
  }

  @FXML
  public void srExited() {
    if (!srHovered()) {
      showSRSM(false);
    } else showSRSM(true);
  }

  @FXML
  public void statExited() {
    if (!statHovered()) {
      showStatSM(false);
    } else showStatSM(true);
  }

  @FXML
  public void setExited() {
    if (!setHovered()) {
      showSetSM(false);
    } else showSetSM(true);
  }

  @FXML
  public void soExited() {
    if (!soHovered()) {
      showSOSM(false);
    } else showSOSM(true);
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

  public void closeAll() {
    subPane.setVisible(false);
    peopleSMController.setVisible(false);
    navigationSMController.setVisible(false);
    servicerequestSMController.setVisible(false);
    statisticsSMController.setVisible(false);
    settingsSMController.setVisible(false);
    signoutSMController.setVisible(false);
  }

  @FXML
  public boolean peopleHovered() {
    return people.isHover() || peopleSMController.peopleSM.isHover() || mainPane.isHover();
  }

  @FXML
  public boolean navHovered() {
    return navigation.isHover()
        || navigationSMController.navigationSM.isHover()
        || mainPane.isHover();
  }

  @FXML
  public boolean srHovered() {
    return servicerequest.isHover()
        || servicerequestSMController.servicerequestSM.isHover()
        || mainPane.isHover();
  }

  @FXML
  public boolean statHovered() {
    return statistics.isHover()
        || statisticsSMController.statisticsSM.isHover()
        || mainPane.isHover();
  }

  @FXML
  public boolean setHovered() {
    return settings.isHover() || settingsSMController.settingsSM.isHover() || mainPane.isHover();
  }

  @FXML
  public boolean soHovered() {
    return signout.isHover() || signoutSMController.signoutSM.isHover() || mainPane.isHover();
  }
}

//    TranslateTransition tt = new TranslateTransition();
//    javafx.util.Duration duration = Duration.millis(200);
//    tt.setToX(432);
//    tt.setDuration(duration);
//    tt.setNode(subPane);
//    tt.play();
