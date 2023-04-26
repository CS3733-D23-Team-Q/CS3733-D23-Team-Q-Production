package edu.wpi.cs3733.D23.teamQ.db.impl;

import edu.wpi.cs3733.D23.teamQ.db.dao.GenDao;
import edu.wpi.cs3733.D23.teamQ.db.obj.MealRequest;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Getter
public class MealRequestDaoImpl implements GenDao<MealRequest, Integer> {
  private List<MealRequest> mealRequests = new ArrayList<MealRequest>();
  private NodeDaoImpl nodeTable;
  private AccountDaoImpl accountTable;
  private static MealRequestDaoImpl single_instance = null;
  private String fileName = "Meal_Requests.csv";

  public static synchronized MealRequestDaoImpl getInstance(
      AccountDaoImpl accountTable, NodeDaoImpl nodeTable) {
    if (single_instance == null) single_instance = new MealRequestDaoImpl(accountTable, nodeTable);

    return single_instance;
  }

  private MealRequestDaoImpl(AccountDaoImpl accountTable, NodeDaoImpl nodeTable) {
    this.nodeTable = nodeTable;
    this.accountTable = accountTable;
    populate();
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
      System.out.println(e.getMessage());
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
    try (Connection connection = GenDao.connect();
        PreparedStatement st =
            connection.prepareStatement(
                "UPDATE \"mealRequest\" SET \"requestID\" = ?, requester = ?, progress = ?, assignee = ?, \"nodeID\" = ?, \"specialInstructions\" = ?, drink = ?, entree = ?, side = ? "
                    + "WHERE \"requestID\" = ?")) {

      st.setInt(1, requestID);
      st.setString(2, newRequest.getRequester().getUsername());
      st.setInt(3, newRequest.getProgress().ordinal());
      st.setString(4, newRequest.getAssignee().getUsername());
      st.setInt(5, newRequest.getNode().getNodeID());
      st.setString(6, newRequest.getSpecialInstructions());
      st.setString(7, newRequest.getDrink());
      st.setString(8, newRequest.getEntree());
      st.setString(9, newRequest.getSide());
      st.setInt(10, requestID);

      st.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    int index = this.getIndex(requestID);
    mealRequests.get(index).setRequester(newRequest.getRequester());
    mealRequests.get(index).setProgress(newRequest.getProgress());
    mealRequests.get(index).setAssignee(newRequest.getAssignee());
    mealRequests.get(index).setNode(newRequest.getNode());
    mealRequests.get(index).setSpecialInstructions(newRequest.getSpecialInstructions());
    mealRequests.get(index).setDrink(newRequest.getDrink());
    mealRequests.get(index).setEntree(newRequest.getEntree());
    mealRequests.get(index).setSide(newRequest.getSide());

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
      stmt.setString(1, request.getRequester().getUsername());
      stmt.setInt(2, request.getProgress().ordinal());
      stmt.setString(3, request.getAssignee().getUsername());
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
    return populate();
  }

  @Override
  public boolean populate() {
    try {
      mealRequests.clear();
      Connection conn = GenDao.connect();
      Statement stm = conn.createStatement();
      ResultSet rst = stm.executeQuery("Select * From \"mealRequest\"");
      while (rst.next()) {
        mealRequests.add(
            new MealRequest(
                rst.getInt("requestID"),
                nodeTable.retrieveRow(rst.getInt("nodeID")),
                accountTable.retrieveRow(rst.getString("requester")),
                accountTable.retrieveRow(rst.getString("assignee")),
                rst.getString("specialInstructions"),
                rst.getDate("date"),
                rst.getString("time"),
                rst.getInt("progress"),
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
      if (x.getRequestID() == requestID) {
        return i;
      }
    }
    throw new RuntimeException("No meal delivery request found with ID: " + requestID);
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
