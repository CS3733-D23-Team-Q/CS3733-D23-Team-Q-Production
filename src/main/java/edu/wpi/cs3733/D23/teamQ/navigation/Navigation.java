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

  public static void navigateLogin(final Screen screen) {
    final String filename = screen.getFilename();
    try {
      final var resource = App.class.getResource(filename);
      final FXMLLoader loader = new FXMLLoader(resource);

      Stage primaryStage = App.getPrimaryStage();

      Node n = loader.load();
      App.getRootBorder().setRight(null);
      App.getRootCenter().getChildren().clear();
      App.getRootCenter().getChildren().add(n);
      AnchorPane.setTopAnchor(n, 0.0);
      AnchorPane.setLeftAnchor(n, 0.0);
      AnchorPane.setRightAnchor(n, 0.0);
      AnchorPane.setBottomAnchor(n, 0.0);
      primaryStage.setFullScreen(true);
    } catch (IOException | NullPointerException e) {
      e.printStackTrace();
    }
  }

  public static void navigate(final Screen screen) {
    final String filename = screen.getFilename();

    try {
      final var resource = App.class.getResource(filename);
      final FXMLLoader loader = new FXMLLoader(resource);

      //      Stage primaryStage = App.getPrimaryStage();
      //      final Scene scene = new Scene(loader.load());
      //      scene
      //          .getStylesheets()
      //          .add(
      //              Navigation.class
      //                  .getResource("/edu/wpi/cs3733/D23/teamQ/views/styles/Home.css")
      //                  .toExternalForm());
      Node n = loader.load();
      App.getRootBorder().setRight(null);
      App.getRootCenter().getChildren().clear();
      App.getRootCenter().getChildren().add(n);
      AnchorPane.setTopAnchor(n, 0.0);
      AnchorPane.setLeftAnchor(n, 0.0);
      AnchorPane.setRightAnchor(n, 0.0);
      AnchorPane.setBottomAnchor(n, 0.0);
    } catch (IOException | NullPointerException e) {
      e.printStackTrace();
    }
  }

  public static void navigateRight(final Screen screen) {
    final String filename = screen.getFilename();

    try {
      final var resource = App.class.getResource(filename);
      final FXMLLoader loader = new FXMLLoader(resource);

      //      Stage primaryStage = App.getPrimaryStage();
      //      final Scene scene = new Scene(loader.load());
      //      scene
      //          .getStylesheets()
      //          .add(
      //              Navigation.class
      //                  .getResource("/edu/wpi/cs3733/D23/teamQ/views/styles/Home.css")
      //                  .toExternalForm());
      Node n = loader.load();
      App.getRootRight().getChildren().clear();
      App.getRootBorder().setRight(n);
      AnchorPane.setTopAnchor(n, 0.0);
      AnchorPane.setLeftAnchor(n, 0.0);
      AnchorPane.setRightAnchor(n, 0.0);
      AnchorPane.setBottomAnchor(n, 0.0);
    } catch (IOException | NullPointerException e) {
      e.printStackTrace();
    }
  }

  public static void logout() {
    App.getRootBorder().setLeft(null);
    App.getRootBorder().setRight(null);
    Screen loginScreen = Screen.LOGIN;
    final String filename = loginScreen.getFilename();
    final String title = loginScreen.getTitle();

    try {
      final var resource = App.class.getResource(filename);
      final FXMLLoader loader = new FXMLLoader(resource);

      //      Stage primaryStage = App.getPrimaryStage();
      //      final Scene scene = new Scene(loader.load());
      //      scene
      //          .getStylesheets()
      //          .add(
      //              Navigation.class
      //                  .getResource("/edu/wpi/cs3733/D23/teamQ/views/styles/Home.css")
      //                  .toExternalForm());
      Node n = loader.load();
      App.getRootCenter().getChildren().clear();
      App.getRootCenter().getChildren().add(n);
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

  public static void noMenuNavigate(final Screen screen) {
    App.getRootBorder().setLeft(null);
    App.getRootBorder().setRight(null);
    final String filename = screen.getFilename();
    final String title = screen.getTitle();

    try {
      final var resource = App.class.getResource(filename);
      final FXMLLoader loader = new FXMLLoader(resource);
      Node n = loader.load();
      App.getRootCenter().getChildren().clear();
      App.getRootCenter().getChildren().add(n);
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
