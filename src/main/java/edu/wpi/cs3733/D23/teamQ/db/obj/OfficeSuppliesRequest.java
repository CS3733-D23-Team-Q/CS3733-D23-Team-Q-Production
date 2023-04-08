package edu.wpi.cs3733.D23.teamQ.db.obj;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OfficeSuppliesRequest extends ServiceRequest {
  private String item;
  private int quantity;

  public OfficeSuppliesRequest(
      int requestID,
      String requester,
      int progress,
      String assignee,
      String roomNumber,
      String specialInstructions,
      String item,
      int quantity) {
    super(requestID, requester, progress, assignee, roomNumber, specialInstructions);
    this.item = item;
    this.quantity = quantity;
  }

  public OfficeSuppliesRequest(
      String requester,
      int progress,
      String assignee,
      String roomNumber,
      String specialInstructions,
      String item,
      int quantity) {
    super(0, requester, progress, assignee, roomNumber, specialInstructions);
    this.item = item;
    this.quantity = quantity;
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
