package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.Pathfinding.Star;
import edu.wpi.cs3733.D23.teamQ.db.Qdb;
import edu.wpi.cs3733.D23.teamQ.db.obj.Node;
import edu.wpi.cs3733.D23.teamQ.navigation.Navigation;
import edu.wpi.cs3733.D23.teamQ.navigation.Screen;
import io.github.palexdev.materialfx.controls.MFXTextField;
import java.sql.SQLException;
import java.util.List;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;

public class PathTextController implements IController {
  @FXML Button resetButton;
  @FXML Button backButton;
  @FXML Button submitButton;

  @FXML MenuItem homeItem;
  @FXML MenuItem exitItem;

  @FXML MFXTextField startNodeField;
  @FXML MFXTextField endNodeField;
  @FXML Label textualPath;
  @FXML MenuItem profileItem;

  @FXML
  public void initialize() {}

  @FXML
  public void resetButtonClicked() {
    startNodeField.clear();
    endNodeField.clear();
  }

  @FXML
  public void backButtonClicked() {
    Navigation.navigate(Screen.HOME2);
  }

  @FXML
  public void submitButtonClicked() throws SQLException {
    if ((startNodeField.getText()).equals("") || (endNodeField.getText()).equals("")) {
      textualPath.setText("Please enter two valid Node IDs");
    } else {
      Qdb qdb = Qdb.getInstance();
      Node start = qdb.retrieveNode(Integer.parseInt(startNodeField.getText()));
      Node end = qdb.retrieveNode(Integer.parseInt(endNodeField.getText()));
      if (start == null || end == null) {
        textualPath.setText("Please enter two valid Node IDs");
      } else {
        List<Node> path = Star.aStar(start, end);
        String sPath = "";
        for (Node n : path) {
          sPath = sPath + n.getNodeID() + " ";
        }
        //       String textPath = Star.returnPath(Astar.aStar(start, end));
        textualPath.setText(sPath);
      }
    }
  }

  @FXML
  public void homeItemClicked() {
    Navigation.navigate(Screen.HOME);
  }

  @FXML
  public void exitItemClicked() {
    Platform.exit();
  }

  @FXML
  public void profileItemClicked() {
    Navigation.navigate(Screen.PROFILE_PAGE);
  }
}
