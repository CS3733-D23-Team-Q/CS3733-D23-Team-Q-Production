package edu.wpi.cs3733.D23.teamQ.db.obj;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Edge {
  private int edgeID;
  private Node startNode;
  private Node endNode;

  public Edge(int edgeID, Node startNode, Node endNode) {
    this.edgeID = edgeID;
    this.startNode = startNode;
    this.endNode = endNode;
  }

  public Edge(Node startNode, Node endNode) {
    this.startNode = startNode;
    this.endNode = endNode;
  }

  public Edge() {}

  public int getWeight() {
    int xDist = Math.abs(this.getStartNode().getXCoord() - this.getEndNode().getXCoord());
    int yDist = Math.abs(this.getStartNode().getYCoord() - this.getEndNode().getYCoord());
    int weight = (int) Math.sqrt(xDist * xDist + yDist * yDist);
    return weight;
  }

  public Node getStartNode() {
    return startNode;
  }

  public Node getEndNode() {
    return endNode;
  }

  public String toString() {
    return edgeID + "," + startNode.getNodeID() + "," + endNode.getNodeID();
  }
}
