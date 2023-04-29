package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.App;
import edu.wpi.cs3733.D23.teamQ.db.Qdb;
import edu.wpi.cs3733.D23.teamQ.db.obj.ConferenceRequest;
import edu.wpi.cs3733.D23.teamQ.navigation.Navigation;
import edu.wpi.cs3733.D23.teamQ.navigation.Screen;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import io.github.palexdev.materialfx.controls.MFXFilterComboBox;
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

public class ConferenceRoomRequest2Controller {
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
  @FXML MFXFilterComboBox foodField;
  @FXML TextArea specialInstructionsField;
  ObservableList<String> foodOptionsList =
      FXCollections.observableArrayList(
          "Brunch spread", "Dinner spread", "Snack spread", "No food");

  @FXML Button resetButton;
  @FXML Button cancelButton;
  @FXML Button submitButton;

  @FXML private ImageView FoodImage;
  @FXML private GesturePane titlePane;

  @FXML
  public void initialize() {
    this.assigneeField.setValue("");
    this.assigneeField.setItems(qdb.getAllNames());
    this.roomNumberField.setValue("");
    this.timeField.setValue("");
    this.timeField.setItems(timeList);
    String[] conf = {"CONF"};
    this.roomNumberField.setItems(qdb.getAllLongNames(conf));
    this.foodField.setValue("");
    this.foodField.setItems(foodOptionsList);

    Image titleImage = new Image(App.class.getResourceAsStream("ConferenceRequestTitle.jpg"));
    ImageView iv = new ImageView(titleImage);
    titlePane.setContent(iv);
  }

  @FXML
  public void resetButtonClicked() {
    assigneeField.setValue("");
    roomNumberField.setValue("");
    dateField.clear();
    timeField.setValue("");
    foodField.setValue("");
    specialInstructionsField.clear();

    FoodImage.setImage(null);
  }

  @FXML
  public void cancelButtonClicked() {
    Navigation.navigateRight(Screen.SERVICE_PLACEHOLDER);
  }

  @FXML
  public void submitButtonClicked() {
    Qdb qdb = Qdb.getInstance();
    ConferenceRequest cr =
        new ConferenceRequest(
            qdb.getNodeFromLocation(roomNumberField.getValue().toString()),
            qdb.retrieveAccount(assigneeField.getValue().toString().split(",")[0]),
            qdb.retrieveAccount(LoginController.getUsername()),
            specialInstructionsField.getText(),
            Date.valueOf(dateField.getValue()),
            (String) timeField.getValue(),
            0,
            (String) foodField.getValue());

    qdb.addConferenceRequest(cr);
    Navigation.navigate(Screen.SUBMISSION);
  }

  @FXML
  public void FoodSelected(ActionEvent event) {
    if (foodField.getValue().equals("Brunch spread")) {
      Image brunchImage = new Image(App.class.getResourceAsStream("BrunchSpread.jpg"));
      ImageView iv = new ImageView(brunchImage);
      FoodImage.setImage(iv.getImage());
    }
    if (foodField.getValue().equals("Dinner spread")) {
      Image dinnerImage = new Image(App.class.getResourceAsStream("DinnerSpread.jpg"));
      ImageView iv = new ImageView(dinnerImage);
      FoodImage.setImage(iv.getImage());
    }
    if (foodField.getValue().equals("Snack spread")) {
      Image snackImage = new Image(App.class.getResourceAsStream("SnackSpread.jpg"));
      ImageView iv = new ImageView(snackImage);
      FoodImage.setImage(iv.getImage());
    }
  }
}
