package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.db.Qdb;
import edu.wpi.cs3733.D23.teamQ.db.impl.ProfileImageDaoImpl;
import edu.wpi.cs3733.D23.teamQ.db.obj.Account;
import edu.wpi.cs3733.D23.teamQ.navigation.Navigation;
import edu.wpi.cs3733.D23.teamQ.navigation.Screen;
import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.SQLException;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class ProfilePage1Controller {

  Qdb qdb = Qdb.getInstance();
  @FXML private HBox myHBox;
  @FXML private ImageView profileImage;
  @FXML private Text fullName;
  @FXML private Text title;
  @FXML private Text displayUsername;
  @FXML private Text email;
  @FXML private Text phone;
  @FXML private Text status;

  public ProfilePage1Controller() throws SQLException {}

  public void initialize() throws IOException, SQLException {
    String username = LoginController.getLoginUsername();
    ProfileImageDaoImpl dao = new ProfileImageDaoImpl();
    Account account = qdb.retrieveAccount(username);

    fullName.setText(account.getFirstName() + " " + account.getLastName());
    title.setText(account.getTitle());
    displayUsername.setText(username);
    email.setText(account.getEmail());
    phone.setText(Integer.toString(account.getPhoneNumber()));
    status.setText("Online");

    byte[] imageData = dao.retrieveRow(username).getImageData();
    ByteArrayInputStream bis = new ByteArrayInputStream(imageData);
    Image pfp = new Image(bis);
    profileImage.setImage(pfp);

  }

  public void editPressed() {
    Navigation.navigate(Screen.EDIT_PROFILE);
  }
}
