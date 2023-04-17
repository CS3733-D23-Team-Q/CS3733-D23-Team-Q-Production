package edu.wpi.cs3733.D23.teamQ.Pathfinding;

import edu.wpi.cs3733.D23.teamQ.db.obj.Node;
import java.util.ArrayList;
import java.util.List;

public class Graph {
  private List<Node> nodes;

  public Graph() {
    nodes = new ArrayList<Node>();
  }

  public void addNode(Node node) {
    nodes.add(node);
  }

  public List<Node> getNodes() {
    return nodes;
  }
}
