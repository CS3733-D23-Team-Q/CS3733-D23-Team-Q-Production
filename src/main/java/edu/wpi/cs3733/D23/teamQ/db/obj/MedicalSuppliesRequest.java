package edu.wpi.cs3733.D23.teamQ.db.obj;

import edu.wpi.cs3733.D23.teamQ.db.dao.IServiceRequest;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class MedicalSuppliesRequest extends ServiceRequest implements IServiceRequest{
    private String item;
    private int quantity;

    public MedicalSuppliesRequest(
            int requestID,
            String requester,
            int progress,
            String assignee,
            Node node,
            String specialInstructions,
            Date date,
            String time,
            String item,
            int quantity) {
        super(requestID, requester, progress, assignee, node, specialInstructions, date, time);
        this.item = item;
        this.quantity = quantity;
    }

    public MedicalSuppliesRequest(
            String requester,
            int progress,
            String assignee,
            Node node,
            String specialInstructions,
            Date date,
            String time,
            String item,
            int quantity) {
        super(0, requester, progress, assignee, node, specialInstructions, date, time);
        this.item = item;
        this.quantity = quantity;
    }

    public int progressToInt(ServiceRequest.Progress progress) {
        if (progress == ServiceRequest.Progress.BLANK) {
            return 0;
        } else if (progress == ServiceRequest.Progress.PROCESSING) {
            return 1;
        } else {
            return 2;
        }
    }
}
