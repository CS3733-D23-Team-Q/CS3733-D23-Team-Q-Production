package edu.wpi.cs3733.D23.teamQ.controllers;

import static java.lang.System.currentTimeMillis;

import edu.wpi.cs3733.D23.teamQ.db.Qdb;
import edu.wpi.cs3733.D23.teamQ.db.obj.Account;
import edu.wpi.cs3733.D23.teamQ.db.obj.Message;
import io.github.palexdev.materialfx.controls.MFXFilterComboBox;
import io.github.palexdev.materialfx.controls.MFXScrollPane;
import io.github.palexdev.materialfx.controls.MFXTextField;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class MessagingController {
  Account receiver;
  @FXML ImageView sendButton;
  @FXML VBox messageVbox;
  @FXML MFXTextField messageField;
  @FXML MFXScrollPane messageSP;
  @FXML MFXFilterComboBox peopleSelector;

  @FXML ImageView profilePicture;
  @FXML Circle activeIndicator;

  SimpleBooleanProperty sbp = new SimpleBooleanProperty(false);

  @FXML
  public void initialize() {
    Qdb qdb = Qdb.getInstance();

    peopleSelector.setValue("");
    peopleSelector.setItems(qdb.getAllNames());

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

    Bindings.when(sbp)
        .then(
            new ChangeListener<ObservableList<Message>>() {
              @Override
              public void changed(
                  ObservableValue<? extends ObservableList<Message>> observable,
                  ObservableList<Message> oldValue,
                  ObservableList<Message> newValue) {
                // Your code here to handle the list change event
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
                  if (!messageVbox.getChildren().isEmpty()) {
                    messageVbox.getChildren().clear();
                  }
                  result = matcher.group(0);
                  receiver = qdb.retrieveAccount(result);
                  if (receiver.isActive()) activeIndicator.setStyle("-fx-fill: #37AC2B");
                  else activeIndicator.setStyle("-fx-fill: #CE3C49");

                  if (qdb.getProfileImageIndex(receiver.getUsername()) != -1) {
                    Image pfp =
                        qdb.convertByteaToImage(
                            qdb.retrieveProfileImage(receiver.getUsername()).getImageData());
                    profilePicture.setImage(pfp);
                  }

                  for (Message m :
                      qdb.retrieveMessages(LoginController.getUsername(), receiver.getUsername())) {
                    if (m.getSender().getUsername().equals(LoginController.getUsername()))
                      sentHistorically(m);
                    else messageReceived(m);
                  }
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

      if (qdb.retrieveMessages(LoginController.getUsername(), receiver.getUsername()).isEmpty()
          || (System.currentTimeMillis()
                  - qdb.retrieveMessages(LoginController.getUsername(), receiver.getUsername())
                      .get(
                          qdb.retrieveMessages(
                                      LoginController.getUsername(), receiver.getUsername())
                                  .size()
                              - 1)
                      .getTimeStamp()
              >= 3600000)) displayTime(System.currentTimeMillis());

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

  public void sentHistorically(Message messageSent) {

    Qdb qdb = Qdb.getInstance();

    String message = messageSent.getMessage();

    if (qdb.retrieveMessages(LoginController.getUsername(), receiver.getUsername()).isEmpty()
        || (System.currentTimeMillis()
                - qdb.retrieveMessages(LoginController.getUsername(), receiver.getUsername())
                    .get(
                        qdb.retrieveMessages(LoginController.getUsername(), receiver.getUsername())
                                .size()
                            - 1)
                    .getTimeStamp()
            >= 3600000)) displayTime(System.currentTimeMillis());

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

  public void messageReceived(Message messageReceived) {

    Qdb qdb = Qdb.getInstance();

    String message = messageReceived.getMessage();

    if (qdb.retrieveMessages(LoginController.getUsername(), receiver.getUsername()).isEmpty()
        || (System.currentTimeMillis()
                - qdb.retrieveMessages(LoginController.getUsername(), receiver.getUsername())
                    .get(
                        qdb.retrieveMessages(LoginController.getUsername(), receiver.getUsername())
                                .size()
                            - 1)
                    .getTimeStamp()
            >= 3600000)) displayTime(System.currentTimeMillis());

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
}
