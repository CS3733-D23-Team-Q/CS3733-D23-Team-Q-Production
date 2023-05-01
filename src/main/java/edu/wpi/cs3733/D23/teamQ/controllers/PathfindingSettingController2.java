package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.SecondaryStage;
import edu.wpi.cs3733.D23.teamQ.db.Qdb;
import edu.wpi.cs3733.D23.teamQ.db.obj.DefaultLocation;
import edu.wpi.cs3733.D23.teamQ.db.obj.Location;
import edu.wpi.cs3733.D23.teamQ.db.obj.Move;
import edu.wpi.cs3733.D23.teamQ.db.obj.Node;
import edu.wpi.cs3733.D23.teamQ.navigation.Screen;
import io.github.palexdev.materialfx.controls.MFXFilterComboBox;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class PathfindingSettingController2 extends SecondaryStage {
  /*
  static Stage stage;
  Qdb qdb = Qdb.getInstance();
  Boolean isStartSelected;
  Boolean isKioskSelected;
  @FXML MFXFilterComboBox<String> startLocSelect;
  @FXML MFXFilterComboBox<String> kioskLocSelect;

  @FXML
  public void initialize() {
    List<Move> moves = qdb.retrieveAllMoves();
    List<String> lnames = new ArrayList<>();
    for (Move m : moves) {
      String lname = m.getLongName();
      List<Node> nodes = qdb.retrieveAllNodes();
      Location loc = null;
      for (Node n : nodes) {
        if (n.getLocation().getLongName().equals(m.getLongName())) {
          loc = n.getLocation();
          break;
        }
      }
      if (loc != null) {
        String nodetype = loc.getNodeType();
        if (!lnames.contains(lname)
            && !nodetype.equals("HALL")
            && !nodetype.equals("ELEV")
            && !nodetype.equals("STAI")) {
          lnames.add(lname);
        }
      }
    }

    for (String lname : lnames) {
      startLocSelect.getItems().add(lname);
      kioskLocSelect.getItems().add(lname);
    }
    isStartSelected = false;
    isKioskSelected = false;
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

  public void kioskLocSelected() {
    String kioskLoc = kioskLocSelect.getValue();
    if (kioskLoc != null && !kioskLoc.equals("")) {
      isKioskSelected = true;
    }
  }

  public void addButtonClicked() {
    String startLocs = "";
    Location startLocl = null;
    if (isStartSelected) {
      startLocs = startLocSelect.getValue();
      startLocl = qdb.getNodeFromLocation(startLocs).getLocation();
      Location[] oldKioskLocs;
      DefaultLocation old = qdb.retrieveDefaultLocation(1);
      if (old != null) {
        oldKioskLocs = old.getKiosks();
      } else {
        oldKioskLocs = new Location[0];
      }
      qdb.updateDefaultLocation(1, new DefaultLocation(startLocl, oldKioskLocs));
      isStartSelected = false;
    }
    if (isKioskSelected) {
      String kioskLocs = kioskLocSelect.getValue();
      Location kioskLocl = qdb.getNodeFromLocation(kioskLocs).getLocation();
      DefaultLocation old = qdb.retrieveDefaultLocation(1);
      Location oldStartLoc = null;
      if (old.getStartingLocation() != null) {
        oldStartLoc = old.getStartingLocation();
      }
      Location[] newKioskLocs;
      if (old.getKiosks() != null) {
        Location[] oldKioskLocs = old.getKiosks();
        newKioskLocs = new Location[oldKioskLocs.length + 1];
        for (int i = 0; i < oldKioskLocs.length; i++) {
          newKioskLocs[i] = oldKioskLocs[i];
        }
        newKioskLocs[newKioskLocs.length - 1] = kioskLocl;
      } else {
        newKioskLocs = new Location[1];
        newKioskLocs[0] = kioskLocl;
      }
      if (!startLocs.equals("")) {
        qdb.updateDefaultLocation(1, new DefaultLocation(startLocl, newKioskLocs));
      } else {
        qdb.updateDefaultLocation(1, new DefaultLocation(oldStartLoc, newKioskLocs));
      }
      isKioskSelected = false;
    }
  }
   */
}
