package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.navigation.Navigation;
import edu.wpi.cs3733.D23.teamQ.navigation.Screen;
import javafx.fxml.FXML;


@FXML Button homeButton;

public class SettingsController {

    @FXML
    public void homeItemClicked() {
        Navigation.navigate(Screen.HOME);
    }

}
