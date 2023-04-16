package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.db.Qdb;
import edu.wpi.cs3733.D23.teamQ.db.obj.Account;
import edu.wpi.cs3733.D23.teamQ.navigation.Navigation;
import edu.wpi.cs3733.D23.teamQ.navigation.Screen;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class EditProfileController {

  @FXML private MFXTextField ProfileEditPage_Email_TextField;

  @FXML private MFXTextField ProfileEditPage_FirstName_TextField;

  @FXML private MFXTextField ProfileEditPage_IDNumber_TextField;

  @FXML private MFXTextField ProfileEditPage_LastName_TextField;

  @FXML private MFXTextField ProfileEditPage_PhoneNumber_TextField;

  @FXML private MFXTextField ProfileEditPage_Title_TextField;

  @FXML private MFXTextField ProfileEditPage_Username_TextField;

  @FXML
  private void initialize() {
    String username = LoginController.getLoginUsername();

    String email = LoginController.getLoginEmail();

    Qdb qdb = Qdb.getInstance();
    this.ProfileEditPage_Title_TextField.setText(qdb.retrieveAccount(username).getTitle());
    this.ProfileEditPage_IDNumber_TextField.setText(
        String.valueOf(qdb.retrieveAccount(username).getIDNum()));
    this.ProfileEditPage_FirstName_TextField.setText(qdb.retrieveAccount(username).getFirstName());
    this.ProfileEditPage_LastName_TextField.setText(qdb.retrieveAccount(username).getLastName());
    this.ProfileEditPage_Email_TextField.setText(email);

    this.ProfileEditPage_PhoneNumber_TextField.setText(
        String.valueOf(qdb.retrieveAccount(username).getPhoneNumber()));

    this.ProfileEditPage_Username_TextField.setText(username);
  }

  @FXML
  public void DonePressed(ActionEvent event) {
    String username = LoginController.getLoginUsername();

    String email = LoginController.getLoginEmail();

    Qdb qdb = Qdb.getInstance();
    Account newAccount =
        new Account(
            username,
            qdb.retrieveAccount(username).getPassword(),
            qdb.retrieveAccount(username).getEmail(),
            qdb.retrieveAccount(username).getSecurityQuestion1(),
            qdb.retrieveAccount(username).getSecurityQuestion2(),
            qdb.retrieveAccount(username).getSecurityAnswer1(),
            qdb.retrieveAccount(username).getSecurityAnswer2(),
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
