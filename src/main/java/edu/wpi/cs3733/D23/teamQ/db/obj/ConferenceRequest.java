package edu.wpi.cs3733.D23.teamQ.db.obj;

import edu.wpi.cs3733.D23.teamQ.db.dao.IServiceRequest;
import java.lang.reflect.Type;
import java.sql.Date;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ConferenceRequest extends ServiceRequest implements IServiceRequest {
  private static final String type = "Conference Request";
  private String dateTime;
  private String foodChoice;
  private Type requestType = this.getClass();

  public ConferenceRequest(
      int requestID,
      Node node,
      Account requester,
      Account assignee,
      String specialInstructions,
      Date date,
      String time,
      int progress,
      String foodChoice) {
    super(requestID, node, requester, assignee, specialInstructions, date, time, progress, type);
    this.foodChoice = foodChoice;
  }

  public ConferenceRequest(
      Node node,
      Account requester,
      Account assignee,
      String specialInstructions,
      Date date,
      String time,
      int progress,
      String foodChoice) {
    super(0, node, requester, assignee, specialInstructions, date, time, progress, type);
    this.foodChoice = foodChoice;
  }
}
