package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.db.Qdb;
import edu.wpi.cs3733.D23.teamQ.db.obj.Account;
import edu.wpi.cs3733.D23.teamQ.navigation.Navigation;
import edu.wpi.cs3733.D23.teamQ.navigation.Screen;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class ViewProfilePageController {
  Qdb qdb = Qdb.getInstance();
  @FXML private HBox myHBox;
  @FXML private ImageView profileImage;
  @FXML private Text fullName;
  @FXML private Text title;
  @FXML private Text displayUsername;
  @FXML private Text email;
  @FXML private Text phone;
  @FXML private Text status;
  @FXML private MFXButton backButton;

  public void initialize() {
    String loggedInUser = LoginController.getLoginUsername();
    String username;
    if (LoginController.isAdmin()) {
      username = AdminDirectoryController.getViewProfileUsername();
    } else {
      username = DirectoryController.getViewProfileUsername();
    }

    Account account = qdb.retrieveAccount(username);

    fullName.setText(account.getFirstName() + " " + account.getLastName());
    title.setText(account.getTitle());
    displayUsername.setText(username);
    email.setText(account.getEmail());
    phone.setText(Integer.toString(account.getPhoneNumber()));
    status.setText("Online");

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
}
