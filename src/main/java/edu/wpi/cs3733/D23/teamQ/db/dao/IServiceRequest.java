package edu.wpi.cs3733.D23.teamQ.db.dao;

import edu.wpi.cs3733.D23.teamQ.db.obj.Node;
import edu.wpi.cs3733.D23.teamQ.db.obj.ServiceRequest;

import java.sql.Date;

public interface IServiceRequest {
  public int getRequestID();

  public String getRequester();

  public int progressToInt(ServiceRequest.Progress progress);

  public ServiceRequest.Progress getProgress();

  public String getAssignee();

  public Node getNode();

  public String getSpecialInstructions();

  public Date getDate();

  public String getTime();
}
