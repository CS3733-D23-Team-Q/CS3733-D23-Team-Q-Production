package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.Alert;
import edu.wpi.cs3733.D23.teamQ.Pathfinding.*;
import edu.wpi.cs3733.D23.teamQ.db.Qdb;
import edu.wpi.cs3733.D23.teamQ.db.obj.Location;
import edu.wpi.cs3733.D23.teamQ.db.obj.Move;
import edu.wpi.cs3733.D23.teamQ.db.obj.Node;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXFilterComboBox;
import io.github.palexdev.materialfx.controls.MFXScrollPane;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.animation.Interpolator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.util.Duration;
import javafx.util.Pair;
import net.kurobako.gesturefx.*;
import org.apache.commons.lang3.tuple.Triple;

public class PathfindingController {
  String username = LoginController.getUsername();
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
  String algorithm;
  Date date;
  List<Date> moveDates;
  List<Node> startNodes;
  ToggleGroup dateToggle;
  List<Triple<Integer, Button, Integer>> cfpath;
  Text messageText;
  List<Image> directions;
  List<Node> current;
  static List<Node> latest = new ArrayList<>();
  Location defaultsl;

  ObservableList<String> algorithmsList =
      FXCollections.observableArrayList("aStar", "bfs", "dfs", "djikstra");

  @FXML GridPane root;
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
  @FXML MFXFilterComboBox<String> startSelect;
  @FXML MFXFilterComboBox<String> endSelect;
  @FXML MFXComboBox<String> dateSelect;
  @FXML MFXComboBox<String> algorithmSelect;
  @FXML TextField messageField;
  @FXML MFXScrollPane textualPathfinding;
  @FXML VBox textArea;
  @FXML Label floorLabel;

  @FXML
  public void initialize() throws IOException {
    defaultsl = null;
    if (qdb.getDefaultLocationIndex(username) != -1) {
      defaultsl = qdb.retrieveDefaultLocation(username).getStartingLocation();
    }
    current = new ArrayList<>();
    Image bottomleft = new Image("/Bottom-Left.png");
    Image bottomright = new Image("/Bottom-Right.png");
    Image down = new Image("/Down.png");
    Image left = new Image("/Left.png");
    Image right = new Image("/Right.png");
    Image topleft = new Image("/Top-Left.png");
    Image topright = new Image("/Top-Right.png");
    Image up = new Image("/Up.png");
    directions = new ArrayList<>();
    directions.add(right);
    directions.add(left);
    directions.add(up);
    directions.add(down);
    directions.add(topright);
    directions.add(bottomright);
    directions.add(topleft);
    directions.add(bottomleft);
    cfpath = new ArrayList<>();
    dateToggle = new ToggleGroup();
    date = getLatestDate();
    moveDates = new ArrayList<>();
    startNodes = new ArrayList<>();

    switch (qdb.retrieveSettings(username).getAlgorithm().ordinal()) {
      case 0:
        algorithm = "aStar";
        pathfindingAlgorithmSelection.setPathfindingAlgorithm(aStar);
      case 1:
        algorithm = "dfs";
        pathfindingAlgorithmSelection.setPathfindingAlgorithm(dfs);
      case 2:
        algorithm = "bfs";
        pathfindingAlgorithmSelection.setPathfindingAlgorithm(bfs);
      case 3:
        algorithm = "djikstra";
        pathfindingAlgorithmSelection.setPathfindingAlgorithm(djikstra);
    }

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
    previousPath = new ArrayList<>();
    floor = 2;
    floorLabel.setText("Floor " + whichFloorS());
    ready4Second = false;
    if (defaultsl != null) {
      getLatestNodesb();
      List<Node> latestNodes = latest;
      for (Node n : latestNodes) {
        if (n.getLocation().equals(defaultsl)) {
          int f = whichFloorI(n.getFloor());
          int crossFloors = Math.abs(f - floor);
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
        }
      }
    } else {
      addButtons(whichFloorS());
    }
    pane = new GesturePane();
    pane.setContent(parent);
    pane.setFitMode(GesturePane.FitMode.COVER);
    root.add(pane, 0, 0);
    GridPane.setRowSpan(pane, GridPane.REMAINING);
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
    setUpAlgos();
  }

  public void setUpAlgos() {
    System.out.println(algorithm);
    algorithmSelect.setText(algorithm);
    algorithmSelect.setItems(algorithmsList);
  }

  public Date getLatestDate() {
    List<Move> allMoves = qdb.retrieveAllMoves();
    Date d1 = allMoves.get(0).getDate();
    for (int i = 1; i < allMoves.size(); i++) {
      Date d2 = allMoves.get(i).getDate();
      if (d2.after(d1)) {
        d1 = d2;
      }
    }
    return d1;
  }

  public void addButtons(String f) {
    // List<Node> allNodes = qdb.retrieveAllNodes();
    List<Move> allMoves = qdb.retrieveAllMoves();
    List<Node> floorNodes = new ArrayList<>();
    List<Move> dateMoves = new ArrayList<>();
    List<Node> moveNodes = new ArrayList<>();
    List<Node> currentNodes = new ArrayList<>();
    List<Node> nodeswchanges = new ArrayList<>();

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

    startNodes.removeAll(startNodes);

    nodeIds.removeAll(nodeIds);
    startSelect.getItems().clear();
    endSelect.getItems().clear();

    // int k = 0;
    for (Move m : allMoves) {
      // k++;
      Date d = m.getDate();
      if (d.compareTo(Date.valueOf("2023-01-01")) == 0) {
        Node startn =
            new Node(
                m.getNode().getNodeID(),
                m.getNode().getXCoord(),
                m.getNode().getYCoord(),
                m.getNode().getFloor(),
                m.getNode().getBuilding(),
                m.getNode().getLocation()); // Node startn = m.getNode();

        /*
        //if (k >= 200) {
            startn.setLocation(qdb.getNodeFromLocation(m.getLongName()).getLocation());
        //}
        */

        List<Node> nodes = qdb.retrieveAllNodes();
        for (Node n : nodes) {
          if (n.getLocation().getLongName().equals(m.getLongName())) {
            startn.setLocation(n.getLocation());
            break;
          }
        }
        startNodes.add(startn);
      }

      if (d.compareTo(date) == 0) {
        dateMoves.add(m);
      }
      if (!moveDates.contains(d)) {
        moveDates.add(d);
        dateSelect.getItems().add(d.toString());
      }
    }

    for (Move m : dateMoves) {
      Node moven =
          new Node(
              m.getNode().getNodeID(),
              m.getNode().getXCoord(),
              m.getNode().getYCoord(),
              m.getNode().getFloor(),
              m.getNode().getBuilding(),
              m.getNode().getLocation()); // Node moven = m.getNode();

      /*
      // System.out.println(m.getLongName() + "move before");
      moven.setLocation(qdb.getNodeFromLocation(m.getLongName()).getLocation());
      // System.out.println(moven.getLocation().getLongName() + "move after");
      */

      List<Node> nodes = qdb.retrieveAllNodes();
      for (Node n : nodes) {
        if (n.getLocation().getLongName().equals(m.getLongName())) {
          moven.setLocation(n.getLocation());
          break;
        }
      }
      moveNodes.add(moven);
    }

    Collections.sort(
        moveDates,
        new Comparator<Date>() {
          public int compare(Date date1, Date date2) {
            return date1.compareTo(date2);
          }
        });
    List<Date> dateswofirst = new ArrayList<>();
    dateswofirst.addAll(moveDates);
    dateswofirst.remove(0);
    List<Node> previousNodesm = new ArrayList<>();
    for (Date movedate : dateswofirst) {
      if (movedate.before(date)) {
        previousNodesm = previousChange(movedate, previousNodesm);
      }
    }

    // moveNodes = previousChange(date, previousNodesm);

    nodeswchanges.addAll(moveNodes);

    if (previousNodesm.size() > 0) {
      for (Node node : previousNodesm) {
        boolean add = true;
        for (Node moven : moveNodes) {
          if (node.getLocation()
              .equals(moven.getLocation())) { // !(node.getNodeID() == moven.getNodeID() ||
            // node.getLocation().equals(moven.getLocation()))
            add = false;
          }
        }
        if (add) {
          nodeswchanges.add(node);
        }
      }
    }

    currentNodes.addAll(nodeswchanges);

    if (date.compareTo(Date.valueOf("2023-01-01")) != 0) {
      for (Node node : startNodes) {
        boolean add = true;
        for (Node moven : nodeswchanges) { // Move m : dateMoves
          if (node.getLocation()
              .equals(moven.getLocation())) { // !(node.getNodeID() == moven.getNodeID() ||
            // node.getLocation().equals(moven.getLocation()))
            add = false;
          }
        }
        if (add) {
          currentNodes.add(node);
        }
      }
    } else {
      currentNodes = startNodes;
    }

    current = currentNodes;
    if (date.compareTo(getLatestDate()) == 0) {
      latest = currentNodes;
    }

    for (int i = 0; i < currentNodes.size(); i++) { // Node n : nodes/startNodes/moveNodes
      Node n = currentNodes.get(i); // startNodes/moveNodes
      int nodeid = n.getNodeID();
      Location location = n.getLocation(); // qdb.retrieveLocation(nodeid);
      String nodetype = location.getNodeType();
      String lname = location.getLongName();
      if (n.getFloor().equals(f)) {
        floorNodes.add(n);
      }
      if (!nodetype.equals("HALL")
          && !nodetype.equals("ELEV")
          && !nodetype.equals("STAI")) { // short names were not changed
        nodeIds.add(nodeid);
        startSelect.getItems().add(lname);
        endSelect.getItems().add(lname);
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
      node.setStyle("-fx-border-color: black;" + "-fx-background-insets: 0px;");
      node.setStyle("-fx-background-color: transparent;");
      node.setDisable(true);
      parent.getChildren().add(node);
      node.toFront();
      if (defaultsl != null && highlightedNodesp.size() == 0) {
        if (n.getLocation().equals(defaultsl)) {
          System.out.println("floor" + floor);
          start = n;
          ready4Second = true;
          highlight(node, "red");
          highlightedNodesp.add(0, Triple.of(node, floor, nodeid));
        }
      }
      previousNodes.add(node);
      switch (f) {
        case "L1":
          l1nodes.add(new Pair<>(nodeid, node));
          break;
        case "L2":
          l2nodes.add(new Pair<>(nodeid, node));
          break;
        case "1":
          ffnodes.add(new Pair<>(nodeid, node));
          break;
        case "2":
          sfnodes.add(new Pair<>(nodeid, node));
          break;
        case "3":
          tfnodes.add(new Pair<>(nodeid, node));
          break;
      }
    }

    for (Node n : floorNodes) {
      double x = n.getXCoord() / 5 - 1.5;
      double y = n.getYCoord() / 5 - 1.5;
      int nodeid = n.getNodeID();
      Location location = n.getLocation(); // qdb.retrieveLocation(nodeid);
      String sname = location.getShortName();
      // String lname = location.getLongName();
      String nodetype = location.getNodeType();
      if (!nodetype.equals("HALL")
          && !nodetype.equals("ELEV")
          && !nodetype.equals("STAI")) { // short names were not changed
        text = new Text(x, y, sname);
        text.setFill(Color.BLUE);
        text.setStyle("-fx-font-size: 3px;");
        // parent.getChildren().add(text);
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

  public List<Node> previousChange(Date datein, List<Node> previousNodesm) {
    List<Move> allMoves = qdb.retrieveAllMoves();
    List<Move> dateMoves = new ArrayList<>();
    List<Node> moveNodes = new ArrayList<>();
    for (Move m : allMoves) {
      Date d = m.getDate();
      if (d.compareTo(datein) == 0) {
        dateMoves.add(m);
      }
    }
    for (Move m : dateMoves) {
      Node moven =
          new Node(
              m.getNode().getNodeID(),
              m.getNode().getXCoord(),
              m.getNode().getYCoord(),
              m.getNode().getFloor(),
              m.getNode().getBuilding(),
              m.getNode().getLocation());
      List<Node> nodes = qdb.retrieveAllNodes();
      for (Node n : nodes) {
        if (n.getLocation().getLongName().equals(m.getLongName())) {
          moven.setLocation(n.getLocation());
          break;
        }
      }
      moveNodes.add(moven);
    }
    List<Node> currentNodes = new ArrayList<>();
    currentNodes.addAll(moveNodes);
    if (previousNodesm.size() > 0) {
      for (Node node : previousNodesm) {
        boolean add = true;
        for (Node moven : moveNodes) {
          if (!node.getLocation()
              .equals(moven.getLocation())) { // !(node.getNodeID() == moven.getNodeID() ||
            // node.getLocation().equals(moven.getLocation()))
            add = false;
          }
        }
        if (add) {
          currentNodes.add(node);
        }
      }
    }
    return currentNodes;
  }

  public List<Pair<Integer, Text>> addSpecificNode(
      String pattern, String input, List<Pair<Integer, Text>> nodes, Text node, int nodeid) {
    Pattern pattern1 = Pattern.compile(pattern);
    Matcher matcher1 = pattern1.matcher(input);
    if (matcher1.find()) {
      nodes.add(new Pair<>(nodeid, node));
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
    List<Pair<Integer, Button>> cfnodes = new ArrayList<>();
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
                Button index = cfnodes.get(j).getValue();
                Triple<Integer, Button, Integer> triple = Triple.of(nodeid, index, -crossFloors);
                cfpath.add(triple);
              }
            }
          } else {
            int crossFloors = whichFloorI(next.getFloor()) - whichFloorI(n.getFloor());
            for (int j = 0; j < cfnodes.size(); j++) {
              Integer nodeid = cfnodes.get(j).getKey();
              if (nodeid == n.getNodeID()) {
                Button index = cfnodes.get(j).getValue();
                Triple<Integer, Button, Integer> triple = Triple.of(nodeid, index, crossFloors);
                cfpath.add(triple);
              }
            }
          }
        } else {
          fpath.add(new Pair<>(n, false));
          for (int j = 0; j < cfnodes.size(); j++) {
            Integer nodeid = cfnodes.get(j).getKey();
            if (nodeid == n.getNodeID()) {
              Button index = cfnodes.get(j).getValue();
              Triple<Integer, Button, Integer> triple = Triple.of(nodeid, index, 0);
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
                Button index = cfnodes.get(j).getValue();
                Triple<Integer, Button, Integer> triple = Triple.of(nodeid, index, -crossFloors);
                cfpath.add(triple);
              }
            }
          } else {
            int crossFloors = whichFloorI(next.getFloor()) - whichFloorI(n.getFloor());
            for (int j = 0; j < cfnodes.size(); j++) {
              Integer nodeid = cfnodes.get(j).getKey();
              if (nodeid == n.getNodeID()) {
                Button index = cfnodes.get(j).getValue();
                Triple<Integer, Button, Integer> triple = Triple.of(nodeid, index, crossFloors);
                cfpath.add(triple);
              }
            }
          }
        } else {
          fpath.add(new Pair<>(n, false));
          for (int j = 0; j < cfnodes.size(); j++) {
            Integer nodeid = cfnodes.get(j).getKey();
            if (nodeid == n.getNodeID()) {
              Button index = cfnodes.get(j).getValue();
              Triple<Integer, Button, Integer> triple = Triple.of(nodeid, index, 0);
              cfpath.add(triple);
            }
          }
        }
      }
    }

    for (Triple<Integer, Button, Integer> tp : cfpath) {
      ImageView image = new ImageView();
      Button node = tp.getMiddle();
      Integer move = tp.getRight();
      if (move > 0) {
        node.setDisable(false);
        node.setStyle("-fx-background-color: yellow;" + "-fx-background-insets: 0px;");
        image.setImage(new Image("/Up - elev.png"));
        image.fitWidthProperty().bind(node.widthProperty());
        image.fitHeightProperty().bind(node.heightProperty());
        node.setGraphic(image);
        node.toFront();
        highlightedNodes.add(
            Triple.of(
                node,
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
      if (move < 0) {
        node.setDisable(false);
        node.setStyle("-fx-background-color: yellow;" + "-fx-background-insets: 0px;");
        image.setImage(new Image("/Down - elev.png"));
        image.fitWidthProperty().bind(node.widthProperty());
        image.fitHeightProperty().bind(node.heightProperty());
        node.setGraphic(image);
        node.toFront();
        highlightedNodes.add(Triple.of(node, whichFloorI(floor), move)); // whichFloorI(floor)
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

    parent.getChildren().remove(messageText);
    List<Line> lines = new ArrayList<>();
    if (fpath.size() > 0) {
      lines = addLines(fpath);
      if (lines.size() > 0 && floor.equals(fpath.get(0).getKey().getFloor())) {
        Line sl = lines.get(0);
        Line el = lines.get(lines.size() - 1);
        double slx = sl.getStartX();
        double sly = sl.getStartY();
        double elx = el.getEndX();
        double ely = el.getEndY();
        double ax = Math.abs(slx + elx) / 2 + 5;
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
    if (path.size() > 0) {
      clearTextualPathfinding();
      displayTextPF(path);
    }
    return lines;
  }

  public int measureDirections(Node n, Node next) {
    String direction = "";
    int arrow = 0;
    int dx = next.getXCoord() - n.getXCoord();
    int dy = next.getYCoord() - n.getYCoord();
    if (dx > 5) {
      direction += "right";
    } else if (dx < -5) {
      direction += "left";
    }
    if (dy > 5) {
      direction += "down";
    } else if (dy < -5) {
      direction += "up";
    }
    switch (direction) {
      case "right":
        arrow = 0;
        break;
      case "left":
        arrow = 1;
        break;
      case "up":
        arrow = 2;
        break;
      case "down":
        arrow = 3;
        break;
      case "rightup":
        arrow = 4;
        break;
      case "rightdown":
        arrow = 5;
        break;
      case "leftup":
        arrow = 6;
        break;
      case "leftdown":
        arrow = 7;
        break;
    }
    return arrow;
  }

  public String textDirections(int index) {
    String direction = "";
    switch (index) {
      case 0:
        direction = "Turn right to ";
        break;
      case 1:
        direction = "Turn left to ";
        break;
      case 2:
        direction = "Go straight up to ";
        break;
      case 3:
        direction = "Head down to ";
        break;
      case 4:
        direction = "Turn right up to ";
        break;
      case 5:
        direction = "Turn right down to ";
        break;
      case 6:
        direction = "Turn left up to ";
        break;
      case 7:
        direction = "Turn left down to ";
        break;
    }
    return direction;
  }

  public List<Node> nameConversion(List<Node> path) {
    for (Node node : path) {
      for (Node n : current) {
        if (node.getNodeID() == n.getNodeID()) {
          node.setLocation(n.getLocation());
        }
      }
    }
    return path;
  }

  public void displayTextPF(List<Node> path) {
    path = nameConversion(path);
    String direction = "";
    direction = "Floor " + path.get(path.size() - 1).getFloor() + ": ";
    Label text = new Label(direction);
    VBox.setMargin(text, new Insets(10, 0, 0, 0));
    textArea.getChildren().add(text);
    direction = path.get(path.size() - 1).getLocation().getLongName();
    for (int i = path.size() - 2; i >= 0; i--) {
      Node n = path.get(i);
      Node previous = path.get(i + 1);
      int index = 0;
      if (i != 0) {
        index = measureDirections(path.get(i), path.get(i - 1));
      }
      if (!previous.getFloor().equals(n.getFloor())) {
        if (i == path.size() - 2) {
          text = new Label(direction);
          ImageView icon = new ImageView(new Image("/Start.png"));
          icon.setFitWidth(22);
          icon.setFitHeight(29);
          text.setGraphic(icon);
        } else {
          text = new Label(textDirections(index) + direction);
          ImageView icon = new ImageView(directions.get(index));
          icon.setFitWidth(22);
          icon.setFitHeight(29);
          text.setGraphic(icon);
        }
        textArea.getChildren().add(text);
        text = new Label();
        textArea.getChildren().add(text);
        direction = "Floor " + n.getFloor() + ": ";
        text = new Label(direction);
        textArea.getChildren().add(text);
        direction = n.getLocation().getLongName();
      } else {
        if (i == path.size() - 2) {
          text = new Label(direction);
          ImageView icon = new ImageView(new Image("/Start.png"));
          icon.setFitWidth(22);
          icon.setFitHeight(29);
          text.setGraphic(icon);
        } else {
          text = new Label(textDirections(index) + direction);
          ImageView icon = new ImageView(directions.get(index));
          icon.setFitWidth(22);
          icon.setFitHeight(29);
          text.setGraphic(icon);
        }
        textArea.getChildren().add(text);
        direction = n.getLocation().getLongName();
      }
    }
    text = new Label(direction);
    int index = measureDirections(path.get(1), path.get(0));
    ImageView icon = new ImageView(directions.get(index));
    icon.setFitWidth(22);
    icon.setFitHeight(29);
    text.setGraphic(icon);
    HBox hbox = new HBox();
    hbox.setSpacing(5);
    hbox.getChildren().add(text);
    icon = new ImageView(new Image("/Target.png"));
    icon.setFitWidth(22);
    icon.setFitHeight(29);
    hbox.getChildren().add(icon);
    textArea.getChildren().add(hbox);
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

  public List<Pair<Integer, Button>> setCF(List<Pair<Integer, Button>> cfnodes) {
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
    List<Pair<Integer, Button>> cfnodes = new ArrayList<>();
    String f = "";
    // parent.getChildren().remove(messageText);
    if (floor == 1) {
      previousFloor.setDisable(true);
    }
    if (nextFloor.isDisable()) {
      nextFloor.setDisable(false);
    }

    if (floor > 0) {
      floor--;
      f = whichFloorS();
      System.out.println(floor + f);
      floorLabel.setText("Floor " + whichFloorS());
    }
    Image previous = floors.get(floor);
    map.setImage(previous);

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
    System.out.println(highlightedNodesp.size());
    // if on the same floor
    if (highlightedNodes.size() > 0) {
      for (int i = 0; i < highlightedNodes.size(); i++) {
        if (highlightedNodes.get(i).getMiddle() == floor) {
          highlighte(highlightedNodes.get(i).getLeft(), highlightedNodes.get(i).getRight());
        }
      }
    }

    cfnodes = setCF(cfnodes); // gets updated after the add button()
    if (highlightedNodesp.size() > 0) {
      for (int i = 0; i < highlightedNodesp.size(); i++) {
        for (int j = 0; j < cfnodes.size(); j++) {
          if (cfnodes.get(j).getKey().equals(highlightedNodesp.get(i).getRight())) {
            highlight(cfnodes.get(j).getValue(), "red");
          }
        }
      }
    }
    if (start != null && target != null) { // && !ready4second
      highlightedNodes.removeAll(highlightedNodes);
      previousPath = drawLinesf(start, target, f);
    }
  }

  public void nextFloorClicked() throws IOException {
    List<Pair<Integer, Button>> cfnodes = new ArrayList<>();
    String f = "";
    // parent.getChildren().remove(messageText);
    if (floor == 3) {
      nextFloor.setDisable(true);
    }
    if (previousFloor.isDisable()) {
      previousFloor.setDisable(false);
    }

    if (floor < 4) {
      floor++;
      f = whichFloorS();
      floorLabel.setText("Floor " + whichFloorS());
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

    cfnodes = setCF(cfnodes);
    if (highlightedNodesp.size() > 0) {
      for (int i = 0; i < highlightedNodesp.size(); i++) {
        for (int j = 0; j < cfnodes.size(); j++) {
          if (cfnodes.get(j).getKey().equals(highlightedNodesp.get(i).getRight())) {
            highlight(cfnodes.get(j).getValue(), "red");
          }
        }
      }
    }
    if (start != null && target != null) { // && previousPath.size() > 0 && !ready4second
      highlightedNodes.removeAll(highlightedNodes);
      previousPath = drawLinesf(start, target, f);
    }
  }

  public void highlight(Button child, String color) {
    String border = "-fx-border-color: ";
    border = border + color + ";";
    child.setDisable(false);
    child.setStyle("-fx-background-color: lightblue;" + "-fx-background-insets: 0px;" + border);
  }

  public void highlighte(Button child, int move) {
    ImageView image = new ImageView();
    child.setDisable(false);
    child.setStyle("-fx-background-color: yellow;" + "-fx-background-insets: 0px;");
    if (move < 0) {
      image.setImage(new Image("/Down - elev.png"));
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
      image.setImage(new Image("/Up - elev.png"));
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

  public void unhighlight(Button child) {
    child.setStyle("-fx-background-insets: 0px;" + "-fx-border-color: black;");
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

  public void typeChecked(CheckBox check, List<Pair<Integer, Text>> nodes, String color) {
    if (check.isSelected()) {
      // highlightt(nodes, color);
      for (Pair<Integer, Text> p : nodes) {
        Text text = p.getValue();
        parent.getChildren().add(text);
      }
    } else {
      // unhighlightt(nodes);
      for (Pair<Integer, Text> p : nodes) {
        Text text = p.getValue();
        parent.getChildren().remove(text);
      }
    }
  }

  public void startSelected() throws IOException {
    List<Pair<Integer, Button>> cfnodes = new ArrayList<>();
    String lname = startSelect.getValue();
    if (lname != null && !lname.equals("")) {
      int index = startSelect.getSelectionModel().getSelectedIndex();
      int nodeid = nodeIds.get(index);
      Node n = qdb.retrieveNode(nodeid);
      String nodeFloor = n.getFloor();
      int f = whichFloorI(nodeFloor);
      int crossFloors = Math.abs(f - floor);
      // if (!ready4Second) {
      ready4Second = true;
      start = n;
      target = null;
      removeLines(previousPath);
      parent.getChildren().remove(messageText);
      // }

      if (highlightedNodes.size() > 0) {
        for (int j = 0; j < highlightedNodes.size(); j++) {
          if (highlightedNodes.get(j).getMiddle() == floor) {
            unhighlight(highlightedNodes.get(j).getLeft());
          }
        }
        highlightedNodes.removeAll(highlightedNodes);
      }

      cfnodes = setCF(cfnodes);
      if (highlightedNodesp.size() > 0) {
        for (int i = 0; i < highlightedNodesp.size(); i++) {
          for (int j = 0; j < cfnodes.size(); j++) {
            if (cfnodes.get(j).getKey().equals(highlightedNodesp.get(i).getRight())) {
              unhighlight(cfnodes.get(j).getValue());
            }
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

      cfnodes = setCF(cfnodes); // gets updated after add buttons
      for (int i = 0; i < cfnodes.size(); i++) {
        if (cfnodes.get(i).getKey() == nodeid) {
          Button node = cfnodes.get(i).getValue();
          node.setDisable(false);
          node.setStyle(
              "-fx-background-color: lightblue;"
                  + "-fx-border-color: red;"
                  + "-fx-background-insets: 0px;");
          highlightedNodesp.add(
              0, Triple.of(node, floor, nodeid)); // int index = parent.getChildren().indexOf(node);
        }
      }
    }
  }

  public void endSelected() throws IOException {
    List<Pair<Integer, Button>> cfnodes = new ArrayList<>();
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
        // ready4Second = false;
        removeLines(previousPath);
        if (highlightedNodes.size() > 0) {
          for (int j = 0; j < highlightedNodes.size(); j++) {
            if (highlightedNodes.get(j).getMiddle() == floor) {
              unhighlight(highlightedNodes.get(j).getLeft());
            }
          }
          highlightedNodes.removeAll(highlightedNodes);
        }
        // parent.getChildren().remove(messageText);
        try {
          previousPath = drawLinesf(start, target, f);
        } catch (Exception ex) {
          throw new RuntimeException(ex);
        }
      }

      if (highlightedNodesp.size() == 2) {
        if (highlightedNodesp.get(1).getMiddle() == floor) {
          unhighlight(highlightedNodesp.get(1).getLeft());
        }
        highlightedNodesp.remove(highlightedNodesp.get(1));
      }

      /*
      cfnodes = setCF(cfnodes);
      if (highlightedNodesp.size() == 2) {
        for (int j = 0; j < cfnodes.size(); j++) {
          if (cfnodes.get(j).getKey().equals(highlightedNodesp.get(1).getRight())) {
            unhighlight(cfnodes.get(j).getValue());
          }
        }
        highlightedNodesp.remove(highlightedNodesp.get(1));
      }
       */

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

      cfnodes = setCF(cfnodes); // gets updated after add buttons
      for (int i = 0; i < cfnodes.size(); i++) {
        if (cfnodes.get(i).getKey() == nodeid) {
          Button node = cfnodes.get(i).getValue();
          node.setDisable(false);
          node.setStyle(
              "-fx-background-color: lightblue;"
                  + "-fx-border-color: red;"
                  + "-fx-background-insets: 0px;");
          highlightedNodesp.add(
              1, Triple.of(node, floor, nodeid)); // int index = parent.getChildren().indexOf(node);
        }
      }
    }
  }

  public void algorithmSelected() {
    String algo = algorithmSelect.getValue();
    if (algo != null && !algo.equals("")) {
      switch (algo) {
        case "aStar":
          {
            clearButtonClicked();
            algorithm = "aStar";
          }
          break;
        case "bfs":
          {
            clearButtonClicked();
            algorithm = "bfs";
          }
          break;
        case "dfs":
          {
            clearButtonClicked();
            algorithm = "dfs";
          }
          break;
        case "djikstra":
          {
            clearButtonClicked();
            algorithm = "djikstra";
          }
          break;
      }
    }
  }

  public void dateSelected() { // RadioMenuItem itemSelect
    String d = dateSelect.getValue();
    if (d != null && !d.equals("")) {
      clearButtonClicked();
      date = Date.valueOf(d);
      refresh();
    }
  }

  public void refresh() {
    String f = whichFloorS();
    removeText();
    removeButtons();
    addButtons(f);
  }

  public void clearTextualPathfinding() {
    ObservableList children = textArea.getChildren();
    textArea.getChildren().removeAll(children);
  }

  public void clearButtonClicked() {
    List<Pair<Integer, Button>> cfnodes = new ArrayList<>();
    clearTextualPathfinding();
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
    removeText();
    cfnodes = setCF(cfnodes);
    for (int i = 0; i < cfnodes.size(); i++) {
      unhighlight(cfnodes.get(i).getValue()); // + 1
    }
    highlightedNodes.removeAll(highlightedNodes);
    highlightedNodesp.removeAll(highlightedNodesp);
    startSelect.setValue(null);
    endSelect.setValue(null);
    messageField.setText(""); // null
    parent.getChildren().remove(messageText);
  }

  /*
  public void settingButtonClicked() throws IOException {
    PathfindingSettingController.display();
  }
   */

  public static List<Node> getLatestNodes() {
    return latest;
  }

  public void getLatestNodesb() {
    List<Node> latestNodes = new ArrayList<>();
    List<Move> allMoves = qdb.retrieveAllMoves();
    List<Move> dateMoves = new ArrayList<>();
    List<Node> moveNodes = new ArrayList<>();
    List<Node> currentNodes = new ArrayList<>();
    List<Node> nodeswchanges = new ArrayList<>();
    for (Move m : allMoves) {
      Date d = m.getDate();
      if (d.compareTo(Date.valueOf("2023-01-01")) == 0) {
        Node startn =
            new Node(
                m.getNode().getNodeID(),
                m.getNode().getXCoord(),
                m.getNode().getYCoord(),
                m.getNode().getFloor(),
                m.getNode().getBuilding(),
                m.getNode().getLocation());
        List<Node> nodes = qdb.retrieveAllNodes();
        for (Node n : nodes) {
          if (n.getLocation().getLongName().equals(m.getLongName())) {
            startn.setLocation(n.getLocation());
            break;
          }
        }
        startNodes.add(startn);
      }
      if (d.compareTo(getLatestDate()) == 0) {
        dateMoves.add(m);
      }
      if (!moveDates.contains(d)) {
        moveDates.add(d);
        dateSelect.getItems().add(d.toString());
      }
    }
    for (Move m : dateMoves) {
      Node moven =
          new Node(
              m.getNode().getNodeID(),
              m.getNode().getXCoord(),
              m.getNode().getYCoord(),
              m.getNode().getFloor(),
              m.getNode().getBuilding(),
              m.getNode().getLocation());
      List<Node> nodes = qdb.retrieveAllNodes();
      for (Node n : nodes) {
        if (n.getLocation().getLongName().equals(m.getLongName())) {
          moven.setLocation(n.getLocation());
          break;
        }
      }
      moveNodes.add(moven);
    }
    Collections.sort(
        moveDates,
        new Comparator<Date>() {
          public int compare(Date date1, Date date2) {
            return date1.compareTo(date2);
          }
        });
    List<Date> dateswofirst = new ArrayList<>();
    dateswofirst.addAll(moveDates);
    dateswofirst.remove(0);
    List<Node> previousNodesm = new ArrayList<>();
    for (Date movedate : dateswofirst) {
      if (movedate.before(date)) {
        previousNodesm = previousChange(movedate, previousNodesm);
      }
    }

    nodeswchanges.addAll(moveNodes);

    if (previousNodesm.size() > 0) {
      for (Node node : previousNodesm) {
        boolean add = true;
        for (Node moven : moveNodes) {
          if (node.getLocation().equals(moven.getLocation())) {
            add = false;
          }
        }
        if (add) {
          nodeswchanges.add(node);
        }
      }
    }

    currentNodes.addAll(nodeswchanges);

    for (Node node : startNodes) {
      boolean add = true;
      for (Node moven : nodeswchanges) {
        if (node.getLocation().equals(moven.getLocation())) {
          add = false;
        }
      }
      if (add) {
        currentNodes.add(node);
      }
    }
    latest = currentNodes;
    // return latestNodes;
  }
}
