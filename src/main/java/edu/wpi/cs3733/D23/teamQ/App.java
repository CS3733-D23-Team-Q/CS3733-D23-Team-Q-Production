package edu.wpi.cs3733.D23.teamQ;

import edu.wpi.cs3733.D23.teamQ.navigation.Navigation;
import edu.wpi.cs3733.D23.teamQ.navigation.Screen;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App extends Application {

  @Setter @Getter private static Stage primaryStage;
  @Setter @Getter private static AnchorPane rootPane;

  @Override
  public void init() {
    log.info("Starting Up");
  }

  @Override
  public void start(Stage primaryStage) throws IOException {
    /* primaryStage is generally only used if one of your components require the stage to display */
    App.primaryStage = primaryStage;
    primaryStage.setTitle("Login Page");

    final FXMLLoader loader = new FXMLLoader(App.class.getResource("views/Root.fxml"));
    final AnchorPane root = loader.load();

    App.rootPane = root;
    primaryStage.setFullScreenExitKeyCombination(KeyCombination.keyCombination("F5"));
    final Scene scene = new Scene(root);
    primaryStage.setScene(scene);
    scene
        .getStylesheets()
        .add(
            getClass()
                .getResource("/edu/wpi/cs3733/D23/teamQ/views/styles/Home.css")
                .toExternalForm());
    primaryStage.show();
    Rectangle2D screenBounds = javafx.stage.Screen.getPrimary().getVisualBounds();
    primaryStage.setX(screenBounds.getMinX());
    primaryStage.setY(screenBounds.getMinY());
    primaryStage.setWidth(screenBounds.getWidth());
    primaryStage.setHeight(screenBounds.getHeight());
    Navigation.navigate(Screen.LOGIN);
  }

  @Override
  public void stop() {
    log.info("Shutting Down");
  }
}
