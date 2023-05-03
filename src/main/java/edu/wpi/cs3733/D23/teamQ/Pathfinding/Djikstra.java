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
      path.add(current);
      if (current.equals(end)) {
        return path;
      }
      double lowestLocalCost = 10000000.0;
      boolean nextChosen = false;
      Node chosen = null;
      if (current.getLocation().getNodeType().equalsIgnoreCase("ELEV") // choice block for swithcing
              && !current.getFloor().equalsIgnoreCase(end.getFloor()) // floors
          || current.getLocation().getNodeType().equalsIgnoreCase("STAI")
              && !current.getFloor().equalsIgnoreCase(end.getFloor())) {
        ArrayList<Node> elevatorTargets = getConnections(current);
        for (Node node : elevatorTargets) {
          if (node.getFloor().equalsIgnoreCase(end.getFloor()) && !node.equals(current)
             && node.getBuilding().equalsIgnoreCase(current.getBuilding())) {
            Node chosenElev = node;
            openList.add(chosenElev);
            openList.remove(current);
            closedList.add(current);
            nextChosen = true;
            break;
          }
        }
      }
      if (!nextChosen) {
        for (Edge thisOne :
            current.getEdges()) { // main choice block for start nodes, used to calc distance
          if (!closedList.contains(thisOne.getStartNode())
              && thisOne.getStartNode().getFloor().equalsIgnoreCase(current.getFloor())
              && !thisOne.getStartNode().equals(current)
              && !closedList.contains(thisOne.getStartNode())) {
            double xDist = Math.abs(end.getXCoord() - thisOne.getStartNode().getXCoord());
            double yDist = Math.abs(end.getYCoord() - thisOne.getStartNode().getYCoord());
            double weight = Math.sqrt(xDist * xDist + yDist * yDist);
            if (weight < lowestLocalCost) {
              lowestLocalCost = weight;
              chosen = thisOne.getStartNode();
            }
          } else if (!closedList.contains(
                  thisOne.getEndNode()) // main choice block for end nodes, used to cacl
              && !thisOne.getEndNode().equals(current) // distance
              && !path.contains(thisOne.getEndNode())
              && thisOne.getEndNode().getFloor().equalsIgnoreCase(current.getFloor())) {
            double xDist = Math.abs(end.getXCoord() - thisOne.getEndNode().getXCoord());
            double yDist = Math.abs(end.getYCoord() - thisOne.getEndNode().getYCoord());
            double weight = Math.sqrt(xDist * xDist + yDist * yDist);
            if (weight < lowestLocalCost) {
              lowestLocalCost = weight;
              chosen = thisOne.getEndNode();
            }
          }
        }
      }
      if (chosen == null) {
        ArrayList<Node> path2 = new ArrayList<Node>();
        int i = 1;
        while (chosen == null) {
          ArrayList<Node> altNodes = new ArrayList<Node>(); // backup block
          for (Edge edge : path.get(path.size() - i).getEdges()) {
            altNodes.add(edge.getStartNode());
            altNodes.add(edge.getEndNode());
          }
          path2.add(path.get(path.size() - i));
          i++;
          double lowestLocalCost2 = 10000000.0;
          int g = 0;
          for (Node node2 : altNodes) {
            g++;
            if (!closedList.contains(node2) && !node2.equals(current)) {
              double xDist = Math.abs(end.getXCoord() - node2.getXCoord());
              double yDist = Math.abs(end.getYCoord() - node2.getYCoord());
              double weight = Math.sqrt(xDist * xDist + yDist * yDist);
              if (weight < lowestLocalCost2) {
                lowestLocalCost2 = weight;
                chosen = node2;
              }
            }
          }
          if (chosen != null) {
            path.addAll(path2);
            path.add(chosen);
          }
        }
      }
      if (!openList.contains(chosen)) {
        openList.add(chosen);
      }
      openList.remove(current);
      if (!closedList.contains(current)) closedList.add(current);
    }
    return path;
  }

  public ArrayList<Node> getConnections(Node current) {
    ArrayList<Node> nodesAvailable = new ArrayList<Node>();
    for (Edge edgePath : current.getEdges()) {
      nodesAvailable.add(edgePath.getStartNode());
      nodesAvailable.add(edgePath.getEndNode());
    }
    return nodesAvailable;
  }
}
