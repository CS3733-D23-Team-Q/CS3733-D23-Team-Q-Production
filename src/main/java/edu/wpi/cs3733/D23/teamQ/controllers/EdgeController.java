package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.Alert;
import edu.wpi.cs3733.D23.teamQ.Confirm;
import edu.wpi.cs3733.D23.teamQ.db.Qdb;
import edu.wpi.cs3733.D23.teamQ.db.obj.Edge;
import java.io.IOException;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;

public class EdgeController implements IController {

  Qdb qdb = Qdb.getInstance();

  static Stage stage;

  public void setStage(Stage stage) {
    this.stage = stage;
  }

  private String path;
  Alert alert = new Alert();

  @FXML private TextField ImportPath;

  @FXML private TableView<Edge> edge;

  @FXML private TableColumn<Edge, Number> StartNode;

  @FXML private TableColumn<Edge, Number> EndNode;

  @FXML private TableColumn<Edge, Number> EdgeID;

  /** used to put Edges from database arraylist to observablelist */
  public ObservableList<Edge> edges() {
    ObservableList<Edge> edge = FXCollections.observableArrayList();
    for (int i = 0; i < qdb.retrieveAllEdges().size(); i++) {
      edge.add(qdb.retrieveAllEdges().get(i));
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

  @FXML
  void ExportClicked(MouseEvent event) throws IOException {
    path = ImportPath.getText();
    boolean success = qdb.edgesToCSV(path);
    if (success) {
      Confirm.confirmBox("Export Successfully", "Export Successfully");
    } else {
      alert.alertBox("Failed to Export", "Failed to Export");
    }
    edge.setItems(edges());
  }

  @FXML
  void ImportClicked(MouseEvent event) throws IOException {
    path = ImportPath.getText();
    boolean success = qdb.edgesFromCSV(path);
    if (success) {
      Confirm.confirmBox("Import Successfully", "Import Successfully");
    } else {
      Alert.alertBox("Failed to Import", "Failed to Import");
    }
    edge.setItems(edges());
  }
}
