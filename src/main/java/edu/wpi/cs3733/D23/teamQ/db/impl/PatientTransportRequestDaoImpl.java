package edu.wpi.cs3733.D23.teamQ.db.impl;

import edu.wpi.cs3733.D23.teamQ.db.dao.GenDao;
import edu.wpi.cs3733.D23.teamQ.db.obj.PatientTransportRequest;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientTransportRequestDaoImpl implements GenDao<PatientTransportRequest, Integer> {
  private List<PatientTransportRequest> patientTransportRequests =
      new ArrayList<PatientTransportRequest>();
  private NodeDaoImpl nodeTable;
  private int nextID = 0;
  private static PatientTransportRequestDaoImpl single_instance = null;

  public static synchronized PatientTransportRequestDaoImpl getInstance(NodeDaoImpl nodeTable) {
    if (single_instance == null) single_instance = new PatientTransportRequestDaoImpl(nodeTable);

    return single_instance;
  }

  private PatientTransportRequestDaoImpl(NodeDaoImpl nodeTable) {
    this.nodeTable = nodeTable;
    populate();
    if (patientTransportRequests.size() != 0) {
      nextID = patientTransportRequests.get(patientTransportRequests.size() - 1).getRequestID() + 1;
    }
  }

  /**
   * returns a patientTransportRequest given a requestID
   *
   * @param requestID of patientTransportRequest being retrieved
   * @return a patientTransportRequest with the given nodeID
   */
  public PatientTransportRequest retrieveRow(Integer requestID) {
    try {
      int index = this.getIndex(requestID);
      return patientTransportRequests.get(index);
    } catch (Exception e) {
      System.out.println("No request found with ID: " + requestID);
    }
    return null;
  }

  /**
   * updates patientTransportRequest in list with a new patientTransportRequest
   *
   * @param requestID requestID of patientTransportRequest being replaced
   * @param newRequest new patientTransportRequest being inserted
   * @return true if successful
   */
  public boolean updateRow(Integer requestID, PatientTransportRequest newRequest) {
    try (Connection connection = GenDao.connect();
        PreparedStatement st =
            connection.prepareStatement(
                "UPDATE \"patientTransportRequest\" SET \"requestID\" = ?, requester = ?, progress = ?, assignee = ?, \"nodeID\" = ?, \"specialInstructions\" = ?, item = ? "
                    + "WHERE \"requestID\" = ?")) {

      st.setInt(1, requestID);
      st.setString(2, newRequest.getRequester());
      st.setInt(3, newRequest.getProgress().ordinal());
      st.setString(4, newRequest.getAssignee());
      st.setInt(5, newRequest.getNode().getNodeID());
      st.setString(6, newRequest.getSpecialInstructions());
      st.setString(7, newRequest.getItem());
      st.setInt(8, requestID);

      st.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }

    int index = this.getIndex(requestID);
    patientTransportRequests.get(index).setRequester(newRequest.getRequester());
    patientTransportRequests.get(index).setProgress(newRequest.getProgress());
    patientTransportRequests.get(index).setAssignee(newRequest.getAssignee());
    patientTransportRequests.get(index).setNode(newRequest.getNode());
    patientTransportRequests.get(index).setSpecialInstructions(newRequest.getSpecialInstructions());
    patientTransportRequests.get(index).setItem(newRequest.getItem());

    return true;
  }

  /**
   * deletes patientTransportRequest from list of patientTransportRequests
   *
   * @param requestID of patientTransportRequest being deleted
   * @return true if successfully deleted
   */
  public boolean deleteRow(Integer requestID) {
    try (Connection connection = GenDao.connect();
        PreparedStatement st =
            connection.prepareStatement(
                "DELETE FROM \"patientTransportRequests\" WHERE \"requestID\" = ?")) {;
      st.setInt(1, requestID);
      st.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }

    int index = this.getIndex(requestID);
    patientTransportRequests.remove(index);

    return true;
  }

  /**
   * adds a patientTransportRequest to the list
   *
   * @param request patientTransportRequest being added
   * @return true if successful
   */
  public boolean addRow(PatientTransportRequest request) {
    try (Connection conn = GenDao.connect();
        PreparedStatement stmt =
            conn.prepareStatement(
                "INSERT INTO \"patientTransportRequest\"(requester, progress, assignee, \"nodeID\", \"specialInstructions\", \"date\", \"time\", \"transport\") VALUES (?, ?, ?, ?, ?, ?, ?, ?)")) {
      stmt.setString(1, request.getRequester());
      stmt.setInt(2, request.progressToInt(request.getProgress()));
      stmt.setString(3, request.getAssignee());
      stmt.setInt(4, request.getNode().getNodeID());
      stmt.setString(5, request.getSpecialInstructions());
      stmt.setDate(6, request.getDate());
      stmt.setString(7, request.getTime());
      stmt.setString(8, request.getItem());
      stmt.executeUpdate();
    } catch (SQLException ex) {
      ex.printStackTrace();
    }
    request.setRequestID(nextID);
    nextID++;
    return patientTransportRequests.add(request);
  }

  @Override
  public boolean populate() {
    try {
      Connection conn = GenDao.connect();
      Statement stm = conn.createStatement();
      ResultSet rst = stm.executeQuery("Select * From \"patientTransportRequest\"");
      while (rst.next()) {
        patientTransportRequests.add(
            new PatientTransportRequest(
                rst.getInt("requestID"),
                rst.getString("requester"),
                rst.getInt("progress"),
                rst.getString("assignee"),
                nodeTable.retrieveRow(rst.getInt("nodeID")),
                rst.getString("specialInstructions"),
                rst.getDate("date"),
                rst.getString("time"),
                rst.getString("item")));
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
    for (int i = 0; i < patientTransportRequests.size(); i++) {
      PatientTransportRequest x = patientTransportRequests.get(i);
      if (x.getRequestID() == (Integer) requestID) {
        return i;
      }
    }
    throw new RuntimeException("No request found with ID " + requestID);
  }

  /**
   * function that gets all patient transport requests in the list
   *
   * @return all patient transport requests in list
   */
  public List<PatientTransportRequest> getAllRows() {
    return patientTransportRequests;
  }

  public List<PatientTransportRequest> listPatientTransportRequests(String username) {
    List<PatientTransportRequest> list = new ArrayList<PatientTransportRequest>();
    for (PatientTransportRequest request : patientTransportRequests) {
      if (request.getRequester().equals(username)) {}
    }
    return list;
  }
}
