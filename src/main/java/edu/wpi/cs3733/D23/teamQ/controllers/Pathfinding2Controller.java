package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.Alert;
import edu.wpi.cs3733.D23.teamQ.App;
import edu.wpi.cs3733.D23.teamQ.Pathfinding.*;
import edu.wpi.cs3733.D23.teamQ.db.Qdb;
import edu.wpi.cs3733.D23.teamQ.db.obj.Node;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXCheckbox;
import io.github.palexdev.materialfx.controls.MFXFilterComboBox;
import java.sql.Date;
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
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.util.Duration;
import javafx.util.Pair;
import net.kurobako.gesturefx.GesturePane;

public class Pathfinding2Controller {
  Qdb qdb = Qdb.getInstance();
  // Stage stage = App.getPrimaryStage();
  Context pathfindingAlgorithmSelection = new Context();
  AStar aStar = new AStar();
  BFS bfs = new BFS();
  DFS dfs = new DFS();
  Djikstra djikstra = new Djikstra();
  Alert alert = new Alert();
  GesturePane pane;
  boolean ready4Second;
  Node start;
  Node target;
  List<Line> previousPath;
  Text text;
  List<Image> floors;
  int floor;
  List<Button> previousNodes;
  List<Integer> restNodes;
  List<Integer> deptNodes;
  List<Integer> labsNodes;
  List<Integer> infoNodes;
  List<Integer> confNodes;
  List<Integer> retlNodes;
  List<Integer> servNodes;
  List<Integer> nodeIds;
  List<Pair<Integer, Integer>> highlightedNodes;
  // List<Integer> highlightedNodes;
  List<Pair<Integer, Integer>> l1nodes;
  List<Pair<Integer, Integer>> l2nodes;
  List<Pair<Integer, Integer>> ffnodes;
  List<Pair<Integer, Integer>> sfnodes;
  List<Pair<Integer, Integer>> tfnodes;
  List<String> allSelections;
  String algorithm;
  Date date;
  List<Date> moveDates;
  ToggleGroup dateToggle;
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
  ObservableList<String> algorithmChoices =
      FXCollections.observableArrayList("A*", "BFS", "DFS", "Dijkstra's");
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
    algorithm = "A*";
    pathfindingAlgorithmSelection.setPathfindingAlgorithm(aStar);
    algorithmField.setValue(null);
    algorithmField.setItems(algorithmChoices);
    dateToggle = new ToggleGroup();
    date = Date.valueOf("2023-01-01");
    moveDates = new ArrayList<>();
    algorithm = "aStar";
    floors = new ArrayList<>();
    floors.add(ll1); // 0
    floors.add(ll2); // 1
    floors.add(ff); // 2
    floors.add(sf); // 3
    floors.add(tf); // 4
    floor = 2;
    ready4Second = false;
    previousPath = new ArrayList<>();
    addButtons("1");

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
    algorithmField
        .valueProperty()
        .addListener(
            new ChangeListener() {
              @Override
              public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                algorithm = (String) newValue;
                if (algorithm.equals("A*")) {
                  pathfindingAlgorithmSelection.setPathfindingAlgorithm(aStar);
                } else if (algorithm.equals("BFS")) {
                  pathfindingAlgorithmSelection.setPathfindingAlgorithm(bfs);
                } else if (algorithm.equals("DFS")) {
                  pathfindingAlgorithmSelection.setPathfindingAlgorithm(dfs);
                } else if (algorithm.equals("Dijkstra's")) {
                  pathfindingAlgorithmSelection.setPathfindingAlgorithm(djikstra);
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
