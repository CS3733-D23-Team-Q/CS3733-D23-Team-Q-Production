package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.navigation.Navigation;
import edu.wpi.cs3733.D23.teamQ.navigation.Screen;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;

public class SignageController implements IController {
  @FXML GridPane signageRoot;

  @FXML
  public void initialize() {
    Platform.runLater(
        () -> {
          this.signageRoot
              .getScene()
              .addEventHandler(
                  KeyEvent.KEY_TYPED,
                  new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent event) {
                      if (event.getCharacter().equals("\u001b")) {
                        signageRoot.getScene().removeEventHandler(KeyEvent.KEY_TYPED, this);
                        Navigation.navigate(Screen.HOME);
                      }
                    }
                  });
        });
  }
}
