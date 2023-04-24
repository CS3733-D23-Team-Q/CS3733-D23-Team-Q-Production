package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.Alert;
import edu.wpi.cs3733.D23.teamQ.App;
import edu.wpi.cs3733.D23.teamQ.Pathfinding.*;
import edu.wpi.cs3733.D23.teamQ.db.Qdb;
import edu.wpi.cs3733.D23.teamQ.db.obj.Location;
import edu.wpi.cs3733.D23.teamQ.db.obj.Move;
import edu.wpi.cs3733.D23.teamQ.db.obj.Node;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXCheckbox;
import io.github.palexdev.materialfx.controls.MFXFilterComboBox;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.animation.Interpolator;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.util.Duration;
import javafx.util.Pair;
import net.kurobako.gesturefx.GesturePane;

public class Pathfinding2Controller {
  Qdb qdb = Qdb.getInstance();
  // Stage stage = App.getPrimaryStage();
  Context pathfindingAlgorithmSelection = new Context();
  AStar aStar = new AStar();
  BFS bfs = new BFS();
  DFS dfs = new DFS();
  Djikstra djikstra = new Djikstra();
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
  List<Integer> restNodes;
  List<Integer> deptNodes;
  List<Integer> labsNodes;
  List<Integer> infoNodes;
  List<Integer> confNodes;
  List<Integer> retlNodes;
  List<Integer> servNodes;
  List<Integer> nodeIds;
  List<Pair<Integer, Integer>> highlightedNodes;
  // List<Integer> highlightedNodes;
  List<Pair<Integer, Integer>> l1nodes;
  List<Pair<Integer, Integer>> l2nodes;
  List<Pair<Integer, Integer>> ffnodes;
  List<Pair<Integer, Integer>> sfnodes;
  List<Pair<Integer, Integer>> tfnodes;
  List<String> allSelections;
  String algorithm;
  Date date;
  List<Date> moveDates;
  ToggleGroup dateToggle;
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
    algorithm = "A*";
    pathfindingAlgorithmSelection.setPathfindingAlgorithm(aStar);
    algorithmField.setValue(null);
    algorithmField.setItems(algorithmChoices);
    dateToggle = new ToggleGroup();
    date = Date.valueOf("2023-01-01");
    moveDates = new ArrayList<>();
    algorithm = "aStar";
    floors = new ArrayList<>();
    floors.add(ll1); // 0
    floors.add(ll2); // 1
    floors.add(ff); // 2
    floors.add(sf); // 3
    floors.add(tf); // 4
    floor = 2;
    ready4Second = false;
    previousPath = new ArrayList<>();
    //addButtons("1");

    floorField
        .valueProperty()
        .addListener(
            new ChangeListener() {
              @Override
              public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                String floor = (String) newValue;
                if (floor.equals("Lower Level 1")) {
                  mapImage.setImage(ll1);
                } else if (floor.equals("Lower Level 2")) {
                  mapImage.setImage(ll2);
                } else if (floor.equals("First Floor")) {
                  mapImage.setImage(ff);
                } else if (floor.equals("Second Floor")) {
                  mapImage.setImage(sf);
                } else if (floor.equals("Third Floor")) {
                  mapImage.setImage(tf);
                }
              }
            });
    algorithmField
        .valueProperty()
        .addListener(
            new ChangeListener() {
              @Override
              public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                algorithm = (String) newValue;
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
  }

  public void addButtons(String f) {
    List<Node> allNodes = qdb.retrieveAllNodes(); // nodes
    List<Move> allMoves = qdb.retrieveAllMoves();
    List<Node> moveNodes = new ArrayList<>();
    List<Node> floorNodes = new ArrayList<>(); // fNodes

    restNodes.removeAll(restNodes);
    deptNodes.removeAll(deptNodes);
    labsNodes.removeAll(labsNodes);
    infoNodes.removeAll(infoNodes);
    confNodes.removeAll(confNodes);
    retlNodes.removeAll(retlNodes);
    servNodes.removeAll(servNodes);

    l1nodes.removeAll(l1nodes);
    l2nodes.removeAll(l2nodes);
    ffnodes.removeAll(ffnodes);
    sfnodes.removeAll(sfnodes);
    tfnodes.removeAll(tfnodes);

    for (Move m : allMoves) {
      Date d = m.getDate();
      if (d.compareTo(date) == 0) {
        for (Node n : allNodes) {
          if (m.getNode().getNodeID() == n.getNodeID()) {
            moveNodes.add(n);
          }
          // add a variable to ignore the duplicates
        }
      }
      if (!moveDates.contains(d)) {
        moveDates.add(d);
        RadioMenuItem item = new RadioMenuItem(d.toString());
        if (d.compareTo(Date.valueOf("2023-01-01")) == 0) {
          item.setSelected(true);
        }
        item.setToggleGroup(dateToggle);
        dateField.getItems().add(item);
        item.setOnAction(
                e -> {
                  dateSelected(item);
                });
      }
    }

    for (Node n : moveNodes) { // Node n : nodes
      int nodeid = n.getNodeID();
      nodeIds.add(nodeid);
      Location location = qdb.retrieveLocation(nodeid);
      String nodetype = location.getNodeType();
      String lname = location.getLongName();
      if (n.getFloor().equals(f)) {
        floorNodes.add(n);
      }
      if (!nodetype.equals("HALL") && !nodetype.equals("ELEV") && !nodetype.equals("STAI")) {
        startingField.getItems().add(lname);
        destinationField.getItems().add(lname);
        allSelections.add(lname);
      }
    }
    for (Node n : floorNodes) {
      int x = n.getXCoord() / 5;
      int y = n.getYCoord() / 5;
      int nodeid = n.getNodeID();
      Location location = qdb.retrieveLocation(nodeid);
      String sname = location.getShortName();
      // String lname = location.getLongName();
      String nodetype = location.getNodeType();
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
      nodesPane.getChildren().add(node);
      int index = nodesPane.getChildren().indexOf(node);
      // System.out.println(index);
      node.setOnMouseEntered(
              e -> {
                // String nodeid = "";
                text = new Text(x + 3, y + 3, sname);
                text.setStyle("-fx-font-size: 8px;");
                nodesPane.getChildren().add(text);
              });
      node.setOnMouseExited(
              e -> {
                nodesPane.getChildren().remove(text);
              });
      node.setOnMouseClicked(
              e -> {
                if (!ready4Second) {
                  ready4Second = true;
                  start = n;
                  removeLines(previousPath);
                  if (highlightedNodes.size() > 0) {
                    for (int i = 0; i < highlightedNodes.size(); i++) {
                      if (highlightedNodes.get(i).getValue() == floor) {
                        unhighlight(highlightedNodes.get(i).getKey());
                      }
                    }
                    highlightedNodes.removeAll(highlightedNodes);
                  }
                  highlight(node, "red");
                  highlightedNodes.add(new Pair<>(index, floor));
                } else {
                  target = n;
                  ready4Second = false;
                  highlight(node, "red");
                  highlightedNodes.add(new Pair<>(index, floor));
                  try {
                    // removeLines(previousPath);
                    // progress bar of generating a new path
                    previousPath = drawLinesf(start, target, f);
                  } catch (Exception ex) {
                    throw new RuntimeException(ex);
                  }
                }
              });
      // System.out.println(parent.getChildren().size());
      previousNodes.add(node);
      restNodes = addSpecificNode("\\b(REST|BATH)\\b", nodetype, restNodes, index);
      deptNodes = addSpecificNode("\\bDEPT\\b", nodetype, deptNodes, index);
      labsNodes = addSpecificNode("\\bLABS\\b", nodetype, labsNodes, index);
      infoNodes = addSpecificNode("\\bINFO\\b", nodetype, infoNodes, index);
      confNodes = addSpecificNode("\\bCONF\\b", nodetype, confNodes, index);
      retlNodes = addSpecificNode("\\bRETL\\b", nodetype, retlNodes, index);
      servNodes = addSpecificNode("\\bSERV\\b", nodetype, servNodes, index);
      switch (f) {
        case "L1":
          l1nodes.add(new Pair<>(nodeid, index));
          break;
        case "L2":
          l2nodes.add(new Pair<>(nodeid, index));
          break;
        case "1":
          ffnodes.add(new Pair<>(nodeid, index));
          break;
        case "2":
          sfnodes.add(new Pair<>(nodeid, index));
          break;
        case "3":
          tfnodes.add(new Pair<>(nodeid, index));
          break;
      }
    }
  }

  public List<Integer> addSpecificNode(
          String pattern, String input, List<Integer> nodes, int node) {
    Pattern pattern1 = Pattern.compile(pattern);
    Matcher matcher1 = pattern1.matcher(input);
    if (matcher1.find()) {
      nodes.add(node);
    }
    return nodes;
  }

  public void removeButtons() {
    for (Button b : previousNodes) {
      nodesPane.getChildren().remove(b);
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
      line.setStrokeWidth(2);
      //      parent.getChildren().add(line);
      //      lines.add(line);

      if (!next.getLocation().getNodeType().equals("ELEV")
              && !next.getLocation().getNodeType().equals("STAI")
              && !n.getLocation().getNodeType().equals("ELEV")
              && !n.getLocation().getNodeType().equals("STAI")) {
        nodesPane.getChildren().add(line);
        lines.add(line);
      }
    }
    return lines;
  }

  public void removeLines(List<Line> lines) {
    if (lines.size() > 0) {
      for (Line line : lines) {
        nodesPane.getChildren().remove(line);
      }
    }
  }

  public List<Line> drawLinesf(Node start, Node target, String floor)
          throws IOException { // add a string to specify the algorithm (no)
    List<Node> path = new ArrayList<>();
    if (algorithm.equals("aStar")) {
      pathfindingAlgorithmSelection.setPathfindingAlgorithm(
              aStar); // if a*, call this function (instead, create a String algorithm global variable
      // that changes whenever the button is clicked)
      path = pathfindingAlgorithmSelection.run(start, target);
    } else if (algorithm.equals("bfs")) {
      pathfindingAlgorithmSelection.setPathfindingAlgorithm(bfs);
      path = pathfindingAlgorithmSelection.run(start, target);
    } else if (algorithm.equals("dfs")) {
      pathfindingAlgorithmSelection.setPathfindingAlgorithm(dfs);
      path = pathfindingAlgorithmSelection.run(start, target);
    } else {
      pathfindingAlgorithmSelection.setPathfindingAlgorithm(djikstra);
      path = pathfindingAlgorithmSelection.run(start, target);
    }
    /*
    path =
        AStar.aStar(
            start, target);
     */
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
    if (path.size() == 0) {
      alert.alertBox("No solution", "Failed to find a path");
    }
    return lines;
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

  public List<Pair<Integer, Integer>> setCF(List<Pair<Integer, Integer>> cfnodes) {
    switch (floor) {
      case 0:
        cfnodes = l1nodes;
        break;
      case 1:
        cfnodes = l2nodes;
        break;
      case 2:
        cfnodes = ffnodes;
        break;
      case 3:
        cfnodes = sfnodes;
        break;
      case 4:
        cfnodes = tfnodes;
        break;
    }
    return cfnodes;
  }

  public void previousFloorClicked() throws IOException {
    String f = "";
    if (floor > 0) {
      floor--;
      f = whichFloorS();
    }
    Image previous = floors.get(floor);
    mapImage.setImage(previous);
    /*
    if (highlightedNodes.size() > 0) {
      for (int i = 0; i < highlightedNodes.size(); i++) {
        unhighlight(highlightedNodes.get(i).getKey());
      }
    }
     */
    removeButtons();
    removeLines(previousPath); // remove previous floor's path
    addButtons(f); // add current floor's path
    restChecked();
    deptChecked();
    labsChecked();
    infoChecked();
    confChecked();
    retlChecked();
    servChecked();
    // if on the same floor
    if (highlightedNodes.size() > 0) {
      for (int i = 0; i < highlightedNodes.size(); i++) {
        if (highlightedNodes.get(i).getValue() == floor) {
          highlight(highlightedNodes.get(i).getKey(), "red");
        }
      }
    }
    if (!ready4Second && start != null && target != null) {
      previousPath = drawLinesf(start, target, f);
      /*
      List<Pair<Integer, Integer>> cfnodes = new ArrayList<>();
      cfnodes = setCF(cfnodes);
       */
    }
  }

  public void nextFloorClicked() throws IOException {
    String f = "";
    if (floor < 4) {
      floor++;
      f = whichFloorS();
    }
    Image next = floors.get(floor);
    mapImage.setImage(next);
    /*
    if (highlightedNodes.size() > 0) {
      for (int i = 0; i < highlightedNodes.size(); i++) {
        unhighlight(highlightedNodes.get(i).getKey());
      }
    }
     */
    removeButtons();
    removeLines(previousPath);
    addButtons(f);
    restChecked();
    deptChecked();
    labsChecked();
    infoChecked();
    confChecked();
    retlChecked();
    servChecked();

    // if on the same floor
    if (highlightedNodes.size() > 0) {
      for (int i = 0; i < highlightedNodes.size(); i++) {
        if (highlightedNodes.get(i).getValue() == floor) {
          highlight(highlightedNodes.get(i).getKey(), "red");
        }
      }
    }
    if (!ready4Second && start != null && target != null) { // && previousPath.size() > 0
      previousPath = drawLinesf(start, target, f);
    }
  }

  public void highlight(List<Integer> nodes, String color) {
    String border = "-fx-border-color: ";
    border = border + color + ";";
    ObservableList<javafx.scene.Node> children = nodesPane.getChildren();
    for (int i : nodes) {
      javafx.scene.Node child = children.get(i);
      child.setStyle(
              "-fx-background-radius: 5em;"
                      + "-fx-min-width: 3px;"
                      + "-fx-min-height: 3px;"
                      + "-fx-max-width: 3px;"
                      + "-fx-max-height: 3px;"
                      + "-fx-background-insets: 0px;"
                      + border);
    }
  }

  public void highlight(Button node, String color) {
    String border = "-fx-border-color: ";
    border = border + color + ";";
    node.setStyle(
            "-fx-background-radius: 5em;"
                    + "-fx-min-width: 3px;"
                    + "-fx-min-height: 3px;"
                    + "-fx-max-width: 3px;"
                    + "-fx-max-height: 3px;"
                    + "-fx-background-insets: 0px;"
                    + border);
  }

  public void highlight(int node, String color) {
    String border = "-fx-border-color: ";
    border = border + color + ";";
    ObservableList<javafx.scene.Node> children = nodesPane.getChildren();
    Button child = (Button) children.get(node);
    child.setStyle(
            "-fx-background-radius: 5em;"
                    + "-fx-min-width: 3px;"
                    + "-fx-min-height: 3px;"
                    + "-fx-max-width: 3px;"
                    + "-fx-max-height: 3px;"
                    + "-fx-background-insets: 0px;"
                    + border);
  }

  public void unhighlight(List<Integer> nodes) {
    ObservableList<javafx.scene.Node> children = nodesPane.getChildren();
    for (int i : nodes) {
      javafx.scene.Node child = children.get(i);
      child.setStyle(
              "-fx-background-radius: 5em;"
                      + "-fx-min-width: 3px;"
                      + "-fx-min-height: 3px;"
                      + "-fx-max-width: 3px;"
                      + "-fx-max-height: 3px;"
                      + "-fx-background-insets: 0px;");
    }
  }

  public void unhighlight(int node) {
    ObservableList<javafx.scene.Node> children = nodesPane.getChildren();
    javafx.scene.Node child = children.get(node);
    child.setStyle(
            "-fx-background-radius: 5em;"
                    + "-fx-min-width: 3px;"
                    + "-fx-min-height: 3px;"
                    + "-fx-max-width: 3px;"
                    + "-fx-max-height: 3px;"
                    + "-fx-background-insets: 0px;");
  }

  @FXML
  public void confChecked() {typeChecked(confCheck, confNodes, "yellow");}

  @FXML
  public void deptChecked() {typeChecked(deptCheck, deptNodes, "orange");}

  @FXML
  public void infoChecked() {typeChecked(infoCheck, infoNodes, "darkblue");}

  @FXML
  public void labsChecked() {typeChecked(labsCheck, labsNodes, "purple");}

  @FXML
  public void restChecked() {typeChecked(restCheck, restNodes, "lightgreen");}

  @FXML
  public void retlChecked() {typeChecked(retlCheck, retlNodes, "lightblue");}

  @FXML
  public void servChecked() {typeChecked(servCheck, servNodes, "pink");}

  public void typeChecked(CheckBox check, List<Integer> nodes, String color) {
    if (check.isSelected()) {
      highlight(nodes, color);
    } else {
      unhighlight(nodes);
    }
  }

  public void startSelected() throws IOException {
    List<Pair<Integer, Integer>> cfnodes = new ArrayList<>();
    String lname = (String)startingField.getValue();
    if (!lname.equals("")) {
      int index = startingField.getSelectionModel().getSelectedIndex();
      int nodeid = nodeIds.get(index);
      Node n = qdb.retrieveNode(nodeid);
      String nodeFloor = n.getFloor();
      int f = whichFloorI(nodeFloor);
      int crossFloors = Math.abs(f - floor);
      if (!ready4Second) {
        ready4Second = true;
        start = n;
        removeLines(previousPath);
        if (highlightedNodes.size() > 0) {
          for (int i = 0; i < highlightedNodes.size(); i++) {
            if (highlightedNodes.get(i).getValue() == floor) {
              unhighlight(highlightedNodes.get(i).getKey());
            }
          }
          highlightedNodes.removeAll(highlightedNodes);
        }
      }
      if (f < floor) {
        for (int i = 0; i < crossFloors; i++) {
          previousFloorClicked();
        }
      }
      if (f > floor) {
        for (int i = 0; i < crossFloors; i++) {
          nextFloorClicked();
        }
      }
      cfnodes = setCF(cfnodes);
      for (int i = 0; i < cfnodes.size(); i++) {
        if (cfnodes.get(i).getKey() == nodeid) {
          if (highlightedNodes.size() > 0) {
            for (int j = 0; j < highlightedNodes.size(); j++) {
              if (highlightedNodes.get(j).getValue() == floor) {
                unhighlight(highlightedNodes.get(j).getKey());
              }
              highlightedNodes.removeAll(highlightedNodes);
            }
          }
          highlight(cfnodes.get(i).getValue(), "red"); // button
          highlightedNodes.add(
                  new Pair<>(
                          cfnodes.get(i).getValue(),
                          floor)); // int index = parent.getChildren().indexOf(node);
        }
      }
    }
  }

  public void endSelected() throws IOException {
    List<Pair<Integer, Integer>> cfnodes = new ArrayList<>();
    String f = whichFloorS();
    String lname = (String)destinationField.getValue();
    if (!lname.equals("")) {
      int index = destinationField.getSelectionModel().getSelectedIndex();
      int nodeid = nodeIds.get(index);
      Node n = qdb.retrieveNode(nodeid);
      String nodeFloor = n.getFloor();
      int nodef = whichFloorI(nodeFloor);
      int crossFloors = Math.abs(nodef - floor);
      if (ready4Second) {
        target = n;
        ready4Second = false;
        try {
          // removeLines(previousPath);
          previousPath = drawLinesf(start, target, f);
        } catch (Exception ex) {
          throw new RuntimeException(ex);
        }
      }
      if (nodef < floor) {
        for (int i = 0; i < crossFloors; i++) {
          previousFloorClicked();
        }
      }
      if (nodef > floor) {
        for (int i = 0; i < crossFloors; i++) {
          nextFloorClicked();
        }
      }
      cfnodes = setCF(cfnodes);
      for (int i = 0; i < cfnodes.size(); i++) {
        if (cfnodes.get(i).getKey() == nodeid) {
          highlight(cfnodes.get(i).getValue(), "red"); // button
          highlightedNodes.add(
                  new Pair<>(
                          cfnodes.get(i).getValue(),
                          floor)); // int index = parent.getChildren().indexOf(node);
        }
      }
    }
  }

  public void refresh() {
    String f = whichFloorS();
    removeButtons();
    addButtons(f);
  }

  public void dateSelected(RadioMenuItem itemSelect) {
    if(itemSelect.isSelected()) {
      clearClicked();
      date = Date.valueOf(itemSelect.getText());
      refresh();
    }
  }

  @FXML
  public void clearClicked() {List<Pair<Integer, Integer>> cfnodes = new ArrayList<>();
    // moveDates.removeAll(moveDates);
    removeLines(previousPath);
    start = null;
    target = null;
    // previousPath.removeAll(previousPath);
    restCheck.setSelected(false);
    deptCheck.setSelected(false);
    labsCheck.setSelected(false);
    infoCheck.setSelected(false);
    confCheck.setSelected(false);
    retlCheck.setSelected(false);
    servCheck.setSelected(false);
    // unhighlight(restNodes);
    // unhighlight(cafeNodes);
    cfnodes = setCF(cfnodes);
    // System.out.println(cfnodes.size());
    for (int i = 0; i < cfnodes.size(); i++) {
      unhighlight(cfnodes.get(i).getValue()); // + 1
    }
    highlightedNodes.removeAll(highlightedNodes);
    startingField.setValue(null);
    destinationField.setValue(null);}
}