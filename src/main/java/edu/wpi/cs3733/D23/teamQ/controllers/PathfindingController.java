package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.Alert;
import edu.wpi.cs3733.D23.teamQ.App;
import edu.wpi.cs3733.D23.teamQ.Pathfinding.Star2;
import edu.wpi.cs3733.D23.teamQ.db.Qdb;
import edu.wpi.cs3733.D23.teamQ.db.obj.Node;
import java.io.IOException;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class PathfindingController {
  Qdb qdb = Qdb.getInstance();
  Stage stage = App.getPrimaryStage();
  Alert alert = new Alert();
  @FXML Pane parent;
  // @FXML HBox parent;
  // @FXML HBox root;
  @FXML ImageView map;

  @FXML
  public void initialize() throws IOException {
    /*
    Node start = qdb.retrieveNode(100);
    Node target = qdb.retrieveNode(130);
    int x1 = start.getXCoord();
    int y1 = start.getYCoord();
    int x2 = target.getXCoord();
    int y2 = target.getYCoord();
    Line line = new Line();
    line.setStartX(x1);
    line.setStartY(y1);
    line.setEndX(x2);
    line.setEndY(y2);
    line.setStyle("-fx-stroke: red;");
    line.setStrokeWidth(3);
    root.getChildren().add(line);
    int x11 = 100;
    int y11 = 100;
    int x22 = 500;
    int y22 = 500;
    Line line2 = new Line();
    line.setStartX(x11);
    line.setStartY(y11);
    line.setEndX(x22);
    line.setEndY(y22);
    line.setStyle("-fx-stroke: red;");
    line.setStrokeWidth(3);
    root.getChildren().add(line2);
     */
    Node start = qdb.retrieveNode(2315);
    Node target = qdb.retrieveNode(2240);
    drawLines(start, target);
    //    int x1 = 0;
    //    int y1 = 0;
    //    int x2 = 100;
    //    int y2 = 100;
    //    Line line = new Line(x1, y1, x2, y2);
    //    line.setStyle("-fx-stroke: red;");
    //    line.setStrokeWidth(3);
    //    parent.getChildren().add(line);
  }

  public void addLine(Node n, Node next) {
    int x1 = n.getXCoord() / 5;
    int y1 = n.getYCoord() / 5;
    int x2 = next.getXCoord() / 5;
    int y2 = next.getYCoord() / 5;
    Line line = new Line(x1, y1, x2, y2);
    line.setStyle("-fx-stroke: red;");
    line.setStrokeWidth(3);
    // line.toFront();
    parent.getChildren().add(line);
    // root.getChildren().add(parent);
  }

  public void drawLines(Node start, Node target) throws IOException {
    List<Node> path = Star2.aStar(start, target);
    if (path.size() > 0) {
      for (int i = path.size() - 1; i >= 1; i--) {
        Node n = path.get(i);
        Node next = path.get(i - 1);
        addLine(n, next);
        // map.toBack();
      }
    } else {
      alert.alertBox("No solution", "Failed to find a path");
    }
  }
}
