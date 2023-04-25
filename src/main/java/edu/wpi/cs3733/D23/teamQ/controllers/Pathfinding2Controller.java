package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.App;
import edu.wpi.cs3733.D23.teamQ.Pathfinding.*;
import edu.wpi.cs3733.D23.teamQ.db.Qdb;
import edu.wpi.cs3733.D23.teamQ.db.obj.Node;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXCheckbox;
import io.github.palexdev.materialfx.controls.MFXFilterComboBox;
import java.awt.*;
import java.util.ArrayList;
import javafx.animation.Interpolator;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.util.Duration;
import net.kurobako.gesturefx.GesturePane;

public class Pathfinding2Controller {
  Qdb qdb = Qdb.getInstance();
  // Stage stage = App.getPrimaryStage();
  Context pathfindingAlgorithmSelection = new Context();
  AStar aStar = new AStar();
  BFS bfs = new BFS();
  DFS dfs = new DFS();
  Djikstra djikstra = new Djikstra();
  Node startNode;
  Node destinationNode;
  String currentFloor;
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
    nodesPane.setMinWidth(ll1.getWidth());
    nodesPane.setMinHeight(ll1.getHeight());
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
    currentFloor = "1";
    Circle c = new Circle();
    c.setOnMouseClicked((event) -> {});

    pathfindingAlgorithmSelection.setPathfindingAlgorithm(aStar);
    algorithmField.setValue(null);
    algorithmField.setItems(algorithmChoices);
    startingField.setItems(qdb.getAllLongNames());
    destinationField.setItems(qdb.getAllLongNames());

    startingField
        .valueProperty()
        .addListener(
            new ChangeListener() {
              @Override
              public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                String lName = (String) newValue;
                Node n = qdb.getNodeFromLocation(lName);
                floorSwap(n.getFloor());
              }
            });
    destinationField
        .valueProperty()
        .addListener(
            new ChangeListener() {
              @Override
              public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                String lName = (String) newValue;
                Node n = qdb.getNodeFromLocation(lName);
                floorSwap(n.getFloor());
              }
            });
    floorField
        .valueProperty()
        .addListener(
            new ChangeListener() {
              @Override
              public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                String floor = (String) newValue;
                if (floor.equals("Lower Level 1")) {
                  floorSwap("L1");
                } else if (floor.equals("Lower Level 2")) {
                  floorSwap("L2");
                } else if (floor.equals("First Floor")) {
                  floorSwap("1");
                } else if (floor.equals("Second Floor")) {
                  floorSwap("2");
                } else if (floor.equals("Third Floor")) {
                  floorSwap("3");
                }
              }
            });
    algorithmField
        .valueProperty()
        .addListener(
            new ChangeListener() {
              @Override
              public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                String algorithm = (String) newValue;
                if (algorithm.equals("A*")) {
                  clearClicked();
                  pathfindingAlgorithmSelection.setPathfindingAlgorithm(aStar);
                } else if (algorithm.equals("BFS")) {
                  clearClicked();
                  pathfindingAlgorithmSelection.setPathfindingAlgorithm(bfs);
                } else if (algorithm.equals("DFS")) {
                  clearClicked();
                  pathfindingAlgorithmSelection.setPathfindingAlgorithm(dfs);
                } else if (algorithm.equals("Dijkstra's")) {
                  clearClicked();
                  pathfindingAlgorithmSelection.setPathfindingAlgorithm(djikstra);
                }
              }
            });
    addCircles("1");
  }

  @FXML
  public void addCircles(String curFloor) {
    nodesPane.getChildren().clear();
    ArrayList<Node> nodes = qdb.retrieveAllNodes();
    for (int i = 0; i < nodes.size(); i++) {
      Node n = nodes.get(i);
      if (n.getFloor().equals(curFloor)) {
        Circle circle = new Circle();
        nodesPane.getChildren().add(circle);
        circle.setCenterX(n.getXCoord());
        circle.setCenterY(n.getYCoord());
        circle.setRadius(12);
        circle.setStyle("-fx-fill: #012d5a");
        Label label = new Label();
        nodesPane.getChildren().add(label);
        label.setText(n.getLocation().getLongName());
        label.setLayoutX(n.getXCoord() - 50);
        label.setLayoutY(n.getYCoord() - 50);
        label.setStyle("-fx-text-fill: #012d5a; -fx-font-size: 32; -fx-font-weight: bold");
        label.setVisible(false);
        circle.setOnMouseEntered(
            (event) -> {
              label.setVisible(true);
              circle.setStyle("-fx-fill: #0167B1");
            });
        circle.setOnMouseExited(
            (event) -> {
              label.setVisible(false);
              circle.setStyle("-fx-fill: #012d5a");
            });
        circle.setOnMouseClicked(
            (event) -> {
              circle.setRadius(20);
              floorSwap(n.getFloor());
            });
      }
    }
  }

  @FXML
  public void drawLine() {
    Line line = new Line();
    nodesPane.getChildren().add(line);
    line.setStartX(startNode.getXCoord());
    line.setStartY(startNode.getYCoord());
    line.setEndX(destinationNode.getXCoord());
    line.setEndY(destinationNode.getYCoord());
    line.setStyle("-fx-fill: #0167B1");
    line.setStrokeWidth(8);
  }

  public void nodeSelected() {}

  @FXML
  public void floorSwap(String newFloor) {
    if (!currentFloor.equals(newFloor)) {
      if (newFloor.equals("L1")) {
        mapImage.setImage(ll1);
        currentFloor = "L1";
        addCircles("L1");
      } else if (newFloor.equals("L2")) {
        mapImage.setImage(ll2);
        currentFloor = "L2";
        addCircles("L2");
      } else if (newFloor.equals("1")) {
        mapImage.setImage(ff);
        currentFloor = "1";
        addCircles("1");
      } else if (newFloor.equals("2")) {
        mapImage.setImage(sf);
        currentFloor = "2";
        addCircles("2");
      } else if (newFloor.equals("3")) {
        mapImage.setImage(tf);
        currentFloor = "3";
        addCircles("3");
      }
    }
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
  public void clearClicked() {}

  public void retlChecked() {}

  public void servChecked() {}
}
