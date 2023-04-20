package edu.wpi.cs3733.D23.teamQ.controllers;

import static java.lang.System.currentTimeMillis;

import edu.wpi.cs3733.D23.teamQ.db.Qdb;
import edu.wpi.cs3733.D23.teamQ.db.obj.Account;
import edu.wpi.cs3733.D23.teamQ.db.obj.Message;
import io.github.palexdev.materialfx.controls.MFXFilterComboBox;
import io.github.palexdev.materialfx.controls.MFXScrollPane;
import io.github.palexdev.materialfx.controls.MFXTextField;
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

    // Add new message listener for messages coming in

    // add listener for changing who the message is being sent to
    // peopleSelector.addEventHandler();
    // Set image
    // receiver = qdb.retrieveAccount(peopleSelector.getText());
    // if (receiver.isActive()) activeIndicator.setStyle("-fx-fill: #37AC2B");
    // else activeIndicator.setStyle("-fx-fill: #CE3C49");
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
      textFlow.setPadding(new Insets(5, 10, 5, 10));
      text.setStyle("-fx-font-family: Roboto");
      text.setFill(Color.BLACK);
      hbox.getChildren().add(text);
      messageVbox.getChildren().add(hbox);

      // qdb.addMessage(newMessage);
      messageField.clear();

      System.out.println(message);
    }
  }

  public void messageReceived(Message messageReceived) {
    String message = messageReceived.getMessage();

    HBox hbox = new HBox();
    hbox.setAlignment(Pos.CENTER_LEFT);
    hbox.setPadding(new Insets(5, 10, 5, 5));

    Text text = new Text(message);
    TextFlow textFlow = new TextFlow(text);
    textFlow.setStyle("-fx-color: #B4B4B4");
    textFlow.setStyle("-fx-background-radius: 20px");
    textFlow.setPadding(new Insets(5, 10, 5, 10));
    text.setStyle("-fx-font-family: Roboto");
    text.setFill(Color.BLACK);
    hbox.getChildren().add(textFlow);
    messageVbox.getChildren().add(hbox);
  }
}
