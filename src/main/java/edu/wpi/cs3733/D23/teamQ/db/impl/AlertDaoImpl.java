package edu.wpi.cs3733.D23.teamQ.db.impl;

import edu.wpi.cs3733.D23.teamQ.db.obj.Alert;
import edu.wpi.cs3733.D23.teamQ.db.dao.GenDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlertDaoImpl {
    private static AlertDaoImpl single_instance = null;
    private List<Alert> alerts = new ArrayList<>();

    public static synchronized AlertDaoImpl getInstance() {
        if (single_instance == null) {
            single_instance = new AlertDaoImpl();
        }
        return single_instance;
    }

    private AlertDaoImpl() {
        populate();
    }

    public Alert retrieveRow(int ID) {
        int index = this.getIndex(ID);
        return alerts.get(index);
    }

    public boolean updateRow(Integer alertID, Alert a) {
        try (Connection connection = GenDao.connect();
             PreparedStatement st =
                     connection.prepareStatement(
                             "UPDATE \"alert\" SET \"alertID\" = ?, timestamp = ?, message = ? "
                                     + "WHERE \"requestID\" = ?")) {

            st.setInt(1, alertID);
            st.setLong(2, a.getTimestamp());
            st.setString(3, a.getMessage());

            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        int index = this.getIndex(alertID);
        alerts.set(index, a);

        return true;
    }

    public boolean deleteRow(Integer alertID) {
        try (Connection conn = GenDao.connect();
             PreparedStatement stmt =
                     conn.prepareStatement("DELETE FROM alert WHERE \"alertID\" = ?")) {
            stmt.setInt(1, alertID);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        int index = this.getIndex(alertID);
        alerts.remove(index);
        return true;
    }

    public boolean addRow(Alert a) {
        boolean result = false;
        Connection conn = GenDao.connect();
        try {
            String query =
                    "INSERT INTO alert (\"alertID\", timestamp, message) VALUES(?,?,?)";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setInt(1, a.getAlertID());
            pst.setLong(2, a.getTimestamp());
            pst.setString(3, a.getMessage());
            int rs = pst.executeUpdate();
            if (rs == 1) {
                result = true;
                alerts.add(a);
                System.out.println("Alert created successfully!");
            } else {
                System.out.println("Failed to create your alert.");
            }
            conn.close();
            pst.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    public boolean populate() {
        try {
            Connection con = GenDao.connect();
            alerts.clear();
            String query = "SELECT * FROM alert";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Alert a = new Alert(
                                rs.getInt("alertID"),
                                rs.getLong("timestamp"),
                                rs.getString("message"));
                alerts.add(a);
            }
            con.close();
            st.close();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public List<Alert> getAllRows() {
        return alerts;
    }

    public int getIndex(int ID) {
        for (int i = 0; i < alerts.size(); i++) {
            Alert a = alerts.get(i);
            if (a.getAlertID() == ID) {
                return i;
            }
        }
        return -1;
    }
}
