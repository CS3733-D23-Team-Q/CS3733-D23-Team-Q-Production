package edu.wpi.cs3733.D23.teamQ.db.obj;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class personalEvent {
    String title;
    int personalEventID;
    Date date;
    String startTime;
    String endTime;
    boolean fullDay;
    String user;

    public personalEvent(String title, int personalEventID, Date date, String startTime, String endTime, boolean fullDay, String user){
        this.title = title;
        this.personalEventID = personalEventID;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.fullDay = fullDay;
        this.user = user;
    }
    public personalEvent(String title, Date date, String startTime, String endTime, boolean fullDay, String user){
        this.title = title;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.fullDay = fullDay;
        this.user = user;
    }
}
