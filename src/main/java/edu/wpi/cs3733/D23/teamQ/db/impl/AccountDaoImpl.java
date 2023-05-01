package edu.wpi.cs3733.D23.teamQ.db.impl;

import edu.wpi.cs3733.D23.teamQ.db.Qdb;
import edu.wpi.cs3733.D23.teamQ.db.dao.GenDao;
import edu.wpi.cs3733.D23.teamQ.db.obj.Account;
import edu.wpi.cs3733.D23.teamQ.db.obj.ProfileImage;
import edu.wpi.cs3733.D23.teamQ.db.obj.ServiceRequest;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import lombok.Getter;

@Getter
public class AccountDaoImpl implements GenDao<Account, String> {
  private static AccountDaoImpl single_instance = null;
  private List<Account> accounts = new ArrayList<Account>();
  private String fileName = "Account.csv";

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

  public boolean updateRow(String uname, Account a) {
    boolean result = false;
    Connection conn = GenDao.connect();
    try {
      String query =
          "UPDATE account SET password = ?, email = ?, security_question_1 = ?, security_question_2 = ?, security_answer_1 = ?, security_answer_2 = ?, active = ?, \"IDNum\" = ?, \"firstName\" = ?, \"lastName\" = ?, title = ?, \"phoneNumber\" = ?, notes = ?, todo = ? WHERE username = ?";
      PreparedStatement pst = conn.prepareStatement(query);
      pst.setString(1, a.getPassword());
      pst.setString(2, a.getEmail());
      pst.setInt(3, a.getSecurityQuestion1());
      pst.setInt(4, a.getSecurityQuestion2());
      pst.setString(5, a.getSecurityAnswer1());
      pst.setString(6, a.getSecurityAnswer2());
      pst.setBoolean(7, a.isActive());
      pst.setInt(8, a.getIDNum());
      pst.setString(9, a.getFirstName());
      pst.setString(10, a.getLastName());
      pst.setString(11, a.getTitle());
      pst.setString(12, a.getPhoneNumber());
      pst.setString(13, a.getNotes());
      pst.setString(14, a.getTodo());
      pst.setString(15, uname);
      int rs = pst.executeUpdate();
      if (rs == 1) {
        result = true;
        int index = this.getIndex(uname);
        accounts.set(index, a);
        System.out.println("Updated successfully!");
      } else {
        System.out.println("Failed to update.");
      }
      conn.close();
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
    Qdb qdb = Qdb.getInstance();
    boolean result = false;
    Connection conn = GenDao.connect();
    try {
      String query =
          "INSERT INTO account (username, password, email, security_question_1, security_question_2, security_answer_1, security_answer_2, active, \"IDNum\", \"firstName\", \"lastName\", title, \"phoneNumber\") VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
      PreparedStatement pst = conn.prepareStatement(query);
      pst.setString(1, a.getUsername());
      pst.setString(2, a.getPassword());
      pst.setString(3, a.getEmail());
      pst.setInt(4, a.getSecurityQuestion1());
      pst.setInt(5, a.getSecurityQuestion2());
      pst.setString(6, a.getSecurityAnswer1());
      pst.setString(7, a.getSecurityAnswer2());
      pst.setBoolean(8, a.isActive());
      pst.setInt(9, getAllRows().get(getAllRows().size() - 1).getIDNum() + 1);
      pst.setString(10, a.getFirstName());
      pst.setString(11, a.getLastName());
      pst.setString(12, a.getTitle());
      pst.setString(13, a.getPhoneNumber());
      int rs = pst.executeUpdate();
      if (rs == 1) {
        result = true;
        accounts.add(a);

        Image image = new Image(AccountDaoImpl.class.getResourceAsStream("/default-profile.png"));
        byte[] imageData = qdb.convertImageToBytea(image);
        ProfileImage newProfileImage = new ProfileImage(a.getUsername(), imageData);
        qdb.addProfileImage(newProfileImage);

        System.out.println("Account created successfully!");
      } else {
        System.out.println("Failed to create your account.");
      }
      conn.close();
      pst.close();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return result;
  }

  public List<Account> getAllRows() {
    return accounts;
  }

  public boolean populate() {
    try {
      Connection con = GenDao.connect();
      accounts.clear();
      String query = "SELECT * FROM account";
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
                rs.getString("phoneNumber"),
                rs.getString("notes"),
                rs.getString("todo"));
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

  public Account getAccountFromUN(String UN) {
    Account a = new Account("null");
    for (int i = 0; i < accounts.size(); i++) {
      if (accounts.get(i).getUsername().equals(UN)) {
        return accounts.get(i);
      }
    }
    return a;
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
    try {
      Connection con = GenDao.connect();
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
