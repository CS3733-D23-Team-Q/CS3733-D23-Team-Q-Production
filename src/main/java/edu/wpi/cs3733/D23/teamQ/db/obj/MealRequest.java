package edu.wpi.cs3733.D23.teamQ.db.obj;

import edu.wpi.cs3733.D23.teamQ.db.dao.IServiceRequest;
import java.lang.reflect.Type;
import java.sql.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MealRequest extends ServiceRequest implements IServiceRequest {
  private static final String type = "Meal Request";
  private String drink;
  private String entree;
  private String side;
  private Type requestType = this.getClass();

  public MealRequest(
      int requestID,
      Node node,
      Account requester,
      Account assignee,
      String specialInstructions,
      Date date,
      String time,
      int progress,
      String drink,
      String entree,
      String side) {
    super(requestID, node, requester, assignee, specialInstructions, date, time, progress, type);
    this.drink = drink;
    this.entree = entree;
    this.side = side;
  }

  public MealRequest(
      Node node,
      Account requester,
      Account assignee,
      String specialInstructions,
      Date date,
      String time,
      int progress,
      String drink,
      String entree,
      String side) {
    super(0, node, requester, assignee, specialInstructions, date, time, progress, type);
    this.drink = drink;
    this.entree = entree;
    this.side = side;
  }
}
