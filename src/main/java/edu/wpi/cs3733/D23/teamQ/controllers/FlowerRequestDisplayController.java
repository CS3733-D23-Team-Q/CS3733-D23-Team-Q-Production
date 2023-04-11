package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.db.Qdb;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;

public class FlowerRequestDisplayController {

  ObservableList<String> TypeOfFlowers =
      FXCollections.observableArrayList("Roses", "Daisies", "Tulips", "Sunflowers", "Lilies");

  @FXML MFXButton deleteButton;
  @FXML MFXButton backButton;
  @FXML MFXButton updateButton;

  @FXML ChoiceBox assigneeField;
  @FXML ChoiceBox roomNumberField;
  @FXML MFXDatePicker dateField;
  @FXML MFXTextField timeField;
  @FXML ChoiceBox flowerTypeField;
  @FXML MFXTextField bouquetChoiceField;
  @FXML MFXTextField specialInstructionsField;

  @FXML
  public void initialize() {
    flowerTypeField.setItems(TypeOfFlowers);

    assigneeField.setValue(ListServiceRequestController.getFlowerRequest().getAssignee());
    // roomNumberField.setValue(ListServiceRequestController.getFlowerRequest().getRoomNumber());
    // dateField.setValue(ListServiceRequestController.getFlowerRequest().getDate);
    // timeField.setText(ListServiceRequestController.getFlowerRequest().getTime);
    flowerTypeField.setValue(ListServiceRequestController.getFlowerRequest().getFlowerType());
    bouquetChoiceField.setText(
        String.valueOf(ListServiceRequestController.getFlowerRequest().getNumberOfBouquets()));
    specialInstructionsField.setText(
        ListServiceRequestController.getFlowerRequest().getSpecialInstructions());
  }

  @FXML
  public void deleteButtonClicked() {
    Qdb qdb = Qdb.getInstance();
    qdb.deleteFlowerRequest(ListServiceRequestController.getFlowerRequest().getRequestID());
  }

  @FXML
  public void backButtonClicked() {}

  @FXML
  public void updateButtonClicked() {
    /*
    Qdb qdb = Qdb.getInstance();

    FlowerRequest newFR = new FlowerRequest(
            //ListServiceRequestController.getFlowerRequest().getRequestID(),
            "temp requester",
            0,
            "temp assignee",
            (String) roomNumberField.getValue(),
            (String) specialInstructionsField.getText(),
            "",
            (String) flowerTypeField.getValue(),
            Integer.parseInt((String) bouquetChoiceField.getValue()));


    qdb.updateFlowerRequest(ListServiceRequestController.getFlowerRequest().getRequestID(), )

     */
  }
}
