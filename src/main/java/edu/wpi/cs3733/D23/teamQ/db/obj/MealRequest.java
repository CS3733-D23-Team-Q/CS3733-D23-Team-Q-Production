package edu.wpi.cs3733.D23.teamQ.db.obj;

import edu.wpi.cs3733.D23.teamQ.db.dao.IServiceRequest;
import java.sql.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MealRequest extends ServiceRequest implements IServiceRequest {
  private String drink;
  private String entree;
  private String side;

  public MealRequest(
      int requestID,
      String requester,
      int progress,
      String assignee,
      Node node,
      String specialInstructions,
      Date date,
      String time,
      String drink,
      String entree,
      String side) {
    super(requestID, requester, progress, assignee, node, specialInstructions, date, time);
    this.drink = drink;
    this.entree = entree;
    this.side = side;
  }

  public MealRequest(
      String requester,
      int progress,
      String assignee,
      Node node,
      String specialInstructions,
      Date date,
      String time,
      String drink,
      String entree,
      String side) {
    super(0, requester, progress, assignee, node, specialInstructions, date, time);
    this.drink = drink;
    this.entree = entree;
    this.side = side;
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
