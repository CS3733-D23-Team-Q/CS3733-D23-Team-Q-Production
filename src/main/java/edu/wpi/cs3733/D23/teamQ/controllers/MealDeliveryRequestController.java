package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.App;
import edu.wpi.cs3733.D23.teamQ.db.Qdb;
import edu.wpi.cs3733.D23.teamQ.db.obj.MealRequest;
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

public class MealDeliveryRequestController {
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

  @FXML MFXFilterComboBox drinkField;
  @FXML MFXFilterComboBox entreeField;
  @FXML MFXFilterComboBox sideField;

  @FXML Button resetButton;
  @FXML Button cancelButton;
  @FXML Button submitButton;

  @FXML private ImageView DrinkImage;
  @FXML private ImageView EntreeImage;
  @FXML private ImageView SideImage;
  @FXML private GesturePane titlePane;

  ObservableList<String> drinkList =
      FXCollections.observableArrayList("Water", "Coke", "Coffee", "Tea");
  ObservableList<String> entreeList =
      FXCollections.observableArrayList("Chicken", "Steak", "Pork", "Fish", "Vegetarian");

  ObservableList<String> sideList =
      FXCollections.observableArrayList("Fries", "Onion Rings", "Soup", "Salad");

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

    Image titleImage = new Image(App.class.getResourceAsStream("MealDeliveryTitle.jpg"));
    ImageView iv = new ImageView(titleImage);
    titlePane.setContent(iv);
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

    EntreeImage.setImage(null);
    DrinkImage.setImage(null);
    SideImage.setImage(null);
  }

  @FXML
  public void cancelButtonClicked() {
    Navigation.navigate(Screen.HOME);
  }

  @FXML
  public void submitButtonClicked() {
    Qdb qdb = Qdb.getInstance();

    MealRequest newMR =
        new MealRequest(
            qdb.getNodeFromLocation(roomNumberField.getValue().toString()),
            qdb.retrieveAccount(LoginController.getUsername()),
            qdb.retrieveAccount(assigneeField.getValue().toString().split(",")[0]),
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
      Image chickenImage = new Image(App.class.getResourceAsStream("ChickenEntree.jpg"));
      ImageView iv = new ImageView(chickenImage);
      EntreeImage.setImage(iv.getImage());
    }
    if (entreeField.getValue().equals("Steak")) {
      Image steakImage = new Image(App.class.getResourceAsStream("SteakEntree.jpg"));
      ImageView iv = new ImageView(steakImage);
      EntreeImage.setImage(iv.getImage());
    }
    if (entreeField.getValue().equals("Pork")) {
      Image porkImage = new Image(App.class.getResourceAsStream("PorkEntree.jpg"));
      ImageView iv = new ImageView(porkImage);
      EntreeImage.setImage(iv.getImage());
    }
    if (entreeField.getValue().equals("Fish")) {
      Image fishImage = new Image(App.class.getResourceAsStream("FishEntree.jpg"));
      ImageView iv = new ImageView(fishImage);
      EntreeImage.setImage(iv.getImage());
    }
    if (entreeField.getValue().equals("Vegetarian")) {
      Image vegetarianImage = new Image(App.class.getResourceAsStream("VegetarianEntree.jpg"));
      ImageView iv = new ImageView(vegetarianImage);
      EntreeImage.setImage(iv.getImage());
    }
  }

  @FXML
  public void DrinkSelected(ActionEvent event) {
    if (drinkField.getValue().equals("Water")) {
      Image waterImage = new Image(App.class.getResourceAsStream("WaterDrink.jpg"));
      ImageView iv = new ImageView(waterImage);
      DrinkImage.setImage(iv.getImage());
    }
    if (drinkField.getValue().equals("Coke")) {
      Image cokeImage = new Image(App.class.getResourceAsStream("CokeDrink.jpg"));
      ImageView iv = new ImageView(cokeImage);
      DrinkImage.setImage(iv.getImage());
    }
    if (drinkField.getValue().equals("Coffee")) {
      Image coffeeImage = new Image(App.class.getResourceAsStream("CoffeeDrink.jpg"));
      ImageView iv = new ImageView(coffeeImage);
      DrinkImage.setImage(iv.getImage());
    }
    if (drinkField.getValue().equals("Tea")) {
      Image teaImage = new Image(App.class.getResourceAsStream("TeaDrink.jpg"));
      ImageView iv = new ImageView(teaImage);
      DrinkImage.setImage(iv.getImage());
    }
  }

  @FXML
  public void SideSelected(ActionEvent event) {
    if (sideField.getValue().equals("Fries")) {
      Image friesImage = new Image(App.class.getResourceAsStream("FriesSide.jpg"));
      ImageView iv = new ImageView(friesImage);
      SideImage.setImage(iv.getImage());
    }
    if (sideField.getValue().equals("Onion Rings")) {
      Image onionRingsImage = new Image(App.class.getResourceAsStream("OnionRingsSide.jpg"));
      ImageView iv = new ImageView(onionRingsImage);
      SideImage.setImage(iv.getImage());
    }
    if (sideField.getValue().equals("Soup")) {
      Image soupImage = new Image(App.class.getResourceAsStream("SoupSide.jpg"));
      ImageView iv = new ImageView(soupImage);
      SideImage.setImage(iv.getImage());
    }
    if (sideField.getValue().equals("Salad")) {
      Image saladImage = new Image(App.class.getResourceAsStream("SaladSide.jpg"));
      ImageView iv = new ImageView(saladImage);
      SideImage.setImage(iv.getImage());
    }
  }
}
