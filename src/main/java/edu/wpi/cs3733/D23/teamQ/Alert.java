package edu.wpi.cs3733.D23.teamQ;

import edu.wpi.cs3733.D23.teamQ.controllers.AlertController;
import edu.wpi.cs3733.D23.teamQ.navigation.Navigation;
import edu.wpi.cs3733.D23.teamQ.navigation.Screen;
import java.io.IOException;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Alert extends SecondaryStage {

  public void setLabelAlert(String message, Label type, ImageView alertImage) {
    type.setText(message);
    type.setStyle("-fx-text-fill: #AA3A47;");
    alertImage.setOpacity(1);
  }

  public void clearLabelAlert(Label type, ImageView alertImage) {
    type.setText("");
    type.setStyle(null);
    alertImage.setOpacity(0);
  }

  public void setFieldAlert(TextField type) {
    type.setStyle("-fx-text-box-border: #AA3A47;");
  }

  public void clearFieldAlert(TextField type) {
    type.setStyle(null);
  }

  public void alertBox(String title, String message) throws IOException {
    AlertController ac = (AlertController) Navigation.getController(Screen.ALERT);
    ac.setMessage(message);
    Stage stage = newStage(title, Screen.ALERT);
    stage.initStyle(StageStyle.TRANSPARENT);
    ac.setStage(stage);
    stage.show();
    stage.centerOnScreen();
  }
}
