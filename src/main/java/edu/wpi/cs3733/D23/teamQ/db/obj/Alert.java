package edu.wpi.cs3733.D23.teamQ.db.obj;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Alert {
  private int alertID;
  private long timestamp;
  private String message;

  public Alert(int alertID, long timestamp, String message) {
    this.alertID = alertID;
    this.timestamp = timestamp;
    this.message = message;
  }

  public Alert(long timestamp, String message) {
    this.timestamp = timestamp;
    this.message = message;
  }

  @Override
  public String toString() {
    return timestamp + "," + message + "," + alertID;
  }
}
