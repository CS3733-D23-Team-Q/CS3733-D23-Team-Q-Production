package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.db.Qdb;
import edu.wpi.cs3733.D23.teamQ.db.obj.Account;
import edu.wpi.cs3733.D23.teamQ.db.obj.Question;
import edu.wpi.cs3733.D23.teamQ.navigation.Navigation;
import edu.wpi.cs3733.D23.teamQ.navigation.Screen;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXFilterComboBox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class EditProfileController {

  Qdb qdb = Qdb.getInstance();

  @FXML public MFXButton ProfilePage_Done_Button;
  @FXML public Label question1Display;
  @FXML public Label question2Display;
  @FXML private MFXTextField ProfileEditPage_Email_TextField;

  @FXML private MFXTextField ProfileEditPage_FirstName_TextField;

  // @FXML private MFXTextField ProfileEditPage_IDNumber_TextField;

  @FXML private MFXTextField ProfileEditPage_LastName_TextField;

  @FXML private MFXTextField ProfileEditPage_PhoneNumber_TextField;

  @FXML private MFXTextField ProfileEditPage_Title_TextField;

  @FXML private Label ProfileEditPage_Username_TextField;
  @FXML private MFXTextField ProfileEditPage_Answer1_TextField;
  @FXML private MFXTextField ProfileEditPage_Answer2_TextField;

  @FXML private MFXFilterComboBox editQuestion1Display;
  @FXML private MFXFilterComboBox editQuestion2Display;

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

    this.ProfileEditPage_Title_TextField.setText(qdb.retrieveAccount(username).getTitle());
    this.ProfileEditPage_FirstName_TextField.setText(qdb.retrieveAccount(username).getFirstName());
    this.ProfileEditPage_LastName_TextField.setText(qdb.retrieveAccount(username).getLastName());
    this.ProfileEditPage_Email_TextField.setText(account.getEmail());

    this.ProfileEditPage_PhoneNumber_TextField.setText(
        String.valueOf(qdb.retrieveAccount(username).getPhoneNumber()));

    this.ProfileEditPage_Username_TextField.setText(username);
    this.ProfileEditPage_Answer1_TextField.setText(account.getSecurityAnswer1());
    this.ProfileEditPage_Answer2_TextField.setText(account.getSecurityAnswer2());

    this.editQuestion1Display.setText(
        qdb.retrieveQuestion(account.getSecurityQuestion1()).getQuestion());
    this.editQuestion2Display.setText(
        qdb.retrieveQuestion(account.getSecurityQuestion2()).getQuestion());

    this.editQuestion1Display.setItems(getQuestions());
    this.editQuestion2Display.setItems(getQuestions());
  }

  @FXML
  public void DonePressed() {
    String username = LoginController.getLoginUsername();

    Account newAccount =
        new Account(
            username,
            qdb.retrieveAccount(username).getPassword(),
            ProfileEditPage_Email_TextField.getText(),
            qdb.getQuestionIndex(editQuestion1Display.getText()) + 1,
            qdb.getQuestionIndex(editQuestion2Display.getText()) + 1,
            ProfileEditPage_Answer1_TextField.getText(),
            ProfileEditPage_Answer2_TextField.getText(),
            qdb.retrieveAccount(username).isActive(),
            qdb.retrieveAccount(username).getIDNum(),
            ProfileEditPage_FirstName_TextField.getText(),
            ProfileEditPage_LastName_TextField.getText(),
            ProfileEditPage_Title_TextField.getText(),
            Integer.parseInt(ProfileEditPage_PhoneNumber_TextField.getText()));

    qdb.updateAccount(username, newAccount);
    Navigation.navigate(Screen.PROFILE_PAGE);
  }
}
