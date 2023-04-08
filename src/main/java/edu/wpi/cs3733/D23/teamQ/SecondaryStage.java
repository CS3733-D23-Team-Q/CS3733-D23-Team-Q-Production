package edu.wpi.cs3733.D23.teamQ;

import edu.wpi.cs3733.D23.teamQ.navigation.Navigation;
import edu.wpi.cs3733.D23.teamQ.navigation.Screen;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SecondaryStage {
  protected static Stage stage;

  public static void display(final Screen screen) throws IOException {
    final String filename = screen.getFilename();
    final String title = screen.getTitle();

    stage = new Stage();
    stage.initModality(Modality.APPLICATION_MODAL);
    stage.setTitle(title);

    final FXMLLoader loader = new FXMLLoader(App.class.getResource(filename));
    final VBox root = loader.load();
    final Scene scene = new Scene(root);

    scene
        .getStylesheets()
        .add(
            Navigation.class
                .getResource("/edu/wpi/cs3733/D23/teamQ/views/styles/Home.css")
                .toExternalForm());

    stage.setScene(scene);
    stage.show();
  }
}
