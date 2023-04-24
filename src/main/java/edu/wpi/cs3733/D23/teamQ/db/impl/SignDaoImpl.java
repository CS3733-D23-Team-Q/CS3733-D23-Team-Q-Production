package edu.wpi.cs3733.D23.teamQ.db.impl;

import edu.wpi.cs3733.D23.teamQ.db.Qdb;
import edu.wpi.cs3733.D23.teamQ.db.dao.GenDao;

import edu.wpi.cs3733.D23.teamQ.db.obj.Sign;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SignDaoImpl implements GenDao<Sign, Integer>{
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

    public Sign retrieveRow(Integer kiosk) {
        int index = this.getIndex(kiosk);
        return signs.get(index);
    }


    public boolean updateRow(Integer kiosk, Sign SignWithNewChanges) {
        boolean result = false;
        Connection con = GenDao.connect();
        String newDate = SignWithNewChanges.getDate();
        String newDestination = SignWithNewChanges.getDestination();
        String newDirection = SignWithNewChanges.getDirection();

        try {
            String query =
                    "UPDATE sign SET date = ?, destination = ?, direction = ? WHERE kiosk = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, newDate);
            pst.setString(2, newDestination);
            pst.setString(3, newDirection);
            pst.setInt(4, kiosk);

            int rs = pst.executeUpdate();
            if (rs == 1) {
                result = true;
                int index = this.getIndex(kiosk);
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

    public boolean deleteRow(Integer kiosk) {
        Qdb qdb = Qdb.getInstance();
        boolean result = false;
        Connection con = GenDao.connect();
        try {
            String query = "DELETE FROM sign WHERE kiosk = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, kiosk);
            int rs = pst.executeUpdate();
            if (rs == 1) {
                result = true;
                int index = this.getIndex(kiosk);
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
                    "INSERT INTO account (kiosk, date, destination, direction) VALUES(?,?,?,?)";
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

    public int getIndex(Integer kiosk) {
        for (int i = 0; i < signs.size(); i++) {
            Sign a = signs.get(i);
            if (a.getKiosk()==kiosk) {
                return i;
            }
        }
        return -1;
    }








}
