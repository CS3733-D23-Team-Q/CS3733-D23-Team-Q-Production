package edu.wpi.cs3733.D23.teamQ.Pathfinding;

import edu.wpi.cs3733.D23.teamQ.db.obj.Edge;
import edu.wpi.cs3733.D23.teamQ.db.obj.Node;
import java.util.ArrayList;
import java.util.List;

public class Star extends Edge {

  public Star() {
    super();
  }

  /*public static double calculateHeuristic(Node n, Node target) {
    int dx = Math.abs(n.getXCoord() - target.getYCoord());
    int dy = Math.abs(n.getYCoord() - target.getYCoord());
    int D = Math.max(dx, dy);
    return D;
  }*/

  public static ArrayList<Node> aStar(Node start, Node target) {
    ArrayList<Node> closedList = new ArrayList<Node>();
    ArrayList<Node> openList = new ArrayList<Node>();
    ArrayList<Node> deadList = new ArrayList<Node>();

    openList.add(start);
    while (openList.isEmpty() == false) {
      if (openList.get(0).equals(target)) {
        closedList.add(openList.get(0)); // line to add current node to closed list
        return closedList;
      }
      Node currentNode = openList.get(0);
      if (currentNode.getEdges().size() == 0) { // check for edgeless nodes
        System.out.println("The node " + currentNode.getNodeID() + " has no edges.");
      }
      int bestWeight =
          1000000000; // setting some temp variables used to calculate and find the best weight
      // (shortest distance)
      Edge bestEdge = null;
      List<Edge> edgeClone = currentNode.getEdges();
      ArrayList<Edge> trueClone = new ArrayList<Edge>();
      Node deadNode = null;
      // loop to create duplicate arraylist of edges contained in currentNode
      trueClone.addAll(edgeClone);
      for (Edge thisEdge : trueClone) {
        if (thisEdge.getWeight() < bestWeight && !deadList.contains(thisEdge.getEndNode())) {
          bestWeight = thisEdge.getWeight();
          bestEdge = thisEdge;
        }
      }
      if (currentNode.getEdges().size() == 0) {
        deadNode = currentNode;
        deadList.add(deadNode);
        openList.add(closedList.get(closedList.size() - 1));
      }
      if (bestEdge == null) {
        bestEdge = currentNode.getEdges().get(0);
      }
      closedList.add(
          bestEdge.getStartNode()); // at this point, the best weight has been found, now we need to
      // match the edge
      openList.remove(currentNode);
      openList.add(bestEdge.getEndNode());
    }

    return closedList;
  }
}
