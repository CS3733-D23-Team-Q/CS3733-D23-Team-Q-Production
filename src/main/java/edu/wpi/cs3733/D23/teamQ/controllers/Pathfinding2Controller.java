package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.App;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXCheckbox;
import io.github.palexdev.materialfx.controls.MFXFilterComboBox;
import javafx.animation.Interpolator;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import net.kurobako.gesturefx.GesturePane;

public class Pathfinding2Controller {
  @FXML GesturePane mapPane;
  @FXML Pane nodesPane;
  @FXML ImageView mapImage;

  @FXML MFXFilterComboBox startingField;
  @FXML MFXFilterComboBox destinationField;

  @FXML MFXCheckbox confCheck;
  @FXML MFXCheckbox deptCheck;
  @FXML MFXCheckbox infoCheck;
  @FXML MFXCheckbox labsCheck;
  @FXML MFXCheckbox restCheck;
  @FXML MFXCheckbox retlCheck;
  @FXML MFXCheckbox servCheck;
  @FXML MFXButton clearButton;

  ObservableList<String> floorChoices =
      FXCollections.observableArrayList(
          "Lower Level 1", "Lower Level 2", "First Floor", "Second Floor", "Third Floor");
  @FXML MFXFilterComboBox floorField;
  @FXML MFXFilterComboBox dateField;
  @FXML MFXFilterComboBox algorithmField;
  Image ll1 = new Image(App.class.getResourceAsStream("00_thelowerlevel1.png"));
  Image ll2 = new Image(App.class.getResourceAsStream("00_thelowerlevel2.png"));
  Image ff = new Image(App.class.getResourceAsStream("01_thefirstfloor.png"));
  Image sf = new Image(App.class.getResourceAsStream("02_thesecondfloor.png"));
  Image tf = new Image(App.class.getResourceAsStream("03_thethirdfloor.png"));

  @FXML
  public void initialize() {
    mapImage.setImage(ff);
    mapPane.zoomTo(0, 0, Point2D.ZERO);
    mapPane.setOnMouseClicked(
        e -> {
          if (e.getButton() == MouseButton.PRIMARY && e.getClickCount() == 2) {
            Point2D pivotOnTarget =
                mapPane
                    .targetPointAt(new Point2D(e.getX(), e.getY()))
                    .orElse(mapPane.targetPointAtViewportCentre());
            mapPane
                .animate(Duration.millis(200))
                .interpolateWith(Interpolator.EASE_BOTH)
                .zoomBy(mapPane.getCurrentScale(), pivotOnTarget);
          }
        });
    floorField.setValue(null);
    floorField.setItems(floorChoices);

    floorField
        .valueProperty()
        .addListener(
            new ChangeListener() {
              @Override
              public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                String floor = (String) newValue;
                if (floor.equals("Lower Level 1")) {
                  mapImage.setImage(ll1);
                } else if (floor.equals("Lower Level 2")) {
                  mapImage.setImage(ll2);
                } else if (floor.equals("First Floor")) {
                  mapImage.setImage(ff);
                } else if (floor.equals("Second Floor")) {
                  mapImage.setImage(sf);
                } else if (floor.equals("Third Floor")) {
                  mapImage.setImage(tf);
                }
              }
            });
  }

  @FXML
  public void confChecked() {}

  @FXML
  public void deptChecked() {}

  @FXML
  public void infoChecked() {}

  @FXML
  public void labsChecked() {}

  @FXML
  public void restChecked() {}

  @FXML
  public void retlChecked() {}

  @FXML
  public void servChecked() {}

  @FXML
  public void clearClicked() {}
}
