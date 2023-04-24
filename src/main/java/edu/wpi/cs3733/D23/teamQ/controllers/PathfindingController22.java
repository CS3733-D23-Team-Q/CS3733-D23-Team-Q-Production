package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.Pathfinding.*;
import edu.wpi.cs3733.D23.teamQ.db.Qdb;
import edu.wpi.cs3733.D23.teamQ.db.obj.Move;
import edu.wpi.cs3733.D23.teamQ.db.obj.Node;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import net.kurobako.gesturefx.*;

public class PathfindingController22 {
  Qdb qdb = Qdb.getInstance();
  int floor;
  List<Image> floors;
  GesturePane pane;
  Date selectDate;
  List<Date> allDates;
  ToggleGroup dateToggle;
  @FXML HBox root;
  @FXML Group parent;
  @FXML ImageView map;
  @FXML Button previousFloor;
  @FXML Button nextFloor;
  @FXML CheckBox restCheck;
  @FXML CheckBox deptCheck;
  @FXML CheckBox labsCheck;
  @FXML CheckBox infoCheck;
  @FXML CheckBox confCheck;
  @FXML CheckBox retlCheck;
  @FXML CheckBox servCheck;
  @FXML ComboBox<String> startSelect;
  @FXML ComboBox<String> endSelect;
  @FXML RadioMenuItem aStarSelect;
  @FXML RadioMenuItem bfsSelect;
  @FXML RadioMenuItem dfsSelect;
  @FXML RadioMenuItem djikstraSelect;
  @FXML Menu dateMenu;
  @FXML TextArea textualPathfinding;
  @FXML TextField messageField;

  @FXML
  public void initialize() throws IOException {
    zoomablePane();
    storeFloorsImg();
    floor = 2;
    dateToggle = new ToggleGroup();
  }

  public void zoomablePane() {
    pane = new GesturePane();
    pane.setContent(parent);
    pane.setFitMode(GesturePane.FitMode.COVER);
    root.getChildren().add(pane);
  }

  public void storeFloorsImg() {
    floors = new ArrayList<>();
    Image l1 = new Image("/00_thelowerlevel1.png");
    Image l2 = new Image("/00_thelowerlevel2.png");
    Image ff = new Image("/01_thefirstfloor.png");
    Image sf = new Image("/02_thesecondfloor.png");
    Image tf = new Image("/03_thethirdfloor.png");
    floors.add(l1); // 0
    floors.add(l2); // 1
    floors.add(ff); // 2
    floors.add(sf); // 3
    floors.add(tf); // 4
  }

  public String whichFloorS() {
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

  public int whichFloorI(String floor) {
    int f = 0;
    switch (floor) {
      case "L1":
        f = 0;
        break;
      case "L2":
        f = 1;
        break;
      case "1":
        f = 2;
        break;
      case "2":
        f = 3;
        break;
      case "3":
        f = 4;
        break;
    }
    return f;
  }

  public List<Node> nodesForSelectDate() {
    List<Node> allNodes = qdb.retrieveAllNodes();
    List<Move> allMoves = qdb.retrieveAllMoves();
    List<Node> moveNodes = new ArrayList<>();
    for (Move m : allMoves) {
      Date date = m.getDate();
      if (date.compareTo(selectDate) == 0) {
        for (Node n : allNodes) {
          if (m.getNode().getNodeID() == n.getNodeID()) {
            moveNodes.add(n);
          }
        }
      }
    }
    return moveNodes;
  }

  public void setUpSelectDateMenu() {
    List<Move> allMoves = qdb.retrieveAllMoves();
    for (Move m : allMoves) {
      Date date = m.getDate();
      // ignore the duplicates
      if (!allDates.contains(date)) {
        allDates.add(date);
        RadioMenuItem item = new RadioMenuItem(date.toString());
        if (date.compareTo(Date.valueOf("2023-01-01")) == 0) {
          item.setSelected(true);
        }
        item.setToggleGroup(dateToggle);
        dateMenu.getItems().add(item);
        item.setOnAction(
            e -> {
              dateSelected(item);
            });
      }
    }
  }

  public void addButtons(String f) {
    List<Move> allMoves = qdb.retrieveAllMoves();
    List<Node> moveNodes = nodesForSelectDate();
    List<Node> floorNodes = new ArrayList<>();
  }

  public void removeButtons() {}

  public void removeText() {}

  public void dateSelected(RadioMenuItem itemSelect) {
    if (itemSelect.isSelected()) {
      clearButtonClicked();
      selectDate = Date.valueOf(itemSelect.getText());
      renew();
    }
  }

  public void renew() {
    String f = whichFloorS();
    removeText();
    removeButtons();
    addButtons(f);
  }

  public void clearButtonClicked() {}
}
