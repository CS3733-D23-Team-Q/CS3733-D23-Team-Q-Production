package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.db.Qdb;
import edu.wpi.cs3733.D23.teamQ.db.obj.FlowerRequest;
import edu.wpi.cs3733.D23.teamQ.navigation.Navigation;
import edu.wpi.cs3733.D23.teamQ.navigation.Screen;
import io.github.palexdev.materialfx.controls.*;
import java.sql.Date;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;

public class FlowerRequestController {
  Qdb qdb = Qdb.getInstance();
  @FXML MFXFilterComboBox assigneeField;
  @FXML MFXFilterComboBox roomNumberField;
  @FXML MFXDatePicker dateField;
  ObservableList<String> timeList =
      FXCollections.observableArrayList(
          "00:30", "01:00", "01:30", "02:00", "02:30", "03:00", "03:30", "04:00", "04:30", "05:00",
          "05:30", "06:00", "06:30", "07:00", "07:30", "08:00", "08:30", "09:00", "09:30", "10:00",
          "10:30", "11:00", "11:30", "12:00", "12:30", "13:00", "13:30", "14:00", "14:30", "15:00",
          "15:30", "16:00", "16:30", "17:00", "17:30", "18:00", "18:30", "19:00", "19:30", "20:00",
          "20:30", "21:00", "21:30", "22:00", "22:30", "23:00", "23:30", "24:00");
  @FXML MFXFilterComboBox timeField;
  @FXML MFXFilterComboBox flowerTypeField;
  @FXML MFXTextField bouquetChoiceField;
  @FXML MFXTextField specialInstructionsField;
  ObservableList<String> TypeOfFlowers =
      FXCollections.observableArrayList("Roses", "Daisies", "Tulips", "Sunflowers", "Lilies");

  @FXML MFXButton resetButton;
  @FXML MFXButton cancelButton;
  @FXML MFXButton submitButton;
  @FXML private ImageView RosesImage;
  @FXML private ImageView DaisiesImage;
  @FXML private ImageView TulipsImage;
  @FXML private ImageView SunflowersImage;
  @FXML private ImageView LiliesImage;

  /**
   * Initializes the Flower Request Choice Box's Switches screens to the Home page when Cancel
   * Button is pressed Clears fields when Clears Filters is pressed Switches screens to Flower
   * Request Submission page when Submit Button is pressed Gets values from the Flower Request Data
   */
  @FXML
  public void initialize() {
    this.assigneeField.setValue("");
    this.assigneeField.setItems(qdb.getAllNames());
    this.roomNumberField.setValue("");
    this.timeField.setValue("");
    this.timeField.setItems(timeList);
    String[] conf = {"CONF"};
    this.roomNumberField.setItems(qdb.getAllLongNames(conf));
    this.flowerTypeField.setValue("");
    this.flowerTypeField.setItems(TypeOfFlowers);

    RosesImage.setOpacity(0.0);
    DaisiesImage.setOpacity(0.0);
    TulipsImage.setOpacity(0.0);
    SunflowersImage.setOpacity(0.0);
    LiliesImage.setOpacity(0.0);
  }

  @FXML
  public void resetButtonClicked() {
    assigneeField.setValue("");
    roomNumberField.setValue("");
    dateField.clear();
    timeField.setValue("");
    flowerTypeField.setValue("");
    bouquetChoiceField.clear();
    specialInstructionsField.clear();

    RosesImage.setOpacity(0.0);
    DaisiesImage.setOpacity(0.0);
    TulipsImage.setOpacity(0.0);
    SunflowersImage.setOpacity(0.0);
    LiliesImage.setOpacity(0.0);
  }

  @FXML
  public void cancelButtonClicked() {
    Navigation.navigateRight(Screen.SERVICE_PLACEHOLDER);
  }

  @FXML
  public void submitButtonClicked() {
    Qdb qdb = Qdb.getInstance();

    FlowerRequest newFR =
        new FlowerRequest(
            qdb.getNodeFromLocation(roomNumberField.getSelectedItem().toString()),
            qdb.retrieveAccount(LoginController.getUsername()),
            qdb.retrieveAccount(assigneeField.getValue().toString().split(",")[0]),
            specialInstructionsField.getText(),
            Date.valueOf(dateField.getValue()),
            timeField.getText(),
            0,
            (String) flowerTypeField.getSelectedItem(),
            Integer.parseInt(bouquetChoiceField.getText()));

    qdb.addFlowerRequest(newFR);
    Navigation.navigateRight(Screen.SUBMISSION);
  }

  @FXML
  public void FlowerSelected(ActionEvent event) {
    if (flowerTypeField.getValue().equals("Roses")) {
      RosesImage.setOpacity(1.0);
      DaisiesImage.setOpacity(0.0);
      TulipsImage.setOpacity(0.0);
      SunflowersImage.setOpacity(0.0);
      LiliesImage.setOpacity(0.0);
    }
    if (flowerTypeField.getValue().equals("Daisies")) {
      RosesImage.setOpacity(0.0);
      DaisiesImage.setOpacity(1.0);
      TulipsImage.setOpacity(0.0);
      SunflowersImage.setOpacity(0.0);
      LiliesImage.setOpacity(0.0);
    }
    if (flowerTypeField.getValue().equals("Tulips")) {
      RosesImage.setOpacity(0.0);
      DaisiesImage.setOpacity(0.0);
      TulipsImage.setOpacity(1.0);
      SunflowersImage.setOpacity(0.0);
      LiliesImage.setOpacity(0.0);
    }
    if (flowerTypeField.getValue().equals("Sunflowers")) {
      RosesImage.setOpacity(0.0);
      DaisiesImage.setOpacity(0.0);
      TulipsImage.setOpacity(0.0);
      SunflowersImage.setOpacity(1.0);
      LiliesImage.setOpacity(0.0);
    }
    if (flowerTypeField.getValue().equals("Lilies")) {
      RosesImage.setOpacity(0.0);
      DaisiesImage.setOpacity(0.0);
      TulipsImage.setOpacity(0.0);
      SunflowersImage.setOpacity(0.0);
      LiliesImage.setOpacity(1.0);
    }
  }
}
