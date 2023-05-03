package edu.wpi.cs3733.D23.teamQ.controllers;

import static edu.wpi.cs3733.D23.teamQ.controllers.ListServiceRequestController.getFurnitureRequest;

import edu.wpi.cs3733.D23.teamQ.db.Qdb;
import edu.wpi.cs3733.D23.teamQ.db.obj.Account;
import edu.wpi.cs3733.D23.teamQ.db.obj.FurnitureRequest;
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

public class FurnitureDeliveryRequestDisplayController {

  ObservableList<String> TypeOfFurniture =
      FXCollections.observableArrayList("Desk", "Desk Chair", "Couch", "Examination Table");

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
  @FXML MFXTextField specialInstructionsField;
  @FXML MFXFilterComboBox itemRequestedField;

  @FXML private ImageView DeskImage;
  @FXML private ImageView DeskChairImage;
  @FXML private ImageView CouchImage;
  @FXML private ImageView ExaminationTableImage;

  @FXML
  public void initialize() {
    Qdb qdb = Qdb.getInstance();
    timeField.setItems(timeList);
    itemRequestedField.setItems(TypeOfFurniture);
    assigneeField.setText(getFurnitureRequest().getAssignee().getUsername());
    roomNumberField.setText(
        qdb.retrieveNode(ListServiceRequestController.getFurnitureRequest().getNodeID())
            .getLocation()
            .getLongName());
    dateField.setValue(
        LocalDate.of(
            ListServiceRequestController.getFurnitureRequest().getDate().getYear() + 1900,
            ListServiceRequestController.getFurnitureRequest().getDate().getMonth() + 1,
            ListServiceRequestController.getFurnitureRequest().getDate().getDate()));
    timeField.setText(ListServiceRequestController.getFurnitureRequest().getTime());
    specialInstructionsField.setText(
        ListServiceRequestController.getFurnitureRequest().getSpecialInstructions());
    itemRequestedField.setText(getFurnitureRequest().getItem());

    if (getFurnitureRequest().getItem().equals("Desk")) {
      DeskImage.setOpacity(1.0);
      DeskChairImage.setOpacity(0.0);
      CouchImage.setOpacity(0.0);
      ExaminationTableImage.setOpacity(0.0);
    }
    if (getFurnitureRequest().getItem().equals("Desk Chair")) {
      DeskImage.setOpacity(0.0);
      DeskChairImage.setOpacity(1.0);
      CouchImage.setOpacity(0.0);
      ExaminationTableImage.setOpacity(0.0);
    }
    if (getFurnitureRequest().getItem().equals("Couch")) {
      DeskImage.setOpacity(0.0);
      DeskChairImage.setOpacity(0.0);
      CouchImage.setOpacity(1.0);
      ExaminationTableImage.setOpacity(0.0);
    }
    if (getFurnitureRequest().getItem().equals("Examination Table")) {
      DeskImage.setOpacity(0.0);
      DeskChairImage.setOpacity(0.0);
      CouchImage.setOpacity(0.0);
      ExaminationTableImage.setOpacity(1.0);
    }
  }

  @FXML
  public void deleteButtonClicked() {
    Qdb qdb = Qdb.getInstance();
    qdb.deleteFurnitureRequest(getFurnitureRequest().getRequestID());
  }

  @FXML
  public void backButtonClicked() {
    Navigation.navigate(Screen.SERVICE_PLACEHOLDER);
  }

  @FXML
  public void updateButtonClicked() {

    Qdb qdb = Qdb.getInstance();

    FurnitureRequest newFurR =
        new FurnitureRequest(
            getFurnitureRequest().getRequestID(),
            getFurnitureRequest().getNode(),
            getFurnitureRequest().getRequester(),
            (Account) assigneeField.getValue(),
            specialInstructionsField.getText(),
            Date.valueOf(dateField.getValue()),
            timeField.getText(),
            getFurnitureRequest().getProgress().ordinal(),
            itemRequestedField.getValue().toString());

    qdb.updateFurnitureRequest(getFurnitureRequest().getRequestID(), newFurR);
  }

  @FXML
  public void ItemSelected(ActionEvent event) {
    if (itemRequestedField.getValue().equals("Desk")) {
      DeskImage.setOpacity(1.0);
      DeskChairImage.setOpacity(0.0);
      CouchImage.setOpacity(0.0);
      ExaminationTableImage.setOpacity(0.0);
    }
    if (itemRequestedField.getValue().equals("Desk Chair")) {
      DeskImage.setOpacity(0.0);
      DeskChairImage.setOpacity(1.0);
      CouchImage.setOpacity(0.0);
      ExaminationTableImage.setOpacity(0.0);
    }
    if (itemRequestedField.getValue().equals("Couch")) {
      DeskImage.setOpacity(0.0);
      DeskChairImage.setOpacity(0.0);
      CouchImage.setOpacity(1.0);
      ExaminationTableImage.setOpacity(0.0);
    }
    if (itemRequestedField.getValue().equals("Examination Table")) {
      DeskImage.setOpacity(0.0);
      DeskChairImage.setOpacity(0.0);
      CouchImage.setOpacity(0.0);
      ExaminationTableImage.setOpacity(1.0);
    }
  }
}
