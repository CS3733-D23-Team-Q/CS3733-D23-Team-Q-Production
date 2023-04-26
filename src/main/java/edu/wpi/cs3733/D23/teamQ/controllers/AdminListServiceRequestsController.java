package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.db.Qdb;
import edu.wpi.cs3733.D23.teamQ.db.obj.*;
import io.github.palexdev.materialfx.controls.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class AdminListServiceRequestsController {
  @FXML TableView<ServiceRequest> requestsTable;
  @FXML TableColumn<ServiceRequest, Integer> requestIDColumn;
  @FXML TableColumn<ServiceRequest, String> requesterColumn;
  @FXML TableColumn<ServiceRequest, String> assigneeColumn;
  @FXML TableColumn<ServiceRequest, String> dateColumn;
  @FXML TableColumn<ServiceRequest, String> locationColumn;
  @FXML TableColumn<ServiceRequest, String> instructionsColumn;
  @FXML TableColumn<ServiceRequest, ServiceRequest.Progress> progressColumn;
  @FXML TableColumn<ServiceRequest, MFXButton> editButtonColumn;
  @FXML TableColumn<ServiceRequest, MFXButton> deleteButtonColumn;

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

  ObservableList<String> medicalItemList =
      FXCollections.observableArrayList(
          "bandaids", "cotton balls", "gauze", "tongue depressers", "sterile syringe");

  ObservableList<ServiceRequest.Progress> progressValues =
      FXCollections.observableArrayList(
          ServiceRequest.Progress.BLANK,
          ServiceRequest.Progress.PROCESSING,
          ServiceRequest.Progress.DONE);

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

  @FXML VBox medicalRequestEdit;
  @FXML MFXFilterComboBox medicalAssigneeField;
  @FXML MFXFilterComboBox medicalLocationField;
  @FXML MFXDatePicker medicalDateField;
  @FXML MFXTextField medicalInstructionsField;
  @FXML MFXFilterComboBox medicalTimeField;
  @FXML MFXFilterComboBox medicalItemField;
  @FXML MFXTextField medicalQuantityField;

  @FXML MFXToggleButton toggleButton;

  private static ServiceRequest serviceRequestChange;
  private static FlowerRequest flowerRequest;
  private static ConferenceRequest conferenceRequest;

  private static MealRequest mealRequest;
  private static OfficeSuppliesRequest officeSuppliesRequest;

  private static FurnitureRequest furnitureRequest;
  private static MedicalSuppliesRequest medicalSuppliesRequest;

  Qdb qdb = Qdb.getInstance();
  String username = LoginController.getLoginUsername();

  ObservableList<ServiceRequest> allRequests = qdb.getAllServiceRequestsObservable();
  ObservableList<ServiceRequest> allOutstandingRequests = qdb.getAllOutstandingServingRequests();

  public void initialize() {
    toggleButton.setOnAction(
        event -> {
          if (toggleButton.isSelected()) {
            requestsTable.setItems(allOutstandingRequests);
          } else {
            requestsTable.setItems(allRequests);
          }
          requestsTable.refresh();
        });
    conferenceRequestEdit.setVisible(false);
    flowerRequestEdit.setVisible(false);
    officeRequestEdit.setVisible(false);
    furnitureRequestEdit.setVisible(false);
    mealRequestEdit.setVisible(false);
    medicalRequestEdit.setVisible(false);

    requestIDColumn.setCellValueFactory(new PropertyValueFactory<>("requestID"));
    locationColumn.setCellValueFactory(
        cellData -> {
          ServiceRequest serviceRequest = cellData.getValue();
          Node node = serviceRequest.getNode();
          Location location = node.getLocation();
          StringBinding locationNameBinding =
              Bindings.createStringBinding(() -> location.getLongName());
          return locationNameBinding;
        });
    assigneeColumn.setCellValueFactory(
        cellData -> {
          StringProperty assigneeProperty = new SimpleStringProperty();
          ServiceRequest sr = cellData.getValue();
          String assignee = sr.getAssignee().getUsername();
          assigneeProperty.set(assignee);
          return assigneeProperty;
        });
    instructionsColumn.setCellValueFactory(new PropertyValueFactory<>("specialInstructions"));
    dateColumn.setCellValueFactory(
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
                      serviceRequestChange = serviceRequest;
                      if (qdb.retrieveConferenceRequest(serviceRequest.getRequestID()) != null) {
                        conferenceRequestEdit.setVisible(true);
                        ConferenceRequest cr =
                            qdb.retrieveConferenceRequest(serviceRequest.getRequestID());
                        setConferenceFields(cr);
                        conferenceRequest = cr;
                      } else if (qdb.retrieveFlowerRequest(serviceRequest.getRequestID()) != null) {
                        flowerRequestEdit.setVisible(true);
                        FlowerRequest fr = qdb.retrieveFlowerRequest(serviceRequest.getRequestID());
                        setFlowerFields(fr);
                        flowerRequest = fr;
                      } else if (qdb.retrieveOfficeSuppliesRequest(serviceRequest.getRequestID())
                          != null) {
                        officeRequestEdit.setVisible(true);
                        OfficeSuppliesRequest or =
                            qdb.retrieveOfficeSuppliesRequest(serviceRequest.getRequestID());
                        setOfficeFields(or);
                        officeSuppliesRequest = or;
                      } else if (qdb.retrieveFurnitureRequest(serviceRequest.getRequestID())
                          != null) {
                        furnitureRequestEdit.setVisible(true);
                        FurnitureRequest fr =
                            qdb.retrieveFurnitureRequest(serviceRequest.getRequestID());
                        setFurnitureFields(fr);
                        furnitureRequest = fr;
                      } else if (qdb.retrieveMealRequest(serviceRequest.getRequestID()) != null) {
                        mealRequestEdit.setVisible(true);
                        MealRequest mr = qdb.retrieveMealRequest(serviceRequest.getRequestID());
                        setMealFields(mr);
                        mealRequest = mr;
                      } else {
                        medicalRequestEdit.setVisible(true);
                        MedicalSuppliesRequest mr =
                            qdb.retrieveMedicalSuppliesRequest(serviceRequest.getRequestID());
                        setMedicalFields(mr);
                        medicalSuppliesRequest = mr;
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
                        requestsTable.getItems().remove(serviceRequest);
                        requestsTable.refresh();
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

    progressColumn.setCellFactory(
        column -> {
          return new TableCell<ServiceRequest, ServiceRequest.Progress>() {
            private final MFXComboBox<ServiceRequest.Progress> comboBox = new MFXComboBox<>();

            {
              comboBox.setPrefHeight(20);
              comboBox.setItems(progressValues);
              comboBox.setOnAction(
                  event -> {
                    ServiceRequest serviceRequest = getTableView().getItems().get(getIndex());
                    serviceRequest.setProgress(comboBox.getValue());
                    qdb.updateServiceRequest(serviceRequest.getRequestID(), serviceRequest);
                    requestsTable.refresh();
                  });
            }

            @Override
            protected void updateItem(ServiceRequest.Progress item, boolean empty) {
              super.updateItem(item, empty);
              if (empty) {
                setGraphic(null);
              } else {
                ServiceRequest serviceRequest = getTableView().getItems().get(getIndex());
                comboBox.setValue(serviceRequest.getProgress());
                setGraphic(comboBox);
              }
            }
          };
        });

    requesterColumn.setCellValueFactory(
        cellData -> {
          StringProperty requesterProperty = new SimpleStringProperty();
          ServiceRequest sr = cellData.getValue();
          String requester = sr.getRequester().getUsername();
          requesterProperty.set(requester);
          return requesterProperty;
        });

    requestsTable.setItems(allRequests);
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

    Account assigneeAccount = cr.getAssignee();
    String assigneeString =
        assigneeAccount.getUsername()
            + ", ("
            + assigneeAccount.getFirstName()
            + " "
            + assigneeAccount.getLastName()
            + ", "
            + assigneeAccount.getTitle()
            + ")";

    confAssigneeField.setValue(assigneeString);
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
            qdb.retrieveAccount(confAssigneeField.getValue().toString().split(",")[0]),
            confInstructionsField.getText(),
            Date.valueOf(confDateField.getValue()),
            confTimeField.getText(),
            conferenceRequest.getProgress().ordinal(),
            confFoodField.getText());
    qdb.updateConferenceRequest(conferenceRequest.getRequestID(), cr);
    requestsTable.refresh();
    conferenceRequestEdit.setVisible(false);
  }

  public void setFlowerFields(FlowerRequest fr) {
    flowerTimeField.setItems(timeList);
    flowerAssigneeField.setItems(qdb.getAllNames());
    flowerChoiceField.setItems(TypeOfFlowers);
    flowerLocationField.setItems(qdb.getAllLongNames());

    Account assigneeAccount = fr.getAssignee();
    String assigneeString =
        assigneeAccount.getUsername()
            + ", ("
            + assigneeAccount.getFirstName()
            + " "
            + assigneeAccount.getLastName()
            + ", "
            + assigneeAccount.getTitle()
            + ")";

    flowerAssigneeField.setValue(assigneeString);
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

  public void flowerUpdateClicked() {
    FlowerRequest fr =
        new FlowerRequest(
            flowerRequest.getRequestID(),
            qdb.getNodeFromLocation(flowerLocationField.getText()),
            flowerRequest.getRequester(),
            qdb.retrieveAccount(flowerAssigneeField.getValue().toString().split(",")[0]),
            flowerInstructionsField.getText(),
            Date.valueOf(flowerDateField.getValue()),
            flowerTimeField.getText(),
            flowerRequest.getProgress().ordinal(),
            flowerChoiceField.getText(),
            Integer.parseInt(flowerBouquetField.getText()));

    qdb.updateFlowerRequest(flowerRequest.getRequestID(), fr);
    requestsTable.refresh();
    flowerRequestEdit.setVisible(false);
  }

  public void setOfficeFields(OfficeSuppliesRequest or) {
    officeItemField.setItems(itemList);
    officeAssigneeField.setItems(qdb.getAllLongNames());
    officeLocationField.setItems(qdb.getAllLongNames());
    officeTimeField.setItems(timeList);

    Account assigneeAccount = or.getAssignee();
    String assigneeString =
        assigneeAccount.getUsername()
            + ", ("
            + assigneeAccount.getFirstName()
            + " "
            + assigneeAccount.getLastName()
            + ", "
            + assigneeAccount.getTitle()
            + ")";

    officeAssigneeField.setValue(assigneeString);
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

  public void officeUpdateClicked() {
    OfficeSuppliesRequest or =
        new OfficeSuppliesRequest(
            officeSuppliesRequest.getRequestID(),
            qdb.getNodeFromLocation(officeLocationField.getText()),
            officeSuppliesRequest.getRequester(),
            qdb.retrieveAccount(officeAssigneeField.getValue().toString().split(",")[0]),
            officeInstructionsField.getText(),
            Date.valueOf(officeDateField.getValue()),
            officeTimeField.getText(),
            officeSuppliesRequest.getProgress().ordinal(),
            officeItemField.getText(),
            Integer.parseInt(officeQuantityField.getText()));
    qdb.updateOfficeSuppliesRequest(officeSuppliesRequest.getRequestID(), or);
    requestsTable.refresh();
    officeRequestEdit.setVisible(false);
  }

  public void setFurnitureFields(FurnitureRequest fr) {
    furnitureAssigneeField.setItems(qdb.getAllLongNames());
    furnitureLocationField.setItems(qdb.getAllLongNames());
    furnitureTimeField.setItems(timeList);
    furnitureChoiceField.setItems(furnitureList);

    Account assigneeAccount = fr.getAssignee();
    String assigneeString =
        assigneeAccount.getUsername()
            + ", ("
            + assigneeAccount.getFirstName()
            + " "
            + assigneeAccount.getLastName()
            + ", "
            + assigneeAccount.getTitle()
            + ")";

    furnitureAssigneeField.setValue(assigneeString);
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

  public void furnitureUpdateClicked() {
    FurnitureRequest fr =
        new FurnitureRequest(
            furnitureRequest.getRequestID(),
            qdb.getNodeFromLocation(furnitureLocationField.getText()),
            furnitureRequest.getRequester(),
            qdb.retrieveAccount(furnitureAssigneeField.getValue().toString().split(",")[0]),
            furnitureInstructionsField.getText(),
            Date.valueOf(furnitureDateField.getValue()),
            furnitureTimeField.getText(),
            furnitureRequest.getProgress().ordinal(),
            furnitureChoiceField.getText());
    qdb.updateFurnitureRequest(furnitureRequest.getRequestID(), fr);
    requestsTable.refresh();
    furnitureRequestEdit.setVisible(false);
  }

  private void setMealFields(MealRequest mr) {
    mealAssigneeField.setItems(qdb.getAllLongNames());
    mealLocationField.setItems(qdb.getAllLongNames());
    mealTimeField.setItems(timeList);
    mealDrinkField.setItems(drinkList);
    mealSideField.setItems(sideList);
    mealEntreeField.setItems(entreeList);

    Account assigneeAccount = mr.getAssignee();
    String assigneeString =
        assigneeAccount.getUsername()
            + ", ("
            + assigneeAccount.getFirstName()
            + " "
            + assigneeAccount.getLastName()
            + ", "
            + assigneeAccount.getTitle()
            + ")";

    mealAssigneeField.setValue(assigneeString);
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

  public void mealUpdateClicked() {
    MealRequest mr =
        new MealRequest(
            mealRequest.getRequestID(),
            qdb.getNodeFromLocation(mealLocationField.getText()),
            mealRequest.getRequester(),
            qdb.retrieveAccount(mealAssigneeField.getValue().toString().split(",")[0]),
            mealInstructionsField.getText(),
            Date.valueOf(mealDateField.getValue()),
            mealTimeField.getText(),
            mealRequest.getProgress().ordinal(),
            mealDrinkField.getText(),
            mealEntreeField.getText(),
            mealSideField.getText());
    qdb.updateMealRequest(mealRequest.getRequestID(), mr);
    requestsTable.refresh();
    mealRequestEdit.setVisible(false);
  }

  public void setMedicalFields(MedicalSuppliesRequest mr) {
    medicalItemField.setItems(medicalItemList);
    medicalAssigneeField.setItems(qdb.getAllLongNames());
    medicalLocationField.setItems(qdb.getAllLongNames());
    medicalTimeField.setItems(timeList);

    Account assigneeAccount = mr.getAssignee();
    String assigneeString =
        assigneeAccount.getUsername()
            + ", ("
            + assigneeAccount.getFirstName()
            + " "
            + assigneeAccount.getLastName()
            + ", "
            + assigneeAccount.getTitle()
            + ")";

    medicalAssigneeField.setValue(assigneeString);
    medicalTimeField.setText(mr.getTime());
    medicalLocationField.setText(qdb.retrieveLocation(mr.getNodeID()).getLongName());
    medicalInstructionsField.setText(mr.getSpecialInstructions());
    medicalDateField.setValue(
        LocalDate.of(
            mr.getDate().getYear() + 1900, mr.getDate().getMonth() + 1, mr.getDate().getDate()));
    medicalItemField.setText(mr.getItem());
    medicalQuantityField.setText(Integer.toString(mr.getQuantity()));
  }

  public void medicalCancelClicked(ActionEvent actionEvent) {
    medicalRequestEdit.setVisible(false);
  }

  public void medicalUpdateClicked(ActionEvent actionEvent) {
    MedicalSuppliesRequest mr =
        new MedicalSuppliesRequest(
            medicalSuppliesRequest.getRequestID(),
            qdb.getNodeFromLocation(medicalLocationField.getText()),
            medicalSuppliesRequest.getRequester(),
            qdb.retrieveAccount(medicalAssigneeField.getValue().toString().split(",")[0]),
            medicalInstructionsField.getText(),
            Date.valueOf(medicalDateField.getValue()),
            medicalTimeField.getText(),
            medicalSuppliesRequest.getProgress().ordinal(),
            medicalItemField.getText(),
            Integer.parseInt(medicalQuantityField.getText()));
    qdb.updateMedicalSuppliesRequest(medicalSuppliesRequest.getRequestID(), mr);
    requestsTable.refresh();
    medicalRequestEdit.setVisible(false);
  }
}
