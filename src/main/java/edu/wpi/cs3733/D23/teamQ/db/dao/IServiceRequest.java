package edu.wpi.cs3733.D23.teamQ.db.dao;

import edu.wpi.cs3733.D23.teamQ.db.obj.ServiceRequest;

public interface IServiceRequest {
  public int getRequestID();

  public String getRequester();

  public int progressToInt(ServiceRequest.Progress progress);

  public ServiceRequest.Progress getProgress();

  public String getAssignee();

  public String getRoomNumber();

  public String getSpecialInstructions();
}
