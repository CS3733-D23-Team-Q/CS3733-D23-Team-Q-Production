package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.db.Qdb;
import edu.wpi.cs3733.D23.teamQ.db.obj.Account;
import edu.wpi.cs3733.D23.teamQ.db.obj.Question;
import edu.wpi.cs3733.D23.teamQ.navigation.Navigation;
import edu.wpi.cs3733.D23.teamQ.navigation.Screen;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class EditProfileController {

  Qdb qdb = Qdb.getInstance();
  @FXML private Text fullName;
  @FXML private Text title;

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
  }

  @FXML
  public void DonePressed() {
    String username = LoginController.getLoginUsername();

    // Account newAccount = Account();

    // qdb.updateAccount(username, newAccount);
    Navigation.navigate(Screen.PROFILE_PAGE);
  }
}
