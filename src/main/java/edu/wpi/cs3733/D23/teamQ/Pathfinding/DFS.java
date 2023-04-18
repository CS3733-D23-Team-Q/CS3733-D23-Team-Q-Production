package edu.wpi.cs3733.D23.teamQ.Pathfinding;

import edu.wpi.cs3733.D23.teamQ.db.obj.Edge;
import edu.wpi.cs3733.D23.teamQ.db.obj.Node;
import java.util.ArrayList;

public class DFS implements IPathfinding {
  public ArrayList<Node> run(Node start, Node end) {
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
}
