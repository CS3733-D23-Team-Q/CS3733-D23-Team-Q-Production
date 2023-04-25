package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.db.Qdb;
import edu.wpi.cs3733.D23.teamQ.db.obj.Account;
import edu.wpi.cs3733.D23.teamQ.db.obj.OfficeSuppliesRequest;
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

public class OfficeSuppliesRequestDisplayController {

  ObservableList<String> officeItemList =
      FXCollections.observableArrayList(
          "Printer Paper (by ream)", "Pencil", "Pen", "Highlighter", "Notepad");

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

  @FXML private ImageView PrinterPaperImage;
  @FXML private ImageView PencilImage;
  @FXML private ImageView PenImage;
  @FXML private ImageView HighlighterImage;
  @FXML private ImageView NotepadImage;

  @FXML
  public void initialize() {
    Qdb qdb = Qdb.getInstance();
    timeField.setItems(timeList);
    itemRequestedField.setItems(officeItemList);
    assigneeField.setText(
        ListServiceRequestController.getOfficeRequest().getAssignee().getUsername());
    roomNumberField.setText(
        qdb.retrieveNode(ListServiceRequestController.getOfficeRequest().getNodeID())
            .getLocation()
            .getLongName());
    dateField.setValue(
        LocalDate.of(
            ListServiceRequestController.getOfficeRequest().getDate().getYear() + 1900,
            ListServiceRequestController.getOfficeRequest().getDate().getMonth() + 1,
            ListServiceRequestController.getOfficeRequest().getDate().getDate()));
    timeField.setText(ListServiceRequestController.getOfficeRequest().getTime());
    specialInstructionsField.setText(
        ListServiceRequestController.getOfficeRequest().getSpecialInstructions());
    itemRequestedField.setText(ListServiceRequestController.getOfficeRequest().getItem());
    quantityField.setText(
        String.valueOf(ListServiceRequestController.getOfficeRequest().getQuantity()));

    if (itemRequestedField.getValue().equals("Printer Paper (by ream)")) {
      PrinterPaperImage.setOpacity(1.0);
      PencilImage.setOpacity(0.0);
      PenImage.setOpacity(0.0);
      HighlighterImage.setOpacity(0.0);
      NotepadImage.setOpacity(0.0);
    }
    if (itemRequestedField.getValue().equals("Pencil")) {
      PrinterPaperImage.setOpacity(0.0);
      PencilImage.setOpacity(1.0);
      PenImage.setOpacity(0.0);
      HighlighterImage.setOpacity(0.0);
      NotepadImage.setOpacity(0.0);
    }
    if (itemRequestedField.getValue().equals("Pen")) {
      PrinterPaperImage.setOpacity(0.0);
      PencilImage.setOpacity(0.0);
      PenImage.setOpacity(1.0);
      HighlighterImage.setOpacity(0.0);
      NotepadImage.setOpacity(0.0);
    }
    if (itemRequestedField.getValue().equals("Highlighter")) {
      PrinterPaperImage.setOpacity(0.0);
      PencilImage.setOpacity(0.0);
      PenImage.setOpacity(0.0);
      HighlighterImage.setOpacity(1.0);
      NotepadImage.setOpacity(0.0);
    }
    if (itemRequestedField.getValue().equals("Notepad")) {
      PrinterPaperImage.setOpacity(0.0);
      PencilImage.setOpacity(0.0);
      PenImage.setOpacity(0.0);
      HighlighterImage.setOpacity(0.0);
      NotepadImage.setOpacity(1.0);
    }
  }

  @FXML
  public void deleteButtonClicked() {
    Qdb qdb = Qdb.getInstance();
    qdb.deleteOfficeSuppliesRequest(ListServiceRequestController.getOfficeRequest().getRequestID());
  }

  @FXML
  public void backButtonClicked() {
    Navigation.navigate(Screen.HOME);
  }

  @FXML
  public void updateButtonClicked() {

    Qdb qdb = Qdb.getInstance();

    OfficeSuppliesRequest newOR =
        new OfficeSuppliesRequest(
            ListServiceRequestController.getOfficeRequest().getRequestID(),
            ListServiceRequestController.getOfficeRequest().getNode(),
            ListServiceRequestController.getOfficeRequest().getRequester(),
            (Account) assigneeField.getValue(),
            specialInstructionsField.getText(),
            Date.valueOf(dateField.getValue()),
            timeField.getText(),
            ListServiceRequestController.getOfficeRequest().getProgress().ordinal(),
            itemRequestedField.getValue().toString(),
            Integer.parseInt((String) quantityField.getText()));

    qdb.updateOfficeSuppliesRequest(
        ListServiceRequestController.getOfficeRequest().getRequestID(), newOR);
  }

  @FXML
  public void ItemSelected(ActionEvent event) {
    if (itemRequestedField.getValue().equals("Printer Paper (by ream)")) {
      PrinterPaperImage.setOpacity(1.0);
      PencilImage.setOpacity(0.0);
      PenImage.setOpacity(0.0);
      HighlighterImage.setOpacity(0.0);
      NotepadImage.setOpacity(0.0);
    }
    if (itemRequestedField.getValue().equals("Pencil")) {
      PrinterPaperImage.setOpacity(0.0);
      PencilImage.setOpacity(1.0);
      PenImage.setOpacity(0.0);
      HighlighterImage.setOpacity(0.0);
      NotepadImage.setOpacity(0.0);
    }
    if (itemRequestedField.getValue().equals("Pen")) {
      PrinterPaperImage.setOpacity(0.0);
      PencilImage.setOpacity(0.0);
      PenImage.setOpacity(1.0);
      HighlighterImage.setOpacity(0.0);
      NotepadImage.setOpacity(0.0);
    }
    if (itemRequestedField.getValue().equals("Highlighter")) {
      PrinterPaperImage.setOpacity(0.0);
      PencilImage.setOpacity(0.0);
      PenImage.setOpacity(0.0);
      HighlighterImage.setOpacity(1.0);
      NotepadImage.setOpacity(0.0);
    }
    if (itemRequestedField.getValue().equals("Notepad")) {
      PrinterPaperImage.setOpacity(0.0);
      PencilImage.setOpacity(0.0);
      PenImage.setOpacity(0.0);
      HighlighterImage.setOpacity(0.0);
      NotepadImage.setOpacity(1.0);
    }
  }
}
