package edu.wpi.cs3733.D23.teamQ.db.impl;

import edu.wpi.cs3733.D23.teamQ.db.dao.GenDao;
import edu.wpi.cs3733.D23.teamQ.db.obj.Account;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DateDaoImpl implements GenDao<Date, Integer> {
    static final String url = "jdbc:postgresql://database.cs.wpi.edu:5432/teamqdb";
    static final String user = "teamq";
    static final String password = "teamq140";
    private static DateDaoImpl single_instance = null;

    public static synchronized DateDaoImpl getInstance()
    {
        if (single_instance == null) single_instance = new DateDaoImpl();

        return single_instance;
    }

    private DateDaoImpl() {
        populate();
    }

    private List<Date> dates = new ArrayList<Date>();

    public Date retrieveRow(String uname) {
        int index = this.getIndex(uname);
        return dates.get(index);
    }

    public List<Date> retrieveRows(int dateID) {
        List<Date> as = new ArrayList<Date>();
        List<Integer> index = this.getIndexes(dateID);
        for (int i : index) {
            as.add(dates.get(i));
        }
        return as;
    }

    public boolean updateRow(int dateID, Date newDate) {
        boolean result = false;
        Connection con = GenDao.connect();
        int newMonth = newDate.getMonth();
        int newDay = newDate.getDay();
        int newYear = newDate.getYear();
        try {
            String query =
                    "UPDATE date SET month = ?, day = ?, year = ? WHERE \"dateID\" = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, newMonth);
            pst.setInt(2, newDay);
            pst.setInt(3, newYear);

            int rs = pst.executeUpdate();
            if (rs == 1) {
                result = true;
                int index = this.getIndex(dateID);
                dates.get(index).setMonth(newMonth);
                dates.get(index).setDay(newDay);
                dates.get(index).setYear(newYear);
                ///////////////////////////////////////////////////////////////////////////////////////
                System.out.println("Date updated successful!");
            } else {
                System.out.println("Failed to update date.");
            }
            con.close();
            pst.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    public boolean deleteRow(int dateID) {
        boolean result = false;
        Connection con = GenDao.connect();
        try {
            String query = "DELETE FROM date WHERE dateID = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, dateID);
            int rs = pst.executeUpdate();
            if (rs == 1) {
                result = true;
                int index = this.getIndex(dateID);
                dates.remove(index);
                System.out.println("Date deleted successful!");
            } else {
                System.out.println("Failed to delete date.");
            }
            con.close();
            pst.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    public boolean addRow(Date d) {
        int dateID = d.getDate();
        String pass = a.getPassword();
        String email = a.getEmail();
        int q1 = a.getSecurityQuestion1();
        int q2 = a.getSecurityQuestion2();
        String a1 = a.getSecurityAnswer1();
        String a2 = a.getSecurityAnswer2();
        boolean result = false;
        Connection con = GenDao.connect();
        try {
            String query =
                    "INSERT INTO account (username, password, email, security_question_1, security_question_2, security_answer_1, security_answer_2) VALUES(?,?,?,?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, uname);
            pst.setString(2, pass);
            pst.setString(3, email);
            pst.setInt(4, q1);
            pst.setInt(5, q2);
            pst.setString(6, a1);
            pst.setString(7, a2);
            int rs = pst.executeUpdate();
            if (rs == 1) {
                result = true;
                accounts.add(a);
                System.out.println("Account created successful!");
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
                                rs.getString("security_answer_2"));
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
}
