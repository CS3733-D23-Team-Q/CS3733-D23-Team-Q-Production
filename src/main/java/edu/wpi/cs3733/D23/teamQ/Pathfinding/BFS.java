package edu.wpi.cs3733.D23.teamQ.Pathfinding;

import edu.wpi.cs3733.D23.teamQ.db.obj.Edge;
import edu.wpi.cs3733.D23.teamQ.db.obj.Node;
import java.util.*;

public class BFS implements IPathfinding {

  @Override
  public ArrayList<Node> run(Node start, Node target) {
    ArrayList<Node> visited =
        new ArrayList<>(); // this list will hold nodes that get visited, but unique ones
    Queue<Node> queue = new LinkedList<>();
    ArrayList<Node> returnList =
        new ArrayList<Node>(); // this list is the path that gets returned, with repeat visits
    String endFloor = target.getFloor();
    queue.add(start);
    visited.add(start);
    while (!queue.isEmpty()) {
      Node n = queue.poll();
      returnList.add(n);
      boolean nodeChosen = false;
      if (n.equals(target)) {
        return returnList;
      }
      if (n.getLocation().getNodeType().equalsIgnoreCase("ELEV")
          && !n.getFloor().equalsIgnoreCase(endFloor)
          && !nodeChosen) { // branch to send to correct floor, if needed
        ArrayList<Node> elevatorNodes = getConnections(n);
        for (Node node : elevatorNodes) {
          if (node.getFloor().equalsIgnoreCase(endFloor) && !node.equals(n)) {
            visited.add(node);
            queue.add(node);
            nodeChosen = true;
            break;
          }
        }
      }
      if (n.getLocation().getNodeType().equalsIgnoreCase("ELEV")
          && n.getFloor().equalsIgnoreCase(endFloor)
          && !nodeChosen) { // branch to send to same floor, if needed
        ArrayList<Node> elevatorNodes = getConnections(n);
        for (Node node : elevatorNodes) {
          if (node.getFloor().equalsIgnoreCase(n.getFloor())
              && !visited.contains(node)
              && !node.equals(n)) {
            visited.add(node);
            queue.add(node);
            nodeChosen = true;
            break;
          }
        }
      }
      if (n.getLocation().getNodeType().equalsIgnoreCase("STAI")
          && !n.getFloor().equalsIgnoreCase(endFloor)
          && !nodeChosen) { // branch to send to correct floor, if needed
        ArrayList<Node> stairNodes = getConnections(n);
        for (Node node : stairNodes) {
          if (node.getFloor().equalsIgnoreCase(endFloor) && !node.equals(n)) {
            visited.add(node);
            queue.add(node);
            nodeChosen = true;
            break;
          }
        }
      }
      if (n.getLocation().getNodeType().equalsIgnoreCase("STAI")
          && n.getFloor().equalsIgnoreCase(endFloor)
          && !nodeChosen) { // branch to send to same floor, if needed
        ArrayList<Node> stairNodes = getConnections(n);
        for (Node node : stairNodes) {
          if (node.getFloor().equalsIgnoreCase(n.getFloor())
              && !node.equals(n)
              && !visited.contains(node)) {
            returnList.add(node);
            visited.add(node);
            queue.add(node);
            nodeChosen = true;
            break;
          }
        }
      }
      for (Edge edge : n.getEdges()) { // normal situation choice branches, 1 4 end nodes, 1 4 start
        Node neighbor = edge.getEndNode();
        if (!visited.contains(neighbor)
            && n.getFloor().equalsIgnoreCase(neighbor.getFloor())
            && !neighbor.equals(n)
            && !nodeChosen) {
          visited.add(neighbor);
          queue.add(neighbor);
          nodeChosen = true;
          break;
        }
        if (!visited.contains(edge.getStartNode())
            && n.getFloor().equalsIgnoreCase(edge.getStartNode().getFloor())
            && !n.equals(edge.getStartNode())
            && !nodeChosen) {
          visited.add(edge.getStartNode());
          queue.add(edge.getStartNode());
          nodeChosen = true;
          break;
        }
      }
      if (!nodeChosen) { // if we run into a dead end
        int i = 1;
        ArrayList<Node> nodesRevisited = new ArrayList<Node>();
        ArrayList<Node> backupNodes = new ArrayList<Node>();
        while (!nodeChosen) {
          backupNodes = getConnections(returnList.get(returnList.size() - i));
          nodesRevisited.add(returnList.get(returnList.size() - i));
          i++;
          if (i > 2000) {
            ArrayList<Node> fixPath = new ArrayList<Node>();
            ArrayList<Node> targetNodes = getConnections(target);
            for (Node node : targetNodes) {
              if (returnList.contains(node)) {
                Node connectedNode = node;
                for (int g = returnList.size() - 1; g > 0; g--) {
                  fixPath.add(returnList.get(g));
                  Node pathNode = returnList.get(g);
                  if (pathNode.equals(connectedNode)) {
                    returnList.addAll(fixPath);
                    returnList.add(target);
                    return returnList;
                  }
                }
              }
            }
            for (Node node2 : targetNodes) {
              ArrayList<Node> retracedPath = new ArrayList<Node>();
              ArrayList<Node> connections2 = getConnections(node2);
              for (Node node3 : connections2) {
                if (returnList.contains(node3)) {
                  for (int g = returnList.size() - 1; g > 0; g--) {
                    retracedPath.add(returnList.get(g));
                    if (returnList.get(g).equals(node3)) {
                      returnList.addAll(retracedPath);
                      returnList.add(node2);
                      returnList.add(target);
                      return returnList;
                    }
                  }
                }
              }
            }
            return returnList;
          }
          for (Node node : backupNodes) {
            if (!visited.contains(node)
                && node.getFloor().equalsIgnoreCase(n.getFloor())
                && !node.equals(n)) {
              returnList.addAll(nodesRevisited);
              visited.add(node);
              queue.add(node);
              nodeChosen = true;
              break;
            }
          }
        }
      }
    }
    return returnList;
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
