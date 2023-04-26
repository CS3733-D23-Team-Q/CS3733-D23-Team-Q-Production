package edu.wpi.cs3733.D23.teamQ.db.obj;

import edu.wpi.cs3733.D23.teamQ.db.dao.IServiceRequest;
import java.lang.reflect.Type;
import java.sql.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FurnitureRequest extends ServiceRequest implements IServiceRequest {
  private String item;
  private Type requestType = this.getClass();

  public FurnitureRequest(
      int requestID,
      Node node,
      Account assignee,
      Account requester,
      String specialInstructions,
      Date date,
      String time,
      int progress,
      String item) {
    super(requestID, node, assignee, requester, specialInstructions, date, time, progress);
    this.item = item;
  }

  public FurnitureRequest(
      Node node,
      Account assignee,
      Account requester,
      String specialInstructions,
      Date date,
      String time,
      int progress,
      String item) {
    super(0, node, assignee, requester, specialInstructions, date, time, progress);
    this.item = item;
  }
}
