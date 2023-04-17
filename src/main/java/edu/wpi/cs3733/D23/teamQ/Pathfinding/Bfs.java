package edu.wpi.cs3733.D23.teamQ.Pathfinding;

import edu.wpi.cs3733.D23.teamQ.db.Qdb;
import edu.wpi.cs3733.D23.teamQ.db.obj.Edge;
import edu.wpi.cs3733.D23.teamQ.db.obj.Node;
import java.util.*;

// public class Bfs {
//
//  private int vertex;
//  private LinkedList[] adj;
//  private Queue<Integer> queue;
//
//  static Qdb qdb = Qdb.getInstance();
//  //    List<Edge> edges = new ArrayList<Edge>();
//
//  public Bfs() {
//    // set vertex equal to number of nodes
//    vertex = 46;
//  }
//
//  Bfs(int v) {
//    vertex = v;
//    adj = new LinkedList[vertex];
//    for (int i = 0; i < v; i++) {
//      adj[i] = new LinkedList<>();
//    }
//    queue = new LinkedList<Integer>();
//  }
//  //    static Qdb qdb = Qdb.getInstance();
//
//  public static void addEdges(Node n) {
//    int nodeID = n.getNodeID();
//    List<Edge> edges = qdb.getEdges(nodeID);
//    n.setEdges(edges);
//  }
//
//  void BfsSearch(Node start, Node end) {
//    Queue<Integer> Ids = new LinkedList<>();
//    boolean[] nodes = new boolean[vertex];
//    int a = 0;
//
//    nodes[start.getNodeID()] = true;
//    queue.add(start.getNodeID());
//
//    while (queue.size() != 0) {
//      start.setNodeID(queue.poll());
//      Ids.add(start.getNodeID());
//      if (start == end) {
//        System.out.println(Ids);
//        return;
//      }
//      for (int i = 0; i < adj[start.getNodeID()].size(); i++) {
//        a = (int) adj[start.getNodeID()].get(i);
//        if (!nodes[a]) {
//          nodes[a] = true;
//          queue.add(a);
//        }
//      }
//    }
//    return;
//  }
//
//  public static void main(String args[]) {
//    Bfs g = new Bfs(4);
//
//    Node n1 = new Node(1, 1, 1);
//    Node n2 = new Node(2, 2, 2);
//    Node n3 = new Node(3, 3, 3);
//    Node n4 = new Node(4, 4, 4);
//
//    addEdges(n1);
//    addEdges(n2);
//    addEdges(n3);
//    addEdges(n4);
//    //        Bfs.addEdges(n1, n2);
//    //        g.addEdge(n1, n3);
//    //        g.addEdge(n2, n3);
//    //        g.addEdge(n3, n1);
//    //        g.addEdge(n3, n4);
//    //        g.addEdge(n4, n4);
//
//    System.out.println("Following is Breadth First Traversal " + "(starting from vertex 2)");
//
//    g.BfsSearch(n1, n3);
//  }
// }

public class Bfs {
  static Qdb qdb = Qdb.getInstance();
  //  private Graph graph;
  static List<Node> graph = new ArrayList<Node>();

  // Populate the graph with nodes and edges
  //    Node node1 = new Node(1, 0, 0);
  //    Node node2 = new Node(2, 0, 1);
  //    Node node3 = new Node(3, 1, 0);
  //    Node node4 = new Node(4, 1, 1);
  //    Edge edge1 = new Edge(node1, node2);
  //    Edge edge2 = new Edge(node1, node3);
  //    Edge edge3 = new Edge(node2, node4);
  //    Edge edge4 = new Edge(node3, node4);
  //    node1.addBranch(edge1);
  //    node1.addBranch(edge2);
  //    node2.addBranch(edge3);
  //    node3.addBranch(edge4);
  //    graph.add(node1);
  //    graph.add(node2);
  //    graph.add(node3);
  //    graph.add(node4);
  //
  //    startNode = node1;
  //    endNode = node4;

  //    public static void main(String[] args) {
  // Create some nodes

  public static List<Node> please(Node startNode, Node endNode) {
    Graph graph = startNode.getGraph(); // Get the graph from the starting node
    Set<Node> visited = new HashSet<Node>(); // Set to keep track of visited nodes
    LinkedList<Node> queue = new LinkedList<Node>(); // Queue for BFS traversal
    visited.add(startNode); // Mark the starting node as visited
    queue.add(startNode); // Add the starting node to the queue

    while (queue.size() != 0) {
      // Dequeue a node from the queue and print it
      startNode = queue.poll();
      System.out.print(startNode.getNodeID() + " ");

      // If the dequeued node is the destination node, stop the search
      if (startNode.getNodeID() == endNode.getNodeID()) {
        break;
      }

      // Enqueue all adjacent nodes of the dequeued node that have not been visited yet
      for (Edge edge : startNode.getEdges()) {
        Node adjNode = edge.getEndNode();
        if (!visited.contains(adjNode)) {
          visited.add(adjNode);
          queue.add(adjNode);
        }
      }
    }
    return null;
  }
  //
  //    boolean[] visited = new boolean[graph.size()]; // Array to keep track of visited nodes
  //    LinkedList<Node> queue = new LinkedList<Node>(); // Queue for BFS traversal
  //    visited[startNode.getNodeID() - 1] = true; // Mark the starting node as visited
  //    queue.add(startNode); // Add the starting node to the queue
  //
  //    while (queue.size() != 0) {
  //      // Dequeue a node from the queue and print it
  //      startNode = queue.poll();
  //      System.out.print(startNode.getNodeID() + " ");
  //
  //      // If the dequeued node is the destination node, stop the search
  //      if (startNode.getNodeID() == endNode.getNodeID()) {
  //        break;
  //      }
  //
  //      // Enqueue all adjacent nodes of the dequeued node that have not been visited yet
  //      for (Edge edge : startNode.getEdges()) {
  //        Node adjNode = edge.getEndNode();
  //        if (!visited[adjNode.getNodeID() - 1]) {
  //          visited[adjNode.getNodeID() - 1] = true;
  //          queue.add(adjNode);
  //        }
  //      }
  //    }
  //    return null;
  //  }


  public static void addEdges(Node n) {
    int nodeID = n.getNodeID();
    List<Edge> edges = qdb.getEdges(nodeID);
    n.setEdges(edges);
  }

//  public static void addEdges(Node node, List<Edge> edges) {
//    int nodeID = node.getNodeID();
//    List<Edge> nodeEdges = new ArrayList<>();
//
//    for (Edge edge : edges) {
//      if (edge.getStartNode().getNodeID() == nodeID) {
//        nodeEdges.add(edge);
//      }
//    }
//
//    node.setEdges(nodeEdges);
//  }

}
