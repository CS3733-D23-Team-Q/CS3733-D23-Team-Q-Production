package edu.wpi.cs3733.D23.teamQ.db.impl;

import edu.wpi.cs3733.D23.teamQ.db.dao.GenDao;
import edu.wpi.cs3733.D23.teamQ.db.obj.Settings;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SettingsDaoImpl implements GenDao<Settings, String> {
  private List<Settings> settings = new ArrayList<Settings>();
  private static SettingsDaoImpl single_instance = null;
  private String fileName = "Settings.csv";

  public static synchronized SettingsDaoImpl getInstance() {
    if (single_instance == null) {
      try {
        single_instance = new SettingsDaoImpl();
      } catch (SQLException e) {
        throw new RuntimeException(e);
      }
    }
    return single_instance;
  }

  private SettingsDaoImpl() throws SQLException {
    populate();
  }

  @Override
  public List<Settings> getAllRows() {
    return settings;
  }

  public int getIndex(String uname) {
    for (int i = 0; i < settings.size(); i++) {
      Settings setting = settings.get(i);
      if (setting.getUsername().equals(uname)) {
        return i;
      }
    }
    return -1;
  }

  @Override
  public Settings retrieveRow(String username) {
    int index = this.getIndex(username);
    return settings.get(index);
  }

  @Override
  public boolean updateRow(String username, Settings x) {
    try (Connection connection = GenDao.connect();
        PreparedStatement st =
            connection.prepareStatement(
                "UPDATE \"settings\" SET username = ?, \"twoFactor\" = ?, sound = ?, algorithm = ?, voice = ? "
                    + "WHERE username = ?")) {

      st.setString(1, username);
      st.setBoolean(2, x.isTwoFactor());
      st.setBoolean(3, x.isSound());
      st.setInt(4, x.getAlgorithm().ordinal());
      st.setInt(5, x.getVoice().ordinal());
      st.setString(6, username);

      st.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }

    int index = this.getIndex(username);
    settings.get(index).setTwoFactor(x.isTwoFactor());
    settings.get(index).setSound(x.isSound());
    settings.get(index).setAlgorithm(x.getAlgorithm());
    settings.get(index).setVoice(x.getVoice());

    return true;
  }

  @Override
  public boolean deleteRow(String username) {
    try (Connection connection = GenDao.connect();
        PreparedStatement st =
            connection.prepareStatement("DELETE FROM \"settings\" WHERE \"username\" = ?")) {;
      st.setString(1, username);
      st.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }

    int index = this.getIndex(username);
    settings.remove(index);

    return true;
  }

  @Override
  public boolean addRow(Settings x) {
    boolean result = false;
    try (Connection conn = GenDao.connect();
        PreparedStatement stmt =
            conn.prepareStatement(
                "INSERT INTO \"settings\"(username, \"twoFactor\", sound, algorithm, voice) VALUES (?, ?, ?, ?, ?)")) {
      stmt.setString(1, x.getUsername());
      stmt.setBoolean(2, x.isTwoFactor());
      stmt.setBoolean(3, x.isSound());
      stmt.setInt(4, x.getAlgorithm().ordinal());
      stmt.setInt(5, x.getVoice().ordinal());
      int rs = stmt.executeUpdate();
      if (rs == 1) {
        result = true;
        settings.add(x);
        System.out.println("Settings created.");
      } else {
        System.out.println("Failed to create settings.");
      }
    } catch (SQLException ex) {
      ex.printStackTrace();
    }
    return result;
  }

  @Override
  public boolean populate() {
    Connection connection = GenDao.connect();
    try {
      settings.clear();
      String query = "SELECT * FROM \"settings\"";
      Statement statement = connection.createStatement();
      ResultSet rs = statement.executeQuery(query);
      while (rs.next()) {
        Settings setting =
            new Settings(
                rs.getString("username"),
                rs.getBoolean("twoFactor"),
                rs.getBoolean("sound"),
                rs.getInt("algorithm"),
                rs.getInt("voice"));
        settings.add(setting);
      }
      connection.close();
      statement.close();
      return true;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return false;
  }

  @Override
  public String getFileName() {
    return fileName;
  }
}
