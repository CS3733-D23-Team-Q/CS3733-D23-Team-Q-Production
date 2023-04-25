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
  @FXML MFXButton brunchSpread;
  @FXML MFXButton chevronLeft;
  @FXML MFXButton chevronRight;
  @FXML MFXButton close;
  @FXML MFXButton conferenceRoomTitle;
  @FXML MFXButton confirm;
  @FXML MFXButton dinnerSpread;
  @FXML MFXButton down;
  @FXML MFXButton edge;
  @FXML MFXButton exit;
  @FXML MFXButton exitBlue;
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
  @FXML MFXButton snackSpread;
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
  @FXML MFXButton bandaids;
  @FXML MFXButton chicken;
  @FXML MFXButton coffee;
  @FXML MFXButton coke;
  @FXML MFXButton cotton;
  @FXML MFXButton couch;
  @FXML MFXButton daisies;
  @FXML MFXButton depressors;
  @FXML MFXButton desk;
  @FXML MFXButton deskChair;
  @FXML MFXButton examTable;
  @FXML MFXButton fish;
  @FXML MFXButton frenchFries;
  @FXML MFXButton furnitureTitle;
  @FXML MFXButton gauze;
  @FXML MFXButton highlighters;
  @FXML MFXButton indexCards;
  @FXML MFXButton lilies;
  @FXML MFXButton mealTitle;
  @FXML MFXButton medSupplies;
  @FXML MFXButton notepadsClicked;
  @FXML MFXButton officeSuppliesTitle;
  @FXML MFXButton onionRings;
  @FXML MFXButton pens;
  @FXML MFXButton pencils;
  @FXML MFXButton pork;
  @FXML MFXButton roses;
  @FXML MFXButton salad;
  @FXML MFXButton soup;
  @FXML MFXButton steak;
  @FXML MFXButton sunflowers;
  @FXML MFXButton syringe;
  @FXML MFXButton tea;
  @FXML MFXButton tulips;
  @FXML MFXButton water;
  @FXML MFXButton vegetables;

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
    linkDisplay.setText("https://fontawesome.com/icons/circle-exclamation?f=classic&s=solid");
  }

  @FXML
  public void alertBoxClicked() {
    linkDisplay.setText("https://fontawesome.com/icons/circle-exclamation?f=classic&s=regular");
  }

  @FXML
  public void blueLogoClicked() {
    linkDisplay.setText(
        "https://www.google.com/url?sa=i&url=https%3A%2F%2Finnovation.massgeneralbrigham.org%2F&psig=AOvVaw2Viz7R1kroR73Q--3Ku8uM&ust=1682277692166000&source=images&cd=vfe&ved=0CBAQjRxqFwoTCIibuPSavv4CFQAAAAAdAAAAABAE");
  }

  @FXML
  public void brunchSpreadClicked() {
    linkDisplay.setText(
            "https://www.google.com/search?q=brunch+spread&sxsrf=APwXEdeL0Yb-g694zWTp6lgSiCfbaZb4_w:1682449731163&source=lnms&tbm=isch&sa=X&ved=2ahUKEwia3c_U3cX-AhWMk4kEHYwsDawQ0pQJegQIBhAC&biw=1536&bih=713&dpr=2.5#imgrc=P8XhWPc9aIJAIM");
  }

  @FXML
  public void chevronLeftClicked() {
    linkDisplay.setText("https://fontawesome.com/icons/chevron-left?f=classic&s=solid");
  }

  @FXML
  public void chevronRightClicked() {
    linkDisplay.setText("https://fontawesome.com/icons/chevron-right?f=classic&s=solid");
  }

  @FXML
  public void closeClicked() {
    linkDisplay.setText("https://fontawesome.com/icons/xmark?f=classic&s=light");
  }

  @FXML
  public void crtClicked() {
    linkDisplay.setText("https://www.google.com/search?q=office+couch+horizontal+image&tbm=isch&ved=2ahUKEwiFo__j1sD-AhWrAFkFHU6HCnMQ2-cCegQIABAA&oq=office+couch+horizontal+image&gs_lcp=CgNpbWcQAzoECCMQJ1CTCFiyEmCKHmgAcAB4AIABOogBrAOSAQE4mAEAoAEBqgELZ3dzLXdpei1pbWfAAQE&sclient=img&ei=9X5FZMXUJ6uB5NoPzo6qmAc&bih=713&biw=1536#imgrc=_TaBQN-lmf47LM");
  }
  @FXML
  public void confirmClicked() {
    linkDisplay.setText("https://fontawesome.com/icons/circle-check?f=classic&s=regular");
  }

  @FXML
  public void dinnerSpreadClicked() {
    linkDisplay.setText(
            "https://www.google.com/search?q=dinner+spread+&tbm=isch&ved=2ahUKEwjonL7DqcH-AhUnGN4AHVe2CioQ2-cCegQIABAA&oq=dinner+spread+&gs_lcp=CgNpbWcQAzIECCMQJzIFCAAQgAQyBAgAEB4yBggAEAUQHjIGCAAQBRAeMgYIABAFEB4yBggAEAUQHjIGCAAQBRAeMgYIABAFEB4yBggAEAUQHlDbCljbCmCdDmgAcAB4AIABb4gBywGSAQMxLjGYAQCgAQGqAQtnd3Mtd2l6LWltZ8ABAQ&sclient=img&ei=udVFZKjcJaew-LYP1-yq0AI&bih=713&biw=1536#imgrc=KDn0YzfkbaKHHM");
  }

  @FXML
  public void downClicked() {
    linkDisplay.setText("https://fontawesome.com/icons/arrow-down?f=classic&s=solid");
  }

  @FXML
  public void edgeClicked() {
    linkDisplay.setText("https://fontawesome.com/icons/code-merge?f=classic&s=solid");
  }

  @FXML
  public void exitClicked() {
    linkDisplay.setText("https://freeicons.io/controls-icons/exit-icon-39607");
  }

  @FXML
  public void exitBlueClicked() {
    linkDisplay.setText("https://freeicons.io/controls-icons/exit-icon-39607");
  }

  @FXML
  public void homeClicked() {
    linkDisplay.setText("https://freeicons.io/material-icons-action/home-icon-15944");
  }

  @FXML
  public void homeBlueClicked() {
    linkDisplay.setText("https://freeicons.io/material-icons-action/home-icon-15944");
  }

  @FXML
  public void leftArrowClicked() {
    linkDisplay.setText("https://fontawesome.com/icons/arrow-left?f=classic&s=solid");
  }

  @FXML
  public void locClicked() {
    linkDisplay.setText("https://fontawesome.com/icons/location-dot?f=sharp&s=solid");
  }

  @FXML
  public void loginExitClicked() {
    linkDisplay.setText("https://freeicons.io/material-icons-action-2/exit-to-app-icon-16880");
  }

  @FXML
  public void logoClicked() {
    linkDisplay.setText(
        "https://www.google.com/url?sa=i&url=https%3A%2F%2Finnovation.massgeneralbrigham.org%2F&psig=AOvVaw1-aU9VafVfv0WsXljuKITf&ust=1682278518253000&source=images&cd=vfe&ved=0CBAQjRxqFwoTCLiQp_6dvv4CFQAAAAAdAAAAABAE");
  }

  @FXML
  public void mapClicked() {
    linkDisplay.setText("https://fontawesome.com/icons/map?f=classic&s=regular");
  }

  @FXML
  public void map2Clicked() {
    linkDisplay.setText("https://fontawesome.com/icons/map-location-dot?f=sharp&s=regular");
  }

  @FXML
  public void mapBlueClicked() {
    linkDisplay.setText("https://fontawesome.com/icons/map?f=classic&s=regular");
  }

  @FXML
  public void mapEditorClicked() {
    linkDisplay.setText("https://fontawesome.com/icons/map?f=classic&s=regular");
  }

  @FXML
  public void menuClicked() {
    linkDisplay.setText("https://fontawesome.com/icons/bars?f=classic&s=solid");
  }

  @FXML
  public void moveClicked() {
    linkDisplay.setText("https://fontawesome.com/icons/car?f=classic&s=solid");
  }

  @FXML
  public void nodeClicked() {
    linkDisplay.setText("https://fontawesome.com/icons/location-crosshairs?f=classic&s=solid");
  }

  @FXML
  public void peopleClicked() {
    linkDisplay.setText(
        "https://freeicons.io/public-relations/public-people-target-audience-group-icon-132938");
  }

  @FXML
  public void peopleBlueClicked() {
    linkDisplay.setText(
        "https://freeicons.io/public-relations/public-people-target-audience-group-icon-132938");
  }

  @FXML
  public void profileClicked() {
    linkDisplay.setText("https://fontawesome.com/icons/user?f=classic&s=solid");
  }

  @FXML
  public void rightArrowClicked() {
    linkDisplay.setText("https://fontawesome.com/icons/arrow-right?f=classic&s=solid");
  }

  @FXML
  public void srClicked() {
    linkDisplay.setText(
        "https://freeicons.io/essential-web-4/clipboard-document-office-form-application-icon-40409");
  }

  @FXML
  public void srBlueClicked() {
    linkDisplay.setText(
        "https://freeicons.io/essential-web-4/clipboard-document-office-form-application-icon-40409");
  }

  @FXML
  public void settingsClicked() {
    linkDisplay.setText("https://fontawesome.com/icons/gear?f=classic&s=regular");
  }

  @FXML
  public void settingsBlueClicked() {
    linkDisplay.setText("https://fontawesome.com/icons/gear?f=classic&s=regular");
  }

  @FXML
  public void snackSpreadClicked() {
    linkDisplay.setText(
            "https://www.google.com/search?q=snack+spread+&tbm=isch&ved=2ahUKEwiMnNn-qcH-AhXDMt4AHZwhD3AQ2-cCegQIABAA&oq=snack+spread+&gs_lcp=CgNpbWcQAzIFCAAQgAQyBAgAEB4yBggAEAgQHjIGCAAQCBAeMgYIABAIEB5QsQpYsQpgxgxoAHAAeACAAWeIAcYBkgEDMS4xmAEAoAEBqgELZ3dzLXdpei1pbWfAAQE&sclient=img&ei=NdZFZMytMMPl-LYPnMO8gAc&bih=713&biw=1536#imgrc=HszP5SQsvSRJIM");
  }

  @FXML
  public void statClicked() {
    linkDisplay.setText("https://freeicons.io/outline-and-life-style/analytics-graph-bar-icon-1");
  }

  @FXML
  public void statBlueClicked() {
    linkDisplay.setText("https://freeicons.io/outline-and-life-style/analytics-graph-bar-icon-1");
  }

  @FXML
  public void upClicked() {
    linkDisplay.setText("https://fontawesome.com/icons/arrow-up?f=classic&s=solid");
  }

  // images
  @FXML
  public void firstFloorClicked() {
    linkDisplay.setText("BWH First Floor");
  }

  @FXML
  public void groundFloorClicked() {
    linkDisplay.setText("BWH Second Floor");
  }

  @FXML
  public void exteriorClicked() {
    linkDisplay.setText(
        "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.massgeneralbrigham.org%2Fen%2Fpatient-care%2Finternational%2Fabout%2Fbrigham-and-womens-hospital&psig=AOvVaw3BJrJRgE9gd9oMf_708U4Z&ust=1682276866487000&source=images&cd=vfe&ved=0CBAQjRxqFwoTCJDX6uqXvv4CFQAAAAAdAAAAABAa");
  }

  @FXML
  public void imagemarkClicked() {
    linkDisplay.setText(
        "https://www.google.com/url?sa=i&url=https%3A%2F%2Fnndc.org%2Fnndc-members%2Fbrigham-and-womens-hospital%2F&psig=AOvVaw2acGby4gEVm2jrpGbZVryV&ust=1682277038806000&source=images&cd=vfe&ved=0CBAQjRxqFwoTCICFmb2Yvv4CFQAAAAAdAAAAABAE");
  }

  @FXML
  public void lowerLevel1Clicked() {
    linkDisplay.setText("BWH Lower Level 1");
  }

  @FXML
  public void lowerLevel2Clicked() {
    linkDisplay.setText("BWH Lower Level 2");
  }

  @FXML
  public void secondFloorClicked() {
    linkDisplay.setText("BWH Second Floor");
  }

  @FXML
  public void shapiroClicked() {
    linkDisplay.setText(
        "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.massgeneralbrigham.org%2Fen%2Fpatient-care%2Finternational%2Fabout%2Fbrigham-and-womens-hospital&psig=AOvVaw3BJrJRgE9gd9oMf_708U4Z&ust=1682276866487000&source=images&cd=vfe&ved=0CBAQjRxqFwoTCJDX6uqXvv4CFQAAAAAdAAAAABAa");
  }

  @FXML
  public void textLogoClicked() {
    linkDisplay.setText(
        "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.brighamandwomens.org%2F&psig=AOvVaw3BJrJRgE9gd9oMf_708U4Z&ust=1682276866487000&source=images&cd=vfe&ved=0CBAQjRxqFwoTCJDX6uqXvv4CFQAAAAAdAAAAABAE");
  }

  @FXML
  public void thirdFloorClicked() {
    linkDisplay.setText("BWH Third Floor");
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
