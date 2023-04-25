package edu.wpi.cs3733.D23.teamQ.controllers;

import static edu.wpi.cs3733.D23.teamQ.SecondaryStage.newStage;

import edu.wpi.cs3733.D23.teamQ.Alert;
import edu.wpi.cs3733.D23.teamQ.App;
import edu.wpi.cs3733.D23.teamQ.db.Qdb;
import edu.wpi.cs3733.D23.teamQ.db.obj.Node;
import edu.wpi.cs3733.D23.teamQ.navigation.Navigation;
import edu.wpi.cs3733.D23.teamQ.navigation.Screen;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXFilterComboBox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.animation.Interpolator;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import net.kurobako.gesturefx.GesturePane;

public class GraphicalMapEditor2Controller {
  Node empty = new Node(-1, 0, 0, "empty", "empty", null);
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

  ObservableList<String> nodeTypes =
      FXCollections.observableArrayList(
          "None",
          "All",
          "Conference Room",
          "Department",
          "Elevator",
          "Exit",
          "Information",
          "Laboratory",
          "Restroom",
          "Retail",
          "Stair",
          "Bathroom",
          "Service");
  @FXML MFXFilterComboBox dln;

  ObservableList<String> tableTypes =
      FXCollections.observableArrayList(
          "Node Table", "Location Name Table", "Move Table", "Edge Table");
  @FXML MFXFilterComboBox ot;

  ObservableList<String> floorChoices =
      FXCollections.observableArrayList(
          "Lower Level 1", "Lower Level 2", "First Floor", "Second Floor", "Third Floor");
  @FXML MFXFilterComboBox changeFloor;

  @FXML MFXButton help;

  private boolean startnodeOr = false;
  private boolean endnodeOr = false;
  private Node startnode = empty;
  private Node endnode = empty;
  private List<Text> Texts = new ArrayList<>();
  List<Integer> NodeID = new ArrayList<>();
  private double posx = 0;
  private double posy = 0;
  private List<Line> line = new ArrayList<>();
  private boolean displayingEdges = false;
  @FXML private Label WhichFloor;
  List<Button> button = new ArrayList<>();
  int currentIndex = 0;
  Qdb qdb = Qdb.getInstance();
  Alert alert = new Alert();
  Text text;
  double mouseX;
  double mouseY;
  private String newBuilding;
  private String newFloor;
  private int newXcoord;
  private int newYcoord;
  private String newLongName;
  private String newShortName;
  private String newNodeType;
  private int nodeid;

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
          if (e.getClickCount() == 2) {
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
    changeFloor.setItems(floorChoices);
    dln.setItems(nodeTypes);
    ot.setItems(tableTypes);

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
    //    dln.valueProperty()
    //        .addListener(
    //            new ChangeListener() {
    //              @Override
    //              public void changed(ObservableValue observable, Object oldValue, Object
    // newValue) {
    //                String nodeType = (String) newValue;
    //                if (nodeType.equals("None")) {
    //                  // show location names for none
    //                } else if (nodeType.equals("All")) {
    //                  // show location names for all
    //                } else if (nodeType.equals("Coference Room")) {
    //                  // show location names for conference rooms
    //                } else if (nodeType.equals("Department")) {
    //                  // show location names for department
    //                } else if (nodeType.equals("Elevator")) {
    //                  // show location names for elevator
    //                } else if (nodeType.equals("Exit")) {
    //                  // show location names for exit
    //                } else if (nodeType.equals("Information")) {
    //                  // show location names for information
    //                } else if (nodeType.equals("Laboratory")) {
    //                  // show location names for labs
    //                } else if (nodeType.equals("Restroom")) {
    //                  // show location names for restroom
    //                } else if (nodeType.equals("Retail")) {
    //                  // show location names for retail
    //                } else if (nodeType.equals("Stair")) {
    //                  // show location names for stair
    //                } else if (nodeType.equals("Bathroom")) {
    //                  // show location names for bathroom
    //                } else if (nodeType.equals("Service")) {
    //                  // show location names for service
    //                }
    //              }
    //            });
    //    ot.valueProperty()
    //        .addListener(
    //            new ChangeListener() {
    //              @Override
    //              public void changed(ObservableValue observable, Object oldValue, Object
    // newValue) {
    //                String tableType = (String) newValue;
    //                if (tableType.equals("Nodes")) {
    //                  // nodes list
    //                } else if (tableType.equals("Location Names")) {
    //                  // location table displayed
    //                } else if (tableType.equals("Moves")) {
    //                  // moves table displayed
    //                } else if (tableType.equals("Edges")) {
    //                  // edge table displayed
    //                }
    //              }
    //            });
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
