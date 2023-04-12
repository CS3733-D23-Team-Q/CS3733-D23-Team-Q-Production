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
  @FXML ImageView homeIcon;
  @FXML ImageView peopleIcon;
  @FXML ImageView navigationIcon;
  @FXML ImageView srIcon;
  @FXML ImageView statisticsIcon;
  @FXML ImageView settingsIcon;
  @FXML ImageView exitIcon;
  Image homeBlue;
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
    homeIcon.setImage(new Image(App.class.getResourceAsStream("HomeBlue.png")));
    closeAll();
  }

  @FXML
  public void peopleEntered() {
    peopleIcon.setImage(new Image(App.class.getResourceAsStream("PeopleBlue.png")));
    if (peopleHovered()) showPeopleSM(true);
  }

  @FXML
  public void navEntered() {

    navigationIcon.setImage(new Image(App.class.getResourceAsStream("MapBlue.png")));
    if (navHovered()) showNavSM(true);
  }

  @FXML
  public void srEntered() {
    srIcon.setImage(new Image(App.class.getResourceAsStream("ServiceRequestsBlue.png")));
    if (srHovered()) showSRSM(true);
  }

  @FXML
  public void statEntered() {
    statisticsIcon.setImage(new Image(App.class.getResourceAsStream("StatisticsBlue.png")));
    if (statHovered()) showStatSM(true);
  }

  @FXML
  public void setEntered() {
    settingsIcon.setImage(new Image(App.class.getResourceAsStream("SettingsBlue.png")));
    if (setHovered()) showSetSM(true);
  }

  @FXML
  public void soEntered() {
    exitIcon.setImage(new Image(App.class.getResourceAsStream("ExitBlue.png")));
    if (soHovered()) showSOSM(true);
  }

  @FXML
  public void homeExited() {
    homeIcon.setImage(new Image(App.class.getResourceAsStream("Home.png")));
  }

  @FXML
  public void peopleExited() {
    peopleIcon.setImage(new Image(App.class.getResourceAsStream("people.png")));
    if (!peopleHovered()) showPeopleSM(false);
    else showPeopleSM(true);
  }

  @FXML
  public void navExited() {
    navigationIcon.setImage(new Image(App.class.getResourceAsStream("Map.png")));
    if (!navHovered()) showNavSM(false);
    else showNavSM(true);
  }

  @FXML
  public void srExited() {
    srIcon.setImage(new Image(App.class.getResourceAsStream("ServiceRequests.png")));
    if (!srHovered()) {
      showSRSM(false);
    } else showSRSM(true);
  }

  @FXML
  public void statExited() {
    statisticsIcon.setImage(new Image(App.class.getResourceAsStream("Statistics.png")));
    if (!statHovered()) {
      showStatSM(false);
    } else showStatSM(true);
  }

  @FXML
  public void setExited() {
    settingsIcon.setImage(new Image(App.class.getResourceAsStream("Settings.png")));
    if (!setHovered()) {
      showSetSM(false);
    } else showSetSM(true);
  }

  @FXML
  public void soExited() {
    exitIcon.setImage(new Image(App.class.getResourceAsStream("Exit.png")));
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
