package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.db.Qdb;
import edu.wpi.cs3733.D23.teamQ.db.obj.Location;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;

public class LocationController {

  @FXML private Button homeButton;

  @FXML private TableColumn<Location, String> LongName;

  @FXML private TableColumn<Location, String> NodeType;

  @FXML private TableColumn<Location, String> ShortName;

  @FXML private TableView<Location> locationname;

  @FXML private TableColumn<Location, Number> NodeID;

  /** used to put Locations from database arraylist to observablelist */
  public ObservableList<Location> locations() {
    ObservableList<Location> location = FXCollections.observableArrayList();
    for (int i = 0; i < Qdb.getInstance().locationTable.getAllRows().size(); i++) {
      location.add(Qdb.getInstance().locationTable.getAllRows().get(i));
    }
    return location;
  }

  public void initialize() {

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

    NodeID.setCellValueFactory(
        new Callback<TableColumn.CellDataFeatures<Location, Number>, ObservableValue<Number>>() {
          @Override
          public ObservableValue<Number> call(
              TableColumn.CellDataFeatures<Location, Number> param) {
            SimpleIntegerProperty nodeids = new SimpleIntegerProperty(param.getValue().getNodeID());
            return nodeids;
          }
        });

    /** set the location tableview */
    locationname.setItems(locations());
  }

  @FXML
  void exitClicked(ActionEvent event) {
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
  void moveClicked(ActionEvent event) {
    Navigation.navigate(Screen.Move_Table);
  }

  @FXML
  void mapClicked(ActionEvent event) {}

  @FXML
  void nodeClicked(ActionEvent event) {
    Navigation.navigate(Screen.Node_Table);
  }
}
