/*
package edu.wpi.cs3733.D23.teamQ.db.impl;

import edu.wpi.cs3733.D23.teamQ.db.dao.GenDao;
import edu.wpi.cs3733.D23.teamQ.db.obj.Person;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PersonDaoImpl implements GenDao<Person, Integer> {

  private List<Person> people = new ArrayList<Person>();

  private static PersonDaoImpl single_instance = null;

  public static synchronized PersonDaoImpl getInstance() {
    if (single_instance == null) single_instance = new PersonDaoImpl();

    return single_instance;
  }

  private PersonDaoImpl() {
    populate();
  }

  public Person retrieveRow(Integer IDNum) {
    int index = this.getIndex(IDNum);
    return people.get(index);
  }

  public boolean updateRow(Integer IDNum, Person personWithNewChanges) {
    boolean result = false;
    Connection con = GenDao.connect();
    String newFN = personWithNewChanges.getFirstName();
    String newLN = personWithNewChanges.getLastName();
    String newTitle = personWithNewChanges.getTitle();
    int newPN = personWithNewChanges.getPhoneNumber();
    String newUN = personWithNewChanges.getUsername();

    try {
      String query =
          "UPDATE person SET \"firstName\" = ?, \"lastName\" = ?, title = ?, \"phoneNumber\"= ?, username=? WHERE \"IDNum\" = ?";
      PreparedStatement pst = con.prepareStatement(query);

      pst.setString(1, newFN);
      pst.setString(2, newLN);
      pst.setString(3, newTitle);
      pst.setInt(4, newPN);
      pst.setString(5, newUN);
      pst.setInt(6, IDNum);

      int rs = pst.executeUpdate();
      if (rs == 1) {
        result = true;
        int index = this.getIndex(IDNum);
        people.get(index).setFirstName(newFN);
        people.get(index).setLastName(newLN);
        people.get(index).setTitle(newTitle);
        people.get(index).setPhoneNumber(newPN);
        people.get(index).setUsername(newUN);
        System.out.println("Updated successfully!");

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

  public boolean deleteRow(Integer IDNum) {
    boolean result = false;
    Connection con = GenDao.connect();
    try {
      String query = "DELETE FROM person WHERE IDNum = ?";
      PreparedStatement pst = con.prepareStatement(query);
      pst.setInt(1, IDNum);
      int rs = pst.executeUpdate();
      if (rs == 1) {
        result = true;
        int index = this.getIndex(IDNum);
        people.remove(index);
        System.out.println("Person deleted successful!");
      } else {
        System.out.println("Failed to delete your Person.");
      }
      con.close();
      pst.close();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return result;
  }

  public boolean addRow(Person a) {
    int IDNum = a.getIDNum();
    String FirstName = a.getFirstName();
    String LastName = a.getLastName();
    String Title = a.getTitle();
    int PhoneNumber = a.getPhoneNumber();
    String username = a.getUsername();
    boolean result = false;
    Connection con = GenDao.connect();
    try {
      String query =
          "INSERT INTO person (\"IDNum\", \"firstName\", \"lastName\", title, \"phoneNumber\", username) VALUES(?,?,?,?,?,?)";
      PreparedStatement pst = con.prepareStatement(query);
      pst.setInt(1, IDNum);
      pst.setString(2, FirstName);
      pst.setString(3, LastName);
      pst.setString(4, Title);
      pst.setInt(5, PhoneNumber);
      pst.setString(6, username);
      int rs = pst.executeUpdate();
      if (rs == 1) {
        result = true;
        people.add(a);
        System.out.println("Person created successful!");
      } else {
        System.out.println("Failed to create your person.");
      }
      con.close();
      pst.close();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }

    return result;
  }

  @Override
  public List<Person> getAllRows() {
    return people;
  }

  public boolean populate() {
    Connection con = GenDao.connect();
    try {
      String query = "SELECT * FROM person";
      Statement st = con.createStatement();
      ResultSet rs = st.executeQuery(query);
      while (rs.next()) {
        Person a;
        a =
            new Person(
                rs.getInt("IDNum"),
                rs.getString("firstName"),
                rs.getString("lastName"),
                rs.getString("title"),
                rs.getInt("phoneNumber"),
                rs.getString("username"));
        people.add(a);
      }
      con.close();

      st.close();
      return true;
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return false;
  }

  public ObservableList<String> getAllNames() {
    ObservableList<String> names = FXCollections.observableArrayList();
    for (Person p : people) {
      names.add(p.getFirstName() + " " + p.getLastName() + ", " + p.getTitle());
    }
    return names;
  }

  public Person getPersonWithUsername(String UN) {
    Person b = new Person(null, null, null, 0, UN);
    for (int i = 0; i < people.size(); i++) {
      Person a = people.get(i);
      if (Objects.equals(a.getUsername(), UN)) {
        return a;
      }
    }
    addRow(b);
    return b;
  }

  public void test() {}

  public int getIndex(int IDNum) {
    for (int i = 0; i < people.size(); i++) {
      Person a = people.get(i);
      if (a.getIDNum() == (IDNum)) {
        return i;
      }
    }
    return -1;
  }
}
*/
