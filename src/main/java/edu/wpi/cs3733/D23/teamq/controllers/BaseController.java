package edu.wpi.cs3733.D23.teamq.controllers;

import edu.wpi.cs3733.D23.teamq.navigation.Navigation;
import edu.wpi.cs3733.D23.teamq.navigation.Screen;
import javafx.application.Platform;
import javafx.fxml.FXML;

import java.awt.*;

public class BaseController {
    @FXML
    MenuItem homeItem;
    @FXML MenuItem exitItem;

    @FXML MenuItem logoutItem;
    @FXML MenuItem profileItem;

    @FXML
    public void initialize() {}

    @FXML
    public void homeItemClicked() {
        Navigation.navigate(Screen.HOME);
    }

    @FXML
    public void exitItemClicked() {
        Platform.exit();
    }

    @FXML
    public void logoutItemClicked() {
        Navigation.navigate(Screen.HOME);
    }

    @FXML
    public void profileItemClicked() {
        Navigation.navigate(Screen.HOME);
    }
}
