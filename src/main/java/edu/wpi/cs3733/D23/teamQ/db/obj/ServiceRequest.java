package edu.wpi.cs3733.D23.teamQ.db.obj;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServiceRequest {

  private int requestID;
  private Progress progress;
  private String requester;
  private String assignee;
  private Node node;
  private String specialInstructions;
  int time;
  Date date;

  public enum Progress {
    BLANK,
    PROCESSING,
    DONE
  }

  public ServiceRequest(
      int requestID,
      String requester,
      int progress,
      String assignee,
      Node node,
      String specialInstructions, int time, Date date) {
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
    this.time=time;
    this.date=date;
  }
}
