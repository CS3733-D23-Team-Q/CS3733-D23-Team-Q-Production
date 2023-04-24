package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.db.Qdb;
import edu.wpi.cs3733.D23.teamQ.db.obj.*;
import edu.wpi.cs3733.D23.teamQ.db.obj.ConferenceRequest;
import edu.wpi.cs3733.D23.teamQ.db.obj.FlowerRequest;
import edu.wpi.cs3733.D23.teamQ.db.obj.FurnitureRequest;
import edu.wpi.cs3733.D23.teamQ.db.obj.ServiceRequest;
import io.github.palexdev.materialfx.controls.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

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

  ObservableList<String> timeList =
      FXCollections.observableArrayList(
          "00:30", "01:00", "01:30", "02:00", "02:30", "03:00", "03:30", "04:00", "04:30", "05:00",
          "05:30", "06:00", "06:30", "07:00", "07:30", "08:00", "08:30", "09:00", "09:30", "10:00",
          "10:30", "11:00", "11:30", "12:00", "12:30", "13:00", "13:30", "14:00", "14:30", "15:00",
          "15:30", "16:00", "16:30", "17:00", "17:30", "18:00", "18:30", "19:00", "19:30", "20:00",
          "20:30", "21:00", "21:30", "22:00", "22:30", "23:00", "23:30", "24:00");

  ObservableList<String> foodOptionsList =
      FXCollections.observableArrayList(
          "Brunch spread", "Dinner spread", "Snack spread", "No food");

  @FXML VBox conferenceRequestEdit;
  @FXML MFXFilterComboBox confAssigneeField;
  @FXML MFXFilterComboBox confLocationField;
  @FXML MFXDatePicker confDateField;
  @FXML MFXFilterComboBox confTimeField;
  @FXML MFXFilterComboBox confFoodField;
  @FXML MFXTextField confInstructionsField;

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
    conferenceRequestEdit.setVisible(false);

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
                      if (qdb.retrieveConferenceRequest(serviceRequest.getRequestID()) != null) {
                        conferenceRequestEdit.setVisible(true);
                        setConferenceFields(
                            qdb.retrieveConferenceRequest(serviceRequest.getRequestID()));
                        conferenceRequest =
                            qdb.retrieveConferenceRequest(serviceRequest.getRequestID());
                      }
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

  public void confCancelClicked() {
    conferenceRequestEdit.setVisible(false);
  }

  public void setConferenceFields(ConferenceRequest cr) {
    confTimeField.setItems(timeList);
    confAssigneeField.setItems(qdb.getAllNames());
    String[] nodeType = {"CONF"};
    confLocationField.setItems(qdb.getAllLongNames(nodeType));
    confFoodField.setItems(foodOptionsList);

    confAssigneeField.setText(cr.getAssigneeUsername());
    confTimeField.setText(cr.getTime());
    confLocationField.setText(qdb.retrieveLocation(cr.getNodeID()).getLongName());
    confFoodField.setText(cr.getFoodChoice());
    confInstructionsField.setText(cr.getSpecialInstructions());
    confDateField.setValue(
        LocalDate.of(
            cr.getDate().getYear() + 1900, cr.getDate().getMonth() + 1, cr.getDate().getDate()));
  }

  public void confUpdateClicked() {
    ConferenceRequest cr =
        new ConferenceRequest(
            conferenceRequest.getRequestID(),
            conferenceRequest.getNode(),
            qdb.retrieveAccount(confAssigneeField.getText()),
            conferenceRequest.getRequester(),
            confInstructionsField.getText(),
            Date.valueOf(confDateField.getValue()),
            confTimeField.getText(),
            conferenceRequest.getProgress().ordinal(),
            confFoodField.getText());
    qdb.updateConferenceRequest(conferenceRequest.getRequestID(), cr);
    conferenceRequestEdit.setVisible(false);
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
