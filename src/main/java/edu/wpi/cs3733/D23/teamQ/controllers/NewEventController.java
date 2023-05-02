package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.db.Qdb;
import edu.wpi.cs3733.D23.teamQ.db.obj.Account;
import edu.wpi.cs3733.D23.teamQ.db.obj.personalEvent;
import edu.wpi.cs3733.D23.teamQ.navigation.Navigation;
import edu.wpi.cs3733.D23.teamQ.navigation.Screen;
import io.github.palexdev.materialfx.controls.*;
import java.sql.Date;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

public class NewEventController {
  Qdb qdb = Qdb.getInstance();

  @FXML MFXTextField TitleField;
  @FXML MFXDatePicker DateField;
  @FXML MFXFilterComboBox StartField;
  @FXML MFXFilterComboBox EndField;
  @FXML MFXCheckbox FullDayCheckBox;
  @FXML MFXButton submitButton;
  @FXML MFXButton cancelButton;

  ObservableList<String> timeList =
      FXCollections.observableArrayList(
          "00:30", "01:00", "01:30", "02:00", "02:30", "03:00", "03:30", "04:00", "04:30", "05:00",
          "05:30", "06:00", "06:30", "07:00", "07:30", "08:00", "08:30", "09:00", "09:30", "10:00",
          "10:30", "11:00", "11:30", "12:00", "12:30", "13:00", "13:30", "14:00", "14:30", "15:00",
          "15:30", "16:00", "16:30", "17:00", "17:30", "18:00", "18:30", "19:00", "19:30", "20:00",
          "20:30", "21:00", "21:30", "22:00", "22:30", "23:00", "23:30", "24:00");

  @FXML
  public void initialize() {
    this.StartField.setValue("");
    this.StartField.setItems(timeList);
    this.EndField.setValue("");
    this.EndField.setItems(timeList);
    this.FullDayCheckBox.setSelected(false);
  }

  @FXML
  public void resetButtonClicked() {
    TitleField.clear();
    DateField.clear();
    StartField.setValue("");
    EndField.setValue("");
    FullDayCheckBox.setSelected(false);
  }

  @FXML
  public void cancelButtonClicked() {
    Navigation.navigate(Screen.HOME);
  }

  @FXML
  public void submitButtonClicked() {
    Qdb qdb = Qdb.getInstance();
    String username = LoginController.getLoginUsername();
    Account account = qdb.retrieveAccount(username);
    System.out.println("before pe");
    personalEvent pe =
        new personalEvent(
            TitleField.toString(),

            // get perrsonaleventID
            Date.valueOf(DateField.getValue()),
            (String) StartField.getValue(),
            (String) EndField.getValue(),
            FullDayCheckBox.isSelected(),
            username);
    qdb.addPersonalEvent(pe);
    Navigation.navigate(Screen.HOME);
  }
}
