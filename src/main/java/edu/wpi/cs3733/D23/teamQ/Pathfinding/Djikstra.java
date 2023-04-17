package edu.wpi.cs3733.D23.teamQ.Pathfinding;

import edu.wpi.cs3733.D23.teamQ.db.obj.Edge;
import edu.wpi.cs3733.D23.teamQ.db.obj.Node;
import java.util.ArrayList;

public class Djikstra implements IPathfinding {
  public ArrayList<Node> run(Node start, Node end) {
    ArrayList<Node> closedList = new ArrayList<Node>();
    ArrayList<Node> openList = new ArrayList<Node>();
    ArrayList<Node> path = new ArrayList<Node>();
    String floor = start.getFloor();
    openList.add(start);
    closedList.add(start);
    while (!openList.isEmpty()) {
      Node current = openList.get(0);
      if (current == null) {
        return path;
      }
      if (current.equals(end)) {
        path.add(current);
        return path;
      }
      // write in elevator specifications

      double lowestLocalCost = 10000000.0;
      Node chosen = null;
      for (Edge thisOne : current.getEdges()) {
        if (!closedList.contains(thisOne.getStartNode())
            && !thisOne.getStartNode().equals(current)
            && !path.contains(thisOne.getStartNode())
            && floor.equalsIgnoreCase(thisOne.getStartNode().getFloor())) {
          double xDist = Math.abs(end.getXCoord() - thisOne.getStartNode().getXCoord());
          double yDist = Math.abs(end.getYCoord() - thisOne.getStartNode().getYCoord());
          double weight = Math.sqrt(xDist * xDist + yDist * yDist);
          if (weight < lowestLocalCost) {
            lowestLocalCost = weight;
            chosen = thisOne.getStartNode();
            System.out.println();
            System.out.println("I chose node using THIS " + chosen);
          }
        } else if (!closedList.contains(thisOne.getEndNode())
            && !thisOne.getEndNode().equals(current)
            && !path.contains(thisOne.getEndNode())
            && floor.equalsIgnoreCase(thisOne.getEndNode().getFloor())) {
          double xDist = Math.abs(end.getXCoord() - thisOne.getEndNode().getXCoord());
          double yDist = Math.abs(end.getYCoord() - thisOne.getEndNode().getYCoord());
          double weight = Math.sqrt(xDist * xDist + yDist * yDist);
          if (weight < lowestLocalCost) {
            lowestLocalCost = weight;
            chosen = thisOne.getEndNode();
            System.out.println();
            System.out.println("I chose node using THESE " + chosen);
          }
        }
      }
      path.add(current);
      if (chosen == null) {
        System.out.println();
        System.out.println("No node was chosen at node " + current);
        System.out.println();
        System.out.println("Here are the available Nodes " + current.getEdges());
        System.out.println();
        System.out.println("Here is the closedList " + closedList);
        System.out.println();
        ArrayList<Node> altNodes = new ArrayList<Node>();
        for (Node node : closedList) {
          for (Edge edge : node.getEdges()) {
            altNodes.add(edge.getStartNode());
            altNodes.add(edge.getEndNode());
          }
        }
        for (Node node2 : altNodes) {
          if (!closedList.contains(node2)) {
            chosen = node2;
          }
        }
      }
      if (chosen == null) {
        System.out.println("COUNTERMEAUSRES LULW");
      }
      openList.add(chosen);
      openList.remove(current);
      closedList.add(current);
      System.out.println();
      System.out.println("Added node " + current.getNodeID() + " to closedList");
    }
    return path;
  }
}
