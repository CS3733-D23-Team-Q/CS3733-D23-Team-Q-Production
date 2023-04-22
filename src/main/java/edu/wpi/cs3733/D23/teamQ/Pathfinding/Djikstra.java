package edu.wpi.cs3733.D23.teamQ.Pathfinding;

import edu.wpi.cs3733.D23.teamQ.db.obj.Edge;
import edu.wpi.cs3733.D23.teamQ.db.obj.Node;
import java.util.ArrayList;

public class Djikstra implements IPathfinding {
  public ArrayList<Node> run(Node start, Node end) {
    ArrayList<Node> closedList = new ArrayList<Node>();
    ArrayList<Node> openList = new ArrayList<Node>();
    ArrayList<Node> path = new ArrayList<Node>();
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
      boolean nextChosen = false;
      Node chosen = null;
      if (current.getLocation().getNodeType().equalsIgnoreCase("ELEV") // choice block for swithcing
              && !current.getFloor().equalsIgnoreCase(end.getFloor()) // floors
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
            closedList.add(current);
            nextChosen = true;
            break;
          }
        }
      }
      for (Edge thisOne :
          current.getEdges()) { // main choice block for start nodes, used to calc distance
        if (!closedList.contains(thisOne.getStartNode())
            && !thisOne.getStartNode().equals(current)
            && !path.contains(thisOne.getStartNode())
            //   && thisOne.getStartNode()
            && !nextChosen) {
          double xDist = Math.abs(end.getXCoord() - thisOne.getStartNode().getXCoord());
          double yDist = Math.abs(end.getYCoord() - thisOne.getStartNode().getYCoord());
          double weight = Math.sqrt(xDist * xDist + yDist * yDist);
          if (weight < lowestLocalCost) {
            lowestLocalCost = weight;
            chosen = thisOne.getStartNode();
            System.out.println();
          }
        } else if (!closedList.contains(
                thisOne.getEndNode()) // main choice block for end nodes, used to cacl
            && !thisOne.getEndNode().equals(current) // distance
            && !path.contains(thisOne.getEndNode())
            && thisOne.getEndNode().getFloor().equalsIgnoreCase(start.getFloor())
            && !nextChosen) {
          double xDist = Math.abs(end.getXCoord() - thisOne.getEndNode().getXCoord());
          double yDist = Math.abs(end.getYCoord() - thisOne.getEndNode().getYCoord());
          double weight = Math.sqrt(xDist * xDist + yDist * yDist);
          if (weight < lowestLocalCost) {
            lowestLocalCost = weight;
            chosen = thisOne.getEndNode();
            System.out.println();
          }
        }
      }
      if (!path.contains(current)) {
        path.add(current);
      }
      if (chosen == null) {
        ArrayList<Node> altNodes = new ArrayList<Node>(); // backup block
        for (Node node : closedList) {
          for (Edge edge : node.getEdges()) {
            altNodes.add(edge.getStartNode());
            altNodes.add(edge.getEndNode());
          }
        }
        for (Node node2 : altNodes) {
          if (!closedList.contains(node2)
                  && node2.getLocation().getNodeType().equalsIgnoreCase("ELEV")
              || !closedList.contains(node2)
                  && node2.getLocation().getNodeType().equalsIgnoreCase("STAI")) { // "ELEV", STAI
            chosen = node2;
          }
        }
      }
      if (chosen != null
              && chosen.getLocation().getNodeType().equalsIgnoreCase("STAI") // test statement
          || chosen != null
              && chosen.getLocation().getNodeType().equalsIgnoreCase("ELEV")) { // block
        System.out.println(
            "The chosen node was an elevator/stair at node "
                + chosen.getNodeID()
                + " and it was a "
                + chosen.getLocation().getNodeType());
      }
      if (chosen == null) {
        System.out.println("COUNTERMEAUSRES LULW");
      }
      if (!path.contains(current)) {
        path.add(current);
      }
      if (chosen != null
              && chosen
                  .getLocation()
                  .getNodeType()
                  .equalsIgnoreCase("ELEV") // this block is used to send the path
              && chosen
                  .getFloor()
                  .equalsIgnoreCase(end.getFloor()) // "through" an elevator when on the same
              && current.getLocation().getNodeType().equalsIgnoreCase("ELEV")
          || chosen.getLocation().getNodeType().equalsIgnoreCase("STAI") // floor as a target node
              && chosen.getFloor().equalsIgnoreCase(end.getFloor())
              && current.getLocation().getNodeType().equalsIgnoreCase("STAI")) {
        System.out.println();
        System.out.println("Was at node " + current + "and went to " + chosen);
        ArrayList<Node> availableList =
            new ArrayList<Node>(); // list of nodes available from an elevator/stair
        ArrayList<Node> availableClone = new ArrayList<Node>();
        for (Edge edge : current.getEdges()) { // get all available edges
          availableList.add(edge.getStartNode());
          availableList.add(edge.getEndNode());
          availableClone.add(edge.getStartNode());
          availableClone.add(edge.getEndNode());
        }
        for (Node node : availableClone) { // removes visited nodes from available list
          if (availableList.contains(node) && path.contains(node)
              || availableList.contains(node) && closedList.contains(node)
              || node.equals(current)) {
            availableList.remove(node);
          }
        }
        for (Node node : availableList) { // this loop is used to find a non-elevetor or stair node
          if (!node.getLocation()
                  .getNodeType()
                  .equalsIgnoreCase("ELEV") // if its not a stair or elevator
              || !node.getLocation().getNodeType().equalsIgnoreCase("STAI")) {
            chosen = node;
            nextChosen = true;
            System.out.println();
            System.out.println("Picked node " + node);
            break;
          }
        }
      }
      if (!openList.contains(chosen)) {
        openList.add(chosen);
      }
      if (openList.contains(current)) openList.remove(current);
      if (!closedList.contains(current)) closedList.add(current);
      System.out.println();
      System.out.println("Added node " + current.getNodeID() + " to closedList");
    }
    return path;
  }
}
