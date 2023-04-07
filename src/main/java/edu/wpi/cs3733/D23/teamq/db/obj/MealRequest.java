package edu.wpi.cs3733.D23.teamq.db.obj;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MealRequest extends ServiceRequest {
  private String drink;
  //water, tea, coffee, coca cola, ginger ale, sprite, gatorade
  private String entree;
  //chicken parm, steak tips, hamburger, hot dog, fish&chips, lasagna
  private String side;
  //fries, onion rings, soup of the day, caesar salad

  public MealRequest(
      int requestID,
      String requester,
      int progress,
      String assignee,
      String roomNumber,
      String specialInstructions,
      String drink,
      String entree,
      String side) {
    super(requestID, requester, progress, assignee, roomNumber, specialInstructions);
    this.drink = drink;
    this.entree = entree;
    this.side = side;
  }

  public MealRequest(
      String requester,
      int progress,
      String assignee,
      String roomNumber,
      String specialInstructions,
      String drink,
      String entree,
      String side) {
    super(0, requester, progress, assignee, roomNumber, specialInstructions);
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
