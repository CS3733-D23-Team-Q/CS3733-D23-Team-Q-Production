package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.db.Qdb;
import edu.wpi.cs3733.D23.teamQ.db.obj.Account;
import edu.wpi.cs3733.D23.teamQ.db.obj.FlowerRequest;
import edu.wpi.cs3733.D23.teamQ.navigation.Navigation;
import edu.wpi.cs3733.D23.teamQ.navigation.Screen;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import io.github.palexdev.materialfx.controls.MFXFilterComboBox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import java.sql.Date;
import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;

public class FlowerRequestDisplayController {

  ObservableList<String> TypeOfFlowers =
      FXCollections.observableArrayList("Roses", "Daisies", "Tulips", "Sunflowers", "Lilies");

  @FXML MFXButton deleteButton;
  @FXML MFXButton backButton;
  @FXML MFXButton updateButton;

  @FXML MFXFilterComboBox assigneeField;
  @FXML MFXFilterComboBox roomNumberField;
  @FXML MFXDatePicker dateField;
  ObservableList<String> timeList =
          FXCollections.observableArrayList(
                  "00:00", "00:30", "01:00", "01:30", "02:00", "02:30", "03:00", "03:30", "04:00", "04:30",
                  "05:00", "05:30", "06:00", "06:30", "07:00", "07:30", "08:00", "08:30", "09:00", "09:30",
                  "10:00", "10:30", "11:00", "11:30", "12:00", "12:30", "13:00", "13:30", "14:00", "14:30",
                  "15:00", "15:30", "16:00", "16:30", "17:00", "17:30", "18:00", "18:30", "19:00", "19:30",
                  "20:00", "20:30", "21:00", "21:30", "22:00", "22:30", "23:00", "23:30");
  @FXML MFXFilterComboBox timeField;
  @FXML MFXFilterComboBox flowerTypeField;
  @FXML MFXTextField bouquetChoiceField;
  @FXML MFXTextField specialInstructionsField;
  @FXML private ImageView RosesImage;
  @FXML private ImageView DaisiesImage;
  @FXML private ImageView TulipsImage;
  @FXML private ImageView SunflowersImage;
  @FXML private ImageView LiliesImage;

  @FXML
  public void initialize() {
    Qdb qdb = Qdb.getInstance();
    timeField.setItems(timeList);
    this.assigneeField.setItems(qdb.getAllNames());
    String[] conf = {"CONF"};
    this.roomNumberField.setItems(qdb.getAllLongNames(conf));
    this.flowerTypeField.setItems(TypeOfFlowers);

    assigneeField.setText(
        ListServiceRequestController.getFlowerRequest().getAssignee().getUsername());
    roomNumberField.setText(
        qdb.retrieveNode(ListServiceRequestController.getFlowerRequest().getNodeID())
            .getLocation()
            .getLongName());
    dateField.setValue(
        LocalDate.of(
            ListServiceRequestController.getFlowerRequest().getDate().getYear() + 1900,
            ListServiceRequestController.getFlowerRequest().getDate().getMonth() + 1,
            ListServiceRequestController.getFlowerRequest().getDate().getDate()));
    timeField.setText(ListServiceRequestController.getFlowerRequest().getTime());
    specialInstructionsField.setText(
        ListServiceRequestController.getFlowerRequest().getSpecialInstructions());
    flowerTypeField.setText(ListServiceRequestController.getFlowerRequest().getFlowerType());
    bouquetChoiceField.setText(
        String.valueOf(ListServiceRequestController.getFlowerRequest().getNumberOfBouquets()));

    if (ListServiceRequestController.getFlowerRequest().getFlowerType().equals("Roses")) {
      RosesImage.setOpacity(1.0);
      DaisiesImage.setOpacity(0.0);
      TulipsImage.setOpacity(0.0);
      SunflowersImage.setOpacity(0.0);
      LiliesImage.setOpacity(0.0);
    }
    if (ListServiceRequestController.getFlowerRequest().getFlowerType().equals("Daisies")) {
      RosesImage.setOpacity(0.0);
      DaisiesImage.setOpacity(1.0);
      TulipsImage.setOpacity(0.0);
      SunflowersImage.setOpacity(0.0);
      LiliesImage.setOpacity(0.0);
    }
    if (ListServiceRequestController.getFlowerRequest().getFlowerType().equals("Tulips")) {
      RosesImage.setOpacity(0.0);
      DaisiesImage.setOpacity(0.0);
      TulipsImage.setOpacity(1.0);
      SunflowersImage.setOpacity(0.0);
      LiliesImage.setOpacity(0.0);
    }
    if (ListServiceRequestController.getFlowerRequest().getFlowerType().equals("Sunflowers")) {
      RosesImage.setOpacity(0.0);
      DaisiesImage.setOpacity(0.0);
      TulipsImage.setOpacity(0.0);
      SunflowersImage.setOpacity(1.0);
      LiliesImage.setOpacity(0.0);
    }
    if (ListServiceRequestController.getFlowerRequest().getFlowerType().equals("Lilies")) {
      RosesImage.setOpacity(0.0);
      DaisiesImage.setOpacity(0.0);
      TulipsImage.setOpacity(0.0);
      SunflowersImage.setOpacity(0.0);
      LiliesImage.setOpacity(1.0);
    }
  }

  @FXML
  public void deleteButtonClicked() {
    Qdb qdb = Qdb.getInstance();
    qdb.deleteFlowerRequest(ListServiceRequestController.getFlowerRequest().getRequestID());
  }

  @FXML
  public void backButtonClicked() {
    Navigation.navigateRight(Screen.SERVICE_PLACEHOLDER);
  }

  @FXML
  public void updateButtonClicked() {

    Qdb qdb = Qdb.getInstance();

    FlowerRequest newFR =
        new FlowerRequest(
            ListServiceRequestController.getFlowerRequest().getRequestID(),
            ListServiceRequestController.getFlowerRequest().getNode(),
            (Account) ListServiceRequestController.getFlowerRequest().getRequester(),
            (Account) assigneeField.getValue(),
            (String) specialInstructionsField.getText(),
            Date.valueOf(dateField.getValue()),
            timeField.getText(),
            0,
            (String) flowerTypeField.getValue(),
            Integer.parseInt((String) bouquetChoiceField.getText()));

    qdb.updateFlowerRequest(ListServiceRequestController.getFlowerRequest().getRequestID(), newFR);
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
