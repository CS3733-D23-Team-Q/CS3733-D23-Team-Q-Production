package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.Alert;
import edu.wpi.cs3733.D23.teamQ.Pathfinding.Star2;
import edu.wpi.cs3733.D23.teamQ.db.Qdb;
import edu.wpi.cs3733.D23.teamQ.db.obj.Node;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.animation.Interpolator;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.util.Duration;
import net.kurobako.gesturefx.*;

public class PathfindingController {
  Qdb qdb = Qdb.getInstance();
  // Stage stage = App.getPrimaryStage();
  Alert alert = new Alert();
  boolean ready4Second;
  Node start;
  Node target;
  List<Line> previousPath;
  Text text;
  @FXML Pane root;
  @FXML Group parent;
  @FXML ImageView map;

  @FXML
  public void initialize() throws IOException {
    ready4Second = false;
    previousPath = new ArrayList<>();
    // ImageView view = new ImageView(getClass().getResource("/L1.png").toExternalForm());
    // view.setFitWidth(1000);
    // view.setFitHeight(680);
    addButtons();
    javafx.scene.Node node = parent;
    GesturePane pane = new GesturePane(node);
    root.getChildren().add(pane);

    /*
    Node start = qdb.retrieveNode(100);
    Node target = qdb.retrieveNode(130);
    drawLines(start, target);
     */

    pane.setOnMouseClicked(
        e -> {
          if (e.getButton() == MouseButton.PRIMARY && e.getClickCount() == 2) {
            Point2D pivotOnTarget =
                pane.targetPointAt(new Point2D(e.getX(), e.getY()))
                    .orElse(pane.targetPointAtViewportCentre());
            pane.animate(Duration.millis(200))
                .interpolateWith(Interpolator.EASE_BOTH)
                .zoomBy(pane.getCurrentScale(), pivotOnTarget);
          }
        });
  }

  public void addButtons() {
    List<Node> nodes = qdb.retrieveAllNodes();
    List<Node> L1nodes = new ArrayList<>();
    for (Node n : nodes) {
      if (n.getFloor().equals("L1")) {
        L1nodes.add(n);
      }
    }
    for (Node n : L1nodes) {
      int x = n.getXCoord() / 5;
      int y = n.getYCoord() / 5;
      Button node = new Button();
      node.setLayoutX(x);
      node.setLayoutY(y);
      node.setStyle(
          "-fx-background-radius: 5em;" // 5
              + "-fx-min-width: 6px;" // 3
              + "-fx-min-height: 6px;"
              + "-fx-max-width: 6px;"
              + "-fx-max-height: 6px;"
              + "-fx-background-insets: 0px;");
      node.setOnMouseEntered(
          e -> {
            // double cursorx = e.getX();
            // double cursory = e.getY();
            // parent.getChildren().remove(text);
            text = new Text(x, y, n.getBuilding());
            parent.getChildren().add(text);
          });
      node.setOnMouseExited(
          e -> {
            text.setText("");
          });
      node.setOnMouseClicked(
          e -> {
            if (!ready4Second) {
              ready4Second = true;
              start = n;
            } else {
              target = n;
              ready4Second = false;
              removeLines(previousPath);
              try {
                previousPath = drawLines(start, target);
              } catch (IOException ex) {
                throw new RuntimeException(ex);
              }
            }
          });
      parent.getChildren().add(node);
    }
  }

  public List<Line> addLines(List<Node> path) {
    List<Line> lines = new ArrayList<>();
    for (int i = path.size() - 1; i >= 1; i--) {
      Node n = path.get(i);
      Node next = path.get(i - 1);
      int x1 = n.getXCoord() / 5;
      int y1 = n.getYCoord() / 5;
      int x2 = next.getXCoord() / 5;
      int y2 = next.getYCoord() / 5;
      Line line = new Line(x1, y1, x2, y2);
      line.setStyle("-fx-stroke: blue;");
      line.setStrokeWidth(3);
      parent.getChildren().add(line);
      lines.add(line);
    }
    return lines;
  }

  public void removeLines(List<Line> lines) {
    if (lines.size() > 0) {
      for (Line line : lines) {
        parent.getChildren().remove(line);
      }
    }
  }

  /*
  public void addLine(Node n, Node next) {
    int x1 = n.getXCoord() / 5;
    int y1 = n.getYCoord() / 5;
    int x2 = next.getXCoord() / 5;
    int y2 = next.getYCoord() / 5;
    Line line = new Line(x1, y1, x2, y2);
    line.setStyle("-fx-stroke: blue;");
    line.setStrokeWidth(3);
    // line.toFront();
    parent.getChildren().add(line);
    // root.getChildren().add(parent);
  }
   */

  public List<Line> drawLines(Node start, Node target) throws IOException {
    List<Node> path = Star2.aStar(start, target);
    List<Line> lines = new ArrayList<>();
    if (path.size() > 0) {
      lines = addLines(path);
      /*
      for (int i = path.size() - 1; i >= 1; i--) { // change to i++ when calling the exist one
        Node n = path.get(i);
        Node next = path.get(i - 1);
        addLine(n, next);
        // map.toBack();
      }
       */
    } else {
      alert.alertBox("No solution", "Failed to find a path");
    }
    return lines;
  }
}