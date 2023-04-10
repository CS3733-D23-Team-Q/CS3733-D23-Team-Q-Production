package edu.wpi.cs3733.D23.teamQ.db.obj;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Date
{
    private int dateID;
    private int month;
    private int day;
    private int year;

    public Date(int dateID, int month, int day, int year)
    {
        this.dateID=dateID;
        this.month=month;
        this.day=day;
        this.year=year;
    }

    public void setDay(int day) {
        this.day = day;
    }
}
