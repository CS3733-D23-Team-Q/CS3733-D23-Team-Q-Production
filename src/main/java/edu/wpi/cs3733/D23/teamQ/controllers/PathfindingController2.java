package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.App;
import edu.wpi.cs3733.D23.teamQ.Pathfinding.*;
import edu.wpi.cs3733.D23.teamQ.db.Qdb;
import edu.wpi.cs3733.D23.teamQ.db.obj.Node;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXFilterComboBox;
import java.sql.Date;
import java.util.List;
import javafx.animation.Interpolator;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.shape.Line;
import javafx.util.Duration;
import javafx.util.Pair;
import net.kurobako.gesturefx.GesturePane;

public class PathfindingController2 {
  Qdb qdb = Qdb.getInstance();
  Context pathfindingAlgorithmSelection = new Context();
  BFS bfs = new BFS();
  AStar aStar = new AStar();
  DFS dfs = new DFS();
  Djikstra djikstra = new Djikstra();
  boolean ready4Second;
  Node start;
  Node target;
  List<Line> previousPath;
  // List<Image> floors;
  int curFloor;
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
  @FXML MFXFilterComboBox startingField;
  @FXML MFXFilterComboBox destinationField;
  ObservableList<String> nodeTypes =
      FXCollections.observableArrayList(
          "None",
          "Conference Room",
          "Department",
          "Info Desk",
          "Laboratory",
          "Restroom",
          "Retail",
          "Service");
  @FXML MFXFilterComboBox highlightField;
  @FXML MFXButton clearButton;
  ObservableList<String> floors =
      FXCollections.observableArrayList(
          "Lower Level 1", "Lower Level 2", "First Floor", "Second Floor", "Third Floor");
  @FXML MFXFilterComboBox floorField;
  @FXML MFXFilterComboBox dateField;
  ObservableList<String> algorithmList =
      FXCollections.observableArrayList("A*", "Dijkstra's", "DFS", "BFS");
  @FXML MFXFilterComboBox algorithmField;
  ImageView I0 = new ImageView(new Image(App.class.getResourceAsStream("00_thegroundfloor.png")));
  ImageView I1 = new ImageView(new Image(App.class.getResourceAsStream("00_thelowerlevel1.png")));
  ImageView I2 = new ImageView(new Image(App.class.getResourceAsStream("00_thelowerlevel2.png")));
  ImageView I3 = new ImageView(new Image(App.class.getResourceAsStream("01_thefirstfloor.png")));
  ImageView I4 = new ImageView(new Image(App.class.getResourceAsStream("02_thesecondfloor.png")));
  ImageView I5 = new ImageView(new Image(App.class.getResourceAsStream("03_thethirdfloor.png")));

  @FXML
  public void initialize() {
    mapPane.setContent(I3);
    mapPane.zoomBy(-500, -500, new Point2D(0, 0));
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
    curFloor = 3;
    floorField.setValue("First Floor");
    floorField.setItems(floors);
    algorithmField.setValue("A*");
    algorithmField.setItems(algorithmList);
    highlightField.setValue("None");
    highlightField.setItems(nodeTypes);
    pathfindingAlgorithmSelection.setPathfindingAlgorithm(aStar);

    floorField
        .valueProperty()
        .addListener(
            new ChangeListener<>() {
              @Override
              public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                String floor = (String) floorField.getValue();
                if (floor.equals("Lower Level 1")) {
                  mapPane.setContent(I1);
                } else if (floor.equals("Lower Level 2")) {
                  mapPane.setContent(I2);
                } else if (floor.equals("First Floor")) {
                  mapPane.setContent(I3);
                } else if (floor.equals("Second Floor")) {
                  mapPane.setContent(I4);
                } else if (floor.equals("Third Floor")) {
                  mapPane.setContent(I5);
                }
              }
            });

    algorithmField
        .valueProperty()
        .addListener(
            new ChangeListener<>() {
              @Override
              public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                String algorithm = (String) algorithmField.getValue();
                if (algorithm.equals("A*")) {
                  pathfindingAlgorithmSelection.setPathfindingAlgorithm(aStar);
                } else if (algorithm.equals("Dijkstra's")) {
                  pathfindingAlgorithmSelection.setPathfindingAlgorithm(djikstra);
                } else if (algorithm.equals("DFS")) {
                  pathfindingAlgorithmSelection.setPathfindingAlgorithm(dfs);
                } else if (algorithm.equals("BFS")) {
                  pathfindingAlgorithmSelection.setPathfindingAlgorithm(bfs);
                }
              }
            });

    highlightField
        .valueProperty()
        .addListener(
            new ChangeListener() {
              @Override
              public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                String nodeType = (String) highlightField.getValue();
                if (nodeType.equals("None")) {
                  System.out.println(nodeType);
                } else if (nodeType.equals("Restroom")) {
                  System.out.println(nodeType);
                } else if (nodeType.equals("Department")) {
                  System.out.println(nodeType);
                } else if (nodeType.equals("Laboratory")) {
                  System.out.println(nodeType);
                } else if (nodeType.equals("Info Desk")) {
                  System.out.println(nodeType);
                } else if (nodeType.equals("Conference Room")) {
                  System.out.println(nodeType);
                } else if (nodeType.equals("Retail")) {
                  System.out.println(nodeType);
                } else if (nodeType.equals("Service")) {
                  System.out.println(nodeType);
                }
              }
            });
  }

  @FXML
  public void clearClicked() {
    highlightField.setValue("None");
  }
}
