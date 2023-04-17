package edu.wpi.cs3733.D23.teamQ.Pathfinding;

import edu.wpi.cs3733.D23.teamQ.db.Qdb;
import edu.wpi.cs3733.D23.teamQ.db.obj.Edge;
import edu.wpi.cs3733.D23.teamQ.db.obj.Node;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Bfs {

  private int vertex;
  private LinkedList[] adj;
  private Queue<Integer> queue;

  static Qdb qdb = Qdb.getInstance();
  //    List<Edge> edges = new ArrayList<Edge>();

  public Bfs() {
    // set vertex equal to number of nodes
    vertex = 46;
  }

  Bfs(int v) {
    vertex = v;
    adj = new LinkedList[vertex];
    for (int i = 0; i < v; i++) {
      adj[i] = new LinkedList<>();
    }
    queue = new LinkedList<Integer>();
  }
  //    static Qdb qdb = Qdb.getInstance();

  public static void addEdges(Node n) {
    int nodeID = n.getNodeID();
    List<Edge> edges = qdb.getEdges(nodeID);
    n.setEdges(edges);
  }

  void BfsSearch(Node start, Node end) {
    Queue<Integer> Ids = new LinkedList<>();
    boolean[] nodes = new boolean[vertex];
    int a = 0;

    nodes[start.getNodeID()] = true;
    queue.add(start.getNodeID());

    while (queue.size() != 0) {
      start.setNodeID(queue.poll());
      Ids.add(start.getNodeID());
      if (start == end) {
        System.out.println(Ids);
        return;
      }
      for (int i = 0; i < adj[start.getNodeID()].size(); i++) {
        a = (int) adj[start.getNodeID()].get(i);
        if (!nodes[a]) {
          nodes[a] = true;
          queue.add(a);
        }
      }
    }
    return;
  }

  public static void main(String args[]) {
    Bfs g = new Bfs(4);

    Node n1 = new Node(1, 1, 1);
    Node n2 = new Node(2, 2, 2);
    Node n3 = new Node(3, 3, 3);
    Node n4 = new Node(4, 4, 4);

    addEdges(n1);
    addEdges(n2);
    addEdges(n3);
    addEdges(n4);
    //        Bfs.addEdges(n1, n2);
    //        g.addEdge(n1, n3);
    //        g.addEdge(n2, n3);
    //        g.addEdge(n3, n1);
    //        g.addEdge(n3, n4);
    //        g.addEdge(n4, n4);

    System.out.println("Following is Breadth First Traversal " + "(starting from vertex 2)");

    g.BfsSearch(n1, n3);
  }
}
