package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.App;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import net.kurobako.gesturefx.GesturePane;

public class MealDeliveryRequest2Controller {
  @FXML GesturePane titlePane;

  @FXML
  public void initialize() {
    Image titleImage = new Image(App.class.getResourceAsStream("MealDeliveryTitle.jpg"));
    ImageView iv = new ImageView(titleImage);
    titlePane.setContent(iv);
  }
}
