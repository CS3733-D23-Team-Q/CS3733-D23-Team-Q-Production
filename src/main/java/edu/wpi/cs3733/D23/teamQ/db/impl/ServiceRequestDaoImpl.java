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

  public ServiceRequest retrieveRow(Integer ID) {
    int index = getIndex(ID);
    return serviceRequests.get(index);
  }

  public int getIndex(int requestID) {
    populate();
    for (int i = 0; i < serviceRequests.size(); i++) {
      ServiceRequest sr = serviceRequests.get(i);
      if (sr.getRequestID() == requestID) {
        return i;
      }
    }
    return -1;
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
}
