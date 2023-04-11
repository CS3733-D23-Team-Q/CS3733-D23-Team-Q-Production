package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.SecondaryStage;
import edu.wpi.cs3733.D23.teamQ.navigation.Screen;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ProgressBarController extends SecondaryStage {
  static Stage stage;
  @FXML VBox root;
  Label label;
  ProgressBar loading;

  public static void display() throws IOException {
    stage = newStage(Screen.PROGRESS_BAR);
    stage.initStyle(StageStyle.TRANSPARENT);
    stage.show();
    stage.centerOnScreen();
  }

  @FXML
  public void initialize() {
    label = new Label("Generating path...");
    // label.setStyle("-fx-background-color: transparent;");
    loading = new ProgressBar();
    // loading.setStyle("-fx-background-color: transparent;");
    // root.setStyle("-fx-background-color: transparent;");
    root.getChildren().add(label);
    root.getChildren().add(loading);
    // startProgress();
    /*
    task.setOnSucceeded(
        e -> {
          if (loading.getProgress() >= 1) {
            stage.close();
          }
        });
     */
  }

  public static Stage getStage() {
    return stage;
  }

  public static void setStage(Stage stage) {
    ProgressBarController.stage = stage;
  }

  /*
  Task<Void> task =
      new Task<Void>() {
        protected Void call() throws Exception {
          for (int i = 0; i < 100; i++) {
            updateProgress(i, 99);
            Thread.sleep(50);
          }
          return null;
        }
      };
   */

  /*
  public void startProgress() {
    loading.progressProperty().bind(task.progressProperty());
    root.getChildren().add(label);
    root.getChildren().add(loading);
    new Thread(task).start();
  }
   */
}
