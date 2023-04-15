package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.App;
import edu.wpi.cs3733.D23.teamQ.navigation.Navigation;
import edu.wpi.cs3733.D23.teamQ.navigation.Screen;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class MenuController {
  @FXML AnchorPane menuPane;
  @FXML MFXButton home;
  @FXML ImageView homeIcon;

  //    @FXML
  //    PeopleBlockController peopleBlockController;
  //    @FXML
  //    NavigationBlockController navigationBlockController;
  //    @FXML
  //    ServiceRequestBlockController srBlockController;
  //    @FXML
  //    StatisticsBlockController statBlockController;
  //    @FXML
  //    SettingsBlockController settingsBlockController;
  //    @FXML
  //    SignoutBlockController signoutBlockController;

  @FXML
  public void homeClicked() {
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
  public void setVisible(boolean v) {
    menuPane.setVisible(v);
  }
}
