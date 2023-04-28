package edu.wpi.cs3733.D23.teamQ.db.impl;

import edu.wpi.cs3733.D23.teamQ.db.dao.GenDao;
import edu.wpi.cs3733.D23.teamQ.db.obj.ProfileImage;
import edu.wpi.cs3733.D23.teamQ.db.obj.Settings;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SettingsDaoImpl implements GenDao<Settings, String> {
    private List<Settings> settings = new ArrayList<Settings>();
    private static SettingsDaoImpl single_instance = null;
    private String fileName = "Settings.csv";

    public static synchronized SettingsDaoImpl getInstance() {
        if (single_instance == null) {
            try {
                single_instance = new SettingsDaoImpl();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return single_instance;
    }

    private SettingsDaoImpl() throws SQLException {
        populate();
    }

    @Override
    public List<Settings> getAllRows() {
        return settings;
    }

    public int getIndex(String uname) {
        for (int i = 0; i < settings.size(); i++) {
            Settings setting = settings.get(i);
            if (setting.getUsername().equals(uname)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Settings retrieveRow(String username) {
        int index = this.getIndex(username);
        return settings.get(index);
    }

    @Override
    public boolean updateRow(String ID, Settings x) throws SQLException {
        return false;
    }

    @Override
    public boolean deleteRow(String ID) throws SQLException {
        return false;
    }

    @Override
    public boolean addRow(Settings x) {
        return false;
    }

    @Override
    public boolean populate() throws SQLException {
        return false;
    }

    @Override
    public String getFileName() {
        return null;
    }
}
