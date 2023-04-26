package edu.wpi.cs3733.D23.teamQ.db.obj;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Location {
  private int nodeID;
  private String longName;
  private String shortName;
  private String nodeType;

  public Location(int nodeID, String longName, String shortName, String nodeType) {
    this.nodeID = nodeID;
    this.longName = longName;
    this.shortName = shortName;
    this.nodeType = nodeType;
  }

  public String toString() {
    return nodeID + "," + longName + "," + shortName + "," + nodeType;
  }
}
