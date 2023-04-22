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

  // libraries
  @FXML MFXButton gesturefx;
  @FXML MFXButton materialfx;

  // icons
  @FXML MFXButton alert;
  @FXML MFXButton alertBox;
  @FXML MFXButton blueLogo;
  @FXML MFXButton chevronLeft;
  @FXML MFXButton chevronRight;
  @FXML MFXButton close;
  @FXML MFXButton confirm;
  @FXML MFXButton down;
  @FXML MFXButton edge;
  @FXML MFXButton exit;
  @FXML MFXButton exitBlue;
  @FXML MFXButton flower;
  @FXML MFXButton handshake;
  @FXML MFXButton home;
  @FXML MFXButton homeBlue;
  @FXML MFXButton leftArrow;
  @FXML MFXButton loc;
  @FXML MFXButton loginExit;
  @FXML MFXButton logo;
  @FXML MFXButton map;
  @FXML MFXButton map2;
  @FXML MFXButton mapBlue;
  @FXML MFXButton mapEditor;
  @FXML MFXButton menu;
  @FXML MFXButton move;
  @FXML MFXButton node;
  @FXML MFXButton people;
  @FXML MFXButton peopleBlue;
  @FXML MFXButton profile;
  @FXML MFXButton rightArrow;
  @FXML MFXButton sr;
  @FXML MFXButton srBlue;
  @FXML MFXButton settings;
  @FXML MFXButton settingsBlue;
  @FXML MFXButton stat;
  @FXML MFXButton statBlue;
  @FXML MFXButton up;

  // images
  @FXML MFXButton firstFloor;
  @FXML MFXButton groundFloor;
  @FXML MFXButton exterior;
  @FXML MFXButton imagemark;
  @FXML MFXButton lowerLevel1;
  @FXML MFXButton lowerLevel2;
  @FXML MFXButton secondFloor;
  @FXML MFXButton shapiro;
  @FXML MFXButton textLogo;
  @FXML MFXButton thirdFloor;

  // software
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

  // libraries
  @FXML
  public void gesturefxClicked() {
    linkDisplay.setText("https://github.com/tom91136/GestureFX");
  }

  @FXML
  public void materialfxClicked() {
    linkDisplay.setText("https://github.com/palexdev/MaterialFX");
  }

  // icons
  @FXML
  public void alertClicked() {
    linkDisplay.setText("https://github.com/palexdev/MaterialFX");
  }

  @FXML
  public void alertBoxClicked() {
    linkDisplay.setText("https://github.com/palexdev/MaterialFX");
  }

  @FXML
  public void blueLogoClicked() {
    linkDisplay.setText("https://github.com/palexdev/MaterialFX");
  }

  @FXML
  public void chevronLeftClicked() {
    linkDisplay.setText("https://github.com/palexdev/MaterialFX");
  }

  @FXML
  public void chevronRightClicked() {
    linkDisplay.setText("https://github.com/palexdev/MaterialFX");
  }

  @FXML
  public void closeClicked() {
    linkDisplay.setText("https://github.com/palexdev/MaterialFX");
  }

  @FXML
  public void confirmClicked() {
    linkDisplay.setText("https://github.com/palexdev/MaterialFX");
  }

  @FXML
  public void downClicked() {
    linkDisplay.setText("https://github.com/palexdev/MaterialFX");
  }

  @FXML
  public void edgeClicked() {
    linkDisplay.setText("https://github.com/palexdev/MaterialFX");
  }

  @FXML
  public void exitClicked() {
    linkDisplay.setText("https://github.com/palexdev/MaterialFX");
  }

  @FXML
  public void exitBlueClicked() {
    linkDisplay.setText("https://github.com/palexdev/MaterialFX");
  }

  @FXML
  public void flowerClicked() {
    linkDisplay.setText("https://github.com/palexdev/MaterialFX");
  }

  @FXML
  public void handshakeClicked() {
    linkDisplay.setText("https://github.com/palexdev/MaterialFX");
  }

  @FXML
  public void homeClicked() {
    linkDisplay.setText("https://github.com/palexdev/MaterialFX");
  }

  @FXML
  public void homeBlueClicked() {
    linkDisplay.setText("https://github.com/palexdev/MaterialFX");
  }

  @FXML
  public void leftArrowClicked() {
    linkDisplay.setText("https://github.com/palexdev/MaterialFX");
  }

  @FXML
  public void locClicked() {
    linkDisplay.setText("https://github.com/palexdev/MaterialFX");
  }

  @FXML
  public void loginExitClicked() {
    linkDisplay.setText("https://github.com/palexdev/MaterialFX");
  }

  @FXML
  public void logoClicked() {
    linkDisplay.setText("https://github.com/palexdev/MaterialFX");
  }

  @FXML
  public void mapClicked() {
    linkDisplay.setText("https://github.com/palexdev/MaterialFX");
  }

  @FXML
  public void map2Clicked() {
    linkDisplay.setText("https://github.com/palexdev/MaterialFX");
  }

  @FXML
  public void mapBlueClicked() {
    linkDisplay.setText("https://github.com/palexdev/MaterialFX");
  }

  @FXML
  public void mapEditorClicked() {
    linkDisplay.setText("https://github.com/palexdev/MaterialFX");
  }

  @FXML
  public void menuClicked() {
    linkDisplay.setText("https://github.com/palexdev/MaterialFX");
  }

  @FXML
  public void moveClicked() {
    linkDisplay.setText("https://github.com/palexdev/MaterialFX");
  }

  @FXML
  public void nodeClicked() {
    linkDisplay.setText("https://github.com/palexdev/MaterialFX");
  }

  @FXML
  public void peopleClicked() {
    linkDisplay.setText("https://github.com/palexdev/MaterialFX");
  }

  @FXML
  public void peopleBlueClicked() {
    linkDisplay.setText("https://github.com/palexdev/MaterialFX");
  }

  @FXML
  public void profileClicked() {
    linkDisplay.setText("https://github.com/palexdev/MaterialFX");
  }

  @FXML
  public void rightArrowClicked() {
    linkDisplay.setText("https://github.com/palexdev/MaterialFX");
  }

  @FXML
  public void srClicked() {
    linkDisplay.setText("https://github.com/palexdev/MaterialFX");
  }

  @FXML
  public void srBlueClicked() {
    linkDisplay.setText("https://github.com/palexdev/MaterialFX");
  }

  @FXML
  public void settingsClicked() {
    linkDisplay.setText("https://github.com/palexdev/MaterialFX");
  }

  @FXML
  public void settingsBlueClicked() {
    linkDisplay.setText("https://github.com/palexdev/MaterialFX");
  }

  @FXML
  public void statClicked() {
    linkDisplay.setText("https://github.com/palexdev/MaterialFX");
  }

  @FXML
  public void statBlueClicked() {
    linkDisplay.setText("https://github.com/palexdev/MaterialFX");
  }

  @FXML
  public void upClicked() {
    linkDisplay.setText("https://github.com/palexdev/MaterialFX");
  }

  // images
  @FXML
  public void firstFloorClicked() {
    linkDisplay.setText("BWH First Floor");
  }

  @FXML
  public void groundFloorClicked() {
    linkDisplay.setText("BWH Second Flor");
  }

  @FXML
  public void exteriorClicked() {
    linkDisplay.setText("https://github.com/palexdev/MaterialFX");
  }

  @FXML
  public void imagemarkClicked() {
    linkDisplay.setText("https://github.com/palexdev/MaterialFX");
  }

  @FXML
  public void lowerLevel1Clicked() {
    linkDisplay.setText("https://github.com/palexdev/MaterialFX");
  }

  @FXML
  public void lowerLevel2Clicked() {
    linkDisplay.setText("https://github.com/palexdev/MaterialFX");
  }

  @FXML
  public void secondFloorClicked() {
    linkDisplay.setText("https://github.com/palexdev/MaterialFX");
  }

  @FXML
  public void shapiroClicked() {
    linkDisplay.setText("https://github.com/palexdev/MaterialFX");
  }

  @FXML
  public void textLogoClicked() {
    linkDisplay.setText("https://github.com/palexdev/MaterialFX");
  }

  @FXML
  public void thirdFloorClicked() {
    linkDisplay.setText("https://github.com/palexdev/MaterialFX");
  }

  // software
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
