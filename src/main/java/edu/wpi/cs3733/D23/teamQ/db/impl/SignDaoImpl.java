package edu.wpi.cs3733.D23.teamQ.db.impl;

import edu.wpi.cs3733.D23.teamQ.db.Qdb;
import edu.wpi.cs3733.D23.teamQ.db.dao.GenDao;
import edu.wpi.cs3733.D23.teamQ.db.obj.Sign;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SignDaoImpl implements GenDao<Sign, Integer> {
  private static SignDaoImpl single_instance = null;
  private List<Sign> signs = new ArrayList<Sign>();

  public static synchronized SignDaoImpl getInstance() {
    if (single_instance == null) {
      single_instance = new SignDaoImpl();
    }
    return single_instance;
  }

  private SignDaoImpl() {
    populate();
  }

  public Sign retrieveRow(Integer idNum) {
    int index = this.getIndex(idNum);
    return signs.get(index);
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

  public boolean updateRow(Integer idNum, Sign SignWithNewChanges) {
    boolean result = false;
    Connection con = GenDao.connect();
    int newKiosk = SignWithNewChanges.getKiosk();
    String newDate = SignWithNewChanges.getDate();
    String newDestination = SignWithNewChanges.getDestination();
    String newDirection = SignWithNewChanges.getDirection();

    try {
      String query =
          "UPDATE sign SET kiosk =?, date = ?, destination = ?, direction = ? WHERE idnum = ?";
      PreparedStatement pst = con.prepareStatement(query);
      pst.setInt(1, newKiosk);
      pst.setString(2, newDate);
      pst.setString(3, newDestination);
      pst.setString(4, newDirection);
      pst.setInt(5, idNum);

      int rs = pst.executeUpdate();
      if (rs == 1) {
        result = true;
        int index = this.getIndex(idNum);
        signs.get(index).setKiosk(newKiosk);
        signs.get(index).setDate(newDate);
        signs.get(index).setDestination(newDestination);
        signs.get(index).setDirection(newDirection);
        System.out.println("Updated successful!");
      } else {
        System.out.println("Failed to update.");
      }
      con.close();
      pst.close();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return result;
  }

  public boolean deleteRow(Integer idNum) {
    Qdb qdb = Qdb.getInstance();
    boolean result = false;
    Connection con = GenDao.connect();
    try {
      String query = "DELETE FROM sign WHERE idnum = ?";
      PreparedStatement pst = con.prepareStatement(query);
      pst.setInt(1, idNum);
      int rs = pst.executeUpdate();
      if (rs == 1) {
        result = true;
        int index = this.getIndex(idNum);
        signs.remove(index);
        System.out.println("Sign deleted successfully!");
      } else {
        System.out.println("Failed to delete Sign.");
      }
      con.close();
      pst.close();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }

    return result;
  }

  public boolean addRow(Sign a) {
    int kiosk = a.getKiosk();
    String date = a.getDate();
    String destination = a.getDestination();
    String direction = a.getDirection();

    boolean result = false;
    Connection con = GenDao.connect();
    try {
      String query =
          "INSERT INTO account (idnum, kiosk, date, destination, direction) VALUES(?,?,?,?,?)";
      PreparedStatement pst = con.prepareStatement(query);
      pst.setInt(1, getAllRows().get(getAllRows().size() - 1).getIdNum() + 1);
      pst.setInt(2, kiosk);
      pst.setString(3, date);
      pst.setString(4, destination);
      pst.setString(5, direction);

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

  @Override
  public List<Sign> getAllRows() {
    return signs;
  }

  public boolean populate() {
    Connection con = GenDao.connect();
    try {
      String query = "SELECT * FROM sign";
      Statement st = con.createStatement();
      ResultSet rs = st.executeQuery(query);
      while (rs.next()) {
        Sign a;
        a =
            new Sign(
                rs.getInt("idnum"),
                rs.getInt("kiosk"),
                rs.getString("date"),
                rs.getString("destination"),
                rs.getString("directions"));
        signs.add(a);
      }
      con.close();
      st.close();
      return true;
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return false;
  }

  public int getIndex(Integer idNum) {
    for (int i = 0; i < signs.size(); i++) {
      Sign a = signs.get(i);
      if (a.getIdNum() == idNum) {
        return i;
      }
    }
    return -1;
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
