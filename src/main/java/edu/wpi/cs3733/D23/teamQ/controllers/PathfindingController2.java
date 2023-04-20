package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.App;
import edu.wpi.cs3733.D23.teamQ.Pathfinding.*;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXFilterComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import net.kurobako.gesturefx.GesturePane;

public class PathfindingController2 {
  @FXML GesturePane mapPane;
  @FXML MFXFilterComboBox startingField;
  @FXML MFXFilterComboBox destinationField;
  @FXML MFXFilterComboBox highlightField;
  @FXML MFXButton clearButton;
  ObservableList<String> floors =
      FXCollections.observableArrayList(
          "Ground Floor",
          "Lower Level 1",
          "Lower Level 2",
          "First Floor",
          "Second Floor",
          "Third Floor");
  @FXML MFXFilterComboBox floorField;
  @FXML MFXFilterComboBox dateField;
  @FXML MFXFilterComboBox algorithmField;

  @FXML
  public void initialize() {
    Image hImage = new Image(App.class.getResourceAsStream("00_thegroundfloor.png"));
    ImageView imageView = new ImageView(hImage);
    mapPane.setContent(imageView);
    floorField.setValue("Ground Floor");
    floorField.setItems(floors);
  }
}
