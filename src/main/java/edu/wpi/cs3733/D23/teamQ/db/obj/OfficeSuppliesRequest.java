package edu.wpi.cs3733.D23.teamQ.db.obj;

import edu.wpi.cs3733.D23.teamQ.db.dao.IServiceRequest;
import java.lang.reflect.Type;
import java.sql.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OfficeSuppliesRequest extends ServiceRequest implements IServiceRequest {
  private static final String type = "Office Supplies Request";
  private String item;
  private int quantity;
  private Type requestType = this.getClass();

  public OfficeSuppliesRequest(
      int requestID,
      Node node,
      Account requester,
      Account assignee,
      String specialInstructions,
      Date date,
      String time,
      int progress,
      String item,
      int quantity) {
    super(requestID, node, requester, assignee, specialInstructions, date, time, progress, type);
    this.item = item;
    this.quantity = quantity;
  }

  public OfficeSuppliesRequest(
      Node node,
      Account requester,
      Account assignee,
      String specialInstructions,
      Date date,
      String time,
      int progress,
      String item,
      int quantity) {
    super(0, node, requester, assignee, specialInstructions, date, time, progress, type);
    this.item = item;
    this.quantity = quantity;
  }
}
