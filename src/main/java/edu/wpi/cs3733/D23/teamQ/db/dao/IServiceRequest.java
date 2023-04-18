package edu.wpi.cs3733.D23.teamQ.db.dao;

import edu.wpi.cs3733.D23.teamQ.db.obj.Account;
import edu.wpi.cs3733.D23.teamQ.db.obj.Node;
import edu.wpi.cs3733.D23.teamQ.db.obj.ServiceRequest;

public interface IServiceRequest {
  public int getRequestID();

  public Account getRequester();

  public int progressToInt(ServiceRequest.Progress progress);

  public ServiceRequest.Progress getProgress();

  public Account getAssignee();

  public Node getNode();

  public String getSpecialInstructions();
}
