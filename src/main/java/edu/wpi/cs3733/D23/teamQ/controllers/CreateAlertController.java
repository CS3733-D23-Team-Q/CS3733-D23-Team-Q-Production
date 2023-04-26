package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.db.Qdb;
import edu.wpi.cs3733.D23.teamQ.db.obj.Alert;
import edu.wpi.cs3733.D23.teamQ.navigation.Navigation;
import edu.wpi.cs3733.D23.teamQ.navigation.Screen;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.fxml.FXML;
import lombok.extern.java.Log;

public class CreateAlertController {
    Qdb qdb = Qdb.getInstance();
    @FXML
    MFXButton resetButton;
    @FXML
    MFXButton cancelButton;
    @FXML
    MFXButton submitButton;
    @FXML
    MFXTextField input;

    @FXML
    public void initialize() {}

    @FXML
    public void resetButtonClicked() {
        input.clear();
    }

    @FXML
    public void cancelButtonClicked() {
        Navigation.navigate(Screen.HOME);
    }

    @FXML
    public void submitButtonClicked() {
        Alert a = new Alert(System.currentTimeMillis(), input.getText());
        qdb.addAlert(a);
    }
}
