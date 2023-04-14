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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.util.Duration;
import net.kurobako.gesturefx.*;

public class PathfindingController {
  Qdb qdb = Qdb.getInstance();
  // Stage stage = App.getPrimaryStage();
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

  @FXML HBox root;
  @FXML Group parent;
  @FXML ImageView map;
  @FXML Button previousFloor;
  @FXML Button nextFloor;

  @FXML
  public void initialize() throws IOException {
    previousNodes = new ArrayList<>();
    floors = new ArrayList<>();
    // Image gf = new Image("/00_thegroundfloor.png");
    Image l1 = new Image("/00_thelowerlevel1.png");
    Image l2 = new Image("/00_thelowerlevel2.png");
    Image ff = new Image("/01_thefirstfloor.png");
    Image sf = new Image("/02_thesecondfloor.png");
    Image tf = new Image("/03_thethirdfloor.png");
    // floors.add(gf); // 0
    floors.add(l1); // 0
    floors.add(l2); // 1
    floors.add(ff); // 2
    floors.add(sf); // 3
    floors.add(tf); // 4
    floor = 2;
    ready4Second = false;
    previousPath = new ArrayList<>();
    addButtons("1");
    pane = new GesturePane();
    pane.setContent(parent);
    pane.setFitMode(GesturePane.FitMode.COVER);
    root.getChildren().add(pane);

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

  public void addButtons(String f) {
    List<Node> nodes = qdb.retrieveAllNodes();
    List<Node> fNodes = new ArrayList<>();
    for (Node n : nodes) {
      if (n.getFloor().equals(f)) {
        fNodes.add(n);
      }
    }
    for (Node n : fNodes) {
      int x = n.getXCoord() / 5;
      int y = n.getYCoord() / 5;
      Button node = new Button();
      node.setLayoutX(x);
      node.setLayoutY(y);
      node.setStyle(
          "-fx-background-radius: 5em;"
              + "-fx-min-width: 3px;"
              + "-fx-min-height: 3px;"
              + "-fx-max-width: 3px;"
              + "-fx-max-height: 3px;"
              + "-fx-background-insets: 0px;");
      node.setOnMouseEntered(
          e -> {
            String nodeid = "";
            text = new Text(x + 3, y + 3, nodeid + n.getNodeID());
            text.setStyle("-fx-font-size: 8px;");
            parent.getChildren().add(text);
          });
      node.setOnMouseExited(
          e -> {
            parent.getChildren().remove(text);
          });
      node.setOnMouseClicked(
          e -> {
            if (!ready4Second) {
              ready4Second = true;
              start = n;
            } else {
              target = n;
              ready4Second = false;
              try {
                removeLines(previousPath);
                // progress bar of generating a new path
                previousPath = drawLinesf(start, target, f);
              } catch (Exception ex) {
                throw new RuntimeException(ex);
              }
            }
          });
      previousNodes.add(node);
      parent.getChildren().add(node);
    }
  }

  public void removeButtons() {
    for (Button b : previousNodes) {
      parent.getChildren().remove(b);
    }
  }

  public List<Line> addLines(List<Node> path) {
    List<Line> lines = new ArrayList<>();
    for (int i = path.size() - 1; i >= 1; i--) {
      // for (int i = 0; i < path.size() - 1; i++) {
      Node n = path.get(i);
      Node next = path.get(i - 1);
      // Node next = path.get(i + 1);
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

  public List<Line> drawLinesf(Node start, Node target, String floor) throws IOException {
    List<Node> path = Star2.aStar(start, target);
    List<Node> fpath = new ArrayList<>();
    for (Node n : path) {
      if (n.getFloor().equals(floor)) {
        fpath.add(n);
      }
    }
    List<Line> lines = new ArrayList<>();
    if (fpath.size() > 0) {
      lines = addLines(fpath);
    }
    if (path.size() <= 0) {
      alert.alertBox("No solution", "Failed to find a path");
    }
    return lines;
  }

  public String whichFloor() {
    String f = "";
    switch (floor) {
      case 0:
        f = "L1";
        break;
      case 1:
        f = "L2";
        break;
      case 2:
        f = "1";
        break;
      case 3:
        f = "2";
        break;
      case 4:
        f = "3";
        break;
    }
    return f;
  }

  public void previousFloorClicked() throws IOException {
    String f = "";
    if (floor == 1) {
      previousFloor.setDisable(true);
    }
    if (nextFloor.isDisable()) {
      nextFloor.setDisable(false);
    }
    if (floor > 0) {
      floor--;
      f = whichFloor();
    }
    Image previous = floors.get(floor);
    map.setImage(previous);
    removeButtons();
    removeLines(previousPath); // remove previous floor's path
    addButtons(f); // add current floor's path
    if (!ready4Second) {
      previousPath = drawLinesf(start, target, f);
    }
  }

  public void nextFloorClicked() throws IOException {
    String f = "";
    if (floor == 3) {
      nextFloor.setDisable(true);
    }
    if (previousFloor.isDisable()) {
      previousFloor.setDisable(false);
    }
    if (floor < 4) {
      floor++;
      f = whichFloor();
    }
    Image next = floors.get(floor);
    map.setImage(next);
    removeButtons();
    removeLines(previousPath);
    addButtons(f);
    previousPath = drawLinesf(start, target, f);
  }
}
