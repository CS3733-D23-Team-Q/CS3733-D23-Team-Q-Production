package edu.wpi.cs3733.D23.teamQ.db.obj;

import edu.wpi.cs3733.D23.teamQ.db.dao.IServiceRequest;
import java.lang.reflect.Type;
import java.sql.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConferenceRequest extends ServiceRequest implements IServiceRequest {
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
    super(0, node, assignee, requester, specialInstructions, date, time, progress);
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
    super(0, node, assignee, requester, specialInstructions, date, time, progress);
    this.foodChoice = foodChoice;
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
