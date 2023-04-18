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
      Node node,
      Account requester,
      Account assignee,
      String specialInstructions,
      Date date,
      String time,
      int progress,
      String flowerType,
      int numberOfBouquets) {
    super(0, node, assignee, requester, specialInstructions, date, time, progress);
    this.note = note;
    this.flowerType = flowerType;
    this.numberOfBouquets = numberOfBouquets;
  }

  public FlowerRequest(
      Node node,
      Account requester,
      Account assignee,
      String specialInstructions,
      Date date,
      String time,
      int progress,
      String flowerType,
      int numberOfBouquets) {
    super(0, node, assignee, requester, specialInstructions, date, time, progress);
    this.note = note;
    this.flowerType = flowerType;
    this.numberOfBouquets = numberOfBouquets;
  }
}
