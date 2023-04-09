package edu.wpi.cs3733.D23.teamQ.db.impl;

import edu.wpi.cs3733.D23.teamQ.db.dao.GenDao;
import edu.wpi.cs3733.D23.teamQ.db.obj.FurnitureRequest;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FurnitureRequestDaoImpl implements GenDao<FurnitureRequest, Integer> {
  private List<FurnitureRequest> furnitureRequests = new ArrayList<FurnitureRequest>();
  private int nextID = 0;
  private static FurnitureRequestDaoImpl single_instance = null;

  public static synchronized FurnitureRequestDaoImpl getInstance(NodeDaoImpl nodeTable) {
    if (single_instance == null) single_instance = new FurnitureRequestDaoImpl(nodeTable);

    return single_instance;
  }

  private FurnitureRequestDaoImpl(NodeDaoImpl nodeTable) {
    populate();
    if (furnitureRequests.size() != 0) {
      nextID = furnitureRequests.get(furnitureRequests.size() - 1).getRequestID() + 1;
    }
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
      System.out.println("No request found with ID: " + requestID);
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
    int index = this.getIndex(requestID);
    furnitureRequests.set(index, newRequest);

    deleteRow(requestID);
    addRow(newRequest);

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
                "INSERT INTO \"furnitureRequest\"(requester, progress, assignee, \"specialInstructions\", \"item\", \"roomNum\") VALUES ( ?, ?, ?, ?, ?, ?)")) {
      stmt.setString(1, request.getRequester());
      stmt.setInt(2, request.progressToInt(request.getProgress()));
      stmt.setString(3, request.getAssignee());
      stmt.setString(4, request.getSpecialInstructions());
      stmt.setString(5, request.getItem());
      stmt.setString(6, request.getRoomNumber());
      stmt.executeUpdate();
    } catch (SQLException ex) {
      ex.printStackTrace();
    }
    request.setRequestID(nextID);
    nextID++;
    return furnitureRequests.add(request);
  }

  @Override
  public boolean populate() {
    try {
      Connection conn = GenDao.connect();
      Statement stm = conn.createStatement();
      ResultSet rst = stm.executeQuery("Select * From \"furnitureRequest\"");
      while (rst.next()) {
        furnitureRequests.add(
            new FurnitureRequest(
                rst.getInt("requestID"),
                rst.getString("requester"),
                rst.getInt("progress"),
                rst.getString("assignee"),
                rst.getString("roomNum"),
                rst.getString("specialInstructions"),
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
      if (x.getRequestID() == (Integer) requestID) {
        return i;
      }
    }
    throw new RuntimeException("No request found with ID " + requestID);
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
