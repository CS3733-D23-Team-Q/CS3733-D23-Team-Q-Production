package edu.wpi.cs3733.D23.teamQ.db.impl;

import edu.wpi.cs3733.D23.teamQ.db.dao.GenDao;
import edu.wpi.cs3733.D23.teamQ.db.obj.Move;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MoveDaoImpl implements GenDao<Move, Integer> {
  private List<Move> moves = new ArrayList<>();
  private int nextID = 0;
  private NodeDaoImpl nodeTable;
  private static MoveDaoImpl single_instance = null;

  public static synchronized MoveDaoImpl getInstance(NodeDaoImpl nodeTable) {
    if (single_instance == null) single_instance = new MoveDaoImpl(nodeTable);

    return single_instance;
  }

  private MoveDaoImpl(NodeDaoImpl nodeTable) {
    this.nodeTable = nodeTable;
    populate();
    if (moves.size() != 0) {
      nextID = moves.get(moves.size() - 1).getMoveID() + 1;
    }
  }

  /**
   * returns a move given a nodeID
   *
   * @param moveID of move being retrieved
   * @return a move with the given nodeID
   */
  public Move retrieveRow(Integer moveID) {
    int index = this.getIndex(moveID);
    return moves.get(index);
  }

  /**
   * updates move in list with a new move
   *
   * @param moveID moveID of Move being replaced
   * @param newMove new location being inserted
   * @return true if successful
   */
  public boolean updateRow(Integer moveID, Move newMove) {
    int index = this.getIndex(moveID);
    moves.set(index, newMove);
    return true;
  }

  /**
   * deletes move from list of moves
   *
   * @param moveID of move being deleted
   * @return true if successfully deleted
   */
  public boolean deleteRow(Integer moveID) {
    try (Connection connection = GenDao.connect();
        PreparedStatement st =
            connection.prepareStatement("DELETE FROM move WHERE \"moveID\" = ?")) {;
      st.setInt(1, moveID);
      st.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }

    int index = this.getIndex(moveID);
    moves.remove(index);

    return true;
  }

  /**
   * adds a move to the list
   *
   * @param m move being added
   * @return true if successful
   */
  public boolean addRow(Move m) {
    try (Connection conn = GenDao.connect();
        PreparedStatement stmt =
            conn.prepareStatement(
                "INSERT INTO move(\"nodeID\", \"longName\", \"date\") VALUES (?, ?, ?)")) {
      stmt.setInt(1, m.getNode().getNodeID());
      stmt.setString(2, m.getLongName());
      stmt.setString(3, m.getDate());
      stmt.executeUpdate();
    } catch (SQLException ex) {
      ex.printStackTrace();
    }
    m.setMoveID(nextID);
    nextID++;
    return moves.add(m);
  }

  /**
   * Populates list of moves from database
   *
   * @return true if list populated
   * @throws SQLException
   */
  @Override
  public boolean populate() {
    try {
      Connection conn = GenDao.connect();
      Statement stm = conn.createStatement();
      ResultSet rst = stm.executeQuery("Select * From move");

      while (rst.next()) {
        moves.add(
            new Move(
                rst.getInt("moveID"),
                nodeTable.retrieveRow(rst.getInt("nodeID")),
                rst.getString("longName"),
                rst.getString("date")));
      }
      conn.close();
      stm.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return true;
  }

  /**
   * gets index of given moveID in the list of moves
   *
   * @param moveID moveID being checked
   * @return value of index
   */
  private int getIndex(Integer moveID) {
    for (int i = 0; i < moves.size(); i++) {
      Move m = moves.get(i);
      if (m.getMoveID() == moveID) {
        return i;
      }
    }
    throw new RuntimeException("No move found with ID " + moveID);
  }

  /**
   * function that gets all moves in the list
   *
   * @return all moves in list
   */
  public List<Move> getAllRows() {
    return moves;
  }

  /**
   * Exports database table of moves into a CSV file of a given name
   *
   * @param filename name of file being exported
   * @return true if successfully exported, false otherwise
   */
  public boolean toCSV(String filename) {
    try {
      File myObj = new File(filename);
      if (myObj.createNewFile()) {
        System.out.println("File created: " + myObj.getName());
      }
      FileWriter myWriter = new FileWriter(filename);
      for (int i = 0; i < moves.size(); i++) {
        Move m = moves.get(i);
        myWriter.write(
            m.getMoveID()
                + ','
                + m.getNode().getNodeID()
                + ','
                + m.getLongName()
                + ','
                + m.getDate()
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

  /**
   * Imports moves from csv file into database and local storage
   *
   * @param filename name of file being imported from
   * @return true if successfully imported, false otherwise
   */
  public boolean importCSV(String filename) {
    try {
      File f = new File(filename);
      Scanner myReader = new Scanner(f);
      while (myReader.hasNextLine()) {
        String row = myReader.nextLine();
        String[] vars = row.split(",");
        Move m = new Move(nodeTable.retrieveRow(Integer.parseInt(vars[0])), vars[1], vars[2]);
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
