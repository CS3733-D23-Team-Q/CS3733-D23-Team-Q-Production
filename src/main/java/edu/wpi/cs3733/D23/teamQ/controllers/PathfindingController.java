package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.Alert;
import edu.wpi.cs3733.D23.teamQ.Pathfinding.*;
import edu.wpi.cs3733.D23.teamQ.db.Qdb;
import edu.wpi.cs3733.D23.teamQ.db.obj.Location;
import edu.wpi.cs3733.D23.teamQ.db.obj.Move;
import edu.wpi.cs3733.D23.teamQ.db.obj.Node;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.animation.Interpolator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.util.Duration;
import javafx.util.Pair;
import net.kurobako.gesturefx.*;
import org.apache.commons.lang3.tuple.Triple;

public class PathfindingController {
  Qdb qdb = Qdb.getInstance();
  Context pathfindingAlgorithmSelection = new Context();
  BFS bfs = new BFS();
  AStar aStar = new AStar();
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
  List<Text> previousText;
  List<Pair<Integer, Text>> restText;
  List<Pair<Integer, Text>> deptText;
  List<Pair<Integer, Text>> labsText;
  List<Pair<Integer, Text>> infoText;
  List<Pair<Integer, Text>> confText;
  List<Pair<Integer, Text>> retlText;
  List<Pair<Integer, Text>> servText;
  List<Integer> nodeIds;
  List<Triple<Button, Integer, Integer>> highlightedNodes;
  List<Triple<Button, Integer, Integer>> highlightedNodesp;
  List<Pair<Integer, Button>> l1nodes;
  List<Pair<Integer, Button>> l2nodes;
  List<Pair<Integer, Button>> ffnodes;
  List<Pair<Integer, Button>> sfnodes;
  List<Pair<Integer, Button>> tfnodes;
  List<String> allSelections;
  String algorithm;
  Date date;
  List<Date> moveDates;
  ToggleGroup dateToggle;
  List<Triple<Button, Integer, Integer>> cfpath;
  Text messageText;

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
    cfpath = new ArrayList<>();
    dateToggle = new ToggleGroup();
    date = Date.valueOf("2023-01-01");
    moveDates = new ArrayList<>();
    algorithm = "aStar";
    allSelections = new ArrayList<>();
    l1nodes = new ArrayList<>();
    l2nodes = new ArrayList<>();
    ffnodes = new ArrayList<>();
    sfnodes = new ArrayList<>();
    tfnodes = new ArrayList<>();
    highlightedNodes = new ArrayList<>();
    highlightedNodesp = new ArrayList<>();
    nodeIds = new ArrayList<>();
    restText = new ArrayList<>();
    deptText = new ArrayList<>();
    labsText = new ArrayList<>();
    infoText = new ArrayList<>();
    confText = new ArrayList<>();
    retlText = new ArrayList<>();
    servText = new ArrayList<>();
    previousNodes = new ArrayList<>();
    previousText = new ArrayList<>();
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
    autoComplete(startSelect);
    autoComplete(endSelect);
  }

  public void autoComplete(ComboBox<String> combobox) {
    TextField editor = combobox.getEditor();
    editor.setOnMouseClicked(
        e -> {
          // combobox.setValue(null);
          // editor.setText(null);
          combobox.show();
        });
    // add listener to the editor
    combobox.setOnKeyReleased(
        e -> {
          combobox.setValue(null);
          String input = editor.getText();
          combobox.setItems(FXCollections.observableArrayList(getMatchingItems(input)));
          combobox.show();
        });
    // set whenever an item is selected instead of setonaction
    if (combobox.equals(startSelect)) {
      combobox.setOnAction(
          e -> {
            editor.setText(combobox.getValue());
            try {
              startSelected();
            } catch (IOException ex) {
              throw new RuntimeException(ex);
            }
          });
    } else {
      combobox.setOnAction(
          e -> {
            editor.setText(combobox.getValue());
            try {
              endSelected();
            } catch (IOException ex) {
              throw new RuntimeException(ex);
            }
          });
    }
  }

  public List<String> getMatchingItems(String input) { // String[]
    /*
    return FXCollections.observableArrayList(allSelections)
        .filtered(item -> item.toLowerCase().contains(input.toLowerCase()))
        .toArray(new String[0]);
     */
    FilteredList<String> filteredItems =
        new FilteredList<>(FXCollections.observableList(allSelections));
    if (input == null || input.isEmpty()) {
      return allSelections;
    }
    filteredItems.setPredicate(item -> item.toLowerCase().startsWith(input.toLowerCase()));
    return filteredItems;
  }

  public void addButtons(String f) {
    List<Node> allNodes = qdb.retrieveAllNodes(); // nodes
    List<Move> allMoves = qdb.retrieveAllMoves();
    List<Node> moveNodes = new ArrayList<>();
    List<Node> floorNodes = new ArrayList<>(); // fNodes

    restText.removeAll(restText);
    deptText.removeAll(deptText);
    labsText.removeAll(labsText);
    infoText.removeAll(infoText);
    confText.removeAll(confText);
    retlText.removeAll(retlText);
    servText.removeAll(servText);

    l1nodes.removeAll(l1nodes);
    l2nodes.removeAll(l2nodes);
    ffnodes.removeAll(ffnodes);
    sfnodes.removeAll(sfnodes);
    tfnodes.removeAll(tfnodes);

    previousNodes.removeAll(previousNodes);
    previousText.removeAll(previousText);

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
        dateMenu.getItems().add(item);
        item.setOnAction(
            e -> {
              dateSelected(item);
            });
      }
    }

    boolean added = false;
    // for (Node n : moveNodes) { // Node n : nodes
    for (int i = 0; i < moveNodes.size(); i++) {
      if (i == 0 && nodeIds.size() > 0) {
        added = true;
      }
      Node n = moveNodes.get(i);
      int nodeid = n.getNodeID();
      Location location = qdb.retrieveLocation(nodeid);
      String nodetype = location.getNodeType();
      String lname = location.getLongName();
      if (n.getFloor().equals(f)) {
        floorNodes.add(n);
      }
      if (!added) {
        if (!nodetype.equals("HALL") && !nodetype.equals("ELEV") && !nodetype.equals("STAI")) {
          nodeIds.add(nodeid);
          startSelect.getItems().add(lname);
          endSelect.getItems().add(lname);
          allSelections.add(lname);
        }
      }
    }
    for (Node n : floorNodes) {
      double x = n.getXCoord() / 5 - 1.5;
      double y = n.getYCoord() / 5 - 1.5;
      int nodeid = n.getNodeID();
      Button node = new Button();
      node.setLayoutX(x);
      node.setLayoutY(y);
      node.setShape(new Circle(3));
      node.setMinSize(6, 6);
      node.setMaxSize(6, 6);
      node.setStyle(
          "-fx-background-color: lightblue;"
              + "-fx-border-color: black;"
              + "-fx-background-insets: 0px;");
      node.setStyle("-fx-background-color: transparent;");
      node.setDisable(true);
      parent.getChildren().add(node);
      node.toFront();
      int indexn = parent.getChildren().indexOf(node);
      previousNodes.add(node);
      switch (f) {
        case "L1":
          l1nodes.add(new Pair<>(nodeid, indexn));
          break;
        case "L2":
          l2nodes.add(new Pair<>(nodeid, indexn));
          break;
        case "1":
          ffnodes.add(new Pair<>(nodeid, indexn));
          break;
        case "2":
          sfnodes.add(new Pair<>(nodeid, indexn));
          break;
        case "3":
          tfnodes.add(new Pair<>(nodeid, indexn));
          break;
      }
    }

    for (Node n : floorNodes) {
      double x = n.getXCoord() / 5 - 1.5;
      double y = n.getYCoord() / 5 - 1.5;
      int nodeid = n.getNodeID();
      Location location = qdb.retrieveLocation(nodeid);
      String sname = location.getShortName();
      // String lname = location.getLongName();
      String nodetype = location.getNodeType();
      if (!nodetype.equals("HALL") && !nodetype.equals("ELEV") && !nodetype.equals("STAI")) {
        text = new Text(x, y, sname);
        text.setFill(Color.BLUE);
        text.setStyle("-fx-font-size: 3px;");
        parent.getChildren().add(text);
        int indext = parent.getChildren().indexOf(text);
        previousText.add(text);
        restText = addSpecificNode("\\b(REST|BATH)\\b", nodetype, restText, text, nodeid);
        deptText = addSpecificNode("\\bDEPT\\b", nodetype, deptText, text, nodeid);
        labsText = addSpecificNode("\\bLABS\\b", nodetype, labsText, text, nodeid);
        infoText = addSpecificNode("\\bINFO\\b", nodetype, infoText, text, nodeid);
        confText = addSpecificNode("\\bCONF\\b", nodetype, confText, text, nodeid);
        retlText = addSpecificNode("\\bRETL\\b", nodetype, retlText, text, nodeid);
        servText = addSpecificNode("\\bSERV\\b", nodetype, servText, text, nodeid);
      }
    }
  }

  public List<Pair<Integer, Text>> addSpecificNode(
      String pattern, String input, List<Pair<Integer, Text>> nodes, Text node, int nodeid) {
    Pattern pattern1 = Pattern.compile(pattern);
    Matcher matcher1 = pattern1.matcher(input);
    if (matcher1.find()) {
      nodes.add(new Pair<> (nodeid, node));
    }
    return nodes;
  }

  public void removeButtons() {
    for (Button b : previousNodes) {
      parent.getChildren().remove(b);
    }
  }

  public void removeText() {
    for (Text t : previousText) {
      parent.getChildren().remove(t);
    }
  }

  public List<Line> addLines(List<Pair<Node, Boolean>> path) { // List<Node> path
    List<Line> lines = new ArrayList<>();
    // for (int i = path.size() - 1; i >= 1; i--) {
    for (int i = 0; i < path.size() - 1; i++) {
      Node n = path.get(i).getKey();
      Node next = path.get(i + 1).getKey();
      int x1 = n.getXCoord() / 5;
      int y1 = n.getYCoord() / 5;
      int x2 = next.getXCoord() / 5;
      int y2 = next.getYCoord() / 5;
      Line line = new Line(x1, y1, x2, y2);
      line.setStyle("-fx-stroke: blue;");
      line.setStrokeWidth(3);
      if (!path.get(i).getValue()) {
        parent.getChildren().add(line);
        lines.add(line);
      }
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

  public List<Line> drawLinesf(Node start, Node target, String floor)
      throws IOException { // add a string to specify the algorithm (no)
    List<Node> path = new ArrayList<>();
    List<Pair<Integer, Integer>> cfnodes = new ArrayList<>();
    cfnodes = setCF(cfnodes);
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

    cfpath.removeAll(cfpath);
    List<Pair<Node, Boolean>> fpath = new ArrayList<>();
    for (int i = path.size() - 1; i > 0; i--) {
      Node n = path.get(i);
      Node next = path.get(i - 1);
      if (n.getFloor().equals(floor)) {
        if (!next.getFloor().equals(n.getFloor())) {
          fpath.add(new Pair<>(n, true));
          if (whichFloorI(next.getFloor()) < whichFloorI(n.getFloor())) {
            int crossFloors = whichFloorI(n.getFloor()) - whichFloorI(next.getFloor());
            for (int j = 0; j < cfnodes.size(); j++) {
              Integer nodeid = cfnodes.get(j).getKey();
              if (nodeid == n.getNodeID()) {
                int index = cfnodes.get(j).getValue();
                Triple<Integer, Integer, Integer> triple = Triple.of(nodeid, index, -crossFloors);
                cfpath.add(triple);
              }
            }
          } else {
            int crossFloors = whichFloorI(next.getFloor()) - whichFloorI(n.getFloor());
            for (int j = 0; j < cfnodes.size(); j++) {
              Integer nodeid = cfnodes.get(j).getKey();
              if (nodeid == n.getNodeID()) {
                int index = cfnodes.get(j).getValue();
                Triple<Integer, Integer, Integer> triple = Triple.of(nodeid, index, crossFloors);
                cfpath.add(triple);
              }
            }
          }
        } else {
          fpath.add(new Pair<>(n, false));
          for (int j = 0; j < cfnodes.size(); j++) {
            Integer nodeid = cfnodes.get(j).getKey();
            if (nodeid == n.getNodeID()) {
              int index = cfnodes.get(j).getValue();
              Triple<Integer, Integer, Integer> triple = Triple.of(nodeid, index, 0);
              cfpath.add(triple);
            }
          }
        }
      }
    }

    for (int i = 0; i < path.size() - 1; i++) {
      Node n = path.get(i);
      Node next = path.get(i + 1);
      if (n.getFloor().equals(floor)) {
        if (!next.getFloor().equals(n.getFloor())) {
          if (whichFloorI(next.getFloor()) < whichFloorI(n.getFloor())) {
            int crossFloors = whichFloorI(n.getFloor()) - whichFloorI(next.getFloor());
            for (int j = 0; j < cfnodes.size(); j++) {
              Integer nodeid = cfnodes.get(j).getKey();
              if (nodeid == n.getNodeID()) {
                int index = cfnodes.get(j).getValue();
                Triple<Integer, Integer, Integer> triple = Triple.of(nodeid, index, -crossFloors);
                cfpath.add(triple);
              }
            }
          } else {
            int crossFloors = whichFloorI(next.getFloor()) - whichFloorI(n.getFloor());
            for (int j = 0; j < cfnodes.size(); j++) {
              Integer nodeid = cfnodes.get(j).getKey();
              if (nodeid == n.getNodeID()) {
                int index = cfnodes.get(j).getValue();
                Triple<Integer, Integer, Integer> triple = Triple.of(nodeid, index, crossFloors);
                cfpath.add(triple);
              }
            }
          }
        } else {
          fpath.add(new Pair<>(n, false));
          for (int j = 0; j < cfnodes.size(); j++) {
            Integer nodeid = cfnodes.get(j).getKey();
            if (nodeid == n.getNodeID()) {
              int index = cfnodes.get(j).getValue();
              Triple<Integer, Integer, Integer> triple = Triple.of(nodeid, index, 0);
              cfpath.add(triple);
            }
          }
        }
      }
    }

    for (Triple<Integer, Integer, Integer> tp : cfpath) {
      ImageView image = new ImageView();
      Integer idx = tp.getMiddle();
      Button node = (Button) parent.getChildren().get(idx); // problem here
      Integer move = tp.getRight();
      if (move > 0 && !(node.equals(start) || node.equals(target))) {
        node.setDisable(false);
        node.setStyle("-fx-background-color: yellow;" + "-fx-background-insets: 0px;");
        image.setImage(new Image("/Up.png"));
        image.fitWidthProperty().bind(node.widthProperty());
        image.fitHeightProperty().bind(node.heightProperty());
        node.setGraphic(image);
        node.toFront();
        highlightedNodes.add(
            Triple.of(
                idx,
                whichFloorI(floor),
                move)); // unhighlightednodes and removeall before every drawlinesf
        node.setOnAction(
            e -> {
              try {
                for (int j = 0; j < Math.abs(move); j++) {
                  nextFloorClicked();
                }
              } catch (IOException ex) {
                throw new RuntimeException(ex);
              }
            });
      }
      if (move < 0 && !(node.equals(start) || node.equals(target))) {
        node.setDisable(false);
        node.setStyle("-fx-background-color: yellow;" + "-fx-background-insets: 0px;");
        image.setImage(new Image("/Down.png"));
        image.fitWidthProperty().bind(node.widthProperty());
        image.fitHeightProperty().bind(node.heightProperty());
        node.setGraphic(image);
        node.toFront();
        highlightedNodes.add(Triple.of(idx, whichFloorI(floor), move)); // whichFloorI(floor)
        node.setOnAction(
            e -> {
              try {
                for (int j = 0; j < Math.abs(move); j++) {
                  previousFloorClicked();
                }
              } catch (IOException ex) {
                throw new RuntimeException(ex);
              }
            });
      }
    }

    List<Line> lines = new ArrayList<>();
    if (fpath.size() > 0) {
      lines = addLines(fpath);
      if (lines.size() > 0 && floor.equals(path.get(0).getFloor())) {
        Line sl = lines.get(0);
        Line el = lines.get(lines.size() - 1);
        double slx = sl.getStartX();
        double sly = sl.getStartY();
        double elx = el.getEndX();
        double ely = el.getEndY();
        double ax = Math.abs(slx + elx) / 2;
        double ay = Math.abs(sly + ely) / 2 - 10;
        if (messageField != null) {
          messageText = new Text(ax, ay, messageField.getText());
          messageText.setFill(Color.RED);
          messageText.setStyle("-fx-font-size: 8px;");
          parent.getChildren().add(messageText);
        }
      }
    }

    if (path.size() == 0) {
      alert.alertBox("No solution", "Failed to find a path");
    }

    textualPathfinding.setText(toString(path));
    Text tempText = new Text(textualPathfinding.getText());
    tempText.setFont(textualPathfinding.getFont());
    double prefHeight = tempText.getLayoutBounds().getHeight();
    textualPathfinding.setMaxHeight(313.3);
    textualPathfinding.setPrefHeight(prefHeight);
    return lines;
  }

  public String toString(List<Node> path) {
    String direction = "";
    direction +=
        "Floor " + path.get(0).getFloor() + ": " + "\n" + path.get(0).getLocation().getLongName();
    for (int i = 1; i < path.size(); i++) {
      Node n = path.get(i);
      Node previous = path.get(i - 1);
      if (!previous.getFloor().equals(n.getFloor())) {
        direction +=
            " -> " + "\n\nFloor " + n.getFloor() + ": " + "\n" + n.getLocation().getLongName();
      } else {
        direction += " -> \n" + n.getLocation().getLongName();
      }
    }
    return direction;
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
    if (floor == 1) {
      previousFloor.setDisable(true);
    }
    if (nextFloor.isDisable()) {
      nextFloor.setDisable(false);
    }

    if (highlightedNodes.size() > 0) {
      for (int j = 0; j < highlightedNodes.size(); j++) {
        if (highlightedNodes.get(j).getMiddle() == floor) {
          unhighlight(highlightedNodes.get(j).getLeft());
        }
      }
    }
    if (highlightedNodesp.size() > 0) {
      for (int j = 0; j < highlightedNodesp.size(); j++) {
        if (highlightedNodesp.get(j).getMiddle() == floor) {
          unhighlight(highlightedNodesp.get(j).getLeft());
        }
      }
    }

    if (floor > 0) {
      floor--;
      f = whichFloorS();
    }
    Image previous = floors.get(floor);
    map.setImage(previous);

    // if on the same floor
    if (highlightedNodes.size() > 0) {
      for (int i = 0; i < highlightedNodes.size(); i++) {
        if (highlightedNodes.get(i).getMiddle() == floor) {
          highlighte(highlightedNodes.get(i).getLeft(), highlightedNodes.get(i).getRight());
        }
      }
    }
    if (highlightedNodesp.size() > 0) {
      for (int i = 0; i < highlightedNodesp.size(); i++) {
        if (highlightedNodesp.get(i).getMiddle() == floor) {
          highlight(highlightedNodesp.get(i).getLeft(), "red");
        }
      }
    }

    removeText();
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
    if (!ready4Second && start != null && target != null) {
      highlightedNodes.removeAll(highlightedNodes);
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

    if (highlightedNodes.size() > 0) {
      for (int j = 0; j < highlightedNodes.size(); j++) {
        if (highlightedNodes.get(j).getMiddle() == floor) {
          unhighlight(highlightedNodes.get(j).getLeft());
        }
      }
    }
    if (highlightedNodesp.size() > 0) {
      for (int j = 0; j < highlightedNodesp.size(); j++) {
        if (highlightedNodesp.get(j).getMiddle() == floor) {
          unhighlight(highlightedNodesp.get(j).getLeft());
        }
      }
    }

    if (floor < 4) {
      floor++;
      f = whichFloorS();
    }

    Image next = floors.get(floor);
    map.setImage(next);

    removeText();
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
        if (highlightedNodes.get(i).getMiddle() == floor) {
          highlighte(highlightedNodes.get(i).getLeft(), highlightedNodes.get(i).getRight());
        }
      }
    }
    if (highlightedNodesp.size() > 0) {
      for (int i = 0; i < highlightedNodesp.size(); i++) {
        if (highlightedNodesp.get(i).getMiddle() == floor) {
          highlight(highlightedNodesp.get(i).getLeft(), "red");
        }
      }
    }

    if (!ready4Second && start != null && target != null) { // && previousPath.size() > 0
      highlightedNodes.removeAll(highlightedNodes);
      previousPath = drawLinesf(start, target, f);
    }
  }

  public void highlightt(List<Integer> nodes, String color) {
    ObservableList<javafx.scene.Node> children = parent.getChildren();
    for (int i : nodes) {
      Text child = (Text) children.get(i);
      switch (color) {
        case "lightgreen":
          child.setFill(Color.LIGHTGREEN);
          break;
        case "orange":
          child.setFill(Color.ORANGE);
          break;
        case "purple":
          child.setFill(Color.PURPLE);
          break;
        case "gray":
          child.setFill(Color.GRAY);
          break;
        case "yellow":
          child.setFill(Color.YELLOW);
          break;
        case "brown":
          child.setFill(Color.BROWN);
          break;
        case "pink":
          child.setFill(Color.PINK);
          break;
      }
    }
  }

  public void highlight(int node, String color) {
    String border = "-fx-border-color: ";
    border = border + color + ";";
    ObservableList<javafx.scene.Node> children = parent.getChildren();
    Button child = (Button) children.get(node);
    child.setDisable(false);
    child.setStyle("-fx-background-color: lightblue;" + "-fx-background-insets: 0px;" + border);
  }

  public void highlighte(int node, int move) {
    ObservableList<javafx.scene.Node> children = parent.getChildren();
    Button child = (Button) children.get(node);
    ImageView image = new ImageView();
    child.setDisable(false);
    child.setStyle("-fx-background-color: yellow;" + "-fx-background-insets: 0px;");
    if (move < 0) {
      image.setImage(new Image("/Down.png"));
      child.setOnAction(
          e -> {
            try {
              for (int j = 0; j < Math.abs(move); j++) {
                previousFloorClicked();
              }
            } catch (IOException ex) {
              throw new RuntimeException(ex);
            }
          });
    } else {
      image.setImage(new Image("/Up.png"));
      child.setOnAction(
          e -> {
            try {
              for (int j = 0; j < Math.abs(move); j++) {
                nextFloorClicked();
              }
            } catch (IOException ex) {
              throw new RuntimeException(ex);
            }
          });
    }
    image.fitWidthProperty().bind(child.widthProperty());
    image.fitHeightProperty().bind(child.heightProperty());
    child.setGraphic(image);
    child.toFront();
  }

  public void unhighlightt(List<Integer> nodes) {
    ObservableList<javafx.scene.Node> children = parent.getChildren();
    for (int i : nodes) {
      Text child = (Text) children.get(i);
      child.setFill(Color.BLUE);
    }
  }

  public void unhighlight(int node) {
    ObservableList<javafx.scene.Node> children = parent.getChildren();
    Button child = (Button) children.get(node);
    child.setStyle(
        "-fx-background-color: lightblue;"
            + "-fx-background-insets: 0px;"
            + "-fx-border-color: black;");
    child.setStyle("-fx-background-color: transparent;");
    child.setDisable(true);
    child.setGraphic(null);
  }

  public void restChecked() {
    typeChecked(restCheck, restText, "lightgreen");
  }

  public void deptChecked() {
    typeChecked(deptCheck, deptText, "orange");
  }

  public void labsChecked() {
    typeChecked(labsCheck, labsText, "purple");
  }

  public void infoChecked() {
    typeChecked(infoCheck, infoText, "gray");
  }

  public void confChecked() {
    typeChecked(confCheck, confText, "yellow");
  }

  public void retlChecked() {
    typeChecked(retlCheck, retlText, "brown");
  }

  public void servChecked() {
    typeChecked(servCheck, servText, "pink");
  }

  public void typeChecked(CheckBox check, List<Integer> nodes, String color) {
    if (check.isSelected()) {
      highlightt(nodes, color);
    } else {
      unhighlightt(nodes);
    }
  }

  public void startSelected() throws IOException {
    List<Pair<Integer, Integer>> cfnodes = new ArrayList<>();
    String lname = startSelect.getValue();
    if (lname != null && !lname.equals("")) {
      int index = startSelect.getSelectionModel().getSelectedIndex();
      int nodeid = nodeIds.get(index);
      Node n = qdb.retrieveNode(nodeid);
      String nodeFloor = n.getFloor();
      int f = whichFloorI(nodeFloor);
      int crossFloors = Math.abs(f - floor);
      if (!ready4Second) {
        ready4Second = true;
        start = n;
        removeLines(previousPath);
      }

      if (highlightedNodes.size() > 0) {
        for (int j = 0; j < highlightedNodes.size(); j++) {
          if (highlightedNodes.get(j).getMiddle() == floor) {
            unhighlight(highlightedNodes.get(j).getLeft());
          }
        }
        highlightedNodes.removeAll(highlightedNodes);
      }
      if (highlightedNodesp.size() > 0) {
        for (int j = 0; j < highlightedNodesp.size(); j++) {
          if (highlightedNodesp.get(j).getMiddle() == floor) {
            unhighlight(highlightedNodesp.get(j).getLeft());
          }
        }
        highlightedNodesp.removeAll(highlightedNodesp);
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
          int ind = cfnodes.get(i).getValue();
          javafx.scene.Node node = parent.getChildren().get(ind);
          node.setDisable(false);
          node.setStyle(
              "-fx-background-color: lightblue;"
                  + "-fx-border-color: black;"
                  + "-fx-background-insets: 0px;");
          highlight(cfnodes.get(i).getValue(), "red"); // button
          highlightedNodesp.add(
              Triple.of(
                  cfnodes.get(i).getValue(),
                  floor,
                  0)); // int index = parent.getChildren().indexOf(node);
        }
      }
    }
  }

  public void endSelected() throws IOException {
    List<Pair<Integer, Integer>> cfnodes = new ArrayList<>();
    String f = whichFloorS();
    String lname = endSelect.getValue();
    if (lname != null && !lname.equals("")) {
      int index = endSelect.getSelectionModel().getSelectedIndex();
      int nodeid = nodeIds.get(index);
      Node n = qdb.retrieveNode(nodeid);
      String nodeFloor = n.getFloor();
      int nodef = whichFloorI(nodeFloor);
      int crossFloors = Math.abs(nodef - floor);
      if (ready4Second) {
        target = n;
        ready4Second = false;
        removeLines(previousPath);
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
          int ind = cfnodes.get(i).getValue();
          javafx.scene.Node node = parent.getChildren().get(ind);
          node.setDisable(false);
          node.setStyle(
              "-fx-background-color: lightblue;"
                  + "-fx-border-color: black;"
                  + "-fx-background-insets: 0px;");
          highlight(cfnodes.get(i).getValue(), "red"); // button
          highlightedNodesp.add(
              Triple.of(
                  cfnodes.get(i).getValue(),
                  floor,
                  0)); // int index = parent.getChildren().indexOf(node);
        }
      }
    }
  }

  public void aStarSelected() {
    if (aStarSelect.isSelected()) {
      clearButtonClicked();
      algorithm = "aStar";
    }
  }

  public void bfsSelected() {
    if (bfsSelect.isSelected()) {
      clearButtonClicked();
      algorithm = "bfs";
    }
  }

  public void dfsSelected() {
    if (dfsSelect.isSelected()) {
      clearButtonClicked();
      algorithm = "dfs";
    }
  }

  public void djikstraSelected() {
    if (djikstraSelect.isSelected()) {
      clearButtonClicked();
      algorithm = "djikstra";
    }
  }

  public void dateSelected(RadioMenuItem itemSelect) {
    if (itemSelect.isSelected()) {
      clearButtonClicked();
      date = Date.valueOf(itemSelect.getText());
      refresh();
    }
  }

  public void refresh() {
    String f = whichFloorS();
    removeText();
    removeButtons();
    addButtons(f);
  }

  public void clearButtonClicked() {
    List<Pair<Integer, Integer>> cfnodes = new ArrayList<>();
    textualPathfinding.setText(null);
    textualPathfinding.setPrefHeight(Region.USE_COMPUTED_SIZE);
    removeLines(previousPath);
    start = null;
    target = null;
    restCheck.setSelected(false);
    deptCheck.setSelected(false);
    labsCheck.setSelected(false);
    infoCheck.setSelected(false);
    confCheck.setSelected(false);
    retlCheck.setSelected(false);
    servCheck.setSelected(false);
    cfnodes = setCF(cfnodes);
    for (int i = 0; i < cfnodes.size(); i++) {
      unhighlight(cfnodes.get(i).getValue()); // + 1
    }
    highlightedNodes.removeAll(highlightedNodes);
    startSelect.setValue(null);
    endSelect.setValue(null);
  }
}
