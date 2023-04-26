package edu.wpi.cs3733.D23.teamQ.db;

import edu.wpi.cs3733.D23.teamQ.db.dao.GenDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import javafx.application.Platform;
import lombok.SneakyThrows;

public class RefreshThread implements Runnable {
  private long lastUpdate = System.currentTimeMillis();
  Qdb qdb = Qdb.getInstance();

  private String[] tableNames = {
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
  };

  private ArrayList<String> toUpdate = new ArrayList<>();

  @SneakyThrows
  public void run() {
    while (true) {
      if (checkUpdates().size() > 0) {
        doUpdates();
        toUpdate.clear();
        lastUpdate = System.currentTimeMillis();
      }
      Thread.sleep(3000);
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
    Platform.runLater(() -> qdb.notifySubscribers(Arrays.stream(tableNames).toList()));
    for (String tableName : toUpdate) {
      System.out.println("Updated " + tableName + " from client server.");
    }
  }
}
