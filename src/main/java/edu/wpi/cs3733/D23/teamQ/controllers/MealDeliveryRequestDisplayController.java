package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.db.Qdb;
import edu.wpi.cs3733.D23.teamQ.db.obj.MealRequest;
import edu.wpi.cs3733.D23.teamQ.navigation.Navigation;
import edu.wpi.cs3733.D23.teamQ.navigation.Screen;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import io.github.palexdev.materialfx.controls.MFXTextField;

import java.sql.Date;
import java.time.LocalDate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;

public class MealDeliveryRequestDisplayController {

    ObservableList<String> foodOptionsList =
            FXCollections.observableArrayList(
                    "Brunch spread", "Dinner spread", "Snack spread", "No food");

    @FXML
    MFXButton deleteButton;

    @FXML
    MFXButton backButton;

    @FXML
    MFXButton updateButton;
    @FXML
    ChoiceBox assigneeField;
    @FXML
    ChoiceBox roomNumberField;
    @FXML
    MFXDatePicker dateField;
    @FXML
    MFXTextField timeField;
    @FXML
    MFXTextField specialInstructionsField;

    @FXML
    ChoiceBox drinkField;
    @FXML
    ChoiceBox entreeField;
    @FXML
    ChoiceBox sideField;

    @FXML
    public void initialize() {

        assigneeField.setValue(ListServiceRequestController.getMealRequest().getAssignee());
        roomNumberField.setValue(ListServiceRequestController.getMealRequest().getNode());
        dateField.setValue(
                LocalDate.of(
                        ListServiceRequestController.getMealRequest().getDate().getYear(),
                        ListServiceRequestController.getMealRequest().getDate().getMonth(),
                        ListServiceRequestController.getMealRequest().getDate().getDay()));
        timeField.setText(ListServiceRequestController.getMealRequest().getTime());
        specialInstructionsField.setText(
                ListServiceRequestController.getMealRequest().getSpecialInstructions());
        drinkField.setValue(ListServiceRequestController.getMealRequest().getDrink());
        entreeField.setValue(ListServiceRequestController.getMealRequest().getEntree());
        sideField.setValue(ListServiceRequestController.getMealRequest().getSide());
    }

    @FXML
    public void deleteButtonClicked() {
        Qdb qdb = Qdb.getInstance();
        qdb.deleteConferenceRequest(ListServiceRequestController.getConferenceRequest().getRequestID());
    }

    @FXML
    public void backButtonClicked() {
        Navigation.navigate(Screen.HOME);
    }

    // Update with proper date and time
    @FXML
    public void updateButtonClicked() {
        Qdb qdb = Qdb.getInstance();

        MealRequest newMR =
                new MealRequest(
                        ListServiceRequestController.getMealRequest().getRequestID(),
                        ListServiceRequestController.getMealRequest().getNode(),
                        ListServiceRequestController.getMealRequest().getRequester(),
                        qdb.retrieveAccount(assigneeField.getValue().toString()),
                        specialInstructionsField.getText(),
                        Date.valueOf(dateField.getValue()),
                        timeField.getText(),
                        0,
                        (String) drinkField.getValue(),
                        (String) entreeField.getValue(),
                        (String) sideField.getValue());
        qdb.updateMealRequest(ListServiceRequestController.getMealRequest().getRequestID(), newMR);
    }
}
