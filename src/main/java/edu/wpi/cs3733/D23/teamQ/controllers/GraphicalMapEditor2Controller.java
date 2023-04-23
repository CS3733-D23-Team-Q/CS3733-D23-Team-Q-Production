package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.App;
import io.github.palexdev.materialfx.controls.MFXFilterComboBox;
import io.github.palexdev.materialfx.controls.MFXToggleButton;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import net.kurobako.gesturefx.GesturePane;

public class GraphicalMapEditor2Controller {
  @FXML GesturePane mapPane;
  ObservableList<String> floors =
      FXCollections.observableArrayList(
          "Ground Floor",
          "Lower Level 1",
          "Lower Level 2",
          "First Floor",
          "Second Floor",
          "Third Floor");
  @FXML MFXFilterComboBox mapFloorField;
  @FXML MFXToggleButton edgeToggle;
  Image i1 = new Image(App.class.getResourceAsStream("00_thegroundfloor.png"));
  ImageView I1 = new ImageView(i1);
  Image i2 = new Image(App.class.getResourceAsStream("00_thelowerlevel1.png"));
  ImageView I2 = new ImageView(i2);
  Image i3 = new Image(App.class.getResourceAsStream("00_thelowerlevel2.png"));
  ImageView I3 = new ImageView(i3);
  Image i4 = new Image(App.class.getResourceAsStream("01_thefirstfloor.png"));
  ImageView I4 = new ImageView(i4);
  Image i5 = new Image(App.class.getResourceAsStream("02_thesecondfloor.png"));
  ImageView I5 = new ImageView(i5);
  Image i6 = new Image(App.class.getResourceAsStream("03_thethirdfloor.png"));
  ImageView I6 = new ImageView(i6);

  @FXML
  public void initialize() {
    mapPane.setContent(I1);
    mapPane.zoomBy(-500, -500, new Point2D(0, 0));
    mapFloorField.setValue("First Floor");
    mapFloorField.setItems(floors);
    Color main = Color.web("0x0167B1", 1.0);
    Color secondary = Color.web("0xF1F1F1", 1.0);
    edgeToggle.setColors(main, secondary);

    mapFloorField
        .valueProperty()
        .addListener(
            new ChangeListener<>() {
              @Override
              public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                String floor = (String) mapFloorField.getValue();
                if (floor.equals("Ground Floor")) {
                  mapPane.setContent(I1);
                } else if (floor.equals("Lower Level 1")) {
                  mapPane.setContent(I2);
                } else if (floor.equals("Lower Level 2")) {
                  mapPane.setContent(I3);
                } else if (floor.equals("First Floor")) {
                  mapPane.setContent(I4);
                } else if (floor.equals("Second Floor")) {
                  Image hImage = new Image(App.class.getResourceAsStream("02_thesecondfloor.png"));
                  ImageView imageView = new ImageView(hImage);
                  mapPane.setContent(I5);
                } else if (floor.equals("Third Floor")) {
                  mapPane.setContent(I6);
                }
              }
            });
  }
}