package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.db.Qdb;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class EditProfileController {

  @FXML private MFXButton ProfileEditPage_Done_Button;

  @FXML private MFXTextField ProfileEditPage_Email_TextField;

  @FXML private MFXTextField ProfileEditPage_FirstName_TextField;

  @FXML private MFXButton ProfileEditPage_Home_Button;

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
    this.ProfileEditPage_Title_TextField.setText(qdb.retrievePerson(username).getTitle());
    this.ProfileEditPage_IDNumber_TextField.setText(
        String.valueOf(qdb.retrievePerson(username).getIDNum()));
    this.ProfileEditPage_FirstName_TextField.setText(qdb.retrievePerson(username).getFirstName());
    this.ProfileEditPage_LastName_TextField.setText(qdb.retrievePerson(username).getLastName());
    this.ProfileEditPage_Email_TextField.setText(email);
    if (qdb.retrievePerson(username).getPhoneNumber() != 0) {
      this.ProfileEditPage_PhoneNumber_TextField.setText(
          String.valueOf(qdb.retrievePerson(username).getPhoneNumber()));
    } else {
      this.ProfileEditPage_PhoneNumber_TextField.setText(String.valueOf(" "));
    }
    this.ProfileEditPage_Username_TextField.setText(username);
  }

  @FXML
  void DonePressed(ActionEvent event) {}
}
