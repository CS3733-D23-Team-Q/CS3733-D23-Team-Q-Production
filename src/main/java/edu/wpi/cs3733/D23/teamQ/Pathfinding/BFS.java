package edu.wpi.cs3733.D23.teamQ.Pathfinding;

import edu.wpi.cs3733.D23.teamQ.db.obj.Node;
import java.util.*;

class Graph {
  private int vertex; // No. of vertices
  private LinkedList<Node> adj[]; // Adjacency Lists
  private Queue<Node> queue;

  // Constructor
  Graph(int v) {
    vertex = v;
    adj = new LinkedList[v];
    for (int i = 0; i < v; ++i) adj[i] = new LinkedList();
  }

  // Function to add an edge into the graph
  void addEdge(int v, Node w) {
    adj[v].add(w);
  }

  //    void BFS(Node start, Node end) {
  //        Queue<Integer> nodeIDs = new LinkedList<>();
  //        boolean[] nodes = new boolean[vertex];
  //        int a = 0;
  //
  //        nodes[start.getNodeID()] = true;
  //        queue.add(start);
  //
  //        while (queue.size() != 0) {
  //            start = queue.poll();
  //            nodeIDs.add(start.getNodeID());
  //            if (start == end) {
  //                System.out.println(nodeIDs);
  //                return;
  //            }
  //            for (int i = 0; i < adj[start.getNodeID()].size(); i++) {
  //                a = (int) adj[start].get(i);
  //                if (!nodes[a]) {
  //                    nodes[a] = true;
  //                    queue.add(a);
  //                }
  //            }
  //        }
  //        return;
  //    }

  // prints BFS traversal from a given source s
  void BFS(Node start, Node end) {
    // Mark all the vertices as not visited(By default
    // set as false)
    Node s = start;
    boolean visited[] = new boolean[vertex];
    int a = 0;

    // Create a queue for BFS
    LinkedList<Integer> queue = new LinkedList<Integer>();

    // Mark the current node as visited and enqueue it
    visited[s.getNodeID()] = true;
    queue.add(s.getNodeID());

    while (queue.size() != 0) {
      // Dequeue a vertex from queue and print it
      s.setNodeID(queue.poll());
      System.out.print(s + " ");

      // Get all adjacent vertices of the dequeued
      // vertex s If a adjacent has not been visited,
      // then mark it visited and enqueue it
      //            ListIterator<Node> i = adj[s.getNodeID()].listIterator();
      for (int i = 0; i < adj.length; i++) {
        a = adj[i].element().getNodeID();

        if (!visited[a]) {
          visited[a] = true;
          queue.add(a);
        }
      }
      //            while (i.hasNext()) {
      //                Node n = i.next();
      //                if (!visited[n.getNodeID()]) {
      //                    visited[n] = true;
      //                    queue.add(n);
      //                }
      //            }
    }
  }

  public static void main(String args[]) {
    Graph g = new Graph(4);
    Node n2315 = new Node(0, 0, 2315);
    Node n1875 = new Node(0, 0, 1875);
    Node n2030 = new Node(0, 0, 2030);
    Node n2210 = new Node(0, 0, 2210);

    g.addEdge(2315, n1875);
    g.addEdge(2315, n2210);
    //        g.addEdge(());

    //        g.addEdge(0, 1);
    //        g.addEdge(0, 2);
    //        g.addEdge(1, 2);
    //        g.addEdge(2, 0);
    //        g.addEdge(2, 3);
    //        g.addEdge(3, 3);

    System.out.println("Following is Breadth First Traversal " + "(starting from vertex 2)");

    g.BFS(n2315, n2210);
  }
}
