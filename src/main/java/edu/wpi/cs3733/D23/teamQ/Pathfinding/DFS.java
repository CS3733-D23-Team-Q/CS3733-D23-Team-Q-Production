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
        // look through other edges until an unvisited node is found
        for (Edge index : current.getEdges()) {
          if (!visitedList.contains(index.getStartNode())
              && !index.getStartNode().equals(current)
              && index.getStartNode().getFloor().equalsIgnoreCase(floor)) {
            openList.add(index.getStartNode());
            openList.remove(current);
            path.add(current);
            visitedList.add(current);
            System.out.println();
            System.out.println("current node is " + current);
            System.out.println();
            System.out.println("Edges contain " + current.getEdges());
            System.out.println();
            System.out.println("added from pre-final if");
          } else if (!visitedList.contains(index.getEndNode())
              && !index.getEndNode().equals(current)
              && index.getEndNode().getFloor().equalsIgnoreCase(floor)) {
            openList.add(index.getEndNode());
            openList.remove(current);
            path.add(current);
            visitedList.add(current);
            System.out.println();
            System.out.println("current node is " + current);
            System.out.println();
            System.out.println("Edges contain " + current.getEdges());
            System.out.println();
            System.out.println("added from final if");
          }
        }
      }
    }
    return path;
  }
}
