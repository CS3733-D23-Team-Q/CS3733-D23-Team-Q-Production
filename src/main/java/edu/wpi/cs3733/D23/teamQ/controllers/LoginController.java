package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.Alert;
import edu.wpi.cs3733.D23.teamQ.App;
import edu.wpi.cs3733.D23.teamQ.db.Qdb;
import edu.wpi.cs3733.D23.teamQ.db.impl.AccountDaoImpl;
import edu.wpi.cs3733.D23.teamQ.db.obj.Account;
import edu.wpi.cs3733.D23.teamQ.navigation.Navigation;
import edu.wpi.cs3733.D23.teamQ.navigation.Screen;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import java.io.IOException;
import java.util.List;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import lombok.Getter;
import net.kurobako.gesturefx.GesturePane;

public class LoginController {
  AccountDaoImpl dao = AccountDaoImpl.getInstance();
  static String user;
  Alert alert = new Alert();
  Qdb qdb = Qdb.getInstance();
  @FXML Label loginAlert;
  @FXML ImageView alertImage;
  @FXML MFXTextField usernameField;
  @FXML MFXPasswordField passwordField;
  @FXML MFXButton fpButton;
  @FXML MFXButton login;
  @FXML MFXButton exit;
  @FXML GesturePane imagePane;

  @Getter private static String loginUsername;
  @Getter private static String loginEmail;

  @FXML
  public void initialize() {
    Image hImage = new Image(App.class.getResourceAsStream("Hospital.jpeg"));
    ImageView imageView = new ImageView(hImage);
    imageView.setOpacity(0.75);
    imagePane.setContent(imageView);
  }

  @FXML
  public void exitClicked() {

    Platform.exit();
  }

  @FXML
  public void usernameEntered(KeyEvent e) {
    if (e.getCode().equals(KeyCode.ENTER)) {
      passwordField.requestFocus();
    }
  }

  @FXML
  public void passwordFieldEntered(KeyEvent e) throws IOException {
    if (e.getCode().equals(KeyCode.ENTER)) {
      loginClicked();
    }
  }

  @FXML
  public void loginClicked() throws IOException {
    String username = usernameField.getText();
    String enteredPassword = passwordField.getText();
    String actualPassword = "";
    if (qdb.getAccountIndex(username) != -1) {
      alert.clearLabelAlert(loginAlert, alertImage);
      Account a = qdb.retrieveAccount(username);
      actualPassword = a.getPassword();
      passwordReact(username, enteredPassword, actualPassword);
    } else if (!qdb.getAccountIndexes(username).isEmpty()) {
      alert.clearLabelAlert(loginAlert, alertImage);
      List<Account> as = qdb.retrieveAccounts(username);
      for (Account a : as) {
        actualPassword = a.getPassword();
        username = a.getUsername();
        passwordReact(username, enteredPassword, actualPassword);
      }
    } else {
      alert.setLabelAlert("User doesn't exist", loginAlert, alertImage);
    }
  }

  public void passwordReact(String username, String enteredPassword, String actualPassword)
      throws IOException {
    if (enteredPassword.equals(actualPassword)) {
      user = username;
      Account a = qdb.retrieveAccount(username);
      a.setActive(true);
      // qdb.updateAccount(username, a);
      alert.clearLabelAlert(loginAlert, alertImage);
      Screen menuScreen = Screen.MENU_PANE;
      final String filename = menuScreen.getFilename();
      final var resource = App.class.getResource(filename);
      final FXMLLoader loader = new FXMLLoader(resource);
      Node n = loader.load();
      App.getRootBorder().setLeft(n);
      App.getRController().showMenu(true);

      loginUsername = usernameField.getText();
      qdb.getAccountFromUsername(loginUsername).setActive(true);
      loginEmail = dao.retrieveRow(loginUsername).getEmail();
      Navigation.navigate(Screen.HOME);
    } else {
      alert.setLabelAlert("Wrong password", loginAlert, alertImage);
    }
  }

  @FXML
  public void fpbuttonClicked() throws IOException {
    ForgotPasswordController.display();
  }

  public static String getUsername() {
    return user;
  }
}
