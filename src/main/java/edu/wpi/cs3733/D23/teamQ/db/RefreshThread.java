package edu.wpi.cs3733.D23.teamQ.db;

import edu.wpi.cs3733.D23.teamQ.db.dao.GenDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import lombok.SneakyThrows;

public class RefreshThread implements Runnable {
  private List<Long> lastUpdates = new ArrayList<>();
  Qdb qdb = Qdb.getInstance();

  private List<String> tableNames =
      List.of(
          new String[] {
            "account",
            "edge",
            "locationName",
            "move",
            "node",
            "profileImage",
            "security_question",
            "serviceRequest",
            "message",
            "alert",
            "sign"
          });

  private ArrayList<String> toUpdate = new ArrayList<>();

  @SneakyThrows
  public void run() {
    if (lastUpdates.isEmpty()) {
      for (int i = 0; i < tableNames.size(); i++) {
        lastUpdates.add(System.currentTimeMillis());
      }
    }

    while (true) {
      if (checkUpdates().size() > 0) {
        System.out.println("Updating from server --> " + toUpdate);
        for (String s : toUpdate) {
          lastUpdates.set(tableNames.indexOf(s), System.currentTimeMillis() - 1000);
        }
      }
      doUpdates();

      Thread.sleep(1000);
      toUpdate.clear();
    }
  }

  private ArrayList<String> checkUpdates() {
    try {
      Connection conn = GenDao.connect();
      PreparedStatement pst = conn.prepareStatement("SELECT * FROM \"timestamp\"");
      ResultSet rs = pst.executeQuery();
      while (rs.next()) {
        for (String tableName : tableNames) {
          if (rs.getString("tableName").equals(tableName)) {
            long lastUpdate = lastUpdates.get(tableNames.indexOf(tableName));
            if (rs.getLong("updated_timestamp") > lastUpdate) {
              toUpdate.add(tableName);
            }
          }
        }
      }
      conn.close();
      pst.close();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return toUpdate;
  }

  private void doUpdates() {
    qdb.populate(toUpdate);
    for (String tableName : toUpdate) {
      System.out.println("Updated " + tableName + " from client server.");
    }
  }
}
