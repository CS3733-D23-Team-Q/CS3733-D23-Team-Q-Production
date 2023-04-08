package edu.wpi.cs3733.D23.teamQ;

import java.io.IOException;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Alert extends SecondaryStage {

  public void setLabelAlert(String message, Label type, ImageView alertImage) {
    type.setText(message);
    type.setStyle("-fx-text-fill: #AA3A47;");
    alertImage.setOpacity(1);
  }

  public void clearLabelAlert(Label type, ImageView alertImage) {
    type.setText("");
    type.setStyle(null);
    alertImage.setOpacity(0);
  }

  // create a fxml to modify its UI and make it to be consistent with the SecondaryStage super class
  public static void alertBox(String title, String message) throws IOException {
    Stage secondaryStage = new Stage();
    secondaryStage.initModality(Modality.APPLICATION_MODAL);
    secondaryStage.setTitle(title);
    Label label = new Label();
    label.setText(message);
    label.setStyle("-fx-text-fill: #AA3A47;");
    Button okButton = new Button("Ok");
    okButton.setOnAction(e -> secondaryStage.close());
    VBox layout = new VBox(5);
    layout.getChildren().addAll(label, okButton);
    layout.setAlignment(Pos.CENTER);
    Scene scene = new Scene(layout, 400, 200);
    secondaryStage.setScene(scene);
    secondaryStage.show();
  }
}
