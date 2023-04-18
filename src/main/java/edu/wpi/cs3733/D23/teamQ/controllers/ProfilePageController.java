package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.db.Qdb;
import edu.wpi.cs3733.D23.teamQ.db.obj.Account;
import edu.wpi.cs3733.D23.teamQ.navigation.Navigation;
import edu.wpi.cs3733.D23.teamQ.navigation.Screen;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ProfilePageController implements IController {

  Qdb qdb = Qdb.getInstance();

  @FXML private Button ProfilePage_Edit_Button;

  @FXML private Label Email_Display;

  @FXML private Label First_Name_Display;

  @FXML private Label ID_Number_Display;

  @FXML private Label Last_Name_Display;

  @FXML private Label Phone_Number_Display;

  @FXML private Label Title_Display;

  @FXML private Label Username_Display;

  @FXML private Label question1Display;
  @FXML private Label answer1Display;
  @FXML private Label question2Display;
  @FXML private Label answer2Display;

  private Button ProfilePage_Home_Button;

  @FXML
  private void initialize() {

    // DO ONE FOR PROFILE IMAGE AS WELL NEXT TIME
    String username = LoginController.getLoginUsername();

    Account account = qdb.retrieveAccount(username);

    Qdb qdb = Qdb.getInstance();

    if (account.getFirstName() == null) {
      this.First_Name_Display.setText("empty");
    } else {
      this.First_Name_Display.setText(account.getFirstName());
    }

    if (account.getLastName() == null) {
      this.Last_Name_Display.setText("empty");
    } else {
      this.Last_Name_Display.setText(account.getLastName());
    }

    this.Email_Display.setText(account.getEmail());
    if (account.getTitle() == null) {
      this.Title_Display.setText("empty");
    } else {
      this.Title_Display.setText(account.getTitle());
    }
    if (account.getPhoneNumber() == 0) {
      this.Phone_Number_Display.setText("empty");
    } else {
      this.Phone_Number_Display.setText(String.valueOf(account.getPhoneNumber()));
    }

    this.Username_Display.setText(username);

    this.question1Display.setText(
        qdb.retrieveQuestion(account.getSecurityQuestion1()).getQuestion());
    this.question2Display.setText(
        qdb.retrieveQuestion(account.getSecurityQuestion2()).getQuestion());
    this.answer1Display.setText(account.getSecurityAnswer1());
    this.answer2Display.setText(account.getSecurityAnswer2());
  }

  public void EditPressed() {
    Navigation.navigate(Screen.EDIT_PROFILE);
  }
}
