package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.db.Qdb;
import edu.wpi.cs3733.D23.teamQ.db.obj.ConferenceRequest;
import edu.wpi.cs3733.D23.teamQ.db.obj.FlowerRequest;
import edu.wpi.cs3733.D23.teamQ.db.obj.ServiceRequest;
import edu.wpi.cs3733.D23.teamQ.navigation.Navigation;
import edu.wpi.cs3733.D23.teamQ.navigation.Screen;
import java.sql.SQLException;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ListServiceRequestController {
  @FXML TableView<ServiceRequest> tableView;
  @FXML TableColumn<ServiceRequest, Integer> requestID;
  @FXML TableColumn<ServiceRequest, Integer> progress;
  @FXML TableColumn<ServiceRequest, String> roomNumber;

  private static FlowerRequest flowerRequest;
  private static ConferenceRequest conferenceRequest;

  Qdb qdb = Qdb.getInstance();

  public ListServiceRequestController() {}

  @FXML
  public void initialize() {
    requestID.setCellValueFactory(new PropertyValueFactory<ServiceRequest, Integer>("requestID"));
    progress.setCellValueFactory(new PropertyValueFactory<ServiceRequest, Integer>("progress"));
    roomNumber.setCellValueFactory(new PropertyValueFactory<ServiceRequest, String>("roomNumber"));
    tableView.setItems((ObservableList<ServiceRequest>) qdb.retrieveAllServiceRequests());
  }


  @FXML
  public void rowSelected() {
    if (qdb.retrieveFlowerRequest(
            tableView.getSelectionModel().getSelectedItems().get(0).getRequestID())
        != null) {
      flowerRequest =
          qdb.retrieveFlowerRequest(
              tableView.getSelectionModel().getSelectedItems().get(0).getRequestID());
      Navigation.navigate(Screen.FLOWER_REQUEST_DISPLAY);
    } else if (qdb.retrieveConferenceRequest(
            tableView.getSelectionModel().getSelectedItems().get(0).getRequestID())
        != null) {
      conferenceRequest =
          qdb.retrieveConferenceRequest(
              tableView.getSelectionModel().getSelectedItems().get(0).getRequestID());
      Navigation.navigate(Screen.CONFERENCE_ROOM_REQUEST_DISPLAY);
    }
  }


  public static ConferenceRequest getConferenceRequest() {
    return conferenceRequest;
  }

  public static FlowerRequest getFlowerRequest() {
    return flowerRequest;
  }
}
