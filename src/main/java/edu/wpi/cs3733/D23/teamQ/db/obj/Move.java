package edu.wpi.cs3733.D23.teamQ.db.obj;

import java.sql.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Move {
  private int moveID;
  private Node node;
  private String longName;
  private Date date;

  public Move(Node node, String longName, Date date) {
    this.node = node;
    this.longName = longName;
    this.date = date;
  }

  public Move(int moveID, Node node, String longName, Date date) {
    this.moveID = moveID;
    this.node = node;
    this.longName = longName;
    this.date = date;
  }

  public String toString() {
    return this.moveID
        + ","
        + this.node.getNodeID()
        + ","
        + this.longName
        + ","
        + this.date;
  }
}
