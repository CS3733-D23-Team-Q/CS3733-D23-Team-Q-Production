package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.Alert;
import edu.wpi.cs3733.D23.teamQ.Confirm;
import edu.wpi.cs3733.D23.teamQ.db.Qdb;
import edu.wpi.cs3733.D23.teamQ.db.obj.Move;
import java.io.IOException;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
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

public class MoveController implements IController {

  static Stage stage;

  public void setStage(Stage stage) {
    this.stage = stage;
  }


  private String path;

  @FXML private TextField ImportPath;

  @FXML private TableColumn<Move, Number> MoveID;

  @FXML private TableView<Move> move;

  @FXML private TableColumn<Move, Number> nodeID;

  @FXML private TableColumn<Move, String> date;

  @FXML private TableColumn<Move, String> longName;

  Qdb qdb = Qdb.getInstance();

  /** used to put Moves from database arraylist to observablelist */
  public ObservableList<Move> moves() {
    ObservableList<Move> movelist = FXCollections.observableArrayList();
    for (int i = 0; i < qdb.retrieveAllMoves().size(); i++) {
      movelist.add(qdb.retrieveAllMoves().get(i));
    }
    return movelist;
  }

  public void initialize() {

    /** input the moveID to the table */
    MoveID.setCellValueFactory(
        new Callback<TableColumn.CellDataFeatures<Move, Number>, ObservableValue<Number>>() {
          @Override
          public ObservableValue<Number> call(TableColumn.CellDataFeatures<Move, Number> param) {
            SimpleIntegerProperty moveids = new SimpleIntegerProperty(param.getValue().getMoveID());
            return moveids;
          }
        });

    /** input the nodeId to the table */
    nodeID.setCellValueFactory(
        new Callback<TableColumn.CellDataFeatures<Move, Number>, ObservableValue<Number>>() {
          @Override
          public ObservableValue<Number> call(TableColumn.CellDataFeatures<Move, Number> param) {
            SimpleIntegerProperty nodeIDs =
                new SimpleIntegerProperty(param.getValue().getNode().getNodeID());
            return nodeIDs;
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
            SimpleStringProperty dates =
                new SimpleStringProperty(param.getValue().getDate().toString());
            return dates;
          }
        });

    /** set the move tableview */
    move.setItems(moves());
  }

  @FXML
  void ExportClicked(MouseEvent event) throws IOException {
    path = ImportPath.getText();
    boolean success = qdb.movesToCSV(path);
    if (success) {
      Confirm.confirmBox("Export Successfully", "Export Successfully");
    } else {
      Alert.alertBox("Failed to Export", "Failed to Export");
    }
    move.setItems(moves());
  }

  @FXML
  void ImportClicked(MouseEvent event) throws IOException {
    path = ImportPath.getText();
    boolean success = Qdb.getInstance().movesFromCSV(path);
    if (success) {
      Confirm.confirmBox("Import Successfully", "Import Successfully");
    } else {
      Alert.alertBox("Failed to Import", "Failed to Import");
    }
    move.setItems(moves());
  }

}
