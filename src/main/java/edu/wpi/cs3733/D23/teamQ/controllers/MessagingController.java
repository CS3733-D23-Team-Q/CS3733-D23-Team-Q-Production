package edu.wpi.cs3733.D23.teamQ.controllers;

import static java.lang.System.currentTimeMillis;

import edu.wpi.cs3733.D23.teamQ.db.Qdb;
import edu.wpi.cs3733.D23.teamQ.db.obj.Account;
import edu.wpi.cs3733.D23.teamQ.db.obj.Message;
import io.github.palexdev.materialfx.controls.MFXFilterComboBox;
import io.github.palexdev.materialfx.controls.MFXScrollPane;
import io.github.palexdev.materialfx.controls.MFXTextField;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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

  String hello = "The dog said moo";

  @FXML
  public void initialize() {
    Qdb qdb = Qdb.getInstance();

    //qdb.getMessages(Account a, Account b)


    //NEED METHOD TO PULL THE DATABASE TO DISPLAY MESSAGE HISTORY
    //Take a list of messages in order of send date, and if sender = me call sentHistorically()
    //Sender != me, call messageReceived



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

    // Add new message listener for messages coming in

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
                  if (receiver.isActive()) activeIndicator.setStyle("-fx-fill: #37AC2B");
                  else activeIndicator.setStyle("-fx-fill: #CE3C49");

                  // Image image = qdb.getProfilePicture;

                  // profilePicture = new ImageView(image);
                }
              }
            });
  }

  @FXML
  public void sendButtonClicked() {
    Qdb qdb = Qdb.getInstance();
    String message = messageField.getText();
    if (!message.isEmpty()) {
      Message newMessage =
          new Message(
              qdb.retrieveAccount(LoginController.getUsername()),
              receiver,
              message,
              currentTimeMillis());


      HBox hbox = new HBox();
      hbox.setAlignment(Pos.CENTER_RIGHT);
      hbox.setPadding(new Insets(5, 5, 5, 10));

      Text text = new Text(message);
      TextFlow textFlow = new TextFlow(text);
      textFlow.setStyle("-fx-background-color: #0167B1");
      textFlow.setStyle("-fx-background-radius: 20px");
      textFlow.setPadding(new Insets(5, 10, 5, 100));
      text.setStyle("-fx-font-family: Roboto");
      text.setFill(Color.BLACK);
      text.setFont(Font.font(18));
      hbox.getChildren().add(textFlow);
      messageVbox.getChildren().add(hbox);

      // qdb.addMessage(newMessage);
      messageField.clear();
    }
  }

  public void sentHistorically(Message messageSent){
    String message = messageSent.getMessage();

    HBox hbox = new HBox();
    hbox.setAlignment(Pos.CENTER_RIGHT);
    hbox.setPadding(new Insets(5, 5, 5, 10));

    Text text = new Text(message);
    TextFlow textFlow = new TextFlow(text);
    textFlow.setStyle("-fx-background-color: #0167B1");
    textFlow.setStyle("-fx-background-radius: 20px");
    textFlow.setPadding(new Insets(5, 10, 5, 100));
    text.setStyle("-fx-font-family: Roboto");
    text.setFill(Color.BLACK);
    text.setFont(Font.font(18));
    hbox.getChildren().add(textFlow);
    messageVbox.getChildren().add(hbox);
  }

  public void messageReceived(Message messageReceived) {
    String message = messageReceived.getMessage();

    HBox hbox = new HBox();
    hbox.setAlignment(Pos.CENTER_LEFT);
    hbox.setPadding(new Insets(5, 10, 5, 5));

    Text text = new Text(message);
    TextFlow textFlow = new TextFlow(text);
    textFlow.setStyle("-fx-background-color: #B4B4B4");
    textFlow.setStyle("-fx-background-radius: 20px");
    textFlow.setPadding(new Insets(5, 100, 5, 10));
    text.setStyle("-fx-font-family: Roboto");
    text.setFill(Color.BLACK);
    text.setFont(Font.font(18));
    hbox.getChildren().add(textFlow);
    messageVbox.getChildren().add(hbox);
  }
}
