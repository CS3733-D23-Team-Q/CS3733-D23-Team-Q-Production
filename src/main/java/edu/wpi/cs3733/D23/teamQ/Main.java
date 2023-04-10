package edu.wpi.cs3733.D23.teamQ;

import edu.wpi.cs3733.D23.teamQ.Pathfinding.Star2;
import edu.wpi.cs3733.D23.teamQ.db.Qdb;
import edu.wpi.cs3733.D23.teamQ.db.obj.Node;
import java.util.List;

public class Main {

  public static void main(String[] args) {
    Qdb.getInstance();
    Qdb qdb = Qdb.getInstance();
    App.launch(App.class, args);
    //    Node ns = qdb.retrieveNode(100);
    //    Node ne = qdb.retrieveNode(130);
    //    List<Node> nodes = Star2.aStar(ns, ne);
    //    System.out.println(nodes.size());
    //    for (Node n : nodes) {
    //      System.out.println(n.getYCoord());
    //      // System.out.println(n.getXCoord());
    //    }
    /*
    System.out.println(Star2.heuristic(qdb.retrieveNode(100), qdb.retrieveNode(125)));
    System.out.println(ns.equals(ne));
    if (ns.getParent() == null) {
      System.out.println("null");
    }
    System.out.println(qdb.retrieveAllNodes().size());
    System.out.println(qdb.getEdges(2315).size());
    Star2.addEdges(ns);
    List<Edge> edges = ns.getEdges();
    for (Edge e : edges) {
      System.out.println(e.getEdgeID());
      Node n = e.getEndNode();
      System.out.println(n.getNodeID());
    }
    System.out.println(ns.getF());
     */
    Node start = qdb.retrieveNode(100);
    Node target = qdb.retrieveNode(130);
    List<Node> path = Star2.aStar(start, target);
    for (int i = path.size() - 1; i >= 1; i--) {
      Node n = path.get(i);
      Node next = path.get(i - 1);
      int x1 = n.getXCoord();
      int y1 = n.getYCoord();
      int x2 = next.getXCoord();
      int y2 = next.getYCoord();
      // map.toBack();
    }
    /*
    Node start = qdb.retrieveNode(100);
    Node target = qdb.retrieveNode(130);
    List<Node> path = Star2.aStar(start, target);
    for (int i = path.size() - 1; i >= 1; i--) {
      Node n = path.get(i);
      Node next = path.get(i - 1);
      int x1 = n.getXCoord();
      int y1 = n.getYCoord();
      int x2 = next.getXCoord();
      int y2 = next.getYCoord();
      System.out.println(y1);
      System.out.println(y2);
      // map.toBack();
    }
     */
  }

  // shortcut: psvm

}
