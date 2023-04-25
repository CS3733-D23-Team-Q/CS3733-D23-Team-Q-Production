package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.App;
import edu.wpi.cs3733.D23.teamQ.Pathfinding.*;
import edu.wpi.cs3733.D23.teamQ.db.Qdb;
import edu.wpi.cs3733.D23.teamQ.db.obj.Node;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXCheckbox;
import io.github.palexdev.materialfx.controls.MFXFilterComboBox;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
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
  boolean confHighlighted;
  HashMap<Integer, Circle> confMap = new HashMap<>();
  @FXML MFXCheckbox deptCheck;
  boolean deptHighlighted;
  HashMap<Integer, Circle> deptMap = new HashMap<>();
  @FXML MFXCheckbox infoCheck;
  boolean infoHighlighted;
  HashMap<Integer, Circle> infoMap = new HashMap<>();
  @FXML MFXCheckbox labsCheck;
  boolean labsHighlighted;
  HashMap<Integer, Circle> labsMap = new HashMap<>();
  @FXML MFXCheckbox restCheck;
  boolean restHighlighted;
  HashMap<Integer, Circle> restMap = new HashMap<>();
  @FXML MFXCheckbox retlCheck;
  boolean retlHighlighted;
  HashMap<Integer, Circle> retlMap = new HashMap<>();
  @FXML MFXCheckbox servCheck;
  boolean servHighlighted;
  HashMap<Integer, Circle> servMap = new HashMap<>();
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

    confHighlighted = false;
    deptHighlighted = false;
    infoHighlighted = false;
    labsHighlighted = false;
    restHighlighted = false;
    retlHighlighted = false;
    servHighlighted = false;

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
    addCircles();
  }

  @FXML
  public void addCircles() {
    nodesPane.getChildren().clear();
    ArrayList<Node> nodes = qdb.retrieveAllNodes();
    for (int i = 0; i < nodes.size(); i++) {
      Node n = nodes.get(i);
      if (n.getFloor().equals(currentFloor)) {
        Circle circle = new Circle();
        nodesPane.getChildren().add(circle);
        circle.setCenterX(n.getXCoord());
        circle.setCenterY(n.getYCoord());
        circle.setRadius(12);
        circle.setStyle("-fx-fill: #012d5a");
        //        Label label = new Label();
        //        nodesPane.getChildren().add(label);
        //        label.setText(n.getLocation().getLongName());
        //        label.setLayoutX(n.getXCoord() - 50);
        //        label.setLayoutY(n.getYCoord() - 50);
        //        label.setStyle("-fx-text-fill: #012d5a; -fx-font-size: 32; -fx-font-weight:
        // bold");
        //        label.setVisible(false);
        circle.setOnMouseClicked((event) -> {});
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
        addCircles();
      } else if (newFloor.equals("L2")) {
        mapImage.setImage(ll2);
        currentFloor = "L2";
        addCircles();
      } else if (newFloor.equals("1")) {
        mapImage.setImage(ff);
        currentFloor = "1";
        addCircles();
      } else if (newFloor.equals("2")) {
        mapImage.setImage(sf);
        currentFloor = "2";
        addCircles();
      } else if (newFloor.equals("3")) {
        mapImage.setImage(tf);
        currentFloor = "3";
        addCircles();
      }
      if (confHighlighted) {
        highlightConf();
      }
      if (deptHighlighted) {
        highlightDept();
      }
      if (infoHighlighted) {
        highlightInfo();
      }
      if (labsHighlighted) {
        highlightLabs();
      }
      if (restHighlighted) {
        highlightRest();
      }
      if (retlHighlighted) {
        highlightRetl();
      }
      if (servHighlighted) {
        highlightServ();
      }
    }
  }

  @FXML
  public void highlightConf() {
    ArrayList<Node> nodes = qdb.retrieveAllNodes();
    for (int i = 0; i < nodes.size(); i++) {
      Node n = nodes.get(i);
      if (n.getFloor().equals(currentFloor) && n.getLocation().getNodeType().equals("CONF")) {
        Circle circle = new Circle();
        nodesPane.getChildren().add(circle);
        circle.setCenterX(n.getXCoord());
        circle.setCenterY(n.getYCoord());
        circle.setRadius(16);
        circle.setStyle("-fx-fill: #5A015A");
        confMap.put(i, circle);
      }
    }
    confHighlighted = true;
  }

  @FXML
  public void unhighlightConf() {
    confCheck.setSelected(false);
    for (Map.Entry<Integer, Circle> entry : confMap.entrySet()) {
      Circle c = entry.getValue();
      nodesPane.getChildren().remove(c);
    }
    confHighlighted = false;
  }

  @FXML
  public void highlightDept() {
    ArrayList<Node> nodes = qdb.retrieveAllNodes();
    for (int i = 0; i < nodes.size(); i++) {
      Node n = nodes.get(i);
      if (n.getFloor().equals(currentFloor) && n.getLocation().getNodeType().equals("DEPT")) {
        Circle circle = new Circle();
        nodesPane.getChildren().add(circle);
        circle.setCenterX(n.getXCoord());
        circle.setCenterY(n.getYCoord());
        circle.setRadius(16);
        circle.setStyle("-fx-fill: #5A015A");
        deptMap.put(i, circle);
      }
    }
    deptHighlighted = true;
  }

  @FXML
  public void unhighlightDept() {
    deptCheck.setSelected(false);
    for (Map.Entry<Integer, Circle> entry : deptMap.entrySet()) {
      Circle c = entry.getValue();
      nodesPane.getChildren().remove(c);
    }
    deptHighlighted = false;
  }

  @FXML
  public void highlightInfo() {
    ArrayList<Node> nodes = qdb.retrieveAllNodes();
    for (int i = 0; i < nodes.size(); i++) {
      Node n = nodes.get(i);
      if (n.getFloor().equals(currentFloor) && n.getLocation().getNodeType().equals("INFO")) {
        Circle circle = new Circle();
        nodesPane.getChildren().add(circle);
        circle.setCenterX(n.getXCoord());
        circle.setCenterY(n.getYCoord());
        circle.setRadius(16);
        circle.setStyle("-fx-fill: #5A015A");
        infoMap.put(i, circle);
      }
    }
    infoHighlighted = true;
  }

  @FXML
  public void unhighlightInfo() {
    infoCheck.setSelected(false);
    for (Map.Entry<Integer, Circle> entry : infoMap.entrySet()) {
      Circle c = entry.getValue();
      nodesPane.getChildren().remove(c);
    }
    infoHighlighted = false;
  }

  @FXML
  public void highlightLabs() {
    ArrayList<Node> nodes = qdb.retrieveAllNodes();
    for (int i = 0; i < nodes.size(); i++) {
      Node n = nodes.get(i);
      if (n.getFloor().equals(currentFloor) && n.getLocation().getNodeType().equals("LABS")) {
        Circle circle = new Circle();
        nodesPane.getChildren().add(circle);
        circle.setCenterX(n.getXCoord());
        circle.setCenterY(n.getYCoord());
        circle.setRadius(16);
        circle.setStyle("-fx-fill: #5A015A");
        labsMap.put(i, circle);
      }
    }
    labsHighlighted = true;
  }

  @FXML
  public void unhighlightLabs() {
    labsCheck.setSelected(false);
    for (Map.Entry<Integer, Circle> entry : labsMap.entrySet()) {
      Circle c = entry.getValue();
      nodesPane.getChildren().remove(c);
    }
    labsHighlighted = false;
  }

  @FXML
  public void highlightRest() {
    ArrayList<Node> nodes = qdb.retrieveAllNodes();
    for (int i = 0; i < nodes.size(); i++) {
      Node n = nodes.get(i);
      if (n.getFloor().equals(currentFloor)
          && (n.getLocation().getNodeType().equals("REST")
              || n.getLocation().getNodeType().equals("BATH"))) {
        Circle circle = new Circle();
        nodesPane.getChildren().add(circle);
        circle.setCenterX(n.getXCoord());
        circle.setCenterY(n.getYCoord());
        circle.setRadius(16);
        circle.setStyle("-fx-fill: #5A015A");
        restMap.put(i, circle);
      }
    }
    restHighlighted = true;
  }

  @FXML
  public void unhighlightRest() {
    restCheck.setSelected(false);
    for (Map.Entry<Integer, Circle> entry : restMap.entrySet()) {
      Circle c = entry.getValue();
      nodesPane.getChildren().remove(c);
    }
    restHighlighted = false;
  }

  @FXML
  public void highlightRetl() {
    ArrayList<Node> nodes = qdb.retrieveAllNodes();
    for (int i = 0; i < nodes.size(); i++) {
      Node n = nodes.get(i);
      if (n.getFloor().equals(currentFloor) && n.getLocation().getNodeType().equals("RETL")) {
        Circle circle = new Circle();
        nodesPane.getChildren().add(circle);
        circle.setCenterX(n.getXCoord());
        circle.setCenterY(n.getYCoord());
        circle.setRadius(16);
        circle.setStyle("-fx-fill: #5A015A");
        retlMap.put(i, circle);
      }
    }
    retlHighlighted = true;
  }

  @FXML
  public void unhighlightRetl() {
    retlCheck.setSelected(false);
    for (Map.Entry<Integer, Circle> entry : retlMap.entrySet()) {
      Circle c = entry.getValue();
      nodesPane.getChildren().remove(c);
    }
    retlHighlighted = false;
  }

  @FXML
  public void highlightServ() {
    ArrayList<Node> nodes = qdb.retrieveAllNodes();
    for (int i = 0; i < nodes.size(); i++) {
      Node n = nodes.get(i);
      if (n.getFloor().equals(currentFloor) && n.getLocation().getNodeType().equals("SERV")) {
        Circle circle = new Circle();
        nodesPane.getChildren().add(circle);
        circle.setCenterX(n.getXCoord());
        circle.setCenterY(n.getYCoord());
        circle.setRadius(16);
        circle.setStyle("-fx-fill: #5A015A");
        servMap.put(i, circle);
      }
    }
    servHighlighted = true;
  }

  @FXML
  public void unhighlightServ() {
    servCheck.setSelected(false);
    for (Map.Entry<Integer, Circle> entry : servMap.entrySet()) {
      Circle c = entry.getValue();
      nodesPane.getChildren().remove(c);
    }
    servHighlighted = false;
  }

  @FXML
  public void unhighlightAll() {
    unhighlightConf();
    unhighlightDept();
    unhighlightInfo();
    unhighlightLabs();
    unhighlightRest();
    unhighlightRetl();
    unhighlightServ();
  }

  @FXML
  public void confChecked() {
    if (!confHighlighted) {
      highlightConf();
    } else {
      unhighlightConf();
    }
  }

  @FXML
  public void deptChecked() {
    if (!deptHighlighted) {
      highlightDept();
    } else {
      unhighlightDept();
    }
  }

  @FXML
  public void infoChecked() {
    if (!infoHighlighted) {
      highlightInfo();
    } else {
      unhighlightInfo();
    }
  }

  @FXML
  public void labsChecked() {
    if (!labsHighlighted) {
      highlightLabs();
    } else {
      unhighlightLabs();
    }
  }

  @FXML
  public void restChecked() {
    if (!restHighlighted) {
      highlightRest();
    } else {
      unhighlightRest();
    }
  }

  @FXML
  public void retlChecked() {
    if (!retlHighlighted) {
      highlightRetl();
    } else {
      unhighlightRetl();
    }
  }

  @FXML
  public void servChecked() {
    if (!servHighlighted) {
      highlightServ();
    } else {
      unhighlightServ();
    }
  }

  @FXML
  public void clearClicked() {
    unhighlightAll();
  }
}
