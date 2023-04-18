package edu.wpi.cs3733.D23.teamQ.Pathfinding;

import edu.wpi.cs3733.D23.teamQ.db.obj.Edge;
import edu.wpi.cs3733.D23.teamQ.db.obj.Node;
import java.util.*;

public class BFS implements IPathfinding {


  @Override
  public ArrayList<Node> run(Node start, Node target) {
    Queue<Node> queue = new LinkedList<>();
    ArrayList<Node> visited = new ArrayList<>();
    Map<Node, Node> parentMap = new HashMap<>();

    String floor = start.getFloor();
    queue.offer(start);
    visited.add(start);

    while (!queue.isEmpty()) {
      Node currNode = queue.poll();
      if (currNode == target) {
        return visited;
      }
      for (Edge edge : currNode.getEdges()) {
        Node neighbor = edge.getEndNode();
        if (!visited.contains(neighbor) && floor.equalsIgnoreCase(neighbor.getFloor())) {
          visited.add(neighbor);
          parentMap.put(neighbor, currNode);
          queue.add(neighbor);
        }
      }
    }
    // no path found
    return visited;
  }
}
