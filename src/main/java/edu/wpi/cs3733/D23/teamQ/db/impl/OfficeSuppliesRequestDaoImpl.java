package edu.wpi.cs3733.D23.teamQ.db.impl;

import edu.wpi.cs3733.D23.teamQ.db.dao.GenDao;
import edu.wpi.cs3733.D23.teamQ.db.obj.OfficeSuppliesRequest;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OfficeSuppliesRequestDaoImpl implements GenDao<OfficeSuppliesRequest, Integer> {
    private List<OfficeSuppliesRequest> officeSuppliesRequests =
            new ArrayList<OfficeSuppliesRequest>();
    private int nextID = 0;
    private NodeDaoImpl nodeTable;
    private AccountDaoImpl accountTable;
    private static OfficeSuppliesRequestDaoImpl single_instance = null;

    public static synchronized OfficeSuppliesRequestDaoImpl getInstance(
            AccountDaoImpl accountTable, NodeDaoImpl nodeTable) {
        if (single_instance == null)
            single_instance = new OfficeSuppliesRequestDaoImpl(accountTable, nodeTable);

        return single_instance;
    }

    private OfficeSuppliesRequestDaoImpl(AccountDaoImpl accountTable, NodeDaoImpl nodeTable) {
        this.nodeTable = nodeTable;
        this.accountTable = accountTable;
        populate();
        if (officeSuppliesRequests.size() != 0) {
            nextID = officeSuppliesRequests.get(officeSuppliesRequests.size() - 1).getRequestID() + 1;
        }
    }

    /**
     * returns a officeSuppliesRequest given a requestID
     *
     * @param requestID of officeSuppliesRequest being retrieved
     * @return a officeSuppliesRequest with the given nodeID
     */
    public OfficeSuppliesRequest retrieveRow(Integer requestID) {
        try {
            int index = this.getIndex(requestID);
            return officeSuppliesRequests.get(index);
        } catch (Exception e) {
            System.out.println("No request found with ID: " + requestID);
        }
        return null;
    }

    /**
     * updates officeSuppliesRequest in list with a new officeSuppliesRequest
     *
     * @param requestID  requestID of officeSuppliesRequest being replaced
     * @param newRequest new officeSuppliesRequest being inserted
     * @return true if successful
     */
    public boolean updateRow(Integer requestID, OfficeSuppliesRequest newRequest) {
        try (Connection connection = GenDao.connect();
             PreparedStatement st =
                     connection.prepareStatement(
                             "UPDATE \"officeSuppliesRequest\" SET \"requestID\" = ?, requester = ?, progress = ?, assignee = ?, \"nodeID\" = ?, item = ?, quantity = ?, \"specialInstructions\" = ? "
                                     + "WHERE \"requestID\" = ?")) {

            st.setInt(1, requestID);
            st.setString(2, newRequest.getRequester().getUsername());
            st.setInt(3, newRequest.getProgress().ordinal());
            st.setString(4, newRequest.getAssignee().getUsername());
            st.setInt(5, newRequest.getNode().getNodeID());
            st.setString(6, newRequest.getItem());
            st.setInt(7, newRequest.getQuantity());
            st.setString(8, newRequest.getSpecialInstructions());

            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        int index = this.getIndex(requestID);
        officeSuppliesRequests.get(index).setRequester(newRequest.getRequester());
        officeSuppliesRequests.get(index).setProgress(newRequest.getProgress());
        officeSuppliesRequests.get(index).setAssignee(newRequest.getAssignee());
        officeSuppliesRequests.get(index).setNode(newRequest.getNode());
        officeSuppliesRequests.get(index).setSpecialInstructions(newRequest.getSpecialInstructions());
        officeSuppliesRequests.get(index).setItem(newRequest.getItem());
        officeSuppliesRequests.get(index).setQuantity(newRequest.getQuantity());

        return true;
    }

    /**
     * deletes officeSuppliesRequest from list of officeSuppliesRequests
     *
     * @param requestID of officeSuppliesRequest being deleted
     * @return true if successfully deleted
     */
    public boolean deleteRow(Integer requestID) {
        try (Connection connection = GenDao.connect();
             PreparedStatement st =
                     connection.prepareStatement(
                             "DELETE FROM \"officeSuppliesRequest\" WHERE \"requestID\" = ?")) {
            ;
            st.setInt(1, requestID);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        int index = this.getIndex(requestID);
        officeSuppliesRequests.remove(index);

        return true;
    }

    /**
     * adds a officeSuppliesRequest to the list
     *
     * @param request officeSuppliesRequest being added
     * @return true if successful
     */
    public boolean addRow(OfficeSuppliesRequest request) {
        try (Connection conn = GenDao.connect();
             PreparedStatement stmt =
                     conn.prepareStatement(
                             "INSERT INTO \"officeSuppliesRequest\"(requester, progress, assignee, \"nodeID\", \"specialInstructions\", \"date\", \"time\", \"item\", \"quantity\") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)")) {
            stmt.setString(1, request.getRequester().getUsername());
            stmt.setInt(2, request.progressToInt(request.getProgress()));
            stmt.setString(3, request.getAssignee().getUsername());
            stmt.setInt(4, request.getNode().getNodeID());
            stmt.setString(5, request.getSpecialInstructions());
            stmt.setDate(6, request.getDate());
            stmt.setString(7, request.getTime());
            stmt.setString(8, request.getItem());
            stmt.setInt(9, request.getQuantity());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        request.setRequestID(nextID);
        nextID++;
        return officeSuppliesRequests.add(request);
    }

    @Override
    public boolean populate() {
        try {
            Connection conn = GenDao.connect();
            Statement stm = conn.createStatement();
            ResultSet rst = stm.executeQuery("Select * From \"officeSuppliesRequest\"");
            while (rst.next()) {
                officeSuppliesRequests.add(
                        new OfficeSuppliesRequest(
                                rst.getInt("requestID"),
                                nodeTable.retrieveRow(rst.getInt("nodeID")),
                                accountTable.retrieveRow(rst.getString("requester")),
                                accountTable.retrieveRow(rst.getString("assignee")),
                                rst.getString("specialInstructions"),
                                rst.getDate("date"),
                                rst.getString("time"),
                                rst.getInt("progress"),
                                rst.getString("item"),
                                rst.getInt("quantity")));
            }
            conn.close();
            stm.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    /**
     * gets index of given request in the list of requests
     *
     * @param requestID requestID being checked
     * @return value of index
     */
    private int getIndex(Integer requestID) {
        for (int i = 0; i < officeSuppliesRequests.size(); i++) {
            OfficeSuppliesRequest x = officeSuppliesRequests.get(i);
            if (x.getRequestID() == (Integer) requestID) {
                return i;
            }
        }
        throw new RuntimeException("No request found with ID " + requestID);
    }

    /**
     * function that gets all office supplies requests in the list
     *
     * @return all office supplies requests in list
     */
    public List<OfficeSuppliesRequest> getAllRows() {
        return officeSuppliesRequests;
    }

    public List<OfficeSuppliesRequest> listConferenceRequests(String username) {
        List<OfficeSuppliesRequest> list = new ArrayList<OfficeSuppliesRequest>();
        for (OfficeSuppliesRequest request : officeSuppliesRequests) {
            if (request.getRequester().equals(username)) {
            }
        }
        return list;
    }
}
