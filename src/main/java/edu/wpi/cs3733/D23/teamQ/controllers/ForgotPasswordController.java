package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.Alert;
import edu.wpi.cs3733.D23.teamQ.Confirm;
import edu.wpi.cs3733.D23.teamQ.SecondaryStage;
import edu.wpi.cs3733.D23.teamQ.db.Qdb;
import edu.wpi.cs3733.D23.teamQ.db.obj.Account;
import edu.wpi.cs3733.D23.teamQ.db.obj.Question;
import edu.wpi.cs3733.D23.teamQ.navigation.Navigation;
import edu.wpi.cs3733.D23.teamQ.navigation.Screen;
import java.io.IOException;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class ForgotPasswordController extends SecondaryStage implements IController {
  Qdb qdb = Qdb.getInstance();
  CreateAccountController CAController = new CreateAccountController();
  Alert alert = new Alert();
  Confirm confirm = new Confirm();
  @FXML ChoiceBox<String> questionChoice1;
  @FXML ChoiceBox<String> questionChoice2;
  @FXML TextField usernameField;
  @FXML Label usernameAlert;
  @FXML ImageView usernameAlertImage;
  @FXML TextField answer1Field;
  @FXML TextField answer2Field;
  @FXML PasswordField NPField;
  @FXML Label NPAlert;
  @FXML ImageView NPAlertImage;
  @FXML PasswordField CPField;
  @FXML Label CPAlert;
  @FXML ImageView CPAlertImage;
  @FXML Button CPButton;

  public ForgotPasswordController() throws IOException {}

  public static void display() throws IOException {
    Stage stage = newStage(Screen.FORGOT_PASSWORD);
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

  public void newPasswordReact(
      String username, String newPassword, String repassword, String answer1, String answer2)
      throws IOException {
    Account a = qdb.retrieveAccount(username);
    String oldPassword = a.getPassword();
    if (oldPassword.equals(newPassword)) {
      alert.setLabelAlert(
          "Please enter a different password from the old one", NPAlert, NPAlertImage);
    } else {
      alert.clearLabelAlert(NPAlert, NPAlertImage);
      passwordReact(username, newPassword, repassword, answer1, answer2);
    }
  }

  public void passwordReact(
      String username, String newPassword, String repassword, String answer1, String answer2)
      throws IOException {
    switch (CAController.validPassword(newPassword)) {
      case 0:
        alert.setLabelAlert("Please enter a password", NPAlert, NPAlertImage);
        break;
      case 1:
        alert.clearLabelAlert(NPAlert, NPAlertImage);
        repasswordReact(username, newPassword, repassword, answer1, answer2);
        break;
      case 2:
        alert.setLabelAlert(
            "Please enter a password within the range 7-15 with at least one uppercase letter, one number, one special character",
            NPAlert,
            NPAlertImage);
        break;
    }
  }

  public void repasswordReact(
      String username, String newPassword, String repassword, String answer1, String answer2)
      throws IOException {
    if (newPassword.equals(repassword)) {
      alert.clearLabelAlert(CPAlert, CPAlertImage);
      securityQReact(username, newPassword, repassword, answer1, answer2);
    } else {
      alert.setLabelAlert("Password doesn't match", CPAlert, CPAlertImage);
    }
  }

  public void securityQReact(
      String username, String newPassword, String repassword, String answer1, String answer2)
      throws IOException {
    String question1 = questionChoice1.getValue();
    String question2 = questionChoice2.getValue();
    if (qdb.getQuestionIndex(question1) != -1 || qdb.getQuestionIndex(question2) != -1) {
      securityAReact(username, newPassword, repassword, answer1, answer2);
    } else {
      alert.setFieldAlert(answer1Field);
      alert.setFieldAlert(answer2Field);
      alert.alertBox("Failed to reset password", "One or more answers are wrong.");
    }
  }

  public void securityAReact(
      String username, String newPassword, String repassword, String answer1, String answer2)
      throws IOException {
    String question1 = questionChoice1.getValue();
    String question2 = questionChoice2.getValue();
    int question1id = qdb.retrieveQuestion(question1).getId();
    int question2id = qdb.retrieveQuestion(question2).getId();
    Account a = qdb.retrieveAccount(username);
    int actualq1 = a.getSecurityQuestion1();
    int actualq2 = a.getSecurityQuestion2();
    String actuala1 = a.getSecurityAnswer1();
    String actuala2 = a.getSecurityAnswer2();
    if (question1id == actualq1
        && question2id == actualq2
        && answer1.equals(actuala1)
        && answer2.equals(actuala2)) {
      alert.clearFieldAlert(answer1Field);
      alert.clearFieldAlert(answer2Field);
      a.setPassword(newPassword);
      qdb.updateAccount(username, a);
      Navigation.navigate(Screen.LOGIN);
      confirm.setScene(stage, "Confirmation", "Password reset successful!");
    } else {
      alert.setFieldAlert(answer1Field);
      alert.setFieldAlert(answer2Field);
      alert.alertBox("Failed to reset password", "One or more answers are wrong.");
    }
  }

  public void CPButtonClicked() throws IOException {
    String username = usernameField.getText();
    String newPassword = NPField.getText();
    String repassword = CPField.getText();
    String answer1 = answer1Field.getText();
    String answer2 = answer2Field.getText();
    if (qdb.getAccountIndex(username) != -1) {
      alert.clearLabelAlert(usernameAlert, usernameAlertImage);
      newPasswordReact(username, newPassword, repassword, answer1, answer2);
    } else {
      alert.setLabelAlert("Username doesn't exist", usernameAlert, usernameAlertImage);
    }
  }
}
