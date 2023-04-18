package edu.wpi.cs3733.D23.teamQ.db.obj;

import edu.wpi.cs3733.D23.teamQ.db.dao.IServiceRequest;
import java.lang.reflect.Type;
import java.sql.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FlowerRequest extends ServiceRequest implements IServiceRequest {
  private String note;
  private String flowerType;
  private int numberOfBouquets;
  private Type requestType = this.getClass();

  public FlowerRequest(
      int requestID,
      Account requester,
      int progress,
      Account assignee,
      Node node,
      String specialInstructions,
      Date date,
      String time,
      String note,
      String flowerType,
      int numberOfBouquets) {
    super(requestID, requester, progress, assignee, node, specialInstructions, date, time);
    this.note = note;
    this.flowerType = flowerType;
    this.numberOfBouquets = numberOfBouquets;
  }

  public FlowerRequest(
      Account requester,
      int progress,
      Account assignee,
      Node node,
      String specialInstructions,
      Date date,
      String time,
      String note,
      String flowerType,
      int numberOfBouquets) {
    super(0, requester, progress, assignee, node, specialInstructions, date, time);
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
