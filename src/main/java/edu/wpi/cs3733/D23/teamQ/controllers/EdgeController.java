package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.Alert;
import edu.wpi.cs3733.D23.teamQ.db.Qdb;
import edu.wpi.cs3733.D23.teamQ.db.obj.Edge;
import edu.wpi.cs3733.D23.teamQ.navigation.Navigation;
import edu.wpi.cs3733.D23.teamQ.navigation.Screen;
import java.io.IOException;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

public class EdgeController {

  private String path;
  Alert alert = new Alert();
  /*

  private int newEdgeID;
  private Node newEndNode;
  private Node newStartNode;

   */

  @FXML private TextField ImportPath;
  /*

  @FXML private TextField EdgeIDInput;

  @FXML private TextField EndNodeInput;

  @FXML private TextField StartNodeInput;

   */

  @FXML private TableView<Edge> edge;

  @FXML private TableColumn<Edge, Number> StartNode;

  @FXML private TableColumn<Edge, Number> EndNode;

  @FXML private TableColumn<Edge, Number> EdgeID;

  /** used to put Edges from database arraylist to observablelist */
  public ObservableList<Edge> edges() {
    ObservableList<Edge> edge = FXCollections.observableArrayList();
    for (int i = 0; i < Qdb.getInstance().edgeTable.getAllRows().size(); i++) {
      edge.add(Qdb.getInstance().edgeTable.getAllRows().get(i));
    }
    return edge;
  }

  public void initialize() {
    /** input the start node to the table */
    StartNode.setCellValueFactory(
        new Callback<TableColumn.CellDataFeatures<Edge, Number>, ObservableValue<Number>>() {
          @Override
          public ObservableValue<Number> call(TableColumn.CellDataFeatures<Edge, Number> param) {
            SimpleIntegerProperty startnode =
                new SimpleIntegerProperty(param.getValue().getStartNode().getNodeID());
            return startnode;
          }
        });

    /** input the end node to the tablee */
    EndNode.setCellValueFactory(
        new Callback<TableColumn.CellDataFeatures<Edge, Number>, ObservableValue<Number>>() {
          @Override
          public ObservableValue<Number> call(TableColumn.CellDataFeatures<Edge, Number> param) {
            SimpleIntegerProperty endnode =
                new SimpleIntegerProperty(param.getValue().getEndNode().getNodeID());
            return endnode;
          }
        });

    /** input the edgeID to the table */
    EdgeID.setCellValueFactory(
        new Callback<TableColumn.CellDataFeatures<Edge, Number>, ObservableValue<Number>>() {
          @Override
          public ObservableValue<Number> call(TableColumn.CellDataFeatures<Edge, Number> param) {
            SimpleIntegerProperty edgeIDs = new SimpleIntegerProperty(param.getValue().getEdgeID());
            return edgeIDs;
          }
        });

    edge.setItems(edges());
  }

  /*
  @FXML
  void exitClicked(ActionEvent event) {
    Platform.exit();
  }

  @FXML
  void moveClicked(ActionEvent event) {
    Navigation.navigate(Screen.Move_Table);
  }

  @FXML
  void homeClicked(ActionEvent event) {
    Navigation.navigate(Screen.HOME);
  }

  @FXML
  void locationClicked(ActionEvent event) {
    Navigation.navigate(Screen.LocationName_Table);
  }

  @FXML
  void mapClicked(ActionEvent event) {}

  @FXML
  void nodeClicked(ActionEvent event) {
    Navigation.navigate(Screen.Node_Table);
  }

   */

  @FXML
  void ExportClicked(MouseEvent event) throws IOException {
    path = ImportPath.getText();
    boolean success = Qdb.getInstance().edgeTable.toCSV(path);
    if (success) {
      alert.alertBox("Export Successfully", "Export Successfully");
    } else {
      alert.alertBox("Failed to Export", "Failed to Export");
    }
  }

  @FXML
  void ImportClicked(MouseEvent event) throws IOException {
    path = ImportPath.getText();
    boolean success = Qdb.getInstance().edgeTable.importCSV(path);
    if (success) {
      Alert.alertBox("Import Successfully", "Import Successfully");
    } else {
      Alert.alertBox("Failed to Import", "Failed to Import");
    }
  }

  @FXML
  void BackClicked(ActionEvent event) {
    Navigation.navigate(Screen.MAP_EDITOR_TABLE);
  }
}
