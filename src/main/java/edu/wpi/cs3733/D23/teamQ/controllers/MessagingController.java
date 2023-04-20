package edu.wpi.cs3733.D23.teamQ.controllers;

import io.github.palexdev.materialfx.controls.MFXScrollPane;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

import javax.swing.text.html.ImageView;

public class MessagingController {

@FXML ImageView sendButton;
@FXML ImageView composeButton;
@FXML VBox messageVbox;
@FXML VBox peopleVbox;
@FXML MFXTextField messageField;
@FXML MFXScrollPane messageSP;

@FXML MFXScrollPane peopleSP;

@FXML public void initialize(){

    messageVbox.heightProperty().addListener(new ChangeListener<Number>() {
        @Override
        public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
            messageSP.setVvalue((double) newValue);
        }
    });

}

@FXML public void sendButtonClicked(){String message = messageField.getText();}

@FXML public void composeButtonClicked(){}



}
