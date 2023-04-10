package edu.wpi.cs3733.D23.teamQ.controllers;

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
  @FXML MFXTextField assigneeField;
  @FXML MFXTextField roomNumberField;
  @FXML MFXTextField specialInstructionsField;
  @FXML MFXDatePicker dateField;
  @FXML MFXTextField timeField;
  @FXML ChoiceBox flowerChoiceField;
  ObservableList<String> TypeOfFlowers =
      FXCollections.observableArrayList("Roses", "Daisies", "Tulips", "Sunflowers", "Lilies");
  @FXML MFXTextField bouquetChoiceField;

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
    this.flowerChoiceField.setValue("Select Flower");
    this.flowerChoiceField.setItems(TypeOfFlowers);
  }

  @FXML
  public void resetButtonClicked() {
    assigneeField.clear();
    roomNumberField.clear();
    specialInstructionsField.clear();
    flowerChoiceField.setValue("Select Flower");
    bouquetChoiceField.clear();
  }

  @FXML
  public void cancelButtonClicked() {
    Navigation.navigate(Screen.SERVICE_PLACEHOLDER);
  }

  @FXML
  public void submitButtonClicked() {
    //    Qdb qdb = Qdb.getInstance();
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
