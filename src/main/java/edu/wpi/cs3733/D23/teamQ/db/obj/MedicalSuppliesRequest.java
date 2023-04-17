package edu.wpi.cs3733.D23.teamQ.db.obj;

import edu.wpi.cs3733.D23.teamQ.db.dao.IServiceRequest;
import java.lang.reflect.Type;
import java.sql.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MedicalSuppliesRequest extends ServiceRequest implements IServiceRequest {
  private String item;
  private int quantity;
  private Type requestType = this.getClass();

  public MedicalSuppliesRequest(
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

  public MedicalSuppliesRequest(
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

  public int progressToInt(ServiceRequest.Progress progress) {
    if (progress == ServiceRequest.Progress.BLANK) {
      return 0;
    } else if (progress == ServiceRequest.Progress.PROCESSING) {
      return 1;
    } else {
      return 2;
    }
  }
}
