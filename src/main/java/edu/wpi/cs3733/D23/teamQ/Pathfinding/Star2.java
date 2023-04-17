package edu.wpi.cs3733.D23.teamQ.Pathfinding;

import edu.wpi.cs3733.D23.teamQ.db.Qdb;
import edu.wpi.cs3733.D23.teamQ.db.obj.Edge;
import edu.wpi.cs3733.D23.teamQ.db.obj.Location;
import edu.wpi.cs3733.D23.teamQ.db.obj.Node;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Star2 {
  static Qdb qdb = Qdb.getInstance();

  public static void addEdges(Node n) {
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

  public static ArrayList<Node> DFS(Node start, Node end) {
    ArrayList<Node> visitedList = new ArrayList<Node>(); // list of visited nodes
    ArrayList<Node> openList = new ArrayList<Node>();
    ArrayList<Node> path = new ArrayList<Node>(); // path list if I need it
    openList.add(start);
    path.add(start);
    while (!openList.isEmpty()) {
      Node current = openList.get(0);
      // in case the end node is on a diff floor

      if (current.equals(end)) {
        return path;
      }
      if (current.getEdges().size() == 0) {
        System.out.println("This node is a dead end " + current);
      }
      if (!visitedList.contains(current.getEdges().get(0).getEndNode())) {
        openList.add(current.getEdges().get(0).getEndNode());
        path.add(current);
        visitedList.add(current);
      } else if (!visitedList.contains(current.getEdges().get(0).getStartNode())) {
        openList.add(current.getEdges().get(0).getStartNode());
        path.add(current);
        visitedList.add(current);
      } else {
        // look through other edges until an unvisited node is found
        for (Edge index : current.getEdges()) {
          if (!visitedList.contains(index.getStartNode())) {
            openList.add(index.getStartNode());
            path.add(current);
            visitedList.add(current);
          } else if (!visitedList.contains(index.getEndNode())) {
            openList.add(index.getEndNode());
            path.add(current);
            visitedList.add(current);
          }
        }
      }
    }
    return path;
  }

  public static ArrayList<Node> Djikstra(Node start, Node end) {
    ArrayList<Node> closedList = new ArrayList<Node>();
    ArrayList<Node> openList = new ArrayList<Node>();
    ArrayList<Node> path = new ArrayList<Node>();

    openList.add(start);
    while (!openList.isEmpty()) {
      Node current = openList.get(0);
      if (current.equals(end)) {
        return path;
      }
      // write in elevator specifications

      double lowestLocalCost = 10000000.0;
      Node chosen = null;
      for (Edge thisOne : current.getEdges()) {
        if (!closedList.contains(thisOne.getStartNode())
                && !thisOne.getStartNode().equals(current)
                && !path.contains(thisOne.getStartNode())) {
          double xDist = Math.abs(thisOne.getStartNode().getXCoord() - current.getXCoord());
          double yDist = Math.abs(thisOne.getStartNode().getYCoord() - current.getYCoord());
          double weight = Math.sqrt(xDist * xDist + yDist * yDist);
          if (weight < lowestLocalCost) {
            lowestLocalCost = weight;
            chosen = thisOne.getStartNode();
          }
        } else if (!closedList.contains(thisOne.getEndNode())
                && !thisOne.getEndNode().equals(current)
                && !path.contains(thisOne.getEndNode())) {
          double xDist = Math.abs(thisOne.getEndNode().getXCoord() - current.getXCoord());
          double yDist = Math.abs(thisOne.getEndNode().getYCoord() - current.getYCoord());
          double weight = Math.sqrt(xDist * xDist + yDist * yDist);
          if (weight < lowestLocalCost) {
            lowestLocalCost = weight;
            chosen = thisOne.getStartNode();
          }
        }
      }
      path.add(current);
      openList.add(chosen);
      openList.remove(current);
      closedList.add(current);
    }
    return path;
  }
}
