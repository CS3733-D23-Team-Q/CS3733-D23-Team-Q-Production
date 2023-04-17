package edu.wpi.cs3733.D23.teamQ.Pathfinding;

import static edu.wpi.cs3733.D23.teamQ.Pathfinding.Bfs.addEdges;
import static edu.wpi.cs3733.D23.teamQ.Pathfinding.Bfs.please;

import edu.wpi.cs3733.D23.teamQ.db.obj.Node;
import java.util.ArrayList;
import java.util.List;

public class bfsTest {

  public static void main(String[] args) {
    // Define the graph
    List<Node> graph = new ArrayList<Node>();
    Node startNode = null; // Starting node
    Node endNode = null; // End node

    Node node1 = new Node(1, 1, 1);
    Node node2 = new Node(2, 2, 2);
    Node node3 = new Node(3, 3, 3);
    Node node4 = new Node(4, 4, 4);
    Node node5 = new Node(5, 5, 5);

    // Add some edges between the nodes
    addEdges(node1);
    addEdges(node2);
    addEdges(node3);
    addEdges(node4);
    addEdges(node5);

    // Connect the nodes in a graph
    Graph g = new Graph();
    g.addNode(node1);
    g.addNode(node2);
    g.addNode(node3);
    g.addNode(node4);
    g.addNode(node5);

    // Call the bfs method to find the shortest path from node1 to node5
    please(node1, node5);
  }
}
