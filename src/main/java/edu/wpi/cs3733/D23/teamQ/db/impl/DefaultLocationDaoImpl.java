package edu.wpi.cs3733.D23.teamQ.db.impl;

import edu.wpi.cs3733.D23.teamQ.db.dao.GenDao;
import edu.wpi.cs3733.D23.teamQ.db.obj.DefaultLocation;
import edu.wpi.cs3733.D23.teamQ.db.obj.Location;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DefaultLocationDaoImpl implements GenDao<DefaultLocation, Integer> {
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

  private int getIndex(Integer id) {
    for (int i = 0; i < defaultLocations.size(); i++) {
      DefaultLocation dl = defaultLocations.get(i);
      if (dl.getDefaultLocationID() == id) {
        return i;
      }
    }
    throw new RuntimeException("No DefaultLocation found with ID " + id);
  }

  @Override
  public List<DefaultLocation> getAllRows() {
    return defaultLocations;
  }

  @Override
  public DefaultLocation retrieveRow(Integer ID) {
    int index = this.getIndex(ID);
    return defaultLocations.get(index);
  }

  @Override
  public boolean updateRow(Integer ID, DefaultLocation x) {
    try (Connection connection = GenDao.connect();
        PreparedStatement st =
            connection.prepareStatement(
                "UPDATE \"defaultLocation\" SET \"defaultLocationID\" = ?, \"startingLocation\" = ?, kiosks = ? "
                    + "WHERE \"defaultLocationID\" = ?")) {

      st.setInt(1, ID);
      st.setString(2, x.getStartingLocation().getLongName());

      ArrayList<String> longNames = new ArrayList<>();
      for (Location kiosk : x.getKiosks()) {
        longNames.add(kiosk.getLongName());
      }
      String[] longNamesArray = longNames.toArray(new String[longNames.size()]);
      Array sqlArray = connection.createArrayOf("text", longNamesArray);
      st.setArray(3, sqlArray);
      st.setInt(4, ID);

      st.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }

    int index = this.getIndex(ID);
    defaultLocations.get(index).setStartingLocation(x.getStartingLocation());
    defaultLocations.get(index).setKiosks(x.getKiosks());

    return true;
  }

  @Override
  public boolean deleteRow(Integer ID) {
    try (Connection connection = GenDao.connect();
        PreparedStatement st =
            connection.prepareStatement(
                "DELETE FROM \"defaultLocation\" WHERE \"defaultLocationID\" = ?")) {;
      st.setInt(1, ID);
      st.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }

    int index = this.getIndex(ID);
    defaultLocations.remove(index);

    return true;
  }

  @Override
  public boolean addRow(DefaultLocation x) {
    boolean result = false;
    try (Connection conn = GenDao.connect();
        PreparedStatement stmt =
            conn.prepareStatement(
                "INSERT INTO \"defaultLocation\"(\"startingLocation\", kiosks) VALUES (?, ?)")) {
      stmt.setString(1, x.getStartingLocation().getLongName());

      ArrayList<String> longNames = new ArrayList<>();
      for (Location kiosk : x.getKiosks()) {
        longNames.add(kiosk.getLongName());
      }
      String[] longNamesArray = longNames.toArray(new String[longNames.size()]);
      Array sqlArray = conn.createArrayOf("text", longNamesArray);

      stmt.setArray(2, sqlArray);

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

  @Override
  public boolean populate() {
    LocationDaoImpl locationDao = LocationDaoImpl.getInstance();
    Connection connection = GenDao.connect();
    try {
      defaultLocations.clear();
      String query = "SELECT * FROM \"defaultLocation\"";
      Statement statement = connection.createStatement();
      ResultSet rs = statement.executeQuery(query);
      while (rs.next()) {

        Array kiosks = rs.getArray("kiosks");
        String[] kioskList = (String[]) kiosks.getArray();
        Location[] locations = new Location[kioskList.length];
        for (int i = 0; i < kioskList.length; i++) {
          locations[i] = locationDao.retrieveLocationFromLongName(kioskList[i]);
        }

        DefaultLocation dl =
            new DefaultLocation(
                rs.getInt("defaultLocationID"),
                locationDao.retrieveLocationFromLongName(rs.getString("startingLocation")),
                locations);
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

  @Override
  public String getFileName() {
    return fileName;
  }
}
