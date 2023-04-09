package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.db.Qdb;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import java.io.File;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

public class EditProfileController {
  Qdb qdb = Qdb.getInstance();
  @FXML private MFXButton ProfilePage_ChangePic_Button;

  @FXML private MFXButton ProfilePage_Done_Button;
  @FXML private MFXButton ProfileEditPage_Done_Button;

  @FXML private MFXTextField ProfilePage_Email_TextField;

  @FXML private MFXTextField ProfilePage_FirstName_TextField;

  @FXML private MFXTextField ProfilePage_IDNumber_TextField;

  @FXML private MFXTextField ProfilePage_LastName_TextField;

  @FXML private MFXTextField ProfilePage_PhoneNumber_TextField;

  @FXML private MFXTextField ProfilePage_Title_TextField;

  @FXML private MFXTextField ProfilePage_Username_TextField;

//  @FXML private ImageView ProfilePage_Profile_Image;

//  private FileChooser fileChooser;
//  private File filePath;

  @FXML
  private void initialize() {
    String username = LoginController.getLoginUsername();

    String email = LoginController.getLoginEmail();

    Qdb qdb = Qdb.getInstance();
    this.ProfilePage_Title_TextField.setText(qdb.retrievePerson(username).getTitle());
    this.ProfilePage_IDNumber_TextField.setText(
        String.valueOf(qdb.retrievePerson(username).getIDNum()));
    this.ProfilePage_FirstName_TextField.setText(qdb.retrievePerson(username).getFirstName());
    this.ProfilePage_LastName_TextField.setText(qdb.retrievePerson(username).getLastName());
    this.ProfilePage_Email_TextField.setText(email);
    this.ProfilePage_PhoneNumber_TextField.setText(
        String.valueOf(qdb.retrievePerson(username).getPhoneNumber()));
    this.ProfilePage_Username_TextField.setText(username);
  }

  //  @FXML
  //  public void DonePressed() {
  //    String username = LoginController.getLoginUsername();
  //    String email = LoginController.getLoginEmail();
  //    Qdb qdb = Qdb.getInstance();
  //    boolean title = false;
  //    boolean idNumber = false;
  //    boolean firstName = false;
  //    boolean lastName = false;
  //    boolean userEmail = false; //CHANGE IN USERNAME AS WELL
  //    boolean phoneNumber = false;
  //    boolean userName = false;
  //
  //    if
  // (!(ProfilePage_Title_TextField.getText().equals(qdb.retrievePerson(username).getTitle())))
  //    {
  //      title = true;
  //    }
  //
  //    if
  // (!(ProfilePage_IDNumber_TextField.getText().equals(qdb.retrievePerson(username).getIDNum())))
  //    {
  //      idNumber = true;
  //    }
  //
  //    if
  // (!(ProfilePage_FirstName_TextField.getText().equals(qdb.retrievePerson(username).getFirstName())))
  //    {
  //      firstName = true;
  //    }
  //
  //    if
  // (!(ProfilePage_LastName_TextField.getText().equals(qdb.retrievePerson(username).getLastName())))
  //    {
  //
  //    }
  //
  //    if (!(ProfilePage_Email_TextField.getText().equals(email)))
  //    {
  //
  //    }
  //
  //    if
  // (!(ProfilePage_PhoneNumber_TextField.getText().equals(qdb.retrievePerson(username).getPhoneNumber())))
  //    {
  //
  //    }
  //
  //    if (!(ProfilePage_Username_TextField.getText().equals(username)))
  //    {
  //
  //    }
  //
  //    Navigation.navigate(Screen.PROFILE_PAGE);
  //  }

  // FUNCTION TO EDIT IMAGE FOR THE PROFILE

  /*   public void ChangeProfileImage(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        fileChooser = new FileChooser();
        fileChooser.setTitle("Open image");

        String userDirectoryString = System.getProperty("user.home") + "\\Pictures";
        File userDirectory = new File(userDirectoryString);

        if (!userDirectory.canRead()) userDirectory = new File("c:/");

        fileChooser.setInitialDirectory(userDirectory);
        this.filePath = fileChooser.showOpenDialog(stage);

        try {
            BufferedImage bufferedImage = ImageIO.read(filePath);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            ProfilePage_Profile_Image.setImage(image);
            //        photo.setImage(ProfilePage_Profile_Image.getImage());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
  */
}
