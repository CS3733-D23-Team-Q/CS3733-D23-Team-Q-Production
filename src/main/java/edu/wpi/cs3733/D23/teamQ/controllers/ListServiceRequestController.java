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

public class ListServiceRequestController implements IController {
  @FXML Button homeButton;
  @FXML MenuItem homeItem;
  @FXML MenuItem exitItem;
  @FXML TableView<ServiceRequest> tableView;
  @FXML TableColumn<ServiceRequest, Integer> requestID;
  @FXML TableColumn<ServiceRequest, Integer> progress;
  @FXML TableColumn<ServiceRequest, String> requester;
  @FXML TableColumn<ServiceRequest, String> assignee;
  @FXML TableColumn<ServiceRequest, String> roomNumber;
  @FXML TableColumn<ServiceRequest, String> specialInstructions;

  @FXML Button selectButton;
  @FXML MenuItem profileItem;

  private static FlowerRequest flowerRequest;
  private static ConferenceRequest conferenceRequest;

  Qdb qdb = Qdb.getInstance();

  public ListServiceRequestController() throws SQLException {}

  @FXML
  public void initialize() {
    requestID.setCellValueFactory(new PropertyValueFactory<ServiceRequest, Integer>("requestID"));
    progress.setCellValueFactory(new PropertyValueFactory<ServiceRequest, Integer>("progress"));
    requester.setCellValueFactory(new PropertyValueFactory<ServiceRequest, String>("requester"));
    assignee.setCellValueFactory(new PropertyValueFactory<ServiceRequest, String>("assignee"));
    roomNumber.setCellValueFactory(new PropertyValueFactory<ServiceRequest, String>("roomNumber"));
    specialInstructions.setCellValueFactory(
        new PropertyValueFactory<ServiceRequest, String>("specialInstructions"));

    tableView.setItems((ObservableList<ServiceRequest>) qdb.retrieveAllServiceRequests());
  }

  @FXML
  public void homeButtonClicked() {
    Navigation.navigate(Screen.HOME);
  }

  @FXML
  public void selectButtonClicked() {
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

  @FXML
  public void homeItemClicked() {
    Navigation.navigate(Screen.HOME);
  }

  @FXML
  public void exitItemClicked() {
    Platform.exit();
  }

  public static ConferenceRequest getConferenceRequest() {
    return conferenceRequest;
  }

  public static FlowerRequest getFlowerRequest() {
    return flowerRequest;
  }

  @FXML
  public void profileItemClicked() {
    Navigation.navigate(Screen.PROFILE_PAGE);
  }
}
