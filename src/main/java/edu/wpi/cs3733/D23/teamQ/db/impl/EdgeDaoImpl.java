package edu.wpi.cs3733.D23.teamQ.db.impl;

import edu.wpi.cs3733.D23.teamQ.db.dao.GenDao;
import edu.wpi.cs3733.D23.teamQ.db.obj.Edge;
import edu.wpi.cs3733.D23.teamQ.db.obj.Node;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import lombok.Getter;

@Getter
public class EdgeDaoImpl implements GenDao<Edge, Integer> {
  private List<Edge> edges = new ArrayList<>();
  private int nextID = 0;
  private static EdgeDaoImpl single_instance = null;
  private String fileName = "Edges.csv";

  private EdgeDaoImpl() {
    populate();
    if (edges.size() != 0) {
      nextID = edges.get(edges.size() - 1).getEdgeID() + 1;
    }
  }

  public static synchronized EdgeDaoImpl getInstance() {
    if (single_instance == null) single_instance = new EdgeDaoImpl();

    return single_instance;
  }

  public Edge retrieveRow(Integer edgeID) {
    int index = this.getIndex(edgeID);
    return edges.get(index);
  }

  /**
   * updates edge in linked list with a new edge
   *
   * @param edgeID edgeID of edge being replaced
   * @param newEdge new edge being inserted
   * @return true if successful
   */
  public boolean updateRow(Integer edgeID, Edge newEdge) {
    try (Connection connection = GenDao.connect();
        PreparedStatement st =
            connection.prepareStatement(
                "UPDATE edge SET \"edgeID\" = ?, \"startNode\" = ?, \"endNode\" = ? "
                    + "WHERE \"edgeID\" = ?")) {

      st.setInt(1, edgeID);
      st.setInt(2, newEdge.getStartNode().getNodeID());
      st.setInt(3, newEdge.getEndNode().getNodeID());
      st.setInt(4, edgeID);

      st.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }

    int index = this.getIndex(edgeID);
    edges.get(index).setStartNode(newEdge.getStartNode());
    edges.get(index).setEndNode(newEdge.getEndNode());

    return true;
  }

  /**
   * deletes edges from list of edges
   *
   * @param edgeID of node being deleted
   * @return true if successfully deleted
   */
  public boolean deleteRow(Integer edgeID) {
    try (Connection conn = GenDao.connect();
        PreparedStatement stmt =
            conn.prepareStatement("DELETE FROM \"edge\" WHERE \"edgeID\" = ?")) {
      stmt.setInt(1, edgeID);
      stmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    int index = this.getIndex(edgeID);
    edges.remove(index);
    return true;
  }

  /**
   * adds a edge to the linked list
   *
   * @param e edge being added
   * @return true if successful
   */
  public boolean addRow(Edge e) {
    try (Connection conn = GenDao.connect();
        PreparedStatement stmt =
            conn.prepareStatement(
                "INSERT INTO edge(\"edgeID\", \"startNode\", \"endNode\") VALUES (?, ?, ?)")) {
      stmt.setInt(1, edges.get(edges.size() - 1).getEdgeID() + 1);
      stmt.setInt(2, e.getStartNode().getNodeID());
      stmt.setInt(3, e.getEndNode().getNodeID());
      stmt.executeUpdate();
    } catch (SQLException ex) {
      ex.printStackTrace();
    }
    e.setEdgeID(edges.get(edges.size() - 1).getEdgeID() + 1);
    return edges.add(e);
  }

  @Override
  public boolean populate() {
    try {
      edges.clear();
      Connection conn = GenDao.connect();
      Statement stm = conn.createStatement();
      ResultSet rst = stm.executeQuery("Select * From \"edge\"");
      while (rst.next()) {
        Node startNode = NodeDaoImpl.getInstance().retrieveRow(rst.getInt("startNode"));
        Node endNode = NodeDaoImpl.getInstance().retrieveRow(rst.getInt("endNode"));
        Edge e = new Edge(rst.getInt("edgeID"), startNode, endNode);
        edges.add(e);
        try {
          startNode.addBranch(e);
          endNode.addBranch(e);
        } catch (Exception ex) {
          System.out.println(ex.getMessage());
        }
      }
      conn.close();
      stm.close();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return true;
  }

  /**
   * gets index of given edgeID in the list of edges
   *
   * @param edgeID edgeID being checked
   * @return value of index
   */
  private int getIndex(Integer edgeID) {
    for (int i = 0; i < edges.size(); i++) {
      Edge e = edges.get(i);
      if (e.getEdgeID() == edgeID) {
        return i;
      }
    }
    throw new RuntimeException("No edge found with ID " + edgeID);
  }

  /**
   * function that gets all edges in the list
   *
   * @return all edges in list
   */
  public List<Edge> getAllRows() {
    return edges;
  }

  /**
   * function that exports edge table into given csv file
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
      for (int i = 0; i < edges.size(); i++) {
        Edge e = edges.get(i);
        myWriter.write(
            String.valueOf(e.getEdgeID())
                + ','
                + String.valueOf(e.getStartNode().getNodeID())
                + ','
                + String.valueOf(e.getEndNode().getNodeID())
                + "\n");
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
        Edge e =
            new Edge(
                Integer.parseInt(vars[1]),
                NodeDaoImpl.getInstance().retrieveRow(Integer.parseInt(vars[2])),
                NodeDaoImpl.getInstance().retrieveRow(Integer.parseInt(vars[2])));
        addRow(e);
      }
      myReader.close();
      return true;
    } catch (FileNotFoundException e) {
      return false;
    } catch (Exception e) {
      return false;
    }
  }

  /**
   * return a list of a given nodeID's edges
   *
   * @param nodeID
   * @return
   */
  public List<Edge> getEdges(int nodeID) {
    List<Edge> edgeList = new ArrayList<>();
    for (Edge edge : edges) {
      if (edge.getStartNode().getNodeID() == nodeID || edge.getEndNode().getNodeID() == nodeID) {
        edgeList.add(edge);
      }
    }
    return edgeList;
  }
}
