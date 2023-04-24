package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.db.Qdb;
import edu.wpi.cs3733.D23.teamQ.db.obj.*;
import edu.wpi.cs3733.D23.teamQ.db.obj.ConferenceRequest;
import edu.wpi.cs3733.D23.teamQ.db.obj.FlowerRequest;
import edu.wpi.cs3733.D23.teamQ.db.obj.FurnitureRequest;
import edu.wpi.cs3733.D23.teamQ.db.obj.ServiceRequest;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ListServiceRequestController {
  @FXML TableView<ServiceRequest> yourRequestsTable;
  @FXML TableColumn<ServiceRequest, Integer> yourRequestID;
  @FXML TableColumn<ServiceRequest, String> yourRequestProgress;
  @FXML TableColumn<ServiceRequest, String> yourRequestLocation;
  @FXML TableColumn<ServiceRequest, String> yourRequestInstructions;
  @FXML TableColumn<ServiceRequest, String> yourRequestAssignee;
  @FXML TableColumn<ServiceRequest, String> yourRequestDate;
  @FXML TableColumn<ServiceRequest, MFXButton> editButtonColumn;
  @FXML TableColumn<ServiceRequest, MFXButton> deleteButtonColumn;

  @FXML TableView<ServiceRequest> assignedRequestTable;
  @FXML TableColumn<ServiceRequest, Integer> assignedRequestID;
  @FXML TableColumn<ServiceRequest, String> assignedRequestLocation;
  @FXML TableColumn<ServiceRequest, String> assignedRequestInstructions;
  @FXML TableColumn<ServiceRequest, String> assignedRequestRequester;
  @FXML TableColumn<ServiceRequest, String> assignedRequestDate;
  @FXML TableColumn<ServiceRequest, MFXComboBox> progressDropdownColumn;

  private static FlowerRequest flowerRequest;
  private static ConferenceRequest conferenceRequest;

  private static MealRequest mealRequest;
  private static OfficeSuppliesRequest officeSuppliesRequest;

  private static FurnitureRequest furnitureRequest;
  private static MedicalSuppliesRequest medicalSuppliesRequest;

  Qdb qdb = Qdb.getInstance();
  String username = LoginController.getLoginUsername();
  ObservableList<ServiceRequest> userAssignedRequests =
      qdb.retrieveUserAssignServiceRequests(username);
  ObservableList<ServiceRequest> userRequestedRequests = qdb.getUserRequestedRows(username);

  public ListServiceRequestController() {}

  @FXML
  public void initialize() {
    String username = LoginController.getLoginUsername();
    System.out.println(userAssignedRequests.size());
    System.out.println(userRequestedRequests.size());

    yourRequestID.setCellValueFactory(new PropertyValueFactory<>("requestID"));
    yourRequestProgress.setCellValueFactory(new PropertyValueFactory<>("progress"));

    yourRequestLocation.setCellValueFactory(
            cellData -> {
              StringProperty locationProperty = new SimpleStringProperty();
              ServiceRequest sr = cellData.getValue();
              String location = qdb.retrieveLocation(sr.getNodeID()).getLongName();
              locationProperty.set(location);
              return locationProperty;
            });

    yourRequestInstructions.setCellValueFactory(new PropertyValueFactory<>("specialInstructions"));

    yourRequestAssignee.setCellValueFactory(
        cellData -> {
          StringProperty assigneeProperty = new SimpleStringProperty();
          ServiceRequest sr = cellData.getValue();
          String assignee = sr.getAssignee().getUsername();
          assigneeProperty.set(assignee);
          return assigneeProperty;
        });

    yourRequestDate.setCellValueFactory(
            cellData -> {
              StringProperty dateProperty = new SimpleStringProperty();
              ServiceRequest sr = cellData.getValue();
              String dateTime = sr.getDate().toString() + " " + sr.getTime();
              dateProperty.set(dateTime);
              return dateProperty;
            });
    //    editButtonColumn.setCellValueFactory(new PropertyValueFactory<>("editButton"));
    //    deleteButtonColumn.setCellValueFactory(new PropertyValueFactory<>("deleteButton"));
    yourRequestsTable.setItems(userRequestedRequests);

    // Set data for assignedRequestTable
    assignedRequestID.setCellValueFactory(new PropertyValueFactory<>("requestID"));
    assignedRequestLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
    assignedRequestInstructions.setCellValueFactory(new PropertyValueFactory<>("instructions"));
    assignedRequestRequester.setCellValueFactory(new PropertyValueFactory<>("requester"));
    assignedRequestDate.setCellValueFactory(new PropertyValueFactory<>("date"));
    //    progressDropdownColumn.setCellValueFactory(new
    // PropertyValueFactory<>("progressDropdown"));
    assignedRequestTable.setItems(userAssignedRequests);
  }

  public static ConferenceRequest getConferenceRequest() {
    return conferenceRequest;
  }

  public static FlowerRequest getFlowerRequest() {
    return flowerRequest;
  }

  public static MealRequest getMealRequest() {
    return mealRequest;
  }

  public static FurnitureRequest getFurnitureRequest() {
    return furnitureRequest;
  }

  public static OfficeSuppliesRequest getOfficeRequest() {
    return officeSuppliesRequest;
  }

  public static MedicalSuppliesRequest getMedicalRequest() {
    return medicalSuppliesRequest;
  }
}
