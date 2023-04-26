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
      ArrayList<Node> nodesAvailable = new ArrayList<Node>();
      for (Edge edgePath : current.getEdges()) {
        nodesAvailable.add(edgePath.getStartNode());
        nodesAvailable.add(edgePath.getEndNode());
      }
      if (current.getLocation().getNodeType().equalsIgnoreCase("ELEV") // block used to guide
              && current.getFloor().equalsIgnoreCase(end.getFloor())
          || current.getLocation().getNodeType().equalsIgnoreCase("STAI")
              && current.getFloor().equalsIgnoreCase(end.getFloor())) { // thru stair/elev
        for (Node node : nodesAvailable) {
          if (node.getFloor().equalsIgnoreCase(current.getFloor())
              && !visitedList.contains(node) // find a nonelevator
          ) {
            openList.add(node);
            openList.remove(current);
            path.add(current);
            visitedList.add(current);
            nextChosen = true;
          }
        }
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
              && current.getFloor().equalsIgnoreCase(backup.getFloor())
              && !nextChosen) {
            openList.add(backup);
            openList.remove(current);
            path.add(current);
            // path.add(backup);
            visitedList.add(current);
            nextChosen = true;
            System.out.println();
            System.out.println(
                " FROM BACKUP BRANCH AT CURRRENT " + current.getNodeID() + " WENT TO " + backup);
          }
        }
      }
      int i = 1;
      while (!nextChosen) {
        Node previous = path.get(path.size() - i);
        System.out.println("WENT BACK TO " + previous.getNodeID() + " with I " + i);
        ArrayList<Node> revisitedNodes = new ArrayList<Node>();
        if (!revisitedNodes.contains(previous)) revisitedNodes.add(previous);
        ArrayList<Node> previousNodes = new ArrayList<Node>();
        i++;
        for (Edge previousEdge : previous.getEdges()) {
          previousNodes.add(previousEdge.getStartNode());
          previousNodes.add(previousEdge.getEndNode());
        }
        for (Node previousNode : previousNodes) {
          if (!path.contains(previousNode)
              && !nextChosen
              && !previousNode.equals(current)
              && !visitedList.contains(previousNode)
              && floor.equalsIgnoreCase(previousNode.getFloor())) {
            openList.add(previousNode);
            openList.remove(current);
            // path.add(current);
            path.addAll(revisitedNodes);
            // path.add(previousNode);
            visitedList.add(current);
            nextChosen = true;
            System.out.println();
            System.out.println(
                " FROM NON-CHOSEN BRANCH ADDED FROM NODE "
                    + current.getNodeID()
                    + " WITH PATH "
                    + revisitedNodes
                    + " TO "
                    + previousNode);
            break;
          }
        }
      }
    }
    return path;
  }
}
