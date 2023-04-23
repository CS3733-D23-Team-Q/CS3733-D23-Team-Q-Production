package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.App;
import edu.wpi.cs3733.D23.teamQ.Pathfinding.*;
import edu.wpi.cs3733.D23.teamQ.db.Qdb;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXFilterComboBox;
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
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import net.kurobako.gesturefx.GesturePane;

public class PathfindingController2 {
  Qdb qdb = Qdb.getInstance();
  Context pathfindingAlgorithmSelection = new Context();
  BFS bfs = new BFS();
  AStar aStar = new AStar();
  DFS dfs = new DFS();
  Djikstra djikstra = new Djikstra();
  @FXML GesturePane mapPane;
  @FXML ImageView mapImage;
  @FXML Pane nodesPane;
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
  Image i1 = new Image(App.class.getResourceAsStream("00_thelowerlevel1.png"));
  Image i2 = new Image(App.class.getResourceAsStream("00_thelowerlevel2.png"));
  Image i3 = new Image(App.class.getResourceAsStream("01_thefirstfloor.png"));
  Image i4 = new Image(App.class.getResourceAsStream("02_thesecondfloor.png"));
  Image i5 = new Image(App.class.getResourceAsStream("03_thethirdfloor.png"));

  @FXML
  public void initialize() {
    mapImage.setImage(i3);
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
                  mapImage.setImage(i1);
                } else if (floor.equals("Lower Level 2")) {
                  mapImage.setImage(i2);
                } else if (floor.equals("First Floor")) {
                  mapImage.setImage(i3);
                } else if (floor.equals("Second Floor")) {
                  mapImage.setImage(i4);
                } else if (floor.equals("Third Floor")) {
                  mapImage.setImage(i5);
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
