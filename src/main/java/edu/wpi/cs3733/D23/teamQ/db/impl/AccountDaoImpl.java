package edu.wpi.cs3733.D23.teamQ.db.impl;

import edu.wpi.cs3733.D23.teamQ.db.Qdb;
import edu.wpi.cs3733.D23.teamQ.db.dao.GenDao;
import edu.wpi.cs3733.D23.teamQ.db.obj.Account;
import edu.wpi.cs3733.D23.teamQ.db.obj.ServiceRequest;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AccountDaoImpl implements GenDao<Account, String> {
  private static AccountDaoImpl single_instance = null;
  private List<Account> accounts = new ArrayList<Account>();

  public static synchronized AccountDaoImpl getInstance() {
    if (single_instance == null) {
      single_instance = new AccountDaoImpl();
    }
    return single_instance;
  }

  private AccountDaoImpl() {
    populate();
  }

  public Account retrieveRow(String uname) {
    int index = this.getIndex(uname);
    return accounts.get(index);
  }

  public List<Account> retrieveRows(String email) {
    List<Account> as = new ArrayList<Account>();
    List<Integer> index = this.getIndexes(email);
    for (int i : index) {
      as.add(accounts.get(i));
    }
    return as;
  }

  public boolean updateRow(String uname, Account accountWithNewChanges) {
    boolean result = false;
    Connection con = GenDao.connect();
    String newPass = accountWithNewChanges.getPassword();
    String newEmail = accountWithNewChanges.getEmail();
    int newq1 = accountWithNewChanges.getSecurityQuestion1();
    int newq2 = accountWithNewChanges.getSecurityQuestion2();
    String newa1 = accountWithNewChanges.getSecurityAnswer1();
    String newa2 = accountWithNewChanges.getSecurityAnswer2();
    int newID = accountWithNewChanges.getIDNum();
    String newFN = accountWithNewChanges.getFirstName();
    String newLN = accountWithNewChanges.getLastName();
    String newTitle = accountWithNewChanges.getTitle();
    int newPN = accountWithNewChanges.getPhoneNumber();
    boolean newActive = accountWithNewChanges.isActive();
    try {
      String query =
          "UPDATE account SET password = ?, email = ?, security_question_1 = ?, security_question_2 = ?, security_answer_1 = ?, security_answer_2 = ?, active = ?, \"IDNum\" = ?, \"firstName\" = ?, \"lastName\" = ?, title = ?, \"phoneNumber\" = ? WHERE username = ?";
      PreparedStatement pst = con.prepareStatement(query);
      pst.setString(1, newPass);
      pst.setString(2, newEmail);
      pst.setInt(3, newq1);
      pst.setInt(4, newq2);
      pst.setString(5, newa1);
      pst.setString(6, newa2);
      pst.setBoolean(7, newActive);
      pst.setInt(8, newID);
      pst.setString(9, newFN);
      pst.setString(10, newLN);
      pst.setString(11, newTitle);
      pst.setInt(12, newPN);
      pst.setString(13, uname);
      int rs = pst.executeUpdate();
      if (rs == 1) {
        result = true;
        int index = this.getIndex(uname);
        accounts.get(index).setPassword(newPass);
        accounts.get(index).setEmail(newEmail);
        accounts.get(index).setSecurityQuestion1(newq1);
        accounts.get(index).setSecurityQuestion2(newq2);
        accounts.get(index).setSecurityAnswer1(newa1);
        accounts.get(index).setSecurityAnswer2(newa2);
        accounts.get(index).setActive(newActive);
        accounts.get(index).setIDNum(newID);
        accounts.get(index).setFirstName(newFN);
        accounts.get(index).setLastName(newLN);
        accounts.get(index).setTitle(newTitle);
        accounts.get(index).setPhoneNumber(newPN);
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

  public boolean deleteRow(String uname) {
    Qdb qdb = Qdb.getInstance();
    boolean result = false;
    Connection con = GenDao.connect();
    try {
      String query = "DELETE FROM account WHERE username = ?";
      PreparedStatement pst = con.prepareStatement(query);
      pst.setString(1, uname);
      int rs = pst.executeUpdate();
      if (rs == 1) {
        result = true;
        int index = this.getIndex(uname);
        accounts.remove(index);
        System.out.println("Account deleted successfully!");
      } else {
        System.out.println("Failed to delete your account.");
      }
      con.close();
      pst.close();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    for (ServiceRequest sr : qdb.retrieveAllServiceRequests()) {
      if (sr.getRequester().getUsername().equals(uname)) {
        qdb.deleteServiceRequest(sr.getRequestID());
      }
      if (sr.getRequester().getUsername().equals(uname)) {
        ServiceRequest sr2 = sr;
        sr2.setAssignee(accounts.get(getIndex("admin")));
        qdb.updateServiceRequest(sr.getRequestID(), sr2);
      }
    }

    return result;
  }

  public boolean addRow(Account a) {
    String uname = a.getUsername();
    String pass = a.getPassword();
    String email = a.getEmail();
    int q1 = a.getSecurityQuestion1();
    int q2 = a.getSecurityQuestion2();
    String a1 = a.getSecurityAnswer1();
    String a2 = a.getSecurityAnswer2();
    boolean active = a.isActive();
    int ID = a.getIDNum();
    String FN = a.getFirstName();
    String LN = a.getLastName();
    String t = a.getTitle();
    int PN = a.getPhoneNumber();
    boolean result = false;
    Connection con = GenDao.connect();
    try {
      String query =
          "INSERT INTO account (username, password, email, security_question_1, security_question_2, security_answer_1, security_answer_2, active, \"IDNum\", \"firstName\", \"lastName\", title, \"phoneNumber\") VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
      PreparedStatement pst = con.prepareStatement(query);
      pst.setString(1, uname);
      pst.setString(2, pass);
      pst.setString(3, email);
      pst.setInt(4, q1);
      pst.setInt(5, q2);
      pst.setString(6, a1);
      pst.setString(7, a2);
      pst.setBoolean(8, active);
      pst.setInt(9, ID);
      pst.setString(10, FN);
      pst.setString(11, LN);
      pst.setString(12, t);
      pst.setInt(13, PN);
      int rs = pst.executeUpdate();
      if (rs == 1) {
        result = true;
        accounts.add(a);
        System.out.println("Account created successfully!");
      } else {
        System.out.println("Failed to create your account.");
      }
      con.close();
      pst.close();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return result;
  }

  @Override
  public List<Account> getAllRows() {
    return accounts;
  }

  public boolean populate() {
    Connection con = GenDao.connect();
    try {
      String query = "SELECT * FROM teamq.account";
      Statement st = con.createStatement();
      ResultSet rs = st.executeQuery(query);
      while (rs.next()) {
        Account a;
        a =
            new Account(
                rs.getString("username"),
                rs.getString("password"),
                rs.getString("email"),
                rs.getInt("security_question_1"),
                rs.getInt("security_question_2"),
                rs.getString("security_answer_1"),
                rs.getString("security_answer_2"),
                rs.getBoolean("active"),
                rs.getInt("IDNum"),
                rs.getString("firstName"),
                rs.getString("lastName"),
                rs.getString("title"),
                rs.getInt("phoneNumber"));
        accounts.add(a);
      }
      con.close();
      st.close();
      return true;
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return false;
  }

  public int getIndex(String uname) {
    for (int i = 0; i < accounts.size(); i++) {
      Account a = accounts.get(i);
      if (a.getUsername().equals(uname)) {
        return i;
      }
    }
    return -1;
  }

  public List<Integer> getIndexes(String email) {
    List<Integer> is = new ArrayList<Integer>();
    for (int i = 0; i < accounts.size(); i++) {
      Account a = accounts.get(i);
      if (a.getEmail().equals(email)) {
        is.add(i);
      }
    }
    return is;
  }

  public String getEmailWithUsername(String AUsername) {
    String TheEmail = "";
    for (int i = 0; i < accounts.size(); i++) {
      if (accounts.get(i).getUsername().equals(AUsername)) {
        TheEmail = accounts.get(i).getEmail();
      }
    }
    return TheEmail;
  }

  public int getQuestionId(String question) {
    int q = 0;
    Connection con = GenDao.connect();
    try {
      String query = "SELECT id FROM security_question WHERE question = ?";
      PreparedStatement pst = con.prepareStatement(query);
      pst.setString(1, question);
      ResultSet rs = pst.executeQuery();
      rs.next();
      q = rs.getInt(1);
      con.close();
      pst.close();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return q;
  }

  public ObservableList<String> getAllNames() {
    ObservableList<String> names = FXCollections.observableArrayList();
    for (Account p : accounts) {
      names.add(
          p.getUsername()
              + ", ("
              + p.getFirstName()
              + " "
              + p.getLastName()
              + ", "
              + p.getTitle()
              + ")");
    }
    return names;
  }
}
