package edu.wpi.cs3733.D23.teamQ.db.impl;

import edu.wpi.cs3733.D23.teamQ.db.dao.GenDao;
import edu.wpi.cs3733.D23.teamQ.db.obj.Location;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LocationDaoImpl implements GenDao<Location, Integer> {
  private List<Location> locations = new ArrayList<Location>();
  private static LocationDaoImpl single_instance = null;

  private LocationDaoImpl() {
    populate();
  }

  public static synchronized LocationDaoImpl getInstance() {
    if (single_instance == null) single_instance = new LocationDaoImpl();

    return single_instance;
  }

  /**
   * returns a location given a nodeID
   *
   * @param nodeID of location being retrieved
   * @return a node with the given nodeID
   */
  public Location retrieveRow(Integer nodeID) {
    int index = this.getIndex(nodeID);
    return locations.get(index);
  }

  /**
   * updates location in linked list with a new location
   *
   * @param nodeID nodeID of location being replaced
   * @param newLocation new location being inserted
   * @return true if successful
   */
  public boolean updateRow(Integer nodeID, Location newLocation) {
    deleteRow(nodeID);
    addRow(newLocation);

    int index = this.getIndex(nodeID);
    locations.set(index, newLocation);
    return true;
  }

  /**
   * deletes location from list of locations
   *
   * @param nodeID of location being deleted
   * @return true if successfully deleted
   */
  public boolean deleteRow(Integer nodeID) {
    try (Connection connection = GenDao.connect();
        PreparedStatement st =
            connection.prepareStatement("DELETE FROM \"locationName\" WHERE \"nodeID\" = ?")) {;
      st.setInt(1, nodeID);
      st.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }

    int index = this.getIndex(nodeID);
    locations.remove(index);

    return true;
  }

  /**
   * adds a location to the list
   *
   * @param l location being added
   * @return true if successful
   */
  public boolean addRow(Location l) {
    try (Connection conn = GenDao.connect();
        PreparedStatement stmt =
            conn.prepareStatement(
                "INSERT INTO \"locationName\"(\"nodeID\", \"longName\", \"shortName\", \"nodeType\") VALUES (?, ?, ?, ?)")) {
      stmt.setInt(1, l.getNodeID());
      stmt.setString(2, l.getLongName());
      stmt.setString(3, l.getShortName());
      stmt.setString(4, l.getNodeType());
      stmt.executeUpdate();
    } catch (SQLException ex) {
      ex.printStackTrace();
    }
    return locations.add(l);
  }

  public boolean populate() {
    try {
      Connection conn = GenDao.connect();
      Statement stm = conn.createStatement();
      ResultSet rst = stm.executeQuery("Select * From \"locationName\"");
      while (rst.next()) {
        locations.add(
            new Location(
                rst.getInt("nodeID"),
                rst.getString("longName"),
                rst.getString("shortName"),
                rst.getString("nodeType")));
      }
      conn.close();
      stm.close();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return true;
  }

  /**
   * gets index of given nodeID in the list of locations
   *
   * @param nodeID nodeID being checked
   * @return value of index
   */
  private int getIndex(Integer nodeID) {
    for (int i = 0; i < locations.size(); i++) {
      Location l = locations.get(i);
      if (l.getNodeID() == nodeID) {
        return i;
      }
    }
    throw new RuntimeException("No location found with ID " + nodeID);
  }

  /**
   * function that gets all locations in the list
   *
   * @return all locations in list
   */
  public List<Location> getAllRows() {
    return locations;
  }

  public boolean toCSV(String filename) {
    try {
      File myObj = new File(filename);
      if (myObj.createNewFile()) {
        System.out.println("File created: " + myObj.getName());
      }
      FileWriter myWriter = new FileWriter(filename);
      for (int i = 0; i < locations.size(); i++) {
        Location l = locations.get(i);
        myWriter.write(
            l.getNodeID()
                + ','
                + l.getLongName()
                + ','
                + l.getShortName()
                + ','
                + l.getNodeType()
                + "\n");
      }
      myWriter.close();
      System.out.println("Successfully wrote to the file.");
      return true;
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
      return false;
    } catch(Exception e) {
      return false;
    }

  }

  public boolean importCSV(String filename) {
    try {
      File f = new File(filename);
      Scanner myReader = new Scanner(f);
      while (myReader.hasNextLine()) {
        String row = myReader.nextLine();
        String[] vars = row.split(",");
        Location l = new Location(Integer.parseInt(vars[0]), vars[1], vars[2], vars[3]);
        addRow(l);
      }
      myReader.close();
      return true;
    } catch (FileNotFoundException e) {
      return false;
    } catch (Exception e) {
      return false;
    }
  }
}
