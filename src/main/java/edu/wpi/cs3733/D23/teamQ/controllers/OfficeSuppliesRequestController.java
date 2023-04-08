package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.db.Qdb;
import edu.wpi.cs3733.D23.teamQ.db.obj.OfficeSuppliesRequest;
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

public class OfficeSuppliesRequestController {
  @FXML private MFXTextField assigneeField;
  @FXML private MFXTextField roomNumberField;
  @FXML private MFXTextField specialInstructionsField;
  @FXML private ChoiceBox itemField;
  ObservableList<String> itemList =
      FXCollections.observableArrayList(
          "Printer Paper (by ream)", "Pencil", "Pen", "Highlighter", "Notepad");
  @FXML private ChoiceBox quantityField;
  ObservableList<String> quantityList = FXCollections.observableArrayList("1", "2", "5", "10");

  @FXML Button resetButton;
  @FXML Button backButton;
  @FXML Button submitButton;

  @FXML MenuItem homeItem;

  @FXML MenuItem profileItem;

  @FXML
  public void initialize() {
    this.itemField.setValue("Select Item");
    this.itemField.setItems(itemList);
    this.quantityField.setValue("Select Quantity");
    this.quantityField.setItems(quantityList);
  }

  @FXML
  public void resetButtonClicked() {
    assigneeField.clear();
    roomNumberField.clear();
    specialInstructionsField.clear();
    itemField.setValue("Select Item");
    quantityField.setValue("Select Quantity");
  }

  @FXML
  public void backButtonClicked() {
    Navigation.navigate(Screen.SERVICE_REQUEST_HUB);
  }

  @FXML
  public void submitButtonClicked() {
    Qdb qdb = Qdb.getInstance();
    if (((String) quantityField.getValue()).equals("Select Quantity")) {
      quantityField.setValue("0");
    }
    OfficeSuppliesRequest newOSR =
        new OfficeSuppliesRequest(
            0,
            "temp user",
            0,
            assigneeField.getText(),
            roomNumberField.getText(),
            specialInstructionsField.getText(),
            (String) itemField.getValue(),
            Integer.parseInt((String) quantityField.getValue()));
    // qdb.addFlowerRequest(newOSR);
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
