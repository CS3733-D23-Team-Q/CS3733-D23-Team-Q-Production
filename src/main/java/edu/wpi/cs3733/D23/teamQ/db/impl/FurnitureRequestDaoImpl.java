package edu.wpi.cs3733.D23.teamQ.db.impl;

import edu.wpi.cs3733.D23.teamQ.db.dao.GenDao;
import edu.wpi.cs3733.D23.teamQ.db.obj.FurnitureRequest;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Getter
public class FurnitureRequestDaoImpl implements GenDao<FurnitureRequest, Integer> {
  private List<FurnitureRequest> furnitureRequests = new ArrayList<FurnitureRequest>();
  private static FurnitureRequestDaoImpl single_instance = null;
  private String fileName = "Furniture_Requests.csv";

  public static synchronized FurnitureRequestDaoImpl getInstance() {
    if (single_instance == null) single_instance = new FurnitureRequestDaoImpl();

    return single_instance;
  }

  private FurnitureRequestDaoImpl() {
    populate();
  }

  /**
   * returns a furnitureRequest given a requestID
   *
   * @param requestID of furnitureRequest being retrieved
   * @return a furnitureRequest with the given nodeID
   */
  public FurnitureRequest retrieveRow(Integer requestID) {
    try {
      int index = this.getIndex(requestID);
      return furnitureRequests.get(index);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return null;
  }

  /**
   * updates furnitureRequest in list with a new furnitureRequest
   *
   * @param requestID requestID of furnitureRequest being replaced
   * @param newRequest new furnitureRequest being inserted
   * @return true if successful
   */
  public boolean updateRow(Integer requestID, FurnitureRequest newRequest) {
    try (Connection connection = GenDao.connect();
        PreparedStatement st =
            connection.prepareStatement(
                "UPDATE \"furnitureRequest\" SET \"requestID\" = ?, requester = ?, progress = ?, assignee = ?, \"nodeID\" = ?, \"specialInstructions\" = ?, date = ?, time = ?, item = ? "
                    + "WHERE \"requestID\" = ?")) {

      st.setInt(1, requestID);
      st.setString(2, newRequest.getRequester().getUsername());
      st.setInt(3, newRequest.getProgress().ordinal());
      st.setString(4, newRequest.getAssignee().getUsername());
      st.setInt(5, newRequest.getNode().getNodeID());
      st.setString(6, newRequest.getSpecialInstructions());
      st.setDate(7, newRequest.getDate());
      st.setString(8, newRequest.getTime());
      st.setString(9, newRequest.getItem());
      st.setInt(10, requestID);

      st.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }

    int index = this.getIndex(requestID);
    furnitureRequests.get(index).setRequester(newRequest.getRequester());
    furnitureRequests.get(index).setProgress(newRequest.getProgress());
    furnitureRequests.get(index).setAssignee(newRequest.getAssignee());
    furnitureRequests.get(index).setNode(newRequest.getNode());
    furnitureRequests.get(index).setSpecialInstructions(newRequest.getSpecialInstructions());
    furnitureRequests.get(index).setItem(newRequest.getItem());

    return true;
  }

  /**
   * deletes furnitureRequest from list of furnitureRequests
   *
   * @param requestID of furnitureRequest being deleted
   * @return true if successfully deleted
   */
  public boolean deleteRow(Integer requestID) {
    try (Connection connection = GenDao.connect();
        PreparedStatement st =
            connection.prepareStatement(
                "DELETE FROM \"furnitureRequest\" WHERE \"requestID\" = ?")) {;
      st.setInt(1, requestID);
      st.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }

    int index = this.getIndex(requestID);
    furnitureRequests.remove(index);

    return true;
  }

  /**
   * adds a furnitureRequest to the list
   *
   * @param request furnitureRequest being added
   * @return true if successful
   */
  public boolean addRow(FurnitureRequest request) {
    try (Connection conn = GenDao.connect();
        PreparedStatement stmt =
            conn.prepareStatement(
                "INSERT INTO \"furnitureRequest\"(requester, progress, assignee, \"nodeID\", \"specialInstructions\", \"date\", \"time\", \"item\", type) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {
      stmt.setString(1, request.getRequester().getUsername());
      stmt.setInt(2, request.getProgress().ordinal());
      stmt.setString(3, request.getAssignee().getUsername());
      stmt.setInt(4, request.getNode().getNodeID());
      stmt.setString(5, request.getSpecialInstructions());
      stmt.setDate(6, request.getDate());
      stmt.setString(7, request.getTime());
      stmt.setString(8, request.getItem());
      stmt.setString(9, request.getType());
      stmt.executeUpdate();
    } catch (SQLException ex) {
      ex.printStackTrace();
    }
    return populate();
  }

  @Override
  public boolean populate() {
    try {
      furnitureRequests.clear();
      Connection conn = GenDao.connect();
      Statement stm = conn.createStatement();
      ResultSet rst = stm.executeQuery("Select * From \"furnitureRequest\"");
      while (rst.next()) {
        furnitureRequests.add(
            new FurnitureRequest(
                rst.getInt("requestID"),
                NodeDaoImpl.getInstance().retrieveRow(rst.getInt("nodeID")),
                AccountDaoImpl.getInstance().retrieveRow(rst.getString("requester")),
                AccountDaoImpl.getInstance().retrieveRow(rst.getString("assignee")),
                rst.getString("specialInstructions"),
                rst.getDate("date"),
                rst.getString("time"),
                rst.getInt("progress"),
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
    for (int i = 0; i < furnitureRequests.size(); i++) {
      FurnitureRequest x = furnitureRequests.get(i);
      if (x.getRequestID() == requestID) {
        return i;
      }
    }
    throw new RuntimeException("No furniture request found with ID: " + requestID);
  }

  /**
   * function that gets all conference requests in the list
   *
   * @return all conference requests in list
   */
  public List<FurnitureRequest> getAllRows() {
    return furnitureRequests;
  }

  public List<FurnitureRequest> listFurnitureRequests(String username) {
    List<FurnitureRequest> list = new ArrayList<FurnitureRequest>();
    for (FurnitureRequest request : furnitureRequests) {
      if (request.getRequester().equals(username)) {}
    }
    return list;
  }
}
