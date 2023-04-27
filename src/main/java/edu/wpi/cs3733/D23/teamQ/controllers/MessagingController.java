package edu.wpi.cs3733.D23.teamQ.controllers;

import static java.lang.System.currentTimeMillis;

import edu.wpi.cs3733.D23.teamQ.db.Qdb;
import edu.wpi.cs3733.D23.teamQ.db.dao.Subscriber;
import edu.wpi.cs3733.D23.teamQ.db.obj.Account;
import edu.wpi.cs3733.D23.teamQ.db.obj.Message;
import io.github.palexdev.materialfx.controls.MFXFilterComboBox;
import io.github.palexdev.materialfx.controls.MFXScrollPane;
import io.github.palexdev.materialfx.controls.MFXTextField;
import java.io.File;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class MessagingController implements Subscriber {
  Account receiver;
  @FXML ImageView sendButton;
  @FXML VBox messageVbox;
  @FXML MFXTextField messageField;
  @FXML MFXScrollPane messageSP;
  @FXML MFXFilterComboBox peopleSelector;

  @FXML Label personLabel;

  @FXML ImageView profilePicture;
  @FXML Circle activeIndicator;
  @FXML ImageView composeButton;
  @FXML VBox accountVbox;
  @FXML MFXScrollPane accountSP;

  @FXML
  public void initialize() {
    Qdb qdb = Qdb.getInstance();
    qdb.subscribe(this);

    populateAccounts();

    peopleSelector.setValue("");
    peopleSelector.setItems(qdb.getAllNames());

    messageSP.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
    accountSP.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

    if (qdb.getMessagingAccount() != null) {
      receiver = qdb.getMessagingAccount();
      qdb.setMessagingAccount(null);
      peopleSelector.setText(
          receiver.getUsername()
              + ", ("
              + receiver.getFirstName()
              + " "
              + receiver.getLastName()
              + ", "
              + receiver.getTitle()
              + ")");
      populateMessages();
    }

    messageVbox
        .heightProperty()
        .addListener(
            new ChangeListener<Number>() {
              @Override
              public void changed(
                  ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                messageSP.setVvalue((double) newValue);
              }
            });

    accountVbox
        .heightProperty()
        .addListener(
            new ChangeListener<Number>() {
              @Override
              public void changed(
                  ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                accountSP.setVvalue((double) newValue);
              }
            });

    peopleSelector
        .valueProperty()
        .addListener(
            new ChangeListener<>() {
              @Override
              public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                String regex = "\\A[^,]*";
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(newValue.toString());
                String result;
                if (matcher.find()) {
                  result = matcher.group(0);
                  receiver = qdb.retrieveAccount(result);
                  populateMessages();
                }
              }
            });
  }

  @FXML
  public void keyPressed(KeyEvent e) {
    if (e.getCode().equals(KeyCode.ENTER)) {
      sendButtonClicked();
    }
  }

  @FXML
  public void composeButtonClicked() {

    personLabel.setVisible(false);
    profilePicture.setVisible(false);
    activeIndicator.setVisible(false);
    peopleSelector.setVisible(true);
  }

  @FXML
  public void sendButtonClicked() {
    Qdb qdb = Qdb.getInstance();
    String message = messageField.getText();
    if (!message.isEmpty() && (receiver != null)) {
      Message newMessage =
          new Message(
              qdb.retrieveAccount(LoginController.getUsername()),
              receiver,
              message,
              currentTimeMillis());

      //      if (qdb.retrieveMessages(LoginController.getUsername(),
      // receiver.getUsername()).isEmpty()
      //          || (System.currentTimeMillis()
      //                  - qdb.retrieveMessages(LoginController.getUsername(),
      // receiver.getUsername())
      //                      .get(
      //                          qdb.retrieveMessages(
      //                                      LoginController.getUsername(), receiver.getUsername())
      //                                  .size()
      //                              - 1)
      //                      .getTimeStamp()
      //              >= 3600000)) displayTime(System.currentTimeMillis());

      HBox hbox = new HBox();
      hbox.setAlignment(Pos.CENTER_RIGHT);
      hbox.setPadding(new Insets(4, 16, 4, 640));

      Text text = new Text(message);
      TextFlow textFlow = new TextFlow(text);
      textFlow.setStyle(
          "-fx-background-color: #0167B1; -fx-background-radius: 20px; -fx-padding: 12 20 12 20;");
      text.setStyle("-fx-font-family: Roboto");
      text.setFill(Color.WHITE);
      text.setFont(Font.font(14));
      hbox.getChildren().add(textFlow);
      messageVbox.getChildren().add(hbox);

      qdb.addMessage(newMessage);

      messageField.clear();
    }
  }

  public void populateSent(Message messageSent) {

    Qdb qdb = Qdb.getInstance();

    String message = messageSent.getMessage();

    //    if (qdb.retrieveMessages(LoginController.getUsername(), receiver.getUsername()).isEmpty()
    //        || (System.currentTimeMillis()
    //                - qdb.retrieveMessages(LoginController.getUsername(), receiver.getUsername())
    //                    .get(
    //                        qdb.retrieveMessages(LoginController.getUsername(),
    // receiver.getUsername())
    //                                .size()
    //                            - 1)
    //                    .getTimeStamp()
    //            >= 3600000)) displayTime(System.currentTimeMillis());

    HBox hbox = new HBox();
    hbox.setAlignment(Pos.CENTER_RIGHT);
    hbox.setPadding(new Insets(4, 16, 4, 640));

    Text text = new Text(message);
    TextFlow textFlow = new TextFlow(text);
    textFlow.setStyle(
        "-fx-background-color: #0167B1; -fx-background-radius: 20px; -fx-padding: 12 20 12 20;");
    text.setStyle("-fx-font-family: Roboto");
    text.setFill(Color.WHITE);
    text.setFont(Font.font(14));
    hbox.getChildren().add(textFlow);
    messageVbox.getChildren().add(hbox);
  }

  public void populateReceived(Message messageReceived) {

    Qdb qdb = Qdb.getInstance();
    messageReceived.setRead(true);
    String message = messageReceived.getMessage();

    //    if (qdb.retrieveMessages(LoginController.getUsername(), receiver.getUsername()).isEmpty()
    //        || (System.currentTimeMillis()
    //                - qdb.retrieveMessages(LoginController.getUsername(), receiver.getUsername())
    //                    .get(
    //                        qdb.retrieveMessages(LoginController.getUsername(),
    // receiver.getUsername())
    //                                .size()
    //                            - 1)
    //                    .getTimeStamp()
    //            >= 3600000)) displayTime(System.currentTimeMillis());

    HBox hbox = new HBox();
    hbox.setAlignment(Pos.CENTER_LEFT);
    hbox.setPadding(new Insets(4, 640, 4, 16));

    Text text = new Text(message);
    TextFlow textFlow = new TextFlow(text);
    textFlow.setStyle(
        "-fx-background-color: #B4B4B4; -fx-background-radius: 20px; -fx-padding: 12 20 12 20;");
    text.setStyle("-fx-font-family: Roboto");
    text.setFill(Color.BLACK);
    text.setFont(Font.font(14));
    hbox.getChildren().add(textFlow);
    messageVbox.getChildren().add(hbox);
  }

  public void displayTime(long time) {

    SimpleDateFormat sdf = new SimpleDateFormat("MMM dd yyyy hh:mm a");
    Date resultDate = new Date(time);
    String timeString = sdf.format(resultDate);

    HBox hbox = new HBox();
    hbox.setAlignment(Pos.CENTER);

    Text text = new Text(timeString);
    TextFlow textFlow = new TextFlow(text);
    text.setStyle("-fx-font-family: Roboto; -fx-font-weight: bold;");
    text.setFill(Color.BLACK);
    text.setFont(Font.font(14));
    hbox.getChildren().add(textFlow);
    messageVbox.getChildren().add(hbox);
  }

  public void populateMessages() {
    messageVbox.getChildren().clear();
    Qdb qdb = Qdb.getInstance();
    peopleSelector.setVisible(false);
    personLabel.setText(receiver.getFirstName() + " " + receiver.getLastName());
    personLabel.setVisible(true);
    profilePicture.setVisible(true);
    activeIndicator.setVisible(true);

    if (!messageVbox.getChildren().isEmpty()) {
      messageVbox.getChildren().clear();
    }
    if (receiver.isActive()) activeIndicator.setStyle("-fx-fill: #37AC2B");
    else activeIndicator.setStyle("-fx-fill: #CE3C49");

    if (qdb.getProfileImageIndex(receiver.getUsername()) != -1) {
      Image pfp =
          qdb.convertByteaToImage(qdb.retrieveProfileImage(receiver.getUsername()).getImageData());
      profilePicture.setImage(pfp);
      profilePicture.setFitHeight(120);
      profilePicture.setFitHeight(120);
      Circle ppClip = new Circle(30);
      ppClip.setTranslateX(profilePicture.getFitWidth() / 2);
      ppClip.setTranslateY(profilePicture.getFitHeight() / 2);
      profilePicture.setClip(ppClip);
    }

    if (!qdb.retrieveMessages(LoginController.getUsername(), receiver.getUsername()).isEmpty())
      displayTime(
          qdb.retrieveMessages(LoginController.getUsername(), receiver.getUsername())
              .get(0)
              .getTimeStamp());
    for (Message m : qdb.retrieveMessages(LoginController.getUsername(), receiver.getUsername())) {
      if (m.getSender().getUsername().equals(LoginController.getUsername())) populateSent(m);
      else populateReceived(m);
    }
  }

  public boolean update(List<String> context) {
    System.out.println("Updating messages page");

    if (context.contains("message")) {
      populateMessages();
      populateAccounts();
//      String path = getClass().getResource("/alert.wav").getPath();
//      Media media = new Media(new File(path).toURI().toString());
//      MediaPlayer mediaPlayer = new MediaPlayer(media);
//      mediaPlayer.play();
      return true;
    } else {
      return false;
    }
  }

  public void populateAccounts() {
    accountVbox.getChildren().clear();
    Qdb qdb = Qdb.getInstance();
    for (Message m : qdb.retrieveRecentMessages(LoginController.getUsername())) {
      String person;
      String message = m.getMessage();
      String username;
      ImageView profileImage = new ImageView();

      if (message.length() > 40) message = message.substring(0, 40) + "...";

      if (m.getSender().getUsername().equals(LoginController.getUsername())) {
        person = m.getReceiver().getFirstName() + " " + m.getReceiver().getLastName();
        username = m.getReceiver().getUsername();
      } else {
        person = m.getSender().getFirstName() + " " + m.getSender().getLastName();
        username = m.getSender().getUsername();
      }

      if (qdb.getProfileImageIndex(username) != -1) {
        Image pfp = qdb.convertByteaToImage(qdb.retrieveProfileImage(username).getImageData());
        profileImage.setImage(pfp);
        profileImage.setFitHeight(40);
        profileImage.setFitHeight(40);
        Circle ppClip = new Circle(15);
        ppClip.setTranslateX(profileImage.getFitWidth() / 2);
        ppClip.setTranslateY(profileImage.getFitHeight() / 2);
        profileImage.setClip(ppClip);
      }

      HBox hbox = new HBox();
      hbox.setMaxWidth(300);
      VBox vbox = new VBox();
      vbox.setMaxWidth(200);
      HBox hbox1 = new HBox();
      hbox1.setMaxWidth(200);
      HBox hbox2 = new HBox();
      hbox2.setMaxWidth(200);
      hbox.setAlignment(Pos.CENTER_LEFT);
      hbox.setPadding(new Insets(4, 16, 4, 16));
      Text ptext = new Text(person);
      Text mtext = new Text(message);
      ptext.setFont(Font.font(18));
      mtext.setFont(Font.font(12));
      ptext.setStyle("-fx-font-family: Roboto; -fx-font-weight: bold;");
      mtext.setStyle("-fx-font-family: Roboto; ");
      hbox1.getChildren().add(ptext);
      hbox2.getChildren().add(mtext);
      vbox.getChildren().add(hbox1);
      vbox.getChildren().add(hbox2);
      Line line = new Line(0, 0, 300, 0);
      line.setFill(Color.GRAY);
      line.setStrokeWidth(0.5);
      // hbox.getChildren().add(profileImage);
      hbox.getChildren().add(vbox);
      accountVbox.getChildren().add(line);
      accountVbox.getChildren().add(hbox);
      hbox.setUserData(m);

      hbox.setOnMouseClicked(
          event -> {
            receiver = qdb.getAccountFromUsername(username);
            populateMessages();
          });
    }
  }
}
