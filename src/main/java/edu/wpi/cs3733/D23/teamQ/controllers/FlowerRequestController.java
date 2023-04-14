package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.db.Qdb;
import edu.wpi.cs3733.D23.teamQ.db.obj.FlowerRequest;
import edu.wpi.cs3733.D23.teamQ.navigation.Navigation;
import edu.wpi.cs3733.D23.teamQ.navigation.Screen;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXFilterComboBox;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import io.github.palexdev.materialfx.controls.MFXTextField;
import java.sql.Date;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

public class FlowerRequestController {
  Qdb qdb = Qdb.getInstance();
  @FXML MFXFilterComboBox assigneeField;
  @FXML MFXFilterComboBox roomNumberField;
  @FXML MFXDatePicker dateField;
  @FXML MFXTextField timeField;
  @FXML MFXFilterComboBox flowerTypeField;
  @FXML MFXTextField bouquetChoiceField;
  @FXML MFXTextField specialInstructionsField;
  ObservableList<String> TypeOfFlowers =
      FXCollections.observableArrayList("Roses", "Daisies", "Tulips", "Sunflowers", "Lilies");

  @FXML MFXButton resetButton;
  @FXML MFXButton cancelButton;
  @FXML MFXButton submitButton;

  /**
   * Initializes the Flower Request Choice Box's Switches screens to the Home page when Cancel
   * Button is pressed Clears fields when Clears Filters is pressed Switches screens to Flower
   * Request Submission page when Submit Button is pressed Gets values from the Flower Request Data
   */
  @FXML
  public void initialize() {
    this.assigneeField.setValue("Select an Assignee");
    this.assigneeField.setItems(qdb.getAllNames());
    this.roomNumberField.setValue("Select a Location");
    String[] conf = {"CONF"};
    this.roomNumberField.setItems(qdb.getAllLongNames(conf));
    this.flowerTypeField.setValue("Select Flower");
    this.flowerTypeField.setItems(TypeOfFlowers);
  }

  @FXML
  public void resetButtonClicked() {
    assigneeField.setValue("Select an Assignee");
    roomNumberField.setValue("Select a Location");
    dateField.clear();
    timeField.clear();
    flowerTypeField.setValue("Select Flower");
    bouquetChoiceField.clear();
    specialInstructionsField.clear();
  }

  @FXML
  public void cancelButtonClicked() {
    Navigation.navigateRight(Screen.SERVICE_PLACEHOLDER);
  }

  @FXML
  public void submitButtonClicked() {
    Qdb qdb = Qdb.getInstance();
    //        if (((String) bouquetChoiceField.getValue()).equals("Number of Bouquets")) {
    //          bouquetChoiceField.setValue("0");
    //        }

    FlowerRequest newFR =
        new FlowerRequest(
            LoginController.getUsername(),
            0,
            assigneeField.getSelectedItem().toString(),
            qdb.retrieveNode(qdb.getNodeFromLocation(roomNumberField.getSelectedItem().toString())),
            specialInstructionsField.getText(),
            Date.valueOf(dateField.getValue()),
            timeField.getText(),
            "",
            flowerTypeField.getSelectedItem().toString(),
            Integer.parseInt(bouquetChoiceField.getText()));

    qdb.addFlowerRequest(newFR);

    Navigation.navigateRight(Screen.SUBMISSION);
  }
}
