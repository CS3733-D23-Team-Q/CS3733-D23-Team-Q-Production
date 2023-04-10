package edu.wpi.cs3733.D23.teamQ;

import edu.wpi.cs3733.D23.teamQ.Pathfinding.Star;
import edu.wpi.cs3733.D23.teamQ.Pathfinding.Star2;
import edu.wpi.cs3733.D23.teamQ.db.Qdb;
import edu.wpi.cs3733.D23.teamQ.db.obj.Node;
import java.util.List;

public class Main {

  public static void main(String[] args) {
    Qdb.getInstance();
    Qdb qdb = Qdb.getInstance();
    App.launch(App.class, args);
    Node ns = qdb.retrieveNode(100);
    Node ne = qdb.retrieveNode(250);
    List<Node> nodes = Star2.aStar(ns, ne);
    System.out.println(nodes.size());
    for (Node n : nodes) {
      System.out.println(n.getNodeID());
      // System.out.println(n.getYCoord());
      // System.out.println(n.getXCoord());
    }
    List<Node> nodes2 = Star.aStar(ns, ne);
    System.out.println(nodes2.size());
    for (Node n : nodes2) {
      System.out.println(n.getNodeID());
      // System.out.println(n.getYCoord());
      // System.out.println(n.getXCoord());
    }
  }
}

  // shortcut: psvm
