package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.App;
import edu.wpi.cs3733.D23.teamQ.Pathfinding.*;
import edu.wpi.cs3733.D23.teamQ.db.Qdb;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXFilterComboBox;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import net.kurobako.gesturefx.GesturePane;

public class PathfindingController2 {
  Qdb qdb = Qdb.getInstance();
  Context pathfindingAlgorithmSelection = new Context();
  BFS bfs = new BFS();
  AStar aStar = new AStar();
  DFS dfs = new DFS();
  Djikstra djikstra = new Djikstra();
  @FXML GesturePane mapPane;
  @FXML MFXFilterComboBox startingField;
  @FXML MFXFilterComboBox destinationField;
  ObservableList<String> nodeTypes =
      FXCollections.observableArrayList(
          "Ground Floor",
          "Lower Level 1",
          "Lower Level 2",
          "First Floor",
          "Second Floor",
          "Third Floor");
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
  ObservableList<String> algorithmList =
      FXCollections.observableArrayList("A*", "Dijkstra's", "DFS", "BFS");
  @FXML MFXFilterComboBox algorithmField;
  private String algorithm;
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
    floorField.setValue("First Floor");
    floorField.setItems(floors);
    algorithmField.setValue("A*");
    algorithmField.setItems(algorithmList);
    pathfindingAlgorithmSelection.setPathfindingAlgorithm(aStar);

    floorField
        .valueProperty()
        .addListener(
            new ChangeListener<>() {
              @Override
              public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                String floor = (String) floorField.getValue();
                if (floor.equals("Ground Floor")) {
                  mapPane.setContent(I1);
                } else if (floor.equals("Lower Level 1")) {
                  mapPane.setContent(I2);
                } else if (floor.equals("Lower Level 2")) {
                  mapPane.setContent(I3);
                } else if (floor.equals("First Floor")) {
                  mapPane.setContent(I4);
                } else if (floor.equals("Second Floor")) {
                  mapPane.setContent(I5);
                } else if (floor.equals("Third Floor")) {
                  mapPane.setContent(I6);
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
  }
}
