package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.db.Qdb;
import edu.wpi.cs3733.D23.teamQ.db.obj.*;
import edu.wpi.cs3733.D23.teamQ.db.obj.ConferenceRequest;
import edu.wpi.cs3733.D23.teamQ.db.obj.FlowerRequest;
import edu.wpi.cs3733.D23.teamQ.db.obj.FurnitureRequest;
import edu.wpi.cs3733.D23.teamQ.db.obj.ServiceRequest;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import java.util.Optional;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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
  @FXML TableColumn<ServiceRequest, MFXComboBox<ServiceRequest.Progress>> progressDropdownColumn;

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

    yourRequestsTable.setStyle("-fx-table-column-border-visible: false;");
    assignedRequestTable.setStyle("-fx-table-column-border-visible: false;");

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

    editButtonColumn.setCellFactory(
        column -> {
          return new TableCell<ServiceRequest, MFXButton>() {
            private final MFXButton button = new MFXButton("Edit");

            @Override
            protected void updateItem(MFXButton item, boolean empty) {
              super.updateItem(item, empty);
              if (empty) {
                setGraphic(null);
              } else {
                ServiceRequest serviceRequest = getTableView().getItems().get(getIndex());
                button.setOnAction(
                    event -> {
                      System.out.println(
                          "Edit button clicked for: " + serviceRequest.getRequestID());
                    });
                Image image = new Image(getClass().getResourceAsStream("/EditButton.png"));
                ImageView imageView = new ImageView(image);
                imageView.setFitHeight(20.0);
                imageView.setFitWidth(20.0);
                button.setText("");
                button.setGraphic(imageView);
                button.setStyle("-fx-background-color: transparent;");
                setGraphic(button);
              }
            }
          };
        });

    deleteButtonColumn.setCellFactory(
        column -> {
          return new TableCell<ServiceRequest, MFXButton>() {
            private final Button button = new MFXButton("View Profile");

            @Override
            protected void updateItem(MFXButton item, boolean empty) {
              super.updateItem(item, empty);
              if (empty) {
                setGraphic(null);
              } else {
                ServiceRequest serviceRequest = getTableView().getItems().get(getIndex());
                button.setOnAction(
                    event -> {
                      Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                      alert.setTitle("Confirmation");
                      alert.setHeaderText("Delete Service Request");
                      alert.setContentText("Are you sure you want to delete this service request?");
                      alert.initOwner(button.getScene().getWindow());

                      Optional<ButtonType> result = alert.showAndWait();
                      if (result.isPresent() && result.get() == ButtonType.OK) {
                        qdb.deleteServiceRequest(serviceRequest.getRequestID());
                        yourRequestsTable.getItems().remove(serviceRequest);
                        yourRequestsTable.refresh();
                      }
                    });
                Image image = new Image(getClass().getResourceAsStream("/DeleteButton.png"));
                ImageView imageView = new ImageView(image);
                imageView.setFitHeight(20.0);
                imageView.setFitWidth(20.0);
                button.setText("");
                button.setGraphic(imageView);
                button.setStyle("-fx-background-color: transparent;");
                setGraphic(button);
              }
            }
          };
        });

    yourRequestsTable.setItems(userRequestedRequests);

    // Set data for assignedRequestTable
    assignedRequestID.setCellValueFactory(new PropertyValueFactory<>("requestID"));

    assignedRequestLocation.setCellValueFactory(
        cellData -> {
          StringProperty locationProperty = new SimpleStringProperty();
          ServiceRequest sr = cellData.getValue();
          String location = qdb.retrieveLocation(sr.getNodeID()).getLongName();
          locationProperty.set(location);
          return locationProperty;
        });

    assignedRequestInstructions.setCellValueFactory(
        new PropertyValueFactory<>("specialInstructions"));
    assignedRequestRequester.setCellValueFactory(
        cellData -> {
          StringProperty requesterProperty = new SimpleStringProperty();
          ServiceRequest sr = cellData.getValue();
          String requester = sr.getRequester().getUsername();
          requesterProperty.set(requester);
          return requesterProperty;
        });

    assignedRequestDate.setCellValueFactory(
        cellData -> {
          StringProperty dateProperty = new SimpleStringProperty();
          ServiceRequest sr = cellData.getValue();
          String dateTime = sr.getDate().toString() + " " + sr.getTime();
          dateProperty.set(dateTime);
          return dateProperty;
        });

    progressDropdownColumn.setCellFactory(
        column -> {
          return new TableCell<ServiceRequest, MFXComboBox<ServiceRequest.Progress>>() {
            private final MFXComboBox<ServiceRequest.Progress> comboBox = new MFXComboBox<>();

            {
              // Set up the options for the ComboBox
              comboBox.setPrefHeight(20);
              comboBox.getItems().addAll(ServiceRequest.Progress.values());
              // Set the value of the ComboBox when changed
              comboBox.setOnAction(
                  event -> {
                    ServiceRequest serviceRequest = getTableView().getItems().get(getIndex());
                    serviceRequest.setProgress(comboBox.getValue());
                    // Update the ServiceRequest object and refresh the TableView
                    // with the new value
                    // You may need to call a method to update the progress
                    // in your application logic
                    yourRequestsTable.refresh();
                  });
            }

            @Override
            protected void updateItem(MFXComboBox<ServiceRequest.Progress> item, boolean empty) {
              super.updateItem(item, empty);
              if (empty) {
                setGraphic(null);
              } else {
                ServiceRequest serviceRequest = getTableView().getItems().get(getIndex());
                // Set the value of the ComboBox based on the progress
                // value of the ServiceRequest object
                comboBox.setValue(serviceRequest.getProgress());
                setGraphic(comboBox);
              }
            }
          };
        });

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
