package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.db.Qdb;
import edu.wpi.cs3733.D23.teamQ.db.obj.MealRequest;
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
import javafx.scene.image.ImageView;

public class MealDeliveryRequestController {
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
  @FXML MFXTextField specialInstructionsField;

  @FXML MFXFilterComboBox drinkField;
  @FXML MFXFilterComboBox entreeField;
  @FXML MFXFilterComboBox sideField;

  @FXML Button resetButton;
  @FXML Button cancelButton;
  @FXML Button submitButton;

  ObservableList<String> drinkList =
      FXCollections.observableArrayList("Water", "Coke", "Coffee", "Tea");
  ObservableList<String> entreeList =
      FXCollections.observableArrayList("Chicken", "Steak", "Pork", "Fish", "Vegetarian");

  ObservableList<String> sideList =
      FXCollections.observableArrayList("Fries", "Onion Rings", "Soup", "Salad");

  @FXML private ImageView WaterImage;
  @FXML private ImageView CokeImage;
  @FXML private ImageView CoffeeImage;
  @FXML private ImageView TeaImage;

  @FXML private ImageView ChickenImage;
  @FXML private ImageView SteakImage;
  @FXML private ImageView PorkImage;
  @FXML private ImageView FishImage;
  @FXML private ImageView VegetarianImage;

  @FXML private ImageView FriesImage;
  @FXML private ImageView OnionRingsImage;
  @FXML private ImageView SoupImage;
  @FXML private ImageView SaladImage;

  @FXML
  public void initialize() {
    this.assigneeField.setValue("");
    this.assigneeField.setItems(qdb.getAllNames());
    this.roomNumberField.setValue("");
    this.roomNumberField.setItems(qdb.getAllLongNames());
    this.timeField.setValue("");
    this.timeField.setItems(timeList);
    this.drinkField.setValue("");
    this.drinkField.setItems(drinkList);
    this.entreeField.setValue("");
    this.entreeField.setItems(entreeList);
    this.sideField.setValue("");
    this.sideField.setItems(sideList);

    WaterImage.setOpacity(0.0);
    CokeImage.setOpacity(0.0);
    CoffeeImage.setOpacity(0.0);
    TeaImage.setOpacity(0.0);

    ChickenImage.setOpacity(0.0);
    SteakImage.setOpacity(0.0);
    PorkImage.setOpacity(0.0);
    FishImage.setOpacity(0.0);
    VegetarianImage.setOpacity(0.0);

    FriesImage.setOpacity(0.0);
    OnionRingsImage.setOpacity(0.0);
    SoupImage.setOpacity(0.0);
    SaladImage.setOpacity(0.0);
  }

  @FXML
  public void resetButtonClicked() {
    assigneeField.setValue("");
    roomNumberField.setValue("");
    dateField.clear();
    timeField.setValue("");
    drinkField.setValue("");
    entreeField.setValue("");
    sideField.setValue("");
    specialInstructionsField.clear();

    WaterImage.setOpacity(0.0);
    CokeImage.setOpacity(0.0);
    CoffeeImage.setOpacity(0.0);
    TeaImage.setOpacity(0.0);

    ChickenImage.setOpacity(0.0);
    SteakImage.setOpacity(0.0);
    PorkImage.setOpacity(0.0);
    FishImage.setOpacity(0.0);
    VegetarianImage.setOpacity(0.0);

    FriesImage.setOpacity(0.0);
    OnionRingsImage.setOpacity(0.0);
    SoupImage.setOpacity(0.0);
    SaladImage.setOpacity(0.0);
  }

  @FXML
  public void cancelButtonClicked() {
    Navigation.navigateRight(Screen.SERVICE_PLACEHOLDER);
  }

  @FXML
  public void submitButtonClicked() {
    Qdb qdb = Qdb.getInstance();

    MealRequest newMR =
        new MealRequest(
            qdb.getNodeFromLocation(roomNumberField.getValue().toString()),
            qdb.retrieveAccount(assigneeField.getValue().toString().split(",")[0]),
            qdb.retrieveAccount(LoginController.getUsername()),
            specialInstructionsField.getText(),
            Date.valueOf(dateField.getValue()),
            (String) timeField.getValue(),
            0,
            drinkField.getText(),
            entreeField.getText(),
            sideField.getText());

    qdb.addMealRequest(newMR);
    Navigation.navigate(Screen.SUBMISSION);
  }

  @FXML
  public void EntreeSelected(ActionEvent event) {
    if (entreeField.getValue().equals("Chicken")) {
      ChickenImage.setOpacity(1.0);
      SteakImage.setOpacity(0.0);
      PorkImage.setOpacity(0.0);
      FishImage.setOpacity(0.0);
      VegetarianImage.setOpacity(0.0);
    }
    if (entreeField.getValue().equals("Steak")) {
      ChickenImage.setOpacity(0.0);
      SteakImage.setOpacity(1.0);
      PorkImage.setOpacity(0.0);
      FishImage.setOpacity(0.0);
      VegetarianImage.setOpacity(0.0);
    }
    if (entreeField.getValue().equals("Pork")) {
      ChickenImage.setOpacity(0.0);
      SteakImage.setOpacity(0.0);
      PorkImage.setOpacity(1.0);
      FishImage.setOpacity(0.0);
      VegetarianImage.setOpacity(0.0);
    }
    if (entreeField.getValue().equals("Fish")) {
      ChickenImage.setOpacity(0.0);
      SteakImage.setOpacity(0.0);
      PorkImage.setOpacity(0.0);
      FishImage.setOpacity(1.0);
      VegetarianImage.setOpacity(0.0);
    }
    if (entreeField.getValue().equals("Vegetarian")) {
      ChickenImage.setOpacity(0.0);
      SteakImage.setOpacity(0.0);
      PorkImage.setOpacity(0.0);
      FishImage.setOpacity(0.0);
      VegetarianImage.setOpacity(1.0);
    }
  }

  @FXML
  public void DrinkSelected(ActionEvent event) {
    if (drinkField.getValue().equals("Water")) {
      WaterImage.setOpacity(1.0);
      CokeImage.setOpacity(0.0);
      CoffeeImage.setOpacity(0.0);
      TeaImage.setOpacity(0.0);
    }
    if (drinkField.getValue().equals("Coke")) {
      WaterImage.setOpacity(0.0);
      CokeImage.setOpacity(1.0);
      CoffeeImage.setOpacity(0.0);
      TeaImage.setOpacity(0.0);
    }
    if (drinkField.getValue().equals("Coffee")) {
      WaterImage.setOpacity(0.0);
      CokeImage.setOpacity(0.0);
      CoffeeImage.setOpacity(1.0);
      TeaImage.setOpacity(0.0);
    }
    if (drinkField.getValue().equals("Tea")) {
      WaterImage.setOpacity(0.0);
      CokeImage.setOpacity(0.0);
      CoffeeImage.setOpacity(0.0);
      TeaImage.setOpacity(1.0);
    }
  }

  @FXML
  public void SideSelected(ActionEvent event) {
    if (sideField.getValue().equals("Fries")) {
      FriesImage.setOpacity(1.0);
      OnionRingsImage.setOpacity(0.0);
      SoupImage.setOpacity(0.0);
      SaladImage.setOpacity(0.0);
    }
    if (sideField.getValue().equals("Onion Rings")) {
      FriesImage.setOpacity(0.0);
      OnionRingsImage.setOpacity(1.0);
      SoupImage.setOpacity(0.0);
      SaladImage.setOpacity(0.0);
    }
    if (sideField.getValue().equals("Soup")) {
      FriesImage.setOpacity(0.0);
      OnionRingsImage.setOpacity(0.0);
      SoupImage.setOpacity(1.0);
      SaladImage.setOpacity(0.0);
    }
    if (sideField.getValue().equals("Salad")) {
      FriesImage.setOpacity(0.0);
      OnionRingsImage.setOpacity(0.0);
      SoupImage.setOpacity(0.0);
      SaladImage.setOpacity(1.0);
    }
  }
}
