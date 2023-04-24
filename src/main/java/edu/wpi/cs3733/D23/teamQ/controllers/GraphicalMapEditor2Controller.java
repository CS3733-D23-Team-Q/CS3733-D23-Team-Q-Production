package edu.wpi.cs3733.D23.teamQ.controllers;

import static edu.wpi.cs3733.D23.teamQ.SecondaryStage.newStage;

import edu.wpi.cs3733.D23.teamQ.App;
import edu.wpi.cs3733.D23.teamQ.navigation.Navigation;
import edu.wpi.cs3733.D23.teamQ.navigation.Screen;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXFilterComboBox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import java.io.IOException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import net.kurobako.gesturefx.GesturePane;

public class GraphicalMapEditor2Controller {
  @FXML GesturePane mapPane;
  @FXML Pane nodesPane;
  @FXML ImageView mapImage;

  @FXML MFXTextField nodeidField;
  @FXML MFXTextField xcoordField;
  @FXML MFXTextField ycoordField;
  @FXML MFXFilterComboBox floorField;
  @FXML MFXFilterComboBox buildingField;
  @FXML MFXTextField longnameField;
  @FXML MFXTextField shortnameField;
  @FXML MFXTextField nodetypeField;

  @FXML MFXButton find;
  @FXML MFXButton delete;
  @FXML MFXButton set;
  @FXML MFXButton add;
  @FXML MFXButton clear;

  @FXML MFXTextField startnodeField;
  @FXML MFXTextField endnodeField;

  @FXML MFXButton addEdges;
  @FXML MFXButton displayEdges;
  @FXML MFXButton hideEdges;

  @FXML MFXFilterComboBox dln;
  @FXML MFXFilterComboBox ot;

  ObservableList<String> floorChoices =
      FXCollections.observableArrayList(
          "Lower Level 1", "Lower Level 2", "First Floor", "Second Floor", "Third Floor");
  @FXML MFXFilterComboBox changeFloor;

  @FXML MFXButton help;

  Image ll1 = new Image(App.class.getResourceAsStream("00_thelowerlevel1.png"));
  Image ll2 = new Image(App.class.getResourceAsStream("00_thelowerlevel2.png"));
  Image ff = new Image(App.class.getResourceAsStream("01_thefirstfloor.png"));
  Image sf = new Image(App.class.getResourceAsStream("02_thesecondfloor.png"));
  Image tf = new Image(App.class.getResourceAsStream("03_thethirdfloor.png"));

  @FXML
  public void initialize() {
    mapImage.setImage(ff);
    mapPane.zoomTo(0, 0, Point2D.ZERO);

    changeFloor.setValue(null);
    changeFloor.setItems(floorChoices);

    changeFloor
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
  public void helpClicked() throws IOException {
    MapEditorHelpController controller =
        (MapEditorHelpController) Navigation.getController(Screen.MAPEDITORHELP);
    Stage stage = newStage("Help", Screen.MAPEDITORHELP);
    controller.setStage(stage);
    stage.show();
    stage.centerOnScreen();
  }
}
