package edu.wpi.cs3733.D23.teamQ.navigation;

import edu.wpi.cs3733.D23.teamQ.App;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

public class Navigation {

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

  public static void logout() {
    App.getRootBorder().setLeft(null);
    Screen loginScreen = Screen.LOGIN;
    final String filename = loginScreen.getFilename();

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
    } catch (IOException | NullPointerException e) {
      e.printStackTrace();
    }
  }
}
