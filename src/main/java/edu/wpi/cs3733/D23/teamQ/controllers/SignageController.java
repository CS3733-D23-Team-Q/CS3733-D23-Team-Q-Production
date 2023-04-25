package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.App;
import edu.wpi.cs3733.D23.teamQ.db.Qdb;
import edu.wpi.cs3733.D23.teamQ.db.obj.Sign;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class SignageController implements IController {

  @FXML VBox signageVbox;

  @FXML
  public void initialize() {
    Qdb qdb = Qdb.getInstance();

    if (!signageVbox.getChildren().isEmpty()) {
      signageVbox.getChildren().clear();
    }

    for (Sign s : qdb.retrieveSigns(qdb.getKiosk(), qdb.getDate())) {
      System.out.println(s.getDestination());
      HBox hbox = new HBox();
      hbox.setAlignment(Pos.CENTER);
      hbox.setPadding(new Insets(4, 16, 4, 640));

      Text text = new Text(s.getDestination());
      TextFlow textFlow = new TextFlow(text);
      textFlow.setStyle(
          "-fx-background-color: #0167B1; -fx-background-radius: 10px; -fx-padding: 12 20 12 20;");
      text.setStyle("-fx-font-family: Roboto");
      text.setFill(Color.WHITE);
      text.setFont(Font.font(40));
      hbox.getChildren().add(textFlow);

      ImageView dir =
          new ImageView(
              new Image(App.class.getResourceAsStream("edu/wpi/cs3733/D23/teamQ/rightArrow.png")));
      if (s.getDirection().equals("Right")) dir.setRotate(0);
      if (s.getDirection().equals("Down")) dir.setRotate(90);
      if (s.getDirection().equals("Left")) dir.setRotate(180);
      if (s.getDirection().equals("Up")) dir.setRotate(270);
      hbox.getChildren().add(dir);
      signageVbox.getChildren().add(hbox);
    }
  }
}
