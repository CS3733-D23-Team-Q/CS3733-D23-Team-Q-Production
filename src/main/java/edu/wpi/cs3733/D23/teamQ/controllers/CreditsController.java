package edu.wpi.cs3733.D23.teamQ.controllers;

import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.web.WebView;

public class CreditsController {
  @FXML Label linkDisplay;
  @FXML WebView browser;
  @FXML MFXButton copy;
  @FXML MFXButton clear;

  // libraries
  @FXML MFXButton calendarfx;
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
  @FXML MFXButton delete;
  @FXML MFXButton edit;
  @FXML MFXButton greenCircle;
  @FXML MFXButton phone;
  @FXML MFXButton username;

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
  @FXML MFXButton ream;
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
  @FXML MFXButton crt;

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
  public void initialize() {
    browser.getEngine().load("https://www.brighamandwomens.org/");
  }

  @FXML
  public void clearClicked() {
    linkDisplay.setText("Click an item to display the associated link and page:");
    browser.getEngine().load("https://www.brighamandwomens.org/");
    cb.clear();
  }

  @FXML
  public void copyClicked() {
    content.putString(linkDisplay.getText());
    cb.setContent(content);
  }

  // libraries

  @FXML
  public void calendarfxClicked() {
    linkDisplay.setText(
        "https://www.componentsource.com/product/calendarfx#:~:text=CalendarFX%20is%20a%20calendar%20component,consistent%20with%20standard%20JavaFX%20controls");
    browser
        .getEngine()
        .load(
            "https://www.componentsource.com/product/calendarfx#:~:text=CalendarFX%20is%20a%20calendar%20component,consistent%20with%20standard%20JavaFX%20controls");
  }

  @FXML
  public void gesturefxClicked() {
    linkDisplay.setText("https://github.com/tom91136/GestureFX");
    browser.getEngine().load("https://github.com/tom91136/GestureFX");
  }

  @FXML
  public void materialfxClicked() {
    linkDisplay.setText("https://github.com/palexdev/MaterialFX");
    browser.getEngine().load("https://github.com/palexdev/MaterialFX");
  }

  // icons
  @FXML
  public void alertClicked() {
    linkDisplay.setText("https://fontawesome.com/icons/circle-exclamation?f=classic&s=solid");
    browser.getEngine().load("https://fontawesome.com/icons/circle-exclamation?f=classic&s=solid");
  }

  @FXML
  public void alertBoxClicked() {
    linkDisplay.setText("https://fontawesome.com/icons/circle-exclamation?f=classic&s=regular");
    browser
        .getEngine()
        .load("https://fontawesome.com/icons/circle-exclamation?f=classic&s=regular");
  }

  @FXML
  public void blueLogoClicked() {
    linkDisplay.setText(
        "https://www.google.com/url?sa=i&url=https%3A%2F%2Finnovation.massgeneralbrigham.org%2F&psig=AOvVaw2Viz7R1kroR73Q--3Ku8uM&ust=1682277692166000&source=images&cd=vfe&ved=0CBAQjRxqFwoTCIibuPSavv4CFQAAAAAdAAAAABAE");
    browser
        .getEngine()
        .load(
            "https://www.google.com/url?sa=i&url=https%3A%2F%2Finnovation.massgeneralbrigham.org%2F&psig=AOvVaw2Viz7R1kroR73Q--3Ku8uM&ust=1682277692166000&source=images&cd=vfe&ved=0CBAQjRxqFwoTCIibuPSavv4CFQAAAAAdAAAAABAE");
  }

  @FXML
  public void brunchSpreadClicked() {
    linkDisplay.setText(
        "https://www.google.com/search?q=brunch+spread&sxsrf=APwXEdeL0Yb-g694zWTp6lgSiCfbaZb4_w:1682449731163&source=lnms&tbm=isch&sa=X&ved=2ahUKEwia3c_U3cX-AhWMk4kEHYwsDawQ0pQJegQIBhAC&biw=1536&bih=713&dpr=2.5#imgrc=P8XhWPc9aIJAIM");
    browser
        .getEngine()
        .load(
            "https://www.google.com/search?q=brunch+spread&sxsrf=APwXEdeL0Yb-g694zWTp6lgSiCfbaZb4_w:1682449731163&source=lnms&tbm=isch&sa=X&ved=2ahUKEwia3c_U3cX-AhWMk4kEHYwsDawQ0pQJegQIBhAC&biw=1536&bih=713&dpr=2.5#imgrc=P8XhWPc9aIJAIM");
  }

  @FXML
  public void chevronLeftClicked() {
    linkDisplay.setText("https://fontawesome.com/icons/chevron-left?f=classic&s=solid");
    browser.getEngine().load("https://fontawesome.com/icons/chevron-left?f=classic&s=solid");
  }

  @FXML
  public void chevronRightClicked() {
    linkDisplay.setText("https://fontawesome.com/icons/chevron-right?f=classic&s=solid");
    browser.getEngine().load("https://fontawesome.com/icons/chevron-right?f=classic&s=solid");
  }

  @FXML
  public void closeClicked() {
    linkDisplay.setText("https://fontawesome.com/icons/xmark?f=classic&s=light");
    browser.getEngine().load("https://fontawesome.com/icons/xmark?f=classic&s=light");
  }

  @FXML
  public void confirmClicked() {
    linkDisplay.setText("https://fontawesome.com/icons/circle-check?f=classic&s=regular");
    browser.getEngine().load("https://fontawesome.com/icons/circle-check?f=classic&s=regular");
  }

  @FXML
  public void dinnerSpreadClicked() {
    linkDisplay.setText(
        "https://www.google.com/search?q=dinner+spread+&tbm=isch&ved=2ahUKEwjonL7DqcH-AhUnGN4AHVe2CioQ2-cCegQIABAA&oq=dinner+spread+&gs_lcp=CgNpbWcQAzIECCMQJzIFCAAQgAQyBAgAEB4yBggAEAUQHjIGCAAQBRAeMgYIABAFEB4yBggAEAUQHjIGCAAQBRAeMgYIABAFEB4yBggAEAUQHlDbCljbCmCdDmgAcAB4AIABb4gBywGSAQMxLjGYAQCgAQGqAQtnd3Mtd2l6LWltZ8ABAQ&sclient=img&ei=udVFZKjcJaew-LYP1-yq0AI&bih=713&biw=1536#imgrc=KDn0YzfkbaKHHM");
    browser
        .getEngine()
        .load(
            "https://www.google.com/search?q=dinner+spread+&tbm=isch&ved=2ahUKEwjonL7DqcH-AhUnGN4AHVe2CioQ2-cCegQIABAA&oq=dinner+spread+&gs_lcp=CgNpbWcQAzIECCMQJzIFCAAQgAQyBAgAEB4yBggAEAUQHjIGCAAQBRAeMgYIABAFEB4yBggAEAUQHjIGCAAQBRAeMgYIABAFEB4yBggAEAUQHlDbCljbCmCdDmgAcAB4AIABb4gBywGSAQMxLjGYAQCgAQGqAQtnd3Mtd2l6LWltZ8ABAQ&sclient=img&ei=udVFZKjcJaew-LYP1-yq0AI&bih=713&biw=1536#imgrc=KDn0YzfkbaKHHM");
  }

  @FXML
  public void downClicked() {
    linkDisplay.setText("https://fontawesome.com/icons/arrow-down?f=classic&s=solid");
    browser.getEngine().load("https://fontawesome.com/icons/arrow-down?f=classic&s=solid");
  }

  @FXML
  public void edgeClicked() {
    linkDisplay.setText("https://fontawesome.com/icons/code-merge?f=classic&s=solid");
    browser.getEngine().load("https://fontawesome.com/icons/code-merge?f=classic&s=solid");
  }

  @FXML
  public void exitClicked() {
    linkDisplay.setText("https://freeicons.io/controls-icons/exit-icon-39607");
    browser.getEngine().load("https://freeicons.io/controls-icons/exit-icon-39607");
  }

  @FXML
  public void exitBlueClicked() {
    linkDisplay.setText("https://freeicons.io/controls-icons/exit-icon-39607");
    browser.getEngine().load("https://freeicons.io/controls-icons/exit-icon-39607");
  }

  @FXML
  public void homeClicked() {
    linkDisplay.setText("https://freeicons.io/material-icons-action/home-icon-15944");
    browser.getEngine().load("https://freeicons.io/material-icons-action/home-icon-15944");
  }

  @FXML
  public void homeBlueClicked() {
    linkDisplay.setText("https://freeicons.io/material-icons-action/home-icon-15944");
    browser.getEngine().load("https://freeicons.io/material-icons-action/home-icon-15944");
  }

  @FXML
  public void leftArrowClicked() {
    linkDisplay.setText("https://fontawesome.com/icons/arrow-left?f=classic&s=solid");
    browser.getEngine().load("https://fontawesome.com/icons/arrow-left?f=classic&s=solid");
  }

  @FXML
  public void locClicked() {
    linkDisplay.setText("https://fontawesome.com/icons/location-dot?f=sharp&s=solid");
    browser.getEngine().load("https://fontawesome.com/icons/location-dot?f=sharp&s=solid");
  }

  @FXML
  public void loginExitClicked() {
    linkDisplay.setText("https://freeicons.io/material-icons-action-2/exit-to-app-icon-16880");
    browser.getEngine().load("https://freeicons.io/material-icons-action-2/exit-to-app-icon-16880");
  }

  @FXML
  public void logoClicked() {
    linkDisplay.setText(
        "https://www.google.com/url?sa=i&url=https%3A%2F%2Finnovation.massgeneralbrigham.org%2F&psig=AOvVaw1-aU9VafVfv0WsXljuKITf&ust=1682278518253000&source=images&cd=vfe&ved=0CBAQjRxqFwoTCLiQp_6dvv4CFQAAAAAdAAAAABAE");
    browser
        .getEngine()
        .load(
            "https://www.google.com/url?sa=i&url=https%3A%2F%2Finnovation.massgeneralbrigham.org%2F&psig=AOvVaw1-aU9VafVfv0WsXljuKITf&ust=1682278518253000&source=images&cd=vfe&ved=0CBAQjRxqFwoTCLiQp_6dvv4CFQAAAAAdAAAAABAE");
  }

  @FXML
  public void mapClicked() {
    linkDisplay.setText("https://fontawesome.com/icons/map?f=classic&s=regular");
    browser.getEngine().load("https://fontawesome.com/icons/map?f=classic&s=regular");
  }

  @FXML
  public void map2Clicked() {
    linkDisplay.setText("https://fontawesome.com/icons/map-location-dot?f=sharp&s=regular");
    browser.getEngine().load("https://fontawesome.com/icons/map-location-dot?f=sharp&s=regular");
  }

  @FXML
  public void mapBlueClicked() {
    linkDisplay.setText("https://fontawesome.com/icons/map?f=classic&s=regular");
    browser.getEngine().load("https://fontawesome.com/icons/map?f=classic&s=regular");
  }

  @FXML
  public void mapEditorClicked() {
    linkDisplay.setText("https://fontawesome.com/icons/map?f=classic&s=regular");
    browser.getEngine().load("https://fontawesome.com/icons/map?f=classic&s=regular");
  }

  @FXML
  public void menuClicked() {
    linkDisplay.setText("https://fontawesome.com/icons/bars?f=classic&s=solid");
    browser.getEngine().load("https://fontawesome.com/icons/bars?f=classic&s=solid");
  }

  @FXML
  public void moveClicked() {
    linkDisplay.setText("https://fontawesome.com/icons/car?f=classic&s=solid");
    browser.getEngine().load("https://fontawesome.com/icons/car?f=classic&s=solid");
  }

  @FXML
  public void nodeClicked() {
    linkDisplay.setText("https://fontawesome.com/icons/location-crosshairs?f=classic&s=solid");
    browser.getEngine().load("https://fontawesome.com/icons/location-crosshairs?f=classic&s=solid");
  }

  @FXML
  public void peopleClicked() {
    linkDisplay.setText(
        "https://freeicons.io/public-relations/public-people-target-audience-group-icon-132938");
    browser
        .getEngine()
        .load(
            "https://freeicons.io/public-relations/public-people-target-audience-group-icon-132938");
  }

  @FXML
  public void peopleBlueClicked() {
    linkDisplay.setText(
        "https://freeicons.io/public-relations/public-people-target-audience-group-icon-132938");
    browser
        .getEngine()
        .load(
            "https://freeicons.io/public-relations/public-people-target-audience-group-icon-132938");
  }

  @FXML
  public void profileClicked() {
    linkDisplay.setText("https://fontawesome.com/icons/user?f=classic&s=solid");
    browser.getEngine().load("https://fontawesome.com/icons/user?f=classic&s=solid");
  }

  @FXML
  public void rightArrowClicked() {
    linkDisplay.setText("https://fontawesome.com/icons/arrow-right?f=classic&s=solid");
    browser.getEngine().load("https://fontawesome.com/icons/arrow-right?f=classic&s=solid");
  }

  @FXML
  public void srClicked() {
    linkDisplay.setText(
        "https://freeicons.io/essential-web-4/clipboard-document-office-form-application-icon-40409");
    browser
        .getEngine()
        .load(
            "https://freeicons.io/essential-web-4/clipboard-document-office-form-application-icon-40409");
  }

  @FXML
  public void srBlueClicked() {
    linkDisplay.setText(
        "https://freeicons.io/essential-web-4/clipboard-document-office-form-application-icon-40409");
    browser
        .getEngine()
        .load(
            "https://freeicons.io/essential-web-4/clipboard-document-office-form-application-icon-40409");
  }

  @FXML
  public void settingsClicked() {
    linkDisplay.setText("https://fontawesome.com/icons/gear?f=classic&s=regular");
    browser.getEngine().load("https://fontawesome.com/icons/gear?f=classic&s=regular");
  }

  @FXML
  public void settingsBlueClicked() {
    linkDisplay.setText("https://fontawesome.com/icons/gear?f=classic&s=regular");
    browser.getEngine().load("https://fontawesome.com/icons/gear?f=classic&s=regular");
  }

  @FXML
  public void snackSpreadClicked() {
    linkDisplay.setText(
        "https://www.google.com/search?q=snack+spread+&tbm=isch&ved=2ahUKEwiMnNn-qcH-AhXDMt4AHZwhD3AQ2-cCegQIABAA&oq=snack+spread+&gs_lcp=CgNpbWcQAzIFCAAQgAQyBAgAEB4yBggAEAgQHjIGCAAQCBAeMgYIABAIEB5QsQpYsQpgxgxoAHAAeACAAWeIAcYBkgEDMS4xmAEAoAEBqgELZ3dzLXdpei1pbWfAAQE&sclient=img&ei=NdZFZMytMMPl-LYPnMO8gAc&bih=713&biw=1536#imgrc=HszP5SQsvSRJIM");
    browser
        .getEngine()
        .load(
            "https://www.google.com/search?q=snack+spread+&tbm=isch&ved=2ahUKEwiMnNn-qcH-AhXDMt4AHZwhD3AQ2-cCegQIABAA&oq=snack+spread+&gs_lcp=CgNpbWcQAzIFCAAQgAQyBAgAEB4yBggAEAgQHjIGCAAQCBAeMgYIABAIEB5QsQpYsQpgxgxoAHAAeACAAWeIAcYBkgEDMS4xmAEAoAEBqgELZ3dzLXdpei1pbWfAAQE&sclient=img&ei=NdZFZMytMMPl-LYPnMO8gAc&bih=713&biw=1536#imgrc=HszP5SQsvSRJIM");
  }

  @FXML
  public void statClicked() {
    linkDisplay.setText("https://freeicons.io/outline-and-life-style/analytics-graph-bar-icon-1");
    browser
        .getEngine()
        .load("https://freeicons.io/outline-and-life-style/analytics-graph-bar-icon-1");
  }

  @FXML
  public void statBlueClicked() {
    linkDisplay.setText("https://freeicons.io/outline-and-life-style/analytics-graph-bar-icon-1");
    browser
        .getEngine()
        .load("https://freeicons.io/outline-and-life-style/analytics-graph-bar-icon-1");
  }

  @FXML
  public void upClicked() {
    linkDisplay.setText("https://fontawesome.com/icons/arrow-up?f=classic&s=solid");
    browser.getEngine().load("https://fontawesome.com/icons/arrow-up?f=classic&s=solid");
  }

  @FXML
  public void deleteClicked() {
    linkDisplay.setText("https://icons8.com/icons/set/delete");
    browser.getEngine().load("https://icons8.com/icons/set/delete");
  }

  @FXML
  public void editClicked() {
    linkDisplay.setText("https://thenounproject.com/icon/edit-1000315/");
    browser.getEngine().load("https://thenounproject.com/icon/edit-1000315/");
  }

  @FXML
  public void greenCircleClicked() {
    linkDisplay.setText("https://icons8.com/icons/set/green-circle");
    browser.getEngine().load("https://icons8.com/icons/set/green-circle");
  }

  @FXML
  public void phoneClicked() {
    linkDisplay.setText("https://icons8.com/icons/set/phone");
    browser.getEngine().load("https://icons8.com/icons/set/phone");
  }

  @FXML
  public void usernameClicked() {
    linkDisplay.setText("https://icons8.com/icons/set/username");
    browser.getEngine().load("https://icons8.com/icons/set/username");
  }

  // images
  @FXML
  public void firstFloorClicked() {
    linkDisplay.setText("BWH First Floor");
    browser.getEngine().load("https://www.brighamandwomens.org/");
  }

  @FXML
  public void crtClicked() {
    linkDisplay.setText(
        "https://www.google.com/search?q=conference+room&tbm=isch&ved=2ahUKEwiX_q_UwcD-AhV9ON4AHYlUAIYQ2-cCegQIABAA&oq=conference+room&gs_lcp=CgNpbWcQAzIECCMQJzIICAAQgAQQsQMyBwgAEIoFEEMyBQgAEIAEMgUIABCABDIFCAAQgAQyBQgAEIAEMgUIABCABDIFCAAQgAQyBQgAEIAEOgYIABAHEB46BggAEAgQHjoGCAAQBRAeOgcIIxDqAhAnOgoIABCKBRCxAxBDOgkIABAYEIAEEAo6BwgAEBgQgARQwBZYvUpgsExoBXAAeACAAXiIAYAWkgEEMjMuOJgBAKABAaoBC2d3cy13aXotaW1nsAEKwAEB&sclient=img&ei=z2hFZNfaLf3w-LYPiamBsAg&bih=713&biw=1536#imgrc=Yij-pUBy9oQreM");
    browser.getEngine().load("https://www.google.com/search?q=conference+room&tbm=isch&ved=2ahUKEwiX_q_UwcD-AhV9ON4AHYlUAIYQ2-cCegQIABAA&oq=conference+room&gs_lcp=CgNpbWcQAzIECCMQJzIICAAQgAQQsQMyBwgAEIoFEEMyBQgAEIAEMgUIABCABDIFCAAQgAQyBQgAEIAEMgUIABCABDIFCAAQgAQyBQgAEIAEOgYIABAHEB46BggAEAgQHjoGCAAQBRAeOgcIIxDqAhAnOgoIABCKBRCxAxBDOgkIABAYEIAEEAo6BwgAEBgQgARQwBZYvUpgsExoBXAAeACAAXiIAYAWkgEEMjMuOJgBAKABAaoBC2d3cy13aXotaW1nsAEKwAEB&sclient=img&ei=z2hFZNfaLf3w-LYPiamBsAg&bih=713&biw=1536#imgrc=Yij-pUBy9oQreM");
  }

  @FXML
  public void exteriorClicked() {
    linkDisplay.setText(
        "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.massgeneralbrigham.org%2Fen%2Fpatient-care%2Finternational%2Fabout%2Fbrigham-and-womens-hospital&psig=AOvVaw3BJrJRgE9gd9oMf_708U4Z&ust=1682276866487000&source=images&cd=vfe&ved=0CBAQjRxqFwoTCJDX6uqXvv4CFQAAAAAdAAAAABAa");
    browser.getEngine().load("https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.massgeneralbrigham.org%2Fen%2Fpatient-care%2Finternational%2Fabout%2Fbrigham-and-womens-hospital&psig=AOvVaw3BJrJRgE9gd9oMf_708U4Z&ust=1682276866487000&source=images&cd=vfe&ved=0CBAQjRxqFwoTCJDX6uqXvv4CFQAAAAAdAAAAABAa");
  }

  @FXML
  public void imagemarkClicked() {
    linkDisplay.setText(
        "https://www.google.com/url?sa=i&url=https%3A%2F%2Fnndc.org%2Fnndc-members%2Fbrigham-and-womens-hospital%2F&psig=AOvVaw2acGby4gEVm2jrpGbZVryV&ust=1682277038806000&source=images&cd=vfe&ved=0CBAQjRxqFwoTCICFmb2Yvv4CFQAAAAAdAAAAABAE");
    browser.getEngine().load("https://www.google.com/url?sa=i&url=https%3A%2F%2Fnndc.org%2Fnndc-members%2Fbrigham-and-womens-hospital%2F&psig=AOvVaw2acGby4gEVm2jrpGbZVryV&ust=1682277038806000&source=images&cd=vfe&ved=0CBAQjRxqFwoTCICFmb2Yvv4CFQAAAAAdAAAAABAE");
  }

  @FXML
  public void lowerLevel1Clicked() {
    linkDisplay.setText("BWH Lower Level 1");
    browser.getEngine().load("https://www.brighamandwomens.org/");
  }

  @FXML
  public void lowerLevel2Clicked() {
    linkDisplay.setText("BWH Lower Level 2");
    browser.getEngine().load("https://www.brighamandwomens.org/");
  }

  @FXML
  public void secondFloorClicked() {
    linkDisplay.setText("BWH Second Floor");
    browser.getEngine().load("https://www.brighamandwomens.org/");
  }

  @FXML
  public void shapiroClicked() {
    linkDisplay.setText(
        "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.massgeneralbrigham.org%2Fen%2Fpatient-care%2Finternational%2Fabout%2Fbrigham-and-womens-hospital&psig=AOvVaw3BJrJRgE9gd9oMf_708U4Z&ust=1682276866487000&source=images&cd=vfe&ved=0CBAQjRxqFwoTCJDX6uqXvv4CFQAAAAAdAAAAABAa");
    browser.getEngine().load("https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.massgeneralbrigham.org%2Fen%2Fpatient-care%2Finternational%2Fabout%2Fbrigham-and-womens-hospital&psig=AOvVaw3BJrJRgE9gd9oMf_708U4Z&ust=1682276866487000&source=images&cd=vfe&ved=0CBAQjRxqFwoTCJDX6uqXvv4CFQAAAAAdAAAAABAa");
  }

  @FXML
  public void textLogoClicked() {
    linkDisplay.setText(
        "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.brighamandwomens.org%2F&psig=AOvVaw3BJrJRgE9gd9oMf_708U4Z&ust=1682276866487000&source=images&cd=vfe&ved=0CBAQjRxqFwoTCJDX6uqXvv4CFQAAAAAdAAAAABAE");
    browser.getEngine().load("https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.brighamandwomens.org%2F&psig=AOvVaw3BJrJRgE9gd9oMf_708U4Z&ust=1682276866487000&source=images&cd=vfe&ved=0CBAQjRxqFwoTCJDX6uqXvv4CFQAAAAAdAAAAABAE");
  }

  @FXML
  public void thirdFloorClicked() {
    linkDisplay.setText("BWH Third Floor");
    browser.getEngine().load("https://www.brighamandwomens.org/");
  }

  @FXML
  public void bandaidsClicked() {
    linkDisplay.setText(
        "https://www.google.com/search?q=bandaids+jpg&tbm=isch&ved=2ahUKEwjLq_yvjMP-AhXMwMkDHfEJAWoQ2-cCegQIABAA&oq=bandaids+jpg&gs_lcp=CgNpbWcQAzoHCAAQigUQQzoGCAAQBxAeOgYIABAIEB46CggAEIoFELEDEEM6BQgAEIAEOggIABAFEAcQHjoECAAQHlDoBFiZD2ChEWgAcAB4AIABaYgB-waSAQM5LjGYAQCgAQGqAQtnd3Mtd2l6LWltZ8ABAQ&sclient=img&ei=l8NGZIuEIcyBp84P8ZOE0AY&bih=713&biw=1536#imgrc=4dUVieWjbv5-AM");
    browser.getEngine().load("https://www.google.com/search?q=bandaids+jpg&tbm=isch&ved=2ahUKEwjLq_yvjMP-AhXMwMkDHfEJAWoQ2-cCegQIABAA&oq=bandaids+jpg&gs_lcp=CgNpbWcQAzoHCAAQigUQQzoGCAAQBxAeOgYIABAIEB46CggAEIoFELEDEEM6BQgAEIAEOggIABAFEAcQHjoECAAQHlDoBFiZD2ChEWgAcAB4AIABaYgB-waSAQM5LjGYAQCgAQGqAQtnd3Mtd2l6LWltZ8ABAQ&sclient=img&ei=l8NGZIuEIcyBp84P8ZOE0AY&bih=713&biw=1536#imgrc=4dUVieWjbv5-AM");
  }

  @FXML
  public void chickenClicked() {
    linkDisplay.setText(
        "https://www.google.com/search?q=chicken+entree+jpg&tbm=isch&ved=2ahUKEwjt0sfficP-AhUTFN4AHaokDPEQ2-cCegQIABAA&oq=chicken+entree+jpg&gs_lcp=CgNpbWcQAzoECCMQJzoFCAAQgAQ6BggAEAcQHjoHCAAQigUQQzoICAAQgAQQsQM6CggAEIoFELEDEEM6BwgjEOoCECc6CwgAEIAEELEDEIMBOgYIABAFEB46BAgAEB46BggAEAgQHlDnBljgNGCPNmgDcAB4A4ABeIgBiRSSAQUxMC4xNZgBAKABAaoBC2d3cy13aXotaW1nsAEKwAEB&sclient=img&ei=1sBGZO2PApOo-LYPqsmwiA8&bih=713&biw=1536#imgrc=OkerzZKgIewjxM");
    browser.getEngine().load("https://www.google.com/search?q=chicken+entree+jpg&tbm=isch&ved=2ahUKEwjt0sfficP-AhUTFN4AHaokDPEQ2-cCegQIABAA&oq=chicken+entree+jpg&gs_lcp=CgNpbWcQAzoECCMQJzoFCAAQgAQ6BggAEAcQHjoHCAAQigUQQzoICAAQgAQQsQM6CggAEIoFELEDEEM6BwgjEOoCECc6CwgAEIAEELEDEIMBOgYIABAFEB46BAgAEB46BggAEAgQHlDnBljgNGCPNmgDcAB4A4ABeIgBiRSSAQUxMC4xNZgBAKABAaoBC2d3cy13aXotaW1nsAEKwAEB&sclient=img&ei=1sBGZO2PApOo-LYPqsmwiA8&bih=713&biw=1536#imgrc=OkerzZKgIewjxM");
  }

  @FXML
  public void coffeeClicked() {
    linkDisplay.setText(
        "https://www.google.com/search?q=coffee+jpg&tbm=isch&ved=2ahUKEwjsnfvIicP-AhVjFd4AHUJcD7EQ2-cCegQIABAA&oq=coffee+jpg&gs_lcp=CgNpbWcQAzIFCAAQgAQyBQgAEIAEMgYIABAHEB4yBggAEAcQHjIGCAAQBxAeMgYIABAHEB4yBggAEAcQHjIGCAAQBxAeMgYIABAHEB4yBggAEAcQHjoECCMQJzoHCAAQigUQQzoJCAAQGBCABBAKUNkFWJsYYIobaANwAHgAgAHHAYgB4weSAQM4LjKYAQCgAQGqAQtnd3Mtd2l6LWltZ8ABAQ&sclient=img&ei=psBGZOyyJ-Oq-LYPwri9iAs&bih=713&biw=1536#imgrc=KQ7uwFPpIOq1zM");
    browser.getEngine().load("https://www.google.com/search?q=coffee+jpg&tbm=isch&ved=2ahUKEwjsnfvIicP-AhVjFd4AHUJcD7EQ2-cCegQIABAA&oq=coffee+jpg&gs_lcp=CgNpbWcQAzIFCAAQgAQyBQgAEIAEMgYIABAHEB4yBggAEAcQHjIGCAAQBxAeMgYIABAHEB4yBggAEAcQHjIGCAAQBxAeMgYIABAHEB4yBggAEAcQHjoECCMQJzoHCAAQigUQQzoJCAAQGBCABBAKUNkFWJsYYIobaANwAHgAgAHHAYgB4weSAQM4LjKYAQCgAQGqAQtnd3Mtd2l6LWltZ8ABAQ&sclient=img&ei=psBGZOyyJ-Oq-LYPwri9iAs&bih=713&biw=1536#imgrc=KQ7uwFPpIOq1zM");
  }

  @FXML
  public void cokeClicked() {
    linkDisplay.setText(
        "https://www.google.com/search?q=coke+can+jpg&tbm=isch&ved=2ahUKEwit6pyyicP-AhX_Mt4AHU8TDegQ2-cCegQIABAA&oq=coke+can+jpg&gs_lcp=CgNpbWcQAzIFCAAQgAQ6BAgjECc6BggAEAcQHlDdB1jRCWDFDGgAcAB4AIABc4gB_QKSAQMzLjGYAQCgAQGqAQtnd3Mtd2l6LWltZ8ABAQ&sclient=img&ei=dsBGZK3XOv_l-LYPz6a0wA4&bih=713&biw=1536#imgrc=tlvEFZ55MLDdhM");
    browser.getEngine().load("https://www.google.com/search?q=coke+can+jpg&tbm=isch&ved=2ahUKEwit6pyyicP-AhX_Mt4AHU8TDegQ2-cCegQIABAA&oq=coke+can+jpg&gs_lcp=CgNpbWcQAzIFCAAQgAQ6BAgjECc6BggAEAcQHlDdB1jRCWDFDGgAcAB4AIABc4gB_QKSAQMzLjGYAQCgAQGqAQtnd3Mtd2l6LWltZ8ABAQ&sclient=img&ei=dsBGZK3XOv_l-LYPz6a0wA4&bih=713&biw=1536#imgrc=tlvEFZ55MLDdhM");
  }

  @FXML
  public void cottonClicked() {
    linkDisplay.setText(
        "https://www.google.com/search?q=cotton+balls+medical+jpg&tbm=isch&ved=2ahUKEwiki8uqlMP-AhXCPd4AHTWyBhEQ2-cCegQIABAA&oq=cotton+balls+medical+jpg&gs_lcp=CgNpbWcQAzoECCMQJ1D3A1jbCWDRC2gAcAB4AYABaIgBrwaSAQM3LjKYAQCgAQGqAQtnd3Mtd2l6LWltZ8ABAQ&sclient=img&ei=78tGZOTXLML7-LYPteSaiAE&bih=713&biw=1536#imgrc=KZ-QG7FLo9mqFM");
    browser.getEngine().load("https://www.google.com/search?q=cotton+balls+medical+jpg&tbm=isch&ved=2ahUKEwiki8uqlMP-AhXCPd4AHTWyBhEQ2-cCegQIABAA&oq=cotton+balls+medical+jpg&gs_lcp=CgNpbWcQAzoECCMQJ1D3A1jbCWDRC2gAcAB4AYABaIgBrwaSAQM3LjKYAQCgAQGqAQtnd3Mtd2l6LWltZ8ABAQ&sclient=img&ei=78tGZOTXLML7-LYPteSaiAE&bih=713&biw=1536#imgrc=KZ-QG7FLo9mqFM");
  }

  @FXML
  public void couchClicked() {
    linkDisplay.setText(
        "https://www.google.com/search?q=couch+image+jpg&tbm=isch&ved=2ahUKEwjAi7DmgsP-AhXrPt4AHdVzCTkQ2-cCegQIABAA&oq=couch+image+jpg&gs_lcp=CgNpbWcQAzoECCMQJzoHCAAQigUQQzoFCAAQgAQ6CAgAEIAEELEDOgoIABCKBRCxAxBDOgsIABCABBCxAxCDAToGCAAQBRAeOgQIABAeUNgFWKogYNYhaAFwAHgAgAGDAYgBzQySAQQxMy40mAEAoAEBqgELZ3dzLXdpei1pbWfAAQE&sclient=img&ei=jblGZICREev9-LYP1eelyAM&bih=713&biw=1536");
    browser.getEngine().load("https://www.google.com/search?q=couch+image+jpg&tbm=isch&ved=2ahUKEwjAi7DmgsP-AhXrPt4AHdVzCTkQ2-cCegQIABAA&oq=couch+image+jpg&gs_lcp=CgNpbWcQAzoECCMQJzoHCAAQigUQQzoFCAAQgAQ6CAgAEIAEELEDOgoIABCKBRCxAxBDOgsIABCABBCxAxCDAToGCAAQBRAeOgQIABAeUNgFWKogYNYhaAFwAHgAgAGDAYgBzQySAQQxMy40mAEAoAEBqgELZ3dzLXdpei1pbWfAAQE&sclient=img&ei=jblGZICREev9-LYP1eelyAM&bih=713&biw=1536");
  }

  @FXML
  public void daisiesClicked() {
    linkDisplay.setText(
        "https://www.google.com/search?q=daisies+bouquet&tbm=isch&ved=2ahUKEwi7wuDH_sL-AhVYKt4AHe5CDUUQ2-cCegQIABAA&oq=daisies+bouquet&gs_lcp=CgNpbWcQAzIHCAAQigUQQzIFCAAQgAQyBwgAEIoFEEMyBQgAEIAEMgcIABCKBRBDMgUIABCABDIGCAAQBRAeMgYIABAFEB4yBggAEAUQHjIGCAAQBRAeOgQIIxAnOgYIABAHEB46BwgjEOoCECc6CAgAEIAEELEDOgoIABCKBRCxAxBDOgsIABCABBCxAxCDAVD1BFjTI2DGJWgBcAB4BIABvgGIAbIdkgEFMjAuMTWYAQCgAQGqAQtnd3Mtd2l6LWltZ7ABCsABAQ&sclient=img&ei=G7VGZPvIE9jU-LYP7oW1qAQ&bih=713&biw=1536#imgrc=QzTiECUcTu7L1M");
    browser.getEngine().load("https://www.google.com/search?q=daisies+bouquet&tbm=isch&ved=2ahUKEwi7wuDH_sL-AhVYKt4AHe5CDUUQ2-cCegQIABAA&oq=daisies+bouquet&gs_lcp=CgNpbWcQAzIHCAAQigUQQzIFCAAQgAQyBwgAEIoFEEMyBQgAEIAEMgcIABCKBRBDMgUIABCABDIGCAAQBRAeMgYIABAFEB4yBggAEAUQHjIGCAAQBRAeOgQIIxAnOgYIABAHEB46BwgjEOoCECc6CAgAEIAEELEDOgoIABCKBRCxAxBDOgsIABCABBCxAxCDAVD1BFjTI2DGJWgBcAB4BIABvgGIAbIdkgEFMjAuMTWYAQCgAQGqAQtnd3Mtd2l6LWltZ7ABCsABAQ&sclient=img&ei=G7VGZPvIE9jU-LYP7oW1qAQ&bih=713&biw=1536#imgrc=QzTiECUcTu7L1M");
  }

  @FXML
  public void depressorsClicked() {
    linkDisplay.setText(
        "https://www.google.com/search?q=tongue+depresser+jpg&tbm=isch&ved=2ahUKEwjli_vFlcP-AhVYFN4AHW1zCfMQ2-cCegQIABAA&oq=tongue+depresser+jpg&gs_lcp=CgNpbWcQAzoECCMQJzoFCAAQgAQ6BggAEAcQHjoHCAAQigUQQzoGCAAQBRAeUPQGWO4hYI8uaABwAHgAgAFuiAGoDJIBBDE1LjKYAQCgAQGqAQtnd3Mtd2l6LWltZ8ABAQ&sclient=img&ei=Nc1GZKWdI9io-LYP7ealmA8&bih=713&biw=1536#imgrc=OYWpIKXo6PPcvM");
    browser.getEngine().load("https://www.google.com/search?q=tongue+depresser+jpg&tbm=isch&ved=2ahUKEwjli_vFlcP-AhVYFN4AHW1zCfMQ2-cCegQIABAA&oq=tongue+depresser+jpg&gs_lcp=CgNpbWcQAzoECCMQJzoFCAAQgAQ6BggAEAcQHjoHCAAQigUQQzoGCAAQBRAeUPQGWO4hYI8uaABwAHgAgAFuiAGoDJIBBDE1LjKYAQCgAQGqAQtnd3Mtd2l6LWltZ8ABAQ&sclient=img&ei=Nc1GZKWdI9io-LYP7ealmA8&bih=713&biw=1536#imgrc=OYWpIKXo6PPcvM");
  }

  @FXML
  public void deskClicked() {
    linkDisplay.setText(
        "https://www.google.com/search?q=desk+jpg+image&tbm=isch&ved=2ahUKEwjV69CkgsP-AhWHEt4AHem-CR0Q2-cCegQIABAA&oq=desk+jpg+image&gs_lcp=CgNpbWcQAzoECCMQJzoJCAAQGBCABBAKOgUIABCABDoICAAQgAQQsQM6BggAEAgQHjoECAAQHlCuCViJIGDnIWgDcAB4AIABgwGIAYsMkgEDOS43mAEAoAEBqgELZ3dzLXdpei1pbWfAAQE&sclient=img&ei=A7lGZJXeGIel-LYP6f2m6AE&bih=713&biw=1536#imgrc=OEL1ZAjQwOQEEM");
    browser.getEngine().load("https://www.google.com/search?q=desk+jpg+image&tbm=isch&ved=2ahUKEwjV69CkgsP-AhWHEt4AHem-CR0Q2-cCegQIABAA&oq=desk+jpg+image&gs_lcp=CgNpbWcQAzoECCMQJzoJCAAQGBCABBAKOgUIABCABDoICAAQgAQQsQM6BggAEAgQHjoECAAQHlCuCViJIGDnIWgDcAB4AIABgwGIAYsMkgEDOS43mAEAoAEBqgELZ3dzLXdpei1pbWfAAQE&sclient=img&ei=A7lGZJXeGIel-LYP6f2m6AE&bih=713&biw=1536#imgrc=OEL1ZAjQwOQEEM");
  }

  @FXML
  public void deskChairClicked() {
    linkDisplay.setText(
        "https://www.google.com/search?q=desk+chair+jpg+image&tbm=isch&ved=2ahUKEwi3roa6gsP-AhXJIt4AHWt4BToQ2-cCegQIABAA&oq=desk+chair+jpg+image&gs_lcp=CgNpbWcQAzoECCMQJ1DPA1jVCmCPDGgAcAB4AIAB7AGIAZwHkgEFMS41LjGYAQCgAQGqAQtnd3Mtd2l6LWltZ8ABAQ&sclient=img&ei=MLlGZLfWE8nF-LYP6_CV0AM&bih=713&biw=1536#imgrc=8tayslC8a256tM");
    browser.getEngine().load("https://www.google.com/search?q=desk+chair+jpg+image&tbm=isch&ved=2ahUKEwi3roa6gsP-AhXJIt4AHWt4BToQ2-cCegQIABAA&oq=desk+chair+jpg+image&gs_lcp=CgNpbWcQAzoECCMQJ1DPA1jVCmCPDGgAcAB4AIAB7AGIAZwHkgEFMS41LjGYAQCgAQGqAQtnd3Mtd2l6LWltZ8ABAQ&sclient=img&ei=MLlGZLfWE8nF-LYP6_CV0AM&bih=713&biw=1536#imgrc=8tayslC8a256tM");
  }

  @FXML
  public void examTableClicked() {
    linkDisplay.setText(
        "https://www.google.com/search?q=examination+table+jpg+image&tbm=isch&ved=2ahUKEwil1NXXgsP-AhUNKd4AHfi1AU8Q2-cCegQIABAA&oq=examination+table+jpg+image&gs_lcp=CgNpbWcQAzoECCMQJzoGCAAQBxAeOggIABAFEAcQHjoICAAQCBAHEB5QtQVY4ytgyC1oA3AAeACAAWKIAbkMkgECMTmYAQCgAQGqAQtnd3Mtd2l6LWltZ8ABAQ&sclient=img&ei=brlGZKXlGo3S-LYP-OuG-AQ&bih=713&biw=1536#imgrc=zrxAr-hiiKzv3M");
    browser.getEngine().load("https://www.google.com/search?q=examination+table+jpg+image&tbm=isch&ved=2ahUKEwil1NXXgsP-AhUNKd4AHfi1AU8Q2-cCegQIABAA&oq=examination+table+jpg+image&gs_lcp=CgNpbWcQAzoECCMQJzoGCAAQBxAeOggIABAFEAcQHjoICAAQCBAHEB5QtQVY4ytgyC1oA3AAeACAAWKIAbkMkgECMTmYAQCgAQGqAQtnd3Mtd2l6LWltZ8ABAQ&sclient=img&ei=brlGZKXlGo3S-LYP-OuG-AQ&bih=713&biw=1536#imgrc=zrxAr-hiiKzv3M");
  }

  @FXML
  public void fishClicked() {
    linkDisplay.setText(
        "https://www.google.com/search?q=fish+entree+jpg&tbm=isch&ved=2ahUKEwjI4f_jisP-AhUsIt4AHX71DMsQ2-cCegQIABAA&oq=fish+entree+jpg&gs_lcp=CgNpbWcQA1DeAliXDWDLD2gAcAB4AIABbYgBoAaSAQM2LjOYAQCgAQGqAQtnd3Mtd2l6LWltZ8ABAQ&sclient=img&ei=68FGZIjAL6zE-LYP_uqz2Aw&bih=713&biw=1536#imgrc=KrjwPh9uaHSsnM");
    browser.getEngine().load("https://www.google.com/search?q=fish+entree+jpg&tbm=isch&ved=2ahUKEwjI4f_jisP-AhUsIt4AHX71DMsQ2-cCegQIABAA&oq=fish+entree+jpg&gs_lcp=CgNpbWcQA1DeAliXDWDLD2gAcAB4AIABbYgBoAaSAQM2LjOYAQCgAQGqAQtnd3Mtd2l6LWltZ8ABAQ&sclient=img&ei=68FGZIjAL6zE-LYP_uqz2Aw&bih=713&biw=1536#imgrc=KrjwPh9uaHSsnM");
  }

  @FXML
  public void frenchFriesClicked() {
    linkDisplay.setText(
        "https://www.google.com/search?q=fries+jpg&tbm=isch&ved=2ahUKEwj534P6isP-AhXcJN4AHS-YCzMQ2-cCegQIABAA&oq=fries+jpg&gs_lcp=CgNpbWcQAzIFCAAQgAQyCAgAEAUQBxAeMgYIABAFEB46BggAEAcQHlCdBlj6HmCdIGgGcAB4AIABf4gBuQiSAQM2LjWYAQCgAQGqAQtnd3Mtd2l6LWltZ8ABAQ&sclient=img&ei=GcJGZLnvO9zJ-LYPr7CumAM&bih=713&biw=1536#imgrc=GSL4u7z3wBGcfM");
    browser.getEngine().load("https://www.google.com/search?q=fries+jpg&tbm=isch&ved=2ahUKEwj534P6isP-AhXcJN4AHS-YCzMQ2-cCegQIABAA&oq=fries+jpg&gs_lcp=CgNpbWcQAzIFCAAQgAQyCAgAEAUQBxAeMgYIABAFEB46BggAEAcQHlCdBlj6HmCdIGgGcAB4AIABf4gBuQiSAQM2LjWYAQCgAQGqAQtnd3Mtd2l6LWltZ8ABAQ&sclient=img&ei=GcJGZLnvO9zJ-LYPr7CumAM&bih=713&biw=1536#imgrc=GSL4u7z3wBGcfM");
  }

  @FXML
  public void ftClicked() {
    linkDisplay.setText(
        "https://www.google.com/search?q=office+couch+horizontal+image&tbm=isch&ved=2ahUKEwiFo__j1sD-AhWrAFkFHU6HCnMQ2-cCegQIABAA&oq=office+couch+horizontal+image&gs_lcp=CgNpbWcQAzoECCMQJ1CTCFiyEmCKHmgAcAB4AIABOogBrAOSAQE4mAEAoAEBqgELZ3dzLXdpei1pbWfAAQE&sclient=img&ei=9X5FZMXUJ6uB5NoPzo6qmAc&bih=713&biw=1536#imgrc=_TaBQN-lmf47LM");
    browser.getEngine().load("https://www.google.com/search?q=office+couch+horizontal+image&tbm=isch&ved=2ahUKEwiFo__j1sD-AhWrAFkFHU6HCnMQ2-cCegQIABAA&oq=office+couch+horizontal+image&gs_lcp=CgNpbWcQAzoECCMQJ1CTCFiyEmCKHmgAcAB4AIABOogBrAOSAQE4mAEAoAEBqgELZ3dzLXdpei1pbWfAAQE&sclient=img&ei=9X5FZMXUJ6uB5NoPzo6qmAc&bih=713&biw=1536#imgrc=_TaBQN-lmf47LM");
  }

  @FXML
  public void gauzeClicked() {
    linkDisplay.setText(
        "https://www.google.com/search?q=gauze+jpg&tbm=isch&ved=2ahUKEwiYyNWmlcP-AhU9M94AHYmKAYkQ2-cCegQIABAA&oq=gauze+jpg&gs_lcp=CgNpbWcQAzoECCMQJzoHCAAQigUQQzoKCAAQigUQsQMQQzoFCAAQgAQ6BggAEAcQHjoICAAQCBAHEB5QmAlY6TpgxTxoAnAAeACAAXaIAdgGkgEDMi42mAEAoAEBqgELZ3dzLXdpei1pbWfAAQE&sclient=img&ei=88xGZNiCOr3m-LYPiZWGyAg&bih=713&biw=1536#imgrc=hks02bKtiK-JkM");
    browser.getEngine().load("https://www.google.com/search?q=gauze+jpg&tbm=isch&ved=2ahUKEwiYyNWmlcP-AhU9M94AHYmKAYkQ2-cCegQIABAA&oq=gauze+jpg&gs_lcp=CgNpbWcQAzoECCMQJzoHCAAQigUQQzoKCAAQigUQsQMQQzoFCAAQgAQ6BggAEAcQHjoICAAQCBAHEB5QmAlY6TpgxTxoAnAAeACAAXaIAdgGkgEDMi42mAEAoAEBqgELZ3dzLXdpei1pbWfAAQE&sclient=img&ei=88xGZNiCOr3m-LYPiZWGyAg&bih=713&biw=1536#imgrc=hks02bKtiK-JkM");
  }

  @FXML
  public void highlightersClicked() {
    linkDisplay.setText(
        "https://www.google.com/search?q=highlighter+pack+jpg&&tbm=isch&ved=2ahUKEwiq25yeocP-AhV5Id4AHRQVD9EQ2-cCegQIABAA&oq=highlighter+pack+jpg&gs_lcp=CgNpbWcQAzoECCMQJzoFCAAQgAQ6BggAEAcQHjoECAAQHjoGCAAQBRAeOgYIABAIEB5QwAJYzA9gyxFoAHAAeACAAXCIAecDkgEDMi4zmAEAoAEBqgELZ3dzLXdpei1pbWfAAQE&sclient=img&ei=d9lGZOrDHPnC-LYPlKq8iA0&bih=713&biw=1536#imgrc=eyRKnN8vh1hozM");
    browser.getEngine().load("https://www.google.com/search?q=highlighter+pack+jpg&&tbm=isch&ved=2ahUKEwiq25yeocP-AhV5Id4AHRQVD9EQ2-cCegQIABAA&oq=highlighter+pack+jpg&gs_lcp=CgNpbWcQAzoECCMQJzoFCAAQgAQ6BggAEAcQHjoECAAQHjoGCAAQBRAeOgYIABAIEB5QwAJYzA9gyxFoAHAAeACAAXCIAecDkgEDMi4zmAEAoAEBqgELZ3dzLXdpei1pbWfAAQE&sclient=img&ei=d9lGZOrDHPnC-LYPlKq8iA0&bih=713&biw=1536#imgrc=eyRKnN8vh1hozM");
  }

  @FXML
  public void reamClicked() {
    linkDisplay.setText(
        "https://www.google.com/search?q=printer+paper+by+ream&tbm=isch&ved=2ahUKEwi8j46dlsP-AhWZKt4AHSsXARgQ2-cCegQIABAA&oq=printer+paper+by+ream&gs_lcp=CgNpbWcQAzoECCMQJzoFCAAQgAQ6BAgAEB46BggAEAUQHjoGCAAQCBAeOgcIIxDqAhAnOggIABCABBCxAzoICAAQsQMQgwE6BwgAEIoFEEM6CwgAEIAEELEDEIMBOgcIABAYEIAEUNIMWPkzYMI2aANwAHgEgAGdAYgBzxuSAQUyMC4xNZgBAKABAaoBC2d3cy13aXotaW1nsAEKwAEB&sclient=img&ei=7M1GZLzpFJnV-LYPq66EwAE&bih=713&biw=1536#imgrc=t21H4lqDRNbv2M");
    browser.getEngine().load("https://www.google.com/search?q=printer+paper+by+ream&tbm=isch&ved=2ahUKEwi8j46dlsP-AhWZKt4AHSsXARgQ2-cCegQIABAA&oq=printer+paper+by+ream&gs_lcp=CgNpbWcQAzoECCMQJzoFCAAQgAQ6BAgAEB46BggAEAUQHjoGCAAQCBAeOgcIIxDqAhAnOggIABCABBCxAzoICAAQsQMQgwE6BwgAEIoFEEM6CwgAEIAEELEDEIMBOgcIABAYEIAEUNIMWPkzYMI2aANwAHgEgAGdAYgBzxuSAQUyMC4xNZgBAKABAaoBC2d3cy13aXotaW1nsAEKwAEB&sclient=img&ei=7M1GZLzpFJnV-LYPq66EwAE&bih=713&biw=1536#imgrc=t21H4lqDRNbv2M");
  }

  @FXML
  public void liliesClicked() {
    linkDisplay.setText(
        "https://www.google.com/search?q=lilies+bouquet&tbm=isch&ved=2ahUKEwjLhIn-_8L-AhV9Jd4AHdbkCDMQ2-cCegQIABAA&oq=lilies+bouquet&gs_lcp=CgNpbWcQAzIHCAAQigUQQzIHCAAQigUQQzIFCAAQgAQyBQgAEIAEMgYIABAFEB4yBggAEAUQHjIGCAAQBRAeMgYIABAFEB4yBggAEAUQHjIGCAAQBRAeOgQIIxAnOggIABCABBCxAzoKCAAQigUQsQMQQzoICAAQsQMQgwFQxQZYvRlg4hpoAHAAeACAAXqIAfULkgEDNy44mAEAoAEBqgELZ3dzLXdpei1pbWfAAQE&sclient=img&ei=mbZGZIvUKP3K-LYP1smjmAM&bih=713&biw=1536#imgrc=motdH6b8LgBOaM");
    browser.getEngine().load("https://www.google.com/search?q=lilies+bouquet&tbm=isch&ved=2ahUKEwjLhIn-_8L-AhV9Jd4AHdbkCDMQ2-cCegQIABAA&oq=lilies+bouquet&gs_lcp=CgNpbWcQAzIHCAAQigUQQzIHCAAQigUQQzIFCAAQgAQyBQgAEIAEMgYIABAFEB4yBggAEAUQHjIGCAAQBRAeMgYIABAFEB4yBggAEAUQHjIGCAAQBRAeOgQIIxAnOggIABCABBCxAzoKCAAQigUQsQMQQzoICAAQsQMQgwFQxQZYvRlg4hpoAHAAeACAAXqIAfULkgEDNy44mAEAoAEBqgELZ3dzLXdpei1pbWfAAQE&sclient=img&ei=mbZGZIvUKP3K-LYP1smjmAM&bih=713&biw=1536#imgrc=motdH6b8LgBOaM");
  }

  @FXML
  public void mealTitleClicked() {
    linkDisplay.setText(
        "https://www.google.com/search?q=chicken+entre+horizontal+image&tbm=isch&chips=q:chicken+entre+horizontal+image,online_chips:grilled+chicken+breast:Y3kSzUh2YOQ%3D&hl=en&sa=X&ved=2ahUKEwi3tMTS4MD-AhXbA1kFHWnYCeoQ4lYoBnoECAEQOQ&biw=1519&bih=713#imgrc=Q0tUx17Z4ieqcM");
    browser.getEngine().load("https://www.google.com/search?q=chicken+entre+horizontal+image&tbm=isch&chips=q:chicken+entre+horizontal+image,online_chips:grilled+chicken+breast:Y3kSzUh2YOQ%3D&hl=en&sa=X&ved=2ahUKEwi3tMTS4MD-AhXbA1kFHWnYCeoQ4lYoBnoECAEQOQ&biw=1519&bih=713#imgrc=Q0tUx17Z4ieqcM");
  }

  @FXML
  public void medSuppliesClicked() {
    linkDisplay.setText(
        "https://www.freepik.com/free-photo/border-from-notebook-medical-supplies_2123810.htm#query=medical%20supplies&position=49&from_view=keyword&track=robertav1_2_sidr");
    browser.getEngine().load("https://www.freepik.com/free-photo/border-from-notebook-medical-supplies_2123810.htm#query=medical%20supplies&position=49&from_view=keyword&track=robertav1_2_sidr");
  }

  @FXML
  public void notepadsClicked() {
    linkDisplay.setText(
        "https://www.google.com/search?q=Notepad+pack&tbm=isch&ved=2ahUKEwi06_CXn8P-AhUsIt4AHX71DMsQ2-cCegQIABAA&oq=Notepad+pack&gs_lcp=CgNpbWcQAzIFCAAQgAQyBggAEAgQHjIHCAAQGBCABDIHCAAQGBCABDoECCMQJzoGCAAQBxAeOggIABCABBCxAzoHCAAQigUQQzoICAAQCBAHEB5QowpY3hpgpxxoAHAAeACAAXOIAc4GkgEDNi4zmAEAoAEBqgELZ3dzLXdpei1pbWfAAQE&sclient=img&ei=UddGZPT-EazE-LYP_uqz2Aw&bih=713&biw=1536#imgrc=Ny2bwjlZJOSwUM");
    browser.getEngine().load("https://www.google.com/search?q=Notepad+pack&tbm=isch&ved=2ahUKEwi06_CXn8P-AhUsIt4AHX71DMsQ2-cCegQIABAA&oq=Notepad+pack&gs_lcp=CgNpbWcQAzIFCAAQgAQyBggAEAgQHjIHCAAQGBCABDIHCAAQGBCABDoECCMQJzoGCAAQBxAeOggIABCABBCxAzoHCAAQigUQQzoICAAQCBAHEB5QowpY3hpgpxxoAHAAeACAAXOIAc4GkgEDNi4zmAEAoAEBqgELZ3dzLXdpei1pbWfAAQE&sclient=img&ei=UddGZPT-EazE-LYP_uqz2Aw&bih=713&biw=1536#imgrc=Ny2bwjlZJOSwUM");
  }

  @FXML
  public void ostClicked() {
    linkDisplay.setText(
        "https://www.google.com/search?q=office+supplies+horizontal+image&tbm=isch&ved=2ahUKEwi525D5g8b-AhW8BFkFHVNRAwsQ2-cCegQIABAA&oq=office+supplies+horizontal+image&gs_lcp=CgNpbWcQAzIECCMQJ1AAWABguwJoAHAAeACAATKIATKSAQExmAEAqgELZ3dzLXdpei1pbWfAAQE&sclient=img&ei=aE1IZLmHELyJ5NoP06KNWA&bih=975&biw=1920");
    browser.getEngine().load("https://www.google.com/search?q=office+supplies+horizontal+image&tbm=isch&ved=2ahUKEwi525D5g8b-AhW8BFkFHVNRAwsQ2-cCegQIABAA&oq=office+supplies+horizontal+image&gs_lcp=CgNpbWcQAzIECCMQJ1AAWABguwJoAHAAeACAATKIATKSAQExmAEAqgELZ3dzLXdpei1pbWfAAQE&sclient=img&ei=aE1IZLmHELyJ5NoP06KNWA&bih=975&biw=1920");
  }

  @FXML
  public void onionRingsClicked() {
    linkDisplay.setText(
        "https://www.google.com/search?q=onion+rings+jpg&tbm=isch&ved=2ahUKEwizuZuAjMP-AhXOKN4AHdSMDZEQ2-cCegQIABAA&oq=onion+rings+jpg&gs_lcp=CgNpbWcQAzIFCAAQgAQ6BAgjECc6BggAEAcQHjoGCAAQCBAeOggIABAIEAcQHjoHCAAQGBCABDoICAAQBRAHEB46BwgAEIoFEEM6BggAEAUQHlDqBljLFmCbGGgAcAB4AIABaIgBngiSAQQxMS4xmAEAoAEBqgELZ3dzLXdpei1pbWfAAQE&sclient=img&ei=M8NGZPPTF87R-LYP1Jm2iAk&bih=713&biw=1536#imgrc=QWusqkyYLeiNsM");
    browser.getEngine().load("https://www.google.com/search?q=onion+rings+jpg&tbm=isch&ved=2ahUKEwizuZuAjMP-AhXOKN4AHdSMDZEQ2-cCegQIABAA&oq=onion+rings+jpg&gs_lcp=CgNpbWcQAzIFCAAQgAQ6BAgjECc6BggAEAcQHjoGCAAQCBAeOggIABAIEAcQHjoHCAAQGBCABDoICAAQBRAHEB46BwgAEIoFEEM6BggAEAUQHlDqBljLFmCbGGgAcAB4AIABaIgBngiSAQQxMS4xmAEAoAEBqgELZ3dzLXdpei1pbWfAAQE&sclient=img&ei=M8NGZPPTF87R-LYP1Jm2iAk&bih=713&biw=1536#imgrc=QWusqkyYLeiNsM");
  }

  @FXML
  public void pensClicked() {
    linkDisplay.setText(
        "https://www.google.com/search?q=pen+pack&tbm=isch&ved=2ahUKEwj8sbnonsP-AhVQPd4AHTViB3cQ2-cCegQIABAA&oq=pen+pack&gs_lcp=CgNpbWcQAzIHCAAQigUQQzIFCAAQgAQyBQgAEIAEMgUIABCABDIFCAAQgAQyBQgAEIAEMgUIABCABDIFCAAQgAQyBQgAEIAEMgUIABCABDoHCCMQ6gIQJzoECCMQJzoICAAQgAQQsQM6CggAEIoFELEDEENQAFiMJWDYKGgBcAB4AIABdYgB7gWSAQM1LjOYAQCgAQGqAQtnd3Mtd2l6LWltZ7ABCsABAQ&sclient=img&ei=7dZGZLyHMtD6-LYPtcSduAc&bih=713&biw=1536#imgrc=ue6y6BN7_xDmbM");
    browser.getEngine().load("https://www.google.com/search?q=pen+pack&tbm=isch&ved=2ahUKEwj8sbnonsP-AhVQPd4AHTViB3cQ2-cCegQIABAA&oq=pen+pack&gs_lcp=CgNpbWcQAzIHCAAQigUQQzIFCAAQgAQyBQgAEIAEMgUIABCABDIFCAAQgAQyBQgAEIAEMgUIABCABDIFCAAQgAQyBQgAEIAEMgUIABCABDoHCCMQ6gIQJzoECCMQJzoICAAQgAQQsQM6CggAEIoFELEDEENQAFiMJWDYKGgBcAB4AIABdYgB7gWSAQM1LjOYAQCgAQGqAQtnd3Mtd2l6LWltZ7ABCsABAQ&sclient=img&ei=7dZGZLyHMtD6-LYPtcSduAc&bih=713&biw=1536#imgrc=ue6y6BN7_xDmbM");
  }

  @FXML
  public void pencilsClicked() {
    linkDisplay.setText(
        "https://www.google.com/search?q=pencil+pack+&tbm=isch&ved=2ahUKEwiwkNrlnsP-AhUjwMkDHaVqD5wQ2-cCegQIABAA&oq=pencil+pack+&gs_lcp=CgNpbWcQAzIFCAAQgAQyBQgAEIAEMgUIABCABDIFCAAQgAQyBQgAEIAEMgUIABCABDIFCAAQgAQyBQgAEIAEMgUIABCABDIFCAAQgAQ6BAgjECc6CggAEIoFELEDEEM6BwgAEIoFEEM6CAgAEIAEELEDULECWM8HYOEIaABwAHgAgAH0AYgB_gWSAQUzLjIuMZgBAKABAaoBC2d3cy13aXotaW1nwAEB&sclient=img&ei=6NZGZLD8A6OAp84PpdW94Ak&bih=713&biw=1536#imgrc=CIHJXxfNpkxxXM");
    browser.getEngine().load("https://www.google.com/search?q=pencil+pack+&tbm=isch&ved=2ahUKEwiwkNrlnsP-AhUjwMkDHaVqD5wQ2-cCegQIABAA&oq=pencil+pack+&gs_lcp=CgNpbWcQAzIFCAAQgAQyBQgAEIAEMgUIABCABDIFCAAQgAQyBQgAEIAEMgUIABCABDIFCAAQgAQyBQgAEIAEMgUIABCABDIFCAAQgAQ6BAgjECc6CggAEIoFELEDEEM6BwgAEIoFEEM6CAgAEIAEELEDULECWM8HYOEIaABwAHgAgAH0AYgB_gWSAQUzLjIuMZgBAKABAaoBC2d3cy13aXotaW1nwAEB&sclient=img&ei=6NZGZLD8A6OAp84PpdW94Ak&bih=713&biw=1536#imgrc=CIHJXxfNpkxxXM");
  }

  @FXML
  public void porkClicked() {
    linkDisplay.setText(
        "https://www.google.com/search?q=pork+entree+jpg&tbm=isch&ved=2ahUKEwis1L3bisP-AhUdNN4AHSE5C6UQ2-cCegQIABAA&oq=pork+entree+jpg&gs_lcp=CgNpbWcQA1CiBViiCGDZCmgAcAB4AIABkwGIAbEEkgEDMS40mAEAoAEBqgELZ3dzLXdpei1pbWfAAQE&sclient=img&ei=2cFGZOyDOJ3o-LYPofKsqAo&bih=713&biw=1536#imgrc=Ijaejd4IpT7GjM");
    browser.getEngine().load("https://www.google.com/search?q=pork+entree+jpg&tbm=isch&ved=2ahUKEwis1L3bisP-AhUdNN4AHSE5C6UQ2-cCegQIABAA&oq=pork+entree+jpg&gs_lcp=CgNpbWcQA1CiBViiCGDZCmgAcAB4AIABkwGIAbEEkgEDMS40mAEAoAEBqgELZ3dzLXdpei1pbWfAAQE&sclient=img&ei=2cFGZOyDOJ3o-LYPofKsqAo&bih=713&biw=1536#imgrc=Ijaejd4IpT7GjM");
  }

  @FXML
  public void rosesClicked() {
    linkDisplay.setText(
        "https://www.google.com/search?q=roses+bouquet+images&tbm=isch&ved=2ahUKEwi3vOqo_sL-AhU1LN4AHZO0BEwQ2-cCegQIABAA&oq=roses+bouquet+images&gs_lcp=CgNpbWcQAzIFCAAQgAQyBQgAEIAEMgUIABCABDIGCAAQBRAeMgYIABAFEB4yBggAEAUQHjIGCAAQBRAeMgYIABAFEB4yBggAEAUQHjIGCAAQBRAeOgQIIxAnOgcIABCKBRBDUL4CWPQPYKwRaABwAHgAgAF-iAHRBpIBAzMuNZgBAKABAaoBC2d3cy13aXotaW1nwAEB&sclient=img&ei=2rRGZLfnHLXY-LYPk-mS4AQ&bih=713&biw=1536#imgrc=2ogFD_1TmBu7zM");
    browser.getEngine().load("https://www.google.com/search?q=roses+bouquet+images&tbm=isch&ved=2ahUKEwi3vOqo_sL-AhU1LN4AHZO0BEwQ2-cCegQIABAA&oq=roses+bouquet+images&gs_lcp=CgNpbWcQAzIFCAAQgAQyBQgAEIAEMgUIABCABDIGCAAQBRAeMgYIABAFEB4yBggAEAUQHjIGCAAQBRAeMgYIABAFEB4yBggAEAUQHjIGCAAQBRAeOgQIIxAnOgcIABCKBRBDUL4CWPQPYKwRaABwAHgAgAF-iAHRBpIBAzMuNZgBAKABAaoBC2d3cy13aXotaW1nwAEB&sclient=img&ei=2rRGZLfnHLXY-LYPk-mS4AQ&bih=713&biw=1536#imgrc=2ogFD_1TmBu7zM");
  }

  @FXML
  public void saladClicked() {
    linkDisplay.setText(
        "https://www.google.com/search?q=salad+jpg&tbm=isch&ved=2ahUKEwjopZGYjMP-AhWyzMkDHe2EC8IQ2-cCegQIABAA&oq=salad+jpg&gs_lcp=CgNpbWcQAzIHCAAQigUQQzIGCAAQBRAeMgYIABAHEB4yCAgAEAUQBxAeOgQIIxAnUIAFWNAOYPcQaAFwAHgAgAGJAYgB7gWSAQMzLjSYAQCgAQGqAQtnd3Mtd2l6LWltZ8ABAQ&sclient=img&ei=ZcNGZKjfIbKZp84P7YmukAw&bih=713&biw=1536#imgrc=q_fNFqdkB4bHAM");
    browser.getEngine().load("https://www.google.com/search?q=salad+jpg&tbm=isch&ved=2ahUKEwjopZGYjMP-AhWyzMkDHe2EC8IQ2-cCegQIABAA&oq=salad+jpg&gs_lcp=CgNpbWcQAzIHCAAQigUQQzIGCAAQBRAeMgYIABAHEB4yCAgAEAUQBxAeOgQIIxAnUIAFWNAOYPcQaAFwAHgAgAGJAYgB7gWSAQMzLjSYAQCgAQGqAQtnd3Mtd2l6LWltZ8ABAQ&sclient=img&ei=ZcNGZKjfIbKZp84P7YmukAw&bih=713&biw=1536#imgrc=q_fNFqdkB4bHAM");
  }

  @FXML
  public void soupClicked() {
    linkDisplay.setText(
        "https://www.google.com/search?q=soup+jpg&tbm=isch&ved=2ahUKEwiryOOIjMP-AhXlL94AHfw1CX0Q2-cCegQIABAA&oq=soup+jpg&gs_lcp=CgNpbWcQAzIFCAAQgAQ6BAgjECc6BwgAEIoFEEM6BggAEAcQHlDCBliMDmD9D2gBcAB4AIABcYgB8gSSAQMyLjSYAQCgAQGqAQtnd3Mtd2l6LWltZ8ABAQ&sclient=img&ei=RcNGZOuRFeXf-LYP_Ouk6Ac&bih=713&biw=1536#imgrc=Yia6Lv4j7k_JZM");
    browser.getEngine().load("https://www.google.com/search?q=soup+jpg&tbm=isch&ved=2ahUKEwiryOOIjMP-AhXlL94AHfw1CX0Q2-cCegQIABAA&oq=soup+jpg&gs_lcp=CgNpbWcQAzIFCAAQgAQ6BAgjECc6BwgAEIoFEEM6BggAEAcQHlDCBliMDmD9D2gBcAB4AIABcYgB8gSSAQMyLjSYAQCgAQGqAQtnd3Mtd2l6LWltZ8ABAQ&sclient=img&ei=RcNGZOuRFeXf-LYP_Ouk6Ac&bih=713&biw=1536#imgrc=Yia6Lv4j7k_JZM");
  }

  @FXML
  public void steakClicked() {
    linkDisplay.setText(
        "https://www.google.com/search?q=steak+entree+jpg&tbm=isch&ved=2ahUKEwjvgbPHisP-AhVDNN4AHfdmAR8Q2-cCegQIABAA&oq=steak+entree+jpg&gs_lcp=CgNpbWcQAzoECCMQJ1DEAlj7DmCnEWgAcAB4AIABbIgBlwaSAQM4LjGYAQCgAQGqAQtnd3Mtd2l6LWltZ8ABAQ&sclient=img&ei=r8FGZK_uMMPo-LYP982F-AE&bih=713&biw=1536#imgrc=bTN4FdOrtFdz2M");
    browser.getEngine().load("https://www.google.com/search?q=steak+entree+jpg&tbm=isch&ved=2ahUKEwjvgbPHisP-AhVDNN4AHfdmAR8Q2-cCegQIABAA&oq=steak+entree+jpg&gs_lcp=CgNpbWcQAzoECCMQJ1DEAlj7DmCnEWgAcAB4AIABbIgBlwaSAQM4LjGYAQCgAQGqAQtnd3Mtd2l6LWltZ8ABAQ&sclient=img&ei=r8FGZK_uMMPo-LYP982F-AE&bih=713&biw=1536#imgrc=bTN4FdOrtFdz2M");
  }

  @FXML
  public void sunflowersClicked() {
    linkDisplay.setText(
        "https://www.google.com/search?q=sunflowers+bouquet+images&tbm=isch&ved=2ahUKEwitvaSh_8L-AhV7H94AHcrxCIMQ2-cCegQIABAA&oq=sunflowers+bouquet+images&gs_lcp=CgNpbWcQAzIFCAAQgAQ6BAgjECc6BggAEAcQHjoGCAAQCBAeOggIABAFEAcQHlCYBljJEGDBEmgAcAB4AIABcIgBiAmSAQM3LjWYAQCgAQGqAQtnd3Mtd2l6LWltZ8ABAQ&sclient=img&ei=17VGZO31BPu--LYPyuOjmAg&bih=713&biw=1536#imgrc=FzXwzJjlM21NcM");
    browser.getEngine().load("https://www.google.com/search?q=sunflowers+bouquet+images&tbm=isch&ved=2ahUKEwitvaSh_8L-AhV7H94AHcrxCIMQ2-cCegQIABAA&oq=sunflowers+bouquet+images&gs_lcp=CgNpbWcQAzIFCAAQgAQ6BAgjECc6BggAEAcQHjoGCAAQCBAeOggIABAFEAcQHlCYBljJEGDBEmgAcAB4AIABcIgBiAmSAQM3LjWYAQCgAQGqAQtnd3Mtd2l6LWltZ8ABAQ&sclient=img&ei=17VGZO31BPu--LYPyuOjmAg&bih=713&biw=1536#imgrc=FzXwzJjlM21NcM");
  }

  @FXML
  public void syringeClicked() {
    linkDisplay.setText(
        "https://www.google.com/search?q=sterile+syringe+jpg&tbm=isch&ved=2ahUKEwiNmIGXlsP-AhXjwckDHXGSAikQ2-cCegQIABAA&oq=sterile+syringe+jpg&gs_lcp=CgNpbWcQAzoECCMQJ1C5BFjEEmDzF2gBcAB4AIABWYgBzASSAQE3mAEAoAEBqgELZ3dzLXdpei1pbWfAAQE&sclient=img&ei=381GZM2sIeODp84P8aSKyAI&bih=713&biw=1536#imgrc=lEOu_N8PPxPboM");
    browser.getEngine().load("https://www.google.com/search?q=sterile+syringe+jpg&tbm=isch&ved=2ahUKEwiNmIGXlsP-AhXjwckDHXGSAikQ2-cCegQIABAA&oq=sterile+syringe+jpg&gs_lcp=CgNpbWcQAzoECCMQJ1C5BFjEEmDzF2gBcAB4AIABWYgBzASSAQE3mAEAoAEBqgELZ3dzLXdpei1pbWfAAQE&sclient=img&ei=381GZM2sIeODp84P8aSKyAI&bih=713&biw=1536#imgrc=lEOu_N8PPxPboM");
  }

  @FXML
  public void teaClicked() {
    linkDisplay.setText(
        "https://www.google.com/search?q=tea+jpg&rlz=1C1RXQR_enUS963US963&oq=tea+&aqs=chrome.0.69i59j69i57j0i67i650j69i65j69i60l4.1758j0j7&sourceid=chrome&ie=UTF-8#imgrc=8KhRxx8GZxbYfM");
    browser.getEngine().load("https://www.google.com/search?q=tea+jpg&rlz=1C1RXQR_enUS963US963&oq=tea+&aqs=chrome.0.69i59j69i57j0i67i650j69i65j69i60l4.1758j0j7&sourceid=chrome&ie=UTF-8#imgrc=8KhRxx8GZxbYfM");
  }

  @FXML
  public void tulipsClicked() {
    linkDisplay.setText(
        "https://www.google.com/search?q=tulips+bouquet+images&tbm=isch&ved=2ahUKEwjYrvSS_8L-AhXGPN4AHZgCAgwQ2-cCegQIABAA&oq=tulips+bouquet+images&gs_lcp=CgNpbWcQAzIFCAAQgAQyBQgAEIAEMgYIABAFEB46BAgjECc6DQgAEIoFELEDEIMBEEM6BwgAEIoFEEM6BwgAEBgQgAQ6BggAEAgQHlDKAliTCGDBCmgAcAB4AIABeYgBtwaSAQM1LjOYAQCgAQGqAQtnd3Mtd2l6LWltZ8ABAQ&sclient=img&ei=uLVGZNjyOMb5-LYPmIWIYA&bih=713&biw=1536#imgrc=BzezzEtF95kJJM");
    browser.getEngine().load("https://www.google.com/search?q=tulips+bouquet+images&tbm=isch&ved=2ahUKEwjYrvSS_8L-AhXGPN4AHZgCAgwQ2-cCegQIABAA&oq=tulips+bouquet+images&gs_lcp=CgNpbWcQAzIFCAAQgAQyBQgAEIAEMgYIABAFEB46BAgjECc6DQgAEIoFELEDEIMBEEM6BwgAEIoFEEM6BwgAEBgQgAQ6BggAEAgQHlDKAliTCGDBCmgAcAB4AIABeYgBtwaSAQM1LjOYAQCgAQGqAQtnd3Mtd2l6LWltZ8ABAQ&sclient=img&ei=uLVGZNjyOMb5-LYPmIWIYA&bih=713&biw=1536#imgrc=BzezzEtF95kJJM");
  }

  @FXML
  public void waterClicked() {
    linkDisplay.setText(
        "https://www.google.com/search?q=dasaani+water+bottle+jpg&tbm=isch&ved=2ahUKEwirtLCUicP-AhVyE94AHX4UDkYQ2-cCegQIABAA&oq=dasaani+water+bottle+jpg&gs_lcp=CgNpbWcQAzoECCMQJzoFCAAQgARQ1gJY1w5gyRBoAHAAeACAAXWIAdgGkgEDNi4zmAEAoAEBqgELZ3dzLXdpei1pbWfAAQE&sclient=img&ei=OMBGZKu4FvKm-LYP_qi4sAQ&bih=713&biw=1536#imgrc=iSYapFIdvYR9GM");
    browser.getEngine().load("https://www.google.com/search?q=dasaani+water+bottle+jpg&tbm=isch&ved=2ahUKEwirtLCUicP-AhVyE94AHX4UDkYQ2-cCegQIABAA&oq=dasaani+water+bottle+jpg&gs_lcp=CgNpbWcQAzoECCMQJzoFCAAQgARQ1gJY1w5gyRBoAHAAeACAAXWIAdgGkgEDNi4zmAEAoAEBqgELZ3dzLXdpei1pbWfAAQE&sclient=img&ei=OMBGZKu4FvKm-LYP_qi4sAQ&bih=713&biw=1536#imgrc=iSYapFIdvYR9GM");
  }

  @FXML
  public void vegetablesClicked() {
    linkDisplay.setText(
        "https://www.google.com/search?q=vegetarian+entree+jpg&tbm=isch&ved=2ahUKEwjAr9nwisP-AhUlE94AHfGoDHMQ2-cCegQIABAA&oq=vegetarian+entree+jpg&gs_lcp=CgNpbWcQA1D7HVi3NmDKOGgBcAB4AIABc4gB_wiSAQQxMC4zmAEAoAEBqgELZ3dzLXdpei1pbWfAAQE&sclient=img&ei=BsJGZMCUGaWm-LYP8dGymAc&bih=713&biw=1536#imgrc=v5yKEUPEmfPuuM");
    browser.getEngine().load("https://www.google.com/search?q=vegetarian+entree+jpg&tbm=isch&ved=2ahUKEwjAr9nwisP-AhUlE94AHfGoDHMQ2-cCegQIABAA&oq=vegetarian+entree+jpg&gs_lcp=CgNpbWcQA1D7HVi3NmDKOGgBcAB4AIABc4gB_wiSAQQxMC4zmAEAoAEBqgELZ3dzLXdpei1pbWfAAQE&sclient=img&ei=BsJGZMCUGaWm-LYP8dGymAc&bih=713&biw=1536#imgrc=v5yKEUPEmfPuuM");
  }

  // software
  @FXML
  public void figmaClicked() {
    linkDisplay.setText("https://www.figma.com/");
    browser.getEngine().load("https://www.figma.com/");
  }

  @FXML
  public void githubClicked() {
    linkDisplay.setText("https://github.com/");
    browser.getEngine().load("https://github.com/");
  }

  @FXML
  public void intellijClicked() {
    linkDisplay.setText("https://www.jetbrains.com/idea/");
    browser.getEngine().load("https://www.jetbrains.com/idea/");
  }

  @FXML
  public void jiraClicked() {
    linkDisplay.setText("https://www.atlassian.com/software/jira");
    browser.getEngine().load("https://www.atlassian.com/software/jira");
  }

  @FXML
  public void postgresqlClicked() {
    linkDisplay.setText("https://www.postgresql.org/");
    browser.getEngine().load("https://www.postgresql.org/");
  }

  @FXML
  public void sbuilderClicked() {
    linkDisplay.setText("https://gluonhq.com/products/scene-builder/");
    browser.getEngine().load("https://gluonhq.com/products/scene-builder/");
  }

  @FXML
  public void slackClicked() {
    linkDisplay.setText("https://slack.com/");
    browser.getEngine().load("https://slack.com/");
  }
}
