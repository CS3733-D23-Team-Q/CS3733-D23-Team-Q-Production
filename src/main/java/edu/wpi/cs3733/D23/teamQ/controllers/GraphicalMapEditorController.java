package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.Alert;
import edu.wpi.cs3733.D23.teamQ.db.Qdb;
import edu.wpi.cs3733.D23.teamQ.db.obj.Location;
import edu.wpi.cs3733.D23.teamQ.db.obj.Node;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import javafx.animation.Interpolator;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.util.Duration;
import net.kurobako.gesturefx.GesturePane;

public class GraphicalMapEditorController {
  Qdb qdb = Qdb.getInstance();
  // Stage stage = App.getPrimaryStage();
  Alert alert = new Alert();
  Text text;
  @FXML Pane root;
  @FXML Group parent;
  @FXML ImageView map;
  @FXML Button addButton;
  @FXML Button editButton;
  @FXML Button deleteButton;

  @FXML
  public void initialize() throws IOException {
    addButtons();
    javafx.scene.Node node = parent;
    GesturePane pane = new GesturePane(node);
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
          "-fx-background-radius: 5em;"
              + "-fx-min-width: 3px;"
              + "-fx-min-height: 3px;"
              + "-fx-max-width: 3px;"
              + "-fx-max-height: 3px;"
              + "-fx-background-insets: 0px;");
      node.setOnMouseEntered(
          e -> {
            int nodeID = n.getNodeID();
            Location location = qdb.retrieveLocation(nodeID);
            String name = location.getShortName();
            Pattern pattern = Pattern.compile("(?i).*hall.*");
            if (!pattern.matcher(name).matches()) {
              text = new Text(x + 3, y + 3, name);
              text.setStyle("-fx-font-size: 8px;");
              parent.getChildren().add(text);
            } else {
              text = new Text(x + 3, y + 3, "");
              text.setStyle("-fx-font-size: 8px;");
              parent.getChildren().add(text);
            }
          });
      node.setOnMouseExited(
          e -> {
            parent.getChildren().remove(text);
          });
      final Delta dragDelta = new Delta();
      /*
      node.setOnMousePressed(
          e -> {
            //dragDelta.x = node.getLayoutX() - e.getSceneX();
            //dragDelta.y = node.getLayoutY() - e.getSceneY();
              dragDelta.x = node.getLayoutX() - e.getSceneX();
            node.setCursor(Cursor.MOVE);
          });
      node.setOnMouseReleased(
          e -> {
            node.setCursor(Cursor.HAND);
          });
      node.setOnMouseDragged(
          e -> {
            node.setLayoutX(e.getSceneX());
            node.setLayoutY(e.getSceneY());
          });
      node.setOnMouseEntered(
          e -> {
            node.setCursor(Cursor.HAND);
          });
       */
      /*
      node.setOnMouseClicked(
              e -> {

              });
       */
      parent.getChildren().add(node);
    }
  }

  class Delta {
    double x, y;
  }
}
