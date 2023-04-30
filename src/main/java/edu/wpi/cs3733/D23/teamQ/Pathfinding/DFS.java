package edu.wpi.cs3733.D23.teamQ.Pathfinding;

import edu.wpi.cs3733.D23.teamQ.db.obj.Edge;
import edu.wpi.cs3733.D23.teamQ.db.obj.Node;
import java.util.ArrayList;

public class DFS implements IPathfinding {
  public ArrayList<Node> run(Node start, Node end) {
    ArrayList<Node> visitedList = new ArrayList<Node>(); // list of visited nodes
    ArrayList<Node> openList = new ArrayList<Node>();
    ArrayList<Node> path = new ArrayList<Node>(); // path list if I need it
    openList.add(start);
    visitedList.add(start);
    while (!openList.isEmpty()) {
      boolean nextChosen = false;
      Node current = openList.get(0);
      path.add(current);
      if (current.equals(end)) {
        path.add(current);
        return path;
      }
      ArrayList<Node> nodesAvailable = new ArrayList<Node>();
      for (Edge edgePath : current.getEdges()) {
        nodesAvailable.add(edgePath.getStartNode());
        nodesAvailable.add(edgePath.getEndNode());
      }
      if (current.getLocation().getNodeType().equalsIgnoreCase("ELEV") // block used to guide
              && !current.getFloor().equalsIgnoreCase(end.getFloor())
          || current.getLocation().getNodeType().equalsIgnoreCase("STAI")
              && !current.getFloor().equalsIgnoreCase(end.getFloor())) { // path onto correct floor
        for (Node node : nodesAvailable) {
          if (node.getFloor().equalsIgnoreCase(end.getFloor())
              && !path.contains(node)
              && !node.equals(current)
              && !nextChosen // find a elevator
          ) {
            openList.add(node);
            openList.remove(current);
            visitedList.add(current);
            nextChosen = true;
          }
        }
      }
      if (current.getLocation().getNodeType().equalsIgnoreCase("ELEV") // block used to guide
              && current.getFloor().equalsIgnoreCase(end.getFloor())
          || current.getLocation().getNodeType().equalsIgnoreCase("STAI")
              && current.getFloor().equalsIgnoreCase(end.getFloor())) { // thru stair/elev
        for (Node node : nodesAvailable) {
          if (node.getFloor().equalsIgnoreCase(current.getFloor())
              && !path.contains(node)
              && !node.equals(current)
              && !nextChosen // find a nonelevator
          ) {
            openList.add(node);
            openList.remove(current);
            visitedList.add(current);
            nextChosen = true;
          }
        }
      }
      if (!path.contains(current.getEdges().get(0).getEndNode())
          && !current.equals(current.getEdges().get(0).getEndNode())
          && current
              .getFloor()
              .equalsIgnoreCase((current.getEdges().get(0).getEndNode().getFloor()))
          && !nextChosen) {
        openList.add(current.getEdges().get(0).getEndNode());
        openList.remove(current);
        visitedList.add(current);
        nextChosen = true;
      } else if (!path.contains(current.getEdges().get(0).getStartNode())
          && !current.equals(current.getEdges().get(0).getStartNode())
          && current
              .getFloor()
              .equalsIgnoreCase((current.getEdges().get(0).getStartNode().getFloor()))
          && !nextChosen) {
        openList.add(current.getEdges().get(0).getStartNode());
        openList.remove(current);
        visitedList.add(current);
        nextChosen = true;
      } else {
        for (Node backup : nodesAvailable) {
          if (!path.contains(backup)
              && !current.equals(backup)
              && current.getFloor().equalsIgnoreCase(backup.getFloor())
              && !nextChosen) {
            openList.add(backup);
            openList.remove(current);
            visitedList.add(current);
            nextChosen = true;
          }
        }
      }
      int i = 1;
      ArrayList<Node> revisitedNodes = new ArrayList<Node>();
      while (!nextChosen) {
        Node previous = path.get(path.size() - i);
        revisitedNodes.add(previous);
        ArrayList<Node> previousNodes = new ArrayList<Node>();
        i++;
        if (i > 2000) {
          ArrayList<Node> fixPath = new ArrayList<Node>();
          ArrayList<Node> targetNodes = getConnections(end);
          for (Node node : targetNodes) {
            if (path.contains(node)) {
              Node connectedNode = node;
              for (int g = path.size() - 1; g > 0; g--) {
                fixPath.add(path.get(g));
                Node pathNode = path.get(g);
                if (pathNode.equals(connectedNode)) {
                  path.addAll(fixPath);
                  path.add(end);
                  return path;
                }
              }
            }
          }
          for (Node node2 : targetNodes) {
            ArrayList<Node> retracedPath = new ArrayList<Node>();
            ArrayList<Node> connections2 = getConnections(node2);
            for (Node node3 : connections2) {
              if (path.contains(node3)) {
                for (int g = path.size() - 1; g > 0; g--) {
                  retracedPath.add(path.get(g));
                  if (path.get(g).equals(node3)) {
                    path.addAll(retracedPath);
                    path.add(node2);
                    path.add(end);
                    return path;
                  }
                }
              }
            }
          }
          return path;
        }
        for (Edge previousEdge : previous.getEdges()) {
          previousNodes.add(previousEdge.getStartNode());
          previousNodes.add(previousEdge.getEndNode());
        }
        for (Node previousNode : previousNodes) {
          if (!path.contains(previousNode)
              && !nextChosen
              && !previousNode.equals(current)
              && !visitedList.contains(previousNode)
              && current.getFloor().equalsIgnoreCase(previousNode.getFloor())) {
            openList.add(previousNode);
            openList.remove(current);
            path.addAll(revisitedNodes);
            visitedList.add(current);
            nextChosen = true;
            break;
          }
        }
      }
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
