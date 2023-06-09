package edu.wpi.cs3733.D23.teamQ.db.impl;

import edu.wpi.cs3733.D23.teamQ.db.dao.GenDao;
import edu.wpi.cs3733.D23.teamQ.db.obj.Sign;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignDaoImpl implements GenDao<Sign, Integer> {
  private static SignDaoImpl single_instance = null;
  private List<Sign> signs = new ArrayList<Sign>();
  private String fileName = "Signage.csv";

  public static synchronized SignDaoImpl getInstance() {
    if (single_instance == null) {
      single_instance = new SignDaoImpl();
    }
    return single_instance;
  }

  private SignDaoImpl() {
    System.out.println(signs.size());
    populate();
  }

  public List<Sign> retrieveRows(int kiosk, String date) {
    List<Sign> as = new ArrayList<Sign>();
    List<Integer> index = this.getIndexes(kiosk);
    for (int i : index) {
      if (signs.get(i).getDate().equals(date)) {
        as.add(signs.get(i));
      }
    }
    return as;
  }

  public boolean addRow(Sign a) {
    int kiosk = a.getKiosk();
    String date = a.getDate();
    String destination = a.getDestination();
    String direction = a.getDirection();

    boolean result = false;
    Connection con = GenDao.connect();
    try {
      String query = "INSERT INTO sign (kiosk, date, destination, direction) VALUES(?,?,?,?)";
      PreparedStatement pst = con.prepareStatement(query);
      pst.setInt(1, kiosk);
      pst.setString(2, date);
      pst.setString(3, destination);
      pst.setString(4, direction);

      int rs = pst.executeUpdate();
      if (rs == 1) {
        result = true;
        signs.add(a);
        System.out.println("Sign created successfully!");
      } else {
        System.out.println("Failed to create Sign.");
      }
      con.close();
      pst.close();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return result;
  }

  public List<Sign> getAllRows() {

    return signs;
  }

  @Override
  public Sign retrieveRow(Integer ID) {
    return null;
  }

  @Override
  public boolean updateRow(Integer ID, Sign x) throws SQLException {
    return false;
  }

  @Override
  public boolean deleteRow(Integer ID) throws SQLException {
    return false;
  }

  //  public boolean populate() {
  //    Connection con = GenDao.connect();
  //    try {
  //      String query = "SELECT * FROM sign";
  //      Statement st = con.createStatement();
  //      ResultSet rs = st.executeQuery(query);
  //      while (rs.next()) {
  //        System.out.println("HEEEEREEE");
  //        Sign a;
  //        a = new Sign(
  //                rs.getInt("kiosk"),
  //                rs.getString("date"),
  //                rs.getString("destination"),
  //                rs.getString("directions"));
  //        signs.add(a);
  //        System.out.println("HAHASHDIUeh");
  //      }
  //      System.out.println(signs.get(0));
  //      con.close();
  //      st.close();
  //      return true;
  //    } catch (Exception e) {
  //      System.out.println(e.getMessage());
  //    }
  //    return false;
  //  }

  public boolean populate() {
    try {
      Connection conn = GenDao.connect();
      Statement stm = conn.createStatement();
      ResultSet rs = stm.executeQuery("Select * From \"sign\"");
      while (rs.next()) {
        signs.add(
            new Sign(
                rs.getInt("kiosk"),
                rs.getString("date"),
                rs.getString("destination"),
                rs.getString("direction")));
      }
      conn.close();
      stm.close();
      return true;
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return false;
    }
  }

  public List<Integer> getIndexes(int kiosk) {
    List<Integer> is = new ArrayList<Integer>();
    for (int i = 0; i < signs.size(); i++) {
      Sign a = signs.get(i);
      if (a.getKiosk() == kiosk) {
        is.add(i);
      }
    }
    return is;
  }
}
