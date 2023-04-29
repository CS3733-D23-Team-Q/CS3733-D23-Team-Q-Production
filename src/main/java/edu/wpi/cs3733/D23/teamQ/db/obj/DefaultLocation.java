package edu.wpi.cs3733.D23.teamQ.db.obj;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DefaultLocation {
  int defaultLocationID;
  Location startingLocation;
  Location[] kiosks;

  public DefaultLocation(int defaultLocationID, Location startingLocation, Location[] kiosks) {
    this.defaultLocationID = defaultLocationID;
    this.startingLocation = startingLocation;
    this.kiosks = kiosks;
  }

  public DefaultLocation(Location startingLocation, Location[] kiosks) {
    this.startingLocation = startingLocation;
    this.kiosks = kiosks;
  }
}
