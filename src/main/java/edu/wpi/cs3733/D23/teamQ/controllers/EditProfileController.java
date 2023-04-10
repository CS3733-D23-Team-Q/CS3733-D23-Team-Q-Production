package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.db.Qdb;
import edu.wpi.cs3733.D23.teamQ.db.obj.Person;
import edu.wpi.cs3733.D23.teamQ.navigation.Navigation;
import edu.wpi.cs3733.D23.teamQ.navigation.Screen;
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

    this.ProfileEditPage_PhoneNumber_TextField.setText(
        String.valueOf(qdb.retrievePerson(username).getPhoneNumber()));

    if (qdb.retrievePerson(username).getPhoneNumber() != 0) {
      this.ProfileEditPage_PhoneNumber_TextField.setText(
          String.valueOf(qdb.retrievePerson(username).getPhoneNumber()));
    } else {
      this.ProfileEditPage_PhoneNumber_TextField.setText(String.valueOf(" "));
    }

    this.ProfileEditPage_Username_TextField.setText(username);
    this.ProfileEditPage_Home_Button.setOnMouseClicked(event -> Navigation.navigate(Screen.HOME));
  }

  @FXML
  public void DonePressed(ActionEvent event) {
    String username = LoginController.getLoginUsername();

    String email = LoginController.getLoginEmail();

    Qdb qdb = Qdb.getInstance();
    Person newProfile =
        new Person(
            qdb.retrievePerson(username).getIDNum(),
            ProfileEditPage_FirstName_TextField.getText(),
            ProfileEditPage_LastName_TextField.getText(),
            ProfileEditPage_Title_TextField.getText(),
            Integer.parseInt(ProfileEditPage_PhoneNumber_TextField.getText()),
            username);

    qdb.updatePerson(qdb.retrievePerson(username).getIDNum(), newProfile);
    Navigation.navigate(Screen.PROFILE_PAGE);
  }
}
