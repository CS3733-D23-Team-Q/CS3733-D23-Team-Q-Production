package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.db.Qdb;
import edu.wpi.cs3733.D23.teamQ.db.obj.Account;
import edu.wpi.cs3733.D23.teamQ.db.obj.Question;
import edu.wpi.cs3733.D23.teamQ.navigation.Navigation;
import edu.wpi.cs3733.D23.teamQ.navigation.Screen;
import io.github.palexdev.materialfx.controls.MFXFilterComboBox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class EditProfileController {
  Qdb qdb = Qdb.getInstance();

  @FXML private Text fullName;
  @FXML private Text title;
  @FXML private MFXTextField firstName;
  @FXML private MFXTextField lastName;
  @FXML private MFXTextField email;
  @FXML private MFXTextField phone;
  @FXML private MFXTextField usernameField;
  @FXML private MFXTextField securityAnswer1;
  @FXML private MFXTextField securityAnswer2;
  @FXML private MFXFilterComboBox securityQuestion1;
  @FXML private MFXFilterComboBox securityQuestion2;

  private ObservableList<String> getQuestions() {
    ObservableList<String> questions = FXCollections.observableArrayList();
    List<Question> allQuestions = qdb.retrieveAllQuestions();
    for (int i = 0; i < allQuestions.size() - 1; i++) {
      questions.add(allQuestions.get(i).getQuestion());
    }
    return questions;
  }

  @FXML
  private void initialize() {
    Qdb qdb = Qdb.getInstance();
    String username = LoginController.getLoginUsername();
    Account account = qdb.retrieveAccount(username);

    fullName.setText(account.getFirstName() + " " + account.getLastName());
    title.setText(account.getTitle());
    firstName.setText(account.getFirstName());
    lastName.setText(account.getLastName());
    email.setText(account.getEmail());
    phone.setText(Integer.toString(account.getPhoneNumber()));
    usernameField.setText(username);
    securityAnswer1.setText(account.getSecurityAnswer1());
    securityAnswer2.setText(account.getSecurityAnswer2());
    securityQuestion1.setText(qdb.retrieveQuestion(account.getSecurityQuestion1()).getQuestion());
    securityQuestion2.setText(qdb.retrieveQuestion(account.getSecurityQuestion2()).getQuestion());
    securityQuestion1.setItems(getQuestions());
    securityQuestion2.setItems(getQuestions());
  }

  @FXML
  public void donePressed() {
    String username = LoginController.getLoginUsername();
    Account account = qdb.retrieveAccount(username);

    Account newAccount =
        new Account(
            usernameField.getText(),
            account.getPassword(),
            email.getText(),
            qdb.getQuestionIndex(securityQuestion1.getText()) + 1,
            qdb.getQuestionIndex(securityQuestion2.getText()) + 1,
            securityAnswer1.getText(),
            securityAnswer2.getText(),
            account.isActive(),
            account.getIDNum(),
            firstName.getText(),
            lastName.getText(),
            account.getTitle(),
            Integer.parseInt(phone.getText()));

    qdb.updateAccount(username, newAccount);
    Navigation.navigate(Screen.PROFILE_PAGE);
  }
}
