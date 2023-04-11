package edu.wpi.cs3733.D23.teamQ.navigation;

import edu.wpi.cs3733.D23.teamQ.App;
import edu.wpi.cs3733.D23.teamQ.controllers.IController;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Navigation {

  public static void navigate(final Screen screen) {
    final String filename = screen.getFilename();
    final String menuname = Screen.MENU_PANE.getFilename();

    try {
      final var resource = App.class.getResource(filename);
      final FXMLLoader loader = new FXMLLoader(resource);
      final var resource2 = App.class.getResource(menuname);
      final FXMLLoader loader2 = new FXMLLoader(resource2);

      //      Stage primaryStage = App.getPrimaryStage();
      //      final Scene scene = new Scene(loader.load());
      //      scene
      //          .getStylesheets()
      //          .add(
      //              Navigation.class
      //                  .getResource("/edu/wpi/cs3733/D23/teamQ/views/styles/Home.css")
      //                  .toExternalForm());
      Node n = loader.load();
      Node n2 = loader2.load();
      App.getRootPane().getChildren().clear();
      App.getRootPane().getChildren().addAll(n, n2);
      AnchorPane.setTopAnchor(n, 0.0);
      AnchorPane.setLeftAnchor(n, 0.0);
      AnchorPane.setRightAnchor(n, 0.0);
      AnchorPane.setBottomAnchor(n, 0.0);
    } catch (IOException | NullPointerException e) {
      e.printStackTrace();
    }
  }

  public static void logout() {
    Screen screen = Screen.LOGIN;
    final String filename = screen.getFilename();

    try {
      final var resource = App.class.getResource(filename);
      final FXMLLoader loader = new FXMLLoader(resource);
      Parent n = loader.load();
      App.getRootPane().getChildren().clear();
      App.getRootPane().getChildren().add(n);
      AnchorPane.setTopAnchor(n, 0.0);
      AnchorPane.setLeftAnchor(n, 0.0);
      AnchorPane.setRightAnchor(n, 0.0);
      AnchorPane.setBottomAnchor(n, 0.0);
      Stage primaryStage = App.getPrimaryStage();
      primaryStage.setTitle(title);
      Scene scene = primaryStage.getScene();
      scene
          .getStylesheets()
          .add(
              Navigation.class
                  .getResource("/edu/wpi/cs3733/D23/teamQ/views/styles/Home.css")
                  .toExternalForm());
      /*
      final Scene scene = new Scene(loader.load());
      scene
          .getStylesheets()
          .add(
              Navigation.class
                  .getResource("/edu/wpi/cs3733/D23/teamQ/views/styles/Home.css")
                  .toExternalForm());
       */
    } catch (IOException | NullPointerException e) {
      e.printStackTrace();
    }
  }

  public static IController getController(final Screen screen) throws IOException {
    final String filename = screen.getFilename();
    final var loader = new FXMLLoader(App.class.getResource(filename));
    final Parent layout = loader.load();
    final IController controller = loader.getController();
    return controller;
  }
}
