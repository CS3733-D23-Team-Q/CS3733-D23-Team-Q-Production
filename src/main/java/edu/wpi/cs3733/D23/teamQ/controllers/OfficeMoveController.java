package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.db.Qdb;
import edu.wpi.cs3733.D23.teamQ.db.obj.Move;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import java.sql.Date;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class OfficeMoveController {
  Qdb qdb = Qdb.getInstance();
  @FXML MFXComboBox currentLocationField;
  @FXML MFXComboBox newLocationField;
  @FXML MFXDatePicker dateField;
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

  public void updateTable() {
    currentLocation.setCellValueFactory(
        cellData -> new SimpleStringProperty(cellData.getValue().getLongName()));
    newLocation.setCellValueFactory(
        cellData ->
            new SimpleStringProperty(
                qdb.locationTable
                    .retrieveRow(cellData.getValue().getNode().getNodeID())
                    .getLongName()));
    date.setCellValueFactory(
        cellData -> new SimpleStringProperty(cellData.getValue().getDate().toString()));

    futureMoves.setItems(getFutureMoves());
  }

  public void initialize() {
    updateTable();

    this.currentLocationField.setValue("Select Current Location");
    this.currentLocationField.setItems(qdb.getAllLongNames());
    this.newLocationField.setValue("Select Current Location");
    this.newLocationField.setItems(qdb.getAllLongNames());
  }

  public void resetButtonClicked() {
    this.currentLocationField.setValue("Select Current Location");
    this.newLocationField.setValue("Select Current Location");
    dateField.clear();
  }

  public void submitButtonClicked() {

    if (currentLocationField.getValue() != null
        && newLocationField.getValue() != null
        && dateField.getValue() != null) {
      Move newMove =
          new Move(
              qdb.retrieveNode(
                  qdb.getNodeFromLocation(currentLocationField.getSelectedItem().toString())),
              newLocationField.getSelectedItem().toString(),
              Date.valueOf(dateField.getValue()));

      qdb.addMove(newMove);
      updateTable();
    }
  }
}
