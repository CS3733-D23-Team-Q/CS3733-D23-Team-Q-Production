package edu.wpi.cs3733.D23.teamQ.db.obj;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Node {
  private int nodeID;
  private int xCoord;
  private int yCoord;
  private String floor;
  private String building;
  private List<Edge> edges = new ArrayList<>();
  private Location location;
  private int locID;

  private double f = Double.MAX_VALUE;
  private double g = Double.MAX_VALUE;
  private static int idCounter = 0;
  private int weight;
  Node parent = null;

  public Node(int nodeID, int xCoord, int yCoord, String floor, String building) {
    this.nodeID = nodeID;
    this.xCoord = xCoord;
    this.yCoord = yCoord;
    this.floor = floor;
    this.building = building;

    String x = Integer.toString(xCoord);
    String y = Integer.toString(yCoord);
    String xy = x + y;
    this.locID = Integer.parseInt(xy);
  }

  public Node(int xCoord, int yCoord, int nodeID) {
    this.setXCoord(xCoord);
    this.setYCoord(yCoord);
    this.setNodeID(nodeID);
    this.edges = new ArrayList<Edge>();
  }

  public String nodeToString() {
    return "nodeID: "
        + this.nodeID
        + ", xCoord: "
        + this.xCoord
        + ", yCoord: "
        + this.yCoord
        + ", floor: "
        + this.floor
        + ", building: "
        + this.building
        + ", edges: "
        + this.edges
        + ", location: "
        + this.location;
  }

  public void addBranch(Edge e) {
    this.edges.add(e);
  }
}
