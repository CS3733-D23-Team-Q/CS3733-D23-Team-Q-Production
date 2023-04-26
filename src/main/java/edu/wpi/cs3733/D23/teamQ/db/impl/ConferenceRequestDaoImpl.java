package edu.wpi.cs3733.D23.teamQ.db.impl;

import edu.wpi.cs3733.D23.teamQ.db.dao.GenDao;
import edu.wpi.cs3733.D23.teamQ.db.obj.ConferenceRequest;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConferenceRequestDaoImpl implements GenDao<ConferenceRequest, Integer> {
  private List<ConferenceRequest> conferenceRequests = new ArrayList<ConferenceRequest>();
  private int nextID = 0;
  private NodeDaoImpl nodeTable;
  private AccountDaoImpl accountTable;
  private static ConferenceRequestDaoImpl single_instance = null;

  public static synchronized ConferenceRequestDaoImpl getInstance(
      AccountDaoImpl accountTable, NodeDaoImpl nodeTable) {
    if (single_instance == null)
      single_instance = new ConferenceRequestDaoImpl(accountTable, nodeTable);

    return single_instance;
  }

  private ConferenceRequestDaoImpl(AccountDaoImpl accountTable, NodeDaoImpl nodeTable) {
    this.nodeTable = nodeTable;
    this.accountTable = accountTable;
    populate();
    if (conferenceRequests.size() != 0) {
      nextID = conferenceRequests.get(conferenceRequests.size() - 1).getRequestID() + 1;
    }
  }

  /**
   * returns a conferenceRequest given a requestID
   *
   * @param requestID of conferenceRequest being retrieved
   * @return a conferenceRequest with the given nodeID
   */
  public ConferenceRequest retrieveRow(Integer requestID) {
    try {
      int index = this.getIndex(requestID);
      return conferenceRequests.get(index);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return null;
  }

  /**
   * updates conferenceRequest in list with a new conferenceRequest
   *
   * @param requestID requestID of conferenceRequest being replaced
   * @param newRequest new conferenceRequest being inserted
   * @return true if successful
   */
  public boolean updateRow(Integer requestID, ConferenceRequest newRequest) {
    try (Connection connection = GenDao.connect();
        PreparedStatement st =
            connection.prepareStatement(
                "UPDATE \"conferenceRequest\" SET \"requestID\" = ?, requester = ?, progress = ?, assignee = ?, \"nodeID\" = ?, \"specialInstructions\" = ?, date = ?, time = ?, \"foodChoice\" = ? "
                    + "WHERE \"requestID\" = ?")) {

      st.setInt(1, requestID);
      st.setString(2, newRequest.getRequester().getUsername());
      st.setInt(3, newRequest.getProgress().ordinal());
      st.setString(4, newRequest.getAssignee().getUsername());
      st.setInt(5, newRequest.getNode().getNodeID());
      st.setString(6, newRequest.getSpecialInstructions());
      st.setDate(7, newRequest.getDate());
      st.setString(8, newRequest.getTime());
      st.setString(9, newRequest.getFoodChoice());
      st.setInt(10, requestID);

      st.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    int index = this.getIndex(requestID);
    conferenceRequests.get(index).setRequester(newRequest.getRequester());
    conferenceRequests.get(index).setProgress(newRequest.getProgress());
    conferenceRequests.get(index).setAssignee(newRequest.getAssignee());
    conferenceRequests.get(index).setNode(newRequest.getNode());
    conferenceRequests.get(index).setSpecialInstructions(newRequest.getSpecialInstructions());
    conferenceRequests.get(index).setDate(newRequest.getDate());
    conferenceRequests.get(index).setTime(newRequest.getTime());
    conferenceRequests.get(index).setFoodChoice(newRequest.getFoodChoice());

    return true;
  }

  /**
   * deletes conferenceRequest from list of conferenceRequests
   *
   * @param requestID of conferenceRequest being deleted
   * @return true if successfully deleted
   */
  public boolean deleteRow(Integer requestID) {
    try (Connection connection = GenDao.connect();
        PreparedStatement st =
            connection.prepareStatement(
                "DELETE FROM \"conferenceRequest\" WHERE \"requestID\" = ?")) {;
      st.setInt(1, requestID);
      st.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }

    int index = this.getIndex(requestID);
    conferenceRequests.remove(index);

    return true;
  }

  /**
   * adds a conferenceRequest to the list
   *
   * @param request conferenceRequest being added
   * @return true if successful
   */
  public boolean addRow(ConferenceRequest request) {
    try (Connection conn = GenDao.connect();
        PreparedStatement stmt =
            conn.prepareStatement(
                "INSERT INTO \"conferenceRequest\"(requester, progress, assignee, \"nodeID\", \"specialInstructions\", date, time, \"foodChoice\") VALUES (?, ?, ?, ?, ?, ?, ?, ?)")) {
      stmt.setString(1, request.getRequester().getUsername());
      stmt.setInt(2, request.getProgress().ordinal());
      stmt.setString(3, request.getAssignee().getUsername());
      stmt.setInt(4, request.getNode().getNodeID());
      stmt.setString(5, request.getSpecialInstructions());
      stmt.setDate(6, request.getDate());
      stmt.setString(7, request.getTime());
      stmt.setString(8, request.getFoodChoice());
      stmt.executeUpdate();
    } catch (SQLException ex) {
      ex.printStackTrace();
    }
    return populate();
  }

  @Override
  public boolean populate() {
    try {
      conferenceRequests.clear();
      Connection conn = GenDao.connect();
      Statement stm = conn.createStatement();
      ResultSet rst = stm.executeQuery("Select * From \"conferenceRequest\"");
      while (rst.next()) {
        conferenceRequests.add(
            new ConferenceRequest(
                rst.getInt("requestID"),
                nodeTable.retrieveRow(rst.getInt("nodeID")),
                accountTable.retrieveRow(rst.getString("requester")),
                accountTable.retrieveRow(rst.getString("assignee")),
                rst.getString("specialInstructions"),
                rst.getDate("date"),
                rst.getString("time"),
                rst.getInt("progress"),
                rst.getString("foodChoice")));
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
    for (int i = 0; i < conferenceRequests.size(); i++) {
      ConferenceRequest x = conferenceRequests.get(i);
      if (x.getRequestID() == requestID) {
        return i;
      }
    }
    throw new RuntimeException("No conference request found with ID: " + requestID);
  }

  /**
   * function that gets all conference requests in the list
   *
   * @return all conference requests in list
   */
  public List<ConferenceRequest> getAllRows() {
    return conferenceRequests;
  }

  public List<ConferenceRequest> listConferenceRequests(String username) {
    List<ConferenceRequest> list = new ArrayList<ConferenceRequest>();
    for (ConferenceRequest request : conferenceRequests) {
      if (request.getRequester().equals(username)) {}
    }
    return list;
  }
}
