package edu.wpi.cs3733.D23.teamQ.db.obj;

import java.sql.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServiceRequest {

  private int requestID;
  private Progress progress;
  Account requester;
  Account assignee;
  private Node node;
  private int nodeID;
  private String specialInstructions;
  private Date date;
  private String time;

  public enum Progress {
    BLANK,
    PROCESSING,
    DONE
  }

  public ServiceRequest(
      int requestID,
      Account requester,
      int progress,
      Account assignee,
      Node node,
      String specialInstructions,
      Date date,
      String time) {
    this.requestID = requestID;
    if (progress == 0) {
      this.progress = Progress.BLANK;
    } else if (progress == 1) {
      this.progress = Progress.PROCESSING;
    } else if (progress == 2) {
      this.progress = Progress.DONE;
    } else {
      this.progress = null;
    }
    this.requester = requester;
    this.assignee = assignee;
    this.node = node;
    this.specialInstructions = specialInstructions;
    this.date = date;
    this.time = time;
    this.nodeID = node.getNodeID();
  }
}
