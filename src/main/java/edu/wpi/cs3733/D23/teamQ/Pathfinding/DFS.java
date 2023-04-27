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
      ArrayList<Node> nodesAvailable = new ArrayList<Node>();
      for (Edge edgePath : current.getEdges()) {
        System.out.println();
        System.out.println(" AT CURRENT " + current.getNodeID());

        nodesAvailable.add(edgePath.getStartNode());
        System.out.println();
        System.out.println(" ADDED Node " + edgePath.getStartNode().getNodeID());
        nodesAvailable.add(edgePath.getEndNode());
        System.out.println();
        System.out.println(" ADDED NODE " + edgePath.getEndNode().getNodeID());
      }
      if (current.getLocation().getNodeType().equalsIgnoreCase("ELEV") // block used to guide
              && current.getFloor().equalsIgnoreCase(end.getFloor())
          || current.getLocation().getNodeType().equalsIgnoreCase("STAI")
              && current.getFloor().equalsIgnoreCase(end.getFloor())) { // thru stair/elev
        for (Node node : nodesAvailable) {
          if (node.getFloor().equalsIgnoreCase(current.getFloor())
              && !visitedList.contains(node)
              && !node.equals(current)
              && !nextChosen // find a nonelevator
          ) {
            /*  System.out.println();
            System.out.println(
                " ADDED NODE "
                    + node.getNodeID()
                    + " FROM "
                    + current.getNodeID()
                    + " USING ELEVATORS SAME FLOOR ");*/
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
        /*  System.out.println();
        System.out.println(
            " ADDED NODE "
                + current.getEdges().get(0).getEndNode().getNodeID()
                + " FROM "
                + current.getNodeID()
                + " USING BRANCH FROM END NODES");*/
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
        /* System.out.println();
        System.out.println(
            " ADDED NODE "
                + current.getEdges().get(0).getStartNode().getNodeID()
                + " FROM "
                + current.getNodeID()
                + " USING BRANCH START NODES");*/
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
            /* System.out.println();
            System.out.println(
                " FROM BACKUP BRANCH AT CURRRENT "
                    + current.getNodeID()
                    + " WENT TO "
                    + backup.getNodeID()
                    + " EDGES WERE "
                    + nodesAvailable);*/
          }
        }
      }
      int i = 1;
      ArrayList<Node> revisitedNodes = new ArrayList<Node>();
      while (!nextChosen) {
        Node previous = path.get(path.size() - i);
        // System.out.println("WENT BACK TO " + previous.getNodeID() + " with I " + i);
        // ArrayList<Node> revisitedNodes = new ArrayList<Node>();
        if (!revisitedNodes.contains(previous)) {
          revisitedNodes.add(previous);
        }
        System.out.println();
        System.out.println(" REVISITED NODE " + previous.getNodeID() + " USING INDEX " + i);
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
            path.add(previousNode);
            visitedList.add(current);
            nextChosen = true;
            System.out.println();
            System.out.println(
                " FROM NON-CHOSEN BRANCH ADDED FROM NODE "
                    + current.getNodeID()
                    + " WITH PATH "
                    + revisitedNodes
                    + " AND SIZE "
                    + revisitedNodes.size()
                    + " TO "
                    + previousNode.getNodeID());
            break;
          }
        }
      }
    }
    return path;
  }
}
