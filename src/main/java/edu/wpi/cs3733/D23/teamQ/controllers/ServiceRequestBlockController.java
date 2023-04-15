package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.App;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

public class ServiceRequestBlockController {
  @FXML HBox sRequestsHB;
  @FXML MFXButton sRequests;
  @FXML ImageView sRequestsIcon;
  @FXML AnchorPane sRequestsSMPane;

  @FXML
  public void sRequestsEntered() {
    sRequestsSMPane.setVisible(true);
    sRequestsIcon.setImage(new Image(App.class.getResourceAsStream("ServiceRequestsBlue.png")));
  }

  @FXML
  public void sRequestsExited() {
    sRequestsSMPane.setVisible(false);
    sRequestsIcon.setImage(new Image(App.class.getResourceAsStream("ServiceRequests.png")));
  }
}
