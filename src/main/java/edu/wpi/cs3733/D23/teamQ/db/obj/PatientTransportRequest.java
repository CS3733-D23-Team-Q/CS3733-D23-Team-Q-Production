package edu.wpi.cs3733.D23.teamQ.db.obj;

import edu.wpi.cs3733.D23.teamQ.db.dao.IServiceRequest;
import java.lang.reflect.Type;
import java.sql.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatientTransportRequest extends ServiceRequest implements IServiceRequest {
  private static final String type = "Patient Transport Request";
  private String item;
  private Type requestType = this.getClass();

  public PatientTransportRequest(
      int requestID,
      Node node,
      Account requester,
      Account assignee,
      String specialInstructions,
      Date date,
      String time,
      int progress,
      String item) {
    super(0, node, assignee, requester, specialInstructions, date, time, progress, type);
    this.item = item;
  }

  public PatientTransportRequest(
      Node node,
      Account requester,
      Account assignee,
      String specialInstructions,
      Date date,
      String time,
      int progress,
      String item) {
    super(0, node, assignee, requester, specialInstructions, date, time, progress, type);
    this.item = item;
  }
}
