package edu.wpi.cs3733.D23.teamQ.db.obj;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Sign {

  int kiosk;
  String date;
  String destination;
  String direction;

  public Sign(int kiosk, String date, String destination, String direction) {
    this.kiosk = kiosk;
    this.date = date;
    this.destination = destination;
    this.direction = direction;
  }
}
