package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.App;
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
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import net.kurobako.gesturefx.GesturePane;

public class FlowerRequest2Controller {
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
  @FXML TextArea specialInstructionsField;
  ObservableList<String> TypeOfFlowers =
      FXCollections.observableArrayList("Roses", "Daisies", "Tulips", "Sunflowers", "Lilies");

  @FXML MFXButton resetButton;
  @FXML MFXButton cancelButton;
  @FXML MFXButton submitButton;

  @FXML private ImageView FlowerImage;
  @FXML private GesturePane titlePane;

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

    Image titleImage = new Image(App.class.getResourceAsStream("FlowerRequestTitle.jpg"));
    ImageView iv = new ImageView(titleImage);
    titlePane.setContent(iv);
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

    FlowerImage.setImage(null);
  }

  @FXML
  public void cancelButtonClicked() {
    Navigation.navigate(Screen.HOME);
  }

  @FXML
  public void submitButtonClicked() {
    Qdb qdb = Qdb.getInstance();

    FlowerRequest newFR =
        new FlowerRequest(
            qdb.getNodeFromLocation(roomNumberField.getSelectedItem().toString()),
            qdb.retrieveAccount(assigneeField.getValue().toString().split(",")[0]),
            qdb.retrieveAccount(LoginController.getUsername()),
            specialInstructionsField.getText(),
            Date.valueOf(dateField.getValue()),
            timeField.getText(),
            0,
            (String) flowerTypeField.getSelectedItem(),
            Integer.parseInt(bouquetChoiceField.getText()));

    qdb.addFlowerRequest(newFR);
    Navigation.navigate(Screen.SUBMISSION);
  }

  @FXML
  public void FlowerSelected(ActionEvent event) {
    if (flowerTypeField.getValue().equals("Roses")) {
      Image rosesImage = new Image(App.class.getResourceAsStream("RosesBouquet.jpg"));
      ImageView iv = new ImageView(rosesImage);
      FlowerImage.setImage(iv.getImage());
    }
    if (flowerTypeField.getValue().equals("Daisies")) {
      Image daisiesImage = new Image(App.class.getResourceAsStream("DaisiesBouquet.jpg"));
      ImageView iv = new ImageView(daisiesImage);
      FlowerImage.setImage(iv.getImage());
    }
    if (flowerTypeField.getValue().equals("Tulips")) {
      Image tulipsImage = new Image(App.class.getResourceAsStream("TulipsBouquet.jpg"));
      ImageView iv = new ImageView(tulipsImage);
      FlowerImage.setImage(iv.getImage());
    }
    if (flowerTypeField.getValue().equals("Sunflowers")) {
      Image sunflowersImage = new Image(App.class.getResourceAsStream("SunflowersBouquet.jpg"));
      ImageView iv = new ImageView(sunflowersImage);
      FlowerImage.setImage(iv.getImage());
    }
    if (flowerTypeField.getValue().equals("Lilies")) {
      Image liliesImage = new Image(App.class.getResourceAsStream("LiliesBouquet.jpg"));
      ImageView iv = new ImageView(liliesImage);
      FlowerImage.setImage(iv.getImage());
    }
  }
}
