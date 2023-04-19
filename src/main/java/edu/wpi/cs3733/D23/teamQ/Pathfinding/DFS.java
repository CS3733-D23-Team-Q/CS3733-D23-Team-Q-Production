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
      boolean nextChosen = false;
      Node current = openList.get(0);
      // in case the end node is on a diff floor
      if (current.equals(end)) {
        path.add(current);
        return path;
      }
      if (current.getEdges().size() == 0) {
        System.out.println("This node is a dead end " + current);
      }
      if (current.getLocation().getNodeType().equalsIgnoreCase("ELEV")
              && !current.getFloor().equalsIgnoreCase(end.getFloor())
          || current.getLocation().getNodeType().equalsIgnoreCase("STAI")
              && !current.getFloor().equalsIgnoreCase(end.getFloor())) {
        System.out.println(
            "Found an elevator to a diff floor at " + current + " with end " + end.getFloor());
        ArrayList<Node> elevatorTargets = new ArrayList<Node>();
        for (Edge these : current.getEdges()) {
          elevatorTargets.add(these.getStartNode());
          elevatorTargets.add(these.getEndNode());
        }
        for (Node node : elevatorTargets) {
          if (node.getFloor().equalsIgnoreCase(end.getFloor())) {
            Node chosenElev = node;
            openList.add(chosenElev);
            openList.remove(current);
            path.add(current);
            visitedList.add(current);
            nextChosen = true;
            break;
          }
        }
      }
      ArrayList<Node> nodesAvailable = new ArrayList<Node>();

      for (Edge edgePath : current.getEdges()) {
        nodesAvailable.add(edgePath.getStartNode());
        nodesAvailable.add(edgePath.getEndNode());
      }
      if (!visitedList.contains(current.getEdges().get(0).getEndNode())
          && !current.getEdges().isEmpty()
          && !current.equals(current.getEdges().get(0).getEndNode())
          && floor.equalsIgnoreCase(current.getEdges().get(0).getEndNode().getFloor())
          && !nextChosen) {
        openList.add(current.getEdges().get(0).getEndNode());
        openList.remove(current);
        path.add(current);
        visitedList.add(current);
        nextChosen = true;
      } else if (!visitedList.contains(current.getEdges().get(0).getStartNode())
          && !current.getEdges().isEmpty()
          && !current.equals(current.getEdges().get(0).getStartNode())
          && floor.equalsIgnoreCase(current.getEdges().get(0).getStartNode().getFloor())
          && !nextChosen) {
        openList.add(current.getEdges().get(0).getStartNode());
        openList.remove(current);
        path.add(current);
        visitedList.add(current);
        nextChosen = true;
      } else {
        for (Node backup : nodesAvailable) {
          if (!visitedList.contains(backup)
              && !current.equals(backup)
              && floor.equalsIgnoreCase(backup.getFloor())
              && !nextChosen) {
            openList.add(backup);
            openList.remove(current);
            path.add(current);
            visitedList.add(current);
            nextChosen = true;
          }
        }
      }
      int i = 1;
      while (!nextChosen) {
        Node previous = path.get(path.size() - i);
        ArrayList<Node> previousNodes = new ArrayList<Node>();
        i++;
        for (Edge previousEdge : previous.getEdges()) {
          previousNodes.add(previousEdge.getStartNode());
          previousNodes.add(previousEdge.getEndNode());
        }
        for (Node previousNode : previousNodes) {
          if (!path.contains(previousNode)
              && !visitedList.contains(previousNode)
              && floor.equalsIgnoreCase(previousNode.getFloor())) {
            openList.add(previousNode);
            openList.remove(current);
            path.add(current);
            visitedList.add(current);
            nextChosen = true;
          }
        }
      }
    }
    return path;
  }
}
