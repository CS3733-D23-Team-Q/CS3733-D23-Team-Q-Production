package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.db.Qdb;
import edu.wpi.cs3733.D23.teamQ.db.obj.Person;
import edu.wpi.cs3733.D23.teamQ.navigation.Navigation;
import edu.wpi.cs3733.D23.teamQ.navigation.Screen;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class DisplayProfileController {

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
//    this.ProfileEditPage_Title_TextField.setText(qdb.getDirectory().getTitle());
//    this.ProfileEditPage_IDNumber_TextField.setText(
//        String.valueOf(qdb.retrievePerson(username).getIDNum()));
//    this.ProfileEditPage_FirstName_TextField.setText(qdb.retrievePerson(username).getFirstName());
//    this.ProfileEditPage_LastName_TextField.setText(qdb.retrievePerson(username).getLastName());
//    this.ProfileEditPage_Email_TextField.setText(email);
//
//    this.ProfileEditPage_PhoneNumber_TextField.setText(
//        String.valueOf(qdb.retrievePerson(username).getPhoneNumber()));
//
//    this.ProfileEditPage_Username_TextField.setText(username);
  }

  @FXML
  public void DonePressed(ActionEvent event) {
    Navigation.navigate(Screen.DIRECTORY);
  }
}
