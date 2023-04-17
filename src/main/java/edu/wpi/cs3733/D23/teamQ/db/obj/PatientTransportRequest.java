package edu.wpi.cs3733.D23.teamQ.db.obj;

import edu.wpi.cs3733.D23.teamQ.db.dao.IServiceRequest;
import java.sql.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatientTransportRequest extends ServiceRequest implements IServiceRequest {
  private String item;

  public PatientTransportRequest(
      int requestID,
      Account requester,
      int progress,
      Account assignee,
      Node node,
      String specialInstructions,
      Date date,
      String time,
      String item) {
    super(requestID, requester, progress, assignee, node, specialInstructions, date, time);
    this.item = item;
  }

  public PatientTransportRequest(
      Account requester,
      int progress,
      Account assignee,
      Node node,
      String specialInstructions,
      Date date,
      String time,
      String item) {
    super(0, requester, progress, assignee, node, specialInstructions, date, time);
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
