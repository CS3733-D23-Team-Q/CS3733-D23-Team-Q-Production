package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.db.Qdb;
import edu.wpi.cs3733.D23.teamQ.db.obj.Account;
import edu.wpi.cs3733.D23.teamQ.db.obj.ProfileImage;
import edu.wpi.cs3733.D23.teamQ.db.obj.Question;
import edu.wpi.cs3733.D23.teamQ.navigation.Navigation;
import edu.wpi.cs3733.D23.teamQ.navigation.Screen;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXFilterComboBox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import java.io.File;
import java.sql.SQLException;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

public class AdminAddProfileController {
  Qdb qdb = Qdb.getInstance();

  @FXML private MFXTextField username;
  @FXML private MFXTextField firstName;
  @FXML private MFXTextField lastName;
  @FXML private MFXTextField email;
  @FXML private MFXTextField phone;
  @FXML private MFXTextField titleEdit;
  @FXML private MFXTextField securityAnswer1;
  @FXML private MFXTextField securityAnswer2;
  @FXML private MFXFilterComboBox securityQuestion1;
  @FXML private MFXFilterComboBox securityQuestion2;
  @FXML private ImageView profileImage;
  @FXML private MFXButton editPFP;
  @FXML private MFXTextField password;
  @FXML private MFXButton backButton;

  private ObservableList<String> getQuestions() {
    ObservableList<String> questions = FXCollections.observableArrayList();
    List<Question> allQuestions = qdb.retrieveAllQuestions();
    for (int i = 0; i < allQuestions.size() - 1; i++) {
      questions.add(allQuestions.get(i).getQuestion());
    }
    return questions;
  }

  @FXML
  private void initialize() throws SQLException {
    firstName.setText("");
    lastName.setText("");
    email.setText("");
    phone.setText("");
    titleEdit.setText("");
    username.setText("");
    password.setText("");
    securityAnswer1.setText("");
    securityAnswer2.setText("");
    securityQuestion1.setText(qdb.retrieveQuestion(1).getQuestion());
    securityQuestion2.setText(qdb.retrieveQuestion(2).getQuestion());
    securityQuestion1.setItems(getQuestions());
    securityQuestion2.setItems(getQuestions());

    Image image = new Image(getClass().getResourceAsStream("/EditButton.png"));
    ImageView imageView = new ImageView(image);
    imageView.setFitHeight(30.0);
    imageView.setFitWidth(30.0);
    editPFP.setText("");
    editPFP.setGraphic(imageView);

    //        if (qdb.getProfileImageIndex(username) != -1) {
    //          Image pfp =
    // qdb.convertByteaToImage(qdb.retrieveProfileImage(username).getImageData());
    //          profileImage.setImage(pfp);adin
    //        }
  }

  @FXML
  public void donePressed() {
    //    String username = LoginController.getLoginUsername();
    //    Account account = qdb.retrieveAccount(username);

    Account newAccount =
        new Account(
            username.getText(),
            password.getText(),
            email.getText(),
            qdb.getQuestionIndex(securityQuestion1.getText()) + 1,
            qdb.getQuestionIndex(securityQuestion2.getText()) + 1,
            securityAnswer1.getText(),
            securityAnswer2.getText(),
            false,
            firstName.getText(),
            lastName.getText(),
            titleEdit.getText(),
            phone.getText(),
            null,
            null);
    qdb.addAccount(newAccount);
    Navigation.navigate(Screen.ADMIN_DIRECTORY);
  }

  public void editPFPClicked() throws SQLException {
    String username = LoginController.getLoginUsername();
    FileChooser fileChooser = new FileChooser();
    File selectedFile = fileChooser.showOpenDialog(editPFP.getScene().getWindow());

    if (selectedFile != null) {
      Image image = new Image(selectedFile.toURI().toString());
      profileImage.setImage(image);

      ProfileImage newPFP = new ProfileImage(username, qdb.convertImageToBytea(image));

      if (qdb.getProfileImageIndex(username) == -1) {
        qdb.addProfileImage(newPFP);
      } else {
        qdb.updateProfileImage(username, newPFP);
      }
    }
  }

  @FXML
  void backButtonPressed(ActionEvent event) {
    Navigation.navigate(Screen.ADMIN_DIRECTORY);
  }
}
