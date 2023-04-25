package edu.wpi.cs3733.D23.teamQ.controllers;

import static edu.wpi.cs3733.D23.teamQ.SecondaryStage.newStage;

import edu.wpi.cs3733.D23.teamQ.Alert;
import edu.wpi.cs3733.D23.teamQ.db.Qdb;
import edu.wpi.cs3733.D23.teamQ.db.obj.Edge;
import edu.wpi.cs3733.D23.teamQ.db.obj.Location;
import edu.wpi.cs3733.D23.teamQ.db.obj.Node;
import edu.wpi.cs3733.D23.teamQ.navigation.Navigation;
import edu.wpi.cs3733.D23.teamQ.navigation.Screen;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.animation.Interpolator;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import net.kurobako.gesturefx.GesturePane;

public class GraphicalMapEditorController {

  Node empty = new Node(-1, 0, 0, "empty", "empty", null);

  @FXML private Button AddEdgeBtn;

  @FXML private Label alerts1;

  @FXML private ImageView image11;

  private boolean startnodeOr = false;

  private boolean endnodeOr = false;

  private Node startnode = empty;

  private Node endnode = empty;

  @FXML private TextField endnodeinitial;

  @FXML private TextField startnodeinitial;

  @FXML private ComboBox<String> TableChoose;

  @FXML private Button TableChooseBtn;

  private List<Text> Texts = new ArrayList<>();
  @FXML private Button DisplayLocationBtn;

  @FXML private ComboBox<String> NodeTypeChoose;

  private String NodeType[] = {
    "/", "All", "CONF", "DEPT", "ELEV", "EXIT", "INFO", "LABS", "REST", "RETL", "STAI", "BATH",
    "SERV"
  };

  private String TableType[] = {"Node Table", "Location Name Table", "Move Table", "Edge Table"};

  @FXML private Button AddBtn;

  @FXML private Button CleBtn;

  @FXML private Button DelBtn;

  @FXML private Button DisBtn;

  @FXML private Button HelpBtn;

  @FXML private Button HidBtn;

  @FXML private Button LastBtn;

  @FXML private Button NextBtn;

  @FXML private Button SetBtn;

  @FXML private Button FindBtn;

  List<Integer> NodeID = new ArrayList<>();
  private double posx = 0;
  private double posy = 0;

  private List<Line> line = new ArrayList<>();

  private boolean displayEdges = false;
  @FXML private Label WhichFloor;
  List<Button> button = new ArrayList<>();

  File[] file = {
    new File("src/main/resources/01_thefirstfloor.png"),
    new File("src/main/resources/02_thesecondfloor.png"),
    new File("src/main/resources/03_thethirdfloor.png"),
    new File("src/main/resources/00_thelowerlevel1.png"),
    new File("src/main/resources/00_thelowerlevel2.png")
  };

  String[] images = new String[file.length];

  Image[] image = new Image[images.length];
  int currentIndex = 0;
  @FXML private ImageView imageView;

  Qdb qdb = Qdb.getInstance();

  Alert alert = new Alert();
  Text text;
  double mouseX;
  double mouseY;
  @FXML GridPane root;

  @FXML Group parent;

  private GesturePane pane;

  private String newBuilding;
  private String newFloor;
  private int newXcoord;
  private int newYcoord;
  private String newLongName;
  private String newShortName;

  private String newNodeType;

  @FXML private TextField xinitial;

  @FXML private TextField yinitial;

  private int nodeid;
  @FXML private TextField buildinginitial;

  @FXML private TextField floorinitial;

  @FXML private TextField longnameinitial;

  @FXML private TextField shortnameinitial;

  @FXML private TextField nodetypeinitial;

  @FXML private Label alerts;

  @FXML private ImageView image1;

  @FXML private TextField nodeidinput;

  /**
   * initialize the basic map
   *
   * @throws IOException
   */
  @FXML
  public void initialize() throws IOException {
    NodeTypeChoose.getItems().addAll(NodeType);
    TableChoose.getItems().addAll(TableType);
    button = addButtons(Floor(currentIndex));
    javafx.scene.Node node = parent;
    pane = new GesturePane(node);
    mouseX = 0;
    mouseY = 0;
    root.getChildren().add(pane);
    pane.setContent(node);
    pane.setFitMode(GesturePane.FitMode.COVER);
    pane.setOnMouseClicked(
        e -> {
          if (e.getClickCount() == 2) {
            Point2D pivotOnTarget =
                pane.targetPointAt(new Point2D(e.getX(), e.getY()))
                    .orElse(pane.targetPointAtViewportCentre());
            pane.animate(Duration.millis(200))
                .interpolateWith(Interpolator.EASE_BOTH)
                .zoomBy(pane.getCurrentScale(), pivotOnTarget);
          }
        });
    AddEdgeBtn.setOnMouseEntered(
        e -> {
          AddEdgeBtn.setCursor(Cursor.HAND);
        });
    TableChooseBtn.setOnMouseEntered(
        e -> {
          TableChooseBtn.setCursor(Cursor.HAND);
        });
    DisplayLocationBtn.setOnMouseEntered(
        e -> {
          DisplayLocationBtn.setCursor(Cursor.HAND);
        });

    FindBtn.setOnMouseEntered(
        e -> {
          FindBtn.setCursor(Cursor.HAND);
        });
    DelBtn.setOnMouseEntered(
        e -> {
          DelBtn.setCursor(Cursor.HAND);
        });
    AddBtn.setOnMouseEntered(
        e -> {
          AddBtn.setCursor(Cursor.HAND);
        });
    SetBtn.setOnMouseEntered(
        e -> {
          SetBtn.setCursor(Cursor.HAND);
        });
    CleBtn.setOnMouseEntered(
        e -> {
          CleBtn.setCursor(Cursor.HAND);
        });
    DisBtn.setOnMouseEntered(
        e -> {
          DisBtn.setCursor(Cursor.HAND);
        });
    HidBtn.setOnMouseEntered(
        e -> {
          HidBtn.setCursor(Cursor.HAND);
        });
    LastBtn.setOnMouseEntered(
        e -> {
          LastBtn.setCursor(Cursor.HAND);
        });
    NextBtn.setOnMouseEntered(
        e -> {
          NextBtn.setCursor(Cursor.HAND);
        });
    HelpBtn.setOnMouseEntered(
        e -> {
          HelpBtn.setCursor(Cursor.HAND);
        });
    for (int i = 0; i < file.length; i++) {
      images[i] = file[i].toURI().toString();
    }

    for (int i = 0; i < images.length; i++) {
      image[i] = new Image(images[i]);
    }
    imageView.setImage(image[currentIndex]);
    WhichFloor.setText("The First Floor");
  }

  /**
   * add nodes on the map
   *
   * @param floor
   * @return
   */
  public List<Button> addButtons(String floor) {
    NodeID.clear();
    List<Button> buttons = new ArrayList<>();
    List<Node> nodes = qdb.retrieveAllNodes();
    List<Node> ffNodes = new ArrayList<>();
    for (Node n : nodes) {

      if (n.getFloor().equals(floor)) {
        ffNodes.add(n);
      }
    }
    for (Node n : ffNodes) {
      int nodeID = n.getNodeID();
      double x = n.getXCoord() / 5.0 - 1.5;
      double y = n.getYCoord() / 5.0 - 1.5;
      Button node = new Button();
      node.setLayoutX(x);
      node.setLayoutY(y);
      node.setStyle(
          "-fx-background-radius: 5em;"
              + "-fx-min-width: 3px;"
              + "-fx-min-height: 3px;"
              + "-fx-max-width: 3px;"
              + "-fx-max-height: 3px;"
              + "-fx-background-insets: 0px;"
              + "-fx-background-color: #3492D5");
      node.setOnMouseClicked(
          e -> {
            alert.clearLabelAlert(alerts, image1);
            nodeidinput.setText(Integer.toString(nodeID));
            NodeInformation(nodeID);
            if (startnodeOr) {
              startnodeinitial.setText(Integer.toString(nodeID));
              startnode = qdb.nodeTable.retrieveRow(nodeID);
              startnodeOr = false;
            } else if (endnodeOr) {
              endnodeinitial.setText(Integer.toString(nodeID));
              endnode = qdb.nodeTable.retrieveRow(nodeID);
              endnodeOr = false;
            }
          });
      node.setOnMouseEntered(
          e -> {
            node.setStyle(
                "-fx-background-radius: 5em;"
                    + "-fx-min-width: 3px;"
                    + "-fx-min-height: 3px;"
                    + "-fx-max-width: 3px;"
                    + "-fx-max-height: 3px;"
                    + "-fx-background-insets: 0px;"
                    + "-fx-background-color: #37AC2B");
            node.setCursor(Cursor.HAND);
          });
      node.setOnMouseExited(
          e -> {
            node.setStyle(
                "-fx-background-radius: 5em;"
                    + "-fx-min-width: 3px;"
                    + "-fx-min-height: 3px;"
                    + "-fx-max-width: 3px;"
                    + "-fx-max-height: 3px;"
                    + "-fx-background-insets: 0px;"
                    + "-fx-background-color: #3492D5");
          });
      node.setOnMousePressed(
          e -> {
            node.setStyle(
                "-fx-background-radius: 5em;"
                    + "-fx-min-width: 3px;"
                    + "-fx-min-height: 3px;"
                    + "-fx-max-width: 3px;"
                    + "-fx-max-height: 3px;"
                    + "-fx-background-insets: 0px;"
                    + "-fx-background-color: #37AC2B");
            mouseX = e.getX();
            mouseY = e.getY();
          });
      node.setOnMouseDragged(
          e -> {
            node.setStyle(
                "-fx-background-radius: 5em;"
                    + "-fx-min-width: 3px;"
                    + "-fx-min-height: 3px;"
                    + "-fx-max-width: 3px;"
                    + "-fx-max-height: 3px;"
                    + "-fx-background-insets: 0px;"
                    + "-fx-background-color: #37AC2B");
            double distanceX = e.getX() - mouseX;
            double distanceY = e.getY() - mouseY;
            posx = node.getLayoutX() + distanceX;
            posy = node.getLayoutY() + distanceY;
            node.setLayoutX(posx);
            node.setLayoutY(posy);
            node.setCursor(Cursor.MOVE);
          });
      node.setOnMouseReleased(
          e -> {
            node.setStyle(
                "-fx-background-radius: 5em;"
                    + "-fx-min-width: 3px;"
                    + "-fx-min-height: 3px;"
                    + "-fx-max-width: 3px;"
                    + "-fx-max-height: 3px;"
                    + "-fx-background-insets: 0px;"
                    + "-fx-background-color: #3492D5");
            int currentX = (int) ((node.getLayoutX() + 1.5) * 5);
            int currentY = (int) ((node.getLayoutY() + 1.5) * 5);
            Node newNode = qdb.retrieveNode(nodeID);
            newNode.setXCoord(currentX);
            newNode.setYCoord(currentY);
            qdb.updateNode(nodeID, newNode);
            NodeInformation(nodeID);
            setEdges();
          });
      parent.getChildren().add(node);
      buttons.add(node);
      NodeID.add(nodeID);
    }
    return buttons;
  }

  // Below are all about node

  /**
   * find nodes and display the information of the nodes
   *
   * @param event
   */
  @FXML
  void findclicked(MouseEvent event) {
    findOnMap();
  }

  /**
   * update nodes
   *
   * @param event
   */
  @FXML
  void setclicked(MouseEvent event) {
    if (nodeIDAlertone(nodeidinput, alerts, image1)) {
      if (coordAlert(xinitial, yinitial, alerts, image1)) {
        if (locationAlert(longnameinitial, shortnameinitial, nodetypeinitial, alerts, image1)) {
          if (floorAlert(floorinitial, alerts, image1)) {
            nodeid = Integer.parseInt(nodeidinput.getText());
            newLongName = longnameinitial.getText();
            newNodeType = nodetypeinitial.getText();
            newShortName = shortnameinitial.getText();
            qdb.locationTable.updateRow(
                nodeid, new Location(nodeid, newLongName, newShortName, newNodeType));
            newBuilding = buildinginitial.getText();
            newFloor = floorinitial.getText();
            newXcoord = Integer.parseInt(xinitial.getText());
            newYcoord = Integer.parseInt(yinitial.getText());
            qdb.nodeTable.updateRow(
                nodeid,
                new Node(
                    nodeid,
                    newXcoord,
                    newYcoord,
                    newFloor,
                    newBuilding,
                    Qdb.getInstance().locationTable.retrieveRow(nodeid)));
            refreshNodes();
            setEdges();
            findOnMap();
          }
        }
      }
    }
  }

  /**
   * delete nodes
   *
   * @param event
   */
  @FXML
  void deleteclicked(MouseEvent event) {
    if (nodeIDAlertone(nodeidinput, alerts, image1)) {
      nodeid = Integer.parseInt(nodeidinput.getText());
      qdb.nodeTable.deleteRow(nodeid);
      qdb.locationTable.deleteRow(nodeid);
      refreshNodes();
    } else {
      InitialNode();
    }
    setEdges();
  }

  /**
   * add nodes
   *
   * @param event
   */
  @FXML
  void addclicked(MouseEvent event) {
    if (nodeIDAlerttwo(nodeidinput, alerts, image1)) {
      if (coordAlert(xinitial, yinitial, alerts, image1)) {
        if (locationAlert(longnameinitial, shortnameinitial, nodetypeinitial, alerts, image1)) {
          if (floorAlert(floorinitial, alerts, image1)) {
            nodeid = Integer.parseInt(nodeidinput.getText());
            newLongName = longnameinitial.getText();
            newNodeType = nodetypeinitial.getText();
            newShortName = shortnameinitial.getText();

            qdb.locationTable.addRow(new Location(nodeid, newLongName, newShortName, newNodeType));
            newBuilding = buildinginitial.getText();
            newFloor = floorinitial.getText();
            newXcoord = Integer.parseInt(xinitial.getText());
            newYcoord = Integer.parseInt(yinitial.getText());
            Node newnode =
                new Node(
                    nodeid,
                    newXcoord,
                    newYcoord,
                    newFloor,
                    newBuilding,
                    Qdb.getInstance().locationTable.retrieveRow(nodeid));
            qdb.nodeTable.addRow(newnode);
            refreshNodes();
            findOnMap();
            setEdges();
          }
        }
      }
    }
  }

  /**
   * clear all information of nodes
   *
   * @param event
   */
  @FXML
  void clearclicked(MouseEvent event) {
    alert.clearLabelAlert(alerts, image1);
    nodeidinput.setText("");
    InitialNode();
    button
        .get(findButton(nodeid))
        .setStyle(
            "-fx-background-radius: 5em;"
                + "-fx-min-width: 3px;"
                + "-fx-min-height: 3px;"
                + "-fx-max-width: 3px;"
                + "-fx-max-height: 3px;"
                + "-fx-background-insets: 0px;"
                + "-fx-background-color: #3492D5");
  }

  /** if nodeid exist, display the node information on the map. Else call alert. */
  public void NodeInformation(int id) {
    xinitial.setText(Integer.toString(qdb.nodeTable.retrieveRow(id).getXCoord()));
    buildinginitial.setText(qdb.nodeTable.retrieveRow(id).getBuilding());
    yinitial.setText(Integer.toString(qdb.nodeTable.retrieveRow(id).getYCoord()));
    floorinitial.setText(qdb.nodeTable.retrieveRow(id).getFloor());
    longnameinitial.setText(qdb.locationTable.retrieveRow(id).getLongName());
    shortnameinitial.setText(qdb.locationTable.retrieveRow(id).getShortName());
    nodetypeinitial.setText(qdb.locationTable.retrieveRow(id).getNodeType());
  }

  /** initialize the node information. */
  public void InitialNode() {
    xinitial.setText("--");
    buildinginitial.setText("--");
    yinitial.setText("--");
    floorinitial.setText("--");
    longnameinitial.setText("--");
    shortnameinitial.setText("--");
    nodetypeinitial.setText("--");
  }

  /**
   * true if the node exists, false else
   *
   * @param nodeID
   * @return boolean
   */
  public boolean nodeIDExist(int nodeID) {
    for (int i = 0; i < Qdb.getInstance().nodeTable.getAllRows().size(); i++) {
      if (nodeID == Qdb.getInstance().nodeTable.getAllRows().get(i).getNodeID()) return true;
    }
    return false;
  }

  /** find the node on the map */
  void findOnMap() {
    if (nodeIDAlertone(nodeidinput, alerts, image1)) {
      nodeid = Integer.parseInt(nodeidinput.getText());
      NodeInformation(nodeid);
      String floor = qdb.nodeTable.retrieveRow(nodeid).getFloor();
      if (!floor.equals(Floor(currentIndex))) {
        currentIndex = findFloor(floor);
        if (!button.isEmpty()) parent.getChildren().removeAll(button);
        button = addButtons(Floor(currentIndex));
        imageView.setImage(image[currentIndex]);
        setFloor(currentIndex);
      }
      alert.clearLabelAlert(alerts, image1);
      Point2D pivotOnTarget =
          new Point2D(
              qdb.nodeTable.retrieveRow(nodeid).getXCoord() / 5.0,
              qdb.nodeTable.retrieveRow(nodeid).getYCoord() / 5.0);
      pane.animate(Duration.millis(200))
          .interpolateWith(Interpolator.EASE_BOTH)
          .zoomBy(pane.getCurrentScale(), pivotOnTarget);
      button
          .get(findButton(nodeid))
          .setStyle(
              "-fx-background-radius: 5em;"
                  + "-fx-min-width: 3px;"
                  + "-fx-min-height: 3px;"
                  + "-fx-max-width: 3px;"
                  + "-fx-max-height: 3px;"
                  + "-fx-background-insets: 0px;"
                  + "-fx-background-color: #37AC2B");
    } else {
      InitialNode();
    }
  }

  /**
   * return true if the nodes is on the floor, else return false
   *
   * @param node
   * @param floor
   * @return boolean
   */
  boolean nodeOnTheFloor(int node, int floor) {
    if (qdb.nodeTable.retrieveRow(node).getFloor().equals(Floor(floor))) return true;
    return false;
  }

  /**
   * return the index of the node in the button list with given nodeID
   *
   * @param id
   * @return
   */
  int findButton(int id) {
    int x = 0;
    for (int i = 0; i < NodeID.size(); i++) {
      if (NodeID.get(i) == id) {
        x = i;
      }
    }
    return x;
  }

  // Below are all about turn to the page and the floors

  /**
   * turn on the next floor when click the button
   *
   * @param event
   */
  @FXML
  void NextClicked(MouseEvent event) {
    shownext();
  }

  /**
   * turn on the last page when click the button
   *
   * @param event
   */
  @FXML
  void LastClicked(MouseEvent event) {
    showlast();
  }

  /** turn to the next floor */
  void shownext() {
    startnodeinitial.clear();
    endnodeinitial.clear();
    startnodeinitial.setPromptText("Click here to choose the start node");
    endnodeinitial.setPromptText("Click here to choose the start node");
    startnodeOr = false;
    endnodeOr = false;
    startnode = empty;
    endnode = empty;
    HideEdges();
    clearLocationName();
    currentIndex++;
    if (currentIndex >= file.length) {
      currentIndex = 0;
    }
    refreshNodes();
    imageView.setImage(image[currentIndex]);
    setFloor(currentIndex);
  }

  /** turn on the last page */
  void showlast() {
    startnodeinitial.setPromptText("Click here to choose the start node");
    endnodeinitial.setPromptText("Click here to choose the start node");
    startnodeOr = false;
    endnodeOr = false;
    startnode = empty;
    endnode = empty;
    HideEdges();
    clearLocationName();
    currentIndex--;
    if (currentIndex >= 0) {
      refreshNodes();
      button = addButtons(Floor(currentIndex));
      imageView.setImage(image[currentIndex]);
    } else {
      currentIndex = 4;
      refreshNodes();
      imageView.setImage(image[currentIndex]);
    }
    setFloor(currentIndex);
  }

  /**
   * show which floor it is on the map with the int floor
   *
   * @param x
   */
  void setFloor(int x) {
    if (x == 0) {
      WhichFloor.setText("The First Floor");
    } else if (x == 1) {
      WhichFloor.setText("The Second Floor");
    } else if (x == 2) {
      WhichFloor.setText("The Third Floor");
    } else if (x == 3) {
      WhichFloor.setText("The Lower Level 1");
    } else {
      WhichFloor.setText("The Lower Level 2");
    }
  }

  /**
   * get the int floor from the String floor
   *
   * @param floor
   * @return
   */
  int findFloor(String floor) {
    if (floor.equals("1")) {
      return 0;
    } else if (floor.equals("2")) {
      return 1;
    } else if (floor.equals("3")) {
      return 2;
    } else if (floor.equals("L1")) {
      return 3;
    } else {
      return 4;
    }
  }

  /**
   * return the String type of floor by the integer type of floor.
   *
   * @param x
   * @return
   */
  String Floor(int x) {
    if (x == 0) {
      return "1";
    } else if (x == 1) {
      return "2";
    } else if (x == 2) {
      return "3";
    } else if (x == 3) {
      return "L1";
    } else {
      return "L2";
    }
  }

  // Below are all about alerts

  /**
   * true if the floor exists, else return false and call alerts
   *
   * @param floor
   * @param floorAlert
   * @param image
   * @return boolean
   */
  public boolean floorAlert(TextField floor, Label floorAlert, ImageView image) {
    Alert alert = new Alert();
    String floors = floor.getText();
    if (floors.equals("1")
        || floors.equals("2")
        || floors.equals("3")
        || floors.equals("L1")
        || floors.equals("L2")) {
      alert.clearLabelAlert(floorAlert, image);
      return true;
    }
    alert.setLabelAlert("This floor does not exist.", floorAlert, image);
    return false;
  }

  /**
   * return true if the nodeId exists, else call alerts and return false
   *
   * @param nodeID
   * @param nodeIDAlert
   * @param image
   * @return
   */
  public boolean nodeIDAlertone(TextField nodeID, Label nodeIDAlert, ImageView image) {
    Alert alert = new Alert();
    if (isNumber(nodeID.getText())) {
      int nodeIDInput = Integer.parseInt(nodeID.getText());
      if (nodeIDExist(nodeIDInput)) {
        alert.clearLabelAlert(nodeIDAlert, image);
        return true;
      } else {
        alert.setLabelAlert("This nodeID does not exist.", nodeIDAlert, image);
      }
    } else {
      alert.setLabelAlert("Please input the correct nodeID.", nodeIDAlert, image);
    }
    return false;
  }

  /**
   * return true if the nodeID is legal, and it does not exist, else call alerts and return false
   *
   * @param nodeID
   * @param nodeIDAlert
   * @param image
   * @return
   */
  public boolean nodeIDAlerttwo(TextField nodeID, Label nodeIDAlert, ImageView image) {
    Alert alert = new Alert();
    if (isNumber(nodeID.getText())) {
      int nodeIDInput = Integer.parseInt(nodeID.getText());
      if (!nodeIDExist(nodeIDInput)) {
        alert.clearLabelAlert(nodeIDAlert, image);
        return true;
      } else {
        alert.setLabelAlert("This nodeID exists.", nodeIDAlert, image);
      }
    } else {
      alert.setLabelAlert("Please input the correct nodeID.", nodeIDAlert, image);
    }
    return false;
  }

  /**
   * return true if X-coord and Y-coord are legal, else call alert and return false
   *
   * @param xcoord
   * @param ycoord
   * @param informationAlert
   * @param image
   * @return
   */
  public boolean coordAlert(
      TextField xcoord, TextField ycoord, Label informationAlert, ImageView image) {
    Alert alert = new Alert();
    if (isNumber(xcoord.getText())) {
      if (isNumber(ycoord.getText())) {
        alert.clearLabelAlert(informationAlert, image);
        return true;
      } else {
        alert.setLabelAlert("Please input the correct X-coord.", informationAlert, image);
      }
    } else {
      alert.setLabelAlert("Please input the correct Y-coord.", informationAlert, image);
    }
    return false;
  }

  /**
   * return true if the location name is legal and is not empty, else return false
   *
   * @param longname
   * @param shortname
   * @param nodetype
   * @param informationAlert
   * @param image
   * @return
   */
  public boolean locationAlert(
      TextField longname,
      TextField shortname,
      TextField nodetype,
      Label informationAlert,
      ImageView image) {
    Alert alert = new Alert();
    if (!longname.getText().equals("")) {
      if (!shortname.getText().equals("")) {
        if (!nodetype.getText().equals("")) {
          alert.clearLabelAlert(informationAlert, image);
          return true;
        } else {
          alert.setLabelAlert("Please input the correct node type.", informationAlert, image);
        }
      } else {
        alert.setLabelAlert("Please input the correct short name.", informationAlert, image);
      }
    } else {
      alert.setLabelAlert("Please input the correct long name.", informationAlert, image);
    }
    return false;
  }

  /**
   * return true if the edge is legal, else return false
   *
   * @param edge
   * @return
   */
  boolean edgeAlert(Edge edge) {
    List<Edge> alledges = qdb.edgeTable.getAllRows();
    for (int i = 0; i < alledges.size(); i++) {
      if (edge.getStartNode().equals(empty)) {
        alert.setLabelAlert("The start node cannot be empty", alerts1, image11);
        return false;
      } else if (edge.getEndNode().equals(empty)) {
        alert.setLabelAlert("The end node cannot be empty", alerts1, image11);
        return false;
      } else if (edge.getStartNode().getNodeID() == edge.getEndNode().getNodeID()) {
        alert.setLabelAlert("The end node cannot be the start node", alerts1, image11);
        return false;
      } else if (edge.getStartNode().getNodeID() == alledges.get(i).getStartNode().getNodeID()
          && edge.getEndNode().getNodeID() == alledges.get(i).getEndNode().getNodeID()) {
        alert.setLabelAlert("This edge exists", alerts1, image11);
        return false;
      }
    }
    alert.clearLabelAlert(alerts1, image11);
    return true;
  }

  // Below are all about the help page

  /**
   * open the help page
   *
   * @param event
   * @throws IOException
   */
  @FXML
  void helpClicked(MouseEvent event) throws IOException {
    MapEditorHelpController controller =
        (MapEditorHelpController) Navigation.getController(Screen.MAPEDITORHELP);
    Stage stage = newStage("Help", Screen.MAPEDITORHELP);
    controller.setStage(stage);
    stage.show();
    stage.centerOnScreen();
  }

  // Below are all about refresh

  /** refresh nodes */
  void refreshNodes() {
    parent.getChildren().removeAll(button);
    button = addButtons(Floor(currentIndex));
  }

  // Below are all about lines

  /**
   * add lines
   *
   * @param path
   * @return
   */
  List<javafx.scene.shape.Line> addLines(List<Integer> path) {
    List<javafx.scene.shape.Line> lines = new ArrayList<>();
    for (int i = path.size() - 1; i >= 1; i = i - 2) {
      int n = path.get(i);
      int next = path.get(i - 1);
      DoubleProperty startX =
          new SimpleDoubleProperty(qdb.nodeTable.retrieveRow(n).getXCoord() / 5.0);
      DoubleProperty startY =
          new SimpleDoubleProperty(qdb.nodeTable.retrieveRow(n).getYCoord() / 5.0);
      DoubleProperty endX =
          new SimpleDoubleProperty(qdb.nodeTable.retrieveRow(next).getXCoord() / 5.0);
      DoubleProperty endY =
          new SimpleDoubleProperty(qdb.nodeTable.retrieveRow(next).getYCoord() / 5.0);
      Line line = new BoundLine(startX, startY, endX, endY);
      parent.getChildren().add(line);
      lines.add(line);
    }
    displayEdges = true;
    return lines;
  }

  /**
   * remove lines
   *
   * @param lines
   */
  void removeLines(List<Line> lines) {
    parent.getChildren().removeAll(lines);
    displayEdges = false;
  }

  /**
   * choose lines
   *
   * @param floor
   * @return path
   */
  List<Integer> chooseLines(int floor) {
    List<Integer> path = new ArrayList<>();
    for (int i = 0; i < qdb.edgeTable.getAllRows().size(); i++) {
      int start = qdb.edgeTable.getAllRows().get(i).getStartNode().getNodeID();
      int target = qdb.edgeTable.getAllRows().get(i).getEndNode().getNodeID();
      if (nodeOnTheFloor(start, floor) && nodeOnTheFloor(target, floor)) {
        path.add(start);
        path.add(target);
      }
    }
    return path;
  }

  /**
   * show edges on the map when click the button
   *
   * @param event
   */
  @FXML
  void EdgesDispalyClicked(MouseEvent event) {
    DisplayEdges();
  }

  /** show edges on the map */
  void DisplayEdges() {
    if (!displayEdges) {
      line = addLines(chooseLines(currentIndex));
      displayEdges = true;
    }
  }

  /**
   * hide edges on the map when click the button
   *
   * @param event
   */
  @FXML
  void EdgesHidingClicked(MouseEvent event) {
    HideEdges();
  }

  /** hide edges on the map when click the button */
  void HideEdges() {
    if (displayEdges) removeLines(line);
    displayEdges = false;
  }

  /**
   * start to choose the end node when click the button
   *
   * @param event
   */
  @FXML
  void ChooseEndNodeClicked(MouseEvent event) {
    endnodeOr = true;
    endnodeinitial.setPromptText("Please Click a node as the start node");
  }

  /**
   * start to choss the start button when click the button
   *
   * @param event
   */
  @FXML
  void ChooseStartNodeClicked(MouseEvent event) {
    startnodeOr = true;
    startnodeinitial.setPromptText("Please Click a node as the start node");
  }

  /** refresh the egdes */
  void setEdges() {
    if (displayEdges) {
      HideEdges();
      DisplayEdges();
    }
  }

  /**
   * add an edge with start node and end node when click the button, call alert if it is illegal
   *
   * @param event
   */
  @FXML
  void AddEdgesClicked(MouseEvent event) {
    Edge addedge = new Edge(startnode, endnode);
    if (edgeAlert(addedge)) {
      qdb.edgeTable.addRow(addedge);
    }
    setEdges();
  }

  // Below are about type confirm

  /**
   * if the input is a number, return true, else false
   *
   * @param str
   * @return boolean
   */
  public boolean isNumber(String str) {
    if (str.equals("")) return false;
    for (char c : str.toCharArray()) {
      if (!Character.isDigit(c)) {
        return false;
      }
    }
    return true;
  }

  // Below are about tables

  /**
   * Open a secondary stage of node table
   *
   * @throws Exception
   */
  void NodeTable() throws Exception {
    NodeController controller = (NodeController) Navigation.getController(Screen.Node_Table);
    Stage stage = newStage("Node Table", Screen.Node_Table);
    controller.setStage(stage);
    stage.show();
    stage.centerOnScreen();
  }

  /**
   * Open a secondary stage of location name table
   *
   * @throws IOException
   */
  void LocationTable() throws IOException {
    LocationController controller =
        (LocationController) Navigation.getController(Screen.LocationName_Table);
    Stage stage = newStage("Location Name Table", Screen.LocationName_Table);
    controller.setStage(stage);
    stage.show();
    stage.centerOnScreen();
  }

  /**
   * Open a secondary stage of move table
   *
   * @throws IOException
   */
  void MoveTable() throws IOException {
    MoveController controller = (MoveController) Navigation.getController(Screen.Move_Table);
    Stage stage = newStage("Move Table", Screen.Move_Table);
    controller.setStage(stage);
    stage.show();
    stage.centerOnScreen();
  }

  /**
   * Open a secondary stage of edge table
   *
   * @throws IOException
   */
  void EdgeTable() throws IOException {
    EdgeController controller = (EdgeController) Navigation.getController(Screen.Edge_Table);
    Stage stage = newStage("Edge Table", Screen.Edge_Table);
    controller.setStage(stage);
    stage.show();
    stage.centerOnScreen();
  }

  /**
   * open a secondary stage of the choosing type of table
   *
   * @param event
   * @throws Exception
   */
  @FXML
  void TableClicked(MouseEvent event) throws Exception {
    String table = TableChoose.getValue();
    if (table.equals("Node Table")) {
      NodeTable();
    } else if (table.equals("Location Name Table")) {
      LocationTable();
    } else if (table.equals("Move Table")) {
      MoveTable();
    } else {
      EdgeTable();
    }
  }

  // Below are about the text location name

  /**
   * show the location name of nodes by choosing node type, return true if there are location names
   * on the map, return false else.
   *
   * @return boolean
   */
  boolean ShowLocationName() {
    int count = 1;
    parent.getChildren().removeAll(Texts);
    Texts.clear();
    List<Node> allNodes = qdb.nodeTable.getAllRows();
    String check = NodeTypeChoose.getValue();
    for (int i = 0; i < allNodes.size(); i++) {
      Node node1 = allNodes.get(i);
      String type = node1.getLocation().getNodeType();
      String name = node1.getLocation().getShortName();
      double x = node1.getXCoord() / 5.0;
      double y = node1.getYCoord() / 5.0;
      if (node1.getFloor().equals(Floor(currentIndex))) {
        if (check.equals("All") || type.equals(check)) {
          if (node1.getNodeID() % 10 == 5) {
            if (count == 0) {
              text = new Text(x + 3, y, name);
              count += 1;
            } else {
              text = new Text(x - 5, y - 3, name);
              count -= 1;
            }
          } else {
            if (count == 0) {
              text = new Text(x + 3, y + 3, name);
              count += 1;
            } else {
              text = new Text(x - 5, y + 3, name);
              count -= 1;
            }
          }
          text.setStyle("-fx-font-size: 3px;" + "-fx-font-weight: bold");
          Texts.add(text);
        } else if (check.equals("/")) {
          parent.getChildren().removeAll(Texts);
          return false;
        } else {

        }
      }
    }
    return true;
  }

  /** clear all the location names on the map */
  void clearLocationName() {
    if (!Texts.isEmpty()) parent.getChildren().removeAll(Texts);
  }

  /**
   * Display all the location names on the map when click the button
   *
   * @param event
   */
  @FXML
  void DisplayLocationClicked(MouseEvent event) {
    boolean check = ShowLocationName();
    if ((!Texts.isEmpty()) && check) {
      parent.getChildren().addAll(Texts);
    }
  }
}

class BoundLine extends Line {
  BoundLine(
      DoubleProperty startX, DoubleProperty startY, DoubleProperty endX, DoubleProperty endY) {
    startXProperty().bind(startX);
    startYProperty().bind(startY);
    endXProperty().bind(endX);
    endYProperty().bind(endY);
    setStrokeWidth(0.5);
    setStyle("-fx-stroke: blue;");
    setMouseTransparent(true);
  }
}
