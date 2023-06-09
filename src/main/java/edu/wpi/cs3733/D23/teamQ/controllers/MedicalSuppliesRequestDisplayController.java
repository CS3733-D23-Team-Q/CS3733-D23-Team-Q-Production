package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.db.Qdb;
import edu.wpi.cs3733.D23.teamQ.db.obj.MedicalSuppliesRequest;
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

public class MedicalSuppliesRequestDisplayController {

  ObservableList<String> medicalItemList =
      FXCollections.observableArrayList(
          "bandaids", "cotton balls", "gauze", "tongue depressers", "sterile syringe");

  @FXML MFXButton deleteButton;
  @FXML MFXButton backButton;
  @FXML MFXButton updateButton;

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
  @FXML MFXFilterComboBox itemRequestedField;
  @FXML MFXTextField quantityField;
  @FXML MFXTextField specialInstructionsField;

  @FXML private ImageView BandaidsImage;
  @FXML private ImageView CottonBallsImage;
  @FXML private ImageView GauzeImage;
  @FXML private ImageView TongueDepresserImage;
  @FXML private ImageView SyringeImage;

  @FXML
  public void initialize() {
    Qdb qdb = Qdb.getInstance();
    timeField.setItems(timeList);
    itemRequestedField.setItems(medicalItemList);
    assigneeField.setText(
        ListServiceRequestController.getMedicalRequest().getAssignee().getUsername());
    roomNumberField.setText(
        qdb.retrieveNode(ListServiceRequestController.getMedicalRequest().getNodeID())
            .getLocation()
            .getLongName());
    dateField.setValue(
        LocalDate.of(
            ListServiceRequestController.getMedicalRequest().getDate().getYear() + 1900,
            ListServiceRequestController.getMedicalRequest().getDate().getMonth() + 1,
            ListServiceRequestController.getMedicalRequest().getDate().getDate()));
    timeField.setText(ListServiceRequestController.getMedicalRequest().getTime());
    specialInstructionsField.setText(
        ListServiceRequestController.getMedicalRequest().getSpecialInstructions());
    itemRequestedField.setText(ListServiceRequestController.getMedicalRequest().getItem());
    quantityField.setText(
        String.valueOf(ListServiceRequestController.getMedicalRequest().getQuantity()));
    if (ListServiceRequestController.getMedicalRequest().getItem().equals("Bandaids")) {
      BandaidsImage.setOpacity(1.0);
      CottonBallsImage.setOpacity(0.0);
      GauzeImage.setOpacity(0.0);
      TongueDepresserImage.setOpacity(0.0);
      SyringeImage.setOpacity(0.0);
    }
    if (ListServiceRequestController.getMedicalRequest().getItem().equals("Cotton Balls")) {
      BandaidsImage.setOpacity(0.0);
      CottonBallsImage.setOpacity(1.0);
      GauzeImage.setOpacity(0.0);
      TongueDepresserImage.setOpacity(0.0);
      SyringeImage.setOpacity(0.0);
    }
    if (ListServiceRequestController.getMedicalRequest().getItem().equals("Gauze")) {
      BandaidsImage.setOpacity(0.0);
      CottonBallsImage.setOpacity(0.0);
      GauzeImage.setOpacity(1.0);
      TongueDepresserImage.setOpacity(0.0);
      SyringeImage.setOpacity(0.0);
    }
    if (ListServiceRequestController.getMedicalRequest().getItem().equals("Tongue Depressers")) {
      BandaidsImage.setOpacity(0.0);
      CottonBallsImage.setOpacity(0.0);
      GauzeImage.setOpacity(0.0);
      TongueDepresserImage.setOpacity(1.0);
      SyringeImage.setOpacity(0.0);
    }
    if (ListServiceRequestController.getMedicalRequest().getItem().equals("Syringe")) {
      BandaidsImage.setOpacity(0.0);
      CottonBallsImage.setOpacity(0.0);
      GauzeImage.setOpacity(0.0);
      TongueDepresserImage.setOpacity(0.0);
      SyringeImage.setOpacity(1.0);
    }
  }

  @FXML
  public void deleteButtonClicked() {
    Qdb qdb = Qdb.getInstance();
    qdb.deleteMedicalSuppliesRequest(
        ListServiceRequestController.getMedicalRequest().getRequestID());
  }

  @FXML
  public void backButtonClicked() {
    Navigation.navigate(Screen.HOME);
  }

  @FXML
  public void updateButtonClicked() {

    Qdb qdb = Qdb.getInstance();

    MedicalSuppliesRequest newMSP =
        new MedicalSuppliesRequest(
            ListServiceRequestController.getMedicalRequest().getRequestID(),
            ListServiceRequestController.getMedicalRequest().getNode(),
            ListServiceRequestController.getMedicalRequest().getRequester(),
            qdb.retrieveAccount(assigneeField.getValue().toString()),
            specialInstructionsField.getText(),
            Date.valueOf(dateField.getValue()),
            timeField.getText(),
            ListServiceRequestController.getMedicalRequest().getProgress().ordinal(),
            (String) itemRequestedField.getValue(),
            Integer.parseInt(quantityField.getText()));

    qdb.updateMedicalSuppliesRequest(
        ListServiceRequestController.getMedicalRequest().getRequestID(), newMSP);
  }

  @FXML
  public void ItemSelected(ActionEvent event) {
    if (itemRequestedField.getValue().equals("Bandaids")) {
      BandaidsImage.setOpacity(1.0);
      CottonBallsImage.setOpacity(0.0);
      GauzeImage.setOpacity(0.0);
      TongueDepresserImage.setOpacity(0.0);
      SyringeImage.setOpacity(0.0);
    }
    if (itemRequestedField.getValue().equals("Cotton Balls")) {
      BandaidsImage.setOpacity(0.0);
      CottonBallsImage.setOpacity(1.0);
      GauzeImage.setOpacity(0.0);
      TongueDepresserImage.setOpacity(0.0);
      SyringeImage.setOpacity(0.0);
    }
    if (itemRequestedField.getValue().equals("Gauze")) {
      BandaidsImage.setOpacity(0.0);
      CottonBallsImage.setOpacity(0.0);
      GauzeImage.setOpacity(1.0);
      TongueDepresserImage.setOpacity(0.0);
      SyringeImage.setOpacity(0.0);
    }
    if (itemRequestedField.getValue().equals("Tongue Depressers")) {
      BandaidsImage.setOpacity(0.0);
      CottonBallsImage.setOpacity(0.0);
      GauzeImage.setOpacity(0.0);
      TongueDepresserImage.setOpacity(1.0);
      SyringeImage.setOpacity(0.0);
    }
    if (itemRequestedField.getValue().equals("Syringe")) {
      BandaidsImage.setOpacity(0.0);
      CottonBallsImage.setOpacity(0.0);
      GauzeImage.setOpacity(0.0);
      TongueDepresserImage.setOpacity(0.0);
      SyringeImage.setOpacity(1.0);
    }
  }
}
