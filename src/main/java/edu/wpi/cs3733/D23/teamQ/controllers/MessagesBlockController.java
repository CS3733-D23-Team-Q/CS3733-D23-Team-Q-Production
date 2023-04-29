package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.App;
import edu.wpi.cs3733.D23.teamQ.db.Qdb;
import edu.wpi.cs3733.D23.teamQ.db.dao.Subscriber;
import edu.wpi.cs3733.D23.teamQ.navigation.Navigation;
import edu.wpi.cs3733.D23.teamQ.navigation.Screen;
import io.github.palexdev.materialfx.controls.MFXButton;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

public class MessagesBlockController implements Subscriber {
  @FXML AnchorPane anchor;
  @FXML HBox messagesHB;
  @FXML MFXButton messages;
  @FXML ImageView messagesIcon;
  @FXML AnchorPane messagesAlertPane;
  @FXML MessagesAlertController messagesAlertController;
  @FXML MenuController mc;
  Qdb qdb = Qdb.getInstance();

  @FXML
  public void initialize() {
    qdb.subscribe(this);
  }

  @FXML
  public void anchorEntered() {
    mc.closeAll();
    messages.setStyle("-fx-background-color: #f1f1f1; -fx-text-fill: #012d5a");
    messagesIcon.setImage(new Image(App.class.getResourceAsStream("MessagesBlue.png")));
  }

  @FXML
  public void anchorExited() {
    messages.setStyle("-fx-background-color: #012d5a; -fx-text-fill: #f1f1f1");
    messagesIcon.setImage(new Image(App.class.getResourceAsStream("Messages.png")));
  }

  public void messagesClicked() {
    mc.showAll();
    Navigation.navigate(Screen.MESSAGES);
    messagesAlertPane.setVisible(false);
  }

  @FXML
  public void messagesEntered() {}

  @FXML
  public void messagesExited() {}

  public void setMCController(MenuController MC) {
    mc = MC;
  }

  @FXML
  public void hideBlock() {
    messagesHB.setVisible(false);
  }

  @FXML
  public void showBlock() {
    messagesHB.setVisible(true);
  }

  @Override
  public boolean update(List<String> context) {
    if (context.contains("message")) {
      int num = qdb.getNumUnread(LoginController.getLoginUsername());
      if(num > 0 && num < 9)
      {
        messagesAlertController.setNum("" + num);
      }
      else {
        messagesAlertController.setNum("9+");
      }
      messagesAlertPane.setVisible(true);
    }
    return false;
  }
}
