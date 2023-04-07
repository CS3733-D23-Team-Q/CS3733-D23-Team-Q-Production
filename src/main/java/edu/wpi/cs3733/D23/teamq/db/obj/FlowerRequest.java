package edu.wpi.cs3733.D23.teamq.db.obj;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FlowerRequest extends ServiceRequest {
  private String note;
  private String flowerType;
  private int numberOfBouquets;

  public FlowerRequest(
      int requestID,
      String requester,
      int progress,
      String assignee,
      String roomNumber,
      String specialInstructions,
      String note,
      String flowerType,
      int numberOfBouquets) {
    super(requestID, requester, progress, assignee, roomNumber, specialInstructions);
    this.note = note;
    this.flowerType = flowerType;
    this.numberOfBouquets = numberOfBouquets;
  }

  public FlowerRequest(
      String requester,
      int progress,
      String assignee,
      String roomNumber,
      String specialInstructions,
      String note,
      String flowerType,
      int numberOfBouquets) {
    super(0, requester, progress, assignee, roomNumber, specialInstructions);
    this.note = note;
    this.flowerType = flowerType;
    this.numberOfBouquets = numberOfBouquets;
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
