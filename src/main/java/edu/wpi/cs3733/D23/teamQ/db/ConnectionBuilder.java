package edu.wpi.cs3733.D23.teamQ.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionBuilder {
  static String url = "NOT SET YET";
  static String user = "teamq";
  static String password = "teamq140";

  public static Connection buildConnection() {
    System.out.println(url);
    Connection con = null;
    try {
      Class.forName("org.postgresql.Driver");
      con = DriverManager.getConnection(url, user, password);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return con;
  }

  public static void setUrl(String url) {
    ConnectionBuilder.url = url;
  }
}
