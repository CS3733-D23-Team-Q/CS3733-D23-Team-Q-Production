package edu.wpi.cs3733.D23.teamQ.db;

import edu.wpi.cs3733.D23.teamQ.db.dao.GenDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RefreshThread implements Runnable {
  private long lastUpdate = System.currentTimeMillis();

  public void run() {
    try {
      System.out.println("Database refresh thread is now running.");
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }

    Qdb qdb = Qdb.getInstance();

    String[] tableNames = {
      "account",
      "edge",
      "locationName",
      "move",
      "node",
      "profileImage",
      "security_question",
      "serviceRequest"
    };
    ArrayList<String> toUpdate = new ArrayList<>();
    while (true) {
      try {
        Connection conn = GenDao.connect();
        PreparedStatement pst = conn.prepareStatement("SELECT * FROM \"timestamp\"");
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
          for (String tableName : tableNames) {
            if (rs.getString("tableName").equals(tableName)) {
              if (rs.getLong("updated_timestamp") > lastUpdate) {
                toUpdate.add(tableName);
              }
            }
          }
        }
      } catch (SQLException e) {
        throw new RuntimeException(e);
      }

      try {
        if (toUpdate.size() > 0) {
          System.out.println(toUpdate);
          qdb.populate(toUpdate);
          setTimestamps(toUpdate);
          lastUpdate = System.currentTimeMillis();
          for (String tableName : toUpdate) {
            System.out.println("Updated " + tableName + " from client server.");
          }
        }
        toUpdate.clear();
        Thread.sleep(2000);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }
  }

  private boolean setTimestamps(ArrayList<String> tableNames) {
    long timestamp = System.currentTimeMillis();

    try (Connection connection = GenDao.connect();
        PreparedStatement st =
            connection.prepareStatement(
                "UPDATE \"timestamp\" SET updated_timestamp = ? WHERE \"tableName\" = ?")) {

      for (String tableName : tableNames) {
        st.setLong(1, timestamp);
        st.setString(2, tableName);
        st.executeUpdate();
      }

      return true;
    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    }
  }

  private void refresh() {
    // Refresh db related functionality here.
  }
}
