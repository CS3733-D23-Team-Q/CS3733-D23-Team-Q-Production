package edu.wpi.cs3733.D23.teamQ.db.impl;

import edu.wpi.cs3733.D23.teamQ.db.dao.GenDao;
import edu.wpi.cs3733.D23.teamQ.db.obj.MedicalSuppliesRequest;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MedicalSuppliesRequestDaoImpl implements GenDao<MedicalSuppliesRequest, Integer> {
  private List<MedicalSuppliesRequest> medicalSuppliesRequests =
      new ArrayList<MedicalSuppliesRequest>();
  private NodeDaoImpl nodeTable;
  private AccountDaoImpl accountTable;
  private static MedicalSuppliesRequestDaoImpl single_instance = null;

  public static synchronized MedicalSuppliesRequestDaoImpl getInstance(
      AccountDaoImpl accountTable, NodeDaoImpl nodeTable) {
    if (single_instance == null)
      single_instance = new MedicalSuppliesRequestDaoImpl(accountTable, nodeTable);

    return single_instance;
  }

  private MedicalSuppliesRequestDaoImpl(AccountDaoImpl accountTable, NodeDaoImpl nodeTable) {
    this.nodeTable = nodeTable;
    this.accountTable = accountTable;
    populate();
  }

  /**
   * returns a medicalSuppliesRequest given a requestID
   *
   * @param requestID of medicalSuppliesRequest being retrieved
   * @return a medicalSuppliesRequest with the given nodeID
   */
  public MedicalSuppliesRequest retrieveRow(Integer requestID) {
    try {
      int index = this.getIndex(requestID);
      return medicalSuppliesRequests.get(index);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return null;
  }

  /**
   * updates medicalSuppliesRequest in list with a new medicalSuppliesRequest
   *
   * @param requestID requestID of medicalSuppliesRequest being replaced
   * @param newRequest new medicalSuppliesRequest being inserted
   * @return true if successful
   */
  public boolean updateRow(Integer requestID, MedicalSuppliesRequest newRequest) {
    try (Connection connection = GenDao.connect();
        PreparedStatement st =
            connection.prepareStatement(
                "UPDATE \"medicalSuppliesRequest\" SET \"requestID\" = ?, requester = ?, progress = ?, assignee = ?, \"nodeID\" = ?, item = ?, quantity = ?, \"specialInstructions\" = ? "
                    + "WHERE \"requestID\" = ?")) {

      st.setInt(1, requestID);
      st.setString(2, newRequest.getRequester().getUsername());
      st.setInt(3, newRequest.getProgress().ordinal());
      st.setString(4, newRequest.getAssignee().getUsername());
      st.setInt(5, newRequest.getNode().getNodeID());
      st.setString(6, newRequest.getItem());
      st.setInt(7, newRequest.getQuantity());
      st.setString(8, newRequest.getSpecialInstructions());
      st.setInt(9, requestID);

      st.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }

    int index = this.getIndex(requestID);
    medicalSuppliesRequests.get(index).setRequester(newRequest.getRequester());
    medicalSuppliesRequests.get(index).setProgress(newRequest.getProgress());
    medicalSuppliesRequests.get(index).setAssignee(newRequest.getAssignee());
    medicalSuppliesRequests.get(index).setNode(newRequest.getNode());
    medicalSuppliesRequests.get(index).setSpecialInstructions(newRequest.getSpecialInstructions());
    medicalSuppliesRequests.get(index).setItem(newRequest.getItem());
    medicalSuppliesRequests.get(index).setQuantity(newRequest.getQuantity());
    return true;
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
                "DELETE FROM \"medicalSuppliesRequest\" WHERE \"requestID\" = ?")) {;
      st.setInt(1, requestID);
      st.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }

    int index = this.getIndex(requestID);
    medicalSuppliesRequests.remove(index);

    return true;
  }

  /**
   * adds a medicalSuppliesRequest to the list
   *
   * @param request medicalSuppliesRequest being added
   * @return true if successful
   */
  public boolean addRow(MedicalSuppliesRequest request) {
    try (Connection conn = GenDao.connect();
        PreparedStatement stmt =
            conn.prepareStatement(
                "INSERT INTO \"medicalSuppliesRequest\"(requester, progress, assignee, \"nodeID\", \"specialInstructions\", \"date\", \"time\", \"item\", \"quantity\") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)")) {
      stmt.setString(1, request.getRequester().getUsername());
      stmt.setInt(2, request.getProgress().ordinal());
      stmt.setString(3, request.getAssignee().getUsername());
      stmt.setInt(4, request.getNode().getNodeID());
      stmt.setString(5, request.getSpecialInstructions());
      stmt.setDate(6, request.getDate());
      stmt.setString(7, request.getTime());
      stmt.setString(8, request.getItem());
      stmt.setInt(9, request.getQuantity());
      stmt.executeUpdate();
    } catch (SQLException ex) {
      ex.printStackTrace();
    }
    return populate();
  }

  @Override
  public boolean populate() {
    try {
      medicalSuppliesRequests.clear();
      Connection conn = GenDao.connect();
      Statement stm = conn.createStatement();
      ResultSet rst = stm.executeQuery("Select * From \"medicalSuppliesRequest\"");
      while (rst.next()) {
        medicalSuppliesRequests.add(
            new MedicalSuppliesRequest(
                rst.getInt("requestID"),
                nodeTable.retrieveRow(rst.getInt("nodeID")),
                accountTable.retrieveRow(rst.getString("requester")),
                accountTable.retrieveRow(rst.getString("assignee")),
                rst.getString("specialInstructions"),
                rst.getDate("date"),
                rst.getString("time"),
                rst.getInt("progress"),
                rst.getString("item"),
                rst.getInt("quantity")));
      }
      conn.close();
      stm.close();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return true;
  }

  /**
   * gets index of given request in the list of requests
   *
   * @param requestID requestID being checked
   * @return value of index
   */
  private int getIndex(Integer requestID) {
    for (int i = 0; i < medicalSuppliesRequests.size(); i++) {
      MedicalSuppliesRequest x = medicalSuppliesRequests.get(i);
      if (x.getRequestID() == requestID) {
        return i;
      }
    }
    throw new RuntimeException("No medical supplies request found with ID: " + requestID);
  }

  /**
   * function that gets all medical supplies requests in the list
   *
   * @return all medical supplies requests in list
   */
  public List<MedicalSuppliesRequest> getAllRows() {
    return medicalSuppliesRequests;
  }

  public List<MedicalSuppliesRequest> listConferenceRequests(String username) {
    List<MedicalSuppliesRequest> list = new ArrayList<MedicalSuppliesRequest>();
    for (MedicalSuppliesRequest request : medicalSuppliesRequests) {
      if (request.getRequester().equals(username)) {}
    }
    return list;
  }
}
