package edu.wpi.cs3733.D23.teamQ.Pathfinding;

import edu.wpi.cs3733.D23.teamQ.db.obj.Node;
import java.util.ArrayList;

public class Context {
  private IPathfinding pathfindingAlgorithm;

  public Context() {}

  public Context(IPathfinding pathfindingAlgorithm) {
    this.pathfindingAlgorithm = pathfindingAlgorithm;
  }

  public IPathfinding getPathfindingAlgorithm() {
    return pathfindingAlgorithm;
  }

  public void setPathfindingAlgorithm(IPathfinding pathfindingAlgorithm) {
    this.pathfindingAlgorithm = pathfindingAlgorithm;
  }

  public ArrayList<Node> run(Node start, Node target) {
    return pathfindingAlgorithm.run(start, target);
  }
}
