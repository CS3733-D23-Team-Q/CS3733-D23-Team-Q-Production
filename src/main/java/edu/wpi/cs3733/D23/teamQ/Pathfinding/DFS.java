package edu.wpi.cs3733.D23.teamQ.Pathfinding;

import edu.wpi.cs3733.D23.teamQ.db.obj.Edge;
import edu.wpi.cs3733.D23.teamQ.db.obj.Node;
import java.util.ArrayList;

public class DFS implements IPathfinding {
  public ArrayList<Node> run(Node start, Node end) {
    ArrayList<Node> visitedList = new ArrayList<Node>(); // list of visited nodes
    ArrayList<Node> openList = new ArrayList<Node>();
    ArrayList<Node> path = new ArrayList<Node>(); // path list if I need it
    String floor = start.getFloor();
    openList.add(start);
    visitedList.add(start);
    // path.add(start);
    while (!openList.isEmpty()) {
      Node current = openList.get(0);
      // in case the end node is on a diff floor
      if (current.equals(end)) {
        path.add(current);
        return path;
      }
      if (current.getEdges().size() == 0) {
        System.out.println("This node is a dead end " + current);
      }
      ArrayList<Node> nodesAvailable = new ArrayList<Node>();
      for (Edge edgePath : current.getEdges()) {
        nodesAvailable.add(edgePath.getStartNode());
        nodesAvailable.add(edgePath.getEndNode());
      }
      if (!visitedList.contains(current.getEdges().get(0).getEndNode())
          && !current.getEdges().isEmpty()
          && !current.equals(current.getEdges().get(0).getEndNode())
          && floor.equalsIgnoreCase(current.getEdges().get(0).getEndNode().getFloor())) {
        openList.add(current.getEdges().get(0).getEndNode());
        openList.remove(current);
        path.add(current);
        visitedList.add(current);
        System.out.println();
        System.out.println("current node is " + current);
        System.out.println();
        System.out.println("Edges contain " + current.getEdges());
        System.out.println();
        System.out.println(
            "Added a end node with ID" + current.getEdges().get(0).getEndNode().getNodeID());
      } else if (!visitedList.contains(current.getEdges().get(0).getStartNode())
          && !current.getEdges().isEmpty()
          && !current.equals(current.getEdges().get(0).getStartNode())
          && floor.equalsIgnoreCase(current.getEdges().get(0).getStartNode().getFloor())) {
        openList.add(current.getEdges().get(0).getStartNode());
        openList.remove(current);
        path.add(current);
        visitedList.add(current);
        System.out.println();
        System.out.println("current node is " + current);
        System.out.println();
        System.out.println("Edges contain " + current.getEdges());
        System.out.println();
        System.out.println(
            "Added a start node with ID" + current.getEdges().get(0).getStartNode().getNodeID());
      } else {
        for (Node backup : nodesAvailable) {
          if (!visitedList.contains(backup)
              && !current.equals(backup)
              && floor.equalsIgnoreCase(backup.getFloor())) {
            openList.add(backup);
            openList.remove(current);
            path.add(current);
            visitedList.add(current);
          }
        }
      }
    }
    return path;
  }
}
