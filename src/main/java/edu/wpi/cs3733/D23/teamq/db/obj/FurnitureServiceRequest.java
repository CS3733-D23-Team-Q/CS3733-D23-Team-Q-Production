package edu.wpi.cs3733.D23.teamq.db.obj;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FurnitureServiceRequest extends ServiceRequest {
  private String item;
  //items are: desk, desk chair, waiting room table, waiting room chair, waiting room couch, examination table

  public FurnitureServiceRequest(
      int requestID,
      String requester,
      int progress,
      String assignee,
      String roomNumber,
      String specialInstructions,
      String item) {
    super(requestID, requester, progress, assignee, roomNumber, specialInstructions);
    this.item = item;
  }

  public FurnitureServiceRequest(
      String requester,
      int progress,
      String assignee,
      String roomNumber,
      String specialInstructions,
      String dateTime,
      String foodChoice) {
    super(0, requester, progress, assignee, roomNumber, specialInstructions);
    this.item = item;
  }

  public int progressToInt(Progress progress) {
    if (progress == Progress.BLANK) {
      return 0;
    } else if (progress == Progress.PROCESSING) {
      return 1;
    } else {
      return 2;
    }
  }
}
