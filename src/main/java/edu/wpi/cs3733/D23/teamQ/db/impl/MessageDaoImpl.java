package edu.wpi.cs3733.D23.teamQ.db.impl;

import edu.wpi.cs3733.D23.teamQ.db.dao.GenDao;
import edu.wpi.cs3733.D23.teamQ.db.obj.Account;
import edu.wpi.cs3733.D23.teamQ.db.obj.Message;
import java.sql.*;
import java.util.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;

@Getter
public class MessageDaoImpl implements GenDao<Message, Integer> {
  private List<Message> messages = new ArrayList<>();
  private AccountDaoImpl accountTable;
  private static MessageDaoImpl single_instance = null;
  private String fileName = "Messages.csv";

  public static synchronized MessageDaoImpl getInstance(AccountDaoImpl accountTable) {
    if (single_instance == null) single_instance = new MessageDaoImpl(accountTable);

    return single_instance;
  }

  private MessageDaoImpl(AccountDaoImpl accountTable) {
    this.accountTable = accountTable;
    populate();
  }

  @Override
  public List<Message> getAllRows() {
    return null;
  }

  @Override
  public Message retrieveRow(Integer ID) {
    return null;
  }

  @Override
  public boolean updateRow(Integer ID, Message x) throws SQLException {
    return false;
  }

  public boolean updateRow(Message m) {
    try (Connection connection = GenDao.connect();
        PreparedStatement st =
            connection.prepareStatement(
                "UPDATE message SET read = ? WHERE timestamp = ? AND receiver = ?")) {

      st.setBoolean(1, true);
      st.setLong(2, m.getTimeStamp());
      st.setString(3, m.getReceiver().getUsername());

      st.executeUpdate();
      connection.close();
      st.close();
      return true;
    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    }
  }

  @Override
  public boolean deleteRow(Integer ID) throws SQLException {
    return false;
  }

  public boolean addRow(Message m) {
    try (Connection conn = GenDao.connect();
        PreparedStatement stmt =
            conn.prepareStatement(
                "INSERT INTO \"message\"(sender, receiver, message, timestamp) VALUES (?, ?, ?, ?)")) {
      stmt.setString(1, m.getSender().getUsername());
      stmt.setString(2, m.getReceiver().getUsername());
      stmt.setString(3, m.getMessage());
      stmt.setLong(4, m.getTimeStamp());
      stmt.executeUpdate();
    } catch (SQLException ex) {
      ex.printStackTrace();
    }
    return populate();
  }

  public boolean populate() {
    try {
      messages.clear();
      Connection conn = GenDao.connect();
      Statement stm = conn.createStatement();
      ResultSet rst = stm.executeQuery("Select * From \"message\"");
      while (rst.next()) {
        messages.add(
            new Message(
                accountTable.retrieveRow(rst.getString("sender")),
                accountTable.retrieveRow(rst.getString("receiver")),
                rst.getString("message"),
                rst.getLong("timestamp"),
                rst.getBoolean("read")));
      }
      conn.close();
      stm.close();
      return true;
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return false;
    }
  }

  public ObservableList<Message> retrieveMessages(String p1, String p2) {
    ObservableList<Message> messageList = FXCollections.observableArrayList();
    String[] people = {p1, p2};
    Arrays.sort(people);
    for (Message m : messages) {
      String uname1 = m.getSender().getUsername();
      String uname2 = m.getReceiver().getUsername();
      String[] people2 = {uname1, uname2};
      Arrays.sort(people2);
      if (people[0].equals(people2[0]) && people[1].equals(people2[1])) {
        messageList.add(m);
      }
    }
    return messageList;
  }

  public ObservableList<Message> retrieveConversations(String person) {
    ObservableList<Message> messageList = FXCollections.observableArrayList();
    for (Account a : accountTable.getAllRows()) {
      List<Message> recent = retrieveMessages(a.getUsername(), person);
      if (recent.size() > 0) {
        Message m = recent.get(recent.size() - 1);
        if (!messageList.contains(m)) {
          messageList.add(m);
        }
      }
    }
    sortByTimestamp(messageList);
    return messageList;
  }

  public int getNumUnread(String username) {
    int unread = 0;
    for (Message m : messages) {
      if (!m.getRead() && m.getReceiver().getUsername().equals(username)) {
        unread++;
      }
    }
    return unread;
  }

  public static void sortByTimestamp(List<Message> list) {
    Collections.sort(list, (o1, o2) -> Long.compare(o2.getTimeStamp(), o1.getTimeStamp()));
  }
}
