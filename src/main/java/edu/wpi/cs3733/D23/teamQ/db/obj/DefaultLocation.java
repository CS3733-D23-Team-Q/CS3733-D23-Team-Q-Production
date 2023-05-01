package edu.wpi.cs3733.D23.teamQ.db.obj;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DefaultLocation {
  String username;
  Location startingLocation;

  public DefaultLocation(String username, Location startingLocation) {
    this.username = username;
    this.startingLocation = startingLocation;
  }
}
