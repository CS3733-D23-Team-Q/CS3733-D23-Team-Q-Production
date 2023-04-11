package edu.wpi.cs3733.D23.teamQ.db.impl;

import edu.wpi.cs3733.D23.teamQ.db.dao.GenDao;
import edu.wpi.cs3733.D23.teamQ.db.obj.MealRequest;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MealRequestDaoImpl implements GenDao<MealRequest, Integer> {
  private List<MealRequest> mealRequests = new ArrayList<MealRequest>();
  private int nextID = 0;
  private NodeDaoImpl nodeTable;
  private static MealRequestDaoImpl single_instance = null;

  public static synchronized MealRequestDaoImpl getInstance(NodeDaoImpl nodeTable) {
    if (single_instance == null) single_instance = new MealRequestDaoImpl(nodeTable);

    return single_instance;
  }

  private MealRequestDaoImpl(NodeDaoImpl nodeTable) {
    populate();
    this.nodeTable = nodeTable;
    if (mealRequests.size() != 0) {
      nextID = mealRequests.get(mealRequests.size() - 1).getRequestID() + 1;
    }
  }

  /**
   * returns a mealRequest given a requestID
   *
   * @param requestID of mealRequest being retrieved
   * @return a mealRequest with the given nodeID
   */
  public MealRequest retrieveRow(Integer requestID) {
    try {
      int index = this.getIndex(requestID);
      return mealRequests.get(index);
    } catch (Exception e) {
      System.out.println("No request found with ID: " + requestID);
    }
    return null;
  }

  /**
   * updates mealRequest in list with a new mealRequest
   *
   * @param requestID requestID of mealRequest being replaced
   * @param newRequest new mealRequest being inserted
   * @return true if successful
   */
  public boolean updateRow(Integer requestID, MealRequest newRequest) {
    int index = this.getIndex(requestID);
    mealRequests.set(index, newRequest);

    deleteRow(requestID);
    addRow(newRequest);

    return true;
  }

  /**
   * deletes mealRequest from list of mealRequests
   *
   * @param requestID of mealRequest being deleted
   * @return true if successfully deleted
   */
  public boolean deleteRow(Integer requestID) {
    try (Connection connection = GenDao.connect();
        PreparedStatement st =
            connection.prepareStatement("DELETE FROM \"mealRequest\" WHERE \"requestID\" = ?")) {;
      st.setInt(1, requestID);
      st.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }

    int index = this.getIndex(requestID);
    mealRequests.remove(index);

    return true;
  }

  /**
   * adds a mealRequest to the list
   *
   * @param request mealRequest being added
   * @return true if successful
   */
  public boolean addRow(MealRequest request) {
    try (Connection conn = GenDao.connect();
        PreparedStatement stmt =
            conn.prepareStatement(
                "INSERT INTO \"mealRequest\"(requester, progress, assignee, \"nodeID\", \"specialInstructions\", \"date\", \"time\", \"drink\", \"entree\", \"side\") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {
      stmt.setString(1, request.getRequester());
      stmt.setInt(2, request.progressToInt(request.getProgress()));
      stmt.setString(3, request.getAssignee());
      stmt.setInt(4, request.getNode().getNodeID());
      stmt.setString(5, request.getSpecialInstructions());
      stmt.setDate(6, request.getDate());
      stmt.setString(7, request.getTime());
      stmt.setString(8, request.getDrink());
      stmt.setString(9, request.getEntree());
      stmt.setString(10, request.getSide());
      stmt.executeUpdate();
    } catch (SQLException ex) {
      ex.printStackTrace();
    }
    request.setRequestID(nextID);
    nextID++;
    return mealRequests.add(request);
  }

  @Override
  public boolean populate() {
    try {
      Connection conn = GenDao.connect();
      Statement stm = conn.createStatement();
      ResultSet rst = stm.executeQuery("Select * From \"mealRequest\"");
      while (rst.next()) {
        mealRequests.add(
            new MealRequest(
                rst.getInt("requestID"),
                rst.getString("requester"),
                rst.getInt("progress"),
                rst.getString("assignee"),
                nodeTable.retrieveRow(rst.getInt("nodeID")),
                rst.getString("specialInstructions"),
                rst.getDate("date"),
                rst.getString("time"),
                rst.getString("drink"),
                rst.getString("entree"),
                rst.getString("side")));
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
    for (int i = 0; i < mealRequests.size(); i++) {
      MealRequest x = mealRequests.get(i);
      if (x.getRequestID() == (Integer) requestID) {
        return i;
      }
    }
    throw new RuntimeException("No request found with ID " + requestID);
  }

  /**
   * function that gets all meal requests in the list
   *
   * @return all meal requests in list
   */
  public List<MealRequest> getAllRows() {
    return mealRequests;
  }

  public List<MealRequest> listMealRequests(String username) {
    List<MealRequest> list = new ArrayList<MealRequest>();
    for (MealRequest request : mealRequests) {
      if (request.getRequester().equals(username)) {}
    }
    return list;
  }
}
