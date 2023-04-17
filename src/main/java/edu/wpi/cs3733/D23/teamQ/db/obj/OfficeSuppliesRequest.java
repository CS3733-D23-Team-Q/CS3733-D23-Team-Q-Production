package edu.wpi.cs3733.D23.teamQ.db.obj;

import edu.wpi.cs3733.D23.teamQ.db.dao.IServiceRequest;

import java.lang.reflect.Type;
import java.sql.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OfficeSuppliesRequest extends ServiceRequest implements IServiceRequest {
  private String item;
  private int quantity;
  private Type requestType = this.getClass();

  public OfficeSuppliesRequest(
      int requestID,
      Account requester,
      int progress,
      Account assignee,
      Node node,
      String specialInstructions,
      Date date,
      String time,
      String item,
      int quantity) {
    super(requestID, requester, progress, assignee, node, specialInstructions, date, time);
    this.item = item;
    this.quantity = quantity;
  }

  public OfficeSuppliesRequest(
      Account requester,
      int progress,
      Account assignee,
      Node node,
      String specialInstructions,
      Date date,
      String time,
      String item,
      int quantity) {
    super(0, requester, progress, assignee, node, specialInstructions, date, time);
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
