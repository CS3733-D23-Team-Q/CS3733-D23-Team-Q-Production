package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.db.Qdb;
import edu.wpi.cs3733.D23.teamQ.navigation.Navigation;
import edu.wpi.cs3733.D23.teamQ.navigation.Screen;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;

public class FlowerRequestController {
  Qdb qdb = Qdb.getInstance();
  @FXML ChoiceBox assigneeField;
  @FXML ChoiceBox roomNumberField;
  @FXML MFXDatePicker dateField;
  @FXML MFXTextField timeField;
  @FXML ChoiceBox flowerTypeField;
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
    this.flowerTypeField.setValue("Select Flower");
    this.flowerTypeField.setItems(TypeOfFlowers);
    this.roomNumberField.setValue("Select a Location");
//    this.roomNumberField.setValue(qdb.getAllLongNames());
  }

  @FXML
  public void resetButtonClicked() {}

  @FXML
  public void cancelButtonClicked() {
    Navigation.navigate(Screen.SERVICE_PLACEHOLDER);
  }

  @FXML
  public void submitButtonClicked() {
    //    if (((String) bouquetChoiceField.getValue()).equals("Number of Bouquets")) {
    //      bouquetChoiceField.setValue("0");
    //    }
    //    FlowerRequest newFR =
    //        new FlowerRequest(
    //            0,
    //            "temp user",
    //            0,
    //            assigneeField.getText(),
    //            roomNumberField.getText(),
    //            specialInstructionsField.getText(),
    //            noteField.getText(),
    //            (String) flowerChoiceField.getValue(),
    //            Integer.parseInt((String) bouquetChoiceField.getValue()));
    //    qdb.addFlowerRequest(newFR);
    //    Navigation.navigate(Screen.HOME);
  }
}
