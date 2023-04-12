package edu.wpi.cs3733.D23.teamQ.db.impl;

import edu.wpi.cs3733.D23.teamQ.db.dao.GenDao;
import edu.wpi.cs3733.D23.teamQ.db.obj.Node;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NodeDaoImpl implements GenDao<Node, Integer> {
  private List<Node> nodes = new ArrayList<>();
  private LocationDaoImpl locationTable;
  private static NodeDaoImpl single_instance = null;

  private NodeDaoImpl(LocationDaoImpl locationTable) {
    this.locationTable = locationTable;
    populate();
  }

  public static synchronized NodeDaoImpl getInstance(LocationDaoImpl locationTable) {
    if (single_instance == null) single_instance = new NodeDaoImpl(locationTable);

    return single_instance;
  }

  /**
   * returns a node given a nodeID
   *
   * @param nodeID of node being retrieved
   * @return a node with the given nodeID
   */
  public Node retrieveRow(Integer nodeID) {
    try {
      int index = this.getIndex(nodeID);
      return nodes.get(index);
    } catch (Exception e) {
      return null;
    }
  }

  /**
   * updates node in linked list with a new node
   *
   * @param nodeID nodeID of node being replaced
   * @param newNode new node being inserted
   * @return true if successful
   */
  public boolean updateRow(Integer nodeID, Node newNode) {
    deleteRow(nodeID);
    addRow(newNode);
    int index = this.getIndex(nodeID);
    nodes.set(index, newNode);
    return true;
  }

  /**
   * deletes nodes from list of nodes
   *
   * @param nodeID of node being deleted
   * @return true if successfully deleted
   */
  public boolean deleteRow(Integer nodeID) {
    try (Connection conn = GenDao.connect();
        PreparedStatement stmt =
            conn.prepareStatement("DELETE FROM \"node\" WHERE \"nodeID\" = ?")) {
      stmt.setInt(1, nodeID);
      stmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    int index = this.getIndex(nodeID);
    nodes.remove(index);
    return true;
  }

  /**
   * adds a node to the linked list
   *
   * @param n node being added
   * @return true if successful
   */
  public boolean addRow(Node n) {
    try (Connection conn = GenDao.connect();
        PreparedStatement stmt =
            conn.prepareStatement(
                "INSERT INTO \"node\"(\"nodeID\", xcoord, ycoord, floor, building) VALUES (?, ?, ?, ?, ?)")) {
      stmt.setInt(1, n.getNodeID());
      stmt.setInt(2, n.getXCoord());
      stmt.setInt(3, n.getYCoord());
      stmt.setString(4, n.getFloor());
      stmt.setString(5, n.getBuilding());
      stmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return nodes.add(n);
  }

  @Override
  public boolean populate() {
    try {
      Connection conn = GenDao.connect();
      PreparedStatement pst = conn.prepareStatement("SELECT * FROM \"node\"");
      ResultSet rs = pst.executeQuery();
      while (rs.next()) {
        int nodeID = rs.getInt("nodeID");
        nodes.add(
            new Node(
                nodeID,
                rs.getInt("xcoord"),
                rs.getInt("ycoord"),
                rs.getString("floor"),
                rs.getString("building"),
                locationTable.retrieveRow(nodeID)));
      }
      conn.close();
      pst.close();

      return true;
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return false;
  }

  /**
   * gets index of given nodeID in the list of nodes
   *
   * @param nodeID nodeID being checked
   * @return value of index
   */
  private int getIndex(Integer nodeID) {
    for (int i = 0; i < nodes.size(); i++) {
      Node n = nodes.get(i);
      if (n.getNodeID() == nodeID) {
        return i;
      }
    }
    throw new RuntimeException("No node found with ID " + nodeID);
  }

  /**
   * function that gets all nodes in the list
   *
   * @return all nodes in list
   */
  public List<Node> getAllRows() {
    return nodes;
  }

  /**
   * function that exports node table into given csv file
   *
   * @param filename csv file to export to
   * @return true if successfully exported, false otherwise
   */
  public boolean toCSV(String filename) {
    try {
      File myObj = new File(filename);
      if (myObj.createNewFile()) {
        System.out.println("File created: " + myObj.getName());
      }
      FileWriter myWriter = new FileWriter(filename);
      for (int i = 0; i < nodes.size(); i++) {
        Node n = nodes.get(i);
        myWriter.write(
                String.valueOf(n.getNodeID())
                + ','
                + String.valueOf(n.getXCoord())
                + ','
                + String.valueOf(n.getYCoord())
                + ','
                + n.getFloor()
                + ','
                + n.getBuilding()
                + ",\n");
      }
      myWriter.close();
      System.out.println("Successfully wrote to the file.");
      return true;
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
      return false;
    } catch (Exception e) {
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
        Node m =
            new Node(
                Integer.parseInt(vars[0]),
                Integer.parseInt(vars[1]),
                Integer.parseInt(vars[2]),
                vars[3],
                vars[4],
                locationTable.retrieveRow(Integer.parseInt(vars[0])));
        addRow(m);
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
