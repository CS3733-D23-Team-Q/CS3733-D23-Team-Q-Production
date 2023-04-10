package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.navigation.Navigation;
import edu.wpi.cs3733.D23.teamQ.navigation.Screen;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;

public class MapEditorController {

  @FXML private Button Edge;

  @FXML private Button LocationName;

  @FXML private Button Map;

  @FXML private Button Move;

  @FXML private Button Node;

  @FXML private MenuItem exitItem;

  @FXML private Button homeButton;

  @FXML private MenuItem homeItem;

  @FXML
  void EdgeClicked(MouseEvent event) {
    Navigation.navigate(Screen.Edge_Table);
  }

  @FXML
  void LocationNameClicked(MouseEvent event) {
    Navigation.navigate(Screen.LocationName_Table);
  }

  @FXML
  void MapClicked(MouseEvent event) {
    Navigation.navigate(Screen.Map);
  }

  @FXML
  void MoveClicked(MouseEvent event) {
    Navigation.navigate(Screen.Move_Table);
  }

  @FXML
  void NodeClicked(MouseEvent event) {
    Navigation.navigate(Screen.Node_Table);
  }

  @FXML
  void exitItemClicked(ActionEvent event) {
    Platform.exit();
  }

  @FXML
  void homeButtonClicked(ActionEvent event) {
    Navigation.navigate(Screen.HOME);
  }

  @FXML
  void homeItemClicked(ActionEvent event) {
    Navigation.navigate(Screen.HOME);
  }
}
