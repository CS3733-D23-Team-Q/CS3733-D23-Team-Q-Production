package edu.wpi.cs3733.D23.teamQ.db.obj;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FurnitureRequest extends ServiceRequest {
  private String item;

  public FurnitureRequest(
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

  public FurnitureRequest(
      String requester,
      int progress,
      String assignee,
      String roomNumber,
      String specialInstructions,
      String item) {
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
