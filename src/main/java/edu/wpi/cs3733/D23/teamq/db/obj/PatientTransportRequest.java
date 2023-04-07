package edu.wpi.cs3733.D23.teamq.db.obj;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatientTransportRequest extends ServiceRequest {
  private String transport;
  //stretcher, wheelchair, crutches

  public PatientTransportRequest(
      int requestID,
      String requester,
      int progress,
      String assignee,
      String roomNumber,
      String specialInstructions,
      String dateTime,
      String transport) {
    super(requestID, requester, progress, assignee, roomNumber, specialInstructions);
    this.transport = transport;
  }

  public PatientTransportRequest(
      String requester,
      int progress,
      String assignee,
      String roomNumber,
      String specialInstructions,
      String dateTime,
      String transport) {
    super(0, requester, progress, assignee, roomNumber, specialInstructions);
    this.transport = transport;
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
