package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.db.Qdb;
import edu.wpi.cs3733.D23.teamQ.db.obj.Move;
import edu.wpi.cs3733.D23.teamQ.db.obj.Node;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import java.sql.Date;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.controlsfx.control.SearchableComboBox;

public class OfficeMoveController {
  Qdb qdb = Qdb.getInstance();
  @FXML SearchableComboBox currentLocationField;
  @FXML SearchableComboBox newLocationField;
  @FXML MFXDatePicker dateField;
  @FXML Label submitMessage;
  @FXML private TableView<Move> futureMoves;
  @FXML private TableColumn<Move, String> currentLocation;
  @FXML private TableColumn<Move, String> newLocation;
  @FXML private TableColumn<Move, String> date;

  public ObservableList<Move> getFutureMoves() {
    List<Move> allMoves = qdb.moveTable.getAllRows();
    ObservableList<Move> futureMoves = FXCollections.observableArrayList();

    Date currentDate = new Date(System.currentTimeMillis());
    for (Move move : allMoves) {
      if (move.getDate().compareTo(currentDate) >= 0) {
        futureMoves.add(move);
      }
    }

    futureMoves.sort((move1, move2) -> move1.getDate().compareTo(move2.getDate()));
    return futureMoves;
  }

  public ObservableList<Integer> getNodeIDs() {
    List<Node> allNodes = qdb.nodeTable.getAllRows();
    ObservableList<Integer> ids = FXCollections.observableArrayList();

    for (Node node : allNodes) {
      ids.add(node.getNodeID());
    }
    return ids;
  }

  public void updateTable() {
    currentLocation.setCellValueFactory(
        cellData -> new SimpleStringProperty(cellData.getValue().getLongName()));
    newLocation.setCellValueFactory(
        cellData ->
            new SimpleStringProperty(Integer.toString(cellData.getValue().getNode().getNodeID())));
    date.setCellValueFactory(
        cellData -> new SimpleStringProperty(cellData.getValue().getDate().toString()));

    futureMoves.setItems(getFutureMoves());
  }

  public void initialize() {
    updateTable();

    this.currentLocationField.setValue("Select Location");
    this.currentLocationField.setItems(qdb.getAllLongNames());
    this.newLocationField.setValue("Select New Node ID");
    this.newLocationField.setItems(getNodeIDs());
  }

  public void resetButtonClicked() {
    this.currentLocationField.setValue("Select Current Location");
    this.newLocationField.setValue("Select New Node ID");
    dateField.clear();
  }

  public void submitButtonClicked() {

    if (currentLocationField.getValue() != null
        && newLocationField.getValue() != null
        && dateField.getValue() != null) {
      Move newMove =
          new Move(
              qdb.retrieveNode(Integer.parseInt(newLocationField.getValue().toString())),
              newLocationField.getValue().toString(),
              Date.valueOf(dateField.getValue()));

      qdb.addMove(newMove);
      this.currentLocationField.setValue("Select Current Location");
      this.newLocationField.setValue("Select New Node ID");
      dateField.clear();
      updateTable();
      submitMessage.setText("Move Submitted!");
    } else {
      submitMessage.setText("Please fill in all the required fields.");
    }
  }
}
