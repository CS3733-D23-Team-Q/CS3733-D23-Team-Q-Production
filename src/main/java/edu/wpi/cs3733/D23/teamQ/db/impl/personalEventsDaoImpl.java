package edu.wpi.cs3733.D23.teamQ.db.impl;

import edu.wpi.cs3733.D23.teamQ.db.Qdb;
import edu.wpi.cs3733.D23.teamQ.db.dao.GenDao;
import edu.wpi.cs3733.D23.teamQ.db.obj.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class personalEventsDaoImpl implements GenDao<personalEvent, Integer> {
    private static personalEventsDaoImpl single_instance = null;
    private List<personalEvent> personalEvents = new ArrayList<personalEvent>();
    private String fileName = "personalEvent.csv";

    public static synchronized personalEventsDaoImpl getInstance() {
        if (single_instance == null) {
            single_instance = new personalEventsDaoImpl();
        }
        return single_instance;
    }

    private personalEventsDaoImpl() {
        populate();
    }

    public personalEvent retrieveRow(Integer personalEventID) {
        int index = this.getIndex(personalEventID);
        return personalEvents.get(index);
    }



    public boolean updateRow(Integer personalEventID, personalEvent a) {
        boolean result = false;
        Connection conn = GenDao.connect();
        try {
            String query =
                    "UPDATE \"personalEvent\" SET title = ?, date = ?, \"startTime\" = ?, \"endTime\" = ?, \"fullDay\" = ?, user = ? WHERE \"personalEventID\" = ?";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, a.getTitle());
            pst.setDate(2, (Date) a.getDate());
            pst.setString(3, a.getStartTime());
            pst.setString(4, a.getEndTime());
            pst.setBoolean(5, a.isFullDay());
            pst.setInt(6, a.getPersonalEventID());
            int rs = pst.executeUpdate();
            if (rs == 1) {
                result = true;
                int index = this.getIndex(personalEventID);
                personalEvents.set(index, a);
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

    public boolean deleteRow(Integer personalEventID) {
        Qdb qdb = Qdb.getInstance();
        boolean result = false;
        Connection con = GenDao.connect();
        try {
            String query = "DELETE FROM \'personalEvent\' WHERE \'personalEventID\' = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, personalEventID);
            int rs = pst.executeUpdate();
            if (rs == 1) {
                result = true;
                int index = this.getIndex(personalEventID);
                personalEvents.remove(index);
                System.out.println("event deleted successfully!");
            } else {
                System.out.println("Failed to delete your event.");
            }
            con.close();
            pst.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        return result;
    }

    public boolean addRow(personalEvent a) {
        Qdb qdb = Qdb.getInstance();
        boolean result = false;
        Connection conn = GenDao.connect();
        try {
            String query =
                    "INSERT INTO \'personalEvent\' (title, date, \'startTime\', \'endTime\', \'fullDay\', user) VALUES(?,?,?,?,?,?)";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, a.getTitle());
            pst.setDate(2, (Date) a.getDate());
            pst.setString(3, a.getStartTime());
            pst.setString(4, a.getEndTime());
            pst.setBoolean(5, a.isFullDay());
            pst.setString(6, a.getUser());
            int rs = pst.executeUpdate();
            if (rs == 1) {
                result = true;
                personalEvents.add(a);
                System.out.println("event created successfully!");
            } else {
                System.out.println("Failed to create your event.");
            }
            conn.close();
            pst.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    public List<personalEvent> getAllRows() {
        return personalEvents;
    }

    public boolean populate() {
        try {
            Connection con = GenDao.connect();
            personalEvents.clear();
            String query = "SELECT * FROM \'personalEvent\'";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                personalEvent a;
                a =
                        new personalEvent(
                                rs.getString("title"),
                                rs.getInt("personalEventID"),
                                rs.getDate("date"),
                                rs.getString("startTime"),
                                rs.getString("endTime"),
                                rs.getBoolean("fullDay"),
                                rs.getString("user"));
                personalEvents.add(a);
            }
            con.close();
            st.close();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public int getIndex(Integer id) {
        for (int i = 0; i < personalEvents.size(); i++) {
            personalEvent a = personalEvents.get(i);
            if (a.getPersonalEventID()==id) {
                return i;
            }
        }
        return -1;
    }

    public List<Integer> getIndexes(String user) {
        List<Integer> is = new ArrayList<Integer>();
        for (int i = 0; i < personalEvents.size(); i++) {
            personalEvent a = personalEvents.get(i);
            if (a.getUser().equals(user)) {
                is.add(i);
            }
        }
        return is;
    }
    public String getFileName() {
        return fileName;
    }
}
