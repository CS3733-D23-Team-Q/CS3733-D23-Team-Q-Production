package edu.wpi.cs3733.D23.teamQ.Pathfinding;

import edu.wpi.cs3733.D23.teamQ.db.Qdb;
import edu.wpi.cs3733.D23.teamQ.db.obj.Edge;
import edu.wpi.cs3733.D23.teamQ.db.obj.Node;
import java.util.ArrayList;
import java.util.List;

public class Star2 {
  static Qdb qdb = Qdb.getInstance();

  public static void addEdges(Node n) {
    /*
    ArrayList<Node> nodes = qdb.retrieveAllNodes();
    for (Node n : nodes) {
      int nodeID = n.getNodeID();
      List<Edge> edges = qdb.getEdges(nodeID);
      n.setEdges(edges);
    }
    return nodes;
     */
    int nodeID = n.getNodeID();
    List<Edge> edges = qdb.getEdges(nodeID);
    n.setEdges(edges);
  }

  public static double heuristic(Node n, Node target) {
    int dx = Math.abs(n.getXCoord() - target.getXCoord());
    int dy = Math.abs(n.getYCoord() - target.getYCoord());
    double D = Math.hypot(dx, dy);
    return D;
  }

  public static ArrayList<Node> aStar(Node start, Node target) {
    ArrayList<Node> openSet = new ArrayList<>();
    ArrayList<Node> closedSet = new ArrayList<>();
    ArrayList<Node> path = new ArrayList<>();
    openSet.add(start);
    while (openSet.size() > 0) {
      int winner = 0;
      for (int i = 1; i < openSet.size(); i++) {
        if (openSet.get(i).getF() < openSet.get(winner).getF()) {
          winner = i;
        }
        if (openSet.get(i).getF() == openSet.get(winner).getF()) {
          if (openSet.get(i).getG() > openSet.get(winner).getG()) {
            winner = i;
          }
        }
      }
      Node current = openSet.get(winner);
      if (current.equals(target)) {
        // path.add(current);
        path.add(current);
        while (current.getParent() != null) {
          path.add(current.getParent());
          current = current.getParent();
        }
        return path;
      }
      openSet.remove(current);
      closedSet.add(current);
      addEdges(current);
      List<Edge> edges = current.getEdges();
      List<Node> neighbors = new ArrayList<>();
      for (Edge e : edges) {
        Node n = e.getEndNode();
        neighbors.add(n);
      }
      for (int j = 0; j < neighbors.size(); j++) {
        Node neighbor = neighbors.get(j);
        boolean newPath = false;
        if (!closedSet.contains(neighbor)) {
          double tempG = current.getG() + heuristic(neighbor, current);
          if (!openSet.contains(neighbor)) {
            neighbor.setG(tempG);
            newPath = true;
            openSet.add(neighbor);
          } else {
            if (tempG >= neighbor.getG()) {
              neighbor.setG(tempG);
            }
          }
          if (newPath) {
            // neighbor.setG(tempG);
            double h = heuristic(neighbor, target);
            neighbor.setF(tempG + h);
            neighbor.setParent(current);
          }
        }
      }
      /*
      Node temp = current;
      path.add(temp);
      while (temp.getParent() != null) {
        path.add(temp.getParent());
        temp = temp.getParent();
      }
       */
    }
    path.removeAll(path);
    return path;
  }
}
