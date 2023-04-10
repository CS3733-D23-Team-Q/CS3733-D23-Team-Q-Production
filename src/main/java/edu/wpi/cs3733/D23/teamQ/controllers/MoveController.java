package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.db.Qdb;
import edu.wpi.cs3733.D23.teamQ.db.obj.Move;
import edu.wpi.cs3733.D23.teamQ.db.obj.Node;
import edu.wpi.cs3733.D23.teamQ.navigation.Navigation;
import edu.wpi.cs3733.D23.teamQ.navigation.Screen;
import javafx.application.Platform;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
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

public class MoveController {

  private String path;

  @FXML private TextField ImportPath;

  private int newNodeID;
  private String newLongName;
  private Node newNode;
  private String newDate;

  @FXML private TextField DateInput;

  @FXML private TextField LongNameInput;

  @FXML private TextField NodeIDInput;

  @FXML private TableView<Move> move;

  @FXML private TableColumn<Move, Number> nodeID;

  @FXML private TableColumn<Move, String> date;

  @FXML private TableColumn<Move, String> longName;

  /** used to put Moves from database arraylist to observablelist */
  public ObservableList<Move> moves() {
    ObservableList<Move> move = FXCollections.observableArrayList();
    for (int i = 0; i < Qdb.getInstance().moveTable.getAllRows().size(); i++) {
      move.add(Qdb.getInstance().moveTable.getAllRows().get(i));
    }
    return move;
  }

  public void initialize() {

    /** input the moveId to the table */
    nodeID.setCellValueFactory(
        new Callback<TableColumn.CellDataFeatures<Move, Number>, ObservableValue<Number>>() {
          @Override
          public ObservableValue<Number> call(TableColumn.CellDataFeatures<Move, Number> param) {
            SimpleIntegerProperty moveIDs =
                new SimpleIntegerProperty(param.getValue().getNode().getNodeID());
            return moveIDs;
          }
        });

    /** input the longName to the table */
    longName.setCellValueFactory(
        new Callback<TableColumn.CellDataFeatures<Move, String>, ObservableValue<String>>() {
          @Override
          public ObservableValue<String> call(TableColumn.CellDataFeatures<Move, String> param) {
            SimpleStringProperty longnames =
                new SimpleStringProperty(param.getValue().getLongName());
            return longnames;
          }
        });

    /** input the date to the table */
    date.setCellValueFactory(
        new Callback<TableColumn.CellDataFeatures<Move, String>, ObservableValue<String>>() {
          @Override
          public ObservableValue<String> call(TableColumn.CellDataFeatures<Move, String> param) {
            SimpleStringProperty date = new SimpleStringProperty(param.getValue().getDate());
            return date;
          }
        });

    /** set the move tableview */
    move.setItems(moves());
  }

  @FXML
  void ExitClicked(ActionEvent event) {
    Platform.exit();
  }

  @FXML
  void edgeClicked(ActionEvent event) {
    Navigation.navigate(Screen.Edge_Table);
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

  @FXML
  void AddClicked(MouseEvent event) {
    Move moveex = new Move(10, Qdb.getInstance().nodeTable.retrieveRow(1), "test", "test");
    Qdb.getInstance().moveTable.updateRow(1, moveex);
    move.setItems(moves());
  }

  @FXML
  void DeleteInput(MouseEvent event) {
    if (NodeController.isNumber(NodeIDInput.getText())) {
      newNodeID = Integer.parseInt(NodeIDInput.getText());

      if (nodeIDExistMove(newNodeID)) {
        Qdb.getInstance().locationTable.deleteRow(newNodeID);
        move.setItems(moves());

        // Here to call confirm
      } else {
        // here to call alert "This nodeID does not exist
      }

    } else {
      // Here to call alert "Please input the correct nodeID"

    }
  }

  @FXML
  void SetClicked(MouseEvent event) {}

  public boolean nodeIDExistMove(int nodeID) {
    for (int i = 0; i < Qdb.getInstance().moveTable.getAllRows().size(); i++) {
      if (nodeID == Qdb.getInstance().moveTable.getAllRows().get(i).getNode().getNodeID())
        return true;
    }
    return false;
  }

  @FXML
  void ExportClicked(MouseEvent event) {
    path = ImportPath.getText();
    Qdb.getInstance().nodeTable.toCSV(path);
  }

  @FXML
  void ImportClicked(MouseEvent event) {
    path = ImportPath.getText();
    Qdb.getInstance().nodeTable.importCSV(path);
  }
}
