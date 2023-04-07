package edu.wpi.cs3733.D23.teamQ.Pathfinding;

import edu.wpi.cs3733.D23.teamQ.db.obj.Edge;
import edu.wpi.cs3733.D23.teamQ.db.obj.Node;
import java.util.ArrayList;

public class Star extends Edge {

  public Star() {
    super();
  }

  public static double calculateHeuristic(Node n, Node target) {
    int dx = Math.abs(n.getXCoord() - target.getYCoord());
    int dy = Math.abs(n.getYCoord() - target.getYCoord());
    int D = Math.max(dx, dy);
    return D;
  }

  public static ArrayList<Node> aStar(Node start, Node target) {
    ArrayList<Node> closedList = new ArrayList<Node>();
    ArrayList<Node> openList = new ArrayList<Node>();
    int nextWeight = 0;

    start.setF(start.getG() + calculateHeuristic(start, target));
    openList.add(start);

    while (!openList.isEmpty()) {
      Node n = openList.get(0);
      if (n == target) {
        closedList.add(n);
        return closedList;
      }

      Edge m = null;
      for (int i = 0; i < n.getEdges().size(); i++) {
        m = n.getEdges().get(i);
        Edge next = null;
        Node startNode = n.getEdges().get(i).getStartNode();
        Node end = n.getEdges().get(i).getEndNode();
        if (i < n.getEdges().size() - 1) {
          next = n.getEdges().get(i + 1);
          Node nextStart = n.getEdges().get(i).getStartNode();
          Node nextEnd = n.getEdges().get(i).getEndNode();
        }
        //         double totalWeight = n.getG() + edge.getWeight();
        int totalWeight = m.getWeight(); // first weight
        if (next != null) {
          nextWeight = next.getWeight();
        }

        if (next != null
            && !openList.contains(next.getEndNode())
            && !closedList.contains(next)
            && nextWeight != 0
            && nextWeight < totalWeight) {
          next.getStartNode().setParent(m.getEndNode());
          next.getStartNode().setG(nextWeight);
          next.getStartNode()
              .setF(next.getStartNode().getG() + calculateHeuristic(next.getStartNode(), target));
          openList.add(next.getEndNode());
          //              .setF(next.getStartNode().getG() + calculateHeuristic(next.getStartNode(),
          // target));
          //          openList.add(next.getEndNode());
          if (closedList.size() != 0) {
            //  System.out.println(closedList.get(closedList.size() - 1).getNodeID());
          }
        } else {
          if (totalWeight < nextWeight
              && next != null
              && nextWeight != 0
              && !closedList.contains(next)) { // make this next edge used to be <
            next.getStartNode().setParent(m.getStartNode());
            next.getStartNode().setG(nextWeight);
            next.getStartNode()
                .setF(m.getWeight() + calculateHeuristic(next.getStartNode(), target));
            if (closedList.size() != 0) {
              //  System.out.println(closedList.get(closedList.size() - 1).getNodeID());
            }

            if (!closedList.contains(next.getStartNode()) && next != null) {
              closedList.remove(next.getStartNode());
              openList.add(next.getStartNode());
              if (closedList.size() != 0) {
                // System.out.println(closedList.get(closedList.size() - 1).getNodeID());
              }
            }
          }
        }
      }
      //      if (!closedList.contains(m.getEndNode())) {
      if (m != null
          && !closedList.contains(m.getStartNode())
          && m.getWeight() < n.getEdges().get(0).getWeight()) {
        //        openList.add(m.getStartNode());
        openList.add(m.getEndNode());
      } else if (m != null
          && !openList.contains(m.getEndNode())
          && !closedList.contains(m.getEndNode())) {
        openList.add(m.getEndNode());
      } else if (n.equals(start)) {
        openList.add(n.getEdges().get(0).getEndNode());
      }
      openList.remove(n);
      /*if (n.getEdges() != null) {
        n.setWeight(n.getEdges().get(0).getWeight());
      }*/
      closedList.add(n);
    }

    return closedList;
  }
}
