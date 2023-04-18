package edu.wpi.cs3733.D23.teamQ.Pathfinding;

import edu.wpi.cs3733.D23.teamQ.db.Qdb;
import edu.wpi.cs3733.D23.teamQ.db.obj.Edge;
import edu.wpi.cs3733.D23.teamQ.db.obj.Location;
import edu.wpi.cs3733.D23.teamQ.db.obj.Node;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AStar implements IPathfinding {
  static Qdb qdb = Qdb.getInstance();

  public static void addEdges(Node n) {
    int nodeID = n.getNodeID();
    List<Edge> edges = qdb.getEdges(nodeID);
    n.setEdges(edges);

    //    for (Edge e : edges) {
    //      Edge temp = null;
    //      temp.setStartNode(e.getEndNode());
    //      temp.setEndNode(e.getStartNode());
    //      edges.add(temp);
    //    }
    //    //    n.setEdges(addEdges(););
    //    n.setEdges(edges);
  }

  //  public static void addEdges(Node n, Node n1) {}

  public static double heuristic(Node n, Node target) {
    int dx = Math.abs(n.getXCoord() - target.getXCoord());
    int dy = Math.abs(n.getYCoord() - target.getYCoord());
    double D = Math.hypot(dx, dy);
    return D;
  }

  public ArrayList<Node> run(Node start, Node target) {
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
        path.add(current);
        while (current.getParent() != null) {
          path.add(current.getParent());
          if (!current.equals(current.getParent().getParent())) current = current.getParent();
          else break;
        }
        return path;
      }
      openSet.remove(current);
      closedSet.add(current);
      addEdges(current);
      List<Edge> edges = current.getEdges();
      List<Node> neighbors = new ArrayList<>();
      for (Edge e : edges) {
        Node n1 = e.getStartNode();
        Node n2 = e.getEndNode();
        if (n1.equals(current)) {
          neighbors.add(n2);
        } else {
          neighbors.add(n1);
        }
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
            double h = heuristic(neighbor, target);
            neighbor.setF(tempG + h);
            neighbor.setParent(current);
          }
        }
      }
    }
    path.removeAll(path);
    return path;
  }

  public static ArrayList<Node> aStarElev(Node start, Node target) {
    ArrayList<Node> openSet = new ArrayList<>();
    ArrayList<Node> closedSet = new ArrayList<>();
    ArrayList<Node> path = new ArrayList<>();
    Pattern pattern = Pattern.compile(".*\\bstair\\b.*", Pattern.CASE_INSENSITIVE);
    String lname = "";
    openSet.add(start);
    while (openSet.size() > 0) {
      int winner = 0;
      for (int i = 1; i < openSet.size(); i++) {
        Node node = openSet.get(i);
        int nodeid = node.getNodeID();
        Location location = qdb.retrieveLocation(nodeid);
        lname = location.getLongName();
        Matcher matcher = pattern.matcher(lname);
        if (openSet.get(i).getF() < openSet.get(winner).getF() && !matcher.find()) {
          winner = i;
        }
        if (openSet.get(i).getF() == openSet.get(winner).getF()) {
          if (openSet.get(i).getG() > openSet.get(winner).getG() && !matcher.find()) {
            winner = i;
          }
        }
      }
      Node current = openSet.get(winner);
      if (current != null && current.equals(target)) {
        path.add(current);
        while (current.getParent() != null) {
          path.add(current.getParent());
          if (!current.equals(current.getParent().getParent())) current = current.getParent();
          else break;
        }
        return path;
      }
      openSet.remove(current);
      closedSet.add(current);
      addEdges(current);
      List<Edge> edges = current.getEdges();
      List<Node> neighbors = new ArrayList<>();
      for (Edge e : edges) {
        Node n1 = e.getStartNode();
        Node n2 = e.getEndNode();
        if (n1.equals(current)) {
          neighbors.add(n2);
        } else {
          neighbors.add(n1);
        }
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
            double h = heuristic(neighbor, target);
            neighbor.setF(tempG + h);
            neighbor.setParent(current);
          }
        }
      }
    }
    path.removeAll(path);
    return path;
  }
}
