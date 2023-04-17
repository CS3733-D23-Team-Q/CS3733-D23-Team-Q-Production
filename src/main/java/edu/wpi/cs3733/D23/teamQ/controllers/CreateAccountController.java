package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.Alert;
import edu.wpi.cs3733.D23.teamQ.Confirm;
import edu.wpi.cs3733.D23.teamQ.SecondaryStage;
import edu.wpi.cs3733.D23.teamQ.db.Qdb;
import edu.wpi.cs3733.D23.teamQ.db.obj.Account;
import edu.wpi.cs3733.D23.teamQ.db.obj.Question;
import edu.wpi.cs3733.D23.teamQ.navigation.Screen;
import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class CreateAccountController extends SecondaryStage implements IController {
  Qdb qdb = Qdb.getInstance();
  static Stage stage;
  Alert alert = new Alert();
  Confirm confirm = new Confirm();
  @FXML ChoiceBox<String> questionChoice1 = new ChoiceBox<>();
  @FXML ChoiceBox<String> questionChoice2 = new ChoiceBox<>();
  @FXML TextField usernameField;
  @FXML Label usernameAlert;
  @FXML ImageView usernameAlertImage;
  @FXML PasswordField passwordField;
  @FXML Label passwordAlert;
  @FXML ImageView passwordAlertImage;
  @FXML PasswordField repasswordField;
  @FXML Label CPAlert;
  @FXML ImageView CPAlertImage;
  @FXML TextField emailField;
  @FXML Label emailAlert;
  @FXML ImageView emailAlertImage;
  @FXML TextField answer1Field;
  @FXML Label a1Alert;
  @FXML ImageView a1AlertImage;
  @FXML TextField answer2Field;
  @FXML Label a2Alert;
  @FXML ImageView a2AlertImage;

  public CreateAccountController() throws IOException {}

  public static void display() throws IOException {
    stage = newStage(Screen.CREATE_ACCOUNT);
    stage.show();
    stage.centerOnScreen();
  }

  @FXML
  public void initialize() {
    List<Question> questions = qdb.retrieveAllQuestions();
    for (int i = 0; i < questions.size(); i++) {
      String question = questions.get(i).getQuestion();
      questionChoice1.getItems().add(question);
    }
    for (int i = 0; i < questions.size(); i++) {
      String question = questions.get(i).getQuestion();
      questionChoice2.getItems().add(question);
    }
  }

  public static int validEmail(String email) {
    int result = 0;
    Pattern pattern = Pattern.compile("[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+");
    if (email.length() < 1) {
      result = 0;
    } else if (pattern.matcher(email).matches()) {
      result = 1;
    } else {
      result = 2;
    }
    return result;
  }

  public int validUsername(String uname) {
    int result = 0;
    Pattern pattern = Pattern.compile("^[a-z0-9_-]{3,15}$");
    if (uname.length() < 1) {
      result = 0;
    } else if (pattern.matcher(uname).matches()) {
      result = 1;
    } else {
      if (uname.length() < 3 || uname.length() > 15) {
        result = 2;
      } else {
        result = 3;
      }
    }
    return result;
  }

  public int validPassword(String password) {
    int result = 0;
    Pattern pattern =
        Pattern.compile("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*_+=]).{7,15}$");
    if (password.length() < 1) {
      result = 0;
    } else if (pattern.matcher(password).matches()) {
      result = 1;
    } else {
      result = 2;
    }
    return result;
  }

  public void usernameReact(
      String username,
      String email,
      String password,
      String repassword,
      String question1,
      String question2,
      String answer1,
      String answer2)
      throws IOException {
    switch (validUsername(username)) {
      case 0:
        alert.setLabelAlert("Please enter a username", usernameAlert, usernameAlertImage);
        break;
      case 1:
        alert.clearLabelAlert(usernameAlert, usernameAlertImage);
        emailReact(username, email, password, repassword, question1, question2, answer1, answer2);
        break;
      case 2:
        alert.setLabelAlert(
            "Please enter a username within the range 3-15", usernameAlert, usernameAlertImage);
        break;
      case 3:
        alert.setLabelAlert(
            "Invalid username. (special characters allowed: _ and - only)",
            usernameAlert,
            usernameAlertImage);
        break;
    }
  }

  public void emailReact(
      String username,
      String email,
      String password,
      String repassword,
      String question1,
      String question2,
      String answer1,
      String answer2)
      throws IOException {
    switch (validEmail(email)) {
      case 0:
        alert.setLabelAlert("Please enter a email", emailAlert, emailAlertImage);
        break;
      case 1:
        alert.clearLabelAlert(emailAlert, emailAlertImage);
        passwordReact(
            username, email, password, repassword, question1, question2, answer1, answer2);
        break;
      case 2:
        alert.setLabelAlert("Invalid email address", emailAlert, emailAlertImage);
        break;
    }
  }

  public void passwordReact(
      String username,
      String email,
      String password,
      String repassword,
      String question1,
      String question2,
      String answer1,
      String answer2)
      throws IOException {
    switch (validPassword(password)) {
      case 0:
        alert.setLabelAlert("Please enter a password", passwordAlert, passwordAlertImage);
        break;
      case 1:
        alert.clearLabelAlert(passwordAlert, passwordAlertImage);
        repasswordReact(
            username, email, password, repassword, question1, question2, answer1, answer2);
        break;
      case 2:
        alert.setLabelAlert(
            "Please enter a password within the range 7-15 with at least one uppercase letter, one number, one special character",
            passwordAlert,
            passwordAlertImage);
        break;
    }
  }

  public void repasswordReact(
      String username,
      String email,
      String password,
      String repassword,
      String question1,
      String question2,
      String answer1,
      String answer2)
      throws IOException {
    if (password.equals(repassword)) {
      alert.clearLabelAlert(CPAlert, CPAlertImage);
      securityQReact1(
          username, email, password, repassword, question1, question2, answer1, answer2);
    } else {
      alert.setLabelAlert("Password doesn't match", CPAlert, CPAlertImage);
    }
  }

  public void securityQReact1(
      String username,
      String email,
      String password,
      String repassword,
      String question1,
      String question2,
      String answer1,
      String answer2)
      throws IOException {
    if (questionChoice1.getSelectionModel().isEmpty()) {
      alert.alertBox("Failed to create an account", "Please select a question.");
    } else {
      securityAReact1(
          username, email, password, repassword, question1, question2, answer1, answer2);
    }
  }

  public void securityAReact1(
      String username,
      String email,
      String password,
      String repassword,
      String question1,
      String question2,
      String answer1,
      String answer2)
      throws IOException {
    if (answer1.length() < 1) {
      alert.setLabelAlert("Please enter a answer", a1Alert, a1AlertImage);
    } else {
      alert.clearLabelAlert(a1Alert, a1AlertImage);
      securityQReact2(
          username, email, password, repassword, question1, question2, answer1, answer2);
    }
  }

  public void securityQReact2(
      String username,
      String email,
      String password,
      String repassword,
      String question1,
      String question2,
      String answer1,
      String answer2)
      throws IOException {
    if (questionChoice2.getSelectionModel().isEmpty()) {
      alert.alertBox("Failed to create an account", "Please select a question.");
    } else if (question2.equals(question1)) {
      alert.alertBox("Failed to create an account", "Please choose a different question.");
    } else {
      securityAReact2(
          username, email, password, repassword, question1, question2, answer1, answer2);
    }
  }

  public void securityAReact2(
      String username,
      String email,
      String password,
      String repassword,
      String question1,
      String question2,
      String answer1,
      String answer2)
      throws IOException {
    if (answer2.length() < 1) {
      alert.setLabelAlert("Please enter a answer", a2Alert, a2AlertImage);
    } else {
      alert.clearLabelAlert(a2Alert, a2AlertImage);
      Account a =
          new Account(
              username,
              password,
              email,
              qdb.retrieveQuestion(question1).getId(),
              qdb.retrieveQuestion(question2).getId(),
              answer1,
              answer2,
              false,
              null,
              null,
              null,
              0);
      qdb.addAccount(a);
      confirm.setScene(stage, "Confirmation", "Account created successful!");
    }
  }

  @FXML
  public void SUButtonClicked() throws IOException {
    String username = usernameField.getText();
    String email = emailField.getText();
    String password = passwordField.getText();
    String repassword = repasswordField.getText();
    String question1 = questionChoice1.getValue();
    String question2 = questionChoice2.getValue();
    String answer1 = answer1Field.getText();
    String answer2 = answer2Field.getText();

    if (qdb.getAccountIndex(username) == -1) {
      alert.clearLabelAlert(usernameAlert, usernameAlertImage);
      usernameReact(username, email, password, repassword, question1, question2, answer1, answer2);
    } else {
      alert.setLabelAlert(
          "Username already exist, try another one", usernameAlert, usernameAlertImage);
    }
  }
}
