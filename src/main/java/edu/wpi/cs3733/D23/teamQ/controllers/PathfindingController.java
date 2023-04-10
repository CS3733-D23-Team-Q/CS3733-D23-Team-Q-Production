package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.Alert;
import edu.wpi.cs3733.D23.teamQ.Pathfinding.Star2;
import edu.wpi.cs3733.D23.teamQ.db.Qdb;
import edu.wpi.cs3733.D23.teamQ.db.obj.Node;
import java.io.IOException;
import java.util.List;
import javafx.animation.Interpolator;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.util.Duration;
import net.kurobako.gesturefx.*;

public class PathfindingController {
  Qdb qdb = Qdb.getInstance();
  // Stage stage = App.getPrimaryStage();
  Alert alert = new Alert();
  @FXML Pane parent;
  /*
  @FXML HBox parent;
  @FXML ImageView map;
   */

  @FXML
  public void initialize() throws IOException {
    ImageView view = new ImageView(getClass().getResource("/L1.png").toExternalForm());
    view.setFitWidth(1000);
    view.setFitHeight(680);
    javafx.scene.Node node = view;
    GesturePane pane = new GesturePane(node);
    parent.getChildren().add(pane);
    // addButtons();
    Node start = qdb.retrieveNode(100);
    Node target = qdb.retrieveNode(130);
    drawLines(start, target);
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
    for (Node n : nodes) {
      int x = n.getXCoord() / 5;
      int y = n.getYCoord() / 5;
      Button node = new Button();
      node.setLayoutX(x);
      node.setLayoutY(y);
      parent.getChildren().add(node);
    }
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
      for (int i = path.size() - 1; i >= 1; i--) { // change to i++ when calling the exist one
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
