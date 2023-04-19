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

    String floor = start.getFloor();
    queue.add(start);
    visited.add(start);

    while (!queue.isEmpty()) {
      Node n = queue.poll();
      if (n == target) {
        return visited;
      }
      for (Edge edge : n.getEdges()) {
        Node neighbor = edge.getEndNode();
        if (!visited.contains(neighbor) && floor.equalsIgnoreCase(neighbor.getFloor())) {
          //        if (!visited.contains(neighbor) && floor.equalsIgnoreCase(neighbor.getFloor()))
          visited.add(neighbor);
          // System.out.println("on floor " + n.getFloor());
          parentMap.put(neighbor, n);
          queue.add(neighbor);
        }
        if (!visited.contains(edge.getStartNode())
            && floor.equalsIgnoreCase(edge.getStartNode().getFloor())) {
          visited.add(edge.getStartNode());
          parentMap.put(edge.getStartNode(), n);
          queue.add(edge.getStartNode());
        }
      }
    }
    // no path found
    return visited;
  }
}
