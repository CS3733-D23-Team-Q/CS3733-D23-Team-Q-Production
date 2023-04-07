package edu.wpi.cs3733.D23.teamQ;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Confirm {
  public Scene getScene(Stage stage, String title, String message) {
    stage.setTitle(title);

    Label label = new Label();
    label.setText(message);
    label.setStyle("-fx-font-weight: bold");
    Button okButton = new Button("Ok");
    okButton.setOnAction(e -> stage.close());

    VBox layout = new VBox(5);
    layout.getChildren().addAll(label, okButton);
    layout.setAlignment(Pos.CENTER);

    Scene scene = new Scene(layout, 400, 300);
    return scene;
  }
}
