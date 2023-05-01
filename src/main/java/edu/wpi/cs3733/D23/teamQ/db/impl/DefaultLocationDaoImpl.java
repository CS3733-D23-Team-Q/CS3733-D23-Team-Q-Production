package edu.wpi.cs3733.D23.teamQ.db.impl;

import edu.wpi.cs3733.D23.teamQ.db.dao.GenDao;
import edu.wpi.cs3733.D23.teamQ.db.obj.DefaultLocation;
import edu.wpi.cs3733.D23.teamQ.db.obj.Location;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DefaultLocationDaoImpl implements GenDao<DefaultLocation, String> {
  private List<DefaultLocation> defaultLocations = new ArrayList<>();
  private static DefaultLocationDaoImpl single_instance = null;
  private String fileName = "Default_Locations.csv";

  private DefaultLocationDaoImpl() throws SQLException {
    populate();
  }

  public static synchronized DefaultLocationDaoImpl getInstance() {
    if (single_instance == null) {
      try {
        single_instance = new DefaultLocationDaoImpl();
      } catch (SQLException e) {
        throw new RuntimeException(e);
      }
    }
    return single_instance;
  }

  private int getIndex(String username) {
    for (int i = 0; i < defaultLocations.size(); i++) {
      DefaultLocation dl = defaultLocations.get(i);
      if (dl.getUsername().equals(username)) {
        return i;
      }
    }
    throw new RuntimeException("No DefaultLocation found with username " + username);
  }


  public List<DefaultLocation> getAllRows() {
    return defaultLocations;
  }


  public DefaultLocation retrieveRow(String username) {
    int index = this.getIndex(username);
    return defaultLocations.get(index);
  }


  public boolean updateRow(String username, DefaultLocation x) {
    try (Connection connection = GenDao.connect();
        PreparedStatement st =
            connection.prepareStatement(
                "UPDATE \"defaultLocation\" SET \"username\" = ?, \"startingLocation\" = ? "
                    + "WHERE \"username\" = ?")) {

      st.setString(1, username);
      st.setString(2, x.getStartingLocation().getLongName());
      st.setString(3, username);

      st.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }

    int index = this.getIndex(username);
    defaultLocations.get(index).setStartingLocation(x.getStartingLocation());

    return true;
  }

  public boolean deleteRow(String username) {
    try (Connection connection = GenDao.connect();
        PreparedStatement st =
            connection.prepareStatement(
                "DELETE FROM \"defaultLocation\" WHERE \"username\" = ?")) {;
      st.setString(1, username);
      st.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }

    int index = this.getIndex(username);
    defaultLocations.remove(index);

    return true;
  }

  public boolean addRow(DefaultLocation x) {
    boolean result = false;
    try (Connection conn = GenDao.connect();
        PreparedStatement stmt =
            conn.prepareStatement(
                "INSERT INTO \"defaultLocation\"(\"username\", \"startingLocation\") VALUES (?, ?)")) {

      stmt.setString(1, x.getUsername());
      stmt.setString(2, x.getStartingLocation().getLongName());

      int rs = stmt.executeUpdate();
      if (rs == 1) {
        result = true;
        defaultLocations.add(x);
        System.out.println("DefaultLocation created.");
      } else {
        System.out.println("Failed to create DefaultLocation.");
      }
    } catch (SQLException ex) {
      ex.printStackTrace();
    }
    return result;
  }

  public boolean populate() {
    LocationDaoImpl locationDao = LocationDaoImpl.getInstance();
    Connection connection = GenDao.connect();
    try {
      defaultLocations.clear();
      String query = "SELECT * FROM \"defaultLocation\"";
      Statement statement = connection.createStatement();
      ResultSet rs = statement.executeQuery(query);
      while (rs.next()) {
        DefaultLocation dl =
            new DefaultLocation(
                rs.getString("username"),
                locationDao.retrieveLocationFromLongName(rs.getString("startingLocation")));
        defaultLocations.add(dl);
      }
      connection.close();
      statement.close();
      return true;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return false;
  }

  public String getFileName() {
    return fileName;
  }
}
