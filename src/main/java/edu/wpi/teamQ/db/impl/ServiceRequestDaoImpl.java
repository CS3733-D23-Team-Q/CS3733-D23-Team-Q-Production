package edu.wpi.teamQ.db.impl;

import edu.wpi.teamQ.db.dao.GenDao;
import edu.wpi.teamQ.db.obj.ConferenceRequest;
import edu.wpi.teamQ.db.obj.FlowerRequest;
import edu.wpi.teamQ.db.obj.ServiceRequest;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ServiceRequestDaoImpl {
    private ObservableList<ServiceRequest> serviceRequests = FXCollections.observableArrayList();
    private GenDao conferenceRequestTable;
    private GenDao flowerRequestsTable;

    private static ServiceRequestDaoImpl single_instance = null;

    public static synchronized ServiceRequestDaoImpl getInstance(
            ConferenceRequestDaoImpl conferenceRequestTable, FlowerRequestDaoImpl flowerRequestTable) {
        if (single_instance == null)
            single_instance = new ServiceRequestDaoImpl(conferenceRequestTable, flowerRequestTable);

        return single_instance;
    }

    private ServiceRequestDaoImpl(GenDao conferenceRequestTable, GenDao flowerRequestsTable) {
        this.conferenceRequestTable = conferenceRequestTable;
        this.flowerRequestsTable = flowerRequestsTable;
        populate();
    }

    public boolean populate() {
        List<FlowerRequest> flowerRequests = flowerRequestsTable.getAllRows();
        List<ConferenceRequest> conferenceRequests = conferenceRequestTable.getAllRows();

        for (int i = 0; i < flowerRequests.size(); i++) {
            FlowerRequest fr = flowerRequests.get(i);
            ServiceRequest s =
                    new ServiceRequest(
                            fr.getRequestID(),
                            fr.getRequester(),
                            fr.progressToInt(fr.getProgress()),
                            fr.getAssignee(),
                            fr.getRoomNumber(),
                            fr.getSpecialInstructions());
            serviceRequests.add(s);
        }
        for (int i = 0; i < conferenceRequests.size(); i++) {
            ConferenceRequest cr = conferenceRequests.get(i);
            ServiceRequest s =
                    new ServiceRequest(
                            cr.getRequestID(),
                            cr.getRequester(),
                            cr.progressToInt(cr.getProgress()),
                            cr.getAssignee(),
                            cr.getRoomNumber(),
                            cr.getSpecialInstructions());
            serviceRequests.add(s);
        }
        return true;
    }

    public List<ServiceRequest> getAllRows() {
        return serviceRequests;
    }

    public ServiceRequest retrieveRow(Integer ID) {
        int index = getIndex(ID);
        return serviceRequests.get(index);
    }

    public int getIndex(int requestID) {
        populate();
        for (int i = 0; i < serviceRequests.size(); i++) {
            ServiceRequest sr = serviceRequests.get(i);
            if (sr.getRequestID() == requestID) {
                return i;
            }
        }
        return -1;
    }
}
