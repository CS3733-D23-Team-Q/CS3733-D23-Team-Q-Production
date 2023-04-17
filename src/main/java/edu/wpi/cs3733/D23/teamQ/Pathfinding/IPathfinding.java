package edu.wpi.cs3733.D23.teamQ.Pathfinding;

import edu.wpi.cs3733.D23.teamQ.db.obj.Node;
import java.util.ArrayList;

public interface IPathfinding {
  public ArrayList<Node> run(Node start, Node target);
}
