package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.db.Qdb;
import edu.wpi.cs3733.D23.teamQ.db.obj.FlowerRequest;
import edu.wpi.cs3733.D23.teamQ.navigation.Navigation;
import edu.wpi.cs3733.D23.teamQ.navigation.Screen;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.MenuItem;

public class FlowerRequestEditController {
  @FXML private MFXTextField assigneeField;
  @FXML private MFXTextField roomNumberField;
  @FXML private MFXTextField noteField;
  @FXML private MFXTextField specialInstructionsField;
  @FXML private ChoiceBox flowerChoiceField;
  ObservableList<String> TypeOfFlowers =
      FXCollections.observableArrayList("Roses", "Daisies", "Tulips", "Sunflowers", "Lilies");
  @FXML private ChoiceBox bouquetChoiceField;
  ObservableList<String> NumOfBouquets = FXCollections.observableArrayList("1", "2", "3", "4", "5");

  @FXML Button resetButton;
  @FXML Button backButton;
  @FXML Button submitButton;

  @FXML MenuItem homeItem;

  @FXML MenuItem profileItem;

  /**
   * Initializes the Flower Request Choice Box's Switches screens to the Home page when Cancel
   * Button is pressed Clears fields when Clears Filters is pressed Switches screens to Flower
   * Request Submission page when Submit Button is pressed Gets values from the Flower Request Data
   */
  @FXML
  public void initialize() {
    this.flowerChoiceField.setValue("Select Flower");
    this.flowerChoiceField.setItems(TypeOfFlowers);
    this.bouquetChoiceField.setValue("Number of Bouquets");
    this.bouquetChoiceField.setItems(NumOfBouquets);
  }

  @FXML
  public void resetButtonClicked() {
    roomNumberField.clear();
    noteField.clear();
    specialInstructionsField.clear();
    flowerChoiceField.setValue("Select Flower");
    bouquetChoiceField.setValue("Number of Bouquets");
  }

  @FXML
  public void backButtonClicked() {
    Navigation.navigate(Screen.SERVICE_REQUEST_HUB);
  }

  @FXML
  public void submitButtonClicked() {
    Qdb qdb = Qdb.getInstance();
    if (((String) bouquetChoiceField.getValue()).equals("Number of Bouquets")) {
      bouquetChoiceField.setValue("0");
    }
    FlowerRequest newFR =
        new FlowerRequest(
            "temp user",
            0,
            assigneeField.getText(),
            roomNumberField.getText(),
            specialInstructionsField.getText(),
            noteField.getText(),
            (String) flowerChoiceField.getValue(),
            Integer.parseInt((String) bouquetChoiceField.getValue()));
    qdb.updateFlowerRequest(ListServiceRequestController.getFlowerRequest().getRequestID(), newFR);
    Navigation.navigate(Screen.HOME);
  }

  @FXML
  public void homeItemClicked() {
    Navigation.navigate(Screen.HOME);
  }

  @FXML
  public void exitItemClicked() {
    Platform.exit();
  }

  @FXML
  public void profileItemClicked() {
    Navigation.navigate(Screen.PROFILE_PAGE);
  }
}
