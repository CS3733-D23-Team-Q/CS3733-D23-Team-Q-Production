package edu.wpi.cs3733.D23.teamQ.Pathfinding;

import edu.wpi.cs3733.D23.teamQ.db.obj.Edge;
import edu.wpi.cs3733.D23.teamQ.db.obj.Node;
import java.util.ArrayList;

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
    boolean noElevators = true; // used to determine whether to ignore elevators
    String targetFloor = target.getFloor();
    if (!targetFloor.equalsIgnoreCase(start.getFloor())) {
      noElevators = false;
    }
    openList.add(start);
    closedList.add(start);
    while (!openList.isEmpty()) {
      if (openList.get(0).equals(target)) {
        closedList.add(openList.get(0)); // line to add current node to closed list
        return closedList;
      }
      Node currentNode = openList.get(0);
      if (currentNode.getEdges().size() == 0) { // check for edgeless nodes
        System.out.println("The node " + currentNode.getNodeID() + " has no edges.");
      }
      double bestWeight =
          1000000000.0; // setting some temp variables used to calculate and find the best weight
      // (shortest distance)
      double relativeWeight = 100000.0;
      Edge bestEdge = null;
      //  List<Edge> edgeClone = currentNode.getEdges();
      // ArrayList<Edge> trueClone = new ArrayList<Edge>();
      Node deadNode = null;
      // loop to create duplicate arraylist of edges contained in currentNode
      // trueClone.addAll(edgeClone);
      for (Edge thisEdge : currentNode.getEdges()) {
        /*  if (!thisEdge.getEndNode().getFloor().equalsIgnoreCase(targetFloor)) {
          continue;
        }*/
        double relativeXDist = Math.abs(target.getXCoord() - thisEdge.getEndNode().getXCoord());
        double relativeYDist = Math.abs(target.getYCoord() - thisEdge.getEndNode().getYCoord());
        relativeWeight = Math.sqrt(relativeXDist * relativeXDist + relativeYDist * relativeYDist);
        if (relativeWeight < bestWeight
            && !thisEdge.getEndNode().getLocation().getNodeType().equalsIgnoreCase("ELEV")
            // && thisEdge.getEndNode().getFloor().equalsIgnoreCase(targetFloor)
            && !deadList.contains(thisEdge.getEndNode())
            && !closedList.contains(thisEdge.getEndNode())) {
          bestWeight = relativeWeight;
          bestEdge = thisEdge;
          System.out.println();
          System.out.println("I picked node " + bestEdge.getEndNode());
          System.out.println();
          System.out.println("The current node has edges" + currentNode.getEdges());
          System.out.println();
          System.out.println("And the target node was " + target);
          System.out.println();
          if (closedList.size() != 0) {
            System.out.println("And the closed list contains node " + closedList);
          }
          System.out.println();
          System.out.println("And the weight was" + bestWeight);
        }
      }
      if (currentNode.getEdges().size() == 0) {
        if (currentNode.equals(target)) {
          closedList.add(currentNode);
          return closedList;
        }
        deadNode = currentNode;
        deadList.add(deadNode);
        openList.add(closedList.get(closedList.size() - 1));
        System.out.println("Dead node was found at " + currentNode);
      }
      /* if (bestEdge == null) {
        bestEdge = currentNode.getEdges().get(0);
      }*/
      // I need a fallback process that gets any untraversed node if no good node has been found, to
      // avoid an infinite loop of two nodes choosing each other
      if (bestEdge == null) {
        for (Edge chosen : currentNode.getEdges()) {
          if (!closedList.contains(chosen.getEndNode())
              //  && chosen.getEndNode().getFloor().equalsIgnoreCase(targetFloor)
              && !chosen
                  .getEndNode()
                  .getLocation()
                  .getNodeType()
                  .equalsIgnoreCase("ELEV")) { // get node type
            bestEdge = chosen;
            System.out.println(
                "The non-elevator node fallback was triggered by the edge " + chosen);
          }
        }
      }
      if (bestEdge == null) { // This scenario most likely happens when we are near an elevator
        for (Edge fin : currentNode.getEdges()) {
          if (!closedList.contains(fin.getEndNode())
              && fin.getEndNode().getFloor().equalsIgnoreCase(targetFloor)) {
            bestEdge = fin;
            System.out.println();
            System.out.println("The any-node-on-same-floor fallback was triggered by edge " + fin);
          }
        }
      }
      if (bestEdge == null) {
        for (Edge indicator : currentNode.getEdges()) {
          if (!closedList.contains(indicator.getStartNode())) {
            closedList.add(currentNode);
            openList.remove(currentNode);
            openList.add(indicator.getStartNode());
            System.out.println();
            System.out.println(
                "The untraversed Node is a start node fallback was triggered by edge "
                    + indicator
                    + " And added node "
                    + indicator.getStartNode());
          }
        }
        continue;
      }
      closedList.add(currentNode);
      openList.remove(currentNode);
      openList.add(bestEdge.getEndNode());
    }

    return closedList;
  }
}
