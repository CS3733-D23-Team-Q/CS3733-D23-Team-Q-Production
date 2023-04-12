package edu.wpi.cs3733.D23.teamQ;

import edu.wpi.cs3733.D23.teamQ.controllers.ConfirmController;
import edu.wpi.cs3733.D23.teamQ.navigation.Navigation;
import edu.wpi.cs3733.D23.teamQ.navigation.Screen;
import java.io.IOException;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Confirm extends SecondaryStage {
  public void setScene(Stage stage, String title, String message) throws IOException {
    ConfirmController cc = (ConfirmController) Navigation.getController(Screen.CONFIRM);
    cc.setMessage(message);
    cc.setStage(stage);
    Stage sstage = newStage(title, Screen.CONFIRM);
    Scene scene = sstage.getScene();
    stage.setScene(scene);
    stage.show();
    stage.centerOnScreen();
  }

  public static void confirmBox(String title, String message) throws IOException {
    ConfirmController cc = (ConfirmController) Navigation.getController(Screen.CONFIRM);
    cc.setMessage(message);
    Stage stage = newStage(title, Screen.CONFIRM);
    cc.setStage(stage);
    stage.show();
    stage.centerOnScreen();
  }
}
