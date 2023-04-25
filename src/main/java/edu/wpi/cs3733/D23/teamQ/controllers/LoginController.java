package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.Alert;
import edu.wpi.cs3733.D23.teamQ.App;
import edu.wpi.cs3733.D23.teamQ.SecondaryStage;
import edu.wpi.cs3733.D23.teamQ.db.Qdb;
import edu.wpi.cs3733.D23.teamQ.db.impl.AccountDaoImpl;
import edu.wpi.cs3733.D23.teamQ.db.obj.Account;
import edu.wpi.cs3733.D23.teamQ.navigation.Navigation;
import edu.wpi.cs3733.D23.teamQ.navigation.Screen;
import java.io.IOException;
import java.util.List;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import lombok.Getter;

public class LoginController extends SecondaryStage implements IController {
  AccountDaoImpl dao = AccountDaoImpl.getInstance();
  static String user;
  Alert alert = new Alert();
  Qdb qdb = Qdb.getInstance();
  @FXML Label loginAlert;
  @FXML ImageView alertImage;
  @FXML TextField usernameField;
  @FXML TextField passwordField;
  @FXML Button loginButton;
  @FXML Button FPButton;
  @FXML Button CAButton;

  @FXML Button quitButton;

  @Getter private static String loginUsername;
  @Getter private static String loginEmail;

  public void initialize() {}

  @FXML
  public void quitButtonClicked() {
    Platform.exit();
  }

  /**
   * Whenever the Enter key is pressed inside the username textfield, change the focus to the
   * password textfield.
   *
   * @param e A key pressed event received from the username textfield.
   */
  @FXML
  public void usernameFieldEntered(KeyEvent e) {
    if (e.getCode().equals(KeyCode.ENTER)) {
      passwordField.requestFocus();
    }
  }

  @FXML
  public void passwordFieldEntered(KeyEvent e) throws IOException {
    if (e.getCode().equals(KeyCode.ENTER)) {
      loginButtonClicked();
    }
  }

  @FXML
  public void loginButtonClicked() throws IOException {
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
      qdb.updateAccount(username, a);
      alert.clearLabelAlert(loginAlert, alertImage);
      Screen menuScreen = Screen.MENU_PANE;
      final String filename = menuScreen.getFilename();
      final var resource = App.class.getResource(filename);
      final FXMLLoader loader = new FXMLLoader(resource);
      Node n = loader.load();
      App.getRootBorder().setLeft(n);
      App.getRController().showMenu(true);
      Navigation.navigate(Screen.HOME);
      loginUsername = usernameField.getText();
      loginEmail = dao.retrieveRow(loginUsername).getEmail();
    } else {
      alert.setLabelAlert("Wrong password", loginAlert, alertImage);
    }
  }

  public static String getUsername() {
    return user;
  }

  @FXML
  public void FPButtonClicked() throws IOException {
    ForgotPasswordController.display();
  }

  @FXML
  public void CAButtonClicked() throws IOException {
    CreateAccountController.display();
  }
}
