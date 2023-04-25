package edu.wpi.cs3733.D23.teamQ.db.impl;

import edu.wpi.cs3733.D23.teamQ.db.dao.GenDao;
import edu.wpi.cs3733.D23.teamQ.db.obj.*;
import java.sql.*;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServiceRequestDaoImpl {
  private ObservableList<ServiceRequest> serviceRequests = FXCollections.observableArrayList();
  private NodeDaoImpl nodeTable;
  private AccountDaoImpl accountTable;

  private static ServiceRequestDaoImpl single_instance = null;

  public static synchronized ServiceRequestDaoImpl getInstance(
      AccountDaoImpl accountTable, NodeDaoImpl nodeTable) {
    if (single_instance == null)
      single_instance = new ServiceRequestDaoImpl(accountTable, nodeTable);

    return single_instance;
  }

  private ServiceRequestDaoImpl(AccountDaoImpl accountTable, NodeDaoImpl nodeTable) {
    this.nodeTable = nodeTable;
    this.accountTable = accountTable;
    populate();
  }

  public boolean populate() {
    try {
      serviceRequests.clear();
      Connection conn = GenDao.connect();
      Statement stm = conn.createStatement();
      ResultSet rst = stm.executeQuery("Select * From \"serviceRequest\"");
      while (rst.next()) {
        serviceRequests.add(
            new ServiceRequest(
                rst.getInt("requestID"),
                nodeTable.retrieveRow(rst.getInt("nodeID")),
                accountTable.retrieveRow(rst.getString("requester")),
                accountTable.retrieveRow(rst.getString("assignee").split(",")[0]),
                rst.getString("specialInstructions"),
                rst.getDate("date"),
                rst.getString("time"),
                rst.getInt("progress")));
      }
      conn.close();
      stm.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return true;
  }

  public List<ServiceRequest> getAllRows() {
    return serviceRequests;
  }

  public ServiceRequest retrieveRow(Integer ID) {
    try {
      int index = this.getIndex(ID);
      return serviceRequests.get(index);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return null;
  }

  private int getIndex(Integer requestID) {
    for (int i = 0; i < serviceRequests.size(); i++) {
      ServiceRequest x = serviceRequests.get(i);
      if (x.getRequestID() == requestID) {
        return i;
      }
    }
    throw new RuntimeException("No service request found with ID: " + requestID);
  }

  public List<ServiceRequest> getUserRows(String user) {
    ObservableList<ServiceRequest> list = FXCollections.observableArrayList();
    for (int i = 0; i < serviceRequests.size(); i++) {
      if (serviceRequests.get(i).getRequester().getUsername().equals(user)
          || serviceRequests.get(i).getAssignee().getUsername().equals(user)) {
        list.add(serviceRequests.get(i));
      }
    }
    return list;
  }

  public ObservableList<ServiceRequest> getUserAssignedRows(String user) {
    ObservableList<ServiceRequest> list = FXCollections.observableArrayList();
    for (int i = 0; i < serviceRequests.size(); i++) {
      if (serviceRequests.get(i).getAssignee().getUsername().equals(user)) {
        list.add(serviceRequests.get(i));
      }
    }
    return list;
  }

  public ObservableList<ServiceRequest> getUserAssignedOutstandingRows(String user) {
    ObservableList<ServiceRequest> list = FXCollections.observableArrayList();
    for (int i = 0; i < serviceRequests.size(); i++) {
      if (serviceRequests.get(i).getAssignee().getUsername().equals(user)
          && serviceRequests.get(i).getProgress().ordinal() != 2) {
        list.add(serviceRequests.get(i));
      }
    }
    return list;
  }

  public ObservableList<ServiceRequest> getUserRequestedRows(String user) {
    ObservableList<ServiceRequest> list = FXCollections.observableArrayList();
    for (int i = 0; i < serviceRequests.size(); i++) {
      if (serviceRequests.get(i).getRequester().getUsername().equals(user)) {
        list.add(serviceRequests.get(i));
      }
    }
    return list;
  }

  public ObservableList<ServiceRequest> getUserRequestedOutstandingRows(String user) {
    ObservableList<ServiceRequest> list = FXCollections.observableArrayList();
    for (int i = 0; i < serviceRequests.size(); i++) {
      if (serviceRequests.get(i).getRequester().getUsername().equals(user)
          && serviceRequests.get(i).getProgress().ordinal() != 2) {
        list.add(serviceRequests.get(i));
      }
    }
    return list;
  }

  public ObservableList<ServiceRequest> getAllRequestsObservable() {
    ObservableList<ServiceRequest> list = FXCollections.observableArrayList();
    for (int i = 0; i < serviceRequests.size(); i++) {
      list.add(serviceRequests.get(i));
    }
    return list;
  }

  public ObservableList<ServiceRequest> getAllOutstandingRequestsObservable() {
    ObservableList<ServiceRequest> list = FXCollections.observableArrayList();
    for (int i = 0; i < serviceRequests.size(); i++) {
      if (serviceRequests.get(i).getProgress().ordinal() != 2) {
        list.add(serviceRequests.get(i));
      }
    }
    return list;
  }

  /**
   * deletes medicalSuppliesRequest from list of medicalSuppliesRequests
   *
   * @param requestID of medicalSuppliesRequest being deleted
   * @return true if successfully deleted
   */
  public boolean deleteRow(Integer requestID) {
    try (Connection connection = GenDao.connect();
        PreparedStatement st =
            connection.prepareStatement(
                "DELETE FROM \"serviceRequest\" WHERE \"requestID\" = ?")) {;
      st.setInt(1, requestID);
      st.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    int index = this.getIndex(requestID);
    serviceRequests.remove(index);
    return true;
  }

  public boolean updateRow(Integer requestID, ServiceRequest newRequest) {
    try (Connection connection = GenDao.connect();
        PreparedStatement st =
            connection.prepareStatement(
                "UPDATE \"serviceRequest\" SET \"requestID\" = ?, \"nodeID\" = ?, requester = ?, assignee = ?, \"specialInstructions\" = ?, date = ?, time = ?, progress = ? "
                    + "WHERE \"requestID\" = ?")) {

      st.setInt(1, requestID);
      st.setInt(2, newRequest.getNode().getNodeID());
      st.setString(3, newRequest.getRequester().getUsername());
      st.setString(4, newRequest.getAssignee().getUsername());
      st.setString(5, newRequest.getSpecialInstructions());
      st.setDate(6, newRequest.getDate());
      st.setString(7, newRequest.getTime());
      st.setInt(8, newRequest.getProgress().ordinal());
      st.setInt(9, requestID);
      st.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    int index = this.getIndex(requestID);
    serviceRequests.get(index).setNode(newRequest.getNode());
    serviceRequests.get(index).setRequester(newRequest.getRequester());
    serviceRequests.get(index).setAssignee(newRequest.getAssignee());
    serviceRequests.get(index).setSpecialInstructions(newRequest.getSpecialInstructions());
    serviceRequests.get(index).setDate(newRequest.getDate());
    serviceRequests.get(index).setTime(newRequest.getTime());
    serviceRequests.get(index).setProgress(newRequest.getProgress());
    return true;
  }
}
