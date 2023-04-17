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
    while (!openList.isEmpty()) {
      Node current = openList.get(0);
      if (current.equals(end)) {
        return path;
      }
      // write in elevator specifications

      double lowestLocalCost = 10000000.0;
      Node chosen = null;
      for (Edge thisOne : current.getEdges()) {
        if (!closedList.contains(thisOne.getStartNode())
            && !thisOne.getStartNode().equals(current)
            && !path.contains(thisOne.getStartNode())) {
          double xDist = Math.abs(thisOne.getStartNode().getXCoord() - current.getXCoord());
          double yDist = Math.abs(thisOne.getStartNode().getYCoord() - current.getYCoord());
          double weight = Math.sqrt(xDist * xDist + yDist * yDist);
          if (weight < lowestLocalCost) {
            lowestLocalCost = weight;
            chosen = thisOne.getStartNode();
          }
        } else if (!closedList.contains(thisOne.getEndNode())
            && !thisOne.getEndNode().equals(current)
            && !path.contains(thisOne.getEndNode())) {
          double xDist = Math.abs(thisOne.getEndNode().getXCoord() - current.getXCoord());
          double yDist = Math.abs(thisOne.getEndNode().getYCoord() - current.getYCoord());
          double weight = Math.sqrt(xDist * xDist + yDist * yDist);
          if (weight < lowestLocalCost) {
            lowestLocalCost = weight;
            chosen = thisOne.getStartNode();
          }
        }
      }
      path.add(current);
      openList.add(chosen);
      openList.remove(current);
      closedList.add(current);
    }
    return path;
  }
}
