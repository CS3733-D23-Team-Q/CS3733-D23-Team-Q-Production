package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.db.Qdb;
import edu.wpi.cs3733.D23.teamQ.db.obj.Node;
import edu.wpi.cs3733.D23.teamQ.navigation.Navigation;
import edu.wpi.cs3733.D23.teamQ.navigation.Screen;
import java.sql.SQLException;
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

public class NodeController {

  private int nodeIDEdit;

  private String newBuilding;

  private String newFloor;

  private int newXcoord;

  private int newYcoord;

  @FXML private TextField BuildingInput;

  @FXML private TextField FloorInput;

  @FXML private TextField NodeIDInput;

  @FXML private TextField XInput;

  @FXML private TextField YInput;

  @FXML private TableView<Node> node;

  @FXML private TableColumn<Node, Number> NodeID;

  @FXML private TableColumn<Node, String> Building;

  @FXML private TableColumn<Node, String> Floor;

  @FXML private TableColumn<Node, Number> Xcoord;

  @FXML private TableColumn<Node, Number> Ycoord;

  /** used to put Nodes from database arraylist to observablelist */
  public ObservableList<Node> nodes() {
    ObservableList<Node> node = FXCollections.observableArrayList();
    for (int i = 0; i < Qdb.getInstance().nodeTable.getAllRows().size(); i++) {
      node.add(Qdb.getInstance().nodeTable.getAllRows().get(i));
    }
    return node;
  }

  public void initialize() {

    /** input the building to the table */
    Building.setCellValueFactory(
        new Callback<TableColumn.CellDataFeatures<Node, String>, ObservableValue<String>>() {
          @Override
          public ObservableValue<String> call(TableColumn.CellDataFeatures<Node, String> param) {
            SimpleStringProperty buildings =
                new SimpleStringProperty(param.getValue().getBuilding());
            return buildings;
          }
        });

    /** input the Xcoord to the table */
    Xcoord.setCellValueFactory(
        new Callback<TableColumn.CellDataFeatures<Node, Number>, ObservableValue<Number>>() {
          @Override
          public ObservableValue<Number> call(TableColumn.CellDataFeatures<Node, Number> param) {
            SimpleIntegerProperty xcoords = new SimpleIntegerProperty(param.getValue().getXCoord());
            return xcoords;
          }
        });

    /** input the Ycoord to the table */
    Ycoord.setCellValueFactory(
        new Callback<TableColumn.CellDataFeatures<Node, Number>, ObservableValue<Number>>() {
          @Override
          public ObservableValue<Number> call(TableColumn.CellDataFeatures<Node, Number> param) {
            SimpleIntegerProperty ycoords = new SimpleIntegerProperty(param.getValue().getYCoord());
            return ycoords;
          }
        });

    /** input the nodeID to the table */
    NodeID.setCellValueFactory(
        new Callback<TableColumn.CellDataFeatures<Node, Number>, ObservableValue<Number>>() {
          @Override
          public ObservableValue<Number> call(TableColumn.CellDataFeatures<Node, Number> param) {
            SimpleIntegerProperty nodeids = new SimpleIntegerProperty(param.getValue().getNodeID());
            return nodeids;
          }
        });

    /** input the floor to the table */
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
  void moveClicked(ActionEvent event) {
    Navigation.navigate(Screen.Move_Table);
  }

  public void exitClicked(ActionEvent actionEvent) {
    Platform.exit();
  }

  @FXML
  void AddClicked(MouseEvent event) throws SQLException {
    /* test
    Node nodeexample = new Node(1, 1, 1, "test", "test");
    Qdb.getInstance().nodeTable.addRow(nodeexample);
     */

    /*
    if (BuildingInput.getText() != "") {
      if (FloorInput.getText() != "") {
        if (isNumber(NodeIDInput.getText())) {
          if (isNumber(XInput.getText())) {
            if (isNumber(YInput.getText())) {
            */
    /*
       nodeIDEdit = Integer.parseInt(NodeIDInput.getText());
       //      if (!nodeIDExist(nodeIDEdit)) {
       newBuilding = BuildingInput.getText();
       newFloor = FloorInput.getText();
       newXcoord = Integer.parseInt(XInput.getText());
       newYcoord = Integer.parseInt(YInput.getText());

       Qdb.getInstance()
           .nodeTable
           .addRow(new Node(nodeIDEdit, newXcoord, newYcoord, newFloor, newBuilding));
       node.setItems(nodes());

    */
    /*   } else {
                // Here to call alert "This nodeID exists, please use another one
              }

            } else {
              // Call Alert "Please input the correct Y-coord"
            }

          } else {
            // Call Alert "Please input the  correct X-coord"
          }
        } else {
          // Call Alert "Please input the correct nodeID"
        }
      } else {
        // Call Alert "Please input the correct Floor"
      }
    } else {
      // Call Alert "Please input the correct Building"

    }

            */
  }

  @FXML
  void DeleteClicked(MouseEvent event) throws SQLException {

    if (isNumber(NodeIDInput.getText())) {
      nodeIDEdit = Integer.parseInt(NodeIDInput.getText());

      if (nodeIDExist(nodeIDEdit)) {

        Qdb.getInstance().nodeTable.deleteRow(nodeIDEdit);
        node.setItems(nodes());

        // Here to call confirm
      } else {
        // here to call alert "This nodeID does not exist
      }

    } else {
      // Here to call alert "Please input the correct nodeID"

    }
  }

  @FXML
  void SetClicked(MouseEvent event) throws SQLException {
    if (BuildingInput != null) {
      if (FloorInput != null) {
        if (isNumber(NodeIDInput.getText())) {
          if (isNumber(XInput.getText())) {
            if (isNumber(YInput.getText())) {
              nodeIDEdit = Integer.parseInt(NodeIDInput.getText());
              if (nodeIDExist(nodeIDEdit)) {
                newBuilding = BuildingInput.getText();
                newFloor = FloorInput.getText();
                newXcoord = Integer.parseInt(XInput.getText());
                newYcoord = Integer.parseInt(YInput.getText());

                Qdb.getInstance()
                    .nodeTable
                    .updateRow(
                        nodeIDEdit,
                        new Node(nodeIDEdit, newXcoord, newYcoord, newFloor, newBuilding));
                // Navigation.navigate(Screen.Node_Table);
              } else {
                // Here to call alert "This nodeID does not exist"
              }

            } else {
              // Call Alert "Please input the correct Y-coord"
            }

          } else {
            // Call Alert "Please input the  correct X-coord"
          }
        } else {
          // Call Alert "Please input the correct nodeID"
        }
      } else {
        // Call Alert "Please input the correct Floor"
      }
    } else {
      // Call Alert "Please input the correct Building"

    }
  }

  public static boolean isNumber(String str) {
    if (str == "") return false;
    for (char c : str.toCharArray()) {
      if (!Character.isDigit(c)) {
        return false;
      }
    }
    return true;
  }

  public static boolean nodeIDExist(int nodeID) {
    for (int i = 0; i < Qdb.getInstance().nodeTable.getAllRows().size(); i++) {
      if (nodeID == Qdb.getInstance().nodeTable.getAllRows().get(i).getNodeID()) return true;
    }
    return false;
  }
}
