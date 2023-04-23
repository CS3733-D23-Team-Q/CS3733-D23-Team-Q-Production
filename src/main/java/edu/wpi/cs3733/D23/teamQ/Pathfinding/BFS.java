package edu.wpi.cs3733.D23.teamQ.Pathfinding;

import edu.wpi.cs3733.D23.teamQ.db.obj.Edge;
import edu.wpi.cs3733.D23.teamQ.db.obj.Node;
import java.util.*;

public class BFS implements IPathfinding {

  @Override
  public ArrayList<Node> run(Node start, Node target) {
    ArrayList<Node> visited = new ArrayList<>();
    Queue<Node> queue = new LinkedList<>();
    Map<Node, Node> parentMap = new HashMap<>();

    String endFloor = target.getFloor();
    queue.add(start);
    visited.add(start);

    while (!queue.isEmpty()) {
      Node n = queue.poll();
      if (n == target) {
        return visited;
      }
      if (n.getLocation().getNodeType().equalsIgnoreCase("ELEV")
          && !n.getFloor()
              .equalsIgnoreCase(endFloor)) { // branch to send to correct floor, if needed
        ArrayList<Node> elevatorNodes = new ArrayList<Node>();
        for (Edge edge : n.getEdges()) {
          elevatorNodes.add(edge.getStartNode());
          elevatorNodes.add(edge.getEndNode());
        }
        for (Node node : elevatorNodes) {
          if (node.getFloor().equalsIgnoreCase(endFloor) && !node.equals(n)) {
            visited.add(node);
            parentMap.put(node, n);
            queue.add(node);
          }
        }
      }
      if (n.getLocation().getNodeType().equalsIgnoreCase("ELEV")
          && n.getFloor()
              .equalsIgnoreCase(endFloor)) { // branch to send to correct floor, if needed
        ArrayList<Node> elevatorNodes = new ArrayList<Node>();
        for (Edge edge : n.getEdges()) {
          elevatorNodes.add(edge.getStartNode());
          elevatorNodes.add(edge.getEndNode());
        }
        for (Node node : elevatorNodes) {
          if (node.getFloor().equalsIgnoreCase(n.getFloor())
              && !visited.contains(node)) { // might have to exclude ele
            visited.add(node); // vators as well
            parentMap.put(node, n);
            queue.add(node);
          }
        }
      }
      if (n.getLocation().getNodeType().equalsIgnoreCase("STAI")
          && !n.getFloor()
              .equalsIgnoreCase(endFloor)) { // branch to send to correct floor, if needed
        ArrayList<Node> stairNodes = new ArrayList<Node>();
        for (Edge edge : n.getEdges()) {
          stairNodes.add(edge.getStartNode());
          stairNodes.add(edge.getEndNode());
        }
        for (Node node : stairNodes) {
          if (node.getFloor().equalsIgnoreCase(endFloor) && !node.equals(n)) {
            visited.add(node);
            parentMap.put(node, n);
            queue.add(node);
          }
        }
      }
      if (n.getLocation().getNodeType().equalsIgnoreCase("STAI")
          && n.getFloor()
              .equalsIgnoreCase(endFloor)) { // branch to send to correct floor, if needed
        ArrayList<Node> stairNodes = new ArrayList<Node>();
        for (Edge edge : n.getEdges()) {
          stairNodes.add(edge.getStartNode());
          stairNodes.add(edge.getEndNode());
        }
        for (Node node : stairNodes) {
          if (node.getFloor().equalsIgnoreCase(n.getFloor()) && !node.equals(n)) {
            visited.add(node);
            parentMap.put(node, n);
            queue.add(node);
          }
        }
      }
      for (Edge edge : n.getEdges()) {
        Node neighbor = edge.getEndNode();
        if (!visited.contains(neighbor)
            && n.getFloor().equalsIgnoreCase(neighbor.getFloor())
            && !neighbor.equals(n)) {
          visited.add(neighbor);
          parentMap.put(neighbor, n);
          queue.add(neighbor);
          System.out.println();
          System.out.println(
              "added node "
                  + neighbor.getLocation().getShortName()
                  + " on floor "
                  + neighbor.getFloor());
        }
        if (!visited.contains(edge.getStartNode())
            && n.getFloor().equalsIgnoreCase(edge.getStartNode().getFloor())
            && !n.equals(edge.getStartNode())) {
          visited.add(edge.getStartNode());
          parentMap.put(edge.getStartNode(), n);
          queue.add(edge.getStartNode());
          System.out.println();
          System.out.println(
              "added node "
                  + neighbor.getLocation().getShortName()
                  + " on floor "
                  + neighbor.getFloor());
        }
      }
    }
    // no path found
    return visited;
  }
}
