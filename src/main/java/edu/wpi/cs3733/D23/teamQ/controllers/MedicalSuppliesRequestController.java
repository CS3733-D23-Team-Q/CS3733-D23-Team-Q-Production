package edu.wpi.cs3733.D23.teamQ.controllers;

import static edu.wpi.cs3733.D23.teamQ.controllers.ListServiceRequestController.getMedicalRequest;

import edu.wpi.cs3733.D23.teamQ.App;
import edu.wpi.cs3733.D23.teamQ.db.Qdb;
import edu.wpi.cs3733.D23.teamQ.db.obj.Account;
import edu.wpi.cs3733.D23.teamQ.db.obj.MedicalSuppliesRequest;
import edu.wpi.cs3733.D23.teamQ.navigation.Navigation;
import edu.wpi.cs3733.D23.teamQ.navigation.Screen;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import io.github.palexdev.materialfx.controls.MFXFilterComboBox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import java.sql.Date;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import net.kurobako.gesturefx.GesturePane;

public class MedicalSuppliesRequestController {
  Qdb qdb = Qdb.getInstance();
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
  @FXML TextArea specialInstructionsField;
  @FXML MFXFilterComboBox itemRequestedField;
  ObservableList<String> itemList =
      FXCollections.observableArrayList(
          "Bandaids", "Cotton Balls", "Gauze", "Tongue Depressers", "Sterile Syringe");
  @FXML MFXTextField quantityField;

  @FXML Button resetButton;
  @FXML Button cancelButton;
  @FXML Button submitButton;

  @FXML private ImageView ItemImage;
  @FXML private GesturePane titlePane;

  @FXML
  public void initialize() {
    this.assigneeField.setValue("");
    this.assigneeField.setItems(qdb.getAllNames());
    this.roomNumberField.setValue("");
    this.roomNumberField.setItems(qdb.getAllLongNames());
    this.itemRequestedField.setValue("");
    this.itemRequestedField.setItems(itemList);
    this.timeField.setValue("");
    this.timeField.setItems(timeList);

    Image titleImage = new Image(App.class.getResourceAsStream("MedicalSuppliesTitle.jpg"));
    ImageView iv = new ImageView(titleImage);
    titlePane.setContent(iv);
  }

  @FXML
  public void resetButtonClicked() {
    this.assigneeField.setValue("");
    this.roomNumberField.setValue("");
    this.itemRequestedField.setValue("");
    this.timeField.setValue("");
    dateField.clear();
    specialInstructionsField.clear();
    quantityField.clear();

    ItemImage.setImage(null);
  }

  @FXML
  public void cancelButtonClicked() {
    Navigation.navigateRight(Screen.SERVICE_PLACEHOLDER);
  }

  @FXML
  public void submitButtonClicked() {
    Qdb qdb = Qdb.getInstance();
    MedicalSuppliesRequest newMSR =
        new MedicalSuppliesRequest(
            qdb.getNodeFromLocation(roomNumberField.getValue().toString()),
            qdb.retrieveAccount(LoginController.getUsername()),
            qdb.retrieveAccount(assigneeField.getValue().toString().split(",")[0]),
            specialInstructionsField.getText(),
            Date.valueOf(dateField.getValue()),
            timeField.getText(),
            0,
            itemRequestedField.getValue().toString(),
            Integer.parseInt(quantityField.getText()));
    qdb.addMedicalSuppliesRequest(newMSR);
    Navigation.navigate(Screen.SUBMISSION);
  }

  public void deleteButtonClicked(ActionEvent actionEvent) {
    Qdb qdb = Qdb.getInstance();
    qdb.deleteFurnitureRequest(getMedicalRequest().getRequestID());
  }

  public void backButtonClicked(ActionEvent actionEvent) {
    Navigation.navigate(Screen.HOME);
  }

  public void updateButtonClicked(ActionEvent actionEvent) {
    Qdb qdb = Qdb.getInstance();

    MedicalSuppliesRequest newMedR =
        new MedicalSuppliesRequest(
            getMedicalRequest().getRequestID(),
            getMedicalRequest().getNode(),
            getMedicalRequest().getRequester(),
            (Account) assigneeField.getValue(),
            specialInstructionsField.getText(),
            Date.valueOf(dateField.getValue()),
            timeField.getText(),
            getMedicalRequest().getProgress().ordinal(),
            itemRequestedField.getValue().toString(),
            Integer.parseInt(quantityField.getText()));

    qdb.updateMedicalSuppliesRequest(getMedicalRequest().getRequestID(), newMedR);
  }

  @FXML
  public void ItemSelected(ActionEvent event) {
    if (itemRequestedField.getValue().equals("Bandaids")) {
      Image bandaidsImage = new Image(App.class.getResourceAsStream("Bandaids.jpg"));
      ImageView iv = new ImageView(bandaidsImage);
      ItemImage.setImage(iv.getImage());
    }
    if (itemRequestedField.getValue().equals("Cotton Balls")) {
      Image cottonballsImage = new Image(App.class.getResourceAsStream("Cottonballs.jpg"));
      ImageView iv = new ImageView(cottonballsImage);
      ItemImage.setImage(iv.getImage());
    }
    if (itemRequestedField.getValue().equals("Gauze")) {
      Image gauzeImage = new Image(App.class.getResourceAsStream("Gauze.jpg"));
      ImageView iv = new ImageView(gauzeImage);
      ItemImage.setImage(iv.getImage());
    }
    if (itemRequestedField.getValue().equals("Tongue Depressers")) {
      Image tongueDepressersImage = new Image(App.class.getResourceAsStream("TongueDepresser.jpg"));
      ImageView iv = new ImageView(tongueDepressersImage);
      ItemImage.setImage(iv.getImage());
    }
    if (itemRequestedField.getValue().equals("Sterile Syringe")) {
      Image syringeImage = new Image(App.class.getResourceAsStream("Syringe.jpg"));
      ImageView iv = new ImageView(syringeImage);
      ItemImage.setImage(iv.getImage());
    }
  }
}
