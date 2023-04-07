package edu.wpi.cs3733.D23.teamQ.db.obj;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Move {
  private int moveID;
  private Node node;
  private String longName;
  private String date;

  public Move(Node node, String longName, String date) {
    this.node = node;
    this.longName = longName;
    this.date = date;
  }

  public Move(int moveID, Node node, String longName, String date) {
    this.node = node;
    this.longName = longName;
    this.date = date;
  }

  public String toString() {
    return "moveID: "
        + this.moveID
        + "node: "
        + this.node.getNodeID()
        + ", longName: "
        + this.longName
        + ", date: "
        + this.date;
  }
}
