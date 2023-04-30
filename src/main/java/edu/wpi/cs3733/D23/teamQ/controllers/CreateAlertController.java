package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.db.Qdb;
import edu.wpi.cs3733.D23.teamQ.db.obj.Alert;
import edu.wpi.cs3733.D23.teamQ.navigation.Navigation;
import edu.wpi.cs3733.D23.teamQ.navigation.Screen;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class CreateAlertController {
  Qdb qdb = Qdb.getInstance();
  @FXML MFXButton resetButton;
  @FXML MFXButton cancelButton;
  @FXML MFXButton submitButton;
  @FXML TextArea input;
  @FXML MFXButton blue;
  @FXML MFXButton red;
  @FXML MFXButton black;
  @FXML MFXButton gray;
  @FXML MFXButton yellow;
  @FXML MFXButton orange;
  @FXML MFXButton pink;
  @FXML MFXButton purple;
  @FXML MFXButton green;
  @FXML MFXButton silver;

  @FXML
  public void initialize() {}

  @FXML
  public void blueClicked() {
    input.setText("Code Blue: Cardiac/Respiratory arrest in room ___.");
  }

  @FXML
  public void redClicked() {
    input.setText(
        "Code Red: Fire Emergency on floor ___. Please evacuate all patients immediately.");
  }

  @FXML
  public void blackClicked() {
    input.setText(
        "Code Black: Bomb Threat/Active Shooter on floor ___. Please evacuate all patients immediately.");
  }

  @FXML
  public void grayClicked() {
    input.setText(
        "Code Gray: Severe weather event warning. Please move away from all windows and external doors, and make your way to lower level 1.");
  }

  @FXML
  public void yellowClicked() {
    input.setText("Code Yellow: Missing patient alert. Last seen in room ___.");
  }

  @FXML
  public void orangeClicked() {
    input.setText("Code Orange: Hazardous material release in room ___. Please avoid the area.");
  }

  @FXML
  public void pinkClicked() {
    input.setText("Code Pink: Pediatric emergency in room ___.");
  }

  @FXML
  public void purpleClicked() {
    input.setText(
        "Code Purple: Violent patient/visitor in room ___. Please evacuate the area immediately.");
  }

  @FXML
  public void greenClicked() {
    input.setText("Code Green: External mass casualty event. All staff on high alert.");
  }

  @FXML
  public void silverClicked() {
    input.setText(
        "Code Silver: Security threat on floor ___. Please evacuate the area immediately.");
  }

  @FXML
  public void resetButtonClicked() {
    input.clear();
  }

  @FXML
  public void cancelButtonClicked() {
    Navigation.navigate(Screen.HOME);
  }

  @FXML
  public void submitButtonClicked() {
    Alert a = new Alert(System.currentTimeMillis(), input.getText());
    qdb.addAlert(a);
    Navigation.navigate(Screen.HOME);
  }
}
