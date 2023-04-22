package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.App;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

public class ServiceRequestBlockController {
  @FXML AnchorPane anchor;
  @FXML HBox sRequestsHB;
  @FXML MFXButton sRequests;
  @FXML ImageView sRequestsIcon;
  @FXML AnchorPane sRequestsSMPane;
  @FXML MenuController mc;

  @FXML
  public void anchorEntered() {
    mc.closeAll();
    sRequests.setStyle("-fx-background-color: #f1f1f1; -fx-text-fill: #012d5a");
    sRequestsIcon.setImage(new Image(App.class.getResourceAsStream("ServiceRequestsBlue.png")));
    sRequestsSMPane.setVisible(true);
  }

  @FXML
  public void anchorExited() {
    sRequests.setStyle("-fx-background-color: #012d5a; -fx-text-fill: #f1f1f1");
    sRequestsIcon.setImage(new Image(App.class.getResourceAsStream("ServiceRequests.png")));
    sRequestsSMPane.setVisible(false);
  }

  @FXML
  public void sRequestsEntered() {}

  @FXML
  public void sRequestsExited() {}

  @FXML
  public void hideBlock() {
    sRequestsHB.setVisible(false);
  }

  @FXML
  public void showBlock() {
    sRequestsHB.setVisible(true);
  }

  @FXML
  public void hideSM() {
    sRequestsSMPane.setVisible(false);
  }

  public void setMCController(MenuController MC) {
    mc = MC;
  }
}
