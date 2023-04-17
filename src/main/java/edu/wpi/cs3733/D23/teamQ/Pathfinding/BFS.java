package edu.wpi.cs3733.D23.teamQ.Pathfinding;

import edu.wpi.cs3733.D23.teamQ.db.obj.Edge;
import edu.wpi.cs3733.D23.teamQ.db.obj.Node;
import java.util.*;

public class BFS implements IPathfinding {

  //  private int vertex;
  //  private LinkedList[] adj;
  //  private Queue<Integer> queue;
  //  List<Edge> edges = new ArrayList<Edge>();

  @Override
  public ArrayList<Node> run(Node start, Node target) {
    Queue<Node> queue = new LinkedList<>();
    //    Set<Node> visited = new HashSet<>();
    ArrayList<Node> visited = new ArrayList<>();
    Map<Node, Node> parentMap = new HashMap<>();

    String floor = start.getFloor();

    queue.offer(start);
    visited.add(start);

    while (!queue.isEmpty()) {
      Node currNode = queue.poll();

      System.out.println("curr " + currNode.getNodeID() + " " + currNode.getLocation());

      if (currNode == target) {
        return visited;
      }

      for (Edge edge : currNode.getEdges()) {
        Node neighbor = edge.getEndNode();
        System.out.println("move to " + neighbor.getNodeID() + " " + neighbor.getLocation());
        //        if (!visited.contains(neighbor){
        if (!visited.contains(neighbor) && floor.equalsIgnoreCase(neighbor.getFloor())) {
          visited.add(neighbor);
          parentMap.put(neighbor, currNode);
          //used to be
          //queue.offer(neighbor)
          queue.add(neighbor);
        }
      }
    }

    // no path found
    return visited;
  }

  //  public static ArrayList<Node> getPath(Map<Node, Node> parentMap, Node start, Node end) {
  //    ArrayList<Node> path = new ArrayList<>();
  //    Node currNode = end;
  //
  //    while (currNode != start) {
  //      path.add(currNode);
  //      currNode = parentMap.get(currNode);
  //    }
  //
  //    path.add(start);
  //    Collections.reverse(path);
  //    return path;
  //  }
}
//    Queue<Node> queue = new LinkedList<>();
//    Set<Node> visited = new HashSet<>();
//    Map<Node, Node> parent = new HashMap<>(); // used to reconstruct the path
//    ArrayList<Node> result = new ArrayList<>();
//
//    queue.offer(start);
//    visited.add(start);
//    parent.put(start, null);
//
//    while (!queue.isEmpty()) {
//      Node curr = queue.poll();
//      if (curr == target) {
//        // reconstruct the path
//        while (curr != null) {
//          result.add(0, curr);
//          curr = parent.get(curr);
//        }
//        return result;
//      }
//
//      for (Node neighbor : curr.getNeighbors()) {
//        if (!visited.contains(neighbor)) {
//          visited.add(neighbor);
//          parent.put(neighbor, curr);
//          queue.offer(neighbor);
//        }
//      }
//    }
//
//    return result; // no path exists
//  }

//  public ArrayList<Node> run(Node startNode, Node endNode) {
//    //    Graph graph = startNode.getGraph();
//    Set<Node> visited = new HashSet<Node>();
//    LinkedList<Node> queue = new LinkedList<Node>();
//    visited.add(startNode);
//    queue.add(startNode);
//    ArrayList<Node> path = new ArrayList<>();
//
//    while (queue.size() != 0) {
//
//      startNode = queue.poll();
//      path.add(startNode);
//      System.out.println(startNode.getNodeID() + " " + startNode.getLocation());
//
//      if (startNode.getNodeID() == endNode.getNodeID()) {
//        break;
//      }
//
//      for (Edge edge : startNode.getEdges()) {
//        Node adjNode = edge.getEndNode();
//        if (!visited.contains(adjNode)) {
//          visited.add(adjNode);
//          queue.add(adjNode);
//        }
//      }
//    }
//    return path;
//  }
