package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.db.Qdb;
import edu.wpi.cs3733.D23.teamQ.db.obj.Edge;
import edu.wpi.cs3733.D23.teamQ.db.obj.Location;
import edu.wpi.cs3733.D23.teamQ.db.obj.Move;
import edu.wpi.cs3733.D23.teamQ.db.obj.Node;
import edu.wpi.cs3733.D23.teamQ.navigation.Navigation;
import edu.wpi.cs3733.D23.teamQ.navigation.Screen;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.application.Platform;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;

public class MapEditorTableController implements IController {
  Qdb qdb = Qdb.getInstance();
  @FXML private MenuItem exitItem;

  @FXML private MenuItem homeItem;

  @FXML private MFXButton BackHomeBTN;

  @FXML private TableColumn<Node, String> Building;

  @FXML private TableColumn<Move, String> Date;

  @FXML private TableColumn<Edge, Number> EndNode;

  @FXML private TableColumn<Node, String> Floor;

  @FXML private TableColumn<Location, String> LongName;

  @FXML private TableColumn<Node, Number> NodeID;

  @FXML private TableColumn<Location, String> NodeType;

  @FXML private TableColumn<Location, String> ShortName;

  @FXML private TableColumn<Edge, Number> StartNode;

  @FXML private TableColumn<Node, Number> Xcoord;

  @FXML private TableColumn<Node, Number> Ycoord;

  @FXML private TableView<Edge> edge;

  @FXML private TableView<Location> locationname;

  @FXML private TableView<Move> move;

  @FXML private TableView<Node> node;

  // these bugs will be solved after the database group set getAllRows() to static

  /** used to get Nodes from database */
  public ObservableList<Node> nodes() {
    ObservableList<Node> node = FXCollections.observableArrayList();
    for (int i = 0; i < qdb.retrieveAllNodes().size(); i++) {
      node.add(qdb.retrieveAllNodes().get(i));
    }
    return node;
  }

  /** used to get Locations from database */
  public ObservableList<Location> locations() {
    ObservableList<Location> location = FXCollections.observableArrayList();
    for (int i = 0; i < qdb.retrieveAllLocations().size(); i++) {
      location.add(qdb.retrieveAllLocations().get(i));
    }
    return location;
  }

  /** used to get Move from database */
  public ObservableList<Move> moves() {
    ObservableList<Move> move = FXCollections.observableArrayList();
    for (int i = 0; i < qdb.retrieveAllMoves().size(); i++) {
      move.add(qdb.retrieveAllMoves().get(i));
    }
    return move;
  }

  /** used to get Edge from database */
  public ObservableList<Edge> edges() {
    ObservableList<Edge> edge = FXCollections.observableArrayList();
    for (int i = 0; i < qdb.retrieveAllEdges().size(); i++) {
      edge.add(qdb.retrieveAllEdges().get(i));
    }
    return edge;
  }

  @FXML
  public void initialize() {

    /** Navigate to homepage after click on the button */
    BackHomeBTN.setOnMouseClicked((event -> Navigation.navigate(Screen.HOME)));

    // This part below is all about node tableview

    /** import the building data from node */
    Building.setCellValueFactory(
        new Callback<TableColumn.CellDataFeatures<Node, String>, ObservableValue<String>>() {
          @Override
          public ObservableValue<String> call(TableColumn.CellDataFeatures<Node, String> param) {
            SimpleStringProperty buildings =
                new SimpleStringProperty(param.getValue().getBuilding());
            return buildings;
          }
        });

    /** import the x_coord data from node */
    Xcoord.setCellValueFactory(
        new Callback<TableColumn.CellDataFeatures<Node, Number>, ObservableValue<Number>>() {
          @Override
          public ObservableValue<Number> call(TableColumn.CellDataFeatures<Node, Number> param) {
            SimpleIntegerProperty xcoords = new SimpleIntegerProperty(param.getValue().getXCoord());
            return xcoords;
          }
        });

    /** import the y_coord data from node */
    Ycoord.setCellValueFactory(
        new Callback<TableColumn.CellDataFeatures<Node, Number>, ObservableValue<Number>>() {
          @Override
          public ObservableValue<Number> call(TableColumn.CellDataFeatures<Node, Number> param) {
            SimpleIntegerProperty ycoords = new SimpleIntegerProperty(param.getValue().getYCoord());
            return ycoords;
          }
        });

    /** import the NodeID from node */
    NodeID.setCellValueFactory(
        new Callback<TableColumn.CellDataFeatures<Node, Number>, ObservableValue<Number>>() {
          @Override
          public ObservableValue<Number> call(TableColumn.CellDataFeatures<Node, Number> param) {
            SimpleIntegerProperty nodeids = new SimpleIntegerProperty(param.getValue().getNodeID());
            return nodeids;
          }
        });

    /** import the Floor from node */
    Floor.setCellValueFactory(
        new Callback<TableColumn.CellDataFeatures<Node, String>, ObservableValue<String>>() {
          @Override
          public ObservableValue<String> call(TableColumn.CellDataFeatures<Node, String> param) {
            SimpleStringProperty floors = new SimpleStringProperty(param.getValue().getFloor());
            return floors;
          }
        });

    /** set the node tableview */
    node.setItems(nodes());

    // This part below is all about Location

    /** import the long-name from location */
    LongName.setCellValueFactory(
        new Callback<TableColumn.CellDataFeatures<Location, String>, ObservableValue<String>>() {
          @Override
          public ObservableValue<String> call(
              TableColumn.CellDataFeatures<Location, String> param) {
            SimpleStringProperty longnames =
                new SimpleStringProperty(param.getValue().getLongName());
            return longnames;
          }
        });

    /** import the short-name from location */
    ShortName.setCellValueFactory(
        new Callback<TableColumn.CellDataFeatures<Location, String>, ObservableValue<String>>() {
          @Override
          public ObservableValue<String> call(
              TableColumn.CellDataFeatures<Location, String> param) {
            SimpleStringProperty shortnames =
                new SimpleStringProperty(param.getValue().getShortName());
            return shortnames;
          }
        });

    /** import the node_type */
    NodeType.setCellValueFactory(
        new Callback<TableColumn.CellDataFeatures<Location, String>, ObservableValue<String>>() {
          @Override
          public ObservableValue<String> call(
              TableColumn.CellDataFeatures<Location, String> param) {
            SimpleStringProperty nodetype =
                new SimpleStringProperty(param.getValue().getNodeType());
            return nodetype;
          }
        });

    /** set the location tableview */
    locationname.setItems(locations());

    // This part below is all about move

    /** import the date from Move */
    Date.setCellValueFactory(
        new Callback<TableColumn.CellDataFeatures<Move, String>, ObservableValue<String>>() {
          @Override
          public ObservableValue<String> call(TableColumn.CellDataFeatures<Move, String> param) {
            SimpleStringProperty date = new SimpleStringProperty(param.getValue().getDate());
            return date;
          }
        });

    /** set the move tableview */
    move.setItems(moves());

    // This part below is all about edge

    /** import the nodeID of the start node */
    StartNode.setCellValueFactory(
        new Callback<TableColumn.CellDataFeatures<Edge, Number>, ObservableValue<Number>>() {
          @Override
          public ObservableValue<Number> call(TableColumn.CellDataFeatures<Edge, Number> param) {
            SimpleIntegerProperty startnode =
                new SimpleIntegerProperty(param.getValue().getStartNode().getNodeID());
            return startnode;
          }
        });

    /** import the nodeID of the end node */
    EndNode.setCellValueFactory(
        new Callback<TableColumn.CellDataFeatures<Edge, Number>, ObservableValue<Number>>() {
          @Override
          public ObservableValue<Number> call(TableColumn.CellDataFeatures<Edge, Number> param) {
            SimpleIntegerProperty endnode =
                new SimpleIntegerProperty(param.getValue().getEndNode().getNodeID());
            return endnode;
          }
        });

    /** set the edge tableview */
    edge.setItems(edges());
  }

  public void homeItemClicked(javafx.event.ActionEvent actionEvent) {
    Navigation.navigate(Screen.HOME);
  }

  public void exitItemClicked(javafx.event.ActionEvent actionEvent) {
    Platform.exit();
  }
}
