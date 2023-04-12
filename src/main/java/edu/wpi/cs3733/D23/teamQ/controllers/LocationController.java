package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.Alert;
import edu.wpi.cs3733.D23.teamQ.Confirm;
import edu.wpi.cs3733.D23.teamQ.db.Qdb;
import edu.wpi.cs3733.D23.teamQ.db.obj.Location;
import java.io.IOException;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

public class LocationController {

  Alert alert = new Alert();

  private String path;

  @FXML private TextField ImportPath;

  @FXML private Label nodeIDAlert;

  @FXML private Label InformationAlert;

  @FXML private ImageView image;

  @FXML private ImageView image2;

  private int nodeIDedit;

  private String newLongName;

  private String newShortName;

  private String newNodeType;

  @FXML private TextField LongNameInput;

  @FXML private TextField NodeTypeInput;

  @FXML private TextField ShortNameInput;

  @FXML private TextField NodeIDEdit;

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

  /*
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

   */

  @FXML
  void setClicked(MouseEvent event) {

    if (NodeController.isNumber(NodeIDEdit.getText())) {

      if (LongNameInput.getText() != "") {
        if (ShortNameInput.getText() != "") {
          if (NodeTypeInput.getText() != "") {
            nodeIDedit = Integer.parseInt(NodeIDEdit.getText());
            if (nodeIDExistLocation(nodeIDedit)) {
              alert.clearLabelAlert(InformationAlert, image2);
              alert.clearLabelAlert(nodeIDAlert, image);
              newLongName = LongNameInput.getText();
              newNodeType = NodeTypeInput.getText();
              newShortName = ShortNameInput.getText();

              Qdb.getInstance()
                  .locationTable
                  .updateRow(
                      nodeIDedit, new Location(nodeIDedit, newLongName, newShortName, newNodeType));

              locationname.setItems(locations());
            } else {
              alert.setLabelAlert("This nodeID does not exist.", nodeIDAlert, image);
              alert.clearLabelAlert(InformationAlert, image2);
              // Call Alert "This nodeID does not exist"
            }
          } else {
            alert.setLabelAlert("Please input the correct node type.", InformationAlert, image2);
            alert.clearLabelAlert(nodeIDAlert, image);
            // Call Alert "Please input the correct NodeType"
          }

        } else {
          alert.setLabelAlert("Please input the correct short name.", InformationAlert, image2);
          alert.clearLabelAlert(nodeIDAlert, image);
          // Call Alert "Please input the correct ShortName"
        }

      } else {
        alert.setLabelAlert("Please input the correct long name", InformationAlert, image2);
        alert.clearLabelAlert(nodeIDAlert, image);
        // Call Alert "Please input the correct LongName"
      }

    } else {
      alert.setLabelAlert("Please input the correct nodeID.", nodeIDAlert, image);
      alert.clearLabelAlert(InformationAlert, image2);
      // Call Alert "Please input the correct nodeID"
    }
  }

  @FXML
  void addClicked(MouseEvent event) {

    if (NodeController.isNumber(NodeIDEdit.getText())) {
      if (LongNameInput.getText() != "") {
        if (ShortNameInput.getText() != "") {
          if (NodeTypeInput.getText() != "") {
            nodeIDedit = Integer.parseInt(NodeIDEdit.getText());
            if (!nodeIDExistLocation(nodeIDedit)) {
              alert.clearLabelAlert(InformationAlert, image2);
              alert.clearLabelAlert(nodeIDAlert, image);
              newLongName = LongNameInput.getText();
              newNodeType = NodeTypeInput.getText();
              newShortName = ShortNameInput.getText();

              Qdb.getInstance()
                  .locationTable
                  .addRow(new Location(nodeIDedit, newLongName, newShortName, newNodeType));

              locationname.setItems(locations());
            } else {
              alert.setLabelAlert("This nodeID already exists", nodeIDAlert, image);
              alert.clearLabelAlert(InformationAlert, image2);
            }
          } else {
            alert.setLabelAlert("Please input the correct node type", InformationAlert, image2);
            alert.clearLabelAlert(nodeIDAlert, image);
            // Call Alert "Please input the correct NodeType"
          }

        } else {
          alert.setLabelAlert("Please input the correct short name", InformationAlert, image2);
          alert.clearLabelAlert(nodeIDAlert, image);
          // Call Alert "Please input the correct ShortName"
        }

      } else {
        alert.setLabelAlert("Please input the correct long name", InformationAlert, image2);
        alert.clearLabelAlert(nodeIDAlert, image);
        // Call Alert "Please input the correct LongName"
      }

    } else {
      alert.setLabelAlert("Please input the correct nodeID", nodeIDAlert, image);
      alert.clearLabelAlert(InformationAlert, image2);
      // Call Alert "Please input the correct nodeID"
    }
  }

  @FXML
  void deleteClicked(MouseEvent event) {
    if (NodeController.isNumber(NodeIDEdit.getText())) {
      nodeIDedit = Integer.parseInt(NodeIDEdit.getText());

      if (nodeIDExistLocation(nodeIDedit)) {
        alert.clearLabelAlert(InformationAlert, image2);
        alert.clearLabelAlert(nodeIDAlert, image);
        Qdb.getInstance().locationTable.deleteRow(nodeIDedit);
        locationname.setItems(locations());

        // Here to call confirm
      } else {
        alert.setLabelAlert("This nodeID does not exist", nodeIDAlert, image);
        alert.clearLabelAlert(InformationAlert, image2);
      }

    } else {
      alert.setLabelAlert("Please input the correct nodeID.", nodeIDAlert, image);
      alert.clearLabelAlert(InformationAlert, image2);
    }
  }

  public boolean nodeIDExistLocation(int nodeID) {
    for (int i = 0; i < Qdb.getInstance().locationTable.getAllRows().size(); i++) {
      if (nodeID == Qdb.getInstance().locationTable.getAllRows().get(i).getNodeID()) return true;
    }
    return false;
  }

  @FXML
  void ExportClicked(MouseEvent event) throws IOException {
    path = ImportPath.getText();
    boolean success = Qdb.getInstance().locationTable.toCSV(path);
    if (success) {
      Confirm.confirmBox("Export Successfully", "Export Successfully");
    } else {
      Alert.alertBox("Failed to Export", "Failed to Export");
    }
    locationname.setItems(locations());
  }

  @FXML
  void ImportClicked(MouseEvent event) throws IOException {
    path = ImportPath.getText();
    boolean success = Qdb.getInstance().locationTable.importCSV(path);
    if (success) {
      Confirm.confirmBox("Import Successfully", "Import Successfully");
    } else {
      Alert.alertBox("Failed to Import", "Failed to Import");
    }
    locationname.setItems(locations());
  }
  /*
   @FXML
   void BackClicked(ActionEvent event) {
     Navigation.navigate(Screen.MAP_EDITOR);
   }

  */
}
