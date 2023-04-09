package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.App;
import edu.wpi.cs3733.D23.teamQ.db.Qdb;
import java.awt.*;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PathfindingController {
  Qdb qdb = Qdb.getInstance();
  Stage stage = App.getPrimaryStage();
  Group root = new Group();
  Scene scene = new Scene(root);

  @FXML
  public void initialize() {
    /*
    Node start = qdb.retrieveNode(115);
    Node end = qdb.retrieveNode(130);
    ArrayList<Node> path = Star.aStar(start, end);
    for (int i = 0; i < path.size(); i++) {
      Node starti = path.get(i);
      Node endi = path.get(i);
      int x1 = starti.getXCoord();
      int y1 = starti.getYCoord();
      int x2 = endi.getXCoord();
      int y2 = endi.getYCoord();
      Line line = new Line();
      line.setStartX(x1);
      line.setStartY(y1);
      line.setEndX(x2);
      line.setEndY(y2);
      root.getChildren().add(line);
       */
    /*
    int x1 = 200;
    int y1 = 20;
    int x2 = 300;
    int y2 = 20;
    Line line = new Line();
    line.setStartX(x1);
    line.setStartY(y1);
    line.setEndX(x2);
    line.setEndY(y2);
    root.getChildren().add(line);
    stage.setScene(scene);
    stage.show();
     */
  }
}
