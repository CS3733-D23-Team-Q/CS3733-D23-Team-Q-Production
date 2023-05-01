package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.SecondaryStage;
import edu.wpi.cs3733.D23.teamQ.db.Qdb;
import edu.wpi.cs3733.D23.teamQ.db.obj.DefaultLocation;
import edu.wpi.cs3733.D23.teamQ.db.obj.Location;
import edu.wpi.cs3733.D23.teamQ.db.obj.Node;
import edu.wpi.cs3733.D23.teamQ.navigation.Screen;
import io.github.palexdev.materialfx.controls.MFXFilterComboBox;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class PathfindingSettingController extends SecondaryStage {
  static Stage stage;
  String username = LoginController.getUsername();
  Qdb qdb = Qdb.getInstance();
  boolean isStartSelected;
  @FXML MFXFilterComboBox<String> startLocSelect;

  @FXML
  public void initialize() {
    List<Node> latestNodes = PathfindingController.getLatestNodes();
    List<String> lnames = new ArrayList<>();
    for (Node n : latestNodes) {
      String lname = n.getLocation().getLongName();
      String nodetype = n.getLocation().getNodeType();
      if (!lnames.contains(lname)) {
        if (!lnames.contains(lname)
            && !nodetype.equals("HALL")
            && !nodetype.equals("ELEV")
            && !nodetype.equals("STAI")) {
          lnames.add(lname);
        }
      }
    }
    startLocSelect.getItems().add("null");
    for (String lname : lnames) {
      startLocSelect.getItems().add(lname);
    }
    isStartSelected = false;
  }

  public static void display() throws IOException {
    stage = newStage(Screen.PATH_FINDING_SETTING);
    stage.show();
    stage.centerOnScreen();
  }

  public void startLocSelected() {
    String startLoc = startLocSelect.getValue();
    if (startLoc != null && !startLoc.equals("")) {
      isStartSelected = true;
    }
  }

  public void saveButtonClicked() {
    if (isStartSelected) {
      String startLocs = startLocSelect.getValue();
      if (startLocs.equals("null")) {
        if (qdb.getDefaultLocationIndex(username) != -1) {
          qdb.deleteDefaultLocation(username);
        }
      } else {
        Location startLocl = qdb.getNodeFromLocation(startLocs).getLocation();
        if (qdb.getDefaultLocationIndex(username) != -1) {
          qdb.updateDefaultLocation(username, new DefaultLocation(username, startLocl));
        } else {
          qdb.addDefaultLocation(new DefaultLocation(username, startLocl));
        }
      }
    }
  }
}
