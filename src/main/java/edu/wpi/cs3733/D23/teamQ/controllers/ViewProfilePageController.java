package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.db.Qdb;
import edu.wpi.cs3733.D23.teamQ.db.dao.Subscriber;
import edu.wpi.cs3733.D23.teamQ.db.obj.Account;
import edu.wpi.cs3733.D23.teamQ.navigation.Navigation;
import edu.wpi.cs3733.D23.teamQ.navigation.Screen;
import io.github.palexdev.materialfx.controls.MFXButton;
import java.net.URISyntaxException;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;

public class ViewProfilePageController implements Subscriber {
  Qdb qdb = Qdb.getInstance();
  @FXML private HBox myHBox;
  @FXML private ImageView profileImage;
  @FXML private Label fullName;
  @FXML private Label title;
  @FXML private Label displayUsername;
  @FXML private Label email;
  @FXML private Label phone;
  @FXML private Label status;
  @FXML private MFXButton backButton;

  @FXML private MFXButton chatButton;
  @FXML private Circle statusCircle;

  Account a;

  public void initialize() {
    String loggedInUser = LoginController.getLoginUsername();
    qdb.subscribe(this);
    String username;
    if (LoginController.isAdmin()) {
      username = AdminDirectoryController.getViewProfileUsername();
    } else {
      username = DirectoryController.getViewProfileUsername();
    }

    Account account = qdb.retrieveAccount(username);
    a = account;

    fullName.setText(account.getFirstName() + " " + account.getLastName());
    title.setText(account.getTitle());
    displayUsername.setText(username);
    email.setText(account.getEmail());
    phone.setText(account.getPhoneNumber());

    statusCircle.setStroke(null);
    if (account.isActive()) {
      status.setText("Online");
      statusCircle.setFill(javafx.scene.paint.Color.GREEN);
    } else {
      status.setText("Offline");
      statusCircle.setFill(javafx.scene.paint.Color.RED);
    }

    if (qdb.getProfileImageIndex(username) != -1) {
      Image pfp = qdb.convertByteaToImage(qdb.retrieveProfileImage(username).getImageData());
      profileImage.setImage(pfp);
    }

    Image image = new Image(getClass().getResourceAsStream("/BackButton.png"));
    ImageView imageView = new ImageView(image);
    imageView.setFitHeight(20.0);
    imageView.setFitWidth(20.0);
    backButton.setText("");
    backButton.setGraphic(imageView);
  }

  public void backButtonPressed() {
    String username = LoginController.getLoginUsername();
    if (LoginController.isAdmin()) {
      Navigation.navigate(Screen.ADMIN_DIRECTORY);
    } else {
      Navigation.navigate(Screen.DIRECTORY);
    }
  }

  public void chatButtonClicked() {

    String loggedInUser = LoginController.getLoginUsername();
    String username;
    if (LoginController.isAdmin()) {
      username = AdminDirectoryController.getViewProfileUsername();
    } else {
      username = DirectoryController.getViewProfileUsername();
    }

    qdb.setMessagingAccount(qdb.getAccountFromUsername(username));
    Navigation.navigate(Screen.MESSAGES);
  }

  public boolean update(List<String> context) throws URISyntaxException {
    if (context.contains("account")) {
      String s = a.getUsername();
      if (qdb.getAccountFromUsername(s).isActive()) {
        System.out.println("ACTIVE");
        status.setText("Online");
        statusCircle.setStyle("-fx-fill: #37AC2B");
      }
      if (!(qdb.getAccountFromUsername(s).isActive())) {
        System.out.println("NOT ACTIVE");
        status.setText("Offline");
        statusCircle.setStyle("-fx-fill: #CE3C49");
      }
    }
    return false;
  }
}
