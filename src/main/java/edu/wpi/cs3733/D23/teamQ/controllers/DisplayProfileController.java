package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.db.Qdb;
import edu.wpi.cs3733.D23.teamQ.db.obj.Account;
import edu.wpi.cs3733.D23.teamQ.navigation.Navigation;
import edu.wpi.cs3733.D23.teamQ.navigation.Screen;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class DisplayProfileController {

  DirectoryController directoryController = new DirectoryController();

  @FXML private MFXTextField ProfileEditPage_Email_TextField;

  @FXML private MFXTextField ProfileEditPage_FirstName_TextField;

  @FXML private MFXTextField ProfileEditPage_IDNumber_TextField;

  @FXML private MFXTextField ProfileEditPage_LastName_TextField;

  @FXML private MFXTextField ProfileEditPage_PhoneNumber_TextField;

  @FXML private MFXTextField ProfileEditPage_Title_TextField;

  @FXML private MFXTextField ProfileEditPage_Username_TextField;

  @FXML private Button ProfileEditPage_Done_Button;

  @FXML
  private void initialize() {
    String username = LoginController.getLoginUsername();

    Qdb qdb = Qdb.getInstance();

    Account user = qdb.retrieveAccount(username);

    this.ProfileEditPage_Title_TextField.setText(user.getTitle());
    this.ProfileEditPage_IDNumber_TextField.setText(String.valueOf(user.getIDNum()));

    this.ProfileEditPage_FirstName_TextField.setText(user.getFirstName());

    this.ProfileEditPage_LastName_TextField.setText(user.getLastName());
    this.ProfileEditPage_Email_TextField.setText(user.getEmail());

    this.ProfileEditPage_PhoneNumber_TextField.setText(String.valueOf(user.getPhoneNumber()));

    this.ProfileEditPage_Username_TextField.setText(username);
  }

  @FXML
  public void DonePressed(ActionEvent event) {
    Navigation.navigate(Screen.DIRECTORY);
  }
}
