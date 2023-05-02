package edu.wpi.cs3733.D23.teamQ.db.dao;

import edu.wpi.cs3733.D23.teamQ.db.ConnectionBuilder;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface GenDao<T, G> {
  static Connection connect() {
    return ConnectionBuilder.buildConnection();
  }

  public List<T> getAllRows();

  public T retrieveRow(G ID);

  public boolean updateRow(G ID, T x) throws SQLException;

  public boolean deleteRow(G ID) throws SQLException;

  public boolean addRow(T x);

  public boolean populate() throws SQLException;

  public String getFileName();
}
