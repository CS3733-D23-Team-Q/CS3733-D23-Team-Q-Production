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
  ObservableList<String> TypeOfFlowers =
      FXCollections.observableArrayList("Roses", "Daisies", "Tulips", "Sunflowers", "Lilies");
  ObservableList<String> itemList =
      FXCollections.observableArrayList(
          "Printer Paper (by ream)", "Pencil", "Pen", "Highlighter", "Notepad");

  ObservableList<String> furnitureList =
      FXCollections.observableArrayList("Desk", "Desk Chair", "Couch", "Examination Table");
  ObservableList<String> drinkList =
      FXCollections.observableArrayList("Water", "Coke", "Coffee", "Tea");
  ObservableList<String> entreeList =
      FXCollections.observableArrayList("Chicken", "Steak", "Pork", "Fish", "Vegetarian");
  ObservableList<String> sideList =
      FXCollections.observableArrayList("Fries", "Onion Rings", "Soup", "Salad");

  @FXML VBox conferenceRequestEdit;
  @FXML MFXFilterComboBox confAssigneeField;
  @FXML MFXFilterComboBox confLocationField;
  @FXML MFXDatePicker confDateField;
  @FXML MFXFilterComboBox confTimeField;
  @FXML MFXFilterComboBox confFoodField;
  @FXML MFXTextField confInstructionsField;

  @FXML VBox flowerRequestEdit;
  @FXML MFXFilterComboBox flowerAssigneeField;
  @FXML MFXFilterComboBox flowerLocationField;
  @FXML MFXDatePicker flowerDateField;
  @FXML MFXTextField flowerInstructionsField;
  @FXML MFXFilterComboBox flowerTimeField;
  @FXML MFXFilterComboBox flowerChoiceField;
  @FXML MFXTextField flowerBouquetField;

  @FXML VBox officeRequestEdit;
  @FXML MFXFilterComboBox officeAssigneeField;
  @FXML MFXFilterComboBox officeLocationField;
  @FXML MFXDatePicker officeDateField;
  @FXML MFXTextField officeInstructionsField;
  @FXML MFXFilterComboBox officeTimeField;
  @FXML MFXFilterComboBox officeItemField;
  @FXML MFXTextField officeQuantityField;

  @FXML VBox furnitureRequestEdit;
  @FXML MFXFilterComboBox furnitureAssigneeField;
  @FXML MFXFilterComboBox furnitureLocationField;
  @FXML MFXDatePicker furnitureDateField;
  @FXML MFXTextField furnitureInstructionsField;
  @FXML MFXFilterComboBox furnitureTimeField;
  @FXML MFXFilterComboBox furnitureChoiceField;

  @FXML VBox mealRequestEdit;
  @FXML MFXFilterComboBox mealAssigneeField;
  @FXML MFXFilterComboBox mealLocationField;
  @FXML MFXDatePicker mealDateField;
  @FXML MFXTextField mealInstructionsField;
  @FXML MFXFilterComboBox mealTimeField;
  @FXML MFXFilterComboBox mealDrinkField;
  @FXML MFXFilterComboBox mealSideField;
  @FXML MFXFilterComboBox mealEntreeField;

  private static ServiceRequest serviceRequestChange;
  private static FlowerRequest flowerRequest;
  private static ConferenceRequest conferenceRequest;

  private static MealRequest mealRequest;
  private static OfficeSuppliesRequest officeSuppliesRequest;

  private static FurnitureRequest furnitureRequest;
  private static MedicalSuppliesRequest medicalSuppliesRequest;

  Qdb qdb = Qdb.getInstance();
  String username = LoginController.getLoginUsername();

  // work on these
  ObservableList<ServiceRequest> userRequestedRequests =
      qdb.retrieveUserAssignServiceRequests(username);
  ObservableList<ServiceRequest> userAssignedRequests = qdb.getUserRequestedRows(username);

  public ListServiceRequestController() {}

  @FXML
  public void initialize() {
    String username = LoginController.getLoginUsername();
    conferenceRequestEdit.setVisible(false);
    flowerRequestEdit.setVisible(false);
    officeRequestEdit.setVisible(false);
    furnitureRequestEdit.setVisible(false);
    mealRequestEdit.setVisible(false);

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
          String assignee = sr.getRequester().getUsername();
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
                        serviceRequestChange = serviceRequest;
                      } else if (qdb.retrieveFlowerRequest(serviceRequest.getRequestID()) != null) {
                        flowerRequestEdit.setVisible(true);
                        setFlowerFields(qdb.retrieveFlowerRequest(serviceRequest.getRequestID()));
                        flowerRequest = qdb.retrieveFlowerRequest(serviceRequest.getRequestID());
                        serviceRequestChange = serviceRequest;
                      } else if (qdb.retrieveOfficeSuppliesRequest(serviceRequest.getRequestID())
                          != null) {
                        officeRequestEdit.setVisible(true);
                        setOfficeFields(
                            qdb.retrieveOfficeSuppliesRequest(serviceRequest.getRequestID()));
                        officeSuppliesRequest =
                            qdb.retrieveOfficeSuppliesRequest(serviceRequest.getRequestID());
                        serviceRequestChange = serviceRequest;
                      } else if (qdb.retrieveFurnitureRequest(serviceRequest.getRequestID())
                          != null) {
                        furnitureRequestEdit.setVisible(true);
                        setFurnitureFields(
                            qdb.retrieveFurnitureRequest(serviceRequest.getRequestID()));
                        furnitureRequest =
                            qdb.retrieveFurnitureRequest(serviceRequest.getRequestID());
                        serviceRequestChange = serviceRequest;
                      } else if (qdb.retrieveMealRequest(serviceRequest.getRequestID()) != null) {
                        mealRequestEdit.setVisible(true);
                        setMealFields(qdb.retrieveMealRequest(serviceRequest.getRequestID()));
                        mealRequest = qdb.retrieveMealRequest(serviceRequest.getRequestID());
                        serviceRequestChange = serviceRequest;
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
          String requester = sr.getAssignee().getUsername();
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
            qdb.getNodeFromLocation(confLocationField.getText()),
            conferenceRequest.getRequester(),
            qdb.retrieveAccount(confAssigneeField.getText()),
            confInstructionsField.getText(),
            Date.valueOf(confDateField.getValue()),
            confTimeField.getText(),
            conferenceRequest.getProgress().ordinal(),
            confFoodField.getText());
    qdb.updateConferenceRequest(conferenceRequest.getRequestID(), cr);
    //    yourRequestsTable.getItems().remove(serviceRequestChange);
    //    yourRequestsTable.refresh();
    conferenceRequestEdit.setVisible(false);
  }

  public void setFlowerFields(FlowerRequest fr) {
    flowerTimeField.setItems(timeList);
    flowerAssigneeField.setItems(qdb.getAllNames());
    flowerChoiceField.setItems(TypeOfFlowers);
    flowerLocationField.setItems(qdb.getAllLongNames());

    flowerAssigneeField.setText(fr.getAssigneeUsername());
    flowerTimeField.setText(fr.getTime());
    flowerLocationField.setText(qdb.retrieveLocation(fr.getNodeID()).getLongName());
    flowerInstructionsField.setText(fr.getSpecialInstructions());
    flowerDateField.setValue(
        LocalDate.of(
            fr.getDate().getYear() + 1900, fr.getDate().getMonth() + 1, fr.getDate().getDate()));
    flowerChoiceField.setText(fr.getFlowerType());
    flowerBouquetField.setText(Integer.toString(fr.getNumberOfBouquets()));
  }

  public void flowerCancelClicked() {
    flowerRequestEdit.setVisible(false);
  }

  public void flowerUpdateClicked() {}

  public void setOfficeFields(OfficeSuppliesRequest or) {
    officeItemField.setItems(itemList);
    officeAssigneeField.setItems(qdb.getAllLongNames());
    officeLocationField.setItems(qdb.getAllLongNames());
    officeTimeField.setItems(timeList);

    officeAssigneeField.setText(or.getAssigneeUsername());
    officeTimeField.setText(or.getTime());
    officeLocationField.setText(qdb.retrieveLocation(or.getNodeID()).getLongName());
    officeInstructionsField.setText(or.getSpecialInstructions());
    officeDateField.setValue(
        LocalDate.of(
            or.getDate().getYear() + 1900, or.getDate().getMonth() + 1, or.getDate().getDate()));
    officeItemField.setText(or.getItem());
    officeQuantityField.setText(Integer.toString(or.getQuantity()));
  }

  public void officeCancelClicked() {
    officeRequestEdit.setVisible(false);
  }

  public void officeUpdateClicked() {}

  public void setFurnitureFields(FurnitureRequest fr) {
    furnitureAssigneeField.setItems(qdb.getAllLongNames());
    furnitureLocationField.setItems(qdb.getAllLongNames());
    furnitureTimeField.setItems(timeList);
    furnitureChoiceField.setItems(furnitureList);

    furnitureAssigneeField.setText(fr.getAssigneeUsername());
    furnitureTimeField.setText(fr.getTime());
    furnitureLocationField.setText(qdb.retrieveLocation(fr.getNodeID()).getLongName());
    furnitureInstructionsField.setText(fr.getSpecialInstructions());
    furnitureDateField.setValue(
        LocalDate.of(
            fr.getDate().getYear() + 1900, fr.getDate().getMonth() + 1, fr.getDate().getDate()));
    furnitureChoiceField.setText(fr.getItem());
  }

  public void furnitureCancelClicked() {
    furnitureRequestEdit.setVisible(false);
  }

  public void furnitureUpdateClicked() {}

  private void setMealFields(MealRequest mr) {
    mealAssigneeField.setItems(qdb.getAllLongNames());
    mealLocationField.setItems(qdb.getAllLongNames());
    mealTimeField.setItems(timeList);
    mealDrinkField.setItems(drinkList);
    mealSideField.setItems(sideList);
    mealEntreeField.setItems(entreeList);

    mealAssigneeField.setText(mr.getAssigneeUsername());
    mealTimeField.setText(mr.getTime());
    mealLocationField.setText(qdb.retrieveLocation(mr.getNodeID()).getLongName());
    mealInstructionsField.setText(mr.getSpecialInstructions());
    mealDateField.setValue(
        LocalDate.of(
            mr.getDate().getYear() + 1900, mr.getDate().getMonth() + 1, mr.getDate().getDate()));
    mealEntreeField.setText(mr.getEntree());
    mealSideField.setText(mr.getSide());
    mealDrinkField.setText(mr.getDrink());
  }

  public void mealCancelClicked() {
    mealRequestEdit.setVisible(false);
  }

  public void mealUpdateClicked() {}

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
