package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.db.Qdb;
import edu.wpi.cs3733.D23.teamQ.db.obj.*;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import io.github.palexdev.materialfx.controls.MFXFilterComboBox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
    @FXML TableColumn<ServiceRequest, String> progressColumn;
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

    @FXML
    VBox conferenceRequestEdit;
    @FXML
    MFXFilterComboBox confAssigneeField;
    @FXML MFXFilterComboBox confLocationField;
    @FXML
    MFXDatePicker confDateField;
    @FXML MFXFilterComboBox confTimeField;
    @FXML MFXFilterComboBox confFoodField;
    @FXML
    MFXTextField confInstructionsField;

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

    public void initialize() {
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


    }
}
