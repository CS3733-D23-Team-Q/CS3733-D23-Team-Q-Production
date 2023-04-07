package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.navigation.Navigation;
import edu.wpi.cs3733.D23.teamQ.navigation.Screen;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class HomeController {
  @FXML Button ServiceHubButton;
  @FXML Button ListRequestsButton;
  @FXML Button SPButton;
  @FXML Button LMButton;

  @FXML Button nextButton;

  @FXML MenuItem exit;

  @FXML MenuItem serviceRequestHubMenu;

  @FXML MenuItem listServiceRequestMenu;

  @FXML MenuItem signagePageMenu;

  @FXML MenuItem learnMoreMenu;
  @FXML MenuItem logout;
  @FXML Button settingButton;
  @FXML TextField searchField;

  @FXML
  public void initialize() {}

  /** Navigate to the conference room request page when the CRReservationButton is clicked. */
  @FXML
  public void ServiceHubButtonClicked() {
    Navigation.navigate(Screen.SERVICE_REQUEST_HUB);
  }

  /** Navigate to the flower delivery request page when the FDRequestButton is clicked. */
  @FXML
  public void ListRequestsButtonClicked() {
    Navigation.navigate(Screen.LIST_REQUESTS);
  }

  /** Navigate to the signage page when the SPButton is clicked. */
  @FXML
  public void SPButtonClicked() {

    Navigation.navigate(Screen.SIGNAGE);
  }

  /** Navigate to the help page when the LMButton is clicked. */
  @FXML
  public void LMButtonClicked() {
    Navigation.navigate(Screen.HELP);
    // System.out.println("Navigate to an information screen.");
  }

  /** Exit the application when the exitMenu is clicked. */
  @FXML
  public void exitMenuClicked() {
    Platform.exit();
  }
  /** Navigates to profile page * */
  @FXML
  public void Home_ProfileButton_Clicked() {
    Navigation.navigate(Screen.PROFILE_PAGE);
  }

  @FXML
  public void nextButtonClicked() {
    Navigation.navigate(Screen.HOME2);
  }

  /** Features might be added in the future. */
  @FXML
  public void settingButtonClicked() {
    System.out.println("Pops up a setting screen.");
  }

  @FXML
  public void searchFieldEntered(KeyEvent e) {
    if (e.getCode().equals(KeyCode.ENTER)) {
      System.out.println("Navigate to a specific page according to the text being entered.");
    }
  }

  @FXML
  public void logout() {
    Navigation.navigate(Screen.LOGIN);
  }
}
