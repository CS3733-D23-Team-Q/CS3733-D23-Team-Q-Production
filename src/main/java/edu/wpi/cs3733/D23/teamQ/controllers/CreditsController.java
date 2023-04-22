package edu.wpi.cs3733.D23.teamQ.controllers;

import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;

public class CreditsController {
  @FXML Label linkDisplay;
  @FXML MFXButton copy;
  @FXML MFXButton clear;
  @FXML MFXButton gesturefx;
  @FXML MFXButton materialfx;
  @FXML MFXButton figma;
  @FXML MFXButton github;
  @FXML MFXButton intellij;
  @FXML MFXButton jira;
  @FXML MFXButton postgresql;
  @FXML MFXButton sbuilder;
  @FXML MFXButton slack;
  final Clipboard cb = Clipboard.getSystemClipboard();
  final ClipboardContent content = new ClipboardContent();

  @FXML
  public void initialize() {}

  @FXML
  public void clearClicked() {
    linkDisplay.setText("Click an item to display the associated link:");
    cb.clear();
  }

  @FXML
  public void copyClicked() {
    content.putString(linkDisplay.getText());
    cb.setContent(content);
  }

  @FXML
  public void gesturefxClicked() {
    linkDisplay.setText("https://github.com/tom91136/GestureFX");
  }

  @FXML
  public void materialfxClicked() {
    linkDisplay.setText("https://github.com/palexdev/MaterialFX");
  }

  @FXML
  public void figmaClicked() {
    linkDisplay.setText("https://www.figma.com/");
  }

  @FXML
  public void githubClicked() {
    linkDisplay.setText("https://github.com/");
  }

  @FXML
  public void intellijClicked() {
    linkDisplay.setText("https://www.jetbrains.com/idea/");
  }

  @FXML
  public void jiraClicked() {
    linkDisplay.setText("https://www.atlassian.com/software/jira");
  }

  @FXML
  public void postgresqlClicked() {
    linkDisplay.setText("https://www.postgresql.org/");
  }

  @FXML
  public void sbuilderClicked() {
    linkDisplay.setText("https://gluonhq.com/products/scene-builder/");
  }

  @FXML
  public void slackClicked() {
    linkDisplay.setText("https://slack.com/");
  }
}
