package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.Alert;
import edu.wpi.cs3733.D23.teamQ.db.impl.AccountDaoImpl;
import edu.wpi.cs3733.D23.teamQ.db.obj.Account;
import edu.wpi.cs3733.D23.teamQ.navigation.Navigation;
import edu.wpi.cs3733.D23.teamQ.navigation.Screen;
import java.io.IOException;
import java.util.List;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import lombok.Getter;

public class LoginController {
  AccountDaoImpl dao = AccountDaoImpl.getInstance();
  Alert alert = new Alert();
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

  public void passwordReact(String enteredPassword, String actualPassword) {
    if (enteredPassword.equals(actualPassword)) {
      alert.clearLabelAlert(loginAlert, alertImage);
      Navigation.navigate(Screen.HOME);
      loginUsername = usernameField.getText();
      loginEmail = dao.retrieveRow(loginUsername).getEmail();

    } else {
      alert.setLabelAlert("Wrong password", loginAlert, alertImage);
    }
  }

  @FXML
  public void loginButtonClicked() throws IOException {
    String username = usernameField.getText();
    String enteredPassword = passwordField.getText();
    String actualPassword = "";
    if (dao.getIndex(username) != -1) {
      alert.clearLabelAlert(loginAlert, alertImage);
      Account a = dao.retrieveRow(username);
      actualPassword = a.getPassword();
      passwordReact(enteredPassword, actualPassword);
    } else if (!dao.getIndexes(username).isEmpty()) {
      alert.clearLabelAlert(loginAlert, alertImage);
      List<Account> as = dao.retrieveRows(username);
      for (Account a : as) {
        actualPassword = a.getPassword();
        passwordReact(enteredPassword, actualPassword);
      }
    } else {
      alert.setLabelAlert("User doesn't exist", loginAlert, alertImage);
    }
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
