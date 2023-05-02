package edu.wpi.cs3733.D23.teamQ;

import edu.wpi.cs3733.D23.teamQ.controllers.RootController;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App extends Application {

  @Getter private static Stage primaryStage;
  @Getter private static AnchorPane rootCenter;
  @Getter private static RootController rController;
  @Getter private static BorderPane rootBorder;
  @Getter private static AnchorPane rootRight;

  @Override
  public void init() {
    log.info("Starting Up");
  }

  public static void setPrimaryStage(Stage primaryStage) {
    App.primaryStage = primaryStage;
  }

  public static void setRootCenter(AnchorPane rootCenter) {
    App.rootCenter = rootCenter;
  }

  public static void setrController(RootController rController) {
    App.rController = rController;
  }

  public static void setRootBorder(BorderPane rootBorder) {
    App.rootBorder = rootBorder;
  }

  public static void setRootRight(AnchorPane rootRight) {
    App.rootRight = rootRight;
  }

  @Override
  public void start(Stage primaryStage) throws IOException {
    App.primaryStage = primaryStage;
    primaryStage.setTitle("Splash Screen");

    final FXMLLoader loader = new FXMLLoader(App.class.getResource("views/SplashScreen.fxml"));
    primaryStage.setFullScreenExitKeyCombination(KeyCombination.keyCombination("F5"));
    final Scene scene = new Scene(loader.load());
    primaryStage.setScene(scene);
    scene
        .getStylesheets()
        .add(
            getClass()
                .getResource("/edu/wpi/cs3733/D23/teamQ/views/styles/Home.css")
                .toExternalForm());

    primaryStage.setFullScreen(false);

    primaryStage.show();
  }

  @Override
  public void stop() {
    log.info("Shutting Down");
  }
}
